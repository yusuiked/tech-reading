# コレクションと配列

## コレクションフレームワーク

物の集まりを表現するコレクションに対して、基本的でかつ汎用性の高いデータ構造やアルゴリズムをフレームワークとして提供するAPI。
大きくは分類すると、以下のようになる。

| |ハッシュテーブル|配列|ツリー|リンクリスト|ハッシュテーブル+リンクリスト|
|-|:--------------:|:--:|:----:|:----------:|:---------------------------:|
|セット|HashSet| |TreeSet| |LinkedHashSet|
|リスト| |ArrayList| |LinkedList| |
|デック| |ArrayDeque| |LinkedList| |
|マップ|HashMap| |TreeMap| |LinkedHashMap|

## 変数の型をインタフェース型にする意味

変数の型をインタフェース型にすることで、参照先のオブジェクトを具象クラスのインスタンスとして見立てるのではなく、インタフェースとして振る舞うオブジェクトとして見立てる意図の表明になる。

また、コードから具象クラスへの依存を減らせる。具象クラスは本質的に実装を持つため、変化しやすい存在。一方、インタフェースは変化しにくい部分をインタフェースとして規定するのが本質のため、変更の影響を受けにくいコードになるため。

## リスト

Listインタフェースの代表的なメソッドは以下のとおり。

* add
* contains
* get
* indexOf
* lastIndexOf
* remove
* set
* size

Listインタフェースを実装する具象クラスは、次の2つがある。

* ArrayList
* LinkedList

## ArrayList

* 良い点
    * インデックスを指定して要素を読み出す速度が速い[get]
    * インデックスを指定して要素を書き換える速度が速い[set]
    * 先頭から順にすべての要素をなめる処理が速い
* 悪い点
    * 要素の挿入が遅いことがある（先頭に近い位置への挿入は遅い。末尾に近い位置への挿入は速い時もあるが、遅い時もある）[add]
    * 要素の削除が遅いことがある（先頭に近い位置の削除ほど遅く、末尾に近い位置の削除ほど速い。最後尾の削除は高速。）[remove]
* その他
    * 条件に合致した要素を検索する処理の速度は特別速くはない（工夫によりかなり速くもできる）[contains,indexOf,lastIndexOf]

* 留意点
    * 先頭に近い位置へ要素を追加・削除するほど移動の手間がかかるので、要素数が多いArrayListほど処理が重くなる。
    * 要素の挿入は、ArrayListのデータ構造が連続したメモリ領域を必要とするため、新しい連続メモリ領域を確保して、そこに古い領域のデータをコピーする動作を取る。これは、要素数が多い場合に非常に重い処理になるので、予め要素数の上限がわかっている場合は初期サイズを指定できるコンストラクタを使うことで最確保の無駄を防げる。

## LinkedList

* 良い点
    * 要素の挿入が速い（ただし、多くの場合、挿入の前に検索があることに注意）[add]
    * 要素の削除が速い（ただし、多くの場合、挿入の前に検索があることに注意）[remove]
* 悪い点
    * インデックスを指定して要素を読み出す速度が速くはない[get]
    * インデックスを指定して要素を書き換える速度が速くはない[set]
    * 条件に合致した要素を検索する処理の速度は速くはない[contains,indexOf,lastIndexOf]

* 留意点
    * n番目の要素へアクセスするには、先頭からn個のリンクを辿る必要があるため、要素数が多いほど効率が落ちる。
    * nがリスト全体の真ん中より前か後ろかに応じて、辿る位置を先頭か末尾かを切り替えるような実装になっているため、リストの真ん中当たりにある場合が一番アクセスが遅くなる。
    * 要素の追加と削除は、リンクを付け替えるだけで済む。リンクの付け替えは軽い処理。メモリ領域の再確保も不要（要素ごとにメモリを割り当てるため）
    * 挿入処理の前にインデックスでアクセスするため、リンクを辿る処理が必要なので、挿入処理は速い、という理由だけで一辺倒に使わないようにすること。

## Listの利用方針

* 要素の読み込みが中心（要素すべてを舐める処理や検索）の場合、ArrayListの方が効率的
* 要素の挿入や削除の頻度が高い場合、LinkedListの方が効率的
* 要素の書き換え処理が多い場合、ArrayListの方が効率的
* リストの先頭への追加や削除は、LinkedListでは常套手段だが、ArrayListでは禁じ手。

