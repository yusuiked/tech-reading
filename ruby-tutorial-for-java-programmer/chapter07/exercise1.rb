include Java

import javax.swing.JFrame

class App < JFrame
  def initialize
    super
    setDefaultCloseOperation(JFrame::EXIT_ON_CLOSE)
    setVisible(true)
  end
end

App.new
