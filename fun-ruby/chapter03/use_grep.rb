require "./grep"  # grep.rb の読み込み（「.rb」は不要）

pattern = Regexp.new(ARGV[0])
filename = ARGV[1]
simple_grep(pattern, filename)
