// ... 演算子による可変長引数
function sum(...args) {
  let result = 0;
  for (let arg of args) {
    result += arg;
  }
  return result;
}

console.log(sum(100, 10));  // --> 110
console.log(sum(100, 10, 0, -1, 5));  // --> 114

// 従来ならこう書いてた
// 引数を取らないように見えるのに実は引数を取っている
/*
function sum() {
  var result = 0;
  for(var i = 0; i < arguments.length; i++) {
    result += arguments[i];
  }
  return result;
}
*/
