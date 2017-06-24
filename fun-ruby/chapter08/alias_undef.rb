class C1
  def hello
    "Hello"
  end
end

class C2 < C1
  alias old_hello hello
  
  def hello
    "#{old_hello}, again"
  end
end

obj = C2.new
p obj.old_hello
p obj.hello

# undef はスーパークラスで定義されたメソッドをサブクラスでは削除する、といった用途で使われる
