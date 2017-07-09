def prime?(num)
  return false if num < 2
  2.upto(Math.sqrt(num)) do |i|
    if num % i == 0
      return false
    end
  end
  return true
end

puts prime?(0)
puts prime?(1)
puts prime?(2)
puts prime?(3)
puts prime?(5)
puts prime?(7)
puts prime?(4)
puts prime?(6)
puts prime?(8)
