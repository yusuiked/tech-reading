assert "abc" ==~ /a.c/  //"abc"は/a.c/に全体マッチする
assert !("123abcdef" ==~ /a.c/) // "123abcdef"は/a.c/に全体マッチしない
