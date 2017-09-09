class HelloWorld
  def initialize(myname = "Ruby")
    @name = myname
  end

  def name
    @name
  end
  
  def name=(value)
    @name = value
  end

  def hello
    puts "Hello, world. I am #{@name}."
  end

  # self を使った特異クラスでのクラスメソッド定義
  class << self
    def hello_class_method(name)
      puts "#{name} said Hello class method."
    end
  end
  # self を使ったクラスメソッド定義
  def self.hello_2(name)
    puts "#{name} said hello 2."
  end
end

bob = HelloWorld.new("Bob")
alice = HelloWorld.new("Alice")
ruby = HelloWorld.new

bob.hello
HelloWorld.hello_class_method("Mike")  #=> Mike said Hello class method.
HelloWorld.hello_2("Kevin")  #=> Kevin said hello 2.

# クラスメソッドは、クラス定義の外で以下のように定義することもできる
def HelloWorld.hello_3(name)
  puts "#{name} said hello 3."
end

HelloWorld.hello_3("Michael")  #=> Michael said hello 3.

# 特異クラス定義
class << HelloWorld
  # 特異メソッド
  # そのインスタンスのみに存在するメソッドを定義する特異メソッドを特異クラスに定義することで、
  # クラスメソッドを定義することになる（クラスインスタンスに対する特異メソッド定義）
  def hello(name)
    puts "#{name} said hello."
  end
end

HelloWorld.hello("John")  #=> John said hello.
