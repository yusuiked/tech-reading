var i = 0;

var book = {
  ['image' + (++i)]: 'cover.jpg',
  ['image' + (++i)]: 'logo.jpg',
  ['image' + (++i)]: 'backcover.jpg',
}

console.log(book.image1);
console.log(book.image2);
console.log(book.image3);
