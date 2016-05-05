class Animal {
  constructor(name, sex) {
    this.name = name;
    this.sex = sex;
  }

  toString() {
    return this.name + 'は' + this.sex + 'です。';
  }
}

var animal = new Animal('きら', 'メス');
console.log(animal.toString());
