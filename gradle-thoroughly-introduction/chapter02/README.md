# インストールと設定

* JDK6 以上が必要
* GVM を使ってインストールする（この場合は環境変数の設定は自動）か、バイナリをダウンロードして `PATH` を通す
* プロキシ環境で GVM を使う場合は、環境変数 `http_proxy` を設定して `curl` コマンドにプロキシを認識させる
    * `export http_proxy=http://<プロキシのホスト名>:<ポート番号>/`
* GVM は `JAVA_HOME` を参照する
* Gradle を実行するには `gradle` コマンドへパスが通っていること、`java` コマンドが実行できることが必要。具体的には以下の順で `java` コマンドを探す。
    1. `java` コマンドが実行パスに含まれているか
    2. 環境変数 `JAVA_HOME` で指定されたパスが JDK のルートディレクトリを指しており、`<JAVA_HOME>/bin/java` が実行可能か
* `JAVA_OPTS` の設定は、JDK の標準機能ではないが、JavaVM 全体に影響するオプションを設定できる
    * `JAVA_OPTS=-Dfile.encoding=UTF-8` を設定しておくと、Java ソースコードなどを UTF-8 で読む
* `GRADLE_OPTS` の設定は、Gradle のみに設定するオプション
    * `GRADLE_OPTS=-Xmx1024m` などで Gradle のヒープサイズを設定できる
