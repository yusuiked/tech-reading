include Java

klass = JavaClass.for_name('java.lang.Object')
p klass.class

constructor = klass.constructor
p constructor.class

instance = constructor.new_instance
p instance.class

method = klass.java_method(:toString)
p method.class

p method.invoke(instance)
