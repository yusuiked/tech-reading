include Java

list = java.util.ArrayList.new
list.add('A')
list << 'B'

list.each do |item|
  p item
end

map = java.util.HashMap.new
map.put('A', 1)
map['B'] = 2

map.each do |key, value|
  p [key, value]
end
