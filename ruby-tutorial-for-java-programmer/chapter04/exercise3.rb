module Totalizable
  def sum
    data.inject(0) do |r, e|
      r + e
    end
  end
end
class TestResult
  include Totalizable
  def initialize(*data)
    @data = data
  end
  attr_reader :data
end
x = TestResult.new(80, 90, 100, 80)
$stdout.puts x.sum
