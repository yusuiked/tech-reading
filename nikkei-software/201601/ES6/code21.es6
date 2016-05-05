function triangle(base = 1, height = 1) {
  return base * height / 2;
}

console.log(triangle(2, 3));  // -> 3
// デフォルト引数適用
console.log(triangle()) // -> 0.5
