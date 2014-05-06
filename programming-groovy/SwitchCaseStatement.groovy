class MyClass { boolean isCase(n) { return n==999 } }
a = new MyClass()

switch (x) {
    case "abc": // xの内容が"abc"のとき
        break
    case ~/a(.*)c/: // xが正規表現パターンにマッチするとき
        // ここでの補足グループの参照にはMatcher#getLastMatcher()を使用できる
        assert java.util.regex.Matcher.lastMatcher[0][0] == "abbc"
        assert java.util.regex.Matcher.lastMatcher[0][1] == "bb"
        break
    case 1..3:  // xが1〜3の範囲に含まれるとき
        break
    case [1,3,5]:   // xが1,3,5のどれかであるとき
        break
    case String:    // xがStringのインスタンスであるとき
        break
    case a:         // aがユーザ定義型のインスタンスであるで，a.isCase(x)のとき
        break
    case {it % 2 == 0}: // xが偶数のとき
        break
}
