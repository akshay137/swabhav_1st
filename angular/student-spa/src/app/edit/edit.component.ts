import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';

import { Student, StudentService } from "../student.service";
import { catchError } from 'rxjs/operators';

@Component({
	selector: 'app-edit',
	templateUrl: './edit.component.html',
	styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
	student: Student;
	loaded: boolean;

	constructor(private route: ActivatedRoute, private router: Router,
		private studentsvc: StudentService) {
	}

	ngOnInit() {
		this.student = this.studentsvc.getEmpty();
		this.loaded = false;
		this.route.paramMap.subscribe((params: ParamMap) => {
			let id = params.get('id');
			this.studentsvc.getStudentById(id)
				.subscribe((res: Student) => {
					console.log(res);
					this.student = res;
					this.loaded = true;
				}, err => {
					this.loaded = true;
					console.log('Error:', err);
					alert(err);
					this.router.navigate(['/home']);
					return null;
				})
		});
	}

	editStudent() {
		console.log(this.student);
		this.loaded = false;
		this.studentsvc.updateStudent(this.student)
			.subscribe(res => {
				console.log(res);
				this.loaded = true;
				this.router.navigate(['/home']);
			}, err => {
				console.log(err);
				this.loaded = true;
				alert('Something went wrong');
				return null;
			});
	}

}
