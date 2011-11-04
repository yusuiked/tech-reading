import groovy.xml.StreamingMarkupBuilder

def root = new XmlSlurper().parse('sample.xml')

def out = new OutputStreamWriter(
    new FileOutputStream("xmlslurper.output.xml"), "UTF-8")

def outputBuilder = new StreamingMarkupBuilder()
def writer = outputBuilder.bind {
    mkp.xmlDeclaration()
    mkp.yield(root)
}
out << writer
