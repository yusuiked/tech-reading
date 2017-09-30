include Java

class Action
  # interface を include しなくてもメソッド名が同じなら自動的にダックタイピングされる
  def run
    puts 'Action#run is called.'
  end
end

java.lang.Thread.new(Action.new).start
