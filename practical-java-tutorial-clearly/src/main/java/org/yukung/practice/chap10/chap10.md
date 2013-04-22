javadoc コマンドの実行
=====================

	javadoc -locale ja -encoding utf-8 -docencoding utf-8 -charset utf-8 *.java

jar コマンドでマニフェストファイル指定
====================================

	jar -cvfm hoge.jar META-INF/MANIFEST.MF *.class

jar を直接実行
=============

Main-Class: <メインクラス> エントリのあるマニフェストファイルが含まれている jar が前提

	java -jar hoge.jar

