import groovy.xml.StreamingMarkupBuilder

def xml = new StreamingMarkupBuilder()

def writable = xml.bind {
    Products {
        Product(type:'regular') {
            Name('Instant Noodle')
            Price(147)
        }
    }
}
println writable.toString()
