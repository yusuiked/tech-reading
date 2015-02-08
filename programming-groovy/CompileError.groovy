class MyClass {
    // def toString() { "MyClass" } // エラーとなる
    String toString() { "MyClass" }
}
println new MyClass()
// ==>MyClassが出力される
