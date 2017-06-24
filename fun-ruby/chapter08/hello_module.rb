module HelloModule
  Version = "1.0"

  def hello(name)
    puts "Hello, #{name}"
    p self #=> モジュール関数呼び出しで self はそのモジュールが得られる
           #=> クラスに mix-in すると self はそのクラス自身になる
           #=> 同じメソッドでも呼び出す文脈で結果が異なるので、 Mix-in するモジュール
           #=> self を使わないのが普通。
  end
  
  module_function :hello  # hello メソッドをモジュール関数として公開する
end

p HelloModule::Version
HelloModule.hello("Alice")

include HelloModule
p Version
hello("Alice")
