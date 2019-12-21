function printInfo(students) {
    for (var _i = 0, students_1 = students; _i < students_1.length; _i++) {
        var s = students_1[_i];
        console.log(s);
    }
}
printInfo([{ id: 1, name: 'abc', cgpa: 5.6 },
    { id: 2, name: 'ghi', cgpa: 5 },
    { id: 3, name: 'def' },
    { id: 4, name: 'jkl' },
]);
