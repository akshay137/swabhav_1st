import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { StudentService } from '../student.service';
import { catchError } from 'rxjs/operators';

@Component({
	selector: 'app-delete',
	templateUrl: './delete.component.html',
	styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {

	constructor(private route: ActivatedRoute, private router: Router,
		private studentsvc: StudentService) {
	}

	ngOnInit() {
		this.route.paramMap.subscribe((params: ParamMap) => {
			let id = params.get('id');
			this.studentsvc.deleteStudent(id)
				.pipe(catchError(err => {
					console.log('error', err);
					return null;
				}))
				.subscribe(res => {
					console.log(res);
					this.router.navigate(['/home']);
				})
		});
	}

}
