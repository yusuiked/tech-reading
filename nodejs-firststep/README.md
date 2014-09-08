# 第1章 Node.js の特徴を理解する

省略

# 第2章 Node.js の環境を構築する

ブログにてまとめた。

* http://yukung.hatenablog.com/entry/2014/08/26/003017
* http://yukung.hatenablog.com/entry/2014/08/30/141700

# 第3章 node コマンドと node.js のイベントシステム

## Node.js の対話環境を利用する

node コマンドで REPL が起動する。

```lang-bash
$ node
```

## さまざまな機能が実装された「モジュール」を使う

モジュールを読み込むには、`require` 関数を使う。

```lang-javascript
var http = require('http');
```

また、REPL 内では、`require` 関数を使わなくても、モジュール名を入力するだけでロードされる。

Node.js にはコアモジュールが存在し、様々な機能がビルトインされている。リファレンスは公式サイトを参照する。

Node.js は JavaScript エンジンの v8 がサポートしている ECMAScript 5 で定義された関数やクラスに加えて、いくつかのクラスや関数、オブジェクトが追加されている。これらは特定のモジュールをロードしなくても利用可能。

* `Buffer` クラス
* `setTimeout` 関数
* `clearTimeout` 関数
* `setInterval` 関数
* `clearInterval` 関数
* `require` 関数
* `module` オブジェクト
* `process` オブジェクト

など。

### 非同期処理とコールバック関数

Node.js には、同期的な関数と非同期的な関数がある。Node.js では非同期的な関数が多用され、関数の処理が終わる前に戻り値を返す。そのため、コールバックやイベントといった処理の結果を通知するための仕組みが用意されている。コールバックは、関数の引数としてコールバック関数を登録しておき、処理の完了時にその結果をコールバック関数の引数として与えて実行する仕組み。

Node.js では、実行すべき関数をキューのようなもので管理しており、実行している処理が終了してアイドル状態になった時点で、キュー内に入っている次の関数を実行する。

Node.js はシングルスレッドで動作するため、非同期的な関数を呼び出したあと、すぐに呼び出し元に制御が戻り、その後コールバック関数が実行される。並列処理ではないため、複数の関数が同じ変数にアクセスする「競合」や「ロック」という状況は発生しない。ただし、長い計算や入出力待ちなどで同期的な処理を行ってしまうと、その分処理の実行は止まってしまうため気をつける必要がある。

### Node.js のイベントループ（`EventEmitter` クラス）

非同期的な関数が利用する「イベント」という仕組みは、Node.js の `EventEmitter` クラスが司っており、このクラスの派生クラスを利用して、イベントハンドラーの登録や実行を行いながら処理を実行する。`EventEmitter` クラスの関数には指定したイベントに対応するイベントハンドラー（`Listener`ともいう）を登録してイベントループを実行し、指定されたイベントが発生した時に登録したイベントハンドラーが実行される。

`EventEmitter` クラスが提供するメソッドは、以下の様なものがある。

#### イベントハンドラーを登録する

* `emitter.on`(event, listener)
	* 以下の `addListener` のエイリアス
* `emitter.addListener`(event, listener)
* `emitter.once`(event, listener)
	* 一度だけ実行する

#### イベントハンドラーを削除する

* `emitter.removeListener`

#### イベントを発生させる

* `emitter.emit`
	* 通常これを自分で呼ぶことはなく、内部で Node.js が呼び出す

## Node.js スクリプトを作成／実行する

`node` コマンドの引数にスクリプトを指定する。

Node.js では、基本的に `UTF-8` でファイルを処理するため、他の文字コードでスクリプトを作成してしまうと、文字化けが発生する。改行コードについては、`CR`、`LF`、`CR+LF` のどれでも問題ない。

# npm でモジュールを管理する

JavaScript の言語仕様を定義している ECMAScript ではモジュール化を実現するための機能が定義されておらず、別のファイルに記述されている変数や関数、クラスを直接参照することはできなかった。Node.js では、独自のモジュールシステムを提供している。

### node のモジュール

* 外部モジュールを利用するには、`require` 関数を使い、戻り値の `module.exports` オブジェクトを経由して利用する。なお、`require` 関数は、実行結果をキャッシュしており、同一のモジュールを複数回ロードした場合、モジュール内に記述されているコードは最初の1回目のみ実行される。

