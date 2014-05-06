def m = ("abcabc" =~ /a.c/) // Matcherが生成される
assert m    // Matcher#find()が実行される

if("abcabc" =~ /a.c/){  // 別のMatcherが生成され，find()が実行される
    assert true
} else {
    assert false
}
