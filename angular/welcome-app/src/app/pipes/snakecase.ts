import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'snakeCase' })
export class SnakeCase implements PipeTransform {
	transform(value: string): string {
		return value.replace(/ /g, '_');
	}
}