def tail(n, input)
  queue = Array.new
  open(input) do |f|
    f.each_line do |line|
      queue.push line
      if queue.size > n
        queue.shift
      end
    end
  end
  queue.each do |line|
    puts line
  end
end

tail(5, __dir__ + '/lipsum.txt')
