import { Component, OnInit } from '@angular/core';
import { ExpensesService, Expense, Category } from '../services/expenses.service';
import { Router } from '@angular/router';

@Component({
	selector: 'app-new',
	templateUrl: './new.page.html',
	styleUrls: ['./new.page.scss'],
})
export class NewPage implements OnInit {

	expense: Expense;
	categories: string[];

	constructor(private expensesvc: ExpensesService,
		private router: Router) {
		this.expense = this.expensesvc.getEmpty();
		this.categories = Object.keys(Category).filter((val, i, arr) => {
			return i >= arr.length / 2;
		});
	}

	ngOnInit() {
	}

	addExpense() {
		console.log('adding');
		this.expensesvc.addExpense(this.expense)
			.subscribe(res => {
				// console.log(res);
				this.router.navigate(['/home']);
			}, err => {
				console.log(err);
			});
	}

}
