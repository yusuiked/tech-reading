# 第8章　パラメータ化テスト

## テストデータの選択

* ソフトウェアテストにおいて入力可能な値を全て検証することは現実として不可能
* 入力可能な値を効率よく選択する必要がある

### どのくらいのテストデータが必要か？

#### 同値クラスによるテストデータの選択

* 同値クラスとは、テスト対象メソッドで同じ結果を返す入力値の集合
* 例) 年齢が18歳以上でなければ登録できない会員制サイト
    * 年齢の制限を確認するメソッドの仕様
    * int 型引数（年齢）を取り
    * 18歳以上ならば true, 18歳未満ならば false を返す

```java
/**
 * 年齢を指定して、会員制サイトへ登録できるかを返す
 * @param age 年齢
 * @return 18歳以上ならば true を、18歳未満ならば false
 */
public static boolean canRegister(int age) {
  return 18 <= age;
}
```

この例では、メソッドの結果が true または false なので、2つの同値クラスが存在する

| 結果 | 入力値の例 | 備考 |
| ---- | --------- | ---- |
| true | 20        | 18以上の値 |
| false | 10        | 18未満の値 |

#### 組み合わせによるテストデータの選択

* 例) 会員制サイトのモジュールの1つに特別割引を受けられる優待会員かどうかを確認するメソッド
* 優待会員の条件は「20歳以上であり、メールマガジンに登録してかつ前月の利用回数が1回以上あること」
* このメソッドは出力パターンだけに着目した同値クラスによるテストデータの選択では不十分
* 組み合わせを考慮してテストデータを選択するが、闇雲に選択すると膨大な数になるため、効率よく絞り込む

| 年齢 | メルマガ登録 | 前月の利用回数 | 期待値 |
| ---- | ------------ | -------------- | ------ |
| 20歳以上 | YES | 1回以上 | true |
| 20歳以上 | NO | 1回以上 | false |
| 20歳以上 | YES | 0回 | false |
| 20歳以上 | NO | 0回 | false |
| 20歳未満 | YES | 1回以上 | false |
| 20歳未満 | NO | 1回以上 | false |
| 20歳未満 | YES | 0回 | false |
| 20歳未満 | NO | 0回 | false |

このうち、単項目に着目した入力値の組み合わせと期待値の関係としては

| 年齢 | メルマガ登録 | 前月の利用回数 | 期待値 |
| ---- | ------------ | -------------- | ------ |
| 20歳以上 | YES | 1回以上 | true |
| 20歳未満 | YES | 1回以上 | false |
| 20歳以上 | NO | 1回以上 | false |
| 20歳以上 | YES | 0回 | false |

となる。このようなデータの選択は、テストによるプロダクションコードの実行網羅率（カバレッジ）の測定にも考慮される。

### どのテストデータを選択するか？

* 同値クラス分析を行い、出力値に着目して入力値を絞り込む
* テストデータが不十分であれば、入力値の組み合わせを考慮し、テストデータを増やす
* 各グループからどの値を選択するかについては、効果的に行うために境界値付近で値を選択する

| 年齢 | メルマガ登録 | 前月の利用回数 | 期待値 |
| ---- | ------------ | -------------- | ------ |
| 20 | true | 1 | true |
| 19 | true | 1 | false |
| 20 | false | 1 | false |
| 20 | true | 0 | false |

## 妥当な値の範囲を制限する

* 入力値の範囲を制限することで、テストも効率的になる
* 実装方法としては、メソッドの仕様に受け付けない値の範囲だった場合は例外を送出すると加える
* この実装とテストが必要かどうかは、そのソフトウェアがどの程度の品質を必要とするかによる

```java
public class Age {
  public final int value;

  public Age(int value) {
    if (value < 0 || 150 <= value) {
      throw new IllegalArgumentException();
    }
    this.value = value;
  }
}
```

このように値をドメインとしてオブジェクトで表現すると、プログラムが堅牢になる。が、複雑さとのトレードオフなのでバランスを考慮する。

