# 例外処理

## エラー処理の方法

伝統的なエラー処理の方法としては、以下の2つがある。どちらも今でも普通に利用する手法。

* メソッドの返り値
    * 例えば String#indexOf の返り値は、見つからない時に-1を返す
* エラーハンドラ（エラーコールバック）処理

エラーハンドラ処理の例は、以下のようになる。

```
#!java

interface ErrorHandler {
	void onError(String error);
}

class ErrorHandlerImpl implements ErrorHandler {
	public void onError(String error) {
		System.out.println("error:" + error);
	}
}

// 対象オブジェクト
My my = new My();
my.registerErrorHandler(new ErrorHandlerImpl());

class My {
	private ErrorHandler errorHandler;
	public void registerErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}
	public void doit() {
		// something
		if (hasError()) {
			if (errorHandler != null) {
				errorHandler.onError("something wrong happend.");
			}
		}
	}
}
```

エラー発生とエラー処理が正常なメソッド呼び出しの外部に括りだされている。メソッドを呼ぶ側の視点では、メソッド内でのエラー発生の詳細が隠蔽されている。メソッド呼び出しの先はブラックボックス化され、エラー発生が単なる取り決めの1つになっている。Javaの例外の考え方は、この考え方の延長線上にある。

## 例外の利点

* 返り値で対処する手法に対する利点
    * エラー返り値は無視できるが、例外は無視できないようにできる
    * メソッドのコールチェインに依存するエラー返り値と違い、例外はコールチェインから独立して定義できる
    * エラー返り値を使う場合に比べ、異常系の処理を分離できる
    * エラー返り値を使う場合に比べ、実行効率が良いこともある
* エラーハンドラで対処する手法に対する利点
    * 例外は言語機能なので、構文サポートが得られる（エラーハンドラの登録処理が不要。記述も容易。）
    * 例外は宣言的に記述できる（構文サポートがあるため）
    * 例外をメソッドの契約として宣言できる

## 例外の具体例

* printStackTraceメソッドは、catch節の中に何を書いていいか不明な場合の定石。
* 検査例外はメソッドと呼び出し側との間の取り決め、実行時例外はいわばJava実行環境と開発者の間の取り決め。

## 例外の補足

* catch節は複数記述できるが、最初の節から順番に評価するため、2番めのcatch節が絶対に使われることがない順序（先により上位の基底例外クラスを記述してしまう）だと、コンパイルエラーになる。

## finally節

* finally節は、try節が正常に終了しようと、例外が発生してtry節の実行を中断しようと、必ず実行される。メソッド呼び出しのコールチェインの先で例外が発生すると、途中のメソッドを中断してcatch節まで飛んでくる。finally節だけはコールチェインを中断して戻ってくる途中で必ず実行する。
* finally節を実行せずにtryブロックを抜ける手段はなく、continue文でtryブロックを抜けた場合でも、finally節は実行される。break文やreturn文でtryブロックを抜けた場合も同様。
* tryブロックがネストされている場合、両方のtryブロックで実行される

## return文とfinally節

return文で抜けてもfinally節を実行するが、finally節の中に別のreturn文があると、その返り値が有効になる。finally節の中にreturn文がなければ、try節の中のreturn文の返り値が有効。

## リソース開放処理のイディオム

リソースの開放処理では、たとえ例外が発生しなくても、try-finally構文を使うことを推奨する。finally節の中に書くことによって確実な後処理の必要性が明示できるため。

```
#!java

try {
	obj = open();
} finally {
	if (obj != null) {
		obj.close();
	}
}
```

次のコードはコンパイルエラーになる。内側のtryブロックのcatch節で補足した例外が外側のcatch節に伝播しないため、外の側のcatch節を決して実行しないため。コンパイルを通すには、内側のcatch節で再度外側のcatch節で例外を補足するようにすればいい。catch節の中から例外を投げるコードは一般的。

```
#!java

import java.io.IOException;

public class My {

	public static void main(String[] args) {
		try {
			try {
				throw new IOException();
			} catch (IOException e) {
				System.out.println("IOException catched.");
				// throw e; // ここから例外をスローすればコンパイルが通るようになる
			} finally {
				System.out.println("finally1");
			}
		} catch (IOException e) {
			System.out.println("IOException catched.");
		} finally {
			System.out.println("finally2");
		}
	}

}
```

## 例外の送出

* throw構文による明示的な例外送出
* 演算による例外送出
* assert構文による例外送出
* JVMによる例外送出（メモリ不足など）

メソッドの抜け方は次の3種類のいずれか1つに限る。

