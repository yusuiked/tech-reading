# 第9章　ルール

## ルールとは

* 何をテストするのか（テストデータ）と、どうテストするのか（テストランナー）は分離されている
* ユニットテストの実行方法をカスタマイズしたい場合に少し面倒
* それを解消するために、プラグインのようにユニットテストを拡張できる仕組みとして**ルール**が JUnit 4.7 で追加された
* ルールを使うことで、共通で使う処理を独立したクラスに定義できる
* テスト実行時のメタデータにもアクセスできる

### ルールの宣言

* `@Rule` アノテーションを付与した `public` フィールドを定義する
* ルールとして定義するクラスは、`org.junit.rules.TestRule` インタフェースを実装したクラス
* フィールドの宣言と同時にインスタンスを作成するか、コンストラクタでインスタンスを生成する必要がある

```java
public class TimeoutExampleTest {
  @Rule
  public Timeout timeout = new Timeout(100);
  @Test
  public void 長い時間がかかるかもしれないテスト() throws Exception {
    doLongTask();
  }
}
```

### ルールの仕組み

* ルールで定義された拡張処理は、`setUp()` メソッドなどと同様にテストメソッドの実行ごとに行われる
* `org.junit.rules.ClassRule` アノテーションでルールを宣言すると、`BeforeClass` アノテーションと同様にテストクラスごとに拡張処理を行うことができる
* `Timeout` クラスなど、汎用的に利用できるルールを標準でいくつか提供している
* プロジェクトごとに必要なユニットテストの共通処理は、カスタムルールとして簡単に作成できる
* カスタムルールを定義することで、共通処理の再利用性が高まる

### 複数のルールの宣言

* 複数のルールを定義することもできるが、実行順序を制御することはできない
* その場合は `RuleChain` を使う

```java
public class RuleExampleTest {
  @Rule
  public TestName testName = new TestName();
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void test() throws Exception {
  }
}
```

## JUnit が提供するルール

| ルール | 概要 |
| ----- | ---- |
| TemporaryFolder | テンポラリフォルダの作成と開放を行うルール |
| ExternalResource | 外部リソースを扱うカスタムルールを定義するための基底クラス |
| Verifier | テスト後の事後条件を検証するルール |
| ErrorCollector | テスト時の例外をカスタマイズするルール |
| ExpectedException | 詳細な例外に関する検証を行うルール |
| Timeout | テスト時のタイムアウトを制御するルール |
| TestWatcher | テストの実行時の記録を行うルール |
| TestName | 実行中のテストメソッド名を参照できるルール |

自身でカスタムルールを作成する場合、これらのソースコードは参考になる。

### TemporaryFolder 一時ファイルを扱う

* テストの実行ごとにテンポラリフォルダの作成を行い、テスト実行後に作成したテンポラリフォルダを削除する
* `TemporaryFolder` クラスのメソッドを使うと、テンポラリフォルダでのファイルやフォルダの作成が簡単に行える
* ファイルシステムを扱うテストで、テストの独立性を高めるためにテンポラリフォルダを使うとよい

```java
public class TemporaryFolderExampleTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void mkDefaultFilesで2つのファイルが作成される() throws Exception {
        File folder = temporaryFolder.getRoot();
        TemporaryFolderExample.mkDefaultFiles(folder);
        String[] actualFiles = folder.list();
        Arrays.sort(actualFiles);
        assertThat(actualFiles.length, is(2));
        assertThat(actualFiles[0], is("UnitTest"));
        assertThat(actualFiles[1], is("readme.txt"));
    }
}
```

#### TemporaryFolder の拡張

* テンポラリフォルダの中に特定のサブフォルダを作成したい場合は、`TemporaryFolder` のサブクラスを作ると良い
* `before` / `after` メソッドをオーバーライドすると、処理を書きかえることができる
* 作成したサブフォルダは、ルールのフィールドに保持することで利用できる

