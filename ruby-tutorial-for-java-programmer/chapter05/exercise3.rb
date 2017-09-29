class Operator
  def initialize(prior)
    @value = prior.value
  end
  def input(s)
    AfterOperator.new(self)
  end
end

class Plus < Operator
  def operation
    Proc.new {|val| @value + val}
  end
end

class Minus < Operator
  def operation
    Proc.new {|val| @value - val}
  end
end

class Equal < Operator
  attr_reader :value
  def input(s)
    $stdout.puts @value
    AfterOperator.new(self)
  end
  def operation
  end
end

class Operand
  def initialize(prior = nil)
    @operation = (prior) ? prior.operation : nil
    @value = 0
  end
  attr_reader :value
  def input(s)
    if '0123456789'.index(s)
      @value = @value * 10 + s.to_i
      self
    else
      @value = @operation.call(@value) if @operation
      AfterOperand.new(self).input(s)
    end
  end
end

class SpaceEater
  def input(s)
    if s.strip.length == 0
      self
    else
      n = next_state(s)
      raise ArgumentError.new('bad input:' + s) unless n
      n
    end
  end
end

class AfterOperand < SpaceEater
  OPERATORS = {'+' => Plus, '-' => Minus, '=' => Equal}
  def initialize(prior)
    @value = prior.value
  end
  attr_reader :value
  def next_state(s)
    if OPERATORS[s]
      OPERATORS[s].new(self).input(s)
    end
  end
end

class AfterOperator < SpaceEater
  def initialize(prior)
    @operation = prior.operation
  end
  attr_reader :operation
  def next_state(s)
    if '0123456789'.index(s)
      Operand.new(self).input(s)
    end
  end
end

[
  '12 + 3 - 5 =',
  '1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 =',
  '12345+12345='
].each do |ex|
  state = Operand.new
  ex.split('').each do |s|
    state = state.input(s)
  end
end
