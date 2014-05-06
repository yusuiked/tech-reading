def m = ("abcdef" =~ /a.c/)
assert m.find() == true //"abcdef"は/a.c/を含む
assert m.matches() == false //"abcdef"は/a.c/に全体マッチしない
