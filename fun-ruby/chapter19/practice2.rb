# encoding: utf-8

## Shift_JIS で sjis.txt に出力
File.open("sjis.txt", "w:Shift_JIS") do |f|
  f.write("こんにちは")
end

## sjis.txt を開いて、 UTF-8 で出力
File.open("sjis.txt", "r:Shift_JIS") do |f|
  str = f.read
  ## UTF-8 に変換
  puts str.encode("UTF-8")
end
