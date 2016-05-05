class Animal {
  /* setter を省略するとこのプロパティは読み取り専用になる
  set age(value) {
    console.log('setter が呼び出されました');
    if (value < 0 ) {
      throw new RangeError('age は正数で！');
    }
    this._age;
  }
  */
  get age() {
    console.log('getter が呼び出されました');
    return this._age;
  }
}

var animal = new Animal();
animal.age = 10;
console.log(animal.age);
animal.age = -10;
console.log(animal.age);

// --> Cannot set property age of #<Animal> which has only a getter
