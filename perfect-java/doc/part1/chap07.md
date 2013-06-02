# インターフェース

## インタフェースを意識する重要性

内部実装ではなく、全体の公正を組み立てるときには、それぞれの構成要素を組み合わせるために部品として抽象化する頭の切り替えが必要。
その際に、分割した部品の境界を意識することで、ソースコードの複雑さに退治できる。

## Javaにおけるインタフェース

オブジェクトに対して、文脈によってそのオブジェクトをどう見立てるかによって、オブジェクトの振る舞いを制御できる。
その見立てをどう見立てるかが、インタフェースによる抽象化。

## クラスとインタフェースの違い

* インタフェースがひな形としての役割を持たない点
    * オブジェクトがどう振る舞うかを決めることのみに特化した言語機能。型定義に特化した言語機能とも言い換えられる。
* クラスは、型定義の役割と同時に、ひな形としての役割、つまりオブジェクトの実装を提供する役割を担う。

## インタフェースと多態性

* 参照型変数を通じてオブジェクトに対して呼べるメソッドは、変数の型で決まる
* 呼ばれたメソッドの本体は、参照先のオブジェクトで決まる

変数の型をインタフェース型にすると、本来参照先のオブジェクトが持っているメソッドを限定するように見えるが、それは適切な見方ではなく、変数側から見ると参照可能なオブジェクトの幅が広がる。もし、変数の型が具象クラスの型であれば、その変数が参照可能なオブジェクトは具象クラスのみになる。

変数の型をインタフェース型にすることで、そのインタフェースを実装した任意のクラスのインスタンスを参照できる。
クラスの拡張継承には、多態性と同時に実装コードの共有という目的もあるが、インタフェースの拡張継承は、純粋に多態性のみが目的。

## オブジェクトの振る舞いとオブジェクトの実装

* オブジェクトの振る舞い
    * インタフェース
* オブジェクトの実装
    * クラス

境界を意識するコード、APIとして公開を意識するコード（公開メソッドの日キスや戻り値の型）では、変数の型をインタフェース型にし、それ以外の場合は変数の型をクラス型にするのが一つの指針。

## コードの依存性とインタフェース

変化しにくいものや、変化させるべきでないものをインタフェースにする。使い分けは次のようにする。

* 変化しにくい振る舞いを規定して、それをインタフェースにまとめる
* クラスはインタフェースを実装すると宣言することで、変化しにくい部分を持つことを表明する
* クラスではなくインタフェースに依存することで、変化しにくい部分にのみ依存することを保証する

変化しにくい部分を抽出し、インタフェースとして宣言するのは、変化させないことの意思表示になる。

Javaでは、タイプセーフを実現するための縛りとして、以下の関係でない限り、オブジェクトの参照を変数に代入できない制約を言語仕様として設けている。

* 変数の型が、オブジェクトのクラス型と一致
* 変数の型が、オブジェクトのクラスの拡張元のクラス型と一致
* 変数の型が、オブジェクトのクラスが実装するインタフェース型と一致

## インタフェースと抽象クラス

* 抽象基底クラスは、実装の拡張の役割
    * クラスの拡張による継承は、実装の継承のために用いる
* インタフェースは振る舞いの表明の役割
    * インタフェースの実装による継承は、振る舞いの継承のために用いる

## インタフェース宣言

インタフェースの構成要素は次の通り。

* メソッド（本体なし）
* 定数フィールド
* staticなネストしたクラス
* staticなネストしたインタフェース

インタフェースに使える修飾子は次の通り。

|修飾子|意味|
|------|----|
|public|publicにするとグローバルスコープ。書かないとパッケージスコープ。|
|abstract|インタフェースは暗黙にabstractなので書いても書かなくても同じ。|
|strictfp|インタフェース内に記述した浮動小数点演算を厳密に評価。|
|アノテーション| |

## インタフェースのメンバ

* 定数フィールド宣言
    * 暗黙にpublic static final
* メソッド宣言
    * 暗黙にpublic abstract
* ネストしたクラス宣言およびネストしたインタフェース宣言

