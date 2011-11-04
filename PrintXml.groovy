def root = new XmlParser().parse('sample.xml')

new File("xmlparser.output.xml").withPrintWriter("UTF-8") { writer ->
    writer << '<?xml version="1.0" encoding="UTF-8"?>' << "\n"
    def printer = new XmlNodePrinter(writer)
    printer.preserveWhitespace = true
    printer.print(root)
}
