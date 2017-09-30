begin
  8 / 0
  puts(0)
rescue IOError
  puts(1)
rescue ZeroDivisionError
  puts(2)
rescue
  puts(3)
ensure
  puts(4)
end