## バイナリサーチ

* Collections#sortをしてから、Collections#binarySearchを使う。
* 要素数が少ない場合、リニアサーチとバイナリサーチの速度差はほとんどない。処理が単純な分、リニアサーチが早い場合もある。

## マップ

Mapインタフェースの代表的なメソッドは以下のとおり。

* containsKey
* containsValue
* entrySet
* get
* keySet
* put
* remove
* size
* values

Mapインタフェースを実装する具象クラスは、次の3つがある。

* HashMap
* LinkedHashMap
* TreeMap

## HashMap

* 内部動作
    * 内部的に配列（ハッシュ表）を持っている。
    * キーをハッシュ関数に通して出力を得る。これがハッシュ表のインデックスになる。
    * ハッシュ表の得られたインデックス位置に、キーと値のペアを格納する。
* 性能
    * ハッシュ関数の得られる値の範囲で、同じ出力値になった場合にコンフリクトが起こる。
    * いいハッシュ関数ほど、コンフリクトの確率が低い（出力値の偏りが少ない）が、コンフリクトが起こった時HashMapでは内部にリンクリストを形成する。getした時はハッシュ値でインデックスをとった後、リンクリストをたどって、キーの完全一致の比較をして求めるキーと値のペアを見つける。
    * Java6のHashMapのハッシュ表のサイズは16でデフォルト値設定されている。HashMapは自動的にハッシュ表のサイズを拡張するが、この処理は軽い処理ではない。（すべての既存要素のハッシュ関数の再計算が必要になるため）これを防ぐため、コンストラクタで最初に適切なハッシュ表のサイズを指定して使う。

## LinkedHashMap

* 内部動作
    * ハッシュ表に加えて、LinkedListと同じリンクリストを持っており、これで順序を保持する。
    * put時に、ハッシュ表とリンクリストの両方に要素を追加する。リンクリストに対する要素の追加は、単なる末尾への追加なので、高速に動作する。
    * get時はHashMapと同様にハッシュ表から要素を検索する。
    * 要素を全て列挙する処理は、リンクリストをなめる処理になる。要素を追加した順序で列挙する。
    * コンストラクタの第3引数のaccessOrderにtrueを指定すると、リンクリストの順序がアクセスした順（getもしくはputしたタイミング）になる。このモードは、get時にハッシュ表から要素を探した後、リンクリストの該当要素を削除して先頭に追加する。要素の追加や検索の処理速度が少し遅くなる。

## TreeMap

LinkedHashMapで得られる順序ではなく、数値の大小や文字列の辞書順などの順で扱いたい場合は、Treemapを使う。TreeMapの要素のキーは、 *比較可能* でなければいけない。

* 内部動作
    * JavaのTreeMapは、赤黒木と呼ばれるツリー構造で、2分探索木の一種。
    * 要素を追加する場合、左のノードが小さくなるように並べる。
    * 検索する場合は、左下の要素から開始して、その親ノードと右の子ノードをなめる。その後、1つ上に階層を上がり、右の子孫ノードを同じ規則でなめる。
    * この処理は、再帰構造を利用しているため簡易なコードで実現されている。
    * 要素を追加する時に暗黙に検索処理を行なっている。要素の検索時に起きる処理は要素の追加時に走る処理とまったく同じ。
* 性能
    * 2分探索木の高さに比例する。高ければ高いほど、比較する要素が増えるので検索処理が遅くなる。
    * 理想的に動作した場合、バイナリサーチとアルゴリズム上同じ速度になる。HashMapほど速くはないが、実用上十分な速度。
    * 正確には、赤黒木というツリー構造のため高さがバランシングされるようになっているため、TreeMapは追加も検索も十分に速いデータ構造になっている。

## マップクラスの利用の指針

* 順序が不要な場合
    * HashMap（追加や検索が速い）
* 追加した順序やアクセスした順序が欲しい場合
    * LinkedHashMap
* 任意の比較規則に従った順序が欲しい場合
    * TreeMap

## NavigableMap

Java6で、キーの部分検索で近いキーを検索できるNavigableMapインタフェースが追加された。

* ceilingEntry
* ceilingKey
* higherEntry
* higherKey
* floorEntry
* floorKey
* lowerEntry
* lowerKey
* headMap
* tailMap

などのメソッドが使える。

ex). [コード例](../../../../../commits/d9e4e14fac8ba11ccf669b13c29db509979ea9ed)

