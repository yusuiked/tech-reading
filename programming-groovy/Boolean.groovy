assert true.class == java.lang.Boolean
assert false.class == java.lang.Boolean

assert 1                    // 整数（0以外）は真
assert !0                   // 整数（0）は偽
assert "0"                  // 文字列は数値と同一視されない
assert 0.001                // 浮動小数（0.0以外）は真
assert !0.0                 // 浮動小数（0.0）は偽
assert [1]                  // 空でないリストは真
assert ![]                  // 空リストは偽
assert [key:'value']        // 空ではないマップは真
assert ![:]                 // 空のマップは偽
assert "abcdefg" =~ /c.*f/    // 正規表現がマッチすれば真（Matcher.find()==true）
assert !("abcdefg" =~ /x.*z/) // 正規表現がマッチしなければ偽（Matcher.find()==false）

Iterator iter = [1,2,3,4,5].iterator()
while(iter.hasNext()){
    def n = iter.next()
    if(n != 5){
        assert iter         // 最後の要素ではないIteratorは真
    } else {
        assert !iter        // 最後の要素のIteratorは偽
    }
}
