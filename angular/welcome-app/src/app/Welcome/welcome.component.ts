import { Component } from '@angular/core'

@Component({
	selector: 'sw-welcome',
	templateUrl: './welcome.component.html'
})
export class WelcomeComponent {
	message = "Welcome message"

	greetings = ['You should be sleeping right now',
		'Good Morning', 'Good Afternoon', 'Good Evening'];

	greetMe(): String {
		let time = new Date();
		let h = time.getHours();
		// let h = 18;
		return this.greetings[Math.floor(h / 6)];
	}

	get greet() {
		return 'hello';
	}
}
