class Animal {
  constructor(name, sex) {
    this.name = name;
    this.sex = sex;
  }

  toString() {
    return this.name + 'は' + this.sex + 'です。';
  }
}

// 従来通りのプロトタイプベースの記法でもメソッドを生やせる
Animal.prototype.show = function() {
  console.log(this.toString());
}

var animal = new Animal('きら', 'メス');
console.log(animal.toString());

// 従来通り Animal クラスの実体は関数
console.log(typeof Animal);
// --> function
