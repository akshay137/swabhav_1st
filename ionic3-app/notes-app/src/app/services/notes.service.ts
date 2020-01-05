import { Injectable } from '@angular/core';

import { StorageService } from './storage.service';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class NotesService {
	notes: Note[];
	newOrder: number;

	constructor(private storage: StorageService) {
		this.notes = [];
		this.newOrder = 0;
	}

	private getMaxOrder() {
		this.notes.forEach(note => {
			if (this.newOrder < note.order)
				this.newOrder = note.order;
		})
	}

	getAllNotes() {
		return new Observable<Note[]>(observer => {
			this.storage.getNotes().subscribe((res: Note[]) => {
				this.notes = res;
				if (!this.notes)
					this.notes = [];
				this.getMaxOrder();
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
		note.order = ++this.newOrder;
		this.notes.push(note);
		return this.saveNotes();
	}

	private copyNote(src: Note, dst: Note) {
		dst.id = src.id;
		dst.order = src.order;
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
			order: 0,
			description: '',
			title: ''
		}
	}

	validate(note: Note) {
		if (typeof (note.title) != 'string' || note.title.length <= 0)
			return { isValid: false, msg: 'title is empty' };
		if (typeof (note.order) != 'number' || note.order < 0)
			return { isValid: false, msg: 'Order is not valid' };
		return { isValid: true, msg: 'Everythin seems to be OK' };
	}

	changeOrder(from: number, to: number) {
		return new Observable(observer => {
			if (this.notes.length == 0) {
				observer.error({ msg: 'No notes to reorder' });
				observer.complete();
			} else {
				let n1 = this.notes.find(note => note.id === from);
				if (!n1) {
					observer.error({ msg: 'No note found' });
					observer.complete();
					return;
				}
				let n2 = this.notes.find(note => note.id === to);
				if (!n2) {
					observer.error({ msg: 'No note found' });
					observer.complete();
					return;
				}
				let temp = n1.order;
				n1.order = n2.order;
				n2.order = temp;
				this.notes = this.notes.sort((n1, n2) => {
					return Math.min(Math.max(n1.order - n2.order, -1), 1);
				})
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
	order: number;
	title: string;
	description: string;
}