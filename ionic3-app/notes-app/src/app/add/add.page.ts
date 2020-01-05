import { Component, OnInit } from '@angular/core';
import { NotesService, Note } from '../services/notes.service';
import { Router } from '@angular/router';

@Component({
	selector: 'app-add',
	templateUrl: './add.page.html',
	styleUrls: ['./add.page.scss'],
})
export class AddPage implements OnInit {

	note: Note;
	constructor(private notesvc: NotesService,
		private router: Router) { }

	ngOnInit() {
		this.note = this.notesvc.getEmpty();
	}

	addNote() {
		console.log('Adding', this.note);
		let res = this.notesvc.validate(this.note);
		if (!res.isValid) {
			alert(res.msg);
			return;
		}
		this.notesvc.addNote(this.note).subscribe(res => {
			alert('Note added successfully');
			this.router.navigate(['/home']);
		}, err => {
			alert(err.msg);
		})
	}

}
