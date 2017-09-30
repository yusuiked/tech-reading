array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
array.each do |i|
  if i % 2 == 0
    puts i.to_s + ' even'
  else
    puts i.to_s + ' odd'
  end
end
