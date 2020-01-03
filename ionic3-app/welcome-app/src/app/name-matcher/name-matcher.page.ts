import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';

import { Contacts, Contact } from '@ionic-native/contacts';
import { timer } from 'rxjs';
import { take } from 'rxjs/operators';

@Component({
	selector: 'app-name-matcher',
	templateUrl: './name-matcher.page.html',
	styleUrls: ['./name-matcher.page.scss'],
})
export class NameMatcherPage implements OnInit {

	person1: string;
	person2: string;
	match: number;

	@ViewChild('cv', { static: true }) cv: ElementRef;

	constructor(private contacts: Contacts) { }

	ngOnInit() {
		this.match = 0;
		this.person1 = '';
		this.person2 = '';
		// this.doCanvasStuff();
	}

	doCanvasStuff() {
		let ctx = this.cv.nativeElement.getContext('2d');
		ctx.clearRect(0, 0, this.cv.nativeElement.width,
			this.cv.nativeElement.height);
		ctx.fillStyle = '#000';
		ctx.fillRect(0, 0, 50, 50);
		let x = 0;
		timer(0, 33).subscribe(res => {
			ctx.clearRect(0, 0, this.cv.nativeElement.width,
				this.cv.nativeElement.height);
			ctx.fillRect(x, 0, 50, 50);
			x = (x + 10) % this.cv.nativeElement.width;
		})
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

	reset() {
		if (confirm('Are you sure?')) {
			this.person2 = '';
			this.person1 = '';
			this.calculateMatch();
			// alert('resetting');
		}
	}

	getContact(id: number) {
		console.log('Getting contact for:', id);
		console.log(this.contacts);
		this.contacts.pickContact()
			.then((res: Contact) => {
				console.log(res);
				if (id == 1) {
					this.person1 = res.name.givenName;
				} else {
					this.person2 = res.name.givenName;
				}
				this.calculateMatch();
			}, err => {
				console.log(err);
			});
	}

}
