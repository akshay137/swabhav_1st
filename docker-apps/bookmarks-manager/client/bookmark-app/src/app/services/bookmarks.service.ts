import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { StorageService } from './storage.service';
import { Constant } from './constant';

@Injectable({
	providedIn: 'root'
})
export class BookmarksService {

	readonly baseURL: string = `${Constant.BASE_API}/api/bm/users`;

	constructor(
		private http: HttpClient,
		private storage: StorageService
	) { }

	public getRecent(user_id: string): Promise<any> {
		return new Promise((resolve, reject) => {
			this.http.get(`${this.baseURL}/${user_id}/bookmarks/recent/10`)
				.subscribe(res => {
					resolve(res);
				}, err => {
					reject(err);
				});
		})
	}

	public getCategories(user_id: string): Promise<any> {
		return new Promise((resolve, reject) => {
			this.http.get(`${this.baseURL}/${user_id}/category/`)
				.subscribe(res => {
					resolve(res);
				}, err => {
					reject(err);
				});
		});
	}

	public getBookmarks(user_id: string, cat_id: string): Promise<any> {
		return new Promise((resolve, reject) => {
			this.http.get(
				`${this.baseURL}/${user_id}/bookmarks/category/${cat_id}`)
				.subscribe(res => {
					resolve(res)
				}, err => {
					reject(err)
				});
		});
	}

	public addCategory(user_id: string, name: string): Promise<any> {
		return new Promise((resolve, reject) => {
			let data = new HttpParams()
			data = data.set('name', name)
			this.http.post(`${this.baseURL}/${user_id}/category/`, data)
				.subscribe(res => {
					resolve(res);
				}, err => {
					reject(err);
				});
		});
	}

	public deleteCategory(user_id: string, cat_id: string): Promise<any> {
		return new Promise((resolve, reject) => {
			this.http.delete(`${this.baseURL}/${user_id}/category/${cat_id}`)
				.subscribe(res => {
					resolve(res);
				}, err => {
					reject(err);
				});
		});
	}

	public addBookmark(user_id: string, name: string, url: string, cat_id: string)
		: Promise<any> {
		return new Promise((resolve, reject) => {
			let data = new HttpParams();
			data = data.set('name', name);
			data = data.set('url', url);
			data = data.set('category_id', cat_id);
			this.http.post(`${this.baseURL}/${user_id}/bookmarks/`, data)
				.subscribe(res => {
					resolve(res);
				}, err => {
					reject(err);
				});
		});
	}
}
