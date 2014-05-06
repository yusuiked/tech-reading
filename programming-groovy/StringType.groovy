/*
 * 文字列リテラル
 */
assert "abc" instanceof String
assert 'abc' instanceof String

a = 'ABC'
println "a=$a"  // ==> 'a=ABC'が表示される
println 'a=$a'  // ==> 'a=$a'が表示される

/*
 * トリプルダブルクォート，トリプルシングルクォート
 */
println """1行目
2行目
3行目"""

println '''1行目
2行目
3行目'''

/*
 * スラッシュ（/〜/）を用いた文字列リテラル
 */
assert( /abc/ instanceof String )
assert( /abc/ == "abc" )
a = "abc"
assert( /123 $a 456/ == '123 abc 456')

/*
 * スラッシュ文字列リテラル中のバックスラッシュの扱い
 */
assert( /\n/.size() == 2 )
assert( "\n".size() == 1 )

println "abc\ndef\n"
// => 以下が出力される
// abc
// def

println(/abc\ndef\n/)
// ==>以下が出力される
// abc\ndef\n
