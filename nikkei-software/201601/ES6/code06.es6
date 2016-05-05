class Animal {
  set age(value) {
    console.log('setter が呼び出されました');
    if (value < 0 ) {
      throw new RangeError('age は正数で！');
    }
    this._age;
  }
  /* getter を省略すると書き込み専用になる
  get age() {
    console.log('getter が呼び出されました');
    return this._age;
  }
  */
}

var animal = new Animal();
animal.age = 10;
console.log(animal.age);
animal.age = -10;
console.log(animal.age);
