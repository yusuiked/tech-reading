class MyClass {
    String name
    int age
}
def c = new MyClass(name:"太郎",age:25) // Mapでコンストラクタを呼び出す
assert c.name == "太郎"
assert c.age == 25
println c.dump()
