class Animal {
  constructor(name, sex) {
    this.name = name;
    this.sex = sex;
  }
  toString() {
    return this.name + 'は' + this.sex + 'です。';
  }
}

class Hamster extends Animal {
  constructor(name, sex, type) {
    super(name, sex);
    this.type = type;
  }
  toString() {
    return this.type + 'の' + super.toString();
  }
}

var animal = new Hamster('きら', 'メス', 'スノーホワイト');
console.log(animal.toString());
