# JSP

* JSP のエンコーディング指定

```
#!jsp

<%-- エンコーディングはUTF-8, ページはプレーンテキスト --%>
<%@ page pageEncoding="utf-8" contentType="text/plain; charset=utf-8" %>
```

## JSPの利用方針

JSPはその紆余曲折の歴史により現状では複雑な仕様になっている。単純な規則でデータを表示するテンプレート言語の観点から見るとJSPはオーバースペックといえる。JSPはデータを表示する以外に、データを変更したりデータベースに問い合わせることも出来る。そのような仕様は柔軟だが、「**良い制約**」がユーザーに強制されず、書き手によって表現が大きく異なったり、パフォーマンスが悪かったり、テストが困難になったりする。

JSPのように「**いい制約**」が少ない言語では記述スタイルに準拠する事が重要。JSPは単純なテンプレート言語として利用するのがベストプラクティスであるという前提のもとで使う。

## 基本原理

### JSP実行の工程

JSPは本質的にはサーブレットと等価だと考えて問題ない。JSPは最終的にはJavaクラスとして動作する。サーブレットから参照可能なデータ（リクエストパラメータやセッションデータ）はJSPからも参照可能。JSPはJSPサーブレットというJSPの元締めのサーブレットクラスによって管理及び実行される。以下の工程でJSPが実行される。

1. マッピング
2. JSPのコンパイル
3. JSPインスタンスの生成・実行

JSPのコンパイルは、サーブレットコンテナに同封されているJSPコンパイラが行う。コンパイル済みの場合、この処理をスキップする。JSPコンパイラはJSPを次の形のJavaコードにコンパイルする。

```
#!java

class コンパイル後のサーブレット名 extends HttpServlet {
    public final void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // JSPに記述された処理
    }
}
```

Javaコードにコンパイルできたら、JavaコンパイラでJavaクラスにコンパイルして、クラスローダからロードできるようにする。

JSPのコンパイルが完了すると、コンパイルしたクラスのインスタンスを生成する。このインスタンスをJSPインスタンスと呼ぶ。

例えば次のようなJSPがあるとする。

```
#!java

// sample.jsp:
現在「<%= new java.util.Date() %>」です。
```

このJSPは次のようにコンパイルされる。このクラスのインスタンスの`service`メソッドを呼び出すことでJSPの本体の処理を実行できることがわかる。

```
#!java

class SampleJspServlet.java extends HttpServlet {
    public final void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out.write("現在「");
        out.write(String.valueOf(new java.util.Date()));
        out.write("」です。");
    }
}
```

この処理を行うJSPサーブレットの実装は以下のようになる。

```
#!java

class JspServlet extends HttpServlet {
    public final void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストされたJSPのファイル名を取得
        String jspFileName = ...;
        // JSPのファイル名からコンパイル後のクラス名を取得
        String jspClassName = getJspClassName(jspFileName);
        // クラス名からJSPインスタンスを取得
        HttpServlet servlet = getJspServlet(jspClassName);
        if (servlet == null) {
            Class<?> jspClass = getCompiledJsp(jspFileName);
            if (jspClass == null) {
                jspClass = compileJsp(jspFileName);
            }
            // JSPインスタンスを生成
            servlet = (HttpServlet) jspClass.newInstance();
            registerJspServlet(servlet);
        }
        servlet.service(request, response); // 処理を移譲        
    }
}
```

### JSPのコンパイル規則

#### 静的コンテンツ

そのまま内容を出力するコードにコンパイルされる。

```
#!java

// JSP:
これはテキストです。

// Java:
class コンパイル後のサーブレット名 extends HttpServlet {
    public final void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out.write("これはテキストです。");
    }
}
```

#### スクリプトレット

スクリプトの一種であるスクリプトレットは制御構文や複雑なJavaコードを実行するための構文。

```
#!java

// JSP:
<%
    for (int i = 0; i < 10; i++) {
        if (i % 2 == 0) {
            out.write(String.valueOf(i));
            out.write("は偶数です。");
        }
    }
%>

// Java:
class コンパイル後のサーブレット名 extends HttpServlet {
    public final void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                out.write(String.valueOf(i));
                out.write("は偶数です。");
            }
        }
    }
}
```

#### 式

スクリプトの一種である式はJavaの式を評価してその文字列表現を出力するための構文。

