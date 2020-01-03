import { Injectable } from '@angular/core';
import { StorageService } from './storage.service';
import { Observable, observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class ExpensesService {

	list: Expense[];

	constructor(private storagesvc: StorageService) {
		this.list = [
			{
				id: 1,
				date: '3/01/2019',
				amount: 10.5,
				category: Category.Food,
				description: 'ate some food'
			},
			{
				id: 2,
				date: '2/01/2019',
				amount: 20.5,
				category: Category.Travel,
				description: 'got lost while returning'
			},
		];
	}

	getEmpty(): Expense {
		return {
			id: 0,
			amount: 0,
			category: Category.Misc,
			date: '',
			description: ''
		}
	}

	getAllExpenses() {
		return new Observable<Expense[]>(observer => {
			observer.next(this.list);
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
				observer.next();
				observer.complete();
			}
		});
	}

	private copyExpense(src: Expense, dst: Expense) {
		dst.id = src.id;
		dst.amount = src.amount;
		dst.category = src.amount;
		dst.date = src.date;
		dst.description = src.description
	}

	updateExpense(id: number, expense: Expense) {
		return new Observable(observer => {
			let exp = this.list.find(e => id == e.id);
			if (exp) {
				this.copyExpense(expense, exp);
				observer.next();
				observer.complete();
			} else {
				observer.error({ msg: 'No Expense found' });
				observer.complete();
			}
		});
	}
}

export interface Expense {
	id: number;
	date: string;
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