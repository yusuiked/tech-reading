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
    render: function() {
    	// テンプレートキャッシュがなければ作っておく
    	if (ContactView.templateCache == null) {
    		ContactView.templateCache = _.template($('#contact-template').html());
    	}

    	// テンプレートキャッシュを利用してHTMLを生成する
    	var html = ContactView.templateCache(this.model.toJSON());
    	this.$el.html(html);
    	return this;
    }
}, {
	templateCache: null
});

var contactView = new ContactView({
	model: contact
});

$(function() {
	contactView.render().$el.appendTo($(document.body));
});