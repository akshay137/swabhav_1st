GameController = (function () {
	const cssClassList = ['look_ahead', 'correct', 'look_back'];

	class GameController {
		constructor(level, rootDOM) {
			this._level = 0;
			this._game = null;
			this._root = rootDOM;
			this._gameDiv = null;
			this.hud = null;
			this.endCallback = null;

			this.setGameLevel(level);
			this._gameDiv = gameDiv;
		}

		setGameLevel (level) {
			if (typeof (level) != 'number')
				level = 0;
			if (level < 0)
				level = 0;
			this._level = level;
		}

		start () {
			this.generateGame();
		}
		
		generateGame () {
			this._root.innerHTML = '';
			this._game = new Game(5, 50);
			this._game.setup();
			this.createHUD();
			this.createGameView();
			this._root.append(this._hud);
			this._root.append(this._gameDiv);
		}

		createHUD () {
			this._hud = document.createElement('h3');
			this.updateHUD();
		}

		createGameView() {
			this._gameDiv = document.createElement('div');
			this._gameDiv.classList.add('gameGrid');
			let balls = this._game.getBalls();
			balls.forEach(ball => {
				this._gameDiv.append(generateBallMarkup(
					ball, this.onBallClick, this));
			});
		}

		updateHUD() {
			this._hud.innerHTML = 'AttemptRemaining: '
				+ this._game.getAttempts();
		}

		onBallClick (button) {
			button.disabled = true;
			let index = button.dataset.ballId;
			
			let res = this._game.checkBall(index);
			button.classList.remove('neutral');
			button.classList.add(button.dataset.ballClass);
			this._game.decreaseAttempts();
			this.updateHUD();
			
			if (res == 0) {
				this.gameWin();
				return;
			}

			let attempts = this._game.getAttempts();
			if (attempts <= 0) {
				this.gameLoose();
			}
		}

		gameWin () {
			alert('You Won!\nAttempts remaining:'
				+ this._game.getAttempts());
			this.endGame();
		}

		gameLoose () {
			alert('You Lost');
			this.endGame();
		}

		onEnd (fn) {
			this.endCallback = fn;
		}

		endGame() {
			this._gameDiv.innerHTML = '';
			this._game.reset();
			if (this.endCallback != null)
				this.endCallback();
		}
	}

	function generateBallMarkup (ball, onClick, gc) {
		let btn = document.createElement('button');
		btn.innerHTML = ball.getNumber();
		btn.classList.add('neutral');
		btn.setAttribute('data-ball-id', ball.getNumber());
		btn.setAttribute('data-ball-class', ball.getCSS());
		btn.addEventListener('click', (e) => {
			onClick.call(gc, e.target);
		});
		return btn;
	}

	return GameController;
})();