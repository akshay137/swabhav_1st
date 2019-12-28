import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

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
	@Output('stateChange') stateChange: EventEmitter<string>;

	constructor() {
		console.log(this.onColor, this.offColor);
		this.onColor = 'green';
		this.offColor = 'red';
		this.currentColor = this.offColor;
		console.log(this.onColor, this.offColor);
		this.isOff = true;
		this.stateChange = new EventEmitter<string>();
	}

	ngOnInit() {
		console.log(this.onColor, this.offColor);
	}

	handleClick() {
		this.isOff = !this.isOff;
		this.currentColor = this.isOff ? this.offColor : this.onColor;
		// console.log(this.currentColor);
		this.stateChange.emit(`state changed to ${this.isOff ? 'off' : 'on'}`);
	}

}
