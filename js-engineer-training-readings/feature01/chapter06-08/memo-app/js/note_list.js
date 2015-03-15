App.NoteListView = Backbone.View.extend({
	tagName: 'table',
	// Bootstrap の装飾を与えるために 'table' クラス属性値を指定する
	className: 'table',
	initialize: function(options) {
		this.collection = options.collection;
		// コレクションの reset イベントに応じて render() を呼び出す
		this.listenTo(this.collection, 'reset', this.render);
	},
	render: function() {

		// this.$el.html() が呼び出される前に古いビューを破棄しておく
		this.removeItemViews();

		// テンプレートから自身の DOM 構築を行う
		var template = $('#noteListView-template').html();
		this.$el.html(template);
		// 保持しているコレクションから子ビューを生成してレンダリングする
		this.renderItemViews();
		return this;
	},
	renderItemViews: function() {
		// 子ビューを append() で挿入する地点を特定する
		var $insertionPoint = this.$('.js-noteListItemView-container');

		// あとで適切に破棄できるように子ビューの参照を保持しておく
		this.itemViews = this.collection.map(function(note) {
			var itemView = new App.NoteListItemView({
				model: note
			});
			$insertionPoint.append(itemView.render().$el);
			return itemView;
		}, this);
	},
	// 全ての子ビューを破棄するメソッドを追加する
	removeItemViews: function() {
		// 保持している全てのビューの remove() を呼び出す
		_.invoke(this.itemViews, 'remove');
	}
})