var i = 0;

function print() {
	i++;
	postMessage(i);
	setTimeout(print, 500);
}

print();