include Java

class Action
  include java.lang.Runnable
  def run
    puts "Action#run is called."
  end
end

java.lang.Thread.new(Action.new).start
