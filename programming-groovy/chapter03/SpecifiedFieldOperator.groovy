class Test {
    int data = 0
    int getData() {
        return 1
    }
}
Test x = new Test()
assert x.data == 1  // プロパティ（getData()）の呼び出し
assert x.@data == 0 // フィールドの直接参照
