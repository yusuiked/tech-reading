# 第7章　テストフィクスチャ

## テストフィクスチャとは？

テストデータやテスト実行環境、オブジェクトの状態などを指す。狭義にはテストデータのみを指すこともある。

### ユニットテストのフィクスチャ

* テスト対象オブジェクト
* テストの実行に必要なオブジェクト（入力値）
* テストの検証に必要なオブジェクト（期待値）
* テストの実行までに必要なテストオブジェクトの操作
* ファイルなどの外部リソース
* データベースやソケットサーバなどの外部システム
* 依存クラスや依存外部システムのモックオブジェクト

など。

テストコードの可読性を高めるには、フィクスチャのセットアップをどのように工夫するかが重要。（特に外部リソースに依存するテスト対象クラスのテストなど）

### フレッシュフィクスチャ

* ユニットテストでは、「フィクスチャはテストケースごとに独立し、テストの実行ごとに初期化され、終了時に解放する」が基本的な戦略
* テストケースの独立、互いの依存性の排除というユニットテストの原則と相性が良い
* JUnit では、フィクスチャ配下のように扱われる
    * テストの実行時はテストケースごとにテストクラスのインスタンスを生成し、テストメソッドを実行する
    * テストクラスのインスタンス変数やローカル変数に保持されたテストデータは各テストケースの終了時にガベージコレクタによって破棄
    * フィクスチャが共有されることはない
* シングルトンとして設計されたクラスやデータベースなどの外部リソースはデータを共有せざるをえないため、以下の様な処置が必要
    * テストを並列に実行しない
    * 初期化と後始末を丁寧に行う

### フィクスチャとスローテスト問題

* データベースを扱うテストなどでは、フィクスチャのセットアップに長い時間がかかることが問題となり、これをスローテスト問題と呼ぶ
* フィクスチャのセットアップごとに対象となるテーブルの全レコードを削除し、必要なレコードを追加するため、テストケースが増えてくると全テストの実行に半日以上かかる場合もある
* これを解決するためには、以下の様な方法が有効
    * テストの並列実行
        * マルチスレッドや複数のテスト環境で並列にテストを実行する
    * 共有フィクスチャ
        * 同一のフィクスチャを共有して使うことでセットアップコストを抑える
    * カテゴリ化テスト
        * テストをいくつかのグループに分け、実行するテストをフィルタリングする
* テストケースが少ない場合や、データベースや外部サービスへの通信を含むテストケースが少ない場合は、スローテスト問題が起こることはほとんどないため、スローテスト問題は起こってから対応すべき。原則としてはユニットテストは互いに独立し、全てのテストを実行すること

### 共有フィクスチャ

* フィクスチャは可能な限り共有しないほうが良い
* セットアップコストを抑えることはできるが、代わりに以下の様なデメリットが発生する
    * テストケースの独立性が弱くなり、他のテストケースの実行結果などがテストの実行に影響されやすくなる
    * テストケースのメンテナンス性も低下する
    * 後処理を適切に行う必要がある
    * グローバル変数と同様の問題を引き起こす
* 共有フィクスチャをイミュータブルなオブジェクトとすれば、多くの問題は解決する
    * テストの実行順序
    * テストの実行結果
    * 並列実行
    * いずれにも影響されない

## フィクスチャのセットアップパターン

### インラインセットアップ

* 最も基本的なフィクスチャのセットアップパターン
* テストメソッドごとにフィクスチャのセットアップを行う
* テストメソッド内でテストコードが完結し、見通しが良くなる
* 複雑なセットアップの場合、実行や検証が相対的に短くなるのでわかりづらくなり、可読性が悪い
* 20行を超えるようなメソッドは長いと感じる

##### フィクスチャのインラインセットアップ

```java
public class StringUtilTest {
    @Test
    public void isEmptyOrNullは空文字列でtrueを返す() throws Exception {
        String input = "";
        boolean expected = true;
        boolean actual = StringUtil.isEmptyOrNull(input);
        assertThat(actual, is(expected));
    }
    @Test
    public void isEmptyOrNullはAAAでfalseを返す() throws Exception {
        String input = "AAA";
        boolean expected = false;
        boolean actual = StringUtil.isEmptyOrNull(input);
        assertThat(actual, is(expected));
    }
}
```

