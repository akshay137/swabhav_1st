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

	loaded: boolean;

	constructor(private route: ActivatedRoute, private router: Router,
		private studentsvc: StudentService) {
	}

	ngOnInit() {
		if (confirm('Are you sure')) {
			this.loaded = false;
			this.route.paramMap.subscribe((params: ParamMap) => {
				let id = params.get('id');
				this.studentsvc.deleteStudent(id)
					.subscribe(res => {
						console.log(res);
						this.loaded = true;
						this.router.navigate(['/home']);
					}, err => {
						console.log('error', err);
						alert(err);
						this.loaded = true;
						this.router.navigate(['/home']);
						return null;
					});
			});
		} else {
			this.router.navigate(['/home']);
			// this.loaded = true;
		}
	}

}
