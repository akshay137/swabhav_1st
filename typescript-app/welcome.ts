let myname: string = 'typescript';
let version: Number = 3.7;
let isMale: boolean = true;
let displayFormat =
	`name is ${myname}
version: ${version}
isMale: ${isMale}
`;

console.log(displayFormat);

function f1() {
	console.log('Inside funtion 1');
}
f1();

let f2 = () => {
	console.log('inside f2');
}
f2();

setTimeout(() => {
	console.log('After 3 seconds');
}, 3000);