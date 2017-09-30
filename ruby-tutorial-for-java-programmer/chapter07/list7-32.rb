include Java

begin
  file = java.io.File.new("no such file")
  stream = java.io.FileInputStream.new(file)
rescue java.io.FileNotFoundException
  p $!
end
