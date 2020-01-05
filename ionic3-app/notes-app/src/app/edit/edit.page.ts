import { Component, OnInit } from '@angular/core';
import { NotesService, Note } from '../services/notes.service';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';

@Component({
	selector: 'app-edit',
	templateUrl: './edit.page.html',
	styleUrls: ['./edit.page.scss'],
})
export class EditPage implements OnInit {

	note: Note;
	constructor(private notesvc: NotesService,
		private route: ActivatedRoute,
		private router: Router) {
		// TODO
	}

	ngOnInit() {
		this.note = this.notesvc.getEmpty();
		this.route.paramMap.subscribe((param: ParamMap) => {
			this.notesvc.getNoteById(parseInt(param.get('id')))
				.subscribe(res => {
					this.note = res;
				}, err => {
					alert(err.msg);
					this.router.navigate(['/home']);
				})
		});
	}

	editNote() {
		let res = this.notesvc.validate(this.note);
		if (!res.isValid) {
			alert(res.msg);
			return;
		}
		this.notesvc.updateNote(this.note.id, this.note)
			.subscribe(res => {
				alert('Note updated successfully');
				this.router.navigate(['/home']);
			}, err => {
				alert(err.msg);
			})
	}

}
