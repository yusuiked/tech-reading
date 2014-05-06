class Test {
    int data = 0
    int getData() {
        return 1
    }
}
Test x = new Test()
assert x.data == 1
assert x.@data == 0
