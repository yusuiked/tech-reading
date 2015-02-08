int foo() {
    def x = 0
    3.times {
        x++
    }
    x
}
assert foo() == 3
