import { Component, OnInit, Input } from '@angular/core';

@Component({
	selector: 'app-loaderview',
	templateUrl: './loaderview.component.html',
	styleUrls: ['./loaderview.component.css']
})
export class LoaderviewComponent implements OnInit {
	@Input() loaded: boolean;

	constructor() {
		console.log(this.loaded);
	}

	ngOnInit() {
		console.log(this.loaded);
	}

}
