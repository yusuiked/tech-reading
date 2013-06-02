# リフレクション

## リフレクションとは

リフレクションとは、実行中に型情報を取得でき、型そのものを操作対象に出来る仕組みのこと。この文脈での型は、

* クラス（enum型含む）
* インタフェース
* 配列型
* プリミティブ型
* void
* パッケージ
* アノテーション

が対象になる。

型をオブジェクトのように扱うことで次のようなことができる。

* クラスのロード
* クラスの型階層の列挙
* ひな形（クラスや配列）からオブジェクト生成
* 型（クラス、インタフェース）のメンバ（フィールド定義やメソッド定義など）の型や名前の取得
* レシーバオブジェクトを指定したフィールド値参照、フィールド値変更、メソッド呼び出し

## リフレクションを実現するには

リフレクションを実現する仕掛けは、Class クラス。名前が紛らわしいが、 java.lang.Class という名前のクラス。Class クラスは型情報を保持する。具体的には、特定の型（クラス、インタフェース、配列）についてフィールドの一覧やメソッド一覧を問い合わせることができる。

## Class クラスの役割

Class クラスは、他のクラス同様、型定義とひな形としての役割を持つ。Class クラスをひな形として Class オブジェクトを生成できる。ただし、 new 式で Class オブジェクトは生成できない。 Class オブジェクトはクラスをロードすると自動的に生成される。多くの Class オブジェクトの生成は暗黙に行われる。

ソースコードの中で使われる型（クラスやインタフェース）それぞれに、対応する Class オブジェクトが存在する。型それぞれに個別に Class オブジェクトが存在することの意味は、Stringクラスに対応する Class オブジェクト、StringBuilder クラスに対応する Class オブジェクト、Integer クラスに対応する Class オブジェクトのように、Class オブジェクトが存在することとを意味する。

String クラスをひな形として複数の String オブジェクトが存在するように、 Class クラスをひな形に複数の Class オブジェクトが存在する。Class<String> オブジェクトや Class<StringBuilder> オブジェクトなど。Class クラスはジェネリクス。いくつかの例外を除いて、それぞれの型に対応する Class オブジェクトは1つのみ。Class クラスにも、対応する Class<Class> オブジェクトが存在する。

Java プログラムがあるクラス Foo を使うと、そのプロセスは実行中に自動で Class<Foo> オブジェクトを生成する。Class<Foo> オブジェクトは、クラス Foo のフィールド一覧やメソッド一覧を取得する手段を提供する。

型ごとに Class オブジェクトが1つだけ存在することの例外はクラスローダに関係する。クラスローダとは名前の通りクラスをロードする役目を担うオブジェクトで、Java プロセスは複数のクラスローダを持つことが可能。

Class オブジェクトの参照を取得する手段はいくつか存在する。 Class オブジェクトの参照を得る時、内部的にはクラスローダが保持する Class オブジェクトから検索する。存在すればその Class オブジェクトの参照を返す。存在しない場合、クラスローダが対象クラスをロードして Class オブジェクトを生成する。

クラスローダを明示的に複数保つ方法は後述する。当面、Java プログラムのプロセスにクラスローダは1つと考えておく。（実は起動時点でブートストラップクラスローダとシステムクラスロードの2つのクラスローダが存在するが、通常、気にする必要はない） Class オブジェクトは型ごとに1つずつ存在すると考える。

## Class オブジェクト

Class オブジェクトの参照を取得する方法をいかに示す。

* クラスリテラル
    * 型名に .class を後置する記法。1つのリテラル値として評価され、評価値の型が Class クラス
* Object クラスの getClass メソッド
    * Object クラスのインスタンスメソッド。全てのオブジェクトに対して呼ぶことができ、final修飾子のついたメソッドなのでオーバーライドできない
* Class クラスの forName クラスメソッド
    * 型名を文字列で渡して Class オブジェクトを返す。forName メソッドにクラスローダオブジェクト（java.lang.ClassLoader オブジェクト）を渡すメソッドも存在する。クラスローダを渡さない場合、現在のクラスローダを使う
* その他（Class クラスや ClassLoader クラスのメソッド経由）

[コード例](../../../../../commits/923afb7ca8a48446af5a8ba1c5f1d77916b3b632)

Class 型の変数名には伝統的に clazz をよく使う。class は予約語のため変数名に使えないため。

上記で述べたように、Class クラスはジェネリクス型なので、正しくは

[コード例](../../../../../commits/3a4672e9ce3fb388ec99614d049c17216b78ec0a)

の様に書く。

基本型にも対応する Class オブジェクトが存在する。

[コード例](../../../../../commits/6f8bebf9be54857c6ee89acd1300781d4d60de44)

