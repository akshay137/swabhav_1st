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

	student: Student;
	constructor(private studentsvc: StudentService, private router: Router) { }

	ngOnInit() {
		this.student = {
			id: '',
			name: '',
			age: 0,
			date: '',
			email: '',
			isMale: true,
			rollNo: 0
		};
	}

	addStudent() {
		console.log(this.student);
		this.studentsvc.addStudent(this.student).pipe(catchError(err => {
			console.log('error', err);
			alert('aomething went wrong');
			return null;
		})).subscribe(res => {
			console.log(res);
			this.router.navigate(['/home']);
		});
	}

}
