puts "break の例:"
i = 0
["Perl", "Python", "Perl", "Scheme"].each do |lang|
  i += 1
  if i == 3
    break
  end 
  p [i, lang]
end

puts "next の例:"
i = 0
["Perl", "Python", "Perl", "Scheme"].each do |lang|
  i += 1
  if i == 3
    next
  end 
  p [i, lang]
end

puts "redo の例:"
i = 0
["Perl", "Python", "Perl", "Scheme"].each do |lang|
  i += 1
  if i == 3
    redo
  end 
  p [i, lang]
end
