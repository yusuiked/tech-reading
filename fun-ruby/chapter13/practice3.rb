a = (1..100).to_a

a3 = a.reject { |i| i % 3 != 0 }
p a3

a.reject! { |i| i % 3 != 0 }
p a
