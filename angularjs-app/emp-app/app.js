(function (angular) {
	let empapp = angular.module('emp-app', ['ngRoute']);

	empapp.config(['$routeProvider', function ($routeProvider) {
		$routeProvider.when('/', {
			controller: 'home-controller',
			templateUrl: 'fragments/home.html'
		});
		$routeProvider.when('/edit', {
			controller: 'edit-controller',
			templateUrl: 'fragments/edit.html'
		});
		$routeProvider.when('/add', {
			controller: 'add-controller',
			templateUrl: 'fragments/add.html'
		});
	}]);

	empapp.factory('empsvc', ['$q', function ($q) {
		let svc = {};

		const LS_KEY = 'emp-list';
		let idGen = 0;

		let cached = { data: [], hash: '' };

		function saveData() {
			return $q(function (resolve, reject) {
				let value = '';
				try {
					value = JSON.stringify(cached.data);
				} catch (err) {
					reject({ msg: "You passed something which I couldn't handle" });
					return;
				}
				localStorage.setItem(LS_KEY, value);
				resolve({ msg: 'Everything was fine and stil is!' });
			});
		}

		function copyEmp(src, dst) {
			dst.id = src.id;
			dst.name = src.name;
		}

		svc.get = function () {
			return $q(function (resolve, reject) {
				let lst = JSON.parse(localStorage.getItem(LS_KEY));
				if (lst == null)
					lst = [];
				cached = { data: lst, hash: '' };
				resolve(lst);
			});
		}

		svc.getID = function (id) {
			return $q(function (resolve, reject) {
				svc.get().then(res => {
					let emp = res.find(emp => emp.id == id);
					if (emp == undefined) {
						reject({ msg: `No employee found by id: ${id}` });
					} else {
						resolve({ msg: 'OK', data: emp });
					}
				});
			});
		}

		svc.add = function (emp) {
			emp.id = idGen++;
			cached.data.push(emp);
			return saveData();
		}

		svc.update = function (id, emp) {
			return $q(function (resolve, reject) {
				svc.getID(id).then(res => {
					copyEmp(res, emp);
					resolve({ msg: 'Everyone lived happily ever after' });
				}).catch(err => {
					reject({ msg: `Updating failed: ${err.msg}` });
				});
			});
		}

		return svc;
	}]);

	empapp.controller('home-controller', ['$scope', 'empsvc',
		function ($scope, empsvc, $routeProvider) {
			$scope.employees = [];
			empsvc.get().then(res => {
				console.log(res);
			});

			$scope.addEmp = function () {
				$routeProvider.path('/add');
			}
		}]
	);

	empapp.controller('add-controller', ['$scope', 'empsvc',
		function ($scope, empsvc) {
			$scope.test = 'successfull validation is done here';
			$scope.empno = '';
			$scope.empname = '';
			$scope.salary = '';
		}]
	);

})(window.angular);