Closure clos1 = { println "Hello closure!" }
assert clos1 instanceof groovy.lang.Closure

// リスト3.36 クロージャの呼び出し
clos1.call()    // (A) ==> "Hello closure!"が表示される
clos1() // (B) ==> "Hello closure!"が表示される

// リスト3.37 クロージャの引数
Closure clos2 = { target -> println "Hello $target!" } // (C)
clos2.call("Groovy")    // ==> "Hello Groovy!"が出力される

// リスト3.38 暗黙の引数itを取るクロージャ
Closure clos4 = { println "Hello $it!" } // (D)
clos4.call('Grails')    // ==> Hello Grails!が表示される
clos4.call()    // ==> Hello null!が表示される（itのデフォルト値はnull）

// リスト3.39 引数を取らないクロージャ
Closure clos5 = { -> println "Hello World!" }   // (E)
clos5.call()    // ==> Hello World!が表示される
try {
    clos5.call('a')
    assert false
} catch(MissingMethodException) {
    assert true
    println "==> MissingMethodException: No signature of method"
}

// リスト3.40 クロージャの引数の型指定
Closure clos6 = { String s, int i -> println "s=$s, i=$i" }
clos6.call('Hello', 3)  // ==> s=Hello, i=3 が表示される
