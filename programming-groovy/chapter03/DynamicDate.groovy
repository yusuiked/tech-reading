c = { it << new Date() }.asWritable()
gs = "Current time $c"
while (true) {
    println gs
    Thread.sleep(1000)
}
