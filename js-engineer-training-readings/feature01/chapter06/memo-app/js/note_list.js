App.NoteListView = Backbone.View.extend({
	tagName: 'table',
	// Bootstrap の装飾を与えるために 'table' クラス属性値を指定する
	className: 'table',
	initialize: function(options) {
		// Backbone.Collection インスタンスを受け取る
		this.collection = options.collection;
	},
	render: function() {
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
		// コレクション内の全てのモデルを取り出して
		// 個々のビューを生成し、親ビューの DOM ツリーに挿入する
		this.collection.each(function(note) {
			var itemView = new App.NoteListItemView({
				model: note
			});
			$insertionPoint.append(itemView.render().$el);
		}, this);
	}
})