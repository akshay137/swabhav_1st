import { Injectable } from '@angular/core';

import { StorageService } from './storage.service';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class NotesService {
	notes: Note[];

	constructor(private storage: StorageService) {
		this.notes = [];
	}

	getAllNotes() {
		return new Observable<Note[]>(observer => {
			this.storage.getNotes().subscribe((res: Note[]) => {
				this.notes = res;
				if (!this.notes)
					this.notes = [];
				observer.next(this.notes);
				observer.complete();
			}, err => {
				observer.error(err);
				observer.complete();
			});
		});
	}

	getNoteById(id: number) {
		return new Observable<Note>(observer => {
			if (this.notes.length > 0) {
				let note = this.notes.find(n => n.id === id);
				if (!note) {
					observer.error({ msg: 'No note found' });
				} else {
					observer.next(note);
				}
				observer.complete();
			} else {
				this.getAllNotes().subscribe(res => {
					let note = this.notes.find(n => n.id === id);
					if (!note) {
						observer.error({ msg: 'No note found' });
					} else {
						observer.next(note);
					}
					observer.complete();
				}, err => {
					observer.error(err);
					observer.complete();
				});
			}
		});
	}

	saveNotes() {
		return new Observable(observer => {
			this.storage.saveNotes(this.notes).subscribe(res => {
				observer.next(res);
				observer.complete();
			}, err => {
				observer.error(err);
				observer.complete();
			});
		})
	}

	addNote(note: Note) {
		note.id = Date.now();
		this.notes.push(note);
		return this.saveNotes();
	}

	private copyNote(src: Note, dst: Note) {
		dst.id = src.id;
		dst.description = src.description;
		dst.title = src.title;
	}

	updateNote(id: number, note: Note) {
		let oldnote = this.notes.find(n => n.id === id);
		if (!oldnote) {
			return new Observable(observer => {
				observer.error({ msg: 'No note found' });
				observer.complete();
			});
		}

		this.copyNote(note, oldnote);
		return this.storage.saveNotes(this.notes);

	}

	getEmpty(): Note {
		return {
			id: 0,
			description: '',
			title: ''
		}
	}

	validate(note: Note) {
		if (typeof (note.title) != 'string' || note.title.length <= 0)
			return { isValid: false, msg: 'title is empty' };
		return { isValid: true, msg: 'Everythin seems to be OK' };
	}

	changeOrder(from: number, to: number) {
		return new Observable(observer => {
			if (this.notes.length == 0) {
				observer.error({ msg: 'No notes to reorder' });
				observer.complete();
			} else {
				if (from == to) {
					observer.complete();
					return;
				}
				if (from >= this.notes.length || to >= this.notes.length) {
					observer.error('No such note was found');
					observer.complete();
					return;
				}
				if (from > to) {
					this.notes.splice(to, 0, this.notes[from]);
					this.notes.splice(from + 1, 1);
				} else {
					this.notes.splice(from, 0, this.notes[to]);
					this.notes.splice(to + 1, 1);
				}
				this.saveNotes().subscribe(res => {
					observer.next(res);
					observer.complete();
				}, err => {
					observer.error({ msg: 'Error during saving new order' });
					observer.complete();
				});
			}
		});
	}

	deleteNote(id: number) {
		return new Observable(observer => {
			if (this.notes.length == 0) {
				observer.error({ msg: 'No notes to delete' });
				return;
			}
			let index = this.notes.findIndex(note => note.id === id);
			if (index == -1) {
				observer.error({ msg: `No note found for id: ${id}` });
				return;
			}
			this.notes.splice(index, 1);
			this.saveNotes().subscribe(res => {
				observer.next(res);
				observer.complete();
			}, err => {
				observer.error(err);
			});
		});
	}
}

export interface Note {
	id: number;
	title: string;
	description: string;
}