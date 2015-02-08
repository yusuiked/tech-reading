try {
    throw new IllegalArgumentException()
} catch (e) { // catch (Exception e) と同じ
    assert e instanceof java.lang.IllegalArgumentException
}
