for (int i: 1 .. 3) {
    println i   // ==> 1,2,3 が表示される
}

(1 ..< 3).each {
    println it  // ==> 1,2 が表示される
}

(3 .. 1).each {
    println it  // ==> 3,2,1 が表示される
}

for (String i: "a" .. "c") {
    println i   // ==> a,b,c が表示される
}
