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

