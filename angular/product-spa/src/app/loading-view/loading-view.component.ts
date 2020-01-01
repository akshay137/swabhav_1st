import { Component, OnInit, Input } from '@angular/core';

@Component({
	selector: 'app-loading-view',
	templateUrl: './loading-view.component.html',
	styleUrls: ['./loading-view.component.css']
})
export class LoadingViewComponent implements OnInit {

	@Input() loading: boolean;

	constructor() {
		this.loading = false;
	}

	ngOnInit() {
	}

}
