import { Injectable } from '@angular/core';
import { StorageService } from './storage.service';
import { Observable, observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class ExpensesService {

	list: Expense[];
	categories: string[];

	constructor(private storagesvc: StorageService) {
		this.list = [];
		this.categories = Object.keys(Category).filter((val, i, arr) => {
			return i >= arr.length / 2;
		});
	}

	getCategories() {
		return this.categories;
	}

	getEmpty(): Expense {
		return {
			id: 0,
			amount: 0,
			category: Category.Misc,
			date: null,
			description: ''
		}
	}

	getAllExpenses() {
		return new Observable<Expense[]>(observer => {
			this.storagesvc.getStoredExpenses()
				.subscribe((res: Expense[]) => {
					this.list = res;
					console.log(res);
					if (this.list == null)
						this.list = [];
					observer.next(this.list);
				}, err => {
					console.log(err);
					alert(err.msg);
				});
		});
	}

	getExpenseById(id: number) {
		return new Observable<Expense>(observer => {
			let expense = this.list.find(e => id == e.id);
			if (expense) {
				observer.next(expense);
				observer.complete();
			}
			else {
				observer.error({
					msg: 'No Expense found'
				});
				observer.complete();
			}
		});
	}

	addExpense(expense: Expense) {
		return new Observable(observer => {
			this.list.push(expense);
			this.storagesvc.saveExpenses(this.list);
			observer.next();
			observer.complete();
		})
	}

	deleteExpense(id: number) {
		return new Observable(observer => {
			let i = this.list.findIndex(e => e.id == id);
			if (i == -1) {
				observer.error({ msg: 'No expense found' });
				observer.complete();
			} else {
				this.list.splice(i, 1);
				this.storagesvc.saveExpenses(this.list);
				observer.next();
				observer.complete();
			}
		});
	}

	private copyExpense(src: Expense, dst: Expense) {
		dst.id = src.id;
		dst.amount = src.amount;
		dst.category = src.category;
		dst.date = src.date;
		dst.description = src.description
	}

	updateExpense(id: number, expense: Expense) {
		return new Observable(observer => {
			let exp = this.list.find(e => id == e.id);
			if (exp) {
				this.copyExpense(expense, exp);
				this.storagesvc.saveExpenses(this.list);
				observer.next();
				observer.complete();
			} else {
				observer.error({ msg: 'No Expense found' });
				observer.complete();
			}
		});
	}

	isValid(expense: Expense) {
		if (expense.amount <= 0) {
			return { valid: false, msg: 'amount should be greater than zero' };
		}
		if (!expense.date) {
			return { valid: false, msg: 'No date given' };
		}
		if (typeof (expense.description) != 'string' || expense.description.length == 0) {
			return { valid: false, msg: 'No description provided' };
		}
		return { valid: true, msg: 'Everything is OK' };
	}
}

export interface Expense {
	id: number;
	// date: string;
	date: Date;
	amount: number;
	description?: string;
	category: Category;
}

export enum Category {
	Misc,
	Food,
	Travel,
	Entertainment
}