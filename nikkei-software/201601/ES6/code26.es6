// 意図通りには動かないアロー関数
() => { hoge: 'ほげ' };

// 上記は {} が関数ブロックと見なされるため、以下のように評価され、オブジェクトリテラルが返るわけではない
(function () {
  hoge: 'ほげ';
});

// ただしくはこう
() => ({ hoge: 'ほげ' });

// こうなる
(function () {
  return { hoge: 'ほげ' };  // オブジェクトリテラルが返る
});
