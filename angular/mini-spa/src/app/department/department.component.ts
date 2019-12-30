import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

@Component({
	selector: 'app-department',
	templateUrl: './department.component.html',
	styleUrls: ['./department.component.css']
})
export class DepartmentComponent implements OnInit {

	deptId: number;
	constructor(private route: ActivatedRoute, private router: Router) { }

	ngOnInit() {
		// console.log(this.route.paramMap);
		// never changes
		// this.deptId = parseInt(this.route.snapshot.paramMap.get('deptId'));

		// changing parameters
		this.route.paramMap.subscribe((params: ParamMap) => {
			this.deptId = parseInt(params.get('deptId'));
			console.log('deptId', this.deptId);
			if (isNaN(this.deptId)) {
				console.log('sending back');
				this.router.navigate(['/notfound']);
			}
		});
	}

}