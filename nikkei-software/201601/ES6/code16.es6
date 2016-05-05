// 配列や Map, Set の中身を列挙する for..of
let data = ['JavaScript', 'Ruby', 'PHP', 'Python'];

for (let str of data) {
  console.log(str);
}

// 従来はこう書いていた
/*
var data = ['JavaScript', 'Ruby', 'PHP', 'Python'];

for (var i = 0; i < data.length; i++) {
  console.log(data[if]);
}
*/
