import groovy.xml.MarkupBuilder

def xml = new MarkupBuilder()

xml.shopping() {
    category(type:'groceries') {
        item('chocolate')
        item('coffee')
    }
    category(type:'supplies') {
        item('paper')
        item(quantity:4,'pen')
    }
    category(type:'present') {
        item(when:'Aug 10','Gina\'s birthday')
    }
}
