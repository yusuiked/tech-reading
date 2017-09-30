class Calculator
  def add(x, y)
    x = x.to_f if String === x || Integer === x
    y = y.to_f if String === y || Integer === y
    x + y
  end
end
calc = Calculator.new
$stdout.puts calc.add(1, 2)
$stdout.puts calc.add("1", 2)
$stdout.puts calc.add(1, "2")
$stdout.puts calc.add(1.0, "2.0")
