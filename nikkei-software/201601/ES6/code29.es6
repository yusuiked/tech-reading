// import 構文でライブラリをインポート
import * as Nikkei from './Nikkei'; // as はモジュールに与える別名。import する側で名前空間を定義できる

// Nikkei.js では以下のように定義されている
export class Animal {}  // インポートされる
class Fish {} // export が付いていないのでインポートされない

// default 構文でインポート
// Nsw.js 側
export default class {}

// inport する側
import Animal from './Nsw';
var animal = new Animal('きら', 'メス');
