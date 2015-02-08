// Java
FileInputStream fis = null;
try {
    fis = new FileInputStream("input.txt");
} finally {
    if (fis != null) {
        try { fis.close(); } catch (Exception e) {}
    }
}

// Groovy
new FileInputStream("input.txt").withStream { input ->
    // input からのデータの読み出し
}

// withStream メソッド内でクローズ処理を行ってくれるため例外処理が不要
// ローンパターンと呼ぶ
