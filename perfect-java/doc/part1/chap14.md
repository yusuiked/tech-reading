# ジェネリック型

## ジェネリック型の典型例

* ArrayList<String> と ArrayList<Integer> は概念的には異なる型として考えて問題ない
    * 内部的には Object 型に置き換えた型が1つ生成されるだけ（ <E extends Object> と等価）。ただし、ジェネリック型を使う側に適切なキャストが挿入される
    * <E extends {型名}> や <E super {型名}> とすると、境界のある型パラメータとして指定した型に置き換えられる。インタフェースでもOKで複数指定でき、アンパサンドで繋げる。並び順は クラス名 & インタフェース名 & インタフェース名 & …（複数OK）
    * このことを **イレイジャ** と呼ぶ。（実行時には型情報が消えているから）

もし、ジェネリック型の仕組みがなかった場合を考えると、ListString 型や ListInteger 型などを個別に作るか、Object 型として一括で扱って、具象クラス要素の型を instanceof 演算子で一つ一つ調べて、ダウンキャストを自分で挿し込む必要がある。これは、コンパイルできるが実行時例外（ClassCastException）が発生するおそれがある。つまり、型安全（タイプセーフ）ではない。可読性も落ちる。

ジェネリック型を上手く使うと、タイプセーフに記述することができ、コンパイル時にエラーを見つけることができる。実行時にエラーを見つけるよりも堅牢になる。

## ジェネリック型の形式

```
#!java

// 形式
ジェネリック型として定義されたクラスやインタフェース<境界内の任意のクラス>
// サンプル
List<String> list;
ArrayList<Integer> intArray;
```

## ジェネリック型の意味

* List<E> のように定義したジェネリック型は、 List<String> や List<Integer> などの新しい形を作るためのひな形としての役割を担う
    * ちょうどクラスとオブジェクトの関係に似ている
    * ジェネリック型をひな形として、それをインスタンス化した新しい形（パラメータ化された型）を生成する
    ** ただし、ジェネリック型をインスタンス化して生成した型も、オブジェクトに対するひな形の役割を担う。そういう意味で、ジェネリック型は **ひな形のひな形** と言える。

## ジェネリック型と多態性

クラスのインスタンスであるオブジェクトを生成するとき、new 式を使う。この時にパラメータ引数を渡すことができる。ジェネリック型に型名を<>の中に渡す。
List<E> のEに相当するものを **型変数** 、List<String> のStringに相当するものを **型引数** と呼ぶ。

ジェネリック型を別の側面で見ると、型変数に渡す型引数に応じたコードの切り替え、と見なすことができる。型に応じた動作の切り替えで思い出すのは、実行時にオブジェクトの型で動作を変える多態性（ポリモフィズム）である。

多態性とは、1つのコードが複数の型を扱えることを指す。この定義から、ジェネリック型も多態性の1つである。

* 継承をベースにした多態性は、型に応じた動作の切り替えを実行時に行う
* ジェネリック型による多態性は、コンパイル時に（概念的に）新しい形を創出し型に応じたコードの切り替えをする

## ジェネリック型宣言

```
#!java

// 宣言例
public class Owner<E> {
	private E element;
	public E get() {
		return element;
	}
	public void put(E element) {
		this.element = element;
	}
}
```

* 最初の行の <E> が型変数
* 複数の型変数をカンマで区切って並べることができる
    * この並んだリストを型パラメータと呼ぶ
* 型変数は慣習的に英語大文字1文字を使う

|型変数名|由来|
|--------|----|
|E|element|
|K|key|
|V|value|
|T|type|

ジェネリックに関する用語のまとめは以下。

|名前|意味|
|----|----|
|型パラメータ|ジェネリック型の型宣言の中の<>の中の型変数の並びのこと。class Owner<E>のE|
|型変数|型パラメータ内に並ぶ変数。ジェネリック型の定義内の型として利用される。Owner<E>の定義ないでE element;のように使われる|
|型引数|ジェネリックを使う時に<>に渡す具体的な型。Owner<String>のStringの部分|
|パラメータ化された型|型引数を渡された実際に使えるようになった型のこと。Owner<String>,Owner<Integer>など|

## 型変数

型変数を使える場所と使えない場所は以下のとおり。

* 使える場所
    * インスタンスフィールド変数の型（型引数も含む。以下同様）
    * インスタンスメソッドの返り値の型
    * インスタンスメソッド内のローカル変数の型
    * インスタンスメソッド内のパラメータ変数の型
    * ネストした型の型名
* 使えない場所
    * クラスフィールド変数の型
    * クラスメソッドの返り値の型
    * クラスメソッド内のローカル変数の型
    * クラスメソッド内のパラメータ変数の型
    * new演算子のオペランド

覚える必要はない。原理は単純で、内部的に Object 型に置き換えた型が1つ生成されるだけであるため。List<String> List<Integer> も List<Object> 相当のクラスを使いまわす。クラスが1つしか存在しないから、型変数をクラスフィールドやクラスメソッドに使えないことがわかる。クラスフィールドやクラスメソッドの中で型変数を使っても区別できないため。