```java
public class SpecificTemporaryFolder extends TemporaryFolder {
    public File srcFolder;
    public File binFolder;

    @Override
    protected void before() throws Throwable {
        super.before();
        srcFolder = newFolder("src");
        binFolder = newFolder("bin");
    }
}
```

### ExternalResource 外部リソースを扱う

* テストの実行前に必要なリソースを準備し、テストの実行後にリソースを開放するルール
* `ExternalResource` は抽象クラスなので、具体的に管理するリソースはサブクラスで定義する
* データベース、ソケット、組み込みサーバなどを扱うテストを行う時に便利なルール
* `before` / `after` メソッドをオーバーライドして、起動処理と開放処理を記述する
* 起動と解放の実行コストが大きい場合は、 `@ClassRule` アノテーションで1度だけ行うこともできる
* `temporaryFolder` クラスは、`ExternalResource` のサブクラス

### Verifier 事後検証を行う

* テストの最終局面で共通した検証を行いたい場合に使用する
* `Verifier` クラスは抽象クラスなので、要件に沿って `vefiry()` メソッドをオーバーライドする
* 事後条件の検証は後処理メソッド `@After` でもできるが、検証が複雑であったり、複数のテストクラスで横断した検証をしたい場合hは便利
* `Verifier` による検証は、`@After` の後処理よりもさらに後で実行される

```java
public class VerifierExampleTest {
    @Rule
    public Verifier verifier = new Verifier() {
        @Override
        protected void verify() throws Throwable {
            assertThat("value should be 0.", sut.value, is(0));
        }
    };
    VerifierExample sut;

    @Before
    public void setUp() throws Exception {
        sut = new VerifierExample();
    }

    @After
    public void tearDown() throws Exception {
        // do nothing
    }

    @Test
    public void getValueで計算結果を取得する() throws Exception {
        sut.set(200);
        sut.add(100);
        sut.minus(50);
        int actual = sut.getValue();
        assertThat(actual, is(250));
    }
}
```

### ErrorCollector エラーを収集する

* アサーションの失敗やエラーが発生した場合でも、テストを継続して実行することのできる仕組みが提供される
* テストが失敗したという情報は、`ErrorCollector` オブジェクトに蓄積され、テストを最後まで実行した後に評価される
* `checkThat()` メソッドを利用すると、検証中に期待しない結果でもテストが失敗とならず、最後まで検証が行われて、期待しない結果の項目を知ることができる
* `ErrorCollector` クラスは `Verifier` クラスのサブクラス

```java
@RunWith(Enclosed.class)
public class ShopInfoTest {
    public static class インスタンス化テスト {
        @Rule
        public ErrorCollector errorCollector = new ErrorCollector();

        @Test
        public void デフォルトコンストラクタ() throws Exception {
            // Exercise
            ShopInfo instance = new ShopInfo();
            // Verify
            errorCollector.checkThat(instance, is(notNullValue()));
            errorCollector.checkThat(instance.id, is(nullValue()));
            errorCollector.checkThat(instance.name, is(""));    // fail
            errorCollector.checkThat(instance.address, is("")); // fail
            errorCollector.checkThat(instance.url, is(nullValue()));
        }
    }
}
```

### ExpectedException 詳細な例外を扱う

* 送出された例外を詳細に検証するためのルール
* 通常例外の検証は、`@Test(expected=)` で指定するが、例外メッセージなどの詳細な検証はできない
  * 例外オブジェクトの参照を取得して検証用のコードを書く必要があるが、読みにくいコードになる

```java
@Test(expected = IllegalArgumentException.class)
public void 例外の発生を検証する標準的なテスト() throws Exception {
  throw new IllegalArgumentException();
}
```

```java
@Test
public void 例外の発生とメッセージを検証する標準的なテスト() throws Exception {
  try {
    throwNewIllegalArgumentException();
    fail("例外が発生しない");
  } catch (IllegalArgumentException e) {
    assertThat(e.getMessage(), is("argument is null."));
  }
}
```

* `ExpectedException` クラスを利用すると、詳細な検証ができる