```lang-javascript
// モジュール利用側
var hoge = require('./hoge.js');	// 戻り値は module.exports オブジェクト
hoge.something('こんにちは');

// モジュール定義側では、以下のようにすると外部から利用できる
function something(msg) {
	console.log(msg);
}
exports.something = something; // exports オブジェクトのプロパティとして関数を登録
```

`require` 関数が返す `module.exports` の参照そのものを書き換えてしまうと、予期しない挙動をするので注意する。

```lang-javascript
function something(msg) {
	console.log(msg);
}
exports = something;
// これでは、node.js の module.exports プロパティを指す exports 変数を上書きしてしまい、
// require 関数が返す module.exports プロパティを参照できなくなるため、
// 実際に require 関数を呼び出した際は空のオブジェクトが返ってしまうため、呼び出せない
```

`require` 関数の戻り値として特定のオブジェクトを返したい場合は、以下のようにする。

```lang-javascript
function something(msg) {
	console.log(msg);
}
exports = module.exports = something;
// こうすると、require 関数の戻り値として something 関数オブジェクトが返る
// exports 変数が生成される順番は、
// 1. module オブジェクトが node.js によってモジュールごとに作成される
// 2. module オブジェクトの exports プロパティを参照する exports 変数が生成される
// の順番なので、先に module.exports プロパティを書きかえる必要がある。
```

* `module` オブジェクトはモジュールの実行時に自動的に作成されるオブジェクト
* バイナリモジュールは `.node`
* JSON もロードできる
* `node` コマンドが実行したモジュールか、`require` 関数からロードされたモジュール化を判定するには、`require.main` プロパティを見る

#### ディレクトリを対象に `require` 関数を実行

1. 指定されたディレクトリ内の package.json を探し、`main` プロパティに記述されたモジュールを実行する
2. package.json がなければ、指定されたディレクトリ内の `index.js` を実行する
3. `index.js` がなければ、指定されたディレクトリ内の `index.node` を実行する
4. 上記がすべて存在しない場合は、例外発生

#### パスが省略された際の module 探索ルール

1. `node` の標準モジュール（例えば、`http` とか）をロードしようとする
2. ない場合、`require` 関数を呼び出したモジュールと同じディレクトリにある `node_modules` ディレクトリないで指定されたモジュールを探索
3. それもない場合、親のディレクトリの `node_modules` を探索し、以下再帰的に探索する
4. その後、ホームディレクトリの `.node_modules` 、.`node_libraries` を探索する
5. 更に、`node` のコンパイル時に指定したライブラリディレクトリ（通常は `/usr/local/lib/` 以下）を探索
6. 上記がすべて存在しない場合は、例外発生

例として、`/home/foo/bar/hoge.js` 内で `require('foo.js')` と呼ぶと、

1. `/home/foo/bar/node_modules/foo.js`
2. `/home/foo/node_modules/foo.js`
3. `/home/node_modules/foo.js`
4. `/node_modules/foo.js`
5. `~/.node_modules/foo.js`
6. `~/.node_libraries/foo.js`
7. `/usr/local/lib/foo.js`

と探索する。

### モジュールの実行コンテキストを判断する `require.main` プロパティ

`require.main` プロパティを参照することで、そのモジュールが `require` 関数でロードされたものなのか、`node` コマンドで直接実行されたモジュール（main モジュール）なのかを判断できる。

### モジュールが存在するかどうかを実行前にチェックする `require.resolve` メソッド

`require` 関数で実行するモジュールが実際に存在するかどうかは、`require.resolve` メソッドで判断できる。`require.resolve` メソッドは指定されたモジュールを検索し、存在すればそのフルパスを返す。

## パッケージとパッケージマネージャ `npm`

Node.js のパッケージ管理として、`npm` がある。オープンソースのパッケージが多数存在し、自由に利用できる。

`npm` は Web ブラウザからアクセスできる他、`npm` というコマンドラインツールによってパッケージのダウンロードやインストールが可能。

### パッケージをインストールする

* `npm install` or `npm i`

ダウンロードされたパッケージは、`npm` コマンドを実行したディレクトリ内の `node_modules` ディレクトリにインストールされる。

インストールしたパッケージを使う場合は、以下のように記述する。

```lang-javascript
var mysql = require('mysql');
```

`-g` オプション付きで実行すると、グローバルインストールされシステム全体で使えるようになる。インストール先は、Node.js をコンパイルする際に指定したシステムライブラリディレクトリ（通常であれば `/usr/local/lib` ）にインストールされる。

