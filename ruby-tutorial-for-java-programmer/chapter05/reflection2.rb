# 動的にクラスメソッドを追加
module StringOperation
  String.new.public_methods.each do |name|
    define_method(name) do |str, *arg|
      str.__send__(name, *arg)
    end
    module_function name
  end
end

p StringOperation.upcase('abc')
p StringOperation.gsub('abc', 'b', 'B')
p StringOperation.index('abc', 'b')
p StringOperation.empty?('')
