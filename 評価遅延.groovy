list=['x']
def gs = "$list ${list[0]}"
assert gs instanceof GString
assert gs == "[x] x"

list[0]='y'
assert gs == "[y] x"
