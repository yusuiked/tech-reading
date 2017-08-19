def print_libraries
  $:.each do |path|
    next unless File.directory?(path)
    Dir.glob(["#{path}/**/*.rb"]) do |name|
      puts File.basename(name)
    end
  end
end

print_libraries

puts "----------------------------"

# 別解 (https://tanoshiiruby.github.io/4/answer/#file)
# が、サブディレクトリまで調べていないのでバグ？
def print_libraries2
  $:.each do |path|
    next unless FileTest.directory?(path)
    Dir.open(path) do |dir|
      dir.each do |name|
        if name =~ /\.rb$/i
          puts name
        end
      end
    end
  end
end

print_libraries2
