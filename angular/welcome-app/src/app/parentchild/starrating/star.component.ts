import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
	selector: 'app-star',
	templateUrl: './star.component.html',
	styleUrls: ['./star.component.css']
})
export class StarComponent implements OnInit {

	@Input('rating') rating: number;
	@Output('onMouseOver') onMouseOver: EventEmitter<number>;

	constructor() {
		this.onMouseOver = new EventEmitter<number>();
	}

	ngOnInit() {
	}

	onHover(e) {
		console.log('hovered', e.layerX, e.layerY);
		this.onMouseOver.emit(this.rating);
	}

}
