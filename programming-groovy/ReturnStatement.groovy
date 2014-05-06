def twice(n) {
    n * 2   // return n * 2 と同じ
}
assert twice(2) == 4

def even(n) {
    if(n % 2 == 0){
        true
    } else {
        false
    }
}
assert even(0)==true
assert even(1)==false
