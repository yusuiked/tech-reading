List list = [1,2,3]
assert list[0] == 1
assert list[2] == 3
assert list[-1] == 3
assert list[-2] == 2
assert [1,2,3] + [4,5,6] == [1,2,3,4,5,6]
assert [1,2,3,4] - [2,3] == [1,4]
assert [1,2,3]*2 == [1,2,3,1,2,3]
assert [1,2,3] << [4,5,6] == [1,2,3,[4,5,6]]
