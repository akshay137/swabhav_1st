import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class QuestionbService {
	questions: Question[];

	constructor() {
		this.questions = [
			{
				id: 1,
				question: 'What is 2 + 2?',
				options: ['2', '4', '6', '8'],
				answer: 1
			},
			{
				id: 2,
				question: 'Minimum size of int in bits in c?',
				options: ['32', '24', '16', '8'],
				answer: 2
			},
			{
				id: 3,
				question: 'javascript is ___ typed language.',
				options: ['statically', 'dynamically', 'idiotically'],
				answer: 1
			},
			{
				id: 4,
				question: 'this is _th question',
				options: ['7th', '4th', '5th', '10th'],
				answer: 1
			},
		];
	}

	getQuestions(): Observable<Question[]> {
		// return this.questions;
		return new Observable<Question[]>((observer) => {
			observer.next(this.questions);
		});
	}

	getQuestion(id: number): Question {
		let q = this.questions.find(qstn => qstn.id == id);
		if (q)
			return q;
		return {
			id: -1,
			question: 'This is generic question',
			options: ['This option is correct', 'you may try clicking others'],
			answer: 0
		};
	}
}

export interface Question {
	id: number;
	question: string;
	options: string[];
	answer: number;
}