import { Injectable } from '@angular/core';

@Injectable({
	providedIn: 'root'
})
export class LoggerService {

	constructor() {
		console.log('LoggerService constructor');
	}

	log(msg: any) {
		console.log(msg);
	}
}
