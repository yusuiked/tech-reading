/*
 * X環境がないと動かない
 */
import javax.swing.JFrame
import javax.swing.JButton
import java.awt.event.ActionListener

f = new JFrame()
b = new JButton("hello")
handler = { println "hello!" } as ActionListener // クロージャを直接インタフェースにasする

assert handler instanceof ActionListener
b.addActionListener handler
f.add b
f.show() // ボタンを1つ含むJFrameが表示され，ボタンを押下すると"hello!"が出力される
