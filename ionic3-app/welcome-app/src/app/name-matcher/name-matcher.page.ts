import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-name-matcher',
	templateUrl: './name-matcher.page.html',
	styleUrls: ['./name-matcher.page.scss'],
})
export class NameMatcherPage implements OnInit {

	person1: string;
	person2: string;
	match: number;

	constructor() { }

	ngOnInit() {
		this.match = 0;
		this.person1 = '';
		this.person2 = '';
	}

	calculateMatch() {
		// console.log('matching', this.person1, this.person2);
		let chars = (this.person1.toLocaleLowerCase()
			+ this.person2.toLocaleLowerCase()).split('');
		this.match = chars.reduce((acc, cur) => {
			return acc + cur.charCodeAt(0);
		}, 0);
		this.match %= 101;
	}

}
