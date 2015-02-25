# 第4章　アサーション

## Assert による値の比較検証

* `org.junit.Assert` クラスは、アサーションを行うためのアサーションメソッドが多く提議されている
* `static` インポートと組み合わせると、英語表記のように自然に読めるので可読性が高くなるため、積極的に `static` インポートすること

### `assertThat` ―汎用的な値の比較検証

* JUnit では、値の比較検証のほとんどを `assertThat` メソッドで行える
* 比較検証のルールをカスタマイズして使えるように、`Matcher` オブジェクトを選択できる設計になっている。そのため標準の `Matcher` で不足がある場合は、カスタム `Matcher` を作ることも可能

### `fail` ―テストを失敗させる

* 何らかの理由でテストを無条件に失敗として扱いたい場合に使う
    * テストメソッドを作成したがテストコードを記述していない、など
    * あるブロックが実行された場合にテストを失敗としたい場合

```java
@Test(expected = IllegalStateException.class)
public void timeoutがtrueのときにロジックが実行されないこと() {
    // SetUp
    Runnable logic = new Runnable() {
        public void run() {
            fail("run が呼ばれてしまった");
        }
    };
    sut.timeout = true;
    // Exercise
    sut.invoke(logic);
}
```

ただし、このように一見して何をテストしているのかが不明瞭なテストコードは、可能な限り避けるべき。

### その他のアサーションメソッド

* `assertEquals`
* `assertTrue`

などは、古いバージョンとの互換性のために残っているものなので、基本的には JUnit4 で使うアサーションメソッドは `assertThat` と `fail` の2つ。

## Matcher API によるアサーションの特徴

* Matcher API は、「何らかの値に対して等価であるかを柔軟に比較検証する」ためのAPI
* もとは Hamcrest という独立した JUnit の拡張ライブラリに含まれていたが、JUnit 4.4 で本家に組み込まれた
    * Hamcrest は、`matchers` のアナグラム
* ほとんどの場合、プリミティブ型なら `==` オペレータによる比較、オブジェクトなら `equals` メソッドによる比較で十分だが、特定のオブジェクトの比較の際に特定のフィールドは除外したい、などといった状況がある
* 比較するオブジェクトに `equals` メソッドがオーバーライドされていない場合は、オブジェクトの同一性の比較になってしまうため想定しない結果になる
* これらを解決するために、Matcher API がある

### 可読性の高い記述

```java
// JUnit 4
int expected = 7;
int actual = add(3, 4);
assertThat(actual, is(expected));

// JUnit 3
int expected = 7;
int actual = add(3, 4);
assertEquals(expected, actual);
```

JUnit 4 と Matcher API を用いると、検証コードが自然言語の表記に近くなり、可読性が高まる。仕様をテストコードへ変換しやすくなり、読む際も自然な形で読むことができる。

### 柔軟な比較

`Equals` メソッドによる単純な比較だけでは厳密すぎる場合や独自ロジックで比較検証した場合は、Matcher API を使うと可読性を損ねずに柔軟で再利用可能な比較検証ができる。

例えば、リストの任意の位置に文字列"Hello"が含まれていることを検証したい場合、

```java
List<String> actual = sut.getList();
assertTrue(actual.contains("Hello");
```

と書くこともできるが、これは正しくは「リストの `contains` メソッドに文字列 "Hello" を指定すると `true` となること」を検証している。

Matcher API を使うと、

```java
List<String> actual = sut.getList();
assertThat(actual, hasItems("Hello");
```

とより直感的に検証したいことが表現できる。また、カスタム Matcher を作ることで、より柔軟な比較検証を行うこともできる。例えば、データベースから取得したオブジェクトの自動採番されたIDや作成日時などを比較検証対象から除外したい場合などに使える。

### 詳細な情報の提供

Matcher API を使うと、テストに失敗した際に、多くの情報を提供できる。このため、失敗した際の原因の特定がしやすくなる。

## Matcher API の使用

* `org.hamcrest.CoreMatchers`
* `junit.matchers.JUnitMatchers`

などのクラスのファクトリメソッドを使って `Matcher` オブジェクトを生成する。これらは `static` インポートを使ってワイルドカード指定をすると可読性が高くなる。

### `CoreMatchers` が提供する `Matcher`

#### `is`

```java
assertThat(actual, is(expected));
```

実測値が `null` であることを検証する場合は、`nullValue()` メソッドを使う。また、`Matcher` 型の引数を持つ `is` メソッドがオーバーロードされており、これを使うと他の `Matcher` オブジェクトのファクトリメソッドと組み合わせることで自然な英語表記で検証コードが記述できる。

#### `nullValue`

```java
assertThat(actual, is(nullValue()));
```

`null` であることを検証する。

#### `not`

```java
assertThat(actual, is(not(0));
```

他の `Matcher` の評価値を反転させる `Matcher` を返す。

#### `notNullValue`

```java
assertThat(actual, is(notNullValue()));
```

`null` でないことを検証する。下記と等価。

```java
assertThat(actual, is(not(nullValue())));
```

#### `sameInstance`

