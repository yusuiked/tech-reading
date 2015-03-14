window.App = {};

$(function() {
	// ダミーの Note コレクションを生成する
	var noteCollection = new App.NoteCollection([{
		title: 'テスト1',
		body: 'テスト1です。'
	}, {
		title: 'テスト2',
		body: 'テスト2です。'
	}]);

	// メモ一覧のビューを表示する領域として
	// App.Container を初期化する
	var mainContainer = new App.Container({
		el: '#main-container'
	});
	// コレクションを渡してメモ一覧の親ビューを初期化する
	var noteListView = new App.NoteListView({
		collection: noteCollection
	});
	// 表示領域にメモ一覧を表示する
	// App.Container の show() は受け取ったビューの render()
	// を実行して DOM 要素を自身の el に挿入する
	mainContainer.show(noteListView);
})