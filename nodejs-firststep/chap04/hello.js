var myName = 'Taro';
exports.myName = myName;

function greeting(something) {
	console.log(exports.myName + ': hello, ' + something + '!');
}
exports.greeting = greeting;

greeting('world');