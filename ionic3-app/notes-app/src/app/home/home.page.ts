import { Component, OnInit, ViewChild } from '@angular/core';
import { NotesService, Note } from '../services/notes.service';
import { IonReorderGroup } from '@ionic/angular';

@Component({
	selector: 'app-home',
	templateUrl: 'home.page.html',
	styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit {
	notes: Note[];
	@ViewChild(IonReorderGroup, { static: false }) reorderGroup: IonReorderGroup;

	constructor(private notesvc: NotesService) {
	}

	ngOnInit() {
		this.notesvc.getAllNotes().subscribe(res => {
			this.notes = res;
		}, err => {
			alert(err.msg);
		});
	}

	delete(id: number) {
		if (confirm('Are you sure?')) {
			this.notesvc.deleteNote(id).subscribe(res => {
				alert('Note deleted');
			}, err => {
				alert(err.msg);
			});
		}
	}

	onReorder(event) {
		console.log(event.detail);
		this.switchNotes(event.detail.from, event.detail.to);
		// this.reorderGroup.disabled = true;
		event.detail.complete();
	}

	toggleReorder() {
		this.reorderGroup.disabled = !this.reorderGroup.disabled;
	}

	private switchNotes(from: number, to: number) {
		let note1 = this.notes[from];
		let note2 = this.notes[to];
		this.notesvc.changeOrder(note1.id, note2.id).subscribe(res => {
			console.log('reordered');
		}, err => {
			console.log(err);
			alert('Reordring falied due to internal issue');
		});
	}
}
