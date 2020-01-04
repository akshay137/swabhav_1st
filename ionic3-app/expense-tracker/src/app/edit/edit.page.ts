import { Component, OnInit } from '@angular/core';
import { ExpensesService, Expense, Category } from '../services/expenses.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

@Component({
	selector: 'app-edit',
	templateUrl: './edit.page.html',
	styleUrls: ['./edit.page.scss'],
})
export class EditPage implements OnInit {

	expense: Expense;
	title: string;
	categories: string[];

	constructor(private expensesvc: ExpensesService,
		private route: ActivatedRoute,
		private router: Router) {
		this.expense = this.expensesvc.getEmpty();
		this.categories = this.expensesvc.getCategories();
		this.title = 'Edit';
	}

	ngOnInit() {
		this.loadExpense();
	}

	loadExpense() {
		this.route.paramMap.subscribe((parms: ParamMap) => {
			this.expensesvc.getExpenseById(parseInt(parms.get('id')))
				.subscribe(res => {
					this.expense = res;
					this.title = res.description;
				}, err => {
					console.log(err);
				});
		})
	}

	deleteExpense() {
		if (confirm('Are you sure?')) {
			this.expensesvc.deleteExpense(this.expense.id)
				.subscribe(res => {
					console.log(res);
					this.router.navigate(['/home']);
				}, err => {
					console.log(err);
					this.router.navigate(['/home']);
				});
		}
	}

	updateExpense() {
		let res = this.expensesvc.isValid(this.expense);
		if (res.valid) {
			this.expensesvc.updateExpense(this.expense.id, this.expense)
				.subscribe(res => {
					console.log(res);
					this.router.navigate(['/home']);
				}, err => {
					console.log(err);
				});
		} else {
			alert(res.msg);
		}
	}

}
