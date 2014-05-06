def my_if(Boolean expr, Closure clos) {
    if (expr) {
        clos.call(expr)
    }
}

Closure clos = { println "expr=$it" }
my_if(true, clos)
// ==> "expr=true"が表示される

my_if(true, {println "expr=$it"})
// ==> "exprtrue"が表示される

my_if(true) {
    println "expr=$it" // ==> "expr=true"が表示される
}
