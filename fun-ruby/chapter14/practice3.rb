
ary = "Ruby is an object oriented programming language".split.sort
p ary.sort_by { |s| s.downcase }
