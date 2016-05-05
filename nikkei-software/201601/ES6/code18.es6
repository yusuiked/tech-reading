// オブジェクトも分割代入できる
let {width, height} = {width:250, height:150};
console.log(width); // --> 250

// プロパティ名を変えたい場合
let {width:w, height:h} = {width:250, height:100};
console.log(w);
