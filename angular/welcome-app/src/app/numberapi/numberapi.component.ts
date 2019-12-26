import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import * as moment from 'moment';

@Component({
	selector: 'app-numberapi',
	templateUrl: './numberapi.component.html',
	styleUrls: ['./numberapi.component.css']
})
export class NumberapiComponent implements OnInit {
	link: string;
	n: number;
	facts: Fact[];
	loading: boolean;

	constructor(private http: HttpClient) {
		this.link = 'http://numbersapi.com/';
		this.facts = [];
		this.loading = false;
	}

	ngOnInit() {
		// TODO
	}

	allreadySearched(num): boolean {
		let i = this.facts.findIndex(fact => fact.num == num);
		// console.log('index', i);
		return i != -1;
	}

	hitAPI() {
		if (this.allreadySearched(this.n)) {
			console.log('already searched');
			alert(`You already searched fact for ${this.n}`);
			return;
		}
		this.loading = true;
		setTimeout(() => { this.loading = false; }, 2000);
		this.http.get(`${this.link}${this.n}`, { responseType: 'text' })
			.subscribe((data: any) => {
				console.log(data);
				this.loading = false;
				if (!this.allreadySearched(this.n))
					this.facts.push({ num: this.n, fact: data, time: moment() });
				// console.log(this.facts);
			});
	}

	onKeyUp(event: any) {
		// console.log(event.key);
		if (event.key == "Enter") {
			this.hitAPI();
		}
	}

}

export interface Fact {
	num: number;
	fact: string;
	time?: moment.Moment;
}