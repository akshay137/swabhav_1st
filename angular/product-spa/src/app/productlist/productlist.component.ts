import { Component, OnInit } from '@angular/core';

import { Product, ProductDataService } from '../productdata.service';

@Component({
	selector: 'app-productlist',
	templateUrl: './productlist.component.html',
	styleUrls: ['./productlist.component.css']
})
export class ProductListComponent implements OnInit {

	products: Product[];
	showImage: boolean;
	loading: boolean;
	filter: string;

	constructor(private productdatasvc: ProductDataService) {
		this.products = [];
		this.showImage = true;
		this.loading = false;
		this.filter = '';
	}

	ngOnInit() {
		this.loading = true;
		this.productdatasvc.getProducts().subscribe((res: Product[]) => {
			this.products = res;
			this.loading = false;
		}, err => {
			alert(err.msg);
			this.loading = false;
		});
	}

}
