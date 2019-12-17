Game = (function () {
	
	class Game {
		constructor(max_attempts, ballCount) {
			this.MAX_ATTEMPTS;
			this._attempts = 0;
			this._magickBall = -1;
			this._balls = [];
			this._ballCount = 0;
			setMaxAttempts.call(this, max_attempts);
			this._attempts = this.MAX_ATTEMPTS;
			this._ballCount = ballCount;
		}

		setup () {
			this.reset();
			this._magickBall = generateMagickNumber(this._ballCount);
			generateBalls.call(this, 0, this._magickBall, Game.LOOK_AHEAD);
			generateBalls.call(this,
				this._magickBall, this._magickBall + 1, Game.CORRECT);
			generateBalls.call(this,
				this._magickBall + 1, this._ballCount, Game.LOOK_BACK)
			this._magickBall++;
			
			// debug only
			console.log('magick ball:', this._magickBall);
		}
	
		reset () {
			this._attempts = this.MAX_ATTEMPTS;
			this._balls = [];
			this._magickBall = -1;		
		}
	
		decreaseAttempts () {
			this._attempts--;
		}
	
		getAttempts () {
			return this._attempts;
		}
	
		getMagickBallNum () {
			return this._magickBall;
		}
	
		getBalls () {
			return this._balls;
		}
	
		checkBall (index) {
			return clamp(index - this._magickBall, -1, 1);
		}
	}

	Game.LOOK_BACK = 'look-back';
	Game.LOOK_AHEAD = 'look-ahead';
	Game.NEUTRAL = 'neutral';
	Game.CORRECT = 'correct';

	function setMaxAttempts (max_attempts) {
		if (typeof (max_attempts) != 'number')
			max_attempts = 5;
		if (max_attempts < 0)
			max_attempts = 5;
		this.MAX_ATTEMPTS = max_attempts;
	}

	function generateBalls (start, end, color) {
		for (let i = start; i < end; i++) {
			this._balls.push(new Ball(i + 1, color));
		}
	}

	function generateMagickNumber (max) {
		return Math.floor(Math.random() * max);
	}

	function clamp(v, min, max) {
		if (v < min)
			return min;
		if (v > max)
			return max;
		return v;
	}

	return Game;
})();