include Java

class ExitActionListener
  include java.awt.event.ActionListener
  def actionPerformed(e)
    dispose
  end
end
