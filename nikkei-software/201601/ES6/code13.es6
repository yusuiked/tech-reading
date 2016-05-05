if (true) {
  let x = 1;  // let はブロックスコープ
}

console.log(x);
// --> x is not defined

// これによって以下の様な即時関数は不要になる
(function() {
  var x = 1;
}());
