def root = new XmlParser().parse('sample.xml')

/*
 * findAllメソッドを利用して，<category>要素のうちtype属性が「groceries」であるものをすべて取り出し，
 * 最初の要素（groceries[0]）に対して，子要素<item>のテキストノードをすべて変更
 */
def groceries = root.category.findAll { it.@type == 'groceries' }
groceries[0].item.each { g ->
    g.value = 'luxury ' + g.text()
}

/*
 * findAllメソッドを利用して，複数の<category>要素のうちtype属性が「supplies」であるものをすべて取り出し，
 * 最初の要素（supplies[0]）の子要素<item>のうちテキストノードが「pen」であるものに対して，quantity属性を+2し，
 * 新たにwhen属性を追加
 */
def supplies = root.category.findAll { it.@type == 'supplies' }
supplies[0].item.findAll { it.text() == 'pen' }.each { s ->
    s.@quantity = s.@quantity.toInteger() + 2
    s.@when = 'urgent'
}

/*
 * findメソッドを利用して，<category>要素のうちtype属性が「present」であるものを1つだけ取り出し，
 * その子要素を消去して，新たに2つの<item>要素を追加
 * appendNode()の第1引数には追加する要素名を指定し，第2引数以降にはテキストノードの内容と，
 * 必要であれば属性をMap形式で指定
 */
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
