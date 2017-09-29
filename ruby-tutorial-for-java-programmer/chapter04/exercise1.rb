class Name
  def initialize(first, last)
    @first = first
    @last = last
  end
  attr_accessor :first, :last
end

class TriName
  def initialize(first, middle, last)
    @first = first
    @middle = middle
    @last = last
  end
  attr_accessor :first, :middle, :last
end