### パッケージの依存関係

`npm` では、インストールしたパッケージ自身が依存する別のパッケージについても、依存関係を推移的に解決してインストールしてくれる。依存関係にあるパッケージはそのパッケージがインストールされたディレクトリ内の `node_module` ディレクトリにインストールされる。

### 実行可能なプログラムを含むパッケージ

単体で実行可能なパッケージが存在する。このようなパッケージの場合は、実行可能なモジュールはパッケージディレクトリ以下の `bin` ディレクトリに格納され、インストールコマンドを実行したディレクトリ以下の `node_module/.bin` ディレクトリにシンボリックリンクが作成される。`-g` オプション付きでインストールした場合、実行可能なモジュールへのシンボリックリンクが `/usr/local/bin` 以下に作成され、フルパスを指定せずにモジュールが実行できるようになる。

```lang-bash
$ nom install less
$ ls
hello.js
node_modules
$ ls node_modules/less/bin
lessc
$ node_modules/.bin/lessc # less のコンパイル実行
```

### パッケージをアンインストールする

* `npm uninstall` or `npm remove` or `npm rm`

## パッケージの構造

パッケージには、モジュールだけでなく、そのパッケージに関する情報（メタデータ）や、ドキュメント、テストスクリプトなども含まれている。

### 例： async パッケージの構造

```lang-bash
async/
├── LICENSE
├── README.md
├── component.json
├── lib
│   └── async.js
└── package.json
```

* package.json
	* パッケージに関する情報を含むJSONファイル
* lib/
	* 本体

この `async` パッケージを利用するには、次のように `require` 関数を指定する。

```lang-javascript
var async = require('async');
```

この `async` パッケージの `package.json` は以下のようになっており、`main` プロパティに記述されている index.js モジュールが実行される。

```lang-javascript
{
  "name": "async",
  "description": "Higher-order functions and common patterns for asynchronous code",
  "main": "./index.js",
  "author": {
    "name": "Caolan McMahon"
  },
  "version": "0.9.0",
  "repository": {
    "type": "git",
    "url": "https://github.com/caolan/async.git"
  },
  "bugs": {
    "url": "https://github.com/caolan/async/issues"
  },
  "licenses": [
    {
      "type": "MIT",
      "url": "https://github.com/caolan/async/raw/master/LICENSE"
    }
  ],
  "devDependencies": {
    "nodeunit": ">0.0.0",
    "uglify-js": "1.2.x",
    "nodelint": ">0.0.0"
  },
  "jam": {
    "main": "lib/async.js",
    "include": [
      "lib/async.js",
      "README.md",
      "LICENSE"
    ]
  },
  "scripts": {
    "test": "nodeunit test/test-async.js"
  },
  "readmeFilename": "README.md",
  "_id": "async@0.9.0",
  "_from": "async@"
}
```

index.js ファイル内では、

```lang-javascript
module.exports = require('./lib/async');
```

として `./lib/async.js` モジュールを実行している。

## C/C++ で作成されたモジュールを利用する

省略

# シンプルな Web アプリケーションを作る

## HTTPサーバを実装した `http.Server` クラス

Node.js のコアモジュールとして、`http` モジュールが提供されており、これを使うことで簡単に HTTP サーバを実装することができる。

HTTP サーバを実装するには `http.Server` クラスを利用する。

サンプルコードは `chap05` に格納した。

# バイナリデータの操作とファイルの入出力

## 文字列と `Buffer`

* `Buffer` オブジェクトは `new` 演算子で生成する
	* 配列や文字列を与えて初期化することもできる
* `fill` メソッドで初期化できる
* `String` クラスのように `length` や配列のインデックス指定で値を取ることができる
* `String` クラスは immutable、`Buffer` クラスは mutable
* `Buffer` クラスには `String` クラスに存在する `indexOf`, `search`, `replace`, `substring` などはないが、`slice` はある。
* `Buffer` と `String` の相互変換には 以下のメソッドやクラスを使う
* `toString` は文字列に変換する
* `write` は既存の `Buffer` に文字列を書き込む
* `StringDecoder` は Node.js のコアモジュールとして提供されており、Buffer が文字の単位として分割されていても、正しく文字列へと変換できる。`Buffer.concat` メソッドよりも実行効率が良い。
* `Buffer` と数値の相互変換は、`read*`, `write*` メソッドを使う。型ごとにメソッドが多数用意されているが、使い方は一緒。
* `Buffer` オブジェクトのコピーは、`copy` メソッドを使う
* `Buffer` クラスの静的メソッドとして、以下のメソッドが提供されている
* `Buffer.isBuffer`
	* `Buffer` オブジェクトかどうかを調べる
