def dice10
  sum = 0
  10.times do
    sum += Random.rand(6) + 1
  end
  sum
end

puts dice10