```
#!java

// JSP:
半径13cmの円周は約<%= (int) (13 * 2 * Math.PI) %>cmです。

// Java:
class コンパイル後のサーブレット名 extends HttpServlet {
    public final void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out.write("半径13cmの円周は約");
        out.write(String.valueOf((int) (13 * 2 * Math.PI)));
        out.write("cmです。");
    }
}
```

#### 宣言

スクリプトの一種である宣言は、他のスクリプトで使うメソッドやフィールドを宣言するための構文。

```
#!java

// JSP:
<%-- 宣言 --%>
<%!
    int square(int x) {
        return x * x;
    }
%>
<%-- 静的コンテンツ + 式 --%>
4の2乗は<%= square(4) %>です。

// Java:
class コンパイル後のサーブレット名 extends HttpServlet {
    int square(int x) {
        return x * x;
    }
    public final void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out.write("4の2乗は");
        out.write(String.valueOf(square(4)));
        out.write("です。");
    }
}
```

上記で示したようなコンパイル結果は、実際にJavaファイルとして閲覧できる。サーブレットコンテナは要求されたJSPをJavaクラスにコンパイルする際、一時的にJavaファイルにコンパイルする。Tomcat ではこのファイルを`$TOMCAT/work`以下で見つけることができる。

## スコープ

サーブレットの章の属性を持つオブジェクトでサーブレットクラスの視点で状態の渡し方を記述した。内容としては同じ。

## 構文

JSPの動的コンテンツは次の構文で構成される。

* ディレクティブ
* コメント
* スクリプト
    * スクリプトレット
    * 式
    * 宣言
* アクション
    * 標準アクション
    * カスタムタグ（JSTL含む）
    * タグファイル
* EL（Expression Language, 式言語）

それぞれ主たる機能は異なるが、部分的に機能が重複している。まったく同じ結果を出すための記述方法が複数存在する。

### ディレクティブ

    <%@ ディレクティブ 属性=値 ... %>

#### page ディレクティブ

出力するページのメタ情報を指定する。

* pageEncoding 属性
    * JSPファイルが指定した文字コードで記述されていることを示す
* contentType 属性
    * Webブラウザが解釈するエンコーディングの指定
* import 属性
    * クラスをインポートできる
* session 属性
    * true ならJSPがセッションオブジェクトを自動で生成する。false が推奨。

#### taglib ディレクティブ

タグライブラリの仕様を宣言するディレクティブ。

* prefix 属性
    * 指定したプレフィクスでカスタムタグを指定する。
* uri 属性
    * タグライブラリの場所

#### include ディレクティブ

他のJSPを取り込むディレクティブ。

* ヘッダやフッタなどの共通部分を取り込む
* JSPの共通の処理を実行する

```
#!java

<%@ include file="header.jsp" %>
```

include アクションとの違いを以下に示す。

* include ディレクティブ
    * JSPのコンパイル時点で取り込まれる（静的に取り込まれる）
    * 組み込んだ側のJSPページと一体で処理されるので、処理のオーバーヘッドがない
* include アクション
    * リクエストのあった時点で取り込まれる（動的に取り込まれる）
    * 処理された結果が取り込まれる

### コメント

    <%-- コメント --%>

コメントは出力ページに出力されない情報を記述するための構文。

HTMLコメントと違い、JSPコメントは出力されたHTML上には残らない。

### スクリプト

Javaコードを記述してページ生成処理を記述する構文。3つの種類がある。

#### スクリプトレット

    <%
        Javaコード
    %>

Javaコードを実行してページを生成する構文。主に複雑な処理を実行し結果を出力するために使われる。

制御構文として使うこともある。スクリプトレットに記述するJavaコードは構文として正しい必要はなく、JSPからコンパイルされたJavaコードが最終的に構文として正しければ良い。

自由度が非常に高いスクリプトレットだが、次に述べる理由により使用は推奨しない。

* スクリプトレットを多用したJSPは煩雑になり可読性が低くなる。特別な知識を持たないWebデザイナがJSPを読むことも十分に考えられるため、意味不明な記号とJavaコードでWebデザイナをげんなりさせてはいけない。
* ドメインのロジックやデータベースにアクセスするコードがJSPに記述される可能性が出てくる。そのようなJSPは仕様変更に弱くエラー処理が大変。
* スクリプトレットの殆どはアクションとELに置き換えることができる。アクションとELで記述したJSPは、スクリプトレットで記述したJSPに比べて、可読性や保守性に優れる。