* return文で値を返す
* 返り値の方がvoidで、メソッドの最後の文まで実行して抜ける
* throw文で例外を投げる

throwでメソッドを抜けるのは、return文で抜けるのと似ているが、返り値を持たない。ただし、例外を補足したcatch節やfinally節でreturnすれば返り値を持たせることもできる。
コールチェインを遡っていく途中でどのcatch節にも補足されなかった例外は、最終的に補足されない例外になって、補足されない例外は、そのスレッドの実行を止める。

## 演算による例外送出

```
#!java

// 0による整数除算
int i = 1 / 0; //=> ArithmeticException

// 配列の範囲を超えたアクセス
int[] array = new int[0];
array[0] = 0; //=> ArrayIndexOfBoundsException

// 配列の要素型の違反
Object[] array = new String[1];
array[0] = 0; //=> ArrayStoreException

// 不正な型キャスト
Object obj = "";
Integer i = (Integer)obj; //=> ClassCastException

// null値へのドット演算子アクセス
Object obj = null;
String s = obj.toString(); //=> NullPointerException
```

## 独自例外クラスのコンストラクタの定石

[コード例](../../../../../commits/fc2b4868d4f928e0e0db20584d7c8d052bbf9a9d)

多くの独自例外クラスは、定石のコンストラクタだけを持ち、独自のフィールドやメソッドは持たない。特別なメソッドやフィールドを持たなくても、例外の分割の目的を果たせるため。

## 検査例外と実行時例外の違い

|例外の種類|目的|特徴|
|----------|----|----|
|検査例外|メソッド内で起きる異常の抽象化|throws節での宣言が必須。throws節で宣言していない検査例外は投げられない。投げることが必須ではない。catch節での補足が必須。|
|実行時例外|誤ったメソッド呼び出しの検出|throws節での宣言は自由。catch節での補足は必須ではない。|

## 例外とメソッドのオーバーライド

```
#!java

class MyException extends Exception {}
class MyMyException extends MyException {}
class YouException extends Exception {}

abstract class Base {
	abstract void doit() throws MyException;
}

// 基底例外と同じ例外型をthrows節で宣言できる
class Sub extends Base {
	void doit() throws MyException {}
}

// 基底例外の派生例外型をthrows節で宣言できる
class Sub extends Base {
	void doit() throws MyMyException {}
}

// 実行時例外の例外型をthrows節の宣言に加えるのは自由
class Sub extends Base {
	void doit() throws MyException, MyMyException, RuntimeException {}
}

// throws節の宣言から例外を除去するのは自由
class Sub extends Base {
	void doit() {}
}

// 基底例外の派生元の検査例外型をthrows節の宣言に加えるのは不可
class Sub extends Base {
	void doit() throws Exception {} // コンパイルエラー
}

// 基底例外と派生関係にない検査例外型をthrows節の宣言に加えるのは不可
class Sub extends Base {
	void doit() throws YouException {} // コンパイルエラー
}
```

## 契約によるデザイン（assert）

```
#!java

// assert文の文法
assert 真偽値の式;
assert 真偽値の式 : 詳細式;
```

真偽値の式の評価値が偽の時、assert文はAssertionError例外を送出する。
等価な式を書くと、

```
#!java

assert s != null;
if (s != null) {
	throw new AssertionError();
}

assert s != null : "s must be valid";
if (s != null) {
	throw new AssertionError("s must be valid");
}
```

となる。

assert文が構文として用意されている意味は、絶対に成立すべき式を書くために使い、コードのバグであることの意思表示をするため。コメントで記述することも可能だが、コメントは開発者の良心に依存する弱い制約であるため、より厳密に、より強く仮定を明記するにはassert文を書く。

```
#!java

class User {
	private String name;
	private int birthYear;

	User(String name, int birthYear) {
		assert birthYear > 0;
		// 省略
	}
}
```

assert文を有効にするには、javaコマンドに特別な引数（-ea）が必要。assert文は開発中のプログラムのための機能と考える。実運用時には無効にすることが普通。
assert文を実行する保証がないため、assert文の式に副作用を期待する式を書いてはいけない。

## メソッドの引数チェック

メソッドの引数チェックにassert文を使うのは定石。この場合、それは内部メソッドに限定すべき。
外部公開APIでは、適切な実行時例外を投げる。assert文は開発中にバグを発見するために使うべきで、公開APIの誤った呼び出しのような普通に起こりうることの検出に使うべきではない。javaコマンドに引数(-ea)がないときに不正な引数のチェックをしない公開APIでは役に立たない。

