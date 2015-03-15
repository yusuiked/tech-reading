App.NoteControlView = Backbone.View.extend({
	// フォームの submit イベントの監視を追加する
	events: {
		'submit .js-search-form': 'onSubmit'
	},
	render: function() {
		var html = $('#noteControlView-template').html();
		this.$el.html(html);
		return this;
	},
	onSubmit: function(e) {
		e.preventDefault();
		var query = $('.js-search-query').val();
		this.trigger('submit:form', query);
		// 検索文字列を取得して trigger で search イベントを発火する
	}
});