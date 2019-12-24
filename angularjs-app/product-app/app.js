(function (angular) {
	let productapp = angular.module('product-app', ['ngRoute']);

	productapp.config(['$routeProvider', function ($routeProvider) {
		$routeProvider.when('/', {
			controller: 'home-controller',
			templateUrl: 'fragments/home.html'
		});
		$routeProvider.when('/list', {
			controller: 'list-controller',
			templateUrl: 'fragments/list.html'
		});
		$routeProvider.when('/product/:id', {
			controller: 'product-controller',
			templateUrl: 'fragments/product.html'
		});
		$routeProvider.otherwise({
			controller: 'home-controller',
			templateUrl: 'fragments/home.html'
		});
	}]);

	productapp.directive('stStars', [function () {
		const BLACK_STAR = '\u2605';
		const WHITE_STAR = '\u2605';
		function link(scope, element, attrs) {
			updateText();

			function updateText(stars) {
				element.text('');
				for (let i = 0; i < stars; i++)
					element.append(BLACK_STAR);
				element.append(`[${stars}]`);
			}

			scope.$watch(attrs.stars, function (value) {
				updateText(value);
			})
		}
		return { link: link };
	}]);

	productapp.factory('productsvc', ['$q', '$http',
		function ($q, $http) {
			let svc = {};

			const URL = 'products.jsonx';
			let list = [];

			svc.get = function () {
				return $q(function (resolve, reject) {
					if (list.length > 1) {
						resolve({ msg: 'OK', data: list });
						return;
					}
					$http.get(URL)
						.then(res => {
							console.log(res);
							list = res.data;
							resolve({ msg: 'OK', data: list });
						})
						.catch(err => {
							console.log(err);
							reject({
								msg: `Failed to fetch data: ${err.statusText}`,
								data: []
							});
						})
				});
			}

			svc.getById = function (id) {
				return $q(function (resolve, reject) {
					svc.get().then(res => {
						let p = res.data.find(product => product.productId == id);
						if (p == null) {
							reject({ msg: 'No such product', data: null });
						} else {
							resolve({ msg: 'OK', data: p });
						}
					}).catch(err => {
						reject({
							msg: `Failed to fetch data: ${err.statusText}`,
							data: null
						})
					})
				});
			}

			return svc;
		}]);

	productapp.controller('home-controller', ['$scope',
		function ($scope) {
			$scope.location = 'home';
		}
	]);
	productapp.controller('list-controller', ['$scope', 'productsvc',
		function ($scope, productsvc) {
			$scope.location = 'list';
			$scope.products = [];
			$scope.showImg = true;
			$scope.filtertxt = '';

			productsvc.get().then(res => {
				$scope.products = res.data;
			}).catch(err => {
				console.log('Error:', err.msg);
			});
		}
	]);
	productapp.controller('product-controller', ['$scope', '$routeParams', 'productsvc',
		function ($scope, $routeParams, productsvc) {
			$scope.location = 'product';
			$scope.product = {};

			productsvc.getById($routeParams.id)
				.then(res => {
					$scope.product = res.data;
				})
				.catch(err => {
					console.log('NO such product');
				})
		}
	]);
})(window.angular);