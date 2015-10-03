# Index

* 1.7 pom.xml の構成
* 1.8 ビルドライフサイクル
* 1.9 ビルドプロファイル
* 2.1 ビルドの自動化
* 2.2 プロジェクトをモジュール化する
* 2.5 テスト駆動開発を設定する
* 2.6 受入テストを自動化する

# 1.7 pom.xml の構成

pom.xml の構成要素を箇条書き。

## バージョン

* modelVersion

## 基本要素

* groupId
* artifactId
* version
* packaging
* dependencies
* parent
* dependencyManagement
* modules
* properties

## ビルド設定

* build
* reporting

## プロジェクトメタデータ

* name
* description
* url
* inceptionYear
* licenses
* organization
* developers
* contributors

## 環境設定

* issueManagement
* ciManagement
* mailingLists
* scm
* prerequisites
* repositories
* pluginRepositories
* distributionManagement
* profiles

# 1.8 ビルドライフサイクル

## デフォルトのビルドサイクル

* default
* clean
* site

## default ライフサイクルの代表的なフェーズ

* validate
* compile
* test
* package
* integration-test
* verify
* install
* deploy

それぞれ、先行するビルドフェーズが逐次実行される。

## clean ライフサイクルの代表的なフェーズ

* pre-clean
* clean
* post-clean

## site ライフサイクルの代表的なフェーズ

* pre-site
* site
* post-site
* site-deploy

ビルドライフサイクルの個々のフェーズは、Maven プラグインになっている。

# 1.9 ビルドプロファイル

## 環境依存の設定を使いたい場合

* ファイルシステムの特定の箇所を参照したい
* OSが違う
* 環境ごとにデータベースの接続先が違う

などの場合に使う。
以下の様な方法がある。

* コマンドラインによる明示的な起動
* Maven の設定による起動
* 環境固有の起動

コマンドラインによる明示的な起動の場合は、-P オプションを使ってプロファイルのIDを指定する。カンマ区切りで複数してでき、指定したプロファイルのみ実行される。
Maven の設定による起動の場合は、<activeProfiles> セクションで指定されているプロファイルがアクティブになる。

    <settings>
        <activeProfiles>
            <activeProfile>id</activeProfile>
        </activeProfiles>
    </settings>

毎回デフォルトで起動されるので、コマンドラインで明示的に指定する必要はない。
環境固有の起動の場合は、POMのプロファイル宣言で直接定義する。

    <profiles>
        <profile>
            <activation>
                <property>
                    <name>environment</name>
                    <value>development</value>
                </property>
            </activation>
        </profile>
    </profiles>

コマンド指定時に、`mvn groupId:artifactId:goal -Denvironment=developement` などと指定する。

# 2.1 ビルドの自動化

## 依存関係管理のスコープ

* compile
    * デフォルトスコープ。コンパイル、実行、テストのいずれでも必要になる依存ライブラリ。
* provided
    * JDKまたは環境が実行時に提供してくれると考えて良い依存ライブラリ。
* runtime
    * 実行時に必要な依存ライブラリで、実行時クラスパスで指定される。
* test
    * テストをコンパイル、実行するために必要な依存ライブラリ。
* system
    * システムによって提供され常に参照可能だが、Jarのパスを<systemPath>要素で指定しないといけない。
* import
    * 子が親の<dependencyManagement>要素経由で依存ライブラリをインポートする。

## Dependency プラグインの主なゴール

* mvn dependency:analyze
    * 使用、非使用、宣言済み、未宣言を分析
* mvn dependency:resolve
    * すべての依存関係を解決する
* mvn dependency:resolve-plugins
    * 全てのプラグインを解決する
* mvn dependency:tree
    * 依存関係をツリー表示する

# 2.2 プロジェクトをモジュール化する

* 親の pom.xml の packaging を pom にする
* 親ディレクトリでプロジェクトを作成する
* 子プロジェクトの pom.xml に parent 要素で親プロジェクトが指定

## マルチモジュールプロジェクトの依存性管理

* 依存関係は親POMファイルで定義でき、必要なときには子POMファイルで継承できる。
* 1つのファイルで定義出来れば、依存関係のバージョン管理が単純になる。

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>5.1.2</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

と親のPOMに記述し、

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>

と子のPOMにはスタブの依存関係定義だけをインクルードすれば良い。
複数の小モジュールが同じ依存関係を持っていても、バージョンの衝突は起きない。
依存関係スコープとタイプは、それぞれデフォルトで compile と jar になる。

## system スコープの依存アーティファクト

リポジトリでは検索されない。JAR のパスを指定する必要がある。

    <systemPath>${java.home}/../lib/tools.jar</systemPath>

しかし、system スコープの依存情報は使わないほうが良い。これを使うと、Apache Maven の依存関係管理の意味が根本から覆されてしまうため。
ディベロッパーがSCMからコードのクローンを取り出し、Apache Maven コマンドを実行できるようにすることが望ましい。

# 2.5 テスト駆動開発を設定する

コマンドラインで test ビルドフェーズを実行すると、test フェーズとテストを実行するために必要なすべてのフェーズが実行される。
Maven Surefire プラグインは、ビルドライフサイクルの test フェーズにバインドされた test ゴールを持っていて、（Surefire は標準で組み込まれたプラグイン）これを使うと、<project_base_dir>/src/test/java 以下のディレクトリに含まれていて、ファイル名が次のパターンにマッチする全てのテストが実行される。

* **/Test*.java
* **/*Test.java
* **/*TestCase.java

テストが１つでも失敗したら、ビルドは失敗となる。target/surefire-reports にも、XMLのテスト結果が出力される。

# 2.6 受入テストを自動化する

selenium-maven-plugin と、jetty-maven-plugin を使って jetty サーバーにJSPコンテンツを動作させ、Selenium サーバーのアクセスを自動で行なって、End-to-End テストを自動化する。

* integration-test フェーズの前後に、jetty の起動と停止、selenium サーバの起動と停止を行うようにプラグインの execution 設定を行う。
** pre-integration-test, post-integration-test フェーズに、上記の起動・停止設定を差しこむようにする。
* その際、通常の test フェーズのテストが実行されてしまうと、サーバの起動の前にテストが走ってビルドが失敗してしまうので、ユニットテストはスキップするように設定する。