#### 式

    <%= 任意のJavaの文 %>

Javaの式を評価してその文字列表現を出力する構文。

スクリプトレットと比べて、Javaの式のみを受け付ける点と評価結果を`out.write(…)`で出力する点が異なる。自由度は低くなっておりそれほど危険でもないが、スクリプトレット同様、式はアクションとELでほぼ完全に置き換えることができるので使用しないことを推奨。

#### 宣言

    <%! Javaの宣言 %>

他のスクリプトで使うメソッドやフィールドを宣言する構文。

宣言で記述したフィールドの寿命はJSPインスタンスの寿命と同じ。

宣言で記述したメソッドやフィールドはそのJSPのみで有効。他のJSPで宣言したメソッドを、インクルードして呼び出すことは出来ない。（JSPのコンパイル規則を考えれば自明）

宣言はスクリプトレットや式に比べて自由度が低く、時には便利だが次の理由により推奨しない。

* 宣言で記述したフィールドの寿命が不明確。Tomcat を再起動したり、JSPインスタンスが作りなおされたりすると失われる。
* 宣言で記述したフィールドは、マルチスレッドでアクセスされることを想定しないことがある。JSPは基本的にマルチスレッドで動作するので、複数のスレッドが同じJSPインスタンスのフィールドにアクセスする。
* 宣言で記述したメソッドは、殆どの場合、未熟な共通化。他のJSPから使うことができないので、結局同じメソッドを複数個書くことになる。カスタムタグがより優れた手法であるため、宣言の使用は推奨できない。

#### 暗黙オブジェクト

|名前|クラス|
|----|------|
|`request`|`javax.servlet.http.HttpServletRequest`|
|`response`|`javax.servlet.http.HttpServletResponse`|
|`session`|`javax.servlet.http.HttpSession`|
|`application`|`javax.servlet.ServletContext`|
|`pageContext`|`javax.servlet.jsp.PageContext`|
|`out`|`javax.servlet.jsp.JspWriter`|
|`config`|`javax.servlet.ServletConfig`|
|`exception`|`java.lang.Throwable`|

暗黙オブジェクトは、宣言済み変数のように使える。暗黙オブジェクトはコンパイルされたJSP本体の上部に単純に宣言されている。つまり、`service`メソッドで宣言されたローカル変数。

EL でもカスタムタグでも暗黙オブジェクトにアクセスできるため、スクリプトを使う理由にはならない。

### アクション

アクションは複雑な処理や共通処理を実行する構文。

    <接頭辞:タグ名 属性=値 ...>

アクションの実行は対応するJavaコードの実行だと考えて問題ない。実際、全てのアクション定義は最終的にはJavaコードに帰着する。スクリプトレットや式のようにJavaコードに依存した構文ではなく、XMLタグのような柔軟性が高く一貫性がある構文。可読性を損なうことなく複雑な処理をJSPに記述できる。

アクションには次の3つがある。

* カスタムタグ
* タグファイル
* 標準アクション

カスタムタグや標準アクションはJavaのコードで定義する。タグファイルはユーザーがJSPの構文を用いて記述する。JSPは最終的にJavaクラスにコンパイルされることを思い出すと、タグファイルも結局はJavaのコードで定義されることがわかるはず。

* Javaを意識させない構文
* スクリプトと同等の自由度（Javaのコードで定義できるため）
* 再利用可能

カスタムタグやタグファイルの集合をタグライブラリと呼ぶ。

#### カスタムタグ

カスタムタグはJavaコードで定義できるアクション。カスタムタグは基本的に開発者が定義するものだが、JSPには標準でJSTLというタグライブラリがある。

例えば、JSTLのcoreタグライブラリで`<c:out value="6<x*2のときxの範囲を答えよ"/>`といった記述をすると、値の出力の際にXMLの特殊記号をエスケープして出力できる。

#### タグファイル

タグファイルはJSPで定義できるアクション。実行時にJavaクラスにコンパイルされる。タグファイルの集まりをタグライブラリとして扱える。

`/WEB-INF/tags`というディレクトリにタグファイルが格納されている場合、次のように tablib ディレクティブを記述することにより、タグファイルを呼び出すことができる。

    <%@ taglib prefix="pj" tagdir="/WEB-INF/tags" %>

