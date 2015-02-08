def atLeast3 = Math.&max.curry(3)
// Math#maxメソッドの引数の一部を3に確定したクロージャを返すのが Closure#curry()メソッド
// 3より大きければその値、３以下であれば３を返すクロージャ（atLeast3）を作り出す

assert atLeast3(5) == 5
assert atLeast3(4) == 4
assert atLeast3(3) == 3
assert atLeast3(2) == 3
assert atLeast3(1) == 3
