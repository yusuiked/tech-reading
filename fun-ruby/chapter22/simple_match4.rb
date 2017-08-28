len = ARGV[2].to_i
pattern = Regexp.new("(.{0,#{len}})(" + ARGV[0] + ")(.{0,#{len}})")
filename = ARGV[1]

count = 0
File.open(filename) do |file|
  file.each_line do |line|
    line.scan(pattern) do |s|
      prefix_len = 0
      s[0].each_char do |ch|
        if ch.ord < 128
          prefix_len += 1
        else
          prefix_len += 2
        end
      end
      space_len = 20 - prefix_len
      puts "#{" " * space_len}#{s[0]}<<#{s[1]}>>#{s[2]}"
      count += 1
    end
  end
end
puts "count: #{count}"