int 型に対応するオブジェクトの型は概念上は Class<int> だが、ジェネリック型の制約から Class<Integer> になる。

Revision r117 からもわかるように、int に対応する Class<Integer> オブジェクトと、Integer クラスに対応する Class<Integer> オブジェクトは異なるオブジェクト。int に対応する Class<Integer> オブジェクトを Class クラスの forName メソッドで取得する手段は存在しない。

配列型に対応する Class オブジェクトの参照を取得する例は以下。

[コード例](../../../../../commits/aefae742f5a7dd868ba0d465b700ccd228063a66)

配列の要素ごとに異なる Class オブジェクトになる。 forName メソッドに渡す文字列は特殊な規則がある。 forName に渡せる文字列は Class オブジェクトの getName メソッドを呼ぶことで得られる。

int 型配列であれば、int[].class.getName() が "[I" の文字列を返す。この文字列を forName メソッドに渡すと int型配列に対応する Class オブジェクトの参照が得られる。

ジェネリック型はイレイジャにより、要素型が消えるので要素型が異なっても同じ Class オブジェクトになる。 ArrayList を使う例を以下に示す。

[コード例](../../../../../commits/af6117b3871f2a2e0567460065d385843636505b)

クラスリテラルを使う場合、そもそも型名がソースコードに記述されている。 Object クラスの getClass メソッドでは、対象オブジェクトを生成する時点で具象クラスのことを知っている。

この2者と比較すると、Class クラスの forName メソッドは異色。文字列なので、実行時にプロセスの外部から与えることができる。例えば、クラス名をファイルから読んだりユーザに入力させたりできる。これによりソースコード中に記述がないクラスを実行時にロードできる。具体例は後述。

## 型情報の取得

Class オブジェクトから型（クラス、インタフェース、配列型）の情報を取得できる。主なメソッドを分類する。
Class オブジェクトの対象型の種別を判定する代表的なメソッドを示す。

```
#!java

// インタフェース型であれば真を返す
boolean isInterface()

// 配列型であれば真を返す
boolean isArray()

// 基本型であれば真を返す
boolean isPrimitive()

// enum型であれば真を返す
boolean isEnum()
```

クラスやインタフェースのメンバ（構成要素）を取得する代表的なメソッドを示す。Method クラス、 Field クラス、 Constructor クラスの使い方は後述。

```
#!java

// 全ての public フィールド情報を返す（継承したフィールドも含む）
Field[] getFields() throws SecurityException

// 指定した名前の public フィールドを返す（継承したフィールドも含む）
Field[] getField(String name) throws NoSuchFieldException, SecurityException

// 全てのフィールド情報を返す（継承したフィールドを含まない）
Field[] getDeclaredFields() throws SecurityException

// 指定した名前のフィールドを返す（継承したフィールドも含まない）
Field[] getDeclaredField(String name)

// 全ての public メソッド情報を返す（継承したメソッドも含む）
Method[] getMethods() throws SecurityException

// 指定したシグネチャの public メソッド情報を返す（継承したメソッドも含む）
Method getMethod(String name, Class<?>… parameterTypes) throws NoSuchMethodException, SecurityException

// 全てのメソッド情報を返す（継承したメソッドを含まない）
Method[] getDeclaredMethods() throws SecurityException

// 指定したシグネチャのメソッド情報を返す（継承したメソッドを含まない）
Method getDeclaredMethod(String name, Class<?>… parameterTypes) throws NoSuchMethodException, SecurityException

// すべての public コンストラクタ情報を返す
Constructor<?>[] getConstructors() throws SecurityException

// 指定したシグネチャの public コンストラクタを返す
Constructor<T> getConstructor(Class<?>… parameterTypes) throws NoSuchMethodException, SecurityException

// すべてのコンストラクタ情報を返す
Constructor<?>[] getDeclaredConstructors() throws SecurityException

// 指定したシグネチャのコンストラクタを返す
Constructor<T> getDeclaredConstructor(Class<?>… parameterTypes) throws NoSuchMethodException, SecurityException

// ネストした public クラスおよび public インタフェースを返す（継承したクラス、インタフェースも含む）
Class<?>[] getClasses()

// ネストしたクラスおよびインタフェースを返す（継承したクラス、インタフェースを含まない）
Class<?>[] getDeclaredClasses() throws SecurityException
```

インスタンスメンバ（インスタンスフィールドとインスタンスメソッド）とクラスメンバ（クラスフィールドとクラスメソッド）の違いは、Method クラスや Field クラスの getModifiers メソッドの返り値で判定できる。getModifiers メソッドの返り値は java.lang.reflect.Modifier の定数のビット和となる。getModifiers メソッドの返り値に Modifier.STATIC のビットが立っていれば、そのメンバはクラスメンバ。