タグファイルは他のアクション同様、属性を指定して呼び出せる。属性を指定するには、予め attribute ディレクティブで属性の宣言を行う必要がある。

```
#!jsp

// term.tag
<%@ attribute name="name" %>
<span style="text-decoration: underline"><%= name %></span>

// JSP
<pj:term name="バイト"/>は通常、8<pj:term name="ビット"/>を意味するが、特にそのことを明示する場合は1<pj:term name="オクテット"/>を使う。
```

特定の処理を簡単に概念化、共通化できるのがタグファイルの最大の魅力。タグファイルの使用を推奨するが、過度の使用は禁物。本当に必要な時に使うべきであり、先を見越した共通化などは逆に手間がかかる。

#### 標準アクション

よく使用する標準的な操作を定義したもの。標準アクションはJSPの仕様として古くから存在しており、ページ生成に関するアクションと言うよりはページ制御に関するアクションがほとんど。なお、いくつかの標準アクションは、カスタムタグやELがより効果的な代替手段を提供している。

標準アクションはjspという接頭辞で呼び出すアクション。

    <jsp:アクション名 属性=値>

標準アクションには大きく分けて次の3つの種類がある。

* ページ制御に関するもの
    * `<jsp:forward>`, `<jsp:include>`
* ページ生成に関するもの
    * `<jsp:doBody>`
* 変数操作に関するもの
    * `<jsp:useBean>`, `<jsp:getProperty>`, `<jsp:setProperty>`

`<jsp:forward>`の中に`<jsp:param>`を記述することでパラメータを渡して転送できる。転送される側ではリクエストパラメータとして得られる。

`<jsp:include>`はページ生成処理を取り込んで処理を継続する。ヘッダやフッタを共通化して取り込むために使用される。`<jsp:param>`でパラメータも渡せる。

#### include 処理の注意

`<jsp:forward>`はエラー処理など特定の場面で使用することがあるが、`<jsp:include>`の使用は非常に限られる。その理由として include ディレクティブとタグファイルの存在がある。

上で述べたように include ディレクティブは`<jsp:include>`と同様に別のJSPを取り込むことができる。include ディレクティブはコンパイル時に静的に取り込むため実行時のオーバーヘッドがない。また、殆どの場合それで事足りる。そのため`<jsp:include>`より include ディレクティブの使用を推奨する。

更にタグファイルの存在によりJSPを取り込む発想そのものが時代遅れになりつつある。事実、上記のようにヘッダやフッタを共通化するにはタグファイルが最適。include ディレクティブに比べれば実行時に若干のオーバーヘッドがあるが、型安全な属性渡しが可能なため、include ディレクティブ、`<jsp:include>`に比べてかなり高い柔軟性を持つ。

```
#!jsp

// 悪いケース
<jsp:include page="header.jsp" />

// 良いケース
<%@ include file="header.jsp" %>

// 最良のケース
<pj:header />
```

#### jsp:doBody

ページ生成に関するアクションとしてよく使用されるのは`<jsp:doBody>`。`<jsp:doBody>`はタグファイルで使用するアクション。タグファイルから`<jsp:doBody>`を呼び出すと、そのタグファイルの呼び出し時に指定したボディを評価することができる。

`<jsp:doBody>`を利用することにより、特定の条件式を隠蔽したり、内容を囲い込むようなテンプレートの作成が可能になる。

```
#!jsp

// ifmobile.tag
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fx" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test=${fn:containsIgnoreCase(header['user-agent'], 'docomo')}">
    <jsp:doBody />
</c:if>

// index.jsp
<%@ taglib prefix="pj" tagdir="/WEB-INF/tags" %>
<pj:ifmobile>
    <jsp:forward page="mobile.jsp" />
</pj:ifmobile>
```

`<pj:ifmobile>`の呼び出し時にボディとして`<jsp:forward ..>`以下を渡している。このボディは即時に評価されず評価可能なJSPの断片としてタグファイルに渡され、ifmobile.tagは条件をチェックし、条件を満たした場合だけ`<jsp:doBody>`を呼び出す。このように複雑な条件式を隠蔽した条件文を実現できる。

もう1つの例として内容を囲い込むようなテンプレートをタグファイルで作成してみる。HTMLの記述で面倒なのは`<html>`や`<head>`の記述。これらはほとんど変化すること無く、変化するとしても`<head>`内の`<head>`の記述くらい。このような場合にタグファイルと`<jsp:doBody>`の組み合わせが効果的。

