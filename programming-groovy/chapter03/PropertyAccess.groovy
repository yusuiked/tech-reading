class MyClass {
    def name
    String getName() { "[" + name + "]" }
    void SetName(String s) { name = s }
}

MyClass c = new MyClass()
c.name = "yukung"             //セッターの呼び出し
assert c.name == "[yukung]"   //ゲッターの呼び出し

date = new Date()
println date.time   // date.getTime()と等価
println System.env.PATH // System.getenv().get('PATH')と等価