また、型変数はObject型なので、型変数を使ったnew式が使えないこともわかる。

```
#!java

public class Owner<E> {
	public void put(E element) {
		this.element = new E(element); // コンパイルエラー
		// 結局 new Object(element) 相当に置き換わるため、ジェネリック型にとって意味のないオブジェクト生成
		// 同様に new E[n] のような配列の生成もできない。
	}
}
```

## 境界のある型パラメータ

```
#!java

public class Owner<E> { // <E extends Object> と等価
	private E element;
	public E get() {
		return element;
	}
	public void put(E element) {
		this.element = element;
	}
	public String getString() {
		return element.toString(); // OK（Object#toString()を呼んでいる）
	}
	public int getLength() {
		return element.length(); // コンパイルエラー（CharSequence#length()なので、Object型ではない）
	}
}
```

```
#!java

public class Owner<E extends CharSequence> { // 境界はCharSequenceインタフェースとした
	private E element;
	public E get() {
		return element;
	}
	public void put(E element) {
		this.element = element;
	}
	public String getString() {
		return element.toString(); // OK
	}
	public int getLength() {
		return element.length(); // OK
	}
}
// 上記の定義では、CharSequenceの下位型（クラスもしくはインタフェース）しか書けない
```

境界のある型パラメータは、境界型がクラスであろうとインタフェースであろうと extends を使う。
境界型に（拡張継承する）クラスを1つ書け、継承するインタフェースは複数書け、後から書く。

```
#!java

class Foo<E extends クラス名 & インタフェース名> // OK
class Foo<E extends インタフェース名 & クラス名> // NG
```

## 型引数のワイルドカード

次のようにコレクション型の継承関係で、上位型の変数に下位型のオブジェクトを代入できる。

```
#!java

List<Integer> nlist = new ArrayList<Integer>();
Collection<Integer> nlist = new ArrayList<Integer>();
```

要素型の継承関係による上位型の変数への代入はコンパイルエラー。

```
#!java

// コンパイルエラー（NumberはIntegerの上位型にも関わらず）
List<Number> list = new ArrayList<Integer>();
```

次のように型引数をワイルドカードを使い境界型を指定すると、コンパイルできる。
```
#!java

List<? extends Number> nlist = new ArrayList<Integer>();
```

現実的には型引数ワイルドカードはメソッドのパラメータ変数に使う。

<? extends Number> の意味は、型引数がNumberの下位型であるパラメータ化された型を受け入れることを意味する。逆に <? super Number> と書くと、型引数がNumberの上位型であるパラメータ化された型を意味する。

## ジェネリックメソッドとジェネリックコンストラクタ

ジェネリック型と同様の仕組みでジェネリックメソッドとジェネリックコンストラクタがある。ジェネリックメソッドとジェネリックコンストラクタの理屈は同じ。

```
#!java

// ジェネリックメソッドの例
<T> List<T> arrayToList(T[] array) {
	List<T> list = new ArrayList<T>();
	for (T elem : array) {
		list.add(elem);
	}
	return list;
}
```

ジェネリックメソッドの型変数はメソッド内の以下の場所に使える。

* 返り値の型
* パラメータ変数の型
* ローカル変数の型

```
#!java

// 境界のある型パラメータを使う例
<T extends String> List<T> arrayToList(T[] array) {
	List<T> list = new ArrayList<T>();
	for (T elem : array) {
		elem += ".";
		list.add(elem);
	}
		return list;
}
```

ジェネリックメソッドとジェネリック型は独立したもので、非ジェネリッククラス内でもジェネリックメソッドを使える。例えば java.util.Collections クラスは非ジェネリッククラスだが、多くのジェネリックメソッドを持っている。クラスメソッドをジェネリックメソッドにすることも問題ない。

ジェネリッククラスとジェネリックメソッドは独立しているので、ジェネリッククラス内のメソッドをジェネリックメソッドとした場合、その型変数は独立している。

```
#!java

// ジェネリックメソッドの例
class <T> Foo {
	static <T> T f(T t) { // クラス宣言の<T>とメソッド宣言の<T>は別物
		return t;
	}
}
```

## ジェネリック型の設計

ジェネリック型を使う条件は、同じコード（アルゴリズム）で型ごとに独立したコードの実態が欲しい場合にジェネリック型を使う。

例えば、ジェネリック型を使わないで与えられた引数の中の最大値を返すメソッド（関数）を考える。
引数で与えた配列の要素の中で最大値の要素を返す、という仕様の場合、どんな要素型でも受け入れることを想定すると、最初に思いつくメソッド定義は次のようになる。

```
#!java

Object max(Object[] array)
```

maxメソッドの中では要素同士の比較が必要となるため、インタフェースに対してプログラミングする技法に通じていれば、次のようなインタフェース定義とmaxメソッドの実装が考えられる。

