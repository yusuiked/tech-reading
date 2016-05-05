// 分割代入を使って関数の戻り値を複数にするイディオム
function div(x, y) {
  return [Math.floor(x / y), x % y];
}

let [d, q] = div(14, 3);
console.log(d); // -> 4
console.log(q); // -> 2
