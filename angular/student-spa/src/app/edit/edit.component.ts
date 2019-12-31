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

	constructor(private route: ActivatedRoute, private router: Router,
		private studentsvc: StudentService) {
	}

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
		this.route.paramMap.subscribe((params: ParamMap) => {
			let id = params.get('id');
			this.studentsvc.getStudentById(id)
				.pipe(
					catchError(err => {
						console.log('Error:', err);
						this.router.navigate(['/home']);
						return null;
					})
				).subscribe(res => {
					console.log(res);
					this.student = res;
				})
		});
	}

	editStudent() {
		console.log(this.student);
		this.studentsvc.updateStudent(this.student)
			.pipe(catchError(err => {
				console.log(err);
				return null;
			}))
			.subscribe(res => {
				console.log(res);
				this.router.navigate(['/home']);
			});
	}

}
