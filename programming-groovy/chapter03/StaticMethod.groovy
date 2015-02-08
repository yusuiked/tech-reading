class MyClass {
    static foo() {
        assert this == MyClass  // Groovyでは.classは不要
        assert this.class == java.lang.Class
    }
}
MyClass.foo()