```
#!java

// 比較用インタフェース
interface MyComparable {
	int compareTo(Object o);
}
// max関数の実装
static Object max(MyComparble[] array) {
	MyComparable ret = null;
	for (MyComparable e : array) {
		if (ret == null) {
			ret = e;
			continue;
		}
		if (e.compareTo(ret) > 0) {
			ret = e;
		}
	}
	return ret;
}
```

maxメソッドの返り値の型はMyComparableでもよいが、比較可能であることの振る舞いを返り値に期待する理由は無いので、Object型にしている。

compareToメソッドの返り値の意味は java.lang.Comparable#compareTo と同じ。

MyComparableインタフェースの実装クラスを定義する。数値クラスと文字列クラスのために独自に比較用クラスを実装する。

```
#!java

class MyInteger implements MyComparable {
	int i;
	MyInteger(int i) {
		this.i = i;
	}
	@Override public int compareTo(Object o) {
		if (o instanceof MyInteger) {
			return i - (Integer)o;
		} else {
			throw new IllegalArgumentException();
		}
	}
}
```

```
#!java

class MyString implements MyComparable {
	String s;
	MyInteger(String s) {
		this.s = s;
	}
	@Override public int compareTo(Object o) {
		if (o instanceof MyString) {
			return s.length() - ((MyString)o).s.length();
		} else {
			throw new IllegalArgumentException();
		}
	}
}
```

compareToメソッドは、比較対象オブジェクトが同じクラスでない場合に実行時例外を投げるようにしている。本質的に不正な比較なので、実行時例外を投げることは妥当。

maxメソッドを使ってみる。次のコードはObject型からMyString型へのダウンキャストがあることを無視すれば、動作に問題はない。

```
#!java

MyString[] sarray = new MyString[] { new MyString("a"), new MyString("aa"), new MyString("aaaa"), new MyString("aaa") };
MyString m = (MyString)max(sarray);

// 実行時例外が起こる例
MyComparable[] oarray = new MyComparable { new MyString("a"), new MyInteger(0) };
Object o max(oarray);
```

内部的にMyStringオブジェクトとMyIntegerオブジェクトを比較しようとするので、実行時例外が起きる。困ったことにこのコードはコンパイルが普通に通る。無理なキャストすら無い。

インタフェースだけに依存した、コードの表面上はきれいなコードにもかかわらず、実行時に致命的なエラーが起きてしまう。

上記から、インタフェースに対してプログラミングする技法だけでは何かを満たせていないことがわかる。

足りないのは、インタフェースに依存した実装ではなく、型ごとに独立したコードの実体である。つまり、MyStringクラスにはMyStringオブジェクト群から最大値を得るmaxメソッドがあるべきで、MyIntegerクラスにはMyIntegerオブジェクト群から最大値を得るmaxメソッドがあるべき。同じ事は比較メソッドにも言える。

そして、maxメソッドの入力と出力関係を考えてみると、maxメソッドの入力はTの配列で、出力はTオブジェクト。入力と出力を、値としての側面だけではなく型という側面で見てみると、maxメソッドの出力の型Tは入力から直接に決まっている。このパターンのメソッドこそがジェネリック型を使う候補となる。

入力と出力は、引数と返り値だけでなく、もっと広い意味で捉える。例えばJavaの標準のsortメソッド（java.util.Collectionsのクラスメソッド）は、引数で与えたコレクションの要素をソートするが、返り値は特に無い。入力はソート前のコレクション、出力はソート後のコレクションと捉える。

入力と出力は引数にすら現れないこともある。コレクションに要素を追加するaddメソッドは、要素追加前のコレクションと追加したい要素が入力で、要素追加後のコレクションが出力。この場合の入力と出力はオブジェクトのフィールド（状態）も含めて捉えていることがわかる。

ここまでの例で、入力のT情報がそのまま出力に引き継がれていることがわかる。このようなパターンのメソッドがジェネリックメソッドの候補であり、このようなメソッドを持つクラスやインタフェースがジェネリック型の候補。

## ジェネリック型とパラメータ化

これまでトップダウン的にジェネリック型を使える条件を見てきたが、現実的にはボトムアップ的にジェネリック型やジェネリックメソッドを作る事のほうが多いはず。

具体的な利用方法の例として、int型変数のリストから最大値を返すmax_iメソッドがあったとする。double型数値のリストから最大値を返すmax_dメソッドを次に作ったとする。その後、short型数値のリストから最大値を探すmax_sメソッドを作ったとする。

これらのメソッドは、型を除けば、それ以外は同じコードであることに気づくことがジェネリック型への第1歩。共通部分と異なる部分を分けて、異なる部分をパラメータ化して外部に追い出す技法は、プログラミングの大原則。今、京津する部分はコードで、パラメータ化して外部に追い出せるのは型情報。

こうして型情報をパラメータ化して外部化したものがジェネリック型。ジェネリック型は特殊な技法に見えるが、根源となる発想そのものは、変わる部分や異なる部分をパラメータ化して外に追い出すという、プログラミングの大原則に則った正攻法な技法であることがわかる。
