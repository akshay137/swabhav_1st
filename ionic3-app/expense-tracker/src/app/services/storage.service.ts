import { Injectable } from '@angular/core';

import { Storage } from '@ionic/storage';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class StorageService {

	private EXPENSE_KEY = 'expenses';

	constructor(private storage: Storage) { }

	saveData(key: string, value: any) {
		this.storage.set(key, value);
	}

	getData(key: string) {
		return new Observable(observer => {
			this.storage.get(key).then(res => {
				observer.next(res);
				observer.complete();
			}, err => {
				observer.error({ msg: 'Something went wrong', err: err });
				observer.complete();
			});
		});
	}

	getStoredExpenses() {
		return new Observable(observer => {
			this.storage.get(this.EXPENSE_KEY).then(res => {
				observer.next(res);
				observer.complete();
			}, err => {
				observer.error({ msg: 'Something went wrong', err: err });
				observer.complete();
			});
		});
	}

	saveExpenses(expenses: any) {
		this.saveData(this.EXPENSE_KEY, expenses);
	}
}
