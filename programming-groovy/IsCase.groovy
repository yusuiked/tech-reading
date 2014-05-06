class Around {
    def num
    Around(n) {
        num = n
    }
    def isCase(n) {
        return n in (num-3 .. num+3)
    }
}
around30 = new Around(30)
around40 = new Around(40)
assert 31 in around30 == true
assert 19 in around40 == false
