(a, b) = [1, 2]
assert a == 1
assert b == 2

def foo() {
    [3, 4]
}
(c, d) = foo()  // メソッドの戻り値がリスト
assert c == 3
assert d == 4

(e, f) = [1, 2, 3, 4]
assert e == 1
assert f == 2

(g, h, i) = [1, 2]
assert g == 1
assert h ==2
assert i == null