型階層に関係する情報を取得するメソッドを示す。Object クラス、インタフェース型、基本型に対応する Class オブジェクトは getSuperClass メソッドが null を返す。配列型に対応する Class オブジェクトは getSuperClass が Class<Object> オブジェクトを返す。

```
#!java

// 拡張継承の継承元クラスの Class オブジェクトを返す
Class<? super T> getSuperClass()

// 実装しているインタフェースの Class オブジェクトを返す
Class<?>[] getInterfaces()
```

型階層を表示するコード例を以下に示す。

[コード例](../../../../../commmits/b8ac41c67a2a8575f64f3b4a1fb9e23912902a22)

## オブジェクト生成

リフレクションによるオブジェクト生成には、次の2つの手段がある。

* Class クラスの newInstance メソッド呼び出し
    * 引数なしのコンストラクタを呼び出す
    * 対象クラスが引数なしのコンストラクタを持たない場合、InstantiationException 例外が発生する
* Constructor クラスの newInstance メソッド呼び出し
* 配列オブジェクトの生成は、java.lang.reflect.Array クラスの newInstance クラスメソッドを使う

Class クラスの forName メソッドと組み合わせると、ソースコードに全く記述がない具象クラスのオブジェクトを生成できる。以下は、Constructor クラスの newInstance メソッドを使って、StringBuilder オブジェクトを生成する例。

[コード例](../../../../../commits/cf31ea7faf2ab5721aa729c48714022bc9f07f85)

## メソッド呼び出し

リフレクションによりメソッド呼び出しを行うことができる。java.lang.reflect.Method クラスを使う。

Method オブジェクトに対して invoke メソッドを呼ぶことでメソッド呼び出しができる。
クラスメソッドの場合は、invoke の第1引数は無視される。通常は null を渡す。

以下は、invoke メソッドによるメソッド呼び出しの例。ソースコード中に識別子としての StringBuilder が存在しないことに注意する。StringBuilder に関する情報はすべて文字列なので、実行中にユーザに入力させることもできる。

[コード例](../../../../../commits/a503329dc0c6ffb86b6d24abeffe426b8aa892ac)

## フィールド操作

リフレクションによりフィールドを参照したりフィールドに値を設定できる。java.lang.reflect.Field クラスを使う。
Field クラスの代表的なメソッドの定義は以下。

```
#!java

// フィールドの型を返す
Class<?> getType()

// フィールドの値を返す。第1引数はレシーバオブジェクト
Object get(Object obj) throws IllegalArgumentException, IllegalAccessException

// フィールドに値を設定する。第1引数はレシーバオブジェクト、第2引数は設定値
void set(Object obj, Object value) throws IllegalArgumentException, IllegalAccessException
```

Method クラス同様、クラスフィールドを操作する場合、レシーバオブジェクトは無視される。
以下は、Field オブジェクトを使ってフィールドを書き換える例。

[コード例](../../../../../commits/bf1198ac7699abdeba0116c6acc6a24207897bbc)

## Java Beans

Java Beans は元々、GUIの部品（コンポーネント）をツールの支援のもとに容易に作成できることを目的とした技術。GUI部品の作成の手間を軽減する技術は Java 以前に先行技術が多数ある。静的な型付け言語では、オブジェクト指向の継承を活用する技術が主流で、動的な型付け言語の中には実行中に型（クラス）を生成する技術もある。

Java は静的型付け言語だが、Beans はリフレクションを前提に動的な型付け言語に近い方向性の技術。Beans は特定のクラスの継承を前提としない。Beans はメソッド名の命名規約を前提にする。

Java の利用用途がGUIプログラムからサーバ用途に広がる中で、Beans は当初の想定（GUIの部品）より広く使われるようになった。当初の Beans を特徴付ける技術要素は以下のようだった。

* リフレクションによる実行時オブジェクト生成を想定した設計
* カスタマイズ機能を想定した設計
* イベント処理
* プロパティ
* 永続化機能

すべての Beans がこの特徴を備えるわけではないが、Beans が最低限持つ特徴は、リフレクションによる実行時オブジェクト生成を想定した設計。
あるクラスが Beans クラスか否かを決定づける要因はクラス自身にはなく、Bean クラスをインスタンス化して、Bean オブジェクトを操作するツールやフレームワークの要請で決まる。一般にこのようなツールやフレームワークを「**Beanコンテナ**」と呼ぶ。

Bean コンテナが Bean オブジェクトの生成を Class クラスの newInstance メソッド（あるいは java.beans.Beans.instantiate）で行う場合、 Bean クラスは引数なしコンストラクタを持つ必要がある。