## セット

集合を表すコレクション。重複のない要素の集まり。順序もない。

Setインタフェースを実装した具象クラスは以下のとおり。

* HashSet
* LinkedHashSet
* TreeSet

Mapインタフェースを実装した具象クラスと似ている。抽象的な意味は異なるが、マップのキーだけに注目すると、抽象的な意味も合致する。
そのため、JavaではMapインタフェースの実装クラスをそのままSetインタフェースで利用している。具象クラスの名前が似ているのは偶然ではなく、内部実装が同じだから。

具象クラスの性質は、マップのそれと全く同じなので、利用の指針も同じ。
セット自体は要素が順序どおりに並ぶことを抽象的な概念として持っていないが、順序どおりに並ぶことを禁じるものではない。要素に順序を持つLinkedHashSetとTreeSetはセットの概念に違反しているわけではない。

Setインタフェースの代表的なメソッドは以下のとおり。

* add
* contains
* remove
* size

## スタック、キュー、デック

* java.util.Stack クラスは、古いクラスなので、スタック構造のコレクションが欲しい場合は、デック（java.utilDeque インタフェースの実装クラス）で代用するか、Listで代用する。
* キューは java.util.Queue インタフェースの実装クラスを使う。
* デックは java.util.Deque インタフェースの実装クラスを使う。

## イテレータ

要素に対して繰り返して操作する場合は、以下の3パターンがある。

* 拡張for文を使う
* イテレータを使う
* インデックスでforループを回す（非推奨）
    * 順序が必要なため、リストにのみ適用できる繰返し処理のため。

コレクション自体に順序の概念が必須でなく、単にモノの集まりという抽象概念しかないのであれば、繰り返し処理に必要な処理は、要素を1つずつ取り出すという概念のみ。
これをイテレータと呼ぶ。

Javaでは、 java.util.Iterator インタフェースを使う。

* boolean hasNext()
* E next()
* void remove()

が主なメソッド。

## 拡張for構文を使えない場合

拡張for構文は、イテレータの簡易記法（シンタックスシュガー）。
次のような場合に、拡張for構文ではなくイテレータを使う必要がある。

* コレクションの要素を逆になめる場合
    * java.util.ListIterator インタフェースを使う
* ループを途中で中断して抜けた後、再び同じ位置からループを再開したい場合
    * 途中で抜ける時にIteratorオブジェクトを保持しておいて、後でまた参照すれば良い
* コレクションの要素をなめるループの中で、要素の追加や削除を行う場合
    * 拡張for構文の中で要素の中を更新すると、ConcurrentModificationException が発生する。（典型的なバグ）
    * インデックスでループを回しながらコレクションの要素を更新すると、ループの回数も変化するため、すべての要素を操作できないのでバグ。
    * 一般に、コレクションの要素をなめる処理中に、要素の追加や削除のような要素の数を変化させる処理を行うことは危険。
    * リストの具象クラスによっては、ループ内で挿入処理を繰り返すコードは遅くなることも注意。
    * 消す場合はclear()メソッドを使ったほうが良い。
    * 順序がないメソッドで追加する場合は、追加すべき要素の一時的なコレクションを作成してから、まとめてaddAll()などで追加するのが定石。

## コレクションの技法

一般に引数で渡ってくるオブジェクトを書き換えるメソッドは良い習慣ではない。破壊的なメソッド、副作用があるメソッドと呼ばれる。
呼び出し側でオブジェクトが変更されるかどうかを気にしなければならないため。

引数のオブジェクトを変更しないメソッドを関数的と呼ぶ。一般論では、関数的なメソッドのほうが良いとされるが、状況によっては非効率にもなるため、絶対にどちらが良いと断言できるものではない。

[コード例](../../../../../commits/69b4e4079104deffc742fd6ff73c1c0dbd26b102)

## 変更不能コレクション

メソッドに渡したコレクションが破壊されないことを保証できると便利。Collectionsクラスに、コレクションを変更不能に変換できるクラスメソッドがある。

* unmodifiableListなど

これを使うと、変更不能リストを渡すことができる。このコレクションに対して変更処理を行うと、実行時にUnsupportedOperationException例外が発生して、予期せぬ破壊的なメソッドを検出できる。

Collectionsクラスには他にも、同期版コレクションへの変換ができるsynchronizedCollectionメソッドなど、便利なメソッドがあるので、一度眼を通すこと。
変更不能コレクションでも、参照先のオブジェクトの変更は防げない。