* `Buffer.byteLength`
	* バイト長を取得
* `Buffer.concat`
	* バイト列を結合

## ファイルやファイルシステムにアクセスする

`fs` モジュールを使ってファイル入出力処理を行える。`fs` モジュールのメソッドには、大きく同期メソッドと非同期メソッドが提供されており、同期的なメソッドには sync と付いている。

* ファイルからデータを読み出すには、`fs.readFile(filename, [encoding], [callback])` を使う。`Buffer` オブジェクトが処理結果として返る。`callback` 関数には `err`, `data` 引数が与えられる。
* ファイルにデータを書き込むには、`fs.writeFile(filename, data, [encoding], [callback])` を使う。`writeFileSync` の場合は、戻り値は `undefined`。`callback` 関数には `err` 引数が与えられる。
	* ファイルは上書きされる。
* ファイルへのデータ追記は、`fs.appendFile(filename, data, [encoding], [callback])` を使う。

## より柔軟なファイル操作を行う

`fs.readFile` や `fs.writeFile` は、ファイルのオープンとデータの読み書き、クローズがまとめて実行される。
個別に読み書きするには、以下の関数を使う。同じく Sync と付くメソッドは同期メソッド。

* `fs.open(path, flags, [mode], [callback])`
	* ファイルを開く
	* `mode` 引数は、パーミッションを文字列か数値で指定。指定がない場合は `0666` が指定される。
* `fs.close(fd)`
	* ファイルを閉じる
* `fs.write(fd, buffer, offset, length, position, [callback])`
	* ファイルに書き込む
* `fs.read(fd, buffer, offset, length, position, [callback])`
	* ファイルの読み込み

`mode` 引数の `rs`, `rs+` は、ローカルのファイルシステムキャッシュを使用しないため遅い。なので `fs.open` ではなく `fs.openSync` を使うべき。

同期メソッド、非同期メソッドともに、コールバック関数の引数または戻り値にファイルディスクリプタが与えられる。そのため同期メソッドと非同期メソッドを組み合わせて利用することもできる。

読み書き系のメソッドの `position` はファイルの位置、その他の `offset`, `length` は `buffer` に対する指定。

また、非同期書き込みでコールバック関数を指定せずに `fs.write` 関数を実行することはできるが、同じファイルに対してコールバック関数の完了を待たずに連続して実行するのは非推奨。その場合は `fs.writeSync` か `fs.writeStream` を使うべき。

```lang-javascript
var fs = require('fs');
fs.open('hoge.txt', 'w', function (err, fd) {
	var but = new Buffer('apple-orange-grape-peach');
	fs.write(fd, buf, 0, 6, 0);
	fs.write(fd, buf, 6, 7, 6);
	fs.write(fd, buf, 13, 6, 13);
	fs.write(fd, but, 19, 5, 19, function (err, written, buffer) {
		fs.close(fd);	});});
```

### その他の操作

* ファイルの大きさを変更する
	* `fs.truncate(fd, len, [callback]);`
	* 指定したファイルが `len` よりも大きい場合は切り詰められ、`len` よりも小さい場合は0埋めされる
* ファイルバッファをフラッシュする
	* `fs.fsync(fd, [callback]);`
* ファイルの情報を取得する
	* `fs.stat(path, [callback]);`
	* `fs.lstat(path,[callback]);`
		* シンボリックリンクの場合は参照先を取得
	* `fs.fstat(fd, [callback]);`
		* ファイルディスクリプタで操作
	* コールバック関数の引数には、`err`, `stats`(`fs.Stats` オブジェクト) が与えられる。
* ファイルの存在を調べる
	* `fs.exists(path, [callback])`
* ファイルの所有者を変更する
	* `fs.chown(path, uid, god, [callback])`
	* `fs.lchown(path, uid, god, [callback])`
	* `fs.fchown(fd, uid, god, [callback])`
* ファイルのパーミッションを変更する
	* `fs.chmod(path, mode, [callback])`
	* `fs.fchmod(fd, mode, [callback])`
	* `fs.lchmod(path, mode, [callback])`
