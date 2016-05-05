// 変数の分割代入
let [x, y, z] = ['赤', '白', '黃'];

console.log(x);
console.log(y);
console.log(z);

let [a, b, c, d] = [1, 2, 3, 4, 5, 6, 7]; // 5, 6, 7 は無視される

console.log(a);
console.log(b);
console.log(c);
console.log(d);

let [i, j, k, ...other] = [1, 2, 3, 4, 5, 6, 7];  // ... は演算子
console.log(other); // --> [4, 5, 6, 7]