```
#!java

void rejectLongString(List<StringBuilder> list, int n) {
    for (StringBuilder sb : list) {
        if (sb.length() > n) {
            sb.setLength(n);
        }
    }
}

// 呼び出し側
reject(Collections.unmodifiableList(list), 3);
```

これを防ぐには、要素自体を不変オブジェクトにする必要がある。

## コレクションの初期化記法

以下にコレクションを簡易に初期化する2つの記法を示す。

```
#!java

// コレクションの初期化（配列を利用）
List<StringBuilder> list = Arrays.asList( new StringBuilder[]{ new StringBuilder("foo"),
                                                               new StringBuilder("bar"),
                                                               new StringBuilder("baz") });

// コレクションの初期化（無名クラス + staticイニシャライザ）
List<StringBuilder> slist = new ArrayList<StringBuilder>() {
    {
        add(new StringBuilder("foo"));
        add(new StringBuilder("bar"));
        add(new StringBuilder("baz"));
    }
};

List<StringBuilder> slist = Collections.unmodifiableList(new ArrayList<StringBuilder>() {
    {
        add(new StringBuilder("foo"));
        add(new StringBuilder("bar"));
        add(new StringBuilder("baz"));
    }
});
```

## ソートとアルゴリズム（高階関数の概念）

リストのバイナリサーチにおいて、事前にソートする必要があることは前述のとおり。リストをソートするには、Collectionsクラスのsortクラスメソッドを使える。
sortメソッドには2つの形式がある。

```
#!java

// Collectionsクラスのsortメソッドの定義
<T extends Comparable<? super T>> void sort(List<T> list);
<t> void sort(List<T> list, Comparator<? super T> c);
```

前者はリストの要素の型がComparableインタフェースを実装している必要性を示している。後者は第2引数に渡すオブジェクトがComparator型であることを示している。
要素の型がComparableインタフェース（java.lang.Comparable）を実装していることの意味は要素同士を比較可能という意味。要素同士を比較可能であれば要素を並べる基準が得られる。
後者のsortメソッドは要素同士を比較するためのメソッドを持つオブジェクトを引数に渡してソートを実現する。

特定のメソッドを渡すことを目的とするオブジェクトを *ファンクタ* と呼ぶ。専門的には、関数に渡す関数のことを *高階関数（higher order function）* と呼ぶ。アルゴリズムを移譲の形で外部に追い出す、いわゆるストラテジパターンの関数版。Javaは関数（メソッド）だけを取り出して引数に渡すことができないため、関数メソッドを持つインタフェースを定義して、その実装オブジェクト（ファンクタ）を引数パラメータで渡すことで高階関数相当の処理を実現するのが定石。

```
#!java

// Comparable によるソート
List<String> list = new ArrayList<String>() {
	{
		add("foo");
		add("bar");
		add("bazbazbazbaz");
		add("foofoo");
		add("barbarbar");
		add("baz");
	}
};
Collections.sort(list);
for (String s : list) {
	System.out.println(s);
}
```

Stringクラス自身が持つ比較基準でソートする。文字列オブジェクトは辞書順でソートする基準を持つのでリストは辞書順に並ぶ。
ファンクタを使う例は以下。

```
#!java

// Comparatorによるソート
private static class StringLengthComparator<T extends String> implements Comparator<T> {

	@Override
	public int compare(String o1, String o2) {
		return o1.length() - o2.length();
	}
}

public static void main(String[] args) {
	List<String> list = new ArrayList<String>() {
		{
			add("foo");
			add("bar");
			add("bazbazbazbaz");
			add("foofoo");
			add("barbarbar");
			add("baz");
		}
	};
	Collections.sort(list, new StringLengthComparator<String>());
	for (String s : list) {
		System.out.println(s);
	}
}
```

多くの場合、ファンクタには無名クラスを使う。無名クラスを使いsortメソッドを呼ぶ例を示す。

```
#!java

// Comparatorによるソート（無名クラス版）
List<String> list = new ArrayList<String>() {
	{
		add("foo");
		add("bar");
		add("bazbazbazbaz");
		add("foofoo");
		add("barbarbar");
		add("baz");
	}
};
Collections.sort(list, new Comparator<String>() {
	@Override public int compare(String o1, String o2) {
		return o1.length() - o2.length();
	}
});
for (String s : list) {
	System.out.println(s);
}
```

