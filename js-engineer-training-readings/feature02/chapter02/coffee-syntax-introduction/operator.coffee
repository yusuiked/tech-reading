# 真偽値

console.log true
console.log false
console.log on
console.log off
console.log yes
console.log no

# 比較演算子

1 == true # => 1 === true

1 is true # => ===
1 isnt true # => !==

# 論理演算子

1 is 2 or 3 is 3 # => ||
2 is 2 and 4 isnt 3 # => &&

# ?演算子

a = null ? true # => true
b = undefined ? true # => true
c = {} ? true # => {}
# CoffeeScrip において false は明示的に存在する null ではない値
d = false ? {} # => false