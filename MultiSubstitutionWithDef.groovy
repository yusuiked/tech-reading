def (Integer a, Integer b) = [1, 2] // a,bを同時に宣言・初期化
assert a == 1
assert b == 2
def (int c, double d, String e) = [4, 5.5, "abc"]
assert c == 4
assert d == 5.5
assert e == "abc"
def (f, g, h) = [4, 5.5, "abc"] // 型指定を省略
assert f == 4
assert g == 5.5
assert h == "abc"
// (int i, double j, String k) = [4, 5.5, "abc"]   // これはエラー
