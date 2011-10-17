a = 1       // バインディング変数
/* バインディング変数は，
 * ・型を指定することができない
 * ・型を指定しないことを意味するdefキーワードを指定することもできない
 * という制限がある。
 */
def b = 2   // ローカル変数

def foo() {
    println a   // => 1
    println b   // => 実行時エラー（MissingPropertyException）
}
foo()
