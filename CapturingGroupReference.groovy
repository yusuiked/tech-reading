def expected = [['a','bb','ccc'],['aaa','bb','c']]
def target = "a bb ccc aaa bb c"    // マッチング対象の文字列。/(a+) (b+) (c+)/は2回マッチする

// Matcher#each()で，1個以上の引数を指定した場合
i=0
(target =~ /(a+) (b+) (c+)/).each { g0, g1, g2, g3 ->
    // g1, g2, g3で部分マッチを後方参照
    // g0にはマッチした文字列全体が入る("a bb ccc"もしくは"aaa bb c")
    assert [g1, g2, g3] == expected[i++]
}

// String#eachMatch()で，1個以上の引数を指定した場合
i=0
target.eachMatch(/(a+) (b+) (c+)/) { g0, g1, g2, g3 ->
    assert [g1, g2, g3] == expected[i++]
}
