# 第5章　テストランナー

## JUnit の実行の仕組み

* `org.junit.runner.JUnitCore` が `main` メソッドを持っており、起動時引数に渡されてきたテストクラスを以下の流れで実行する
    * `@Test` アノテーションが付いたメソッドの収集
    * 実行
    * 結果のレポート
* テストケースの除外や複数テストクラスの実行など、テストの実行をカスタマイズするために JUnit は「テストランナー」という仕組みを提供している
    * 例えば `suite` テストランナーを使うと複数のテストクラスのテストケースをまとめて実行できる
* テストランナーを指定するには、テストクラスに `org.junit.runner.RunWith` アノテーションを付与して、引数にテストランナークラスを指定する。何も指定しないと、デフォルトで `JUnit4.class` がテストランナーとして実行される。

```java
@RunWith(JUnit4.class)
public class SomeTest {
}
```

* テストの実行結果をカスタマイズしたい（例えば別のツールに連携するために出力結果を JSON/XML/YAML などで吐きたい、など）場合は、`org.junit.runner.Runner` インタフェースを実装してカスタムテストランナーを作成する

## JUnit が提供するテストランナー

### JUnit4 ― テストクラスの全テストケースを実行する

* デフォルトのテストランナー
* `@Test` アノテーションが付いた戻り値が `void` で引数を持たないメソッドを実行する

### Suite ― 複数のテストクラスをまとめて実行する

* テストクラスをまとめるテストスイートクラスを作成し、以下の2つのアノテーションを付与する
* `@RunWith` アノテーションに `org.junit.runners.Suite` クラスを指定する
* `org.junit.runners.Suite.SuiteClasses` アノテーションを付与してパラメータに実行したいテストクラスまたはテストスイートクラスを配列で指定する
* テストスイートクラスは慣習的に `*Tests` という複数形の命名にする
* テストスイートクラスはテストクラスでもあるので、`org.junit.runner.JUnitCore` にパラメータとして渡せばそのテストスイートクラスに含まれるテストが全て実行される

```java
@RunWith(Suite.class)
@SuiteClasses({FooTest.class, BarTest.class})
public class AllTests {
}
```

* Maven などのビルドツールでは、テスト実行対象のクラスとして `*Test` で終わっているクラス命名規約で識別するので、慣習的にテストクラスは `*Test` という命名で統一する

### Enclosed ― 構造化したテストクラスを実行する

* テストケースが増えてきた時に効果を発揮する
* `@RunWith` アノテーションに `org.junit.runners.Enclosed` クラスを指定する
* テストクラスの中にネストしたテストクラスをいくつでも定義できる
* テストのコンテキスト（例えば0件の時、1件以上ある時、など）ごとにテストケースを記述でき、同じ特徴のテストケースを構造化できる
* ネストしたテストクラスはそれぞれ `setUp` メソッドを持てるため、コンテキストごとに初期化を行うことも出来るため、テストコードの可読性を高めることができる

```java
@RunWith(Enclosed.class)
public class ItemStockTest {

	public static class 空の場合 {
		ItemStock sut;
		
		@Before
		public void setUp() throws Exception {
			sut = new ItemStock();
		}
		
		@Test
		public void size_Aが0を返す() throws Exception {
			assertThat(sut.size("A"), is(0));
		}
		
		@Test
		public void contains_Aはfalseを返す() throws Exception {
			assertThat(sut.contains("A"), is(false));
		}
	}
	
	public static class 商品Aを1件含む場合 {
		ItemStock sut;
		
		@Before
		public void setUp() throws Exception {
			sut = new ItemStock();
			sut.add("A", 1);
		}
		
		@Test
		public void sizeが1を返す() throws Exception {
			assertThat(sut.size("A"), is(1));
		}
		
		@Test
		public void contains_Aはtrueを返す() throws Exception {
			assertThat(sut.contains("A"), is(true));
		}
	}
}
```

### Theories ― パラメータ化したテストケースを実行する

* パラメータ化テストとは、テストケースとテストデータを分離し、同じテストケースを複数のテストデータを使ってテストするテクニック
* `@RunWith` アノテーションに `org.junit.experimental.theories.Theories` クラスを指定する
* `@Test` アノテーションの代わりに、`org.junit.experimental.theories.Theory` アノテーションをテストメソッドに付与する
* テストメソッドに渡されるパラメータは、`org.junit.experimental.theories.DataPoints` アノテーションを付与した static フィールドなどで定義する
* 入力値や期待値を外部ファイルとして定義することもできる

```java
@RunWith(Theories.class)
public class CalcTheoriesTest {
	@DataPoints
	public static int[][] VALUES = {
		{0, 0, 0},
		{0, 1, 1},
		{0, 1, 1},
		{1, 0, 1},
		{3, 4, 7},
	};
	
	@Theory
	public void add(int[] values) throws Exception {
		Calc sut = new Calc();
		assertThat(sut.add(values[0], values[1]), is(values[2]));
	}
}
```

### Categories ― 特定カテゴリのテストクラスをまとめて実行する

* テストケースをカテゴリ化し、実行するテストケースをフィルタリングするためのテストランナー
* カテゴリ化とは、簡単に言うと目印を付けて分類すること
	* 目印となるカテゴリクラスを作成する
	* どんなクラス（インタフェース）でも構わない
	* スローテストとしてマーキングするなら例えば `public interface SlowTests {}` など
* `org.junit.experimental.categories.Category` アノテーションに、カテゴリクラスを指定する
* `@Category` アノテーションはテストメソッドにも、テストクラスにも付与でき、クラスに付与した場合は、そのクラスに属する全てのメソッドがそのカテゴリに属する
* 実行するには、`Suite` テストランナーと同様に `@SuiteClasses` で対象となるテストクラスを指定し、`@ExcludeCategory` アノテーションで除外するカテゴリクラスを指定する

以下は、`SlowTest` カテゴリと宣言されていないテストだけが実行される例。

#### カテゴリ化したテストケース

```java
public class SlowAndFastTest {
	@Test
	public void fastTest_01() throws Exception {
	}
	
	@Test
	@Category(SlowTests.class)
	public void slowTest_01() throws Exception {
		fail();
	}
	@Test
	@Category(SlowTests.class)
	public void slowTest_02() throws Exception {
		fail();
	}
}
```

#### カテゴリ化したテストケースを実行するテストスイートクラス

```java
@RunWith(Categories.class)
@ExcludeCategory(SlowTests.class)
@SuiteClasses(SlowAndFastTest.class)
public class CategoriesTests {
}
```