(function (angular) {
	let empapp = angular.module('emp-app', ['ngRoute']);

	empapp.config(['$routeProvider', function ($routeProvider) {
		$routeProvider.when('/', {
			controller: 'home-controller',
			templateUrl: 'fragments/home.html'
		});
		$routeProvider.when('/edit/:id/', {
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
		const LS_TS = 'emp-list-ts';

		let cached = { data: [], timestamp: 0 };

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
				localStorage.setItem(LS_TS, Date.now());
				resolve({ msg: 'Everything was fine and stil is!' });
			});
		}

		function copyEmp(src, dst) {
			dst.name = src.name;
			dst.salary = src.salary;
		}

		svc.get = function () {
			return $q(function (resolve, reject) {
				let ts = JSON.parse(localStorage.getItem(LS_TS));
				if (ts == null) {
					ts = 0;
				}
				if (cached.timestamp < ts) {
					let lst = JSON.parse(localStorage.getItem(LS_KEY));
					if (lst == null)
						lst = [];
					cached = { data: lst, timestamp: ts };
				}
				resolve(cached.data);
			});
		}

		svc.getId = function (id) {
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
			emp.id = Date.now();
			cached.data.push(emp);
			return saveData();
		}

		svc.update = function (id, emp) {
			return $q(function (resolve, reject) {
				let i = cached.data.findIndex(em => em.id == id);
				if (i != -1) {
					copyEmp(emp, cached.data[i]);
					saveData().then(res => {
						resolve({ msg: 'Everyone lived happily ever after' });
					}).catch(err => {
						reject({
							msg: `error occured during saving data: ${err.msg}`
						});
					});
				} else {
					reject({ msg: `No employee found: ${id}` });
				}
			});
		}

		svc.remove = function (emp) {
			return $q(function (resolve, reject) {
				svc.get().then(res => {
					let i = res.findIndex(em => em.id == emp.id);
					if (i == -1) {
						reject({ msg: 'no employee', status: false });
					}
					res.splice(i, 1);
					saveData();
					resolve({ msg: 'removed', status: true });
				})
			});
		}

		return svc;
	}]);

	empapp.controller('home-controller', ['$scope', 'empsvc',
		function ($scope, empsvc, $routeProvider) {
			$scope.employees = [];
			empsvc.get().then(res => {
				// console.log(res);
				$scope.employees = res;
			});

			$scope.addEmp = function () {
				$routeProvider.path('/add');
			}

			$scope.delete = function (emp) {
				empsvc.remove(emp).then(res => {
					// console.log(res);
					if (res.status) {
						let i = $scope.employees.findIndex(em => em.id == emp.id);
						if (i != -1) {
							$scope.employees.splice(i, 1);
						}
					}
				}).catch(err => {
					console.log('err', err);
				})
			}
		}]
	);

	empapp.controller('add-controller', ['$scope', 'empsvc', '$location',
		function ($scope, empsvc, $location) {
			$scope.test = 'successfull validation is done here';
			$scope.empname = '';
			$scope.empsalary = '';

			$scope.addEmp = function () {
				// console.log('adding');
				empsvc.add({ name: $scope.empname, salary: $scope.empsalary })
				$location.path('/');
			}
		}]
	);

	empapp.controller('edit-controller', ['$scope', 'empsvc',
		'$location', '$routeParams',
		function ($scope, empsvc, $location, $routeParams) {
			$scope.test = 'successfull update is done here';
			$scope.empid = parseInt($routeParams.id);
			if ($scope.empid == NaN) {
				$scope.empid = 0;
			}
			$scope.empname = '';
			$scope.empsalary = '';

			empsvc.getId($scope.empid).then(res => {
				// console.log(res);
				$scope.empname = res.data.name;
				$scope.empsalary = res.data.salary;
			})

			$scope.update = function () {
				console.log('updating');
				empsvc.update($scope.empid, { name: $scope.empname, salary: $scope.empsalary }).then(res => {
					console.log(res);
				}).catch(err => {
					console.log(err);
				});
				$location.path('/');
			}
		}]
	);

})(window.angular);