```
#!jsp

// document.tag
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" %>
<html>
    <head>
        <title><c:out value=${title}"/></title>
    </head>
    <body>
        <jsp:doBody />
    </body>
</html>

// sample.jsp
<%@ taglib prefix="pj" tagdir="/WEB-INF/tags" %>
<pj:document title="サンプル">
    <p>これはサンプルです。</p>
</pj:document>
```

上記の`document.tag`を利用することで、必要最低限のHTMLのみを記述すれば良くなる。

このように`<jsp:doBody>`は上手く利用すれば非常に効果を発揮する。

#### jsp:useBean

名前からは想像しにくいが、`jsp:useBean`はスコープからの変数の取得、インスタンス生成、スコープへの変数の格納を一度に行う奇妙なアクション。例として以下のJSPを考えてみる。

```
#!jsp

<jsp:useBean id="date" class="java.uitl.Date" scope="session"/>
${date}
```

このJSPは初回に表示した時刻を何度も表示し続ける。この時<jsp:useBean>は表示の度に以下の様なことを行う。

1. セッションスコープにdateという変数が存在するか確認する
2. date が存在する場合は、その変数をclass属性で指定したクラスにキャストする。キャスト出来なければ`ClassCastException`が発生する
3. date が存在しない場合は、class属性で指定したクラスをインスタンス化する。そのインスタンスをscope属性で指定されたスコープに格納する

Javaコードで記述すると以下のようになる。

```
#!jsp

<%
    Object var = pageContext.getAttribute("date", javax.servlet.jsp.PageContext.SESSION_SCOPE);
    if (var != null) {
        java.util.Date typedVar = (java.util.Date) var;
    } else {
        var = new java.util.Date();
        pageContext.setAttribute("date", var, javax.servlet.jsp.PageContext.SESSION_SCOPE);
    }
%>
```

id属性で指定する名前はページで一意である必要がある。なぜならid属性で指定する名前はJSPコンパイル時にJavaの変数名として展開されるため。

インスタンス化するBeanを指定する方法には、class属性でクラスを肢蹄する他に、beanName属性でbean名を指定する方法もある。

beanName属性が指定されていると、インスタンス化する際に、new式の代わりに`java.beans.Beansinstantiate`でインスタンス化する。beanName属性にはクラスあるいはシリアライズしたリソース名を指定できる。

#### jsp:useBean の型指定

以下のように行う。

    <jsp:useBean id="map" type="java.util.Map" class="java.util.HashMap"/>

上記のJSPは次のようなJavaコードにコンパイルされる。

```
#!java

class コンパイル後のサーブレット名 extends HttpServlet {
    public final void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ...
        java.util.Map map = (java.util.Map) pageContext.getAttribute("map");
        if (map == null) {
            map = new java.util.HashMap();
            pageContext.setAttriute("map", map);
        }
        …
    }
}
```

type属性を指定するとid属性で指定した名前の変数をtype属性で指定した型にキャストしようとする。キャスト出来ない場合は`ClassCastException`が発生する。

またid属性で指定した名前の変数がスコープに存在しない場合は例外を投げる。そのためid属性とtype属性を使用した`<jsp:useBean>`は、スコープに特定の型の変数が存在することを前提にすることになる。

`<jsp:useBean>`は、変数が存在しない時にインスタンスを生成するか例外を投げるかという2つの挙動がある。type属性で例外を投げる方は、防衛的プログラミングを実践する方法として有効。JSP処理の早い段階で不正なデータを発見できるため。

インスタンスを生成する場合は注意が必要。次のように複雑なデータを<jsp:useBean>で生成する場合にはスクリプトの使用を避けられない。

```
#!jsp

<jsp:useBean id="appConfig" class="java.util.HashMap"/>
<%
    if (appConfig.isEmpty()) {
        appConfig.put("key1", "value1");
        appConfig.put("key2", "value2");
    }
%>
</c:if>
${appConfig.key1}
${appConfig.key2}
```

このようなコードをJSPに書くのが良いのか悪いのかは設計思想の範疇かもしれないが、テンプレート言語としてのJSPの前提に従うならば、このJSPは悪いJSPになる。

class属性で実装クラスをプログラムに記述するのはDIの可能性を完全に無視することになる。そのようなJSPは仕様変更に弱く、DIで解決すべき依存性を表現するコードを多量に記述する必要がある。

