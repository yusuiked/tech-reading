function dialog({
  content,
  height,
  width,
  modeless,
  resizable
}) {
  // do anything
  // 名前付き引数を関数が取り出す処理は分割代入が使われる
}

// 名前付き引数
// 実体はオブジェクト。 Groovy だと Map
// 名前が付いてるので引数の順番は問わない
dialog({ content: 'ダイアログです',
  height: 300,
  width: 500,
  modeless: true,
  resizable: false
});
