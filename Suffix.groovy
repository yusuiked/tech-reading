assert (3.0).class == java.math.BigDecimal
assert (3.0D).class == java.lang.Double
assert (3i).class == java.lang.Integer
assert (3L).class == java.lang.Long
assert (3F).class == java.lang.Float
assert (3D).class == java.lang.Double
assert (3G).class == java.math.BigInteger
assert (3.0G).class == java.math.BigDecimal

/*
 * Groovyでは，演算によって演算結果の型が変更されることはありません。たとえばRubyでは，FixnumとFixnumの乗算の結果がFixnumに収まらないとき，結果の型がBignumに自動拡張されますが，Groovyではそのような動作は行いません
 */
