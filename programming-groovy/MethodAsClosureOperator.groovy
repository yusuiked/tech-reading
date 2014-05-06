def atLeast3 = Math.&max.curry(3)

assert atLeast3(5) == 5
assert atLeast3(4) == 4
assert atLeast3(3) == 3
assert atLeast3(2) == 3
assert atLeast3(1) == 3
