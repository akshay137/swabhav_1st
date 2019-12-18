(function (angular) {
	let blueBall = angular.module('blue-ball', []);
	blueBall.factory('ballsvc', [function () {
		let ballsvc = {};

		ballsvc.getBall = function (num, neutral, cssCls) {
			return {
				number: num,
				currentClass: neutral,
				cssClass: cssCls,
				clicked: false,
				show: function () {
					this.currentClass = this.cssClass;
				}
			};
		}

		return ballsvc;
	}]);

	blueBall.factory('game-controller-svc', ['$rootScope',
		function ($rootScope) {
			let gamecntrl = {};

			gamecntrl.onStartGame = function (scope, callback) {
				let handler = $rootScope.$on('startGame', callback);
				scope.$on('$destroy', handler);
			}

			$rootScope.startGame = function () {
				console.log('starting game');
				$rootScope.$emit('startGame');
			}

			gamecntrl.onEndGame = function (scope, callback) {
				let handler = $rootScope.$on('endGame', callback);
				scope.$on('$destroy', handler);
			}

			gamecntrl.endGame = function () {
				console.log('ending game');
				$rootScope.$emit('endGame');
			}

			return gamecntrl;
		}
	]);
	
	blueBall.controller('menu-controller', ['$scope', '$rootScope',
		'game-controller-svc',
		function ($scope, $rootScope, gameController) {
			$scope.showmenu = true;
			gameController.onStartGame($scope, () => {
				$scope.showmenu = false;
			});

			gameController.onEndGame($scope, () => {
				$scope.showmenu = true;
			})
		}]
	);
	
	blueBall.controller('game', ['$scope', '$rootScope',
		'ballsvc', 'game-controller-svc', '$window',
		function ($scope, $rootScope, ballsvc, gameController, $window) {
			$scope.balls = [];
			$scope.showgame = false;

			gameController.onStartGame($scope, startGame);
			gameController.onEndGame($scope, cleanup);

			$scope.checkBall = function (ball) {
				console.log('clicked', ball.number);
				ball.show();
				ball.clicked = true;
				checkGameStatus(ball);
			}

			function checkGameStatus (ball) {
				if (ball.number == magickBall) {
					endGame('Win');
				} else {
					decreaseAttempts();
				}
			}

			function decreaseAttempts () {
				$scope.attempts--;
				if ($scope.attempts <= 0) {
					endGame('loose');
				}
			}

			function endGame (status) {
				$window.alert('You ' + status + '!\n'
					+ 'Attempts remaining: ' + $scope.attempts);
				gameController.endGame();
			}

			const MAX_BALLS = 50;
			let magickBall = 0;

			function startGame() {
				$scope.showgame = true;
				$scope.attempts = 5;
				generateMagickBall(MAX_BALLS);
				generateBalls(MAX_BALLS);
			}

			function cleanup() {
				$scope.showgame = false;
				$scope.balls = [];
			}

			function generateMagickBall (max) {
				magickBall = Math.floor(Math.random() * max) + 1;
			}

			function generateBalls(count) {
				console.log(magickBall);
				const blueBall = magickBall - 1;
				for (let i = 0; i < count; i++) {
					let cssClass = i < blueBall ? 'look-ahead'
						: i == blueBall ? 'correct' : 'look-back';
					let ball = ballsvc.getBall(i + 1,
						'neutral', cssClass);
					$scope.balls.push(ball);
				}
			}
		}]
	);
})(window.angular);