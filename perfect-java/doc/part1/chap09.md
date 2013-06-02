# Javaプログラムの実行と制御構造

## Javaプログラムの実行

mainメソッドは、javaコマンドを起動した時にJVMが最初に制御を移すという以上の意味は無い。

1. Javaプログラムを実子するには、引数にクラス名を与えてjavaコマンドを実行する
2. JavaコマンドはJVMを起動する
3. JVMは、クラス名からクラスファイルを探す
4. JVMは見つけたクラスファイルをメモリに読み込む（クラスのロード）
5. JVMはクラスのバイトコードを解釈して実行する

このため、mainメソッドはstaticかつpublicである必要がある。（JVMから見える必要があり、かつヒープではなくパーマネント領域にロードされるため？）

## メソッドのコールチェイン

[コード例](../../../../../commits/4ad67128699195d81ccbe49b2ec835358ca242a1)

## nullチェック

```
#!java

void dumpList(List<String> list) {
	for (String s : list) {
		System.out.println(s);
	}
}
```

上記のメソッドの引数にnullが渡ると、NullPointerExceptionが発生する。NullPointerExceptionが起きるコードはバグ。このバグを修正する方法の一つは、次のように呼び出し元でnullとの比較をする条件分岐を行う方法。

```
#!java

void caller(List<String> list) {
	if (list != null) {
		for (String s : list) {
			System.out.println(s);
		}
	}
}
```

このように、オブジェクト（厳密にはオブジェクト参照）とnullとの比較はしばしば行う。（nullチェック）
すべての呼び出し元にnullチェックを入れるのは現実的ではないので、上記の例の場合はdumpListメソッド内でnullチェックを行うようにすれば、一箇所で済む。また、新規に書かれるコードでnullチェックを忘れる可能性も低くなる。

nullチェックをどちらで行うべきかの間に明確な答えはなく、1つの理想は、null参照の変数を避けること。（nullオブジェクトパターン）

## 定数条件式

コメントアウトでコードのある部分を一時的に無効にしたい局面があるが、代わりに以下のようなif-else文を書くこともある。

```
#!java

if (true) {
	// something
}
if (false) {
	// something
}
```

## 条件演算子

    条件式 ? 式1 : 式2

次のコードはコンパイルエラー。

```
#!java

flag ? System.out.println("1") : System.out.println("0");
```

理由は、条件演算子を使った式は式文ではないため。結果を代入するような代入式であれば、メソッド呼び出しを式1及び式2に書いてもエラーにはならない。
次の例は有効なコード。

```
#!java

int len = flag ? (new StringBuilder("abc")).length() : (new StringBuilder("defght")).length();
```

式1および式2に書けるのは式で、文ではない。そのため、次のコードもエラー。

```
#!java

int len = flag ? { (new StringBuilder("abc")).length(); } : (new StringBuilder("defght")).length();
```

if-else文よりも制約が大きいと言えるが、式をつなげることで条件演算子でもネストした式を書ける。

```
#!java

int i = flag1 ? (flag2 ? 0 : 1) : 2;
```

if文の条件式に条件演算子を使うことも、評価値の型が真偽値であれば、使うことができる。

```
#!java

if (flag ? s.contains("abc") : s.contains("xyz")) {
	System.out.println("true");
}
if (s.contains(flag ? "abc" : "xyz")) {
	System.out.println("true");
}
```

条件演算子は、多用しすぎても可読性を落とす可能性があるため、使い方の指針としては、変数の初期化に利用できる場合のみ条件演算子を使用する、というのがわかりやすい。

```
#!java

int len = flag ? 1 : 0;
```

## switch-case文

使い方の指針としては、ある局面でif-else文よりも可読性が高くなる。その局面としては、

* ==演算による比較式が並ぶ場合
* breakを使わず、caseラベルからそのまま下に流れることを生かしたコード（if-else文では、||で比較式をつなげる必要がある）
* enumの比較を使うコード（switch文の式にenum型を使うと、caseラベルの定数式のenum型名を省略できる）

