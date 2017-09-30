include Java

string = java.lang.String.new("A,B,C,D")
array = string.split(",")
array.each do |str|
  p str
end
