include Java

import('java.lang.Object') { 'JObject' }

obj1 = JObject.new
obj2 = JObject.new

def obj1.singleton
  'Singleton Method'
end

p obj1.singleton
p obj2.singleton