まとめると、`<jsp:useBean>`の使用が許される条件は以下のようになる。

* id属性とtype属性を使用して防衛的プログラミングの効果が得られること
* class属性を使用する場合は、生成するオブジェクトがよく知られたシンプルなオブジェクトで、かつ寿命が短いこと
* beanName属性を使用してクラスローダからオブジェクトを取得すること

#### <jsp:getProperty>と<jsp:setProperty>

`<jsp:getProperty>`は`<jsp:useBean>`で作成したインスタンスのプロパティを取得するアクション。プロパティアクセスはJava Beansの仕様に従う。

name属性に`<jsp:useBean>`のid属性で指定した属性名を指定し、property属性に取得するプロパティ名を指定する。

```
#!jsp

<jsp:useBean id="now" class="java.util.Date"/>
<jsp:getProperty name="now" property="time"/>
```

`<jsp:setProperty>`は`<jsp:useBean>`で作成したインスタンスのプロパティを設定するアクション。

name属性とproperty属性は`<jsp:getProperty>`と同様に指定する。value属性にプロパティに設定する値を指定する。

```
#!jsp

<jsp:useBean id="now" class="java.util.Date"/>
<jsp:getProperty name="now" property="hours"/>
<jsp:setProperty name="now" property="hourts" value="1"/>
<jsp:getProperty name="now" property="hours"/>
```

`<jsp:useBean>`と同様、`<jsp:getProperty>`と`<jsp:setProperty>`もJavaのコードにコンパイルされる。その規則は単純で、name属性に指定した名前はJavaの変数名になっているので、その変数に対しプロパティのアクセッサメソッドを呼び出す。

`<jsp:getProperty>`と`<jsp:setProperty>`はリフレクションを利用しない純粋なJavaコードにコンパイルされるため、プロパティが存在しない（つまり該当のアクセッサメソッドが存在しない）バグを早期に発見できる。

`<jsp:getProperty>`と`<jsp:setProperty>`の前に`<jsp:useBean>`がない場合は、予め型を特定できないのでリフレクションを使用するコードにコンパイルされてしまう。そのため`<jsp:getProperty>`と`<jsp:setProperty>`の型安全性は`<jsp:useBean>`を併用しないと発揮されない。

ところが前述のように`<jsp:useBean>`の利用場面は限られているため、結局のところ`<jsp:getProperty>`と`<jsp:setProperty>`の利用場面も限られてしまう。特に`<jsp:getProperty>`はELのドット演算子でスマートに置き換えることができるので、`<jsp:getProperty>`の利用場面は非常に限られているといえる。

### EL

アクションは我々に優れた手段を提供したが、次の1点においてその優位性も疑わしいものになった。つまり、属性に値を渡すために、固定文字列か式による演算結果しか使えない点。

固定文字列は表現力が低すぎたり、あるいは高すぎたりする。式は安全でなく、保守性・可読性が低い問題がある。特にタグファイルの登場で、これまで以上にアクションの使用頻度が向上する中、式に変わる何らかの表現が必要であると結論された。

そこで登場したのがEL（Expression Language; 式言語）。ELは式ほどの表現力は持たないが、安全であり、保守性・可読性が高く、かつシンプルな言語。

ELはコンパクトな仕様で簡単に習得できる。ELはJavaランタイムに特化した言語ではなく、それ自体がランタイムに依存しない形で仕様として独立している。このためJSP以外の場面でも利用できる。（設定ファイルなど）

ELは式と同様、ページ生成とアクション属性指定の2つの用途がある。つまり式をほぼ完全にELで置き換えることができる。

```
#!jsp

// 式:
<c:if test="<%= request.getParameter("a") != null && request.getParameter("b") != null %>">
    <%= request.getParameter("a") %> + <%= request.getParameter("b") %> = <%= Integer.valueOf(request.getParameter("a")) + Integer.valueOf(request.getParameter("b")) %>
</c:if>

// EL:
<c:if test="${!empty param.a and !empty param.b}" >
    ${param.a} + ${param.b} = ${param.a + param.b}
</c:if>
```

この例は極端かもしれないが、ELの優位性は十分に理解できる。

JSTLなどのカスタムタグとの親和性が高いのも特筆すべき点。そのためELの使用を強く推奨する。ELで記述できるものは式であろうと何であろうとELで記述すべきという主張。

ELは次の章でも詳細を追う。