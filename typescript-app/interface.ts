interface IStudent {
	id: Number,
	name: String,
	cgpa?: Number
}

function printInfo(students: IStudent[]) {
	for (let s of students) {
		console.log(s);
	}
}

printInfo([{ id: 1, name: 'abc', cgpa: 5.6 },
{ id: 2, name: 'ghi', cgpa: 5 },
{ id: 3, name: 'def' },
{ id: 4, name: 'jkl' },
]);