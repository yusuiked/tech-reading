class Name
  def initialize(first, last)
    @first = first
    @last = last
  end
  attr_accessor :first, :last
end

class TriName < Name
  def initialize(first, middle, last)
    super(first, last)
    @middle = middle
  end
  attr_accessor :middle
end

tri_name = TriName.new('test', 'hoge', 'fuga')
puts tri_name.first
puts tri_name.middle
puts tri_name.last
