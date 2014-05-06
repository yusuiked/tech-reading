def foo(Map param) {
    assert param.name == "太郎"
    assert param.age == 25
    assert param.gender == null
}
foo(age:25, name:"太郎")    // 名前付き引数
