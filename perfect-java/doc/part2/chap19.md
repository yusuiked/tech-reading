# Java EE 概論

## Java EE とは

サーバ系開発のための技術規格を集めたもの。Webアプリ以外の技術規格も含んでいるが、実質上はWebアプリのための技術規格が中心。

以下は Java EE5 に含まれる主な技術規格の一覧。

|規格名|バージョン|説明|
|------|----------|----|
|Java Servlet|2.5|サーブレットAPI。Webアプリの基本API。|
|JavaServer Pages|2.1|いわゆるJSP。Webアプリのビューの基本。|
|JavaServer Pages Standard Tag Library|1.0|いわゆるJSTL。JSPファイル内で使う標準カスタムタグ。|
|JavaServer Faces|1.2|いわゆるJSF。HTMLフォーム処理。|
|Java Persistence API|1.0|ORMのAPI。|
|Enterprise JavaBeans|3.0|いわゆるEJB。Webアプリのコンポーネント化|
|Java API for XML-Based Web Services|1.0|いわゆるJAX-WS。SOAPベースのWebサービス。|

個々の技術規格に個別のバージョンがついていて混乱しがちだが、全体をまとめて Java EE としてバージョンがつく。Java EE 自体は技術規格なので実装とは独立している。Sun 自身も参照実装を配布しているが、Sun 以外も実装を配布している。

## Webアプリケーションサーバ

一般に、Webアプリケーションサーバと呼ばれるミドルウェアは最低限以下の機能を持つ。

* HTTPサーバ
* サーブレットエンジン
* JSPコンパイラ

Java EE準拠のミドルウェアをアプリケーションサーバと呼び、Tomcat のようなサーブレット+JSPエンジンを持つだけのミドルウェアをサーブレットコンテナと呼び分ける流儀もある。

代表的なWebアプリケーションサーバは、以下の一覧。Java EE 準拠の意味は Java EE の技術規格のすべての実装を持つミドルウェアのこと。準拠していないミドルウェアは Java EE に違反している意味ではなく、一部の技術規格だけを実装していることを意味する。

|名称|配布元|説明|
|----|------|----|
|Tomcat|Apache Software Foundation|代表的なオープンソースのサーブレットコンテナ|
|Jetty|Mort Bay Consulting|オープンソースのサーブレットコンテナ。他のアプリケーションサーバの１コンポーネントになっていることも多い|
|GlassFish|Sun|Java EE規格を満たすSun製アプリケーションサーバ。参照実装としての位置づけ（Java EE6 の先行実装など）|
|JBoss AS|RedHat|Java EEにほぼ準拠。商用とオープンソースの両方の配布形態がある。色々なオープンソースソフトを利用|
|WebSphere|IBM|Java EE準拠。商用。|
|WebLogic|Oracle|Java EE準拠。商用。|
|Apache Geronimo|Apache Software Foundation|Java EE準拠。オープンソース。TomcatやOpenEJBなどを寄せ集めてJava EEに準拠|
|Apache TomEE|Apache Software Foundation|Java EE準拠。オープンソース。TomcatベースのJava EE6 Web Profile 対応のアプリケーションサーバ。|
|Resin|Caucho|高速性が特徴のサーブレットコンテナ。|

## Tomcat

Tomcat は Java EEの規格のうちサーブレットとJSPの規格の実装を持つ。サーブレット実装の実体は、サーブレットAPIを提供するクラスライブラリとサーブレットオブジェクトの制御を司るサーブレットエンジン。

JSP実装の実体は、JSPファイルをコンパイルするJSPコンパイラ。JSPで記述したJSPファイルはサーブレットAPIを使うJavaコードに変換される。

Tomcat のJava EE規格のサポート状況は以下のようになっている。

|Tomcat バージョン|サーブレット規格バージョン|JSP規格バージョン|
|-----------------|--------------------------|-----------------|
|6.x|2.5|2.1|
|5.x|2.4|2.0|
|4.x|2.3|1.2|
|3.x|2.2|1.1|

