import { Component, OnInit } from '@angular/core';
import { StudentService, Student } from '../student.service';

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

	students: Student;

	constructor(private studentsvc: StudentService) { }

	ngOnInit() {
		this.studentsvc.getStudents().subscribe((data: any) => {
			this.students = data;
		});
	}

}