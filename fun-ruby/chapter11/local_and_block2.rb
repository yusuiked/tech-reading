x = y = z = 0
ary = [1, 2, 3]
ary.each do |x; y| # ブロック変数 x, ブロックローカル変数 y を定義
  y = x # ブロックローカル変数 y に代入
  z = x # ブロックローカルでない変数 z に代入
  p [x, y, z]
end
puts
p [x, y, z]
