(function (angular) {
	let spa = angular.module('mini-spa', ['ngRoute']);

	spa.config(['$routeProvider', function ($routeProvider) {
		$routeProvider.when('/', {
			controller: 'home-controller',
			templateUrl: 'fragments/home.html'
		});
		$routeProvider.when('/home', {
			controller: 'home-controller',
			templateUrl: 'fragments/home.html'
		});
		$routeProvider.when('/about', {
			controller: 'about-controller',
			templateUrl: 'fragments/about.html'
		});
		$routeProvider.when('/career', {
			controller: 'career-controller',
			templateUrl: 'fragments/career.html'
		});
	}]);

	spa.controller('home-controller', ['$scope', function ($scope) {
		$scope.home = { msg: 'at home' }
	}]);

	spa.controller('about-controller', ['$scope', function ($scope) {
		$scope.about = { msg: 'at about' }
	}]);

	spa.controller('career-controller', ['$scope', function ($scope) {
		$scope.career = { msg: 'at career' }
	}]);
})(window.angular);