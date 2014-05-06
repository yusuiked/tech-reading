String s = null
try{
    assert s?.toUpperCase() == null // NullPointerException は発生しない
}catch(NullPointerException e){
    assert false
}