```
#!java

enum DayOfWeek {
	SUNDAY,
	MONDAY,
	TUESDAY,
	WEDNESDAY,
	THURSDAY,
	FRIDAY,
	SATURDAY,
};
public static void doit_by_day(DayOfWeek dow) {
	switch (dow) {
		case SUNDAY:
		case SATURDAY:
			System.out.println("day off");
			break;
		case MONDAY:
		case TUESDAY:
		case WEDNESDAY:
		case THURSDAY:
		case FRIDAY:
			System.out.println("workday");
			break;
		default:
			assert(false);	// not reached
			// 曜日なのでここに到達しないことは自明だが、書き忘れのミスを防ぐために防衛的に拾う仕組み
	}
}
```

## while文

使い所は、条件式でオブジェクトに対し特定のメソッドを繰り返し呼び出す場合。例えば、ストリームに対するreadメソッドや、正規表現のMatcherに対するfindメソッドなど。

## do-while文

使い所は、事実上以下の2つの場合。

* 文を少なくとも1回実行しないと、条件式の評価に意味が出ない場合
* 文を少なくとも1回っ実行することを保証したい場合

例えば以下の様な場合。

```
#!java

static void printNumberFromRight(int n) {
	do {
		System.out.println(n % 10);
		n /= 10;
	} while (n > 0);
}
```

## for文

* ループ変数がそのfor文だけに使われる場合、ループ変数の宣言と初期化を初期化式に書くべき。（ループ変数のスコープが小さい）
* for文の条件式は省力可能（省略した場合、常に真と評価される式があるとみなされる）
* 更新式には任意の式を書くことができ、一般的にはループ変数を更新する式を書く。更新式は条件式の評価の前に評価される。continue文をforループ内に書いた場合も更新式を評価する。
* 更新式の記述は省略可能
* 10回のループは明確な意図がなければ i = 1; i <=10; i++ よりも、 i = 0; i < 10; i++ にすべき。
* 初期化式と更新式では、複数の式をカンマで区切って並べることもできる。その場合、型は同じものである必要がある。
    * 違う型を変数として使いたければ、for文の外で宣言する必要がある
* 初期化式と更新式には任意の式を書くことができる。極論だが、初期化式と更新式にprint文を書くことすら可能。
* 無限ループは for (;;) {}

## ジャンプ

ループの中からループを抜けたい場合は、ループ変数をループ内で書き換えたりするのではなく、breakやcontinueを使う。

```
#!java

// これは絶対やらない
for (int i = 0; i < 10; i++) {
	if (ループを抜ける条件) {
		i = 10;
	}
}

for (int i = 0; i < 10; i++) {
	if (ループを抜ける条件) {
		break;
		// break文は、while&dowhileの条件式の評価、for文の更新式の評価はしない
	}
}
```

```
#!java

// ループ変数が偶数の時だけ処理をするforループ
// continueを使うことでインデントを1段節約できた。インデントの節約は可読性の向上につながる
for (int i = 0; i < 10; i++) {
	if (i % 2 != 0) {
		continue;
	}
	// something
}
```

## ラベルジャンプ

入れ子になったループでbreakで抜けると、抜けるのは内側のループだけ。入れ子になったループを同時に抜けるには、フラグ変数を使う方法があるが、これは推奨されない。変更に弱いため。

```
#!java

// 推奨しない
// 内部ループを抜けた後、外部ループ内に処理を加える変更があった場合、
// flag_loop変数を見て処理を飛ばすif文が必要
boolean frag_loop = true;
while (flag_loop) {
	System.out.println("outer loop");
	while (条件式) {
		System.out.println("inner loop");
		if (ループを抜ける条件) {
			flag_loop = false;
			break;
		}
	}
}

// 入れ子のループを抜ける
outer_loop:
while (true) {
	System.out.println("outer loop");
	while (true) {
		System.out.println("inner loop");
		break outer_loop;
	}
}
```

continue文もラベルを使うことができる。
