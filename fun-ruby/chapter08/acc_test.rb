class AccTest
  def pub
    puts "pub is a public method."
  end
  
  public :pub # pub メソッドを public に設定（指定しなくても良い）

  def priv
    puts "priv is a private method."
  end
  
  private :priv
end

acc = AccTest.new
acc.pub
acc.priv # private method `priv' called (NoMethodError)
