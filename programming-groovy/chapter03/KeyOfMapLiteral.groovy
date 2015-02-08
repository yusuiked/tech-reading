// カッコでくくることで任意の式を与えることができる
// 変数や式の値をキーにすることもできる
Map map1 = [('a'):1, ('b'+'c'):2, ('d'*3):3]
// キーが文字列定数の時はカッコが不要
Map map2 = ['a':1, '!bc':2, '123':3]
// 識別子として有効な文字列定数ならクォートも不要
Map map3 = [a:1, bc:2, ddd:3]
// 日本語キーもOK
Map map4 = [名前: 'もも太郎', 年齢: 18, 生年月日: new Date()]
println map1.keySet().dump()
println map2.keySet().dump()
println map3.keySet().dump()
println map4.keySet().dump()
