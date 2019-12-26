import { Component, OnInit, Input } from '@angular/core';

@Component({
	selector: 'app-toggle',
	templateUrl: './toggle.component.html',
	styleUrls: ['./toggle.component.css']
})
export class ToggleComponent implements OnInit {

	currentColor: string;
	@Input('onColor') onColor: string;
	@Input('offColor') offColor: string;
	isOff: boolean;

	constructor() {
		console.log(this.onColor, this.offColor);
		this.onColor = 'green';
		this.offColor = 'red';
		this.currentColor = this.offColor;
		console.log(this.onColor, this.offColor);
		this.isOff = true;
	}

	ngOnInit() {
		console.log(this.onColor, this.offColor);
	}

	handleClick() {
		this.isOff = !this.isOff;
		this.currentColor = this.isOff ? this.offColor : this.onColor;
		// console.log(this.currentColor);
	}

}
