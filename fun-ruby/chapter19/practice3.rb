# encoding: utf-8

str = '①'

encoding = [Encoding::Windows_31J, Encoding::Shift_JIS]
encoding.each do |enc|
  begin
    print "strを#{enc}に変換します。=> "
    puts "結果: %p" % [str.encode(enc)]
  rescue => ex
    p ex
  end
  puts
end
