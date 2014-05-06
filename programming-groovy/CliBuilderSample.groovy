def cli = new CliBuilder(usage:'groovy CliBuilderSample.groovy -f filename [-d]')

// cli.h(longOpt:'help', 'ヘルプ')
// cli.f(longOpt:'file', required:true, args:1, '処理対象ファイル名')
// cli.d(longOpt:'debug', 'デバッグモード')

/*
 * withを使った書き換え
 */
cli.with {
    h longOpt:'help', 'ヘルプ'
    f longOpt:'file', required:true, args:1, '処理対象ファイル名'
    d longOpt:'debug', 'デバッグモード'
}

def options = cli.parse(args)

if (!options) return

if (options.h) {
    cli.usage()
    return
}

if (options.f) println "オプション-fが指定されました（引数=${options.f})"

if (options.d) println "オプション-dが指定されました"
