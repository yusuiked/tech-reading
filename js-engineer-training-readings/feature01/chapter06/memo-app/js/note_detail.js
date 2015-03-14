App.NoteDetailView = Backbone.View.extend({
	render: function() {
		var template = $('#noteDetailView-template').html();
		var compiled = _.template(template);
		var html = compiled(this.model.toJSON());
		this.$el.html(html);
		return this;
	}
});