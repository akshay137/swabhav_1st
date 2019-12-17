(function () {
	'use strict';
	const api = 'http://gsmktg.azurewebsites.net/api/v1/techlabs/test/students/';
	const studentList = $('#student-list #list');
	const studentModal = $('#student-form-modal');
	const studentModalTitle = $('#student-modal-title');
	const studentForm = $('form[name=student-data-form]');
	const studentFormData = {
		btn: $('#btnSubmit'),
		btnText: $('#btnSubmitText'),
		id: $('#sid'),
		roll: $('#sroll'),
		name: $('#sname'),
		email: $('#semail'),
		age: $('#sage'),
		date: $('#sdate'),
		gender: $('input[name=gender]')
	};

	studentFormData.gender.on('change', (e) => {
		console.log(e.target.value, studentFormData.gender.val());
	});
	console.log(studentFormData);

	const addStudent = function (student) {
		// console.log(student);
		let div = document.createElement('div');
		div.classList.add('row', 'bg-dark',
			'text-light', 'h5', 'py-2', 'border', 'rounded');

		div.setAttribute('data-toggle', 'modal');
		div.setAttribute('data-target', '#student-form-modal');
		div.setAttribute('data-type', 'update');

		div.setAttribute('data-id', student.id);
		div.setAttribute('data-roll', student.rollNo);
		div.setAttribute('data-name', student.name);
		div.setAttribute('data-email', student.email);
		div.setAttribute('data-age', student.age);
		div.setAttribute('data-date', student.date);
		div.setAttribute('data-gender', student.isMale ? 'male' : 'female');

		let rollNo = document.createElement('div');
		rollNo.classList.add('col');
		rollNo.innerHTML = student.rollNo;

		let name = document.createElement('div');
		name.classList.add('col');
		name.innerHTML = student.name;

		let age = document.createElement('div');
		age.classList.add('col', 'd-none', 'd-md-block');
		age.innerHTML = student.age;

		let date = document.createElement('div');
		date.classList.add('col', 'd-none', 'd-md-block');
		date.innerHTML = student.date;

		let gender = document.createElement('div');
		gender.classList.add('col', 'd-none', 'd-md-block');
		gender.innerHTML = student.isMale ? 'Male' : 'Female'

		div.append(rollNo);
		div.append(name);
		div.append(age);
		div.append(date);
		div.append(gender);
		studentList.append(div);
	}

	const displayStudentList = function (res) {
		console.log(res);
		studentList.text('');
		res.forEach((student) => {
			addStudent(student);
		})
	}

	const getStudentList = function () {
		$.ajax({
			url: api,
			method: 'GET',
			success: displayStudentList,
			error: (res) => {
				console.log('err:', res);
			}
		});
	}

	const resetModal = function () {
		studentModalTitle.text('Add New Student data:');
		studentFormData.btnText.text('Add');
		studentFormData.id.val('');
		studentFormData.roll.val('');
		studentFormData.name.val('');
		studentFormData.email.val('');
		studentFormData.age.val('');
		studentFormData.date.val('');
		studentFormData.gender.get(0).checked = true;
		studentForm.attr('method', 'POST');
	}

	const updateModalForCorrection = function (target) {
		studentModalTitle.text('Update Student data:');
		studentFormData.btnText.text('Update');
		studentFormData.id.val(target.data('id'));
		studentFormData.roll.val(target.data('roll'));
		studentFormData.name.val(target.data('name'));
		studentFormData.email.val(target.data('email'));
		studentFormData.age.val(target.data('age'));
		studentFormData.date.val(target.data('date'));
		studentFormData.gender.each((index) => {
			if (studentFormData.gender.get(index).value == target.data('gender')) {
				studentFormData.gender.get(index).checked = true;
			}
		});
		studentForm.attr('method', 'PUT');
	}

	const updateModal = function (e) {
		let target = $(e.relatedTarget);
		let type = target.data('type');
		// console.log(type);
		studentFormData.btn.attr('disabled', false);
		if (type === 'add') {
			resetModal();
		} else {
			updateModalForCorrection(target);
		}
	}

	const onSubmit = function (e) {
		e.preventDefault();
		let finalURL = api;
		if (studentForm.attr('method') == 'PUT')
			finalURL += studentFormData.id.val();
		let isMaleData = true;
		studentFormData.gender.each(index => {
			if (studentFormData.gender.get(index).checked) {
				isMaleData = studentFormData.gender.get(index).value == 'male';
			}
		})
		$.ajax({
			url: finalURL,
			data: {
				"rollNo": studentFormData.roll.val(),
				"name": studentFormData.name.val(),
				"age": studentFormData.age.val(),
				"email": studentFormData.email.val(),
				"date": studentFormData.date.val(),
				"isMale": isMaleData
			},
			method: studentForm.attr('method'),
			success: (res) => {
				console.log(res);
				studentModal.modal('hide');
				getStudentList();
			}
		});
		studentFormData.btn.attr('disabled', true);
	}

	$(document).ready(() => {
		getStudentList();
		studentModal.on('show.bs.modal', updateModal);
		studentForm.submit(onSubmit);
	});
})();