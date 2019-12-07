(function () {
	'use strict'
	const qBankUrl = 'qb.json';
	const qUI = document.getElementById('qUi');
	const resultUi = document.getElementById('result');
	const qCounter = document.getElementById('qCounter');
	const qId = document.getElementById('qid');
	const qString = document.getElementById('qstring');
	const optionsForm = document.forms['options'];

	const wrongPercent = document.getElementById('wrongPercent');
	const wrongDiv = document.getElementById('wrong');
	const rightPercent = document.getElementById('rightPercent');
	const rightDiv = document.getElementById('right');
	const resultText = document.getElementById('resText');

	const btnNext = document.getElementById('btnNext');
	const btnPre = document.getElementById('btnPre');
	const btnFinish = document.getElementById('btnFinish');

	let qBank = [];
	let currentQuestion = -1;
	let currentAnswerIndex = -1;
	let correctAnswers = 0;

	const showFinisButton = function (state) {
		btnFinish.style.display = state ? 'inline' : 'none';
	}

	const disableForm = function () {
		let options = optionsForm.getElementsByTagName('input');
		console.log(options);
		options.forEach((option) => {
			option.disabled = true;
		})
	}

	const hideButtons = function () {
		btnPre.style.display = 'none';
		btnNext.style.display = 'none';
	}

	const toggleButtons = function () {
		if (currentQuestion == 0) {
			btnPre.style.display = 'none';
			btnNext.style.display = 'inline';
		} else {
			btnPre.style.display = 'inline';
			btnNext.style.display = 'inline';
		}
	}

	const displayOptions = function (options, node) {
		node.innerHTML = '';
		for (let i = 0; i < options.length; i++) {
			let div = document.createElement('div');
			div.id = 'option_div';
			let optionNode = document.createElement('input');
			optionNode.type = 'radio';
			optionNode.name = 'option';
			optionNode.id = 'option' + i;
			optionNode.value = i;
			div.appendChild(optionNode);

			// let text = document.createTextNode(options[i]);
			let label = document.createElement('label');
			label.for = 'option' + i;
			label.innerHTML = options[i];
			div.appendChild(label);

			// const span = document.createElement('span');
			// span.classList.add('checkmark');
			// div.appendChild(span);

			node.appendChild(div);
		}
	}

	const displayQuestion = function (question) {
		qCounter.innerHTML = '{'
			+ (currentQuestion + 1) + '/' + qBank.length
			+ '}';
		qId.innerHTML = '[' + question.id + ']';
		qString.innerHTML = question.text;
		displayOptions(question.options, optionsForm);
	}

	const showQuestion = function () {
		const q = qBank[currentQuestion];
		currentAnswerIndex = q.answer;
		displayQuestion(q);
		toggleButtons();
	}

	const setNextQuestion = function () {
		currentQuestion++;
		if (currentQuestion >= qBank.length) {
			console.log('finished with questions, correct', correctAnswers);
			return false;
		}
		showQuestion();
	}

	const setPreQuestion = function () {
		if (currentQuestion == 0)
			return;
		currentQuestion--;
		showQuestion();
	}

	const checkAnswer = function (selected, correct) {
		console.log(selected, correct);
		if (selected == correct) {
			correctAnswers++;
		}
	}

	const setStartState = function () {
		correctAnswers = 0;
		currentQuestion = -1;
		showFinisButton(false);
		qUI.style.display = 'grid';
		resultUi.style.display = 'none';
	}

	const showResult = function () {
		console.log('showing result');
		const marks = Math.round((correctAnswers / qBank.length) * 100);
		wrongPercent.innerHTML = 'wrong:' + (100 - marks) + '%';
		wrongDiv.style.width = (100 - marks) + '%';
		rightPercent.innerHTML = 'correct:' + (marks) + '%';
		rightDiv.style.width = marks + '%';
		resultText.innerHTML = 'You Scored: ' + marks + '%';
	}

	// onload function
	const onQuestionLoaded = function (data) {
		// console.log(data);
		qBank = JSON.parse(data);
		if (qBank == null)
			qBank = [];
		console.log(qBank);
		setStartState();
		setNextQuestion();

		// correctAnswers = qBank.length * Math.random();
		// showResult();
	}

	const loadQuestions = function () {
		const xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function (e) {
			// console.log(this.readyState);
			switch (this.readyState) {
				case 4:
					if (this.status === 200)
						onQuestionLoaded(this.responseText);
					break;
			}
		}
		xhr.open('GET', qBankUrl, true);
		xhr.send();
	}

	// btn listeners
	const onNext = function (e) {
		let selected = optionsForm['option'].value;
		if (selected == undefined || selected == '') {
			alert('Please select answer');
			return;
		}
		console.log('selected answer:', selected);
		console.log(typeof (selected));
		// checkAnswer
		checkAnswer(parseInt(selected), currentAnswerIndex);
		let state = setNextQuestion();
		if (state === false) {
			alert("t'was the last question");
			hideButtons();
			disableForm();
			showFinisButton(true);
		}
	}

	const onPre = function (e) {
		correctAnswers--;
		setPreQuestion();
	}

	const onFinish = function (e) {
		qUI.style.display = 'none';
		resultUi.style.display = 'block';
		showResult();
	}

	btnNext.addEventListener('click', onNext);
	btnPre.addEventListener('click', onPre);
	btnFinish.addEventListener('click', onFinish);
	window.addEventListener('load', () => {
		loadQuestions();
	});
})();