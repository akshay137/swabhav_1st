import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const api = 'http://gsmktg.azurewebsites.net/api/v1/techlabs/test/students/';

@Injectable({
	providedIn: 'root'
})
export class StudentService {
	students: Student[];

	constructor(private http: HttpClient) { }

	getStudents() {
		return new Observable<Student[]>((observer) => {
			this.http.get(api).subscribe((data: any) => {
				console.log(data);
				observer.next(data);
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