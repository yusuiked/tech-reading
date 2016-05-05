if (true) {
  let x = 1;  // let はブロックスコープ
}

console.log(x);
// --> x is not defined

// これによって以下の様な即時関数は不要になる
(function() {
  var x = 1;
}());

var x = 100;
var x = 200;

// 同じスコープ内で宣言が重複できなくなった
let y = 10;
let y = 20;

// --> Duplicate declaration "x"