## 入力値と期待値のパラメータ化

テスト対象メソッドが複数のパラメータを持ち、かつその組み合わせによって結果が異なる場合、多数のテストデータが必要となる。そのような場合に、テストデータとテストメソッドを分割するパラメータ化テストが有効。

### Theories パラメータ化テストのテストランナー

```java
@RunWith(Theories.class)
public class ParameterizedTest {
}
```

なお、 `Theories` テストランナーは、 `Enclosed` テストランナーと併用できる。

```java
@RunWith(Enclosed.class)
public class ParameterizedTest {
  @RunWith(Theories.class)
  public static class XXXの場合 {
  }
}
```

### `@Theory` テストメソッドに指定するアノテーション

パラメータ化テストでテストメソッドに付与するアノテーションで、`@Test` の代わりに付与する。

```java
@RunWith(Theories.class)
public class ParameterizedTest {
  @Theory
  public void testCase(int x) throws Exception {
  }
}
```

### `@DataPoint` パラメータを定義するアノテーション

パラメータを定義するアノテーションで、`static` かつ `public` なフィールドまたはメソッドで定義する。

```java
@RunWith(Theories.class)
class ParameterizedTest {
  @DataPoint
  public static int INT_PARAM_1 = 3;
  @DataPoint
  public static int INT_PARAM_2 = 4;
  public ParameterizedTest() {
    System.out.println("初期化");
  }
  @Theory
  public void 引数を持つテストメソッド(int param) throws Exception {
    System.out.println("引数を持つテストメソッド(" + param + ")");
  }
}
```

### Parameterized テストランナー

`Theories` テストランナーの他に、`Parameterized` テストランナーを使ってパラメータ化テストを行うこともできる。`Parameterized` テストランナーでは、コンストラクタでパラメータを受け取る。このため、テストクラスのフィールドに保持する必要がある。

歴史的には、`Parameterized` テストランナーが先に実装された機能だが、他の xUnit フレームワークではテストメソッドでパラメータを受け取るような仕組みが一般的なので、今後は `Theories` テストランナーを使うほうが良い。

#### 複数のテストメソッドがある場合

パラメータとして定義されたフィールドの型情報から、どのテストメソッドのパラメータとなるかが決定する。

```java
@RunWith(Theories.class)
public class ParameterizedTypeTest {
    @DataPoint
    public static int INT_PARAM_1 = 3;
    @DataPoint
    public static int INT_PARAM_2 = 4;

    @DataPoint
    public static String STRING_PARAM_1 = "Hello";
    @DataPoint
    public static String STRING_PARAM_2 = "World";

    @Theory
    public void 引数がint型のテストメソッド(int param) throws Exception {
        System.out.println("引数がint型のテストメソッド(" + param + ")");
    }

    @Theory
    public void 引数がString型のテストメソッド(String param) throws Exception {
        System.out.println("引数がStringのテストメソッド(" + param + ")");
    }
}
```

しかしこれは、実行結果が直感的に理解できる形ではないため、もし異なる種類のパラメータを扱うパラメータ化テストを行うなら、`Enclosed` テストランナーを併用するとよい。

```java
@RunWith(Enclosed.class)
public class EnclosedParameterizedTypeTest {
    @RunWith(Theories.class)
    public static class intのパラメータ化テスト {
        @DataPoint
        public static int INT_PARAM_1 = 3;
        @DataPoint
        public static int INT_PARAM_2 = 4;

        @Theory
        public void 引数がint型のテストメソッド(int param) throws Exception {
            System.out.println("引数がint型のテストメソッド(" + param + ")");
        }
    }

    @RunWith(Theories.class)
    public static class Stringのパラメータ化テスト {
        @DataPoint
        public static String STRING_PARAM_1 = "Hello";
        @DataPoint
        public static String STRING_PARAM_2 = "World";

        @Theory
        public void 引数がString型のテストメソッド(String param) throws Exception {
            System.out.println("引数がStringのテストメソッド(" + param + ")");
        }
    }
}
```

