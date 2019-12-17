(function (angular) {
	angular.module('eventMod', [])
		.controller('eventController', ['$interval',
			'$timeout', '$scope',
			function ($interval, $timeout, $scope) {
				$scope.students = [];
				$scope.counter = 0;

				$scope.loadData = function () {
					console.log('Loading');
					$scope.students = studentList;
					$timeout(function () {
						$scope.students[0].name = 'abc to xyz';
					}, 3000);
				}
			}]).filter('displayStudent', function () {
				return function (st) {
					return st.name + ': ' + st.cgpa + ' CGPA';
				}
			});

	let studentList = [
		{ name: 'abc', cgpa: 7, img: 'images/icon_32.png' },
		{ name: 'def', cgpa: 8, img: 'images/icon_32.png' },
		{ name: 'ghi', cgpa: 7.5, img: 'images/icon_32.png' },
		{ name: 'jkl', cgpa: 6.5, img: 'images/icon_32.png' }
	];
})(window.angular);