```
#!java

public class User {
	private String name;
	private int birthYear;

	public User(String name, int birthYear) {
		if (name == null || name.isEmpty() || birthYear <= 0) {
			throw new IllegalArgumentException();
		}
		// 省略
	}
}
```

引数チェックにString nameのnullチェックもassertで行うようにすべきか？これはバランスの問題で、どちらにしろnameにnullが渡ればどこかでNullPointerExceptionが起こるはずなので、チェック不要という考え方も出きる。

## 例外の指針

* ExceptionクラスおよびRuntimeExceptionクラスは直接扱わない（直接throwしない。直接throws節に宣言しない）
* 独自の例外（アプリケーション例外）を検査例外として定義する（Exceptionのサブクラス）
* 実行時例外は可能な限り、標準ライブラリで定義済みの例外を使い回し、独自に定義しない（後述する広域脱出用例外を除く）
* Error（エラー例外）のサブクラスは作らない

## 独自の例外（アプリケーション例外）と例外翻訳

ライブラリが投げる検査例外に対していたるところにcatch節を追加してしまい、コードの可読性や保守性の観点から好ましくないコードを書きがちであるが、かと言ってライブラリコードを呼ぶたびにメソッドのthrows節に検査例外を追加して伝播してもthrows節に検査例外が延々と並ぶコードになる。これも同様に可読性の悪いコード。
このような場合、ライブラリの例外を補足して、アプリケーション例外を投げる技法がよく使われる。これを「*例外翻訳*」と呼ぶ。

```
#!java

class AppException extends Exception {
    // 省略
}

void doit() throws AppException {
    try {
        int c = System.in.read();  // 低レベルの検査例外が発生する処理
    } catch (IOException e) {
        throw new AppException();  // 高レベルの検査例外に翻訳
    }
}
```

例外翻訳によって、低レベルの細かい例外への対応コードではなく、高レベルの例外への対応コードだけに集中できる。

例外翻訳を行った場合、原因となった低レベルの例外を知りたいことがある。例外オブジェクトは内部に原因例外オブジェクトを保持できる。この目的のために、アプリケーション例外クラスには、「**例外型と例外オブジェクト**」の定石のコンストラクタに従い、原因例外オブジェクトを引数で受け取るコンストラクタを実装する。


```
#!java

class AppException extends Exception {
    // 省略
    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
    public AppException(Throwable cause) {
        super(cause);
    }
}

void doit() throws AppException {
    try {
        int c = System.in.read();  // 低レベルの検査例外が発生する処理
    } catch (IOException e) {
        throw new AppException(e);  // 高レベルの検査例外に翻訳
    }
}
```

## イディオム化している実行時例外

検査例外に比べて実行時例外を独自に定義する機会は多くない。実行時例外を独自に定義する前に、標準ライブラリで定義済みの実行時例外を使いまわせないかを検討する。

|実行時例外|意味|
|----------|----|
|IllegalArgumentException|不正な引数|
|IllegalStateException|オブジェクトの不正な状態|
|IndexOutOgBoundsException|境界を越えたインデックス値|
|NullPointerException|null値へのアクセス|
|ConcurrentModificationException|オブジェクトの不正な変更|
|UnsupportedOperationException|不正な操作|

## 広域脱出のための実行時例外

* 実行時例外の基本的な利用目的はプログラムの誤りを検出すること
* 検査例外はメソッド呼び出しが複雑化すると、例外変更の変更コストが乗数的に増える危険性がある
* 実行時例外の呼び出し側で補足しなければ自動的に例外が通過する性質を利用した方法として、フレームワークなどで有効に利用される技法がある
    * フレームワークとアプリケーションの間で、何か異常があれば実行時例外を投げる取り決めをしておき、アプリケーション側で実行時例外を補足することはしない
    * 結果として、実行時例外は元のフレームワークのコードまで伝播し、フレームワーク側で実行時例外の補足処理を書くことで、アプリケーション側で例外コードを各所に記述する必要がなくなる
    ** 実行時例外なので、throws節に例外を宣言する必要もない
    * フレームワーク側も、アプリケーション側に立ち入ることなく、異常が起きたことだけを検出可能になる。Webアプリケーションであれば、画面のレスポンスが必要なので、この技法で異常時の処理を一括して記述することができる
* 大局的に見ると、アプリケーションの任意の場所から、実行時例外の例外処理コードにジャンプしているように見える。一般的にメソッドのコールチェインを一気に抜けてジャンプすることを広域脱出と呼ぶ
    * GOTO文など、広域脱出は一般的に可読性の悪いコードをもたらすとして伝統的に悪い手法と呼ばれてきたが、使い所によっては有効に使える一例