Beans ではフィールドの代わりにプロパティを持つ。
Beans のプロパティアクセスには次の規則が決まっている。

* プロパティ名が foo の時、プロパティ値を取得するメソッド名は getFoo
* プロパティ名が foo の時、プロパティ値を設定するメソッド名は setFoo

現実の Beans のほとんどはプロパティと1対1に対応するフィールドを持つ（通常、フィールドのアクセス制御は private にする）。しかし、 Beans のプロパティ foo にとっての本質は、 foo という名前のフィールドが存在することではなく、setFoo や getFoo のメソッドが存在すること。

```
#!java

// Bean クラスの例
class MyBean {
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
```

## DI（Dependency Injection）

次のクラスを考えてみる。

```
#!java

class StringList {
	private List<String> list;

	public StringList(List<String> list) {
		this.list = list;
	}

	public void append(String s) {
		list.add(s);
	}
	public void dump() {
		for (String s : list) {
			System.out.println(s);
		}
	}
}
```

この StringList クラスを使うにはどこかに次のようなコードが必要。

```
#!java

List<String> list = new ArrayList<String>();
StringList sl = new StringList(list);
```

オブジェクト指向は、機能や役割をクラスに分割する。クラスの分割を進めた先に複雑さが残るのはクラス間の関係性。結果として、オブジェクト指向で作成したプログラムには、上記のようなオブジェクト生成の処理が相対的に増えていく。必要な処理を行うために、オブジェクトの生成が不可欠だから。オブジェクト生成には依存するオブジェクトを事前に生成する必要がある。（上記の例では StringList オブジェクトの前に ArrayList オブジェクトが必要なように）

このように依存関係のあるオブジェクト群の生成処理を総称して、オブジェクト構築処理と呼ぶ。
オブジェクト構築処理は面倒で複雑な作業。このため、オブジェクト構築にまつわる技法が生まれている。デザインパターンの世界で、ファクトリパターンやビルダーパターンなどが有名。

## DIの役割

面倒で複雑な作業は、独立した役割として分離してみる。オブジェクト構築処理を分離する技法の1つがDI（Dependency Injection：依存性注入）。DIエンジンは、オブジェクト構築処理を役割として引き受ける。オブジェクト構築は結果としてオブジェクト間の依存性の解決を行う。このことを外部から依存性を注入（解決）すると称する。

従来のファクトリパターンが個々のクラスごとにオブジェクト生成を隠蔽したのに対し、DIエンジンは依存関係のあるオブジェクト群の構築を一括して隠蔽する。いわば、巨大な汎用ファクトリエンジンになる。オブジェクト構築を引き受けたDIエンジンは同時にオブジェクトのライフサイクル管理を担うこともある。

上記の StringList クラスの依存性を解決する簡易なDIエンジンは以下。

* [コード例](../../../../../commits/b7482b5b3bb53334eaade6cfb31fc1fce34623ea)
* [コード例](../../../../../commits/3e50e26dc186ab6018e70d85f1ec943914c5fde2)

MyDI には"StringList"や"java.util.List"を文字列としてハードコードしている。本物のDIエンジンは特定のクラスのために書かれるものではない。構築すべきオブジェクトの情報を外部ファイルやアノテーションで指定したり、あるいは命名規約で確定したりする。

## クラスローダとホットデプロイ

リフレクションを使うと、実行時に指定したクラスをロードすることができる。1度ロードしたクラスはクラスローダが保持し続ける。このため、繰り返し Class クラスの forName メソッドを呼んでもクラスを再ロードはしない。しかし時には、クラスファイルを書き換え再ロードしたい時がある。たとえば、Webアプリの世界でホットデプロイとして知られる機能などに必要。

Javaのクラスをロードするのはクラスローダ（java.lang.ClassLoader）の役割。普通にJavaプログラムを起動した場合、クラスローダオブジェクトは自動的に作成され、開発者がその存在を気にする必要はない。

独自にクラスローダを作ったり、プログラム起動中に新しいクラスローダオブジェクトを生成することが可能。明示的にクラスローダオブジェクトを生成した場合、生成したクラスローダにクラスをロードさせることができる。クラスのロードはクラスローダごとに行える。クラスローダオブジェクトを明示的に生成することで、クラスの再ロードを実現することができる。

以下に、クラスローダの具体例のコードを示す。

[コード例](../../../../../commits/107b2ebcadf17e7422a4c0e2620e5151d390dd3f)

javax.tools.JavaCompiler を使って、コードの中からソースファイルのコンパイルを行い、クラスローダオブジェクトを作成。
作成したクラスローダを指定してクラスを再ロードする。

コンソールが入力待ちの時に、My.javaを書き換えて再度実行すると、クラスが再ロードされて実行結果が変わる様を確認できるはず。