#### 複数の引数が定義されている場合

定義されたパラメータの全ての組み合わせがテストとして実行される。

```java
@RunWith(Theories.class)
public class ParameterizedMultiParamsTest {
    @DataPoint
    public static int INT_PARAM_1 = 3;
    @DataPoint
    public static int INT_PARAM_2 = 4;
    @DataPoint
    public static String STRING_PARAM_1 = "Hello";
    @DataPoint
    public static String STRING_PARAM_2 = "World";

    @Theory
    public void テストメソッド(int intParam, String strParam) throws Exception {
        System.out.println("テストメソッド(" + intParam + ", " + strParam + ")");
    }
}
```

```
テストメソッド(3, Hello)
テストメソッド(3, World)
テストメソッド(4, Hello)
テストメソッド(4, World)
```

#### 同じ方の引数が複数定義されている場合

重複も含めて全ての組み合わせで実行される。複雑になるので、フィクスチャオブジェクトを利用すると良い。

```java
@RunWith(Theories.class)
public class ParameterizedMultiSameTypeParamsTest {
    @DataPoint
    public static int INT_PARAM_1 = 3;
    @DataPoint
    public static int INT_PARAM_2 = 4;

    @Theory
    public void テストメソッド(int x, int y) throws Exception {
        System.out.println("テストメソッド(" + x + ", " + y + ")");
    }
}
```

```
テストメソッド(3, 3)
テストメソッド(3, 4)
テストメソッド(4, 3)
テストメソッド(4, 4)
```

#### パラメータをフィクスチャオブジェクトにまとめる

可読性を高めるためにパラメータをフィクスチャオブジェクトにまとめるとよい。

```java
@RunWith(Theories.class)
public class CalculatorDataPointTest {
    @DataPoint
    public static Fixture PARAM_1 = new Fixture(3, 4, 7);
    @DataPoint
    public static Fixture PARAM_2 = new Fixture(0, 5, 5);
    @DataPoint
    public static Fixture PARAM_3 = new Fixture(-3, 1, -2);

    @Theory
    public void add(Fixture p) throws Exception {
        Calculator sut = new Calculator();
        assertThat(sut.add(p.x, p.y), is(p.expected));
    }

    static class Fixture {
        int x;
        int y;
        int expected;

        Fixture(int x, int y, int expected) {
            this.x = x;
            this.y = y;
            this.expected = expected;
        }
    }
}
```

### `@DataPoints` 複数のパラメータを定義するアノテーション

`@DataPoints` アノテーションは、`@DataPoint` アノテーションと同様にパラメータ化テストのパラメータの定義を行うアノテーション。

`DataPoints` を使うと1つのフィールドに複数のパラメータを定義できる。`DataPoint` と同様 `static` メソッドでパラメータを定義できるので、外部リソースとしてパラメータを定義するとテストデータのバリエーションを増やすにもテストクラスを修正する必要がなくなる。

```java
@RunWith(Theories.class)
public class CalculatorDataPointsTest {
    @DataPoints
    public static Fixture[] PARAMS = {
            new Fixture(3, 4, 7),
            new Fixture(0, 5, 5),
            new Fixture(-3, 1, -2)
    };

    @Theory
    public void add(Fixture p) throws Exception {
        Calculator sut = new Calculator();
        assertThat(sut.add(p.x, p.y), is(p.expected));
    }

    static class Fixture {
        int x;
        int y;
        int expected;

        Fixture(int x, int y, int expected) {
            this.x = x;
            this.y = y;
            this.expected = expected;
        }
    }
}
```

#### 外部リソースを使ったパラメータ化テスト

