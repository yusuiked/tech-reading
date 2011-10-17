class MyClass {
    String name
    int age
}
def c = new MyClass(name:"太郎",age:25) // Mapでコンストラクタを呼び出す
println c.dump()
