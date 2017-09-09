require "open-uri"
#require "nkf"  # Shift_JIS 環境の場合

url = "http://cruel.org/freeware/cathedral.html"
filename = "cathedral.html"

File.open(filename, "w") do |f|
  text = open(url).read
  f.write text
  #f.write NKF.nkf("-s", text)  # Shift_JIS 環境の場合
end
