# スタートアップ Gradle

## Groovy 特有の文法

### 文字列

* `'` は Java の String リテラル `"` とほぼ同等
* `"` は GString のリテラルであり、`${}` で動的な内容を埋め込むことができる
    * `${message} です`
    * `$message です` でもOK

### メソッド呼び出し時の括弧省略

```groovy
println('Hello World!')
println 'Hello World'
```

#### 注意点

* 引数がない時は省略できない
* メソッド呼び出しがトップレベルの式でないと省略できない
* メソッド呼び出しがし昨日編にあると省略できない

### def による型指定の省略

```groovy
def name = 'John'
println name.toUpperCase()
```

* `def` は `Object` 型を指定した場合と同等だが、ダックタイピングにより上記のような呼び出しができる

### クロージャ

* Java8 のラムダ式や JavaScript の関数オブジェクトのようなもの
* Gradle はクロージャを多用している

```groovy
def clos1 = { mes -> println "Hi, $mes" }
def clos2 = { println "Hi, $it" }

clos1.call('John')
clos2('Bob')
clos2 'Alice'
```

#### Gradle のタスク定義のサンプル

```groovy
task hello << {
    println 'Hello Gradle world!'
}
// Groovy コードの以下と等価
def clos = { println 'Hello Gradle world!' }
task hello {}
hello.leftShift(clos)
```

# Hello Gradle world

## ビルドスクリプトの作成と実行

ビルドスクリプトのデフォルトエンコーディングは、

* Gradle 2.0 以降は UTF-8
* Gradle 1.x ではプラットフォームのデフォルトエンコーディング

## 組み込みタスク

* `gradle tasks` でタスクの一覧を表示
    * `gradle help --task <タスク名>` でタスクのヘルプを参照できる
* `gradle properties` でビルドスクリプトで定義されているプロパティの一覧を表示

## 主要なコマンドラインオプション

* `-i`, `--info`
    * ログレベルが `INFO` に設定される
* `-s`, `--stacktrace`
    * 例外発生時にユーザー例外部分のみスタックトレースを表示する
* `-S`, `--full-stacktrace`
    * 例外発生時に全てのスタックトレースを表示する
* `-d`, `--debug`
    * ログレベルが `DEBUG` に設定される
* `-q`, `--quiet`
    * ログレベルが `QUIET` に設定される
* `-b`, `--build-file`
    * ビルドスクリプトを指定する

## プロジェクト自動生成とビルド

### Java プロジェクト自動生成

`$ gradle init --type java-library`

### ビルド

`$ gradle build`

### プロキシ経由での実行

`gradle.properties` に以下のように記述し、プロジェクトのルートディレクトリ、または `<HOME>/.gradle` に配置する

```
systemProp.http.proxyHost=www.example.com
systemProp.http.proxyPort=8080
systemProp.https.proxyHost=www.example.com
systemProp.https.proxyPort=8080
```

* Java プロジェクトのビルドとテスト
* War プロジェクトのビルドと実行

### Gradle の便利な機能

#### Gradle デーモン

起動に時間がかかる問題は、JVM を常駐起動させることで解消できる。Gradle にデーモンモードが有りこれを利用する。

`$ gradle --daemon <タスク名>`

と一度起動すると、2度目以降の起動は高速に実行される。

Gradle デーモンはシステムプロパティ `org.gradle.daemon` を `true` に設定することでも有効化できる。環境変数 `GRADLE_OPTS` に以下のように

`GRADLE_OPTS="-Dorg.gradle.daemon=true"`

と設定しておくとデフォルトで有効になる。

また、`gradle.properties` に `org.gradle.dameon=true` と設定しておき、プロジェクトのルートディレクトリまたは、`<HOME>/.gradle` に配置すると有効になる。

#### Gradle ラッパー

Gradle はインストールされていなくても、Gradle 自身をダウンロードして実行する仕組みが備わっている。

1. バージョン管理システムのリポジトリからプロジェクトをチェックアウト
2. `gradlew` コマンドを実行

するだけで Gradle のバイナリが自動的にダウンロードされてそのまま実行できる。