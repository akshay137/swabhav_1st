import { Component, OnInit } from '@angular/core';
import { StudentService, Student } from '../student.service';
import { catchError } from 'rxjs/operators';

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

	loaded: boolean;
	students: Student[];
	currStudent: Student;

	constructor(private studentsvc: StudentService) {
		this.students = [];
		this.currStudent = this.studentsvc.getEmpty();
	}

	ngOnInit() {
		this.loaded = false;
		this.studentsvc.getStudents()
			.subscribe((data: Student[]) => {
				this.students = data;
				this.loaded = true;
			}, err => {
				console.log(err);
				alert('Somthing went wrong');
				this.loaded = true;
			});
	}

	setCurrentStudent(student: Student) {
		this.currStudent = student;
	}

}