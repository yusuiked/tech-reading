window.App = {};

// ダミーの Note モデルを生成する関数
var initializeNotes = function() {
	var noteCollection = new App.NoteCollection([{
		title: 'テスト1',
		body: 'テスト1です。'
	}, {
		title: 'テスト2',
		body: 'テスト2です。'
	}, {
		title: 'テスト3',
		body: 'テスト3です。'
	}]);

	// 作成したモデルはローカルストレージに保存する
	noteCollection.each(function(note) {
		note.save();
	});

	// この処理で作ったコレクションは一時的な用途であり
	// 必要なのは中身のモデルなのでモデルの配列を返す
	return noteCollection.models;
}

$(function() {
	// NoteCollection を初期化する
	// 後で別の js ファイルからも参照するので
	// App名前空間配下に参照を持たせておく
	App.noteCollection = new App.NoteCollection();

	// メモ一覧のビューを表示する領域として
	// App.Container を初期化する
	// これも同様の理由で App 配下に参照をもたせる
	App.mainContainer = new App.Container({
		el: '#main-container'
	});

	// NoteCollection のデータを受信する
	// (Backbone.LocalStorageを使用しているので
	// ブラウザのローカルストレージから読み込む)
	App.noteCollection.fetch().then(function(notes) {
		// もし読み込んだデータが空であれば
		// ダミーデータでコレクションの中身を上書きする
		if (notes.length == 0) {
			var models = initializeNotes();
			App.noteCollection.reset(models);
		}
	})

	// コレクションを渡してメモ一覧の親ビューを初期化する
	var noteListView = new App.NoteListView({
		collection: App.noteCollection
	});
	// 表示領域にメモ一覧を表示する
	// App.Container の show() は受け取ったビューの render()
	// を実行して DOM 要素を自身の el に挿入する
	App.mainContainer.show(noteListView)
})