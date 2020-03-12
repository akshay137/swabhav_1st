import { Injectable } from '@angular/core';

@Injectable({
	providedIn: 'root'
})
export class StorageService {

	constructor() { }

	public setCache(key: string, data: string) {
		// let str = JSON.stringify(data)
		sessionStorage.setItem(key, data)
	}

	public getCache(key: string): string {
		return sessionStorage.getItem(key)
	}
}

export class STORAGE {
	static readonly USER_ID: string = 'user_id'
}