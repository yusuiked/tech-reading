class String
  def statistic
    r = Hash.new
    each_char do |c|
      unless r[c]
        r[c] = 1
      else
        r[c] += 1
      end
    end
    r
  end
end

p "abc".statistic
p "abbc".statistic
