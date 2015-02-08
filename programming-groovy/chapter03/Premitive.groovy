assert 1.class == java.lang.Integer
int i=1
assert i.class == java.lang.Integer
assert 2.plus(i) == 3   // 2+iは2.plus(i)のシンタックスシュガー
assert (i-5).abs() == 4 // 絶対値
