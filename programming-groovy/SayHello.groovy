import java.io.*

class SayHello { // クラス名が衝突するのでエラー
    void sayHello() {
        println "hello"
    }
}
def foo = new SayHello()
foo.sayHello()
