line_num = 0
word_num = 0
char_num = 0
File.open(File.dirname(__FILE__) + "/lipsum.txt") do |io|
  io.each_line do |line|
    words = line.split(/\s/)
    word_num += words.size
    char_num += line.size
    line_num += 1
  end
end
p line_num
p word_num
p char_num
