App.NoteControlView = Backbone.View.extend({
	render: function() {
		var html = $('#noteControlView-template').html();
		this.$el.html(html);
		return this;
	}
});