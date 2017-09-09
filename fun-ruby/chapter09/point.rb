class Point
  attr_accessor :x, :y

  def initialize(x=0, y=0)
    @x, @y = x, y
  end
  
  def inspect
    "(#{x}, #{y})"
  end

  # 二項演算子で計算するときは演算子をメソッド名として定義する
  def +(other)
    self.class.new(x + other.x, y + other.y)
  end

  def -(other)
    self.class.new(x - other.x, y - other.y)
  end

  # 単項演算子は +, -, ~, ! の 4 つが定義でき、 +@, -@, ~@, !@ という名前のメソッドで定義できる
  def +@
    dup # 自分の複製を返す
  end
  
  def -@
    self.class.new(-x, -y)  # x, y のそれぞれの正負を逆にする
  end
  
  def ~@
    self.class.new(-y, x) # 90 度反転させた座標を返す
  end

  # 添字メソッド
  def [](index)
    case index
    when 0
      x
    when 1
      y
    else
      raise ArgumentError, "out of range `#{index}`"
    end
  end

  def []=(index, val)
    case index
    when 0
      self.x = val
    when 1
      self.y = val
    else
      raise ArgumentError, "out of range `#{index}`"
    end
  end
end

point0 = Point.new(3, 6)
point1 = Point.new(1, 8)

p point0
p point1
p point0 + point1
p point0 - point1

point = Point.new(3, 6)
p +point
p -point
p ~point

p point[0]
p point[1] = 2
p point[1]
p point[2]
