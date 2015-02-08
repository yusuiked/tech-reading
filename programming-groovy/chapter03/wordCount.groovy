// 指定したテキストファイル中の単語の出現回数を一覧表示する
def map = [:]
new File(args[0]).eachLine {
    it.split(/\s+/).each {
        map[it] = (map[it]==null) ? 1 : (map[it] + 1)
    }
}
map.entrySet().sort{it.value}.each {
    println "${it.value}: [${it.key}]"
}
