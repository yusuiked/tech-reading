import groovy.xml.MarkupBuilder

/*
 * CreateDom.groovyを書き変えたコード
 */
def xml = new MarkupBuilder()

xml.Products(null,
// MarkupBuilderのインスタンスに対してProducts()メソッドが実行される。
// 第1引数はnull，第2引数はクロージャ
  {
    Product(type:'regular',
    // Product()メソッドが実行される。
    // 第1引数はtype:'regular'，第2引数はクロージャ
      {
        // クロージャの内部で，Name()メソッドとPrice()メソッドが実行される
        Name('Instant Noodle')
        Price(147)
      }
    )
  }
)