```java
@RunWith(Theories.class)
public class CalculatorDataPointTest {
    @DataPoint
    public static Fixture PARAM_1 = new Fixture(3, 4, 7);
    @DataPoint
    public static Fixture PARAM_2 = new Fixture(0, 5, 5);
    @DataPoint
    public static Fixture PARAM_3 = new Fixture(-3, 1, -2);

    @Theory
    public void add(Fixture p) throws Exception {
        Calculator sut = new Calculator();
        assertThat(sut.add(p.x, p.y), is(p.expected));
    }

    static class Fixture {
        int x;
        int y;
        int expected;

        Fixture(int x, int y, int expected) {
            this.x = x;
            this.y = y;
            this.expected = expected;
        }
    }
}
```

```yml
!!seq [
  !!yaml.CalculatorDataPointsYamlTest$Fixture
  { x: 3, y: 4, expected: 7 },
  !!yaml.CalculatorDataPointsYamlTest$Fixture
  { x: 0, y: 5, expected: 5 },
  !!yaml.CalculatorDataPointsYamlTest$Fixture
  { x: -3, y: 1, expected: -2 },
]
```

## 組み合わせテスト

`Theories` テストランナーを使うと、多数のパラメータを組み合わせでテストできるが、テスト結果が同一のパラメータしか定義できない（そうしないと失敗する）ため、あまり使いみちがない。そこで `assumeThat` メソッドと組み合わせて使う。

`Assume` クラスは `Assert` クラスとよく似たクラスで、値の検証メソッドを提供する。`assumeThat` メソッドは `assertThat` メソッドに対応していて、`assumeThat` メソッドは `assertThat` メソッドと違い検証結果が `false` の場合でも失敗にならない。

### Assume によるパラメータのフィルタリング

```java
@RunWith(Theories.class)
public class MemberTest {
    @DataPoints
    public static int[] AGES = {15, 20, 25, 30};
    @DataPoints
    public static Member.Gender[] GENDERS = Member.Gender.values();

    @Theory
    public void canEntryは25歳以下の女性の場合にtrueを返す(int age, Member.Gender gender) throws Exception {
        assumeTrue(age <= 25 && gender == Member.Gender.FEMALE);
        assertThat(Member.canEntry(age, gender), is(true));
    }
    @Theory
    public void canEntryは25歳以下の女性でない場合にfalseを返す(int age, Member.Gender gender) throws Exception {
        assumeTrue(25 < age || gender != Member.Gender.FEMALE);
        assertThat(Member.canEntry(age, gender), is(false));
    }
}

public class Member {
    public static boolean canEntry(int age, Gender gender) {
        if (gender == Gender.MALE) return false;
        if (25 < age)  return false;
        return true;
    }

    public enum Gender {
        MALE,
        FEMALE,
    }
}
```

`assumeThat` メソッドや `assumeTrue` メソッドは、検証が失敗した場合は `AssumptionViolatedException` を送出する。

このように条件を宣言的に記述できるため、可読性を損なわない。

`Assume` クラスを使うと特定の環境に依存するテストの実行をフィルタリングできる。

```java
public class WindowsOnlyTest {
  @Test
  public void windows環境では改行はrn() throws Exception {
    assumeTrue(System.getProperty("os.name").contains("Windows"));
    assertThat(System.getProperty("line.separator"), is("\r\n"));
  }
}
```

## パラメータ化テストの問題

### データの網羅性

* データの取り方や網羅性に関して保証されるわけではない                                                                                               * 先の例では、テストデータに 25 歳以下の女性が含まれていることは保証されない
* レビューで検証する必要がある

### パラメータに関する情報の欠落

* どんなパラメータで失敗したか？という情報が欠落してしまう
* テストケースが成功／失敗したかどうかしか通知されない
  * 何番目のパラメータであるか、という情報は出力される
* 問題の分析に時間がかかってしまう
* アサーションメソッドにパラメータ情報を含めると良い

```java
@Theory
public void canEntryは25歳以下の女性でない場合にfalseを返す(int age, Gender gender) throws Exception {
  assumeTrue(25 < age || gender != Gender.FEMALE);
  String msg = "When age=" + age + ", gender=" + gender;
  assertThat(msg, Member.canEntry(age, gender), is(false));
}
```
