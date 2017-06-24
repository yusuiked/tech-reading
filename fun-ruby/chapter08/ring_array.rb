class RingArray < Array
  def [](i)
    idx = i % size
    super(idx)
  end
end

wday = RingArray["日", "月", "火", "水", "木", "金", "土"]
p wday[6]
p wday[11]
p wday[15]
p wday[-1]
