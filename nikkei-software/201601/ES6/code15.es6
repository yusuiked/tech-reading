// 重複なしの集合を表す Set
let s = new Set();
s.add('XML');
s.add('JavaScript');
s.add('PHP');
s.add('Ruby');
s.add('JavaScript');

console.log(s.has('XML'));  // --> true
