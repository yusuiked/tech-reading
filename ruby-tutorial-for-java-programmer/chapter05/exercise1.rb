class MyClass
  def print
    $stdout.puts "hogefuga"
  end
end
MyClass.freeze

class MyClass
  def print
    $stdout.puts "Foo"
  end
end

foo = Foo.new
foo.print
