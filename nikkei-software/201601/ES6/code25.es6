// ES5 の関数リテラルと ES6 のアロー関数の違い
var Person = function(name) {
  this.name = name;
  this.show = () => {
    // ES6 のアロー関数は this が常にオブジェクトが生成された時点の Person オブジェクトを指す
    console.log('こんにちは、' + this.name + 'さん');
  }
}

var p = new Person('理央');
setTimeout(p.show, 5000);

// ES5 の関数リテラルは this の内容が生成時と実行時で違う可能性がある
var Person = function(name) {
  var that = this;  // this の固定イディオム。that とか _this とか self とかを使う
  this.name = name;
  this.show = function() {
    // show 関数の実行時、この this はもう Person オブジェクトを指しておらず、show 関数自身を指してる
    console.log('こんにちは、' + this.name + 'さん');
    console.log('こんにちは、' + this.name + 'さん');
  }
}
