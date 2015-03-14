App.NoteListItemView = Backbone.View.extend({
	tagName: 'tr',
	initialize: function() {
		// モデルの destroy イベントを監視して
		// Backbone.View の remove() メソッドを呼び出す
		this.listenTo(this.model, 'destroy', this.remove);
	},
	// [Delete]ボタンを監視して
	// onClickDelete() メソッドを呼び出す
	events: {
		'click .js-delete': 'onClickDelete'
	},
	render: function() {
		var template = $('#noteListItemView-template').html();
		var compiled = _.template(template);
		var html = compiled(this.model.toJSON());
		this.$el.html(html);
		return this;
	},
	onClickDelete: function() {
		// モデルを削除する
		this.model.destroy();
	}
});