### 暗黙的セットアップ

* セットアップメソッドでフィクスチャのセットアップを行うパターン（暗黙的にテスト実行前にセットアップされるため）
* テストメソッドのコードは実行と検証が中心となり、何をテストしようとしているかが明確になる
* `Enclosed` テストランナーを使ったユニットテストで高い効果を期待できる
* 共通の初期化処理を持つテストケースでグループ化するため、テストコードの見通しが良くなる
* テストケースごとに入力値や期待値が異なる場合は、前提条件を満たすために必要なオブジェクトの初期化や操作はセットアップメソッドで行い、それ以外のテストケースごとの固有のフィクスチャはテストメソッド内でセットアップする

##### ネストしたクラスごとに行われる暗黙的セットアップ

```java
@RunWith(Enclosed.class)
public class LruCacheTest {
    public static class AとBを追加している場合 {
        LruCache<String> sut;
        
        @Before
        public void setUp() throws Exception {
            sut = new LruCache<String>();
            sut.put("A", "valueA");
            sut.put("B", "valueB");
        }
        
        @Test
        public void sizeは2() throws Exception {
            assertThat(sut.size(), is(2));
        }
        
        @Test
        public void get_AでvalueAを返しkeysはBA() throws Exception {
            assertThat(sut.get("A"), is("valueA"));
            assertThat(sut.keys.size(), is(2));
            assertThat(sut.keys.get(0), is("B"));
            assertThat(sut.keys.get(1), is("A"));
        }
        
        @Test
        public void get_BでvalueBを返しkeysはAB() throws Exception {
            assertThat(sut.get("B"), is("valueB"));
            assertThat(sut.keys.size(), is(2));
            assertThat(sut.keys.get(0), is("A"));
            assertThat(sut.keys.get(1), is("B"));
        }
        
        @Test
        public void get_Cでnullを返しkeysはAB() throws Exception {
            assertThat(sut.get("C"), is(nullValue()));
            assertThat(sut.keys.size(), is(2));
            assertThat(sut.keys.get(0), is("A"));
            assertThat(sut.keys.get(1), is("B"));
        }
        
        @Test
        public void put_Cでsizeは3_keysはABCとなる() throws Exception {
            String key = "C";
            String item = "valueC";
            sut.put(key, item);
            assertThat(sut.size(), is(3));
            assertThat(sut.keys.size(), is(3));
            assertThat(sut.keys.get(0), is("A"));
            assertThat(sut.keys.get(1), is("B"));
            assertThat(sut.keys.get(2), is("C"));
        }
    }
}
```

### 生成メソッドでのセットアップ

* 暗黙的セットアップでは、複数のテストクラスの共通化処理をセットアップメソッドで行うことはできない
* 共通した共通化処理を独立したクラスのメソッドに抽出するパターン
* 独立したクラスに `static` メソッドとして定義することで、`static` インポートを使うことが出来、可読性を高めることができる
* テストコードでは拡張性はあまり考慮せず、テストコードとして読みやすいかどうかを重視する

##### 生成メソッドによるフィクスチャのセットアップ

```java
public class BookStoreTest {
    @Test
    public void getTotalPrice() throws Exception {
        BookStore sut = new BookStore();
        Book book = Bookオブジェクトの作成_MartinFowlerのRefactoring();
        sut.addToCart(book, 1);
        assertThat(sut.getTotalPrice(), is(4500));
    }
    
    @Test
    public void get_0() throws Exception {
        BookStore sut = new BookStore();
        Book book = Bookオブジェクトの作成_MartinFowloerのRefactoring();
        sut.addToCart(book, 1);
        assertThat(sut.get(0), is(sameInstance(book));
    }
}
```

##### Bookオブジェクトの生成メソッド

```java
public class BookStoreTestHelper {
    public static Book Bookオブジェクトの作成_MartinFowlerのRefactoring() {
        Book book = new Book();
        book.setTitle("Refactoring");
        book.setPrice(4500);
        Author author = new Author();
        author.setFirstName("Martin");
        author.setLastName("Fowler");
        book.setAuthor(author);
        return book;
    }
}
```

### 外部リソースからのセットアップ

* Java ではフィクスチャのセットアップコードは手続き的に記述することが多くなり、宣言的に記述することはしにくいので読み易くない
    * `set` や `put` メソッドを羅列していくと読みづらくなる
* 外部に定義したリソースファイルにテストデータを記述し、生成メソッドなどで読み込む手法が有効
    * YAML
        * シンプルで XML と同程度の表現力があるのでオススメ
    * JSON
        * 複数行テキストの扱いに難があるが、Webサービスなどではよく使われる
    * CSV
        * リスト構造で大量に必要ならば便利
    * Excel
        * リスト構造で大量に必要ならば便利
    * XML
        * XML は複雑なテストデータも表現できるが、記述量が多くなり専用のエディタを使わないとメンテナンスしにくいためオススメしない
        * 利用する場合は外部ツールなどを使いフィクスチャを管理し、リソースファイルは自動生成すると良い

#### YAML を使ったセットアップ

* YAML ではオブジェクトを構造的に宣言できるため、複雑なオブジェクトや多くのプロパティを持つオブジェクトを可読性の高い状態で記述できる
* テストケースとテストデータがそれぞれ独立したファイルに定義されるため、相互参照を行いにくいことが難点

##### YAML によるリソースファイル

```
!!ch07.Book
title: Refactoring
price: 4500
author: !!ch07.Author
  firstName: Martin
  lastName: Fowler
```

##### SnakeYaml による YAML ファイルの読み込み

```java
public class BookStoreYamlTest {
    @Test
    public void getTotalPrice() throws Exception {
        BookStore sut = new BookStore();
        Book book = (Book) new Yaml().load(getClass().getResourceAsStream("book_fixtures.yaml"));
        sut.addToCart(book, 1);
        assertThat(sut.getTotalPrice(), is(4500));
    }
    
    @Test
    public void get_0() throws Exception {
        BookStore sut = new BookStore();
        Book book = (Book) new Yaml().load(getClass().getResourceAsStream("book_fixtures.yaml"));
        sut.addToCart(book, 1);
        assertThat(sut.get(0), is(sameInstance(book)));
    }
}
```

### Java と宣言的記法

匿名インナークラスとインスタンスイニシャライザを使うことで、トリッキーではあるものの宣言的なコードを書くこともできる。
ただし、いくつか制約がある。

* 匿名インナークラスのサブクラスとして定義されるため、`final` クラスでは利用できない
* 厳密には `Book` クラスではなく `Book` クラスのサブクラスとなっているため、クラスローダ関連の問題で悩まされる可能性がある
* フィールドにアクセスする必要が有るため、`private` フィールドや `final` フィールドは利用できない
* サブクラスからのアクセスとなるため、デフォルト（パッケージプライベート）以上の可視性が必要となる

トリッキーではあるが、Java の言語仕様のみで記述しているので、コード補完やコンパイラによる検証、Eclipse によるナビゲーションなどの恩恵が受けられることがメリット。

```java
public static Book Bookオブジェクトの作成_MartinFowlerのRefactoring() {
    return new Book() {
        {
            title = "Refactoring";
            price = 4500;
            author = new Author() {
                {
                    firstName = "Martin";
                    lastName = "Fowler";
                }
            };
        }
    };
}
```

外部リソースを使わずに宣言的なフィクスチャのセットアップを行いたい場合は、Groovy の導入がオススメ。Groovy を Java のテスト用のライブラリとして利用しても、プロダクションコードに全く影響を与えない。ユニットテストのフィクスチャセットアップに限定して利用するならば、学習コストも殆どかからない。

```groovy
class BookStoreGroovyTestHelper {
    static Book Bookオブジェクトの作成_MartinFowlerのRefactoring() {
        new Book(
            title: "Refactoring",
            price: 4500,
            author: new Author(
                firstName: "Martin",
                lastName: "Fowler"
            )
        )
    }
}
```