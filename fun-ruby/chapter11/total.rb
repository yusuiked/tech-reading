def total(from, to)
  result = 0
  from.upto(to) do |num|
    if block_given?
      result += yield(num)
    else
      result += num
    end
  end
end

p total(1, 10)
p total(1, 10) { |num| num ** 2 }