```java
assertThat(actual, is(sameInstance(expected)));
```

実測値と期待値が同一のインスタンスであるかを比較する。つまり、オブジェクトに対する `==` 演算子の比較と同様。

プリミティブ型で `sameInstance` メソッドを利用する場合は、ボクシング変換に注意する。

```java
// fail する
assertThat(128, is(sameInstance(128)));
```

上記のコードは、Integer オブジェクトが内部的に 127 までの値をキャッシュして同一のインスタンスを返すが、128 以降は新しくインスタンスを生成して返すような JVM の実装であるため、インスタンスは異なるためにテストが失敗する。このように、一見して理解できないコードは避けること。

#### `instanceOf`

```java
assertThat(actual, is(instanceOf(Serializable.class)));
```

実測値が期待するクラスのインスタンスと互換性のある型であるかを比較検証する。つまり、`instanceOf` 演算子の比較と同様。実測値が `null` の場合は必ず失敗する。

### `JUnitMatchers` が提供する `Matcher`

#### `hasItem`

```java
List<String> actual = sut.getList();
assertThat(actual, hasItem("World"));
```

実測値がリストや配列など反復可能なオブジェクト（`Iterable` インタフェースを実装したクラスのインスタンス）に期待する値が含まれているかを検証する。

#### `hasItems`

```java
assertThat(actual, hasItems("Hello", "World"));
```

実測値がリストや配列など反復可能なオブジェクトに期待する値が複数含まれているかを検証する。引数が可変長引数となるので、期待値を複数指定できる。すべての値が実測値に含まれているかを検証する。

リストの順序は問わずに必要な要素が全て含まれているかどうかを検証したい場合に有効。

### その他 Hamcrest が提供する `Matcher`

以下抜粋。

| クラス名 | メソッド名 | 検証内容 |
|----------|------------|----------|
|`org.hamcrest.collection.isEmptyCollection`|`empty`|空の Collection であること|
|`org.hamcrest.collection.IsCollectionWithSize`|`hasSize`|Collection が期待するサイズであること|
|`org.hamcrest.collection.IsMapContaining`|`hasEntry`|Map が指定した Key と Value を持つこと|
|`org.hamcrest.collection.IsMapContaining`|`hasKey`|Map が指定した Key を持つこと|
|`org.hamcrest.collection.IsMapContaining`|`hasValue`|Map が指定した Value を持つこと|
|`org.hamcrest.number.OrderingComparison`|`comparesEqualTo`|比較して同じ数値であること|
|`org.hamcrest.number.OrderingComparison`|`greaterThan`|比較して期待値より大きいこと（算術記号の「>」に相当）|
|`org.hamcrest.number.OrderingComparison`|`greaterThanOrEqualTo`|比較して期待値より大きいか同じであること（算術記号の「>=」に相当）|
|`org.hamcrest.number.OrderingComparison`|`lessThan`|比較して期待値より小さいこと（算術記号の「<」に相当）|
|`org.hamcrest.number.OrderingComparison`|`lessThanOrEqualTo`|比較して期待値より小さいか同じであること（算術記号の「<=」に相当）|
|`org.hamcrest.number.IsCloseTo`|`closeTo`|比較して期待値の範囲の数値であること|
|`org.hamcrest.text.isEmptyString`|`isEmptyString`|文字列が空文字列であること|
|`org.hamcrest.text.isEmptyString`|`isEmptyOrNullString`|文字列が空文字列または `null` であること|
|`org.hamcrest.text.isEqualIgnoringCase`|`equalToIgnoringCase`|大文字小文字を無視して文字列が一致すること|
|`org.hamcrest.beans.SamePropertyValuesAs`|`samePropertyValuesAs`|Bean のプロパティがそれぞれ一致すること|

## カスタム Matcher の作成

### 日付の比較検証を行うカスタム Matcher の要件

Java で日付や時刻を扱う場合、標準的には `java.util.Date` クラスを使用するが、JUnit で `Date` 型のオブジェクトのアサーションを行うのは手間がかかる。特定日付の `Date` オブジェクトは作りにくく、さらにミリ秒までを保持するため、年月日だけを比較検証したい場合でも簡単には比較できない。このような時にカスタム Matcher を作ると良い。以下、要件をまとめると、

* 年／月／日をそれぞれ `int` 型で指定して日付のみを比較できる
* 時／分／秒／ミリ秒は比較検証時に無視する
* 比較に失敗した場合は `yyyy/MM/dd` 表記で確認できる

### カスタム Matcher の作成手順

* カスタム Matcher は、`org.hamcrest.Matcher` インタフェースを実装したクラスとして作成する
* ただし、`Matcher` インタフェースを直接 `implements` するのではなく、`org.hamcrest.BaseMatcher` クラスのサブクラスとする
* `BaseMatcher` クラスのサブクラスでは、`matches` メソッドと `describeTo` メソッドを実装する
* `matches` メソッド
    * 値の比較検証を行う
* `describeTo` メソッド
    * 比較が失敗した場合にフレームワークに通知する情報を作成する

以下、日付の比較検証をするカスタム Matcher `IsDate` の作成例。

