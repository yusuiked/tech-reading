App.Router = Backbone.Router.extend({
	routes: {
		'notes/:id': 'showNoteDetail',
		'*actions': 'defaultRoute'
	},

	// ルーティングが受け取った :id パラメータは
	// そのまま引数 id で受け取れる
	showNoteDetail: function(id) {
		var note = App.noteCollection.get(id);
		var noteDetailView = new App.NoteDetailView({
			model: note
		});
		App.mainContainer.show(noteDetailView);
	},

	defaultRoute: function() {
		this.showNoteList();
		this.navigate('notes');
	},

	showNoteList: function() {
		// コレクションを渡してメモ一覧の親ビューを初期化する
		var noteListView = new App.NoteListView({
			collection: App.noteCollection
		});
		// 表示領域にメモ一覧を表示する
		// App.Container の show() は受け取ったビューの render()
		// を実行して DOM 要素を自身の el に挿入する
		App.mainContainer.show(noteListView);
	}
});