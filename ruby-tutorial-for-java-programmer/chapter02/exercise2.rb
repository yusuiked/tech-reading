total = 1
i = 2
while i <= 10
  total *= i
  i += 1
end
puts total
total = 1
i = 2
until i > 10
  total *= i
  i += 1
end
puts total
total = 1
2.upto(10) do |i|
  total *= i
end
puts total
