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

