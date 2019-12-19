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
							reject({ msg: 'error', data: [], err: err });
						});
				});
			}

			function sendData(data, method, link) {
				return $q(function (resolve, reject) {
					$http({
						method: method,
						url: link,
						data: data
					}).then(res => {
						resolve({ msg: 'OK', data: res.data });
					}).catch(err => {
						reject({ msg: 'Error', data: [], err: err });
					})
				});
			}

			svc.addStudent = function (student) {
				return sendData(student, 'POST', api);
			}

			svc.updateStudent = function (student) {
				return sendData(student, 'PUT', api + student.id);
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
			$scope.disableButton = false;
			$scope.curStudent = null;

			const modal = $('#student-form-modal');

			function refreshStudentList() {
				studentsvc.getStudents()
					.then(res => {
						$scope.students = res.data;
						console.log(res);
					}).catch(err => {
						console.log('Error occured:', err.msg);
						$scope.students = [];
					});
			}
			refreshStudentList();

			$scope.updateStudent = function (student) {
				$scope.formMethod = 'PUT';
				$scope.modalTitle = 'Update Student data:';
				$scope.modalBtnTxt = 'Update';
				$scope.curStudent = student;
				$scope.curStudent.ngdate = new Date(student.date);
			}

			$scope.addStudent = function (student) {
				$scope.formMethod = 'POST';
				$scope.modalTitle = 'Add Student data:';
				$scope.modalBtnTxt = 'Add';
				$scope.curStudent = null;
			}

			$scope.submit = function () {
				console.log($scope.curStudent);
				$scope.disableButton = true;
				console.log($scope.curStudent.ngdate);
				$scope.curStudent.date = ''
					+ $scope.curStudent.ngdate.getFullYear()
					+ '-' + ($scope.curStudent.ngdate.getMonth() + 1)
					+ '-' + $scope.curStudent.ngdate.getDate();

				let svcFn = $scope.formMethod == 'POST'
					? studentsvc.addStudent : studentsvc.updateStudent;
				svcFn({
					id: $scope.curStudent.id,
					rollNo: $scope.curStudent.rollNo,
					name: $scope.curStudent.name,
					email: $scope.curStudent.email,
					date: $scope.curStudent.date,
					age: $scope.curStudent.age,
					isMale: $scope.curStudent.isMale
				}).then(res => {
					console.log(res);
					modal.modal('hide');
					$scope.disableButton = false;
					refreshStudentList();
				}).catch(err => {
					modal.modal('hide');
					$scope.disableButton = false;
					console.log('Error:', err);
				});

			}
		}
	]);
})(window.angular);