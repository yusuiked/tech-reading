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

#### `undifined` には代入も可能

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