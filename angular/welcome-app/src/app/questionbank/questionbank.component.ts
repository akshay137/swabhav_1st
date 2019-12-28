import { Component, OnInit } from '@angular/core';

import { QuestionbService, Question } from '../services/questionb.service';

@Component({
	selector: 'app-questionbank',
	templateUrl: './questionbank.component.html',
	styleUrls: ['./questionbank.component.css']
})
export class QuestionbankComponent implements OnInit {

	questions: Question[];
	answers: number[];
	currQi: number;
	currentQ: Question;
	answer: number;
	score: number;
	showRes: boolean;
	nextEnabled: boolean;
	finished: boolean;

	correctP: number;
	wrongP: number;

	constructor(private qbank: QuestionbService) { }

	ngOnInit() {
		// this.questions = this.qbank.getQuestions();
		this.qbank.getQuestions().subscribe((data: Question[]) => {
			this.questions = data;
		});
		console.log(this.questions);
		this.restart();
	}

	onNext(): void {
		// this.checkAnswer();
		this.getNextQ();
	}

	onPre(): void {
		this.getPreQ();
	}

	getPreQ() {
		if (this.currQi == 0) {
			return;
		}
		this.currQi--;
		this.currentQ = this.questions[this.currQi];
		this.nextEnabled = this.answers[this.currQi] != undefined;
	}

	getNextQ() {
		this.currQi++;
		if (this.currQi >= this.questions.length) {
			this.currQi = this.questions.length - 1;
			this.nextEnabled = false;
			this.finished = true;
			return;
		}
		this.currentQ = this.questions[this.currQi];
		this.nextEnabled = this.answers[this.currQi] != undefined;
	}

	onFinish() {
		this.showResult();
	}

	showResult() {
		for (let i = 0; i < this.answers.length; i++) {
			if (this.answers[i] == this.questions[i].answer)
				this.score++;
		}
		this.correctP = Math.round((this.score / this.questions.length) * 100);
		this.wrongP = 100 - this.correctP;
		this.showRes = true;
	}

	restart() {
		this.currQi = 0;
		this.currentQ = this.questions[this.currQi];
		this.score = 0;
		this.showRes = false;
		this.finished = false;
		this.answers = [];
		this.answers.length = this.questions.length;
	}

	isAnswerChecked(option: number): boolean {
		return this.answers[this.currQi] == option;
	}

	enableNext() {
		this.nextEnabled = true;
	}

}
