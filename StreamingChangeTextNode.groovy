import groovy.xml.StreamingMarkupBuilder

def root = new XmlSlurper().parse('sample.xml')

def groceries = root.category.findAll { it.@type == 'groceries' }
def g = groceries[0]

g.item.eachWithIndex { item, i ->
    g.item[i] = 'luxury ' + item
}

def supplies = root.category.findAll { it.@type == 'supplies' }
def pens = supplies[0].item.findAll { it.text() == 'pen' }
pens.each { p ->
    p.@quantity = (p.@quantity.toInteger() + 2).toString()
    p.@when = 'Urgent'
}

def presents = root.category.find { it.@type == 'present' }
presents.replaceNode { node ->
    category(type:'present') {
        item("mother's birthday")
        item("Monica's birthday", when:'Oct 15')
    }
}

def out = new OutputStreamWriter(
    new FileOutputStream("xmlslurper.output.xml"),"UTF-8")

def outputBuilder = new StreamingMarkupBuilder()
def writer = outputBuilder.bind {
    mkp.xmlDeclaration()
    mkp.yield(root)
}
out << writer
