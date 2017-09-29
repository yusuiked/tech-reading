class String
  def statistic
    info = {}
    self.each_char do |c|
      if info[c]
        info[c] += 1
      else
        info[c] = 1
      end
    end
    info
  end
end

p "abc".statistic
p "abbc".statistic
