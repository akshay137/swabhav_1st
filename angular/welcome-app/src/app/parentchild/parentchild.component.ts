import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-parentchild',
	templateUrl: './parentchild.component.html',
	styleUrls: ['./parentchild.component.css']
})
export class ParentchildComponent implements OnInit {

	starRating: number;
	hovered: boolean;

	constructor() {
		this.hovered = false;
	}

	ngOnInit() {
	}

	stateChangeHandler(e): void {
		console.log('state changed:', e);
	}

	onHoverStar(e) {
		console.log(e);
		this.starRating = e;
		this.hovered = true;
	}

}
