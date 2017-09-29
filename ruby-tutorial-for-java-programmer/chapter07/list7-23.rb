include Java

runner = java.lang.Runnable.impl do
  puts "Runnable#run is called."
end

java.lang.Thread.new(runner).start
