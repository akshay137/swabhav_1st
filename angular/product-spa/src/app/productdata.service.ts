import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subscriber } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class ProductDataService {
	private products: Product[];

	constructor(private http: HttpClient) {
		this.products = [];
	}

	getEmpty(): Product {
		return {
			productId: 0,
			productName: '',
			description: '',
			imageUrl: '',
			imgSrc: '',
			price: 0,
			productCode: '',
			releaseDate: '',
			starRating: 0
		}
	}

	getProducts() {
		return new Observable<Product[]>(observer => {
			this.http.get('assets/products.jsonx')
				.subscribe((res: Product[]) => {
					this.products = res;
					observer.next(this.products);
					observer.complete();
				}, err => {
					observer.error({
						msg: 'Something went terribly wrong',
						errTxt: err.statusText
					});
				});
		});
	}

	getProductById(id: number) {
		return new Observable<Product>(observer => {
			if (this.products.length != 0) {
				this.findProduct(id, observer);
			} else {
				this.getProducts().subscribe(res => {
					this.findProduct(id, observer);
				}, err => {
					observer.error(err);
					observer.complete();
				})
			}
		});
	}

	private findProduct(id: number, observer: Subscriber<Product>) {
		let p = this.products.find(prdct => prdct.productId == id);
		if (p) {
			observer.next(p);
			observer.complete();
		} else {
			observer.error({
				msg: 'No product found',
				errTxt: `${id} does not belong to any product.`
			});
			observer.complete();
		}
	}
}

export interface Product {
	productId: number;
	productName: string;
	productCode: string;
	releaseDate: string;
	description: string;
	price: number;
	starRating: number;
	imageUrl: string;
	imgSrc: string;
}