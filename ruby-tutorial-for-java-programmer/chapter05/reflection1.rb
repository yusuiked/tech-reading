# メソッド定義メソッド
class A
  define_method('hello') do
    puts 'hello from A'
  end
end

A.new.hello

# 実行時評価
class B
  module_eval "def hello;puts 'hello from B';end"
end

B.new.hello

# 実行時判断
class C
  def method_missing(name, *args)
    if name == :hello
      puts 'hello from C'
    end
  end
end

C.new.hello
