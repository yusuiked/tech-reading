# 読書メモ

サポートサイトは[こちら](http://gihyo.jp/book/2012/978-4-7741-5376-6)。

# 第2章

## 文字列と数値

### 文字列の定義

シングルクォーテーションでもダブルクォーテーションでも違いはなく、同じ文字列として扱われる。ただし、[Google JavaScript Style Guide](http://google-styleguide.googlecode.com/svn/trunk/javascriptguide.xml?showone=Strings#Strings) の中で、「"」よりも「'」を使うことが推奨されている。理由は、HTML を含む文字列を作る際に便利なため。

### オブジェクトの操作

#### [] を使ってプロパティ名を指定して値を取得

通常、入れ子になっているオブジェクトのプロパティの指定は、`.` 繋ぎで参照するが、`[]` を使って参照することもできる。この時に指定するキー名は、変数でもOK。

```lang-javascript
var person = {
	name: '鈴木一郎',
	age: 20,
	gender: 'male'
};
var key = 'name';
var name = person[key];
console.log(name);	//=> 鈴木一郎
```

## 条件判定のための型と演算子

### 比較演算子

`===` は厳密な比較を行う。例えば、次のような違いがある。

```lang-javascript
console.log('10' == 10);	//=> true
console.log('10' === 10);	//=> false
```

基本的には、厳密比較を行う `===` を使うべき。CoffeeScript では `==` しか存在しないが、JavaScript に変換後は `===` に変換されることからもそれが言える。

### 条件式

#### false に変換される値

「データがない」とみなされる値は、条件式で false に変換される。具体的には次の5つ。

* 0（数値のゼロ）
* NaN
* 空文字
* null
* undefined

## 関数

### 関数の定義と呼び出し

#### オブジェクトのプロパティとして定義する

JavaScript の関数はファーストクラスオブジェクトなので、オブジェクトのプロパティとしても定義できる。

```lang-javascript
var person = {
	name: '鈴木一郎',
	age: 20,
	say: function() {
		console.log('こんにちは!');	}}

person.say();	//=> こんにちは!
```

### 引数

#### 引数の数を間違えるとどうなるのか

JavaScript では文法的にエラーではなく、単に `undefined` が返る。

```lang-javascript
function func(a) {
	console.log(a);}

func();	//=> undefined
```

### 戻り値

return を使って値を返す。値を返さない関数（Java でいうところの void）は、正確には `undefined` が返る。

# 第3章

## window オブジェクト

window オブジェクトは組み込みのオブジェクトで、最初から window オブジェクトを使うことができる。

## window オブジェクトの省略

window オブジェクトは、JavaScript では特別な扱いになっており、window オブジェクトのプロパティにアクセスするときは `window` を省略できる。

```lang-javascript
alert('hello, world');
```

`console.log` も、`window` オブジェクトが省略されている。

## DOM

### 要素の取得

#### querySelector, querySelectorAll

`getElementById`, `getElementsByTagName`, `getElementsByClassName` などの他にも、要素の取得を行える関数がある。`querySelector`, `querySelectorAll` は、CSS のセレクタを使って要素を取得する関数。

```
document.querySelector(セレクタ)
document.querySelectorAll(セレクタ)
```

```lang-javascript
document.querySelector('#box');
document.querySelectorAll('.box');
document.querySelectorAll('div');
document.querySelectorAll('#box > ul');
document.querySelectorAll('input[type=radio]');
```

ただし、Internet Explorer のバージョン7以下は対応していない。

## イベント

### 要素のプロパティによるイベントの設定

#### 要素のプロパティによるイベント設定の欠点

`onclick` などの要素オブジェクトのプロパティにイベントを設定する方法は、イベントを1つしか設定できない。この欠点を解消するには、`addEventListener` を使う。

```
対象の要素.addEventListener('なにか起きたら', '何かする', false);
```

ただし、Internet Explorer のバージョン8以下では対応していない。代わりに、`attachEvent` という関数があるが、これに対応することによってブラウザによって処理を分ける必要がある。
ブラウザにかかわらずこういう処理を行うためには、jQuery などのライブラリを使う。

### window.onload と DOMContentLoaded

ブラウザは HTML を上から順にレンダリングしていくため、DOM を使った JavaScript を実行する場合、要素が JavaScript 実行よりも前にレンダリングされていないと動作しない。

これを解消するために、`window.onload` イベントを使うことで、HTML の読み込みが終わった時に処理を実行することが保証される。ただし、`window.onload` にも欠点があり、画像などの外部ファイルのダウンロードも全て終了しないとイベントの処理が実行されないため、サイズが大きい画像などの読み込みが入る場合、JavaScript の実行も遅れる。

さらにこれを解決する方法として、`DOMContentLoaded` というイベントがある。これも HTML を読み込みが終わった時点で処理が実行されるイベントだが、画像などのダウンロードは待たず、HTML を JavaScript から取得・操作可能な準備ができた時点で実行される。
`DOMContentLoaded` イベントは、要素のプロパティにイベントを設定する方法では設定できないため、`addEventListener` で設定する必要がある。

```lang-javascript
<div id="box">This is box</div>

document.addEventListener('DOMContentLoaded', function() {
	var box = document.getElementById('box');
	console.log(box);	//=> <div id="box">This is box</div>
}, false);
```

`DOMContentLoaded` イベントは、Internet Explorer のバージョン8以下では使えない。

jQuery ではこの処理をクロスブラウザで行える書き方があり、 `$(function() {})` と書くことで同様の処理が行える。

# 第4章

