import { Component, OnInit } from '@angular/core';
import { ExpensesService, Expense, Category } from '../services/expenses.service';

@Component({
	selector: 'app-home',
	templateUrl: 'home.page.html',
	styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit {
	expenses: Expense[];

	constructor(private expensesvc: ExpensesService) {
		this.expenses = [];
	}

	ngOnInit() {
		this.loadExpenses();
	}

	loadExpenses() {
		this.expensesvc.getAllExpenses()
			.subscribe(res => {
				this.expenses = res;
			}, err => {
				console.log(err);
			});
	}

	getName(cat: Category) {
		return Category[cat];
	}

}
