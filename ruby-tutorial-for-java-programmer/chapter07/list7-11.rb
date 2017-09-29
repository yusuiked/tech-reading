require 'thread.so'

2.times do
  Thread.new { p Thread.current }
end
