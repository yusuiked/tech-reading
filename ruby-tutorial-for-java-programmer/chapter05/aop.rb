class String
  @@debug = false
  def self.debug=(f)
    @@debug = f
  end
  public_instance_methods(false).each do |m|
    mx = instance_method(m)
    define_method(m) do |*a|
      if dbg = @@debug
        @@debug = false
        p 'enter: ' + m.to_s
        p 'args: ' + a.inspect
      end
      result = mx.bind(self).call(*a)
      p 'result: ' + result.to_s if dbg
      @@debug = dbg
      result
    end
  end
end

String.debug = true
'abc'.upcase
' a b c'.strip
'abccd'.gsub('c', 'x')
