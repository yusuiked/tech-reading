def a = "ABC"

assert "Hi ${a*2}" == "Hi ABCABC"   // 式の展開
assert "Hi ${a}" == "Hi ABC"
assert "Hi $a" == "Hi ABC"           // 変数の場合は{}が不要
