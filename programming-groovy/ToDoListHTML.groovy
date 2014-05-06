import groovy.xml.MarkupBuilder

def todos = [
    [name:'書籍の原稿を書く',due:'3/22',priority:1],
    [name:'プレゼンテーションの準備',due:'4/11',priority:2]
]

def builder = new MarkupBuilder()

builder.html {
    head {
        title('ToDoリスト')
    }
    body {
        table(border:'1') {
            tr {
                th('項目')
                th('期日')
                th('優先度')
            }
            todos.each { todo ->
                tr {
                    td(todo['name'])
                    td(todo['due'])
                    td(align:'center', todo['priority'])
                }
            }
        }
    }
}
