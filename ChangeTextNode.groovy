def root = new XmlParser().parse('sample.xml')

def groceries = root.category.findAll { it.@type == 'groceries' }
groceries[0].item.each { g ->
    g.value = 'luxury ' + g.text()
}

def supplies = root.category.findAll { it.@type == 'supplies' }
supplies[0].item.findAll { it.text() == 'pen' }.each { s ->
    s.@quantity = s.@quantity.toInteger() + 2
    s.@when = 'urgent'
}

def presentCategory = root.category.find { it.@type == 'present' }
presentCategory.children().clear()
presentCategory.appendNode('item', "mother's birthday")
presentCategory.appendNode('item', [when:'Oct 15'], "Monica's birthday")

new File("xmlparser.output.xml").withPrintWriter("UTF-8") { writer ->
    writer << '<?xml version="1.0" encoding="UTF-8"?>' << "\n"
    def printer = new XmlNodePrinter(new PrintWriter(writer))
    printer.setPreserveWhitespace(true)
    printer.print(root)
}
