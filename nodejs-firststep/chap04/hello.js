var myName = 'Taro';

function greeting(something) {
	console.log(exports.myName + ': hello, ' + something + '!');
}

greeting('world');