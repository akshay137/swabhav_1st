import { Pipe, PipeTransform } from '@angular/core';

import { Fact } from '../numberapi/numberapi.component';

@Pipe({ name: 'oddOrAll' })
export class OddOrAll implements PipeTransform {
	transform(facts: Fact[], shouldFilter: boolean): Fact[] {
		if (shouldFilter)
			return facts.filter(fact => fact.num % 2 != 0);
		return facts;
	}
}