var myname = 'typescript';
var version = 3.7;
var isMale = true;
var displayFormat = "name is " + myname + "\nversion: " + version + "\nisMale: " + isMale + "\n";
console.log(displayFormat);
function f1() {
    console.log('Inside funtion 1');
}
f1();
var f2 = function () {
    console.log('inside f2');
};
f2();
setTimeout(function () {
    console.log('After 3 seconds');
}, 3000);
