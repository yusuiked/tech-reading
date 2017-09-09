def to_utf8(str_euc, str_sjis)
  str_euc.encode("UTF-8") + str_sjis.encode("UTF-8")
end

str_euc = "こんにちは".encode("EUC-JP")
str_sjis = "さようなら".encode("Shift_JIS")
puts to_utf8(str_euc, str_sjis)
