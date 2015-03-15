# 行コメント。コンパイル時に消去される

###
  複数行コメント。
  コンパイル時に消去されず、出力先にも残る。
###

# 文字列リテラル
'this is string'
# "" は #{...} を評価する
"this is #{2 + 3}"

name = 'yukung'
'#{name}'
"my name is #{name}"

# 複数行の文字列リテラルは ''' または """ 
text = """
  aaa
  #{'bbb'}
  ccc
"""

# インデントが少ない行に他の行が調整される
text = """
    aaa
#{'bbb'}
  ccc
"""

# JavaScript リテラル（JavaScript コードを CoffeeScript に埋め込む）
`
function func() {
  var i = 3;
  return i;
}
`

# 配列
a = [1, 2, 3]
# インデントを使って表現することもできる
b = [
  1
  2
  3
]

# レンジ
[1..3]
[0...3]
# for 文で使うと
arr = [0..3]
for i in [0...arr.length]
  console.log i

# 配列のスライス
arr = ['a', 'b', 'c', 'd', 'e']
arr[1..3] # => arr.slice(1, 4);
# => [ 'b', 'c', 'd' ]

# オブジェクトリテラル
# JavaScript で obj = {a: 1, b: 2, c: 3} はブロックが自明な限り中括弧を省略できる
obj = a: 1, b: 2, c: 3
# インデントを使って表現できる。この場合はカンマを省略できる。
# ネストを深くすることでオブジェクトのネストも表現できる
# インデントでブロックを表現することをオフサイドルールと呼ぶ
obj2 =
  a: 4
  b: 5
  c:
    d: 7
    e: 8
# 関数の引数として適用することもできる
###
func({
  a: 1,
  b: 2
})
###
func
  a: 1
  b: 2

# スコープ上のオブジェクト宣言
a = 3
b = 2
obj = {a, b, c: 1} # => {a: 3, b: 2, c: 1}
# this のメンバに対しても適用可能
f = ->
  @a = 3
  @b = 2
  obj = {@a, @b, c: 1} # => {a: this.a, b: this.b, c: 1}