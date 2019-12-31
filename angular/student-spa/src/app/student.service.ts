import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError, observable } from 'rxjs';

const api = 'http://gsmktg.azurewebsites.net/api/v1/techlabs/test/students/';

@Injectable({
	providedIn: 'root'
})
export class StudentService {
	students: Student[];

	constructor(private http: HttpClient) { }

	getStudents() {
		return new Observable<Student[]>((observer) => {
			this.http.get(api).subscribe((data: Student[]) => {
				console.log(data);
				observer.next(data);
			}, (err) => {
				console.log('Error:', err);
				return throwError(`Something went wrong. ${err.statusText}`);
			});
		});
	}

	addStudent(student: Student) {
		return new Observable<string>((observer) => {
			this.http.post(api, student).subscribe(res => {
				observer.next('' + res);
			}, err => {
				console.log('Error:', err);
				return throwError(`Something went wrong. ${err.statusText}`);
			});
		});
	}

	updateStudent(student: Student) {
		return new Observable<string>(observer => {
			this.http.put(`${api}${student.id}`, student)
				.subscribe(res => {
					observer.next('' + res);
				}, err => {
					console.log('Error in update', err);
					return throwError(`Something went wrong. ${err.statusText}`);
				});
		});
	}

	deleteStudent(id: string) {
		return new Observable<string>(observer => {
			this.http.delete(`${api}${id}`)
				.subscribe(res => {
					observer.next('' + res);
				}, err => {
					console.log('Error in update', err);
					return throwError(`Something went wrong. ${err.statusText}`);
				});
		});
	}

	getStudentById(id: string) {
		return new Observable<Student>(observer => {
			if (this.students) {
				let st = this.students.find(student => student.id == id);
				if (st != null) {
					observer.next(st);
					return;
				}
			}
			this.http.get<Student>(`${api}${id}`).subscribe(res => {
				console.log(res);
				observer.next(res[0]);
			}, err => {
				return throwError(`Something went wrong: ${err.statusText}`);
			});
		});
	}
}

export interface Student {
	id: string;
	name: string;
	date: string;
	age: number;
	email: string;
	isMale: boolean;
	rollNo: number;
}