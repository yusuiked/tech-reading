include Java

class MyEvent < java.util.EventObject
end

event = MyEvent.new(:source)
p event.get_source
