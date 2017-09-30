include Java

class Java::JavaLang::Object
  def toString
    "Hello, World!"
  end
  def helloWorld!
    puts toString
  end
end

obj = java.lang.Object.new
obj.helloWorld!
