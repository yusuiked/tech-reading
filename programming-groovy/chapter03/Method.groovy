class MyClass {
    def method1() { return "A" }
    def method2() { return "B" }
    def method3() { return "C" }
}
c = new MyClass()
i=1; assert c."method$i"() == "A"
i=2; assert c."method$i"() == "B"
i=3; assert c."method$i"() == "C"
