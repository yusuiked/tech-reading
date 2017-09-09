chars = {}
"Ruby is an object oriented programming language".each_char do |c|
  chars[c] ? chars[c] = chars[c] + 1 : chars[c] = 1
end
chars.keys.sort.each do |c|
  puts "'#{c}': #{'*' * chars[c]}"
end

# 別解
str = "Ruby is an object oriented programming language"
count = Hash.new
str.each_char do |c|
  count[c] = 0 unless count[c]
  count[c] += 1
end
count.keys.sort.each do |c|
  printf("'%s': %s\n", c, "*" * count[c])
end
# 別解 ハッシュのデフォルト値を 0 とする
str = "Ruby is an object oriented programming language"
count = Hash.new(0)
str.each_char do |c|
  count[c] += 1
end
count.keys.sort.each do |c|
  printf("'%s': %s\n", c, "*" * count[c])
end
