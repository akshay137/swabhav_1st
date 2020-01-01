import { Component, OnInit } from '@angular/core';
import { Product, ProductDataService } from '../productdata.service';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';

@Component({
	selector: 'app-productdetail',
	templateUrl: './productdetail.component.html',
	styleUrls: ['./productdetail.component.css']
})
export class ProductDetailComponent implements OnInit {
	product: Product;
	loading: boolean;

	constructor(private productsvc: ProductDataService,
		private route: ActivatedRoute,
		private router: Router) {
		this.product = productsvc.getEmpty();
		this.loading = false;
	}

	ngOnInit() {
		this.route.paramMap.subscribe((params: ParamMap) => {
			this.loading = true;
			let id = parseInt(params.get('id'));
			if (isNaN(id)) {
				alert('Wrong product');
				this.loading = false;
				this.router.navigate(['/list']);
				return;
			}
			this.productsvc.getProductById(id).subscribe(res => {
				this.loading = false;
				this.product = res;
			}, err => {
				alert(err.msg);
				this.loading = false;
				this.router.navigate(['list']);
			})
		})
	}

}
