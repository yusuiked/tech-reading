def products = new XmlParser().parse('newXML.xml')

assert products.Product[0].@type== 'regular'
assert products.Product[0].Name.text() == 'Instant Noodle'
assert products.Product[0].Price.text() == '147'

products.eachWithIndex { Product, idx ->
    println "No.${idx+1}"
    println "Name: ${Product.Name.text()}"
    println "Price: ${Product.Price.text()}"
    println "Type: ${Product.@type}"
    println ""
}
