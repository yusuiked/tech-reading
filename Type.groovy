def sayHello1(String msg) {}    // 型指定を省略しない
def sayHello2(def msg) {}       // 型指定を省略
def sayHello3(msg) {}           // defキーワードも省略

def x
// Integer x    // 型を指定すると実行時の値代入時にエラー
x = 3       // Integer型を代入
x = "hello" // String型を代入
