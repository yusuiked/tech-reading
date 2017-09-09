a = (1..100).to_a

p a.inject(0) { |sum, i| sum += i }

# 別解
p a.inject { |sum, i| sum += i }
# inject(symbol) を使うとメソッド名を Symbol として受け取り, ブロックの代わりにそのメソッドを実行する
p a.inject(:+)