インタフェースの場合、メンバのアクセス制御はすべてが暗黙にpublic。なので慣習的には省略することが多い。
定数フィールドは、publicの場合定数定義のように使用できるが、このようなインタフェースの使い方に異論もある。列挙型などを使うべきとの意見に変わってきた。
インタフェースのメソッド宣言に書ける修飾子は以下のとおり。

|修飾子|意味|
|------|----|
|public|暗黙にpublicなので書いても書かなくても同じ|
|abstract|暗黙にabstractなので書いても書かなくても同じ|
|protected,private,static,final,native,synchronized,strictfp|禁止|

## ネストしたインタフェース

クラスと同様、インタフェースもネストすることができる。以下の組み合わせがある。

* クラス内のネストしたインタフェース
* インタフェース内のネストしたインタフェース
* インタフェース内のネストしたクラス

static修飾子があろうとなかろうと常にstatic。なので、内部クラスのような仕組みは存在しない。

```
#!java

class NestedInterfaceSample {
    interface Foo {
        String getMessage();
    }
    private class FooImpl implements Foo {
        private String s;
        FooImpl(String s) {
            this.s = s;
        }
        @Override public String getMessage() {
            return s;
        }
    }
    Foo getFoo() {
        return new FooImpl("abc");
    }
}

// 呼び出し側
NestedInterfaceSample sample = new NestedInterfaceSample();
NestedInterfaceSample.Foo obj = sample.getFoo();
System.out.println(obj.getMessage());
```

```
#!java

// インタフェース内のネストしたクラスの例
interface Foo {
    // インタフェース内のネストしたクラスのアクセス制御は常にpublicかつ、static。記述は慣習的に省略。
    class Baz {
        private String s;
        public Baz(String s) {
            this.s = s;
        }
        public String getMessge() {
            return s;
        }
    }
    // ネストしたクラスをFooインタフェースの戻り値として使える
    Baz getBaz();
}

// 実装クラス側
class FooImpl implements Foo {
    public Baz getBaz() {
        return new Foo.Baz("abc");
    }
}

// 呼び出し側
Foo obj = new FooImpl();
Foo.Baz baz = obj.getBaz();
System.out.println(baz.getMessage());
```

## 多重継承

クラスは複数のインタフェースをimplementsで指定できる。

```
#!java

// 多重継承した2つのインタフェースが同じシグネチャのメソッドを持つ場合
interface Foo {
    void print(String s);
}
interface Bar {
    void print(String s);
}
class FooBar implements Foo, Bar {
    public void print(String s) {
        // 実装
    }
}

// 多重継承したインタフェースが同じ同名のメソッドを持つ場合
interface Foo {
    void print();
}
interface Bar {
    void print(String s);
}
class FooBar implements Foo, Bar {
    public void print() {
        // 実装が必要
    }
    public void print(String s) {
        // 実装が必要
    }
}

// 次の例はシグネチャとして戻り値の型を含まないため、オーバーロードとして認められずコンパイルエラー
interface Foo {
    void print();
}
interface Bar {
    String print();
}
class FooBar implements Foo, Bar {
    public void print() {
        // 実装してもコンパイルエラー
    }
    public String print() {
        // 実装してもコンパイルエラー
    }
}

// ただし、次のように戻り値の型に型の継承関係がある場合、有効
interface Foo {
    Object print();
}
interface Bar {
    String print(); // StringはObjectの継承型
}
class FooBar implements Foo, Bar {
    public String print() {  // 最下のサブタイプを戻り値の型としたメソッドを実装する
        // 実装が必要
    }
}

// 多重継承でのインタフェース定数
// 以下の例はコンパイルエラー
interface Foo {
    int FOO = 1;
}
interface Bar {
    int FOO = 1;
}
class FooBar implements Foo, Bar {
    // 省略
}

// 定数を上書きで宣言してコンパイルエラーを回避
// ただし、このような定数の隠蔽はコードの可読性を落とすので、避けるべき。
interface Foo {
    int FOO = 1;
}
interface Bar {
    int FOO = 1;
}
class FooBar implements Foo, Bar {
    int FOO = 1;
}
```
