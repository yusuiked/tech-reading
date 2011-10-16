import java.awt.List as AWTList // AWTList という別名をつける

// java.util.Listを修飾なしで使っても衝突しない
List list = new ArrayList()
assert list.class == java.util.ArrayList
