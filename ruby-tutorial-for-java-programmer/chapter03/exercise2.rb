class Name1
  def initialize(first, last)
    @first = first
    @last = last
    @middle = ''
  end
  attr_reader :first, :last
  attr_accessor :middle
end

class Name2
  def initialize(first, last, middle = '')
    @first = first
    @last = last
    @middle = middle
  end
  attr_reader :first, :last, :middle
end
