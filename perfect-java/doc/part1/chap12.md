# 列挙型と定数と不変型

## リテラル表記

|名称|型|具体例|補足|
|----|--|------|----|
|ブール値リテラル|boolean|true,false|ブール値リテラルはtrueとfalseのみ|
|文字リテラル|char|'a'||
|文字列リテラル|Stringクラス|"abc"||
|整数リテラル|int or long|123 or 123L|lもしくはLを末尾につけるとlong型。直接short型やbyte型の変数に代入する時はshort型やbyte型|
|浮動小数点リテラル|double or float|1.23 or 1.23f|fもしくはFを末尾につけるとfloat型|
|クラスリテラル|Classクラス|String.class||
|参照リテラル|なし|null|参照リテラルはnullのみ||

## 定数表記

特に数値リテラルはマジックナンバーは排除するよう、定数化するようにして、意味を持たせるとともにタイプミスを未然に防ぐ。また、定数は final 修飾子を付けて再代入を禁止する。

基本型変数の場合は再代入不可が値が変わらないことが等価だが、参照型変数の場合は再代入不可であることと、参照先オブジェクトが変更不可であることは別の話なので、不変オブジェクトでない限りは変更できてしまうので注意する。Stringオブジェクトは不変オブジェクトなので、final修飾子をつけると定数扱いできる。

## 定数定義の使い方

* メソッド内で定数定義をする場合、変数宣言に final 修飾子を付けて、変数名を大文字にする
* クラス内の定数定義は、static final にする
    * インスタンスごとに定数のコピーを持つことは無駄なので、 static でクラスフィールドにする
* クラスにまたがった定数定義は、アクセス修飾子を public にして外部から参照する
* Java5以降は、staticインポートを使って簡潔に書く
    * 定数インタフェースを定義する方法も以前はあったが、今は使わない

* 定数はすべてクラスフィールドかつfinalにする
* コンストラクタをprivateにして、インスタンス化させる意思のないことを示す
* クラスにfinal修飾子を付けて、継承させる意思のないことを示す

* 注意
    * インポートではインポート先の値を取り込むようなことはせず、名前解決のためだけに使う。
    * 基本型変数と文字列の定数定義だけは、例外的に値をとり込むので、定数の値を変更した後インポートした側の再コンパイルが必要

## enum型

Java5で列挙型（enum型）が導入された。値そのものに意味がなく、区別するための定数定義はenum型で書くことができる。例えば、DBの項目によくある「区分」など。

```
#!java

static final int MAN = 0;
static final int WOMAN = 1;
static final int OTHER = 2;

// enumで書き換え
enum Gender {
	MAN,
	WOMAN,
	OTHER,
}
```

enumで定義した型は java.lang.Enum クラスの継承型になっている。つまり、enumは内部的にはクラス。通常のクラスと異なり、new演算子でオブジェクトを生成することはできない。その代わり、enum宣言内に列挙したenum値が暗黙にオブジェクトを生成する。

Enum値の列挙は、valuesメソッドを使う。

```
#!java

for (Gender gender : Gender.values()) {
	System.out.println(gender);
}
```

valuesメソッドはenum値を記述した順序を保証する。ただし、enum血の順序に依存するコードは変更に弱いため推奨しない。

enum値に意味のある値を持たせることもできる。次のようにフィールドとコンストラクタを定義する。

```
#!java

enum Gender {
	MAN("man"),
	WOMAN("woman"),
	OTHER("other");

	private String val;
	private Gender(String val) {
		this.val = val;
	}
}
```

フィールドやメソッドを独自に定義することで、クラスに近い利用も可能。次のコードは、表示用の文字列とデータベース保存用の値定義を持つenum型の例。

```
#!java

enum Gender {
	MAN("man", 0),
	WOMAN("woman", 1),
	OTHER("other", 2);

	private String name4disp;
	private int val4db;
	private Genger(String name4disp, int val4db) {
		this.name4disp = name4disp;
		this.val4db = val4db;
	}
	public String toString() {
		return this.name4disp;
	}
	public int toDatabaseValue() {
		return this.val4db;
	}
}

// 利用側
System.out.println(Genger.MAN); // => 暗黙にtoString()メソッド呼び出し
System.out.println(Gender.MAN.toDatabaseValue()); // => "0"
```

## enum値とswitch文

enum値をswitch-case文の式に書くことができる。enum値以外を書くとコンパイルエラーになるので、単なる定数定義よりも変更に強いコードになる。

## 不変型

不変型にする実装方法は、

* フィールドをprivateにする
* 変更メソッドを提供しない
* フィールドはコンストラクタで初期化して、以降は変更しない
* フィールドをfinalにする（初期化した値を別のスレッドが読めるように保証するため）
* finalクラスにする（拡張継承したクラスが不変性を破ることを防ぐため）

外部からフィールドを変更されない限り、広義の不変オブジェクトと言えるが、狭義の不変オブジェクトは、最初にオブジェクトが生成された時点から一切状態が変化しないオブジェクト。これを保証するには、コンストラクタですべての状態を完全に初期化すべき。
マルチスレッドを考慮してフィールドをfinalにする。たとえ別のスレッドから参照されないオブジェクト出会っても、finalにすることで失うものはない。

## 不変型の注意

一見不変クラスのようで、不変性が破れる例がある。

[コード例](../../../../../commits/35df5e547c6adc065ce514de1ad2fb250b213393)

これらはStringBuilderオブジェクトが不変でないために起きる事故。これを防ぐには次の2つの指針を守る必要がある。

* 引数で受け取る不変でないオブジェクトをフィールド変数で参照する場合、コピーしたオブジェクトを参照する（防御的コピー）
* 不変ではない内部フィールドを返す場合は、不変型（StringBuilderではなくStringなど）に変換して返すか、もしくはコピーして返す

例に上げたような、StringBuilderの事故はあまり起きない。文字列であれば無意識にStringクラスを使うことが多いから。しかし、コレクションオブジェクトをフィールドに持っていると、コレクションオブジェクトを返すメソッドを定義してしまうことがある。

不変クラスにするためには、変更不能なコレクションを返す必要がある。次のようにCollectionクラスのクラスメソッドで簡単に実現できる。こうして返したコレクションを変更しようとすると、UnsupportedOperationException例外が発生する。

内部フィールドの配列を返すメソッドがある場合、配列は変更不能にできない。メソッドの返り値をList型に変更して、Collections.unmodifiableList(Arrays.asList(array))で変更不能なListに変換して返す。

List<String>と異なり、List<StringBuilder>は変更不能なListに変換しても、要素を不変にできないことに注意する。
Collections#unmodifiableListは、要素のコピーをするわけではないため、パフォーマンス的にも問題ない。