```java
public class ExpectedExceptionExampleTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void 例外の発生とメッセージを検証するテスト() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("argument is null.");
        throw new IllegalArgumentException("argument is null.");
    }
}
```

### Timeout テストのタイムアウトを検証する

* テストケースのタイムアウトを検証する
* テストの実行時間が指定された値を超えると、テストは自動的に失敗となる
* タイムアウトは JUnit の標準的な機能としても提供されている
  * `@Test(timeout = 100L)`
* `Timeout` クラスを利用すると、テストクラス内のすべてのテストメソッドにタイムアウトが設定される

```java
public class TimeoutTest {
  @Test(timeout = 100L)
  public void アノテーションを使ったタイムアウト() throws Exception {
    doLongTask();
  }
}
```

```java
public class TimeoutExampleTest {
    @Rule
    public Timeout timeout = new Timeout(100);

    @Test
    public void 長い時間がかかるかもしれないテスト() throws Exception {
        doLongTask();
    }

    private void doLongTask() throws InterruptedException {
        Thread.sleep(1000L);
    }
}
```

### TestWatcher テストの実行を監視する

* テストの実行を監視するルール
* テスト実行時の様々なタイミングで追加の処理を実行できる
* `TestWatcher` は抽象クラスで、サブクラスで必要なメソッドをオーバーライドする

```java
public class TestWatcherExampleTest {
    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            Logger.getAnonymousLogger().info("succeeded:" + description.getMethodName());
        }

        @Override
        protected void failed(Throwable e, Description description) {
            Logger.getAnonymousLogger().info("failed:" + description.getMethodName());
        }

        @Override
        protected void starting(Description description) {
            Logger.getAnonymousLogger().info("start:" + description.getMethodName());
        }

        @Override
        protected void finished(Description description) {
            Logger.getAnonymousLogger().info("finished:" + description.getMethodName());
        }
    };

    @Test
    public void 成功するテスト() throws Exception {
    }

    @Test
    public void 失敗するテスト() throws Exception {
        fail("NG");
    }
}
```

### TestName テスト名を扱う

* テストメソッド内で実行中のテストメソッドを取得するためのルール
* テストメソッド名をテストケースの概要とすることが多いため、テストメソッド名は有益な情報を含んでいる

```java
public class TestNameExampleTest {
    @Rule
    public TestName testName = new TestName();

    @Test
    public void テスト名() throws Exception {
        fail(testName.getMethodName() + " is unimplements yet.");
    }
}
```

## カスタムルールの作成

* ルールは簡単にカスタムルールが作成できる
* `org.junit.rules.TestRule` インタフェースを実装するか、既存のルールのサブクラスを作成する

### TestRule インタフェース

* `TestRule` インタフェースには次のメソッドが定義されている

```java
Statement apply(Statement base, Description description);
```

* `org.junit.runners.model.Statement` オブジェクトは、テストの実行を制御するオブジェクト
* `Statement` クラスに定義された `evaluate()` メソッドが呼び出されるとテストが実行される
* `evaluate()` メソッドを呼び出すと、次のようにテストが実行される
  1. テストクラスのインスタンスを生成する
  2. `Before` アノテーションの付与されたメソッドを実行する（事前処理）
  3. テストメソッドを実行する
  4. `After` アノテーションの付与されたメソッドを実行する（事後処理）
* `org.junit.runner.Description` オブジェクトは、テストケースのメタ情報を保持するオブジェクト
* テストクラスの名前やテストメソッドの名前、付与されたアノテーションなどを取得できる
* `apply` メソッドの戻り値は `Statement` 型
* ルールの一般的な実装では、引数として渡された `Statement` オブジェクトのプロキシオブジェクトを生成して返す
  * オリジナルの `evaluate` メソッドを実行する前後に独自の処理を行う `Statement` オブジェクトを生成する

### 事前条件をチェックするカスタムルール

```java
public abstract class PreCondition implements TestRule {
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                verify();
                base.evaluate();
            }
        };
    }

    protected abstract void verify() throws Throwable;
}
```

