def a
  begin
    b
  rescue => e
    $stdout.puts "a: rescue message=#{e.message}"
  ensure
    $stdout.puts "a: ensure"
  end
  $stdout.puts 'a: exit'
end

def b
  begin
    c
  rescue IOError
    $stdout.puts 'b: rescue by IOError'
  ensure
    $stdout.puts 'b: ensure'
  end
  $stdout.puts 'b: exit'
end

def c
  raise RuntimeError, 'raise by c'
end

a
