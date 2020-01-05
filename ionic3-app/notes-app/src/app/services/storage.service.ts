import { Injectable } from '@angular/core';

import { Storage } from '@ionic/storage';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class StorageService {

	private NOTES_KEY = 'notes';
	constructor(private storage: Storage) { }

	getNotes() {
		return new Observable(observer => {
			this.storage.get(this.NOTES_KEY).then(res => {
				observer.next(res);
				observer.complete();
			}, err => {
				observer.error({ msg: 'Something went wrong', err: err });
				observer.complete();
			});
		});
	}

	saveNotes(notes: any) {
		return new Observable(observer => {
			this.storage.set(this.NOTES_KEY, notes).then(res => {
				observer.next(res);
				observer.complete();
			}, err => {
				observer.error({ msg: 'Something went wrong', err: err });
				observer.complete();
			});
		});
	}
}