### OSに依存したテストを行うカスタムルール

```java
public class OSDependentTest {
    @Rule
    public OSDependent osDependent = new OSDependent();

    @Test
    @RunOn(RunOn.OS.WINDOWS)
    public void onlyWindows() throws Exception {
        System.out.println("test: onlyWindows");
        assertThat(File.separator, is("\\"));
    }

    @Test
    @RunOn(RunOn.OS.MAC)
    public void onlyMac() throws Exception {
        System.out.println("test: onlyMac");
        assertThat(File.separator, is("/"));
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RunOn {
    public enum OS {
        WINDOWS, MAC, LINUX
    }
    public OS value();
}

public class OSDependent implements TestRule {
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RunOn env = description.getAnnotation(RunOn.class);
                if (env == null || canEvaluate(env.value())) {
                    base.evaluate();
                } else {
                    // don't evaluate
                }
            }
        };
    }

    private boolean canEvaluate(RunOn.OS os) {
        String osName = System.getProperty("os.name");
        if (osName == null) return false;
        if (os == RunOn.OS.WINDOWS && osName.startsWith("Windows")) return true;
        if (os == RunOn.OS.MAC && osName.startsWith("Mac OS X")) return true;
        if (os == RunOn.OS.LINUX && osName.startsWith("Linux")) return true;
        return false;
    }
}
```

## RuleChain によるルールの連鎖

* ルールを特定の順序で制御させたいケース
  * アプリケーションサーバより前にデータベースサーバを起動したい
* それぞれのルールはカスタムルールで簡単に定義できるが、順序は制御出来ない
* その場合に、`org.junit.rules.RuleChain` クラスを利用する

```java
public class RuleChainExampleTest {
    @Rule
    public RuleChain ruleChain = RuleChain.outerRule(new DBServer()).around(new AppServer());

    @Test
    public void テスト() throws Exception {
    }

    private class DBServer extends ExternalResource {
        @Override
        protected void before() throws Throwable {
            System.out.println("Start DB");
        }

        @Override
        protected void after() {
            System.out.println("Shutdown DB");
        }
    }

    private class AppServer extends ExternalResource {
        @Override
        protected void before() throws Throwable {
            System.out.println("Start App");
        }

        @Override
        protected void after() {
            System.out.println("Shutdown App");
        }
    }
}
```

## ClassRule によるテストクラス単位でのルールの適用

* ユニットテストでは原則的にテストケースごとに事前処理や事後処理を行う
* データベースサーバの起動や停止など、テストケースごとに処理を行うとコストが大きい場合がある
* `@Before/@After` の代わりに `@BeforeClass/@AfterClass` を使う
* ルールでも同様に、`@Rule` アノテーションの代わりに `@ClassRule` アノテーションを使うことで、テストクラスごとにルールを適用できる
* `TestRule` インタフェースを実装したクラスをテストクラスの `public` フィールドに持たせるが、`@ClassRule` の場合は `static` フィールドである必要がある

```java
public class ClassRuleExampleTest {
    @ClassRule
    public static ExternalServer externalServer = new ExternalServer();

    @Test
    public void test01() throws Exception {
        System.out.println("test01");
    }

    @Test
    public void test02() throws Exception {
        System.out.println("test02");
    }

    static class ExternalServer extends ExternalResource {
        @Override
        protected void before() throws Throwable {
            System.out.println("connect");
        }

        @Override
        protected void after() {
            System.out.println("disconnect");
        }
    }
}
```

```
connect
test01
test02
disconnect
```

### JUnit のバージョンとルール

* JUnit4.9 から `MethodRule` インタフェースが非推奨となり、`TestRule` インタフェースを使用するように変更
* `TestWatchman` クラスが非推奨となり、`TestWatcher` クラスが追加
* JUnit4.9 で `@ClassRule` アノテーションが追加
* JUnit4.10 で `RuleChain` クラスが追加
