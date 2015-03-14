App.Router = Backbone.Router.extend({
	routes: {
		'notes/:id': 'showNoteDetail',
		'new': 'showNewNote',
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

		// メモの詳細画面ではボタンを消したいので
		// App.Container の empty() メソッドを呼び出して
		// ビューを破棄しておく
		App.headerContainer.empty();
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
		// メモ一覧操作ビューを表示するメソッドの呼び出しを追加する
		this.showNoteControl();
	},

	// メモ一覧操作ビューを表示するメソッド
	showNoteControl: function() {
		var noteControlView = new App.NoteControlView();
		App.headerContainer.show(noteControlView);
	},

	// 新規作成画面のルーティング
	showNewNote: function() {
		var self = this;
		// テンプレートの <%= title %> などの出力を空文字列で空欄にしておくため、
		// 新規に生成した Note モデルを渡して NoteFormView を初期化する
		var noteFormView = new App.NoteFormView({
			model: new App.Note()
		});
		noteFormView.on('submit:form', function(attrs) {
			// submit:form イベントで受け取ったフォームの入力値を
			// NoteCollection の create() に渡して Note モデルの新規作成と保存を行う
			App.noteCollection.create(attrs);

			// モデル一覧を表示してルートを #notes に戻す
			self.showNoteList();
			self.navigate('notes');
		});

		App.mainContainer.show(noteFormView);
		// 新規追加ボタンはこの画面では必要ないのでビューを破棄しておく
		App.headerContainer.empty();
	}
});