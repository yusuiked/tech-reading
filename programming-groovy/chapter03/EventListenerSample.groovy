import javax.swing.*

def frame = new JFrame()
JButton button = new JButton("say hello")
button.actionPerformed = { event -> println 'hello!' } // イベントハンドラを設定
frame.add button
frame.visible = true // setVisible(true)の呼び出し
