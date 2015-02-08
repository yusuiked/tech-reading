// リスト3.19 defによる型指定の省略
def foo = 3
def bar() {} // メソッドの場合は更に def も省略できる

// リスト3.20 メソッド引数における型指定の省略
def sayHello1(String msg) {}    // 型指定を省略しない
def sayHello2(def msg) {}       // 型指定を省略
def sayHello3(msg) {}           // defキーワードも省略

// リスト3.21 型を省略した場合
def x

// リスト3.22 型を指定した場合
// Integer x    // 型を指定すると実行時の値代入時にエラー
x = 3       // Integer型を代入
x = "hello" // String型を代入
