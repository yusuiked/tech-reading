# モジュールとは

クラスとモジュールの違いは、

- モジュールはインスタンスを持つことができない
- モジュールは継承できない

という点。

## モジュールの使い方

### 名前空間の提供

**モジュール名**.**メソッド名** という形式で利用するメソッドを**モジュール関数**と呼ぶ。

```sh-session
>> p FileTest.exist?("/usr/bin/ruby")
true
=> true
>> p FileTest.size("/usr/bin/ruby")
51936
=> 51936
```

### Mix-in による機能の提供

- 似たような機能を持っているが同じクラスとは考えたくない
- Ruby は多重継承はできない

こういったケースでは Mix-in の方が柔軟に対応できる。

## Mix-in

### メソッド検索のルール

- 継承の関係と同じように、元のクラスで同じ名前のメソッドが定義されている場合はそちらが優先される
- 同じクラスに複数のモジュールをインクルードした場合は、あとからインクルードしたものが優先される
- インクルードが入れ子になった場合も、検索順は一列に並ぶ。
- 同じモジュールを2回以上インクルードした場合、2回目以降は無視される

```ruby
module M
  def meth
    "M#meth"
  end
end

class C
  include M # M をインクルードする
  def meth
    "C#meth"
  end
end

c = C.new
p c.meth  #=> C#meth
```

```ruby
module M1
end

module M2
end

class C
  include M1
  include M2
end

p C.ancestors  #=> [C, M2, M1, Object, Kernel, BasicObject]
```

```ruby
module M1
end

module M2
end

module M3
  include M2
end

class C
  include M1
  include M3
end

p C.ancestors  #=> [C, M3, M2, M1, Object, Kernel, BasicObject]
```

```ruby
module M1
end

module M2
end

class C
  include M1
  include M2
  include M1
end

p C.ancestors  #=> [C, M2, M1, Object, Kernel, BasicObject]
```

### `extend` メソッド

モジュールに定義されたメソッドを特異メソッドとしてオブジェクトに追加する場合は、 `Object#extend` メソッドを使う。

```ruby
module Edition
  def edition(n)
    "#{self} 第#{n}版"
  end
end

str = "たのしいRuby"
str.extend(Edition) # module を Object に Mix-in する

p str.edition(4)
```

### クラスと Mix-in

Ruby のクラスは、それ自身が `Class` クラスのオブジェクトとして提供されている。また、クラスメソッドは、クラスをレシーバとするメソッドである。つまりクラスメソッドはクラスオブジェクトに対するインスタンスメソッド。そのようなメソッドは以下のもの。

- `Class` クラスのインスタンスメソッド
- クラスオブジェクトの特異メソッド

クラスを継承すると、これらのメソッドはサブクラスにもクラスメソッドとして引き継がれる。サブクラスで特異メソッドを追加することによって、クラスに新しいクラスメソッドを追加できる。

上でクラスメソッドを追加する構文を記したが、その他にクラスオブジェクトに対して `extend` メソッドを使うことでクラスメソッドを追加できる。

```ruby
module ClassMethods
  def cmethod
    "class method"
  end
end

module InstanceMethods
  def imethod
    "instance method"
  end
end

class MyClass
  # extend するとクラスメソッドを追加できる
  extend ClassMethods
  # include するとインスタンスメソッドを追加できる
  include InstanceMethods
  # Ruby のすべてのメソッド呼び出しは、レシーバとなるオブジェクトを伴ってじっこうされる。
  # つまり、レシーバの種類によって便宜的に「クラスメソッド」「インスタンスメソッド」という風に呼び分けているに過ぎない。
end

p MyClass.cmethod      #=> "class method"
p MyClass.new.imethod  #=> "instance method"
```
