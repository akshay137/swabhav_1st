(function (angular) {
	let studentApp = angular.module('student-app', []);

	studentApp.factory('studentsvc', ['$q', '$http',
		function ($q, $http) {
			const api = 'http://gsmktg.azurewebsites.net/api/v1/techlabs/test/students/';
			let svc = {};

			svc.getStudents = function () {
				return $q(function (resolve, reject) {
					$http.get(api)
						.then(res => {
							resolve({ msg: 'OK', data: res.data });
						}).catch(err => {
							reject({ msg: 'error', data: [] });
						});
				});
			}

			svc.addStudent = function (student) {
				return $q(function (resolve, reject) {
					$http.post(api)
						.then(res => {
							resolve({ msg: 'OK', data: res.data });
						}).catch(err => {
							reject({ msg: 'Error', data: [] });
						})
				});
			}

			svc.updateStudent = function (student) {
				return $q(function (resolve, reject) {
					$http.put(api)
						.then(res => {
							resolve({ msg: 'OK', data: res.data });
						}).catch(err => {
							reject({ msg: 'Error', data: [] });
						})
				});
			}

			return svc;
		}
	]);

	studentApp.controller('student-controller', ['$scope', 'studentsvc', '$filter',
		function ($scope, studentsvc, $filter) {
			$scope.students = [];
			$scope.formMethod = 'POST';
			$scope.modalTitle = 'Add New Student data:';
			$scope.modalBtnTxt = 'Add';
			$scope.curStudent = {
				id: '',
				name: '',
				rollNo: '',
				email: '',
				date: '',
				isMale: true
			};

			studentsvc.getStudents()
				.then(res => {
					$scope.students = res.data;
					console.log(res);
				}).catch(err => {
					console.log('Error occured:', err.msg);
					$scope.students = [];
				});

			$scope.updateStudent = function (student) {
				$scope.formMethod = 'PUT';
				console.log(student, $scope.formMethod);
				$scope.modalTitle = 'Update Student data:';
				$scope.modalBtnTxt = 'Update';
				$scope.curStudent = student;
			}

			$scope.addStudent = function (student) {
				$scope.formMethod = 'POST';
				console.log($scope.formMethod);
				$scope.modalTitle = 'Add Student data:';
				$scope.modalBtnTxt = 'Add';
				$scope.curStudent = {
					id: '',
					name: '',
					rollNo: '',
					email: '',
					date: '',
					isMale: true
				};;
			}

			$scope.submit = function () {
				console.log($scope.curStudent);
			}
		}
	]);
})(window.angular);