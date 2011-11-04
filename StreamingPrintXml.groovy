import groovy.xml.StreamingMarkupBuilder

def root = new XmlSlurper().parse('sample.xml') // GPathResultオブジェクトが得られるので，StreamingMarkupBuilderにそのままバインド出来る

def out = new OutputStreamWriter(
    new FileOutputStream("xmlslurper.output.xml"), "UTF-8")

def outputBuilder = new StreamingMarkupBuilder()
def writer = outputBuilder.bind {
    /*
     * mkpはGroovyが事前に予約している特殊な名前空間として認識される
     */
    mkp.xmlDeclaration()    // XML宣言の出力
    mkp.yield(root) // 事前に定義したクロージャを，ビルダーのメソッド呼び出しに読み替える
}
out << writer
