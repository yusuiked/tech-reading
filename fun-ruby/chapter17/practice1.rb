def wc(file)
  nline = nword = nchar = 0
  File.open(file) do |io|
    io.each_line do |line|
      words = line.split(/\s+/).reject {|w| w.empty? }
      nline += 1
      nword += words.length
      nchar += line.length
    end
  end
  puts "lines=#{nline} words=#{nword} chars=#{nchar}"
end

p File.dirname(__FILE__)
p File.expand_path("../lipsum.txt", __FILE__)
p __dir__ # ruby 2.0 から
wc(File.dirname(__FILE__) + "/lipsum.txt")
wc(File.expand_path("../lipsum.txt", __FILE__))
wc(__dir__ + "/lipsum.txt")
