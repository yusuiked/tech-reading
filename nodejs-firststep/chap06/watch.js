var fs = require('fs');
var watcher = fs.watch('../README.md', function (event, filename) {
	var name = filename || 'README.md';
	if (event == 'change') {
		console.log(name + ' changed.');
	} else {
		console.log(name + ' renamed.');
	}
});