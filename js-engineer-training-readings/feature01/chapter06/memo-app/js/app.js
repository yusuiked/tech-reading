window.App = {};

$(function() {
	var note = new App.Note({
		title: 'テスト',
		body: 'テストです'
	});
	var noteView = new App.NoteListItemView({
		model: note
	});
	noteView.render().$el.appendTo($(document.body));
});