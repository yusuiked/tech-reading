include Java

import javax.swing.JButton
import javax.swing.JFrame

class App < JFrame
  def initialize
    super
    button = JButton.new("exit")
    button.addActionListener do
      dispose
    end
    add(button)
    pack
  end
end

app = App.new
app.setVisible(true)
