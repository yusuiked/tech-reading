include Java

import javax.swing.JFrame

class MyFrame < JFrame
  def initialize
    super

    set_default_close_operation(JFrame::EXIT_ON_CLOSE)
    set_title "Hello JFrame!"
    set_size 300, 300
    set_visible true
  end
end

MyFrame.new
