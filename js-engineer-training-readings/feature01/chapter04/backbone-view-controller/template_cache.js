var Contact = Backbone.Model.extend({
	defaults: {
		firstName: '',
		lastName: '',
		email: ''
	}
});
var contact = new Contact({
	firstName: 'Alice',
	lastName: 'Henderson',
	email: 'alice@example.com'
});

var ContactView = Backbone.View.extend({
	template: '<div>Name: <%= firstName %> <%= lastName %></div>' +
              '<div>Email: <%= email %></div>',
    render: function() {
    	var compiled = _.template(this.template);
    	var html = compiled(this.model.toJSON());
    	this.$el.html(html);
    	return this;
    }
});

var contactView = new ContactView({
	model: contact
});

$(function() {
	contactView.render().$el.appendTo($(document.body));
});