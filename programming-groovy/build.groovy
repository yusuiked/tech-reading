def ant = new AntBuilder()

ant.copy(todir:'dest') {
    fileset(dir:'source') {
        include(name:'*.properties')
    }
}
