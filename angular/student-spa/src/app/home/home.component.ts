import { Component, OnInit } from '@angular/core';
import { StudentService, Student } from '../student.service';
import { catchError } from 'rxjs/operators';

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

	students: Student[];

	constructor(private studentsvc: StudentService) {
		this.students = [];
	}

	ngOnInit() {
		this.studentsvc.getStudents()
			.pipe(catchError(err => {
				console.log(err);
				return null;
			})).subscribe((data: Student[]) => {
				this.students = data;
			}, err => {
				console.log(err);
			});
	}

}