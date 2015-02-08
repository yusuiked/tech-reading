class MyClass {
    public a = 1
}
c = new MyClass()
assert c.a == 1     // フィールドの参照
assert c.'a' == 1   // フィールド名をクォート
assert c."a" == 1   // フィールド名をクォート

def fieldName = 'a'
assert c."$fieldName" == 1  // フィールド名をGStringで参照（A）

assert c['a'] == 1  // 配列の要素風に参照
assert c[fieldName] == 1    // 配列の要素風に参照
assert c.getProperty(fieldName) == 1    // プロパティ参照