ComparatorのファンクタはTreeMapのコンストラクタに渡してTreeMapのソート基準にも使える。
```
#!java

TreeMap(Comparator<? super K> comparator);
```

## コレクションと歴史的コード

コレクションAPIはJava1.2でコレクションフレームワークとして整理された経緯がある。それまでに集合を扱うクラスとして使用されていたクラスがあり、現在は非推奨だが存在している。古いクラスは新しいコレクションフレームワークに書き換えることができる。以下にその対応を示す。

|古いクラス|新しいクラス|
|----------|------------|
|Vector|ArrayList|
|HashTable|HashMap|
|Stack|ArrayDeque or ArrayList|
|Enumeration|Iterator|
|Dictionary抽象クラス|Mapインタフェース|

古いAPIには、Enumerationを引数に取るメソッドがある。

```
#!java

public SequenceInputStream(Enumeration<? extends InputStream> e)
```

CollectionクラスのenumerationクラスメソッドでコレクションオブジェクトをEnumerationオブジェクトに変換できる。
また、Collectionクラスのlistクラスメソッドで、EnumerationオブジェクトをArrayListオブジェクトに変換できる。

## 配列

配列はコレクションよりもよりプリミティブな言語機能。したがって、配列を非常に上手く使うと高速な実装になる可能性がある。
ただし、その高速性は安全さと可読性を犠牲にした速度。しかも、失うものに比べて得られる性能はごく僅か。

* 配列は、Javaが言語機能としてプリミティブな機能を提供しているだけと割り切り、一般の開発者は高レベルに構築されたコレクションフレームワークを使うべき。
* 高レベルAPIの内部実装に配列を使う（ArrayListの実装に配列を使うように）は許容範囲だが、APIとして配列を外に見せることは非推奨。メソッドの戻り値は引数に配列を使おうと考えていたら、コレクションフレームワークのList型にすることを検討する。
* 配列とリストは簡単に相互変換ができる。
* 配列にできてコレクションにできないこと
    * 基本型を要素とすることができる。

配列は、オブジェクトの並びではなく、変数の並び。参照型変数に要素オブジェクトのポインタが入っているイメージ。
各要素の変数の値は初期状態ではnull。要素数が0の配列も作成可能で、空の配列と呼ぶ。空の配列を使うべき場面でnullを使うことはバグの元
のなのでやめるべき。

## 配列のイテレーション

インデックス値を使うことがない限り、（多くの場合、必要ない）拡張for構文を利用する。
```
#!java

void method(StringBuilder[] arr) {
    for (StringBuilder sb :arr) {
        System.out.println(sb);
    }
}

void method(StringBuilder[] arr) {
    for (int i = 0; i < arr.length; i++) {
        System.out.println(arr[i]);
    }
}
```

## 配列の初期化

配列をnewで初期化すると、要素の型に応じたデフォルト値になる。
配列はnewによる生成以外に、特別な初期化構文を持っている。

```
#!java

int[] arr = {0, 1, 2};

// 要素が参照型
StringBuilder[] arr = {new StringBuilder("012"), new StringBuilder("345")};
// 最後に余計なカンマを書いても無視される
StringBuilder[] arr = {new StringBuilder("012"), new StringBuilder("345"),};

// 愛列生成時の初期化子の例
StringBuilder[] arr;
arr = new StringBuilder[]{ new StringBuilder("012"), new StringBuilder("345") };
// メソッド呼び出しの引数の例
method(new StringBuilder[]{ new StringBuilder("012"), new StringBuilder("345") });
```

## 多次元配列

人間の理解力の問題から、特殊な用途を除いて3次元配列よりも大きな次元の配列は推奨しない。

