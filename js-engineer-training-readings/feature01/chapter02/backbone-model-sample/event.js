// Backbone.Model オブジェクトの定義
var Contact = Backbone.Model.extend({
    defaults: {
        firstName: '',
        lastName: '',
        email: ''
    },
    initialize: function() {
    	this.on('change', function() {
			console.log('属性が変更されました。');
    	});
    	this.on('change:email', function() {
			console.log('email属性が変更されました。');
    	});
    }
});
var contact = new Contact({
	firstName: 'Alice',
	lastName: 'Henderson',
	email: 'alice@example.com'
});
contact.set('email', 'henderson@example.com');