/*
 * SwingとAWTを使っているため，X環境が導入されていないLinux環境では動かない
 */
import javax.swing.*
import java.awt.event.*

f = new JFrame()
lab = new JTextField("hello")
handler = [keyTyped:{KeyEvent key -> println key}] as KeyAdapter // マップをKeyAdapterにasする
// マップのキーをメソッド名に持ち，そのキーの値をメソッド本体としてもつオブジェクトが生成されるため，
// // 指定したクラスやインタフェースを継承したものとなり，リスナオブジェクトとして登録できる
assert handler instanceof KeyAdapter

lab.addKeyListener(handler)
f.add(lab)
f.show()
