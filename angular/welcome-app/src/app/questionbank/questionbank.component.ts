import { Component, OnInit } from '@angular/core';

import { QuestionbService, Question } from '../services/questionb.service';

@Component({
	selector: 'app-questionbank',
	templateUrl: './questionbank.component.html',
	styleUrls: ['./questionbank.component.css']
})
export class QuestionbankComponent implements OnInit {

	questions: Question[];
	currQi: number;
	currentQ: Question;
	answer: number;
	score: number;
	showRes: boolean;

	correctP: number;
	wrongP: number;

	constructor(private qbank: QuestionbService) { }

	ngOnInit() {
		this.questions = this.qbank.getQuestions();
		console.log(this.questions);
		this.restart();
	}

	onNext(): void {
		this.checkAnswer();
		this.getNextQ();
	}

	getNextQ() {
		this.currQi++;
		if (this.currQi >= this.questions.length) {
			this.showResult();
			return;
		}
		this.answer = -1;
		this.currentQ = this.questions[this.currQi];
	}

	checkAnswer() {
		if (this.answer == this.currentQ.answer) {
			this.score++;
		}
	}

	showResult() {
		this.correctP = Math.round((this.score / this.questions.length) * 100);
		this.wrongP = 100 - this.correctP;
		this.showRes = true;
	}

	restart() {
		this.currQi = 0;
		this.currentQ = this.questions[this.currQi];
		this.answer = -1;
		this.score = 0;
		this.showRes = false;
	}

}
