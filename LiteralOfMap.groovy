Map map = [a:1, b:2, c:3]
assert map instanceof java.util.LinkedHashMap
assert map.size() == 3

Map map0 = [:]
assert map0.size() == 0

map0['a'] = 1
assert map0 == [a:1]
assert map0.size() == 1