```
#!java

// 3次元配列のコード例
StringBuilder[][][] arr;
arr = new StringBuilder[2][][];
arr[0] = new StringBuilder[2][];
arr[1] = new StringBuilder[2][];
arr[0][0] = new StringBuilder[2];
arr[0][1] = new StringBuilder[2];
arr[1][0] = new StringBuilder[2];
arr[1][1] = new StringBuilder[2];
arr[0][0][0] = new StringBuilder("000");
arr[0][0][1] = new StringBuilder("001");
arr[0][1][0] = new StringBuilder("010");
arr[0][1][1] = new StringBuilder("011");
arr[1][0][0] = new StringBuilder("100");
arr[1][0][1] = new StringBuilder("101");
arr[1][1][0] = new StringBuilder("110");
arr[1][1][1] = new StringBuilder("111");
// 個数を揃える必要はない
// 個数が揃っている場合は、一気に初期化できる
StringBuilder[][][] arr = new StringBuilder[2][2][2];
arr[0][0][0] = new StringBuilder("000");
arr[0][0][1] = new StringBuilder("001");
arr[0][1][0] = new StringBuilder("010");
arr[0][1][1] = new StringBuilder("011");
arr[1][0][0] = new StringBuilder("100");
arr[1][0][1] = new StringBuilder("101");
arr[1][1][0] = new StringBuilder("110");
arr[1][1][1] = new StringBuilder("111");
```

## 多次元配列の初期化子

```
#!java

StringBuilder[][][] arr = {
    {
        { new StringBuilder("000"), new StringBuilder("001") },
        { new StringBuilder("010"), new StringBuilder("011") },
    },
    {
        { new StringBuilder("100"), new StringBuilder("101") },
        { new StringBuilder("110"), new StringBuilder("111") },
    },
};
```

## 配列のコピー

以下の4つの方法がある。

* System.arraycopyメソッドを使う
* Arrays.copyOfメソッドを使う（Java6以降）
* Object.cloneメソッドを使う
* 自分でイテレーションしてコピー（手動コピー）

```
#!java

// System.arraycopyメソッドの定義
void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
// srcで指定した配列のsrcPos位置から、destで指定した配列のdestPosの位置へ、lengthの数の要素をコピー
srcとdestは同じ配列を指定することもできる

// System.arraycopyメソッドの例
StringBuilder[] src = { new StringBuilder("0"), new StringBuilder("1"), new StringBuilder("2") };
StringBuilder[] dest = new StringBuilder[src.length];
System.arraycopy(src, 0, dest, 0, src.length);
for (StringBuilder sb : dest) {
    System.out.println(sb);
}

// Java6以降
StringBuilder[] src = { new StringBuilder("0"), new StringBuilder("1"), new StringBuilder("2") };
StringBuilder[] dest = Arrays.copyOf(src, src.length);
for (StringBuilder sb : dest) {
    System.out.println(sb);
}
```

コピー先の配列の長さが足りないなどで書き込み位置が終端を超える場合はArraysIndexOutOfBoundsException実行時例外が発生する。

配列要素が参照型の場合、System.arraycopyによる配列のコピーは要素だけをコピーする。専門的には **shallow copy** と言う。参照先のオブジェクトを一緒にコピーする動作を **deep copy** と呼ぶ。 deep copy をするには、自分でイテレーションして参照先のオブジェクトのコピーも同時に行う必要がある。

## 配列とコレクションの相互変換

* 配列 → Listオブジェクト

Arrays.asListクラスメソッドを使う。

```
#!java

StringBuilder[] src = { new StringBuilder("0"), new StringBuilder("1"), new StringBuilder("2") };
List<StringBuilder> dest = Arrays.asList(src);
```
asListメソッドは、内部的に配列のコピーをしていないため、生成したリストは変更不能（要素の追加や削除ができない）。（shallow copy ですらなく、同じ配列を内部で参照し続ける　→だから「as」）
生成したリストに変更を加えようとすると、UnsupportedOperationException実行時例外が発生する。
同じ配列を参照しているので、配列側から要素を書き換えると、Listから要素の変更が見える。

shallow copy をするためには、自前でコピーする必要がある。CollectionクラスのaddAllメソッドを使う。
```
#!java

// 配列からListオブジェクトに変換（shallow copy）
StringBuilder[] src = { new StringBuilder("0"), new StringBuilder("1"), new StringBuilder("2") };
List<StringBuilder> dest = new ArrayList<StringBuilder>();
Colletions.addAll(dest, src);
```

* Collectionオブジェクト → 配列

toArrayメソッドを使う。タイプセーフにするために、引数のあるtoArrayメソッドの利用を推奨する。 → 「to」なのでコピーする。

```
#!java

List<StringBuilder> src = new ArrayList<StringBuilder>() {
    {
        add(new StringBuilder("0"));
        add(new StringBuilder("1"));
        add(new StringBuilder("2"));
    }
};
StringBuilder[] dest = src.toArray(new StringBuilder[src.size()]);
```
