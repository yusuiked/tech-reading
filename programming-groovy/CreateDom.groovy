import groovy.xml.MarkupBuilder

def xml = new MarkupBuilder()

xml.Products() {
    Product(type:'regular') {
        Name('Instant Noodle')
        Price(147)
    }
}
