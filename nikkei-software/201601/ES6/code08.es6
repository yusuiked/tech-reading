var isbn = '978-4-7741-7568-3';
var title = 'AngularJSアプリケーション・プログラミング';

var book = {
  isbn,
  title,
  toString() {
    return this.title + '(' + this.isbn + ')';
  }
}
