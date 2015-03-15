App.Router = Backbone.Router.extend({
	routes: {
		'notes/:id': 'showNoteDetail',
		'new': 'showNewNote',
		'notes/:id/edit': 'showEditNote',
		'notes/search/:query': 'searchNote',
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

	showNoteList: function(models) {
		// 一覧表示用のコレクションを別途初期化する
		if (!this.filteredCollection) {
			this.filteredCollection = new App.NoteCollection();
		}
		// NoteListView のインスタンスが表示中でないときのみ
		// これを初期化して表示する
		if (!App.mainContainer.has(App.NoteListView)) {
			// 初期化の際に一覧表示用のコレクションを渡しておく
			var noteListView = new App.NoteListView({
				collection: this.filteredCollection
			});
			App.mainContainer.show(noteListView);
		}
		// 検索されたモデルの配列が引数に渡されていればそちらを、
		// そうでなければすべてのモデルを持つ App.noteCollection
		// インスタンスのモデルの配列を使用する
		models = models || App.noteCollection.models;

		// 一覧表示用のコレクションの reset() メソッドに採用した方の
		// モデルの配列を渡す
		this.filteredCollection.reset(models);
		this.showNoteControl();
	},

	// メモ一覧操作ビューを表示するメソッド
	showNoteControl: function() {
		var noteControlView = new App.NoteControlView();
		// submit:form イベントの監視を追加する
		noteControlView.on('submit:form', function(query) {
			this.searchNote(query);
			// 検索結果のパーマリンクを作成しておくことで履歴やブックマークに対応する
			this.navigate('notes/search/' + query);
		}, this);
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
	},

	// 編集画面のルーティング
	showEditNote: function(id) {
		var self = this;
		// 既存の Note モデルを取得して NoteFormView に渡す
		var note = App.noteCollection.get(id);
		var noteFormView = new App.NoteFormView({
			model: note
		});
		noteFormView.on('submit:form', function(attrs) {
			// submit:form イベントで受け取ったフォームの入力値を Note モデルに保存する
			note.save(attrs);

			// モデル詳細画面を表示してルートも適切なものに書き換える
			self.showNoteDetail(note.get('id'));
			self.navigate('notes/' + note.get('id'));
		});

		App.mainContainer.show(noteFormView);
	},

	// メモ検索用のメソッド
	searchNote: function(query) {
		// Underscore.js の filter() メソッドは Backbone.Collection からは直接呼び出せる
		// filtered はコレクションではなくただの配列であることに注意
		var filtered = App.noteCollection.filter(function(note) {
			return note.get('title').indexOf(query) !== -1;
		});
		this.showNoteList(filtered);
	}
});