import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Constant } from './constant';
import { URLSearchParams } from 'url';

@Injectable({
	providedIn: 'root'
})
export class AuthService {

	private baseURL: string = `${Constant.BASE_API}/api/bm/users`;

	constructor(
		private http: HttpClient
	) { }

	login(email: string, password: string): Promise<any> {
		return new Promise((resolve, reject) => {
			let data = new HttpParams()
			data = data.set('email', email)
			data = data.set('password', password)
			let headers = new HttpHeaders()
			this.http.post(`${this.baseURL}/login/`, data, {
				headers: headers
			}).subscribe(res => {
				resolve(res)
			}, err => {
				reject(err)
			})
		})
	}

	register(name: string, email: string, password: string): Promise<any> {
		return new Promise((resolve, reject) => {
			let data = new HttpParams()
			data = data.set('username', name)
			data = data.set('email', email)
			data = data.set('password', password)
			this.http.post(`${this.baseURL}/register/`, data)
				.subscribe(res => {
					resolve(res)
				}, err => {
					reject(err)
				});
		})
	}

	getUser(user_id: string): Promise<any> {
		return new Promise((resolve, reject) => {
			this.http.get(`${this.baseURL}/${user_id}`)
				.subscribe(res => {
					resolve(res)
				}, err => {
					reject(err)
				});
		})
	}
}
