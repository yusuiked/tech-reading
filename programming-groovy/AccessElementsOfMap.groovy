Map map = [a:1,b:2,c:3]
assert map['a'] == 1
// キーが文字列定数の時は，フィールドの様にアクセスできる
assert map.a == 1
keyname = 'a'
// 評価することでキー名となるようなGStringを使うこともできる
assert map."$keyname" == 1
assert map[keyname] == 1
