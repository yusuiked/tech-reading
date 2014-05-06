/*
 * Groovyでは，引数がオーバーロード定義されている場合は，
 * 引数の動的な型（実行時に代入されているインスタンスの型）によって，
 * 実行されるメソッドが変わる
 */
def hello(String s) {
    println "hello(String) -> $s"
}
def hello(Integer s) {
    println "hello(Integer) -> $s"
}
Object x = 3
Object y = "ABC"
hello(x)    // ==> "hello(Integer)-> 3"が表示される.
hello(y)    // ==> "hello(String)-> ABC"が表示される.