* ファイルのタイムスタンプを変更する
	* `fs.utimes(path, atime, mtime, [callback])`
	* `fs.futimes(fd, atime, mtime, [callback])`
* シンボリックリンクが参照しているファイルのパスを取得する
	* `fs.readlink(path, [callback])`
* 正規化された絶対パスを取得する
	* `fs.realpath(path, [callback])`
* ファイルやディレクトリのリネーム
	* `fs.rename(path1, path2, [callback])`
* ハードリンクの作成
	* `fs.link(srcpath, distpath, [callback])`
* シンボリックリンクの作成
	* `fs.symlink(link data, path, [type], [callback])`
	* `type` 引数は windows のみ有効
* ファイルの削除
	* `fs.unlink(path, [callback])`
* ディレクトリの削除
	* `fs.rmdir(path, [callback])`
* ディレクトリの作成
	* `fs.mkdir(path, [mode], [callback])`
	* `mode` 引数はパーミッション
* ディレクトリ中のファイル一覧を取得
	* `fs.readdir(path, [callback])`
	* カレントディレクトリや親ディレクトリは含まれない
* ファイルやディレクトリを監視する
	* `fs.watch(filename, [options], [listener])`
	* `options` 引数は、`{ persistent: true/false, interval: number}` のオブジェクトが指定できる
	* `listener` 引数は、`change` イベントに対するイベントハンドラ

## ストリームを使ったファイルアクセス

ストリームは様々な入出力を抽象化して扱えることが特徴。Node.js のストリームは、イベント駆動でデータの読み書きを実行するため、大量のデータに対して効率的に読み書きできる。ストリームには、`Readable Stream` と `Writable Stream` の2種類がある。

### Readable Stream のイベント

* data
* end
* error
* close

### Writable Stream のイベント

* drain
* error
* close
* pipe

`Writable Stream` の `write` メソッドは、書き込みバッファがいっぱいで書き込めなかったときでも、内部で Node.js のキューに格納され、OS側の書き込みバッファが空いたら自動的に転送されるので、再送は不要。

書き込み終わったら、end メソッドで明示的に終了を宣言する。

### 使い方

```lang-javascript
fs.createReadStream(path, [options]);
// options は、flags, encoding, fd, mode, bufferSize, start, end
fs.createWriteStream(path, [options]);
// options は、flags, encoding, mode, start
```

# Node.js アプリケーションのデバッグ方法

## 標準モジュールに含まれるデバッグに有効な関数

### console 関数

* `%d`, `%s`, `%j` でそれぞれ format 出力できる。
* ```console.log(数値だよ %d, 123);```
* `console.log` と `console.info`、`console.error` と `console.warn` は同じ動作をする。
* `console.trace` はスタックトレースを標準エラー出力に出力する。
* `console.time(label)`, `console.timeEnd(label)` は実行時間を測定できる。

### assert モジュール

引数に与えた値を評価して、期待されたものと異なった場合に AssertionError を発生させる。
どのようなAPIがあるかはリファレンス参照。

## Node.js 組み込みのデバッガ

```lang-bash
$ node debug <スクリプト>
```

* `c`/`continue` で継続
* `r`/`run` で実行再開
* `n`/`next` でステップ実行
* `s`/`stepin` でステップイン
* `o`/`stepout` でステップアウト
* `repl` コマンドで REPL を起動
* `watch`(評価する式) でウォッチ
* ロードされているスクリプト一覧は `scripts`
* `sb`/`setBreakpoint(filename, line)` でブレークポイント設定
* `cb`/`clearBreakpoint(filename, line)` でブレークポイント解除
* ソースコード中に `debugger` と埋め込んでおくと、デバッガはこの箇所でプログラムの実行を停止する

### 実行中の Node.js にデバッガを接続する

1. `ps` コマンドでプロセスIDを調べる
2. `kill` コマンドで `SIGUSR1` シグナルを送ると、デバッガエージェントが起動して待ち受ける
3. `node debug --` コマンドでデバッガが起動する

### node-inspector を使う

Node.js の組み込みデバッガの機能をGUIで使えるようにするのが `node-inspector`。

#### 使い方

```lang-bash
$ npm install -g node-inspector
$ node --debug-brk hoge.js
$ node-inspector
 ```
 
# Node.js からデータベースにアクセスする

後回し

