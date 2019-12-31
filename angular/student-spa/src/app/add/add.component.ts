import { Component, OnInit } from '@angular/core';
import { StudentService, Student } from '../student.service';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
	selector: 'app-add',
	templateUrl: './add.component.html',
	styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

	loaded: boolean;
	student: Student;
	constructor(private studentsvc: StudentService, private router: Router) { }

	ngOnInit() {
		this.student = this.studentsvc.getEmpty();
		this.loaded = true;
	}

	addStudent() {
		console.log(this.student);
		this.loaded = false;
		this.studentsvc.addStudent(this.student).subscribe(res => {
			console.log(res);
			this.loaded = true;
			this.router.navigate(['/home']);
		}, err => {
			console.log('error', err);
			alert('something went wrong');
			this.loaded = true;
			this.router.navigate(['/home']);
			return null;
		});
	}

}
