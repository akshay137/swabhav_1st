const Captcha = (function () {
	'use strict';
	let captcha = function () {
		this.text = '';
	}

	const CAPTHCA_SIZE = 5;

	captcha.prototype.generateNewCaptcha = function (size) {
		this.text = '';
		for (let i = 0; i < size; i++) {
			let c = 0x41 + Math.random() * 26;
			this.text += String.fromCharCode(c);
		}
		console.log('captcha:', this.text);
		return this.text;
	}

	captcha.prototype.matchCaptcha = function (input) {
		return input === this.text;
	}

	captcha.prototype.newCaptcha = function (canvas) {
		this.generateNewCaptcha(CAPTHCA_SIZE);
		this.drawCaptcha(canvas);
	}

	captcha.prototype.drawCaptcha = function (canvas) {
		const ctx = canvas.getContext('2d');
		ctx.fillStyle = '#313131';
		ctx.fillRect(0, 0, canvas.width, canvas.height);
		ctx.fillStyle = '#aaa';
		ctx.font = '30px Times New Roman';
		// ctx.fillText(this.text, 10, 65);
		ctx.save();
		ctx.shadowOffsetX = 3;
		ctx.shadowOffsetY = 3;
		ctx.shadowColor = '#fff';
		ctx.shadowBlur = 4;
		const start = 10;
		const offset = Math.round((canvas.width - start * 2) / CAPTHCA_SIZE);
		const y = 50;
		const range = (Math.PI * 0.75);
		const halfRange = range / 2;
		for (let i = 0; i < this.text.length; i++) {
			let x = start + i * offset;
			ctx.translate(x, y);
			let angle = Math.random() * range - halfRange;
			ctx.rotate(angle);
			ctx.fillText(this.text[i], 0, 0);
			ctx.rotate(-angle);
			ctx.translate(-x, -y);
		}
		ctx.restore();
	}

	return captcha;
})();