E# 第６章　テストのコンテキスト

## テストのコンテキストとは？

* テストに関連する内部状態や前提条件を表すもの
* テストの事前条件や状態、テストの入力データ、期待値となるデータなどすべてを含む

## テストケースの整理

* テストコードは似たようなコードを多いため重複が多くなりがち

### テストクラスの構造化

* 多くのテストケースが定義されたテストコードの可読性が低い理由は、全てのテストメソッドを同列に定義しているから
* 何らかの基準でグループ化することで、構造化し可読性を保つ

### テストケースをグループ化する

大きくは以下の２つの方針が挙げられる。

* テストケースで検証する操作（メソッド）単位でグループ化する
* テストケースを共通の初期化処理を含むものでグループ化する

各グループを別のテストクラスに定義することを前提とすると、操作（メソッド）単位のグループ化では、初期化処理を共通化できない。初期化処理を含むものでグループ化することで、別のテストクラスに切り出したとしても共通化が可能となる。こうすることで、テストメソッドの見通しが良くなり、効率よくテストコードの重複を減らすことができる。

ただし、複数のテストクラスに分割しようとすると、テストクラスを複数定義する必要があるため、テストクラスの命名規則の観点で望ましくない。そのため `Enclosed` テストランナーを利用したテストクラスの構造化を行うとよい。

### Enclosed によるテストクラスの構造化

考え方としては以下。

* 外側のテストクラス（トップレベルクラス）に、テストランナーとして `Enclosed.class` を設定する
* この時、標準的なテストクラスの命名規約にしたがって「テスト対象クラス名＋Test」とする
* ネストしたクラスの名前は、グループ化するテストケースの前後条件（コンテキスト）を表す名前とする
* ３階層以上の構造化は避ける。これが必要になるケースはテスト対象クラスの責務が大きすぎる可能性がある

```java
@RunWith(Enclosed.class)
public class ItemStockTest {
	public static class 空の場合 {
		ItemStock sut;
		
		@Before
		public void setUp() throws Exception {
			sut = new ItemStockImpl();
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
			sut = new ItemStockImpl();
			sut.add("A", 1);
		}
		
		@Test
		public void size_Aが1を返す() throws Exception {
			assertThat(sut.size("A"), is(1));
		}
		
		@Test
		public void contains_Aはtrueを返す() throws Exception {
			assertThat(sut.contains("A"), is(true));
		}
	}
}
```

## コンテキストのパターン

### 共通のデータに着目する

* データベース接続を行うクラスのユニットテストなど
* テストの実行前にデータベースを特定の状態に設定する必要がある
	* テーブルが空の時
	* 10件のデータがある時
* 取得／更新／削除など操作が似通うため、同じデータベースの状態に対し各メソッドのテストを行うほうがわかりやすくなる
* 都度テストケースでデータベースの初期化をするのではなく、共通化する

```java
@RunWith(Enclosed.class)
public class UserDaoTest {
	public static class テーブルが空の場合 {
		UserDao sut;
		
		@Before
		public void setUp() throws Exception {
			DbUtils.drop("users");
			sut = new UserDao();
		}
		
		@Test
		public void getListで0件取得できる() throws Exception {
			List<User> actual = sut.getList();
			assertThat(actual, is(notNullValue());
			assertThat(actual.size(), is(0));
		}
	}
	public static class テーブルにサンプルデータが100件含まれる場合 {
		UserDao sut;
		
		@Before
		public void setUp() throws Exception {
			DbUtils.drop("users");
			DbUtils.insert("users", getClass().getResource("users.yaml"));
			sut = new UserDao();
		}
		
		@Test
		public void getListで100件取得できる() throws Exception {
			List<User> actual = sut.getList();
			assertThat(actual, is(notNullValue()));
			assertThat(actual.size(), is(100));
		}
	}
}

```

### 共通の状態に着目する

* テスト対象クラスが状態を持つ場合、事前処理として行われるテスト対象オブジェクトへの操作が重要
* 事前処理の捜査の結果、特定の状態になる
* テスト対象オブジェクトの状態を変化させる操作をテストメソッドで実行するのではなく、その操作を共通処理として切り出す

```java
@RunWith(Enclosed.class)
public class ArrayListEnclosedTest {

	public static class listに1件追加してある場合 {
		UserDao sut;
		
		@Before
		public void setUp() throws Exception {
			sut = new ArrayList<String>();
			sut.add("A");
		}
		
		@Test
		public void sizeは1を返す() throws Exception {
			int actual = sut.size();
			assertThat(actual, is(1));
		}
	}
	public static class listに2件追加してある場合 {
		private ArrayList<String> sut;
		
		@Before
		public void setUp() throws Exception {
			sut = new ArrayList<String>();
			sut.add("A");
			sut.add("B");
		}
		
		@Test
		public void sizeは2を返す() throws Exception {
			int actual = sut.size();
			assertThat(actual, is(2));
		}
	}
}

```


### コンストラクタのテストを分ける

* オブジェクトの生成に関するテストは他のテストとは性質が違い、テスト対象オブジェクトの生成自体がテスト内容
* 他のテストケースとは分けて、「初期状態を検証する特別なコンテキスト」と考える

```java
@RunWith(Enclosed.class)
public class UserTest {
	public static class インスタンス化テスト {
		@Test
		public void デフォルトコンストラクタ() throws Exception {
			User instance = new User();
			assertThat(instance.getName(), is("nobody"));
			assertThat(instance.isAdmin(), is(false));
		}
	}
}
```

## テストクラスを横断する共通処理

* Enclosed テストランナーでも、異なるテストクラスをまたがった共通処理は整理できない
* 基底クラスを継承したテストクラスで共通化しがちだが、避けるべき
	* 安易な継承はメンテナンス性を下げるだけ
	* 巨大な機能を持った基底クラスになってゆく
* ユーティリティクラスなどに共通処理を切り出し、テストクラスからはユーティリティクラスを利用するほうがよい

#### 複雑な共通処理が記述されたテストクラス

```java
public class UserDaoTest {
	private UserDao sut;
	private InMemoryDB db;
	
	@Before
	public void setUp() throws Exception {
		db = new InMemoryDB();
		db.start();
		sut = new UserDao();
	}
	
	@After
	public void tearDown() throws Exception {
		db.shutdownNow();
	}
	
	@Test
	public void getListは0件を返す() throws Exception {
		// 省略
	}
}
```

このような、テストとは直接関連しない共通処理を抽出する場合は、`Rule` を使うと便利。

#### Rule を使った共通処理の抽出

```java
public class UserDaoTest {
	private UserDao sut;
	
	@Rule
	public InMemoryDBRule db = new InMemoryDBRule();
	
	@Before
	public void setUp() throws Exception {
		sut = new UserDao();
	}
	
	@Test
	public void getListは0件を返す() throws Exception {
		// 省略
	}
}
```