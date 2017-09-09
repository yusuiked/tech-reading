str1 = "Ruby"
str2 = "Ruby"

class << str1
  def hello
    "Hello, #{self}!"
  end
end

p str1.hello  #=> "Hello, Ruby!"
p str2.hello  #=> (NoMethodError)
