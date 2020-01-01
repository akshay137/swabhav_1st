import { Pipe, PipeTransform } from '@angular/core';

import { Product } from './productdata.service';

@Pipe({ name: 'filterByName' })
export class FilterByName implements PipeTransform {
	transform(value: Product[], name: string): Product[] {
		return value.filter(product => product.productName.toLowerCase().includes(name.toLowerCase()));
	}
}