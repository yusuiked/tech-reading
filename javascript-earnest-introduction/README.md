# 読書メモ

サポートページは[こちら](http://gihyo.jp/book/2010/978-4-7741-4466-5/support)

# 第2章 基本的な書き方を身につける

## JavaScript の基本的な記法

### JavaScript を HTML ファイルに組み込む

```lang-markup
<script type="text/javascript">
<!--
JavaScript のコード
// ->
</script>
```

* コメントで JavaScript を囲んでいるのは、JavaScript に対応してないブラウザで JavaScript がそのまま露出してしまうのを避けるため。現在では JavaScript に対応していないブラウザは少なくなっているため、この記述は廃れてきている。
* `<script>` タグの `type` 属性は古いブラウザでは `language` 属性だった（HTML 4.0 以降では非推奨）。現在では主要なブラウザは `type` 属性に対応しているので、併記したりする必要はない。

#### XHTML 文書で `<script>` タグを利用するときの注意

XHTML でも `<script>` タグは利用できるが、HTML 4.01 でのそれと微妙に仕様が異なっているので、注意する。

* `<` はタグの開始とみなされる
* `&` は実体参照の開始とみなされる
* `<!-- -->` で囲まれた内容はコメントとみなされる

HTML 4.01 では `<script>` タグの中身を CDATA と見なすが、XHTML では #PCDATA と見なす。
XHTML で `<script>` タグの中身に JavaScript コードを埋め込みたい場合は、以下のように記述する。

```lang-markup
<script type="text/javascript">
//<![CDATA[
JavaScript のコード
//]]>
</script>
```

<![CDATA[]]> という記法は、XML の文字列データを定義する記法だが、このまま記述しても一部のブラウザでは正しく認識されないので、`//` を付けてコメントアウトするのが一般的。

## データ型

### リテラル

#### `undefined` には代入も可能

以下のコードはエラーにはならない。`undefined` は `Global` オブジェクトのプロパティであるため。

```lang-javascript
undefined = 10;	// 思わぬ悪影響を及ぼす可能性があるので絶対にやらない
```

## 演算子

### 算術演算子

#### 小数点を含む計算には要注意

```lang-javascript
document.writeln(0.2 * 3);	// 0.6000000000000001
```

この結果は、JavaScript が内部的には数値演算を2進数で行っているための誤差。なので、以下の様なコードは直感的ではない結果となる。

```lang-javascript
document.writeln(0.2 * 3 === 0.6);	// false
```

これを防ぐには、有効桁数を保証する桁までx倍して、計算してから小数に戻すようにする。

### 比較演算子

#### 等価演算子（`==`）

全体として、「なんとか等しいと見なせないか」を試みるように振る舞う。

| 左辺／右辺の型 | データ型             | 評価基準                                                                        |
| -------------- | -------------------- | ------------------------------------------------------------------------------- |
| 同じ           | 文字列／数値／論理型 | 単純に双方の値が等しいかを判定（同値性比較だが、Java の同値性比較とは異なる）   |
| 同じ           | 配列／オブジェクト   | 参照先が等しいかを判定（同一性比較）                                            |
| 同じ           | `null`／`undefined`  | 双方とも `null`／`undefined` 、または `null` と `undefined` の比較は全て `true` |
| 異なる         | 文字列／数値／論理型 | 文字列／論理型を数値に変換した上で判定                                          |
| 異なる         | オブジェクト         | 基本型に変換した上で判定                                                        |

```lang-javascript
console.log(1 == true)	// true
var array1 = ['JavaScript', 'Ajax', 'ASP.NET'];
var array2 = ['JavaScript', 'Ajax', 'ASP.NET'];
console.log(array1 == array2);	// false
```

#### 同値演算子（`===`）

以下のコードは、等価演算子で比較した場合いずれも `true` と判定される。

```lang-javascript
console.log('3.14E2' == 314);
console.log('0x10' == 16);
console.log('1' == 1);
```

型は違うが、JavaScript 的には等価判定。これを厳密に判定したい（型まで含めて判定したい）場合は、同値演算子を使う。

```lang-javascript
console.log('3.14E2' === 314);	// false
console.log('0x10' === 16);	// false
console.log('1' === 1);	// false
```

不等価演算子 `!=` と、非同値演算子 `!==` でも同じ関係が成り立つ。

### その他の演算子

* ,
* delete
* instanceof
* new
* typeof
* void

#### delete 演算子

オペランドに指定した変数や配列要素、オブジェクトのプロパティを破棄する。成功すれば `true` を、失敗すれば 'false' を返す。

1. 配列要素を削除した場合、後ろの要素が繰り上がるわけではない（インデックス番号は変わらないし、length もそのまま）
2. プロパティを削除した場合、プロパティそのものが削除されるだけで、プロパティが参照するオブジェクトが削除されるわけではない
3. 明示的に宣言された変数を削除することはできない（`var data1` は消せないが、`data2` で暗黙的に使っている変数は削除できる）

#### typeof 演算子

オペランドに指定した変数／リテラルのデータ型を文字列で返す。基本型は識別できるが、参照型は一様に `object` で返ってくる。どの種類のオブジェクトかを知りたい場合は、`instanceof` 演算子や `constructor` プロパティを使う。

# 第3章 基本データを操作する〜組み込みオブジェクト

## オブジェクトとは

### JavaScript の組み込みオブジェクト

| オブジェクト | 概要                                                |
| ------------ | --------------------------------------------------- |
| (Global)     | JavaScript の基本機能にアクセスするための手段を提供 |
| Object       | 全てのオブジェクトのひな形となる機能を提供          |
| Array        | 配列を操作するための手段を提供                      |
| String       | 文字列を操作するための手段を提供                    |
| Boolean      | 真偽値を操作するための手段を提供                    |
| Number       | 数値を操作するための手段を提供                      |
| Function     | 関数を操作するための手段を提供                      |
| Math         | 数値演算を実行するための手段を提供                  |
| Date         | 日付を操作するための手段を提供                      |
| RegExp       | 正規表現に関わる機能を提供                          |
| Error        | エラー情報を管理                                    |

基本データ型を `new` 演算子を使ってインスタンス化するのは、冗長であるだけでなく、有害な作用を及ぼす可能性があるため、原則避けること。
基本型のラッパーオブジェクトは、JavaScript では相互変換をじ動的に行うため、アプリケーション開発者がこれを意識する必要はない。

## 基本データを扱うためのオブジェクト

### String オブジェクト

```lang-javascript
var str1 = 'にわにはにわにわとりがいる';
document.writeln(str1.indexOf('にわ'));	// 0
document.writeln(str1.lastIndexOf('にわ'));	// 6
document.writeln(str1.indexOf('にわ', 3));	// 4（4文字目から右方向検索）
document.writeln(str1.lastIndexOf('にわ', 5));	// 4（6文字目から左方向検索）
document.writeln(str1.indexOf('ガーデン'));	// -1

var str2 = 'Wingsプロジェクト';
document.writeln(str2.charAt(4));	// s
document.writeln(str2.slice(5, 8));	// プロジ（6 〜 8文字目を抽出）
document.writeln(str2.substring(5, 8));	// プロジ（6 〜 8文字目を抽出）
document.writeln(str2.substr(5, 3));	// プロジ（6文字目から3文字抽出）
document.writeln(str2.split('s'));	// Wing,プロジェクト
document.writeln(str1.split('わ', 3));	// に,にはに,に（3つに分割）

document.writeln('トップ'.anchor('top'));
document.writeln(str2.link('http://www.wings.msn.to/'));
document.writeln('10'.sub());
document.writeln('10'.sup());

document.writeln(str2.concat('有限会社'));
document.writeln(str2.length);
```

### 部分文字列を抽出するときの2つの注意点

JavaScript では、部分文字列を抽出するためのメソッドが3つある。

* substring
* slice
* substr

これらのうち、`substring`/`slice` と `substr` メソッドに関しては、以下のように明確な違いがある。

* `substring/slice` 開始位置〜終了位置の範囲で抽出箇所を指定
* `substr` 開始位置からの文字数指定で抽出箇所を特定

`substring`/`slice` メソッドについては、以下の条件で異なる挙動を取る。

#### 引数 start > 引数 end である場合

この場合、`substring` メソッドは引数 start と 引数 end との関係を入れ替えて end+1 〜 start 文字目までを抽出する。`slice` メソッドは入れ替えはせず、そのまま空文字列を返す。

```lang-javascript
var str = 'WINGSプロジェクト';
document.writeln(str.substring(8, 5));	// プロジ（6 〜 8 文字目を抽出）
document.writeln(str.slice(8, 5));	// 空文字列
```

#### 引数 start / end に負の数を指定した場合

この場合、`substring` メソッドは無条件に0と見なすが、`slice` メソッドは「文字列末尾からの文字数」と見なす。

```lang-javascript
var str = 'WINGSプロジェクト';
document.writeln(str.substring(5, -2));	// WINGS（1 〜 5文字目を抽出）
document.writeln(str.slice(5, -2));	// プロジェ（6 〜 9文字目までを抽出）
```

### Number オブジェクト

> chap3/number.html

### Math オブジェクト

> chap3/math.html

#### with 命令

```lang-javascript
with(document) {
	writeln(Math.abs(-15));
	writeln(Math.max(10, 15));
	writeln(Math.min(-10, 0));}
```

便利な半面、「ブロック内の処理速度が低下する」「そもそもコードが読みにくくなる」というデメリットがあるため、プロダクトコードでは使わないほうが良い。

### Array オブジェクト

Array オブジェクトは、リテラル表記でもコンストラクタ経由でも生成できるが、コンストラクタ経由での生成は以下の理由から避けたほうが良い。

```lang-javascript
var array = new Array(10);		// 10という要素を持つ配列ではなく、長さが10の空の配列を意味する
```

> chap3/array.html

#### Array オブジェクトのメンバで注意すべき３つのこと

1. 多くのメソッドは元の配列に影響を及ぼす（副作用がある）
2. 配列の中身は `toString` メソッドで確認する
3. `sort` メソッドにはユーザ定義関数も指定可能

```lang-javascript
var ary = [5, 25, 10];
console.log(ary.sort());	// 10, 25, 5（文字列としてソート）
console.log(ary.sort(function(x, y) {return x - y;}));	// 5, 10, 25（数値としてソート）
```

## 日付・時刻データを操作する - Date オブジェクト -

### Date オブジェクトを生成する

Date オブジェクトは、リテラル表現は存在しないので、コンストラクタを使って生成する。

```lang-javascript
var d = new Date();	// 現在のシステム日付
var d = new Date('2010/12/04');	// 指定の日付
var d = new Date(2010, 11, 4, 23, 55, 30, 500);	// 指定のタイムスタンプ（月は 0 オリジン）
var d = new Date(1217862000000);	// 1970/1/1 00:00:00 からのタイムスタンプ(Unix タイム)
```

Date オブジェクトの持つメンバは、大きく分けて以下の分類の組合せで提供されている。

* 年、月、日、曜日、時、分、秒、ミリ秒、Unixタイムスタンプ、協定世界時との時差
* ローカル時刻か協定時か(get/setUTCxxx系)
* 日付文字列表現の解析(parse, UTC)
* 文字列変換（世界協定時、ローカル時刻、日付部分、時刻部分、文字列表現）

コードは省略。

### 日付・時刻データを加算・減算する

Date オブジェクトでは、日付・時刻を直接加算・減算するメソッドは提供されていないため、get メソッドと set メソッドを組合せて加減算を行う。

### 日付・時刻の差分を求める

差分も直接メソッドが提供されているわけではないため、以下の様なコードを記述する必要がある。

```lang-javascript
var date1 = new Date(2010, 4, 15);
var date2 = new Date(2010, 6, 20);
var diff = (date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24);
console.log(diff + '日の差があります。');
```

## 正規表現で文字を自在に指定する - RegExp オブジェクト -

### RegExp オブジェクトを生成する方法

大きく以下の2つの方法がある。

* RegExp オブジェクトのコンストラクタを経由する
* 正規表現リテラルを利用する

```lang-javascript
var 変数名 = new RegExp('正規表現', 'オプション');	// コンストラクタ
var 変数名 = /正規表現/オプション
```

2つの違いは、正規表現として文字列リテラルを与えるか、正規表現リテラルを与えるかによって、エスケープする文字が異なる。

* コンストラクタ構文では、`\` をエスケープする
* 正規表現リテラルでは、`/` をエスケープする

また、正規表現オブジェクトのオプションは、生成時に指定する他、プロパティやメソッドでも指定できる。

### `String.match` メソッドと `RegExp.exec` メソッドの挙動の違い

`exec`　メソッドは、`g` オプションが有効であるかどうかに関わらず、一度の実行で1つの実行結果しか返さない。`RegExp` オブジェクトは、最後にマッチした文字を記憶する機能を持っている。次回の `exec` メソッドを実行する際に前回のマッチ位置から検索を再開する。検索結果がない場合に null を返す。

マッチング情報を保持するためのプロパティから、その情報も取れる。一覧は以下。

* lastIndex
* leftContext($`)
* rightContext($')
* lastMatch($&)
* lastParen($+)
* $1〜$9

### マッチングの成否を検証する

単に「マッチしているかどうか」を知りたいだけの場合は、`test` メソッドを利用すれば可能。結果を `true/false` で返してくれる。

また、指定された正規表現で最初にマッチした文字位置を返す `String.search` メソッドを利用することもできる。存在しない場合は -1 を返す。

> chap3/regexp.html

### 正規表現で文字列を置き換える

`String.replace` メソッドを利用すれば、正規表現で一致したパターンを置換することもできる。

> chap3/regexp.html

置換後の文字列には、`$1`..`$9` という特殊変数を埋め込める。これらはサブマッチした文字列を保存するための変数で、これによって文字列を装飾することもできる。

### 正規表現で文字列を分割する

正規表現で文字列を分割するには、`String.split` メソッドを利用する。

## 全てのオブジェクトのひな形 - Object オブジェクト -

`Object` オブジェクトは、それ自体の利用を目的したものではなく、オブジェクトの共通的な性質／機能を提供する役割を担った、全てのオブジェクトの基本オブジェクト。

利用できる主なメンバを以下に列挙する。

* `constructor`
* `toString()`
* `valueOf()`
* `hasOwnProperty(prop)`
* `propertyIsEnumerable(prop)`
* `isPrototypeOf(obj)`

### オブジェクトを基本型に変換する - `toString` / `valueOf` メソッド -

* `toString` メソッドは、文字列を返す
* `valueOf` メソッドは、文字列以外の値が返されることを「期待して」使われる

> chap3/object.html

### インスタンスのオブジェクト型を判定する - `constructor` プロパティ -

`typeOf` 演算子は、あくまでも基本型を識別することしかできない。参照型の値については、一様に `object` しか返さない。

参照型の変数を識別するには、`constructor` プロパティを使用する必要がある。

```lang-javascript
var data = [];
if (typeOf data == 'object' && data.constructor == Array) {
	document.writeln('変数dataはArrayオブジェクトのインスタンス');}
```

厳密には、constructor プロパティは戻り値として、インスタンスの生成に使用されたコンストラクタ（`Function` オブジェクト）を返す。オブジェクト名の文字列を返しているわけではないので、`'` で括らないこと。

#### instanceof 演算子

`construtor` プロパティと同等の機能を持つ演算子として、`instanceof` 演算子もある。例えば、以下のように書き換えることができる。

```lang-javascript
if(typeof data == 'object' && data instanceof Array') {
	document.writeln('変数dataはArrayオブジェクトのインスタンス');
}
```

### 匿名オブジェクトを作成する

```lang-javascript
var obj = new Object();
```

上記のようなオブジェクトを、独自のオブジェクト名を持たないという意味で匿名オブジェクト（無名オブジェクト）と呼ぶ。

その場限りしか使わず、再利用することのないような一時的な構造データを受け渡しする場合によく使う。プロパティだけでなく、関数リテラルをプロパティとして持たせることで、メソッドを定義することもできる。ただ、一時的な構造データというシーンでは、あまりメソッドを定義する機会は多くないはず。

#### オブジェクトリテラル

また、上記のコードは、オブジェクトリテラルを使って、

```lang-javascript
var obj = {};
```

とも書きかえることができる。オブジェクトリテラルでオブジェクトを生成することは、匿名オブジェクトを定義することと同じ意味。

## 基本機能を提供する - Global オブジェクト -

### Global オブジェクトとそのメンバ

Global オブジェクトは、これまでのオブジェクトとは異なり、

```lang-javascript
var g = new Global();
Global.メソッド名();
```

のようにインスタンス化することも、配下のメソッドを呼ぶこともできない。

Global オブジェクトは、グローバル変数やグローバル関数を管理するために、JavaScript が自動的に生成する「便宜的な」オブジェクト。

JavaScript で利用可能なグローバル変数／関数を以下に列挙する。

* NaN
* Infinity
* undefined
* isFinite(num)
* isNaN(num)
* Boolean(val)
* Number(val)
* String(val)
* parseFloat(str)
* parseInt(str)
* escape(str)
* unescape(str)
* encodeURI(str)
* decodeURI(str)
* encodeURIComponent(str)
* decodeURIComponent(str)
* eval(exp)

### 数値へ明示的に変換する - `parseFloat/parseInt/Number` 関数 -

JavaScript はコンテキスト（前後の関数や演算子）によって、適切な型へ変換しようとしてくれるが、これが直感的じゃない結果となりバグの温床になるようなケースも有る。
そこで JavaScript では、明示的に変換するための方法を提供している。`parseFloat/parseInt/Number` 関数はそれを担っている。

これらの変換は、例えば `123xxx` のような文字列混在の数値が渡された場合、`123` と解析できる部分を数値として取り込む。（先頭からの連続した数値のみ）。`Number` 関数は、解析できずに `NaN` を返す。

Date オブジェクトが渡された場合は、parse系の関数はこれを解析できずに `NaN` を返すが、`Number` 関数は「Date オブジェクトを経過ミリ秒に換算した値」を数値として返す。

> chap3/global.html

#### Number 関数と Number オブジェクト

Global オブジェクトが提供するグローバル関数 `Number` の正体は、組み込みオブジェクト `Number` でもある。同じように、`String/Boolean` 関数は、`String/Boolean` オブジェクトである。

```lang-javascript
console.log(new Number(d));
var str = new String('123');
var str = String('123');
```

### 算術演算子による文字列／数値への変換

* `+` 演算子は、文字列連結演算子と加算演算子の2つの顔を持っている。オペランドの片方が文字列である場合、文字列連結演算子として振る舞う。
* `-` 演算子は、与えられたオペランドのいずれかが数値だった場合、自動的に他方も数値として変換した上で減算する。

### 動的に生成したスクリプトを実行する - `eval` 関数 -

`eval` 関数は、与えられた文字列を JavaScript のコードとして評価／実行する。

ただし、以下の様な作用もあるため、乱用はしない。

* 第3者による任意のスクリプト実行が可能（セキュリティリスク）
* 通常のコードを実行するよりも、解析処理が入るため処理速度が遅い（パフォーマンスの劣化）

`eval` 関数の利用は JSON データを解析する場合に留めるのが無難。

# 第4章 繰り返し利用するコードを一箇所にまとめる

## 関数とは

### 関数を定義する三つの方法

* function 命令で定義する
* Function コンストラクタ経由で定義する
* 関数リテラル表現で定義する

この中で、あえて Function コンストラクタを利用するメリットはないが、一点だけ重要な特徴がある。それは「Function コンストラクタでは、引数や関数本体を文字列として定義できる」という点。

```lang-javascript
var param = 'height, width';
var formula = 'return height * width /2;';
var diamond = new Function(param, formula);

console.log('ひし形の面積：' + diamond(5,2));	// 5
```

また、Function コンストラクタを利用する場合は、解析処理を含むため繰り返し処理や頻繁に呼び出される関数の中で利用するとパフォーマンスが劣化することに気をつける。

関数リテラルと function キーワードでの関数定義の違いは、

* function キーワード -> 関数 triangle を直接定義している
* 関数リテラル -> 「function(base, height) {...}」と名前の無い関数を定義した上で、変数 triangle に格納している

このような違いから、関数リテラルは匿名関数、無名関数と呼ばれることもある。

## 関数定義における4つの注意点

### return 命令は途中で改行しない

JavaScript では文末にセミコロンをつけることは必須ではないため、`return` を挟んで改行してしまうと、直感的ではない挙動を取ることがあるため、避ける。

```lang-javascript
var triangle = function(base, height) {
	return
	base * height / 2;	// 実行されない};
console.log('三角形の面積：' + triangle(5, 2));
```

同じ理由で、`break`/`continue` も途中で改行しないこと。

### 関数はデータ型の一種

```lang-javascript
var triangle = function(base, height) {
	return base * height / 2;};
console.log(triangle(5,2));	// 5
triangle = 0;	// 正しいコード
console.log(triangle(5,2));	// 0
```

### function 命令は静的な構造を宣言する

```lang-javascript
console.log(triangle(5,2));
function triangle(base, height) {
	return base * height / 2;}
```

関数定義が変数定義である、という前提に基づくと、上記のコードはまだ定義されていない関数を呼び出しているためエラーになりそうだが、これはエラーにならない。

function 命令は、静的な構造を宣言するためのキーワードであるため、コードを解析コンパイルするタイミングで関数を登録している。そのため、コードのどこからでも呼び出すことができる。

### 関数リテラル／Function コンストラクタは実行時に評価される

```lang-javascript
console.log(triangle(5,2));
var triangle = function(base, height) {
	return base * height / 2;}
```
これは、実行時エラーになる。function 命令とは異なり、実行時（代入時）に評価されるため、`triangle` を呼び出すタイミングではまだ関数は定義されていないためエラーとなる。

## 変数はどの場所から参照できるか - スコープ -

JavaScript には、以下の2つのスコープがある。

* グローバルスコープ
* 定義された関数の中でのみ参照できるローカルスコープ

```lang-javascript
var scope = 'Global';
function getValue() {
	var scope = 'Local';
	return scope;}
console.log(getValue());	// Local
console.log(scope);	// Global
```

### 変数宣言に var 命令は必須

```lang-javascript
scope = 'Global';
function getValue() {
	scope = 'Local';
	return scope;}
console.log(getValue());	// Local
console.log(scope);	// Local
```

var 命令を使わずに宣言された変数はすべてグローバルスコープとみなされるため、たとえ関数の中であっても書き換えられてしまう。

ローカル変数を定義するには、必ず var 命令を使用すること。かといって、グローバルとローカルで var をつけるつけないを区別するのは混乱のもとであるため、原則的には変数宣言は var を付けることを統一したほうが良い。

### ローカル変数の有効範囲はどこまで？

```lang-javascript
var scope = 'Global';
function getValue() {
	console.log(scope);	// まだローカル変数 scope が定義されていないため、undefined でエラー
	var scope = 'Local';
	return scope;}
console.log(getValue());	// Local
console.log(scope);	// Global
```

これを防ぐために、**ローカル変数は関数の先頭で宣言する**ことを心がける。

### 仮引数のスコープ

仮引数のスコープは、基本型と参照型によって挙動が異なる。

```lang-javascript
var value = 10;
function decrementValue(value) {
	value--;
	return value;}
console.log(decrementValue(100));	// 99
console.log(value);	// 10 でそのまま
```

```lang-javascript
var value = [1, 2, 4, 8, 16];
function deleteElement(value) {
	value.pop();	// 末尾の要素を削除
	return value;}
console.log(deleteElement(value));	// 1, 2, 4, 8
console.log(value);	// 1, 2, 4, 8 で元の value も書き換わっている（インスタンスの参照だから）
```

### ブロックレベルのスコープは存在しない

JavaScript ではローカル変数は関数全体で有効なので、ブロックスコープという概念はない。if や for、ブロックのなかで変数宣言をしても、同じ関数の中であれば有効。

以下 Java のブロックスコープの例。

```lang-java
if (true) {
	int i = 5;	// ブロックスコープ}
System.out.println(i);	// エラー
```

こちらは JavaScript の例。

```lang-javascript
if (true) {
	var i = 5;	// あくまでも関数内ローカルスコープ}
console.log(i);	// 5
```

#### 擬似的にブロックスコープを定義する

With 命令を使うことで、擬似的にブロックスコープを表現することができるが、トリッキーなので勧めない。

```lang-javascript
with ({i:0}) {
	if (true) {
		i = 5;	}}
console.log(i);	// スコープ外でエラー
```

これがエラーになる理由は、匿名オブジェクトのプロパティ `i` であり、`with` ブロックの中でのみ参照可能な変数となるため。

### 関数リテラル／Function コンストラクタにおけるスコープの違い

```lang-javascript
var scope = 'Global';
function checkScope() {
	var scope = 'Local';
	var f_literal = function() { return scope; };
	console.log(f_literal());	// Local
	var f_constructor = new Function('return scope;');
	console.log(f_constructor());	// Global}
checkScope();
```

これは ECMAScript の仕様にも明記されており、スコープチェーンの違いにより挙動が異なる。

Function コンストラクタを原則利用しないようにすればこの違いが混乱を招くケースは少なくできるが、違いがあることは認識しておくこと。

## 引数情報を管理する - arguments オブジェクト -

### JavaScript は引数の数をチェックしない

```lang-javascript
function showMessage(value) {
	console.log(value);}
showMessage();	// undefined
showMessage('山田');	// 山田
showMessage('山田', '鈴木');	// 山田
```

上記のコードは全て正しく動作する。2つ目の引数は無視される。ただし、捨てているわけではなく、`arguments` オブジェクトに格納されており、このオブジェクト経由で利用することもできる。

#### arguments と Arguments どちらが正しい？

`arguments` オブジェクトの実体は、厳密には「`Arguments` オブジェクトを参照する `arguments` プロパティ」。`Arguments` オブジェクトはあくまで関数内部で暗黙的に生成されるもので、プログラマが意識することはない存在。`Arguments.length` と書くこともできない。

#### 引数のデフォルト値を設定する

引数の数をチェックしない、ということはすべての引数は省略可能であるということ。ただし、引数が省略されただけでは、正しく動作しないことがほとんど。

そのため、デフォルト値を設定しておく。

```lang-javascript
function triangle(base, height) {
	if (base == undefined) { base = 1; }
	if (height == undefined) { height = 1; }
	return base * height / 2;}
console.log(triangle(5));	// 2.5
```

引数 `base` だけを省略することはできない。「省略できるのは、あくまで後ろの引数だけ」である。

### 可変長引数の関数を定義する

上記の内容は、「コンパイラがチェックすべきことで、アプリケーション側でチェックしなければならないのはデメリットでは？」という意見もある。

しかし、`arguments` オブジェクトの用途は、引数チェックだけではなく、**可変長引数の関数**という重要な機能がある。

可変長引数の関数を利用することで、柔軟に処理を記述することができる。例えば、以下は引数に与えられた数値を合計する `sum` 関数を定義する例。

> chap4/variableArgs.html

### 明示的に宣言された引数と可変長引数を混在させる

> chap4/vairableArgs2.html

明示的に宣言された引数と可変長引数は、`arguments` の中では一緒に格納されていることは誤解しないようにする。

構文的には、すべての引数を可変長引数として記述できるが、インデックス番号で引数を指定するよりも、名前で管理する方が圧倒的に読みやすいため、明示できる引数についてはできるだけ名前をつけること。

### 再帰呼び出しを定義する - `callee` プロパティ -

`arguments` オブジェクトでもう一つ重要なのが、現在実行中の関数自身を参照するために用意されている `callee` プロパティ。これを使うことで、再帰処理が容易に書ける。

> chap4/callee.html

## 高度な関数のテーマ

### 名前付き引数でコードを読みやすくする

```lang-javascript
	triangle({ base: 5, height: 4 })
```

名前付き引数を用いることで、以下の様なメリットがある。

* 引数が多くなっても、コードの意味がわかりやすい
* 省略可能な引数をスマートに表現できる
* 引数の順番を自由に変更できる

```lang-javascript
	triangle({ height: 4 }) // 引数省略
	triangle({ height: 4, base: 5 })	// 順番変更
```

これは、以下の様なケースで有効。

* そもそも引数の数が多い
* 省略可能な引数が多く、省略パターンにも様々な組合せがある

名前付き引数と言っても難しいことはなく、引数を匿名オブジェクトで受け取っているだけ。

### 関数の引数も関数 - 高階関数 -

