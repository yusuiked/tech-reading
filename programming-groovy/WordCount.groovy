// 指定したテキストファイル中の単語の出現回数を一覧表示する（改良版）
/* これもコメント */
def map = [:].withDefault{0}
new File(args[0]).eachLine {
    it.split(/\s+/).each {
        map[it]++
    }
}
map.entrySet().sort{it.value}.each {
    println "${it.value}: [${it.key}]"
}
