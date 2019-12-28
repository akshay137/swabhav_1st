import { Directive, ElementRef } from '@angular/core';

@Directive({
	selector: '[highlightText]'
})
export class HighlightTextDirective {

	constructor(el: ElementRef) {
		console.log(el);
		el.nativeElement.style.backgroundColor = 'yellow';
	}

}
