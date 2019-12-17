Ball = (function () {
	class Ball {
		constructor(number, css) {
			this._number = number;
			this._css = css;
		}

		getNumber () {
			return this._number;
		}
	
		getCSS () {
			return this._css;
		}
	}

	return Ball;
})();