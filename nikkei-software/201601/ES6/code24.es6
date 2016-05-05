// アロー関数
let data = [10, 20, 30, 40, 50];
let formatted = data.map(value => value * 1.08);

// アロー関数は本体の計算結果がそのまま戻り値とみなされる
// 引数が 1 つの場合は () を省略できる
// 本体が 1 文の場合は {} も省略できる
// 引数が 0 個の場合は () => alert('OK'); のように書く

// 従来の関数リテラル
/*
var data = [10, 20, 30, 40, 50];
var formatted = data.map(function (value) {
  return value * 1.08;
});
*/
