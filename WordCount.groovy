// 指定したテキストファイル中の単語の出現回数を一覧表示する
/* これもコメント */
def map = [:]
/* def は方を指定せずに変数などを指定するキーワード。初期値として空のmap([:])をリテラルで与えている。
   マップリテラルの実態は，java.util.LinkedHashMapのインスタンス。
   つまり，map = [a: 123, b: 456] は，
   Map<String, Integer> map = new LinkedHashMap<String, Integer>();
   map.put("a", 123);
   map.put("b", 456);
   と等価になる。
*/
new File(args[0]).eachLine {
/* args は，コマンドライン引数を格納している暗黙に定義された変数で，スクリプトで使用可能。
   args の型は，文字列の配列。
   File クラスのインスタンスに対して，eachLine メソッドに引数としてクロージャを与えている。
   File#eachLine(Closure)は，1.ファイルをオープン，2.そのファイルの内容の各行に対してクロージャに指定した処理を実行する，3.ファイルをクローズする，という処理を行う。
*/
    it.split(/\s+/).each {
    /* it には，繰り返しごとに各行の文字列が渡ってくる。その文字列オブジェクトに対して，java.lang.Stirng#Split()を呼び出している。
       /\s+/ は，/の間は正規表現リテラルとなり，スペースの1つ以上の繰り返しでsplitする，ということを行なっている。
       また，正規表現で一致したオブジェクトに対して，String#each(Closure)を呼び出す。正規表現に一致した文字列オブジェクトがitに代入される。
    */
        map[it] = (map[it]==null) ? 1 : (map[it] + 1)
        /* ここでのitは，外側で定義されているitをオーバーライドして，正規表現に一致した文字列が入っている。
           その文字列をMapのキーとして，null（つまり初めて出現した文字列なら1を初期値として設定して，2回目以降に出現した単語はMapのエントリに1を追加する，という処理を行う。
           map[it]は，Javaでのmap.get(it);と同じ意味。
           map[it] = 1は，Javaでのmap.put(it, 1);と同じ意味。
        */
    }
}
map.entrySet().sort{it.value}.each {
/* mapのエントリーセット(.entrySet)を取り出し，そのvalueでソート(.sort)して，それぞれ(.each)を処理する。という意味になる。*/
    println "${it.value}: [${it.key}]"
    /* ${it.value}のように，${}で囲まれた式は，その式の値が実行時に評価され，評価結果の値が文字列の中に展開される。これをGStringと呼ぶ。もし，GStringを使わないで書くと，
       println it.value + ": [" + it.key + "]"
       となる。
    */
}
