/**
 * 日経ソフトウェア 2015/02 Scala 連載1
 * @author yukung
 */
object FizzBuzz {
  def main(args: Array[String]) = {
//    val range = Range(1, 100)
//    range.foreach { i =>
    (1 to 100).foreach { i =>
      println((i % 3, i % 5) match {
        case (0, 0) => "ウェイウェーイ"
        case (0, _) => "ウェイ"
        case (_, 0) => "ウェーイ"
        case _ => i
      })
    }

    // ケースクラス
    val p1 = Person("Naoki Takezoe", 35)
    val p2 = Person("Takako Shimamoto", 31)
    val p3 = p2.copy(age = 32)

    println(p1.name)
    println(p1.age)
    println(p2)
    println(p3)

    // コレクション
    val list = List("Java", "JavaScript", "Scala", "Groovy")

    list
      .filter { x => x.startsWith("Java")}
      .map { x => x.toUpperCase}
      .foreach { x => println(x)}

    // タプル
    val t = (1, "string")
    val i = t._1
    val s = t._2

    println(i)
    println(s)

    patternMatch1("Woman")
    patternMatch1("ほげ")
    patternMatch2(p3.age)
    patternMatch3(p1.name)
    patternMatch3(p1.age)
    patternMatch3(p1.age != p3.age)
    patternMatch(p1)
    patternMatch(p2)
  }

  // 値でのパターンマッチ
  def patternMatch1(gender: String): Unit = {
    gender match {
      case "Man" => println("男性")
      case "Woman" => println("女性")
      case _ => println("その他")
    }
  }
  // 条件付きのパターンマッチ
  def patternMatch2(age: Int): Unit = {
    age match {
      case x if x < 20 => println("未成年")
      case _ => println("成年")
    }
  }
  // 型でのパターンマッチ
  def patternMatch3(value: Any): Unit = {
    value match {
      case x: String => println(s"文字列:${x}")
      case x: Int => println(s"数値　:${x}")
      case x: Boolean => println(s"真偽値:${x}")
      case _ => println("その他")
    }
  }
  // ケースクラスに対するパターンマッチ
  def patternMatch(p: Person): Unit = {
    p match {
      case Person(name, 20) => println(s"${name}さんは二十歳です")
      case Person(name, 60) => println(s"${name}さんは還暦です")
      case Person(name, age) => println(s"${name}さんは${age}歳です")
    }
  }
}

case class Person(name: String, age: Int)
