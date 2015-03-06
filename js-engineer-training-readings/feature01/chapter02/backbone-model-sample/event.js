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
console.log('before');
contact.set('email', 'henderson@example.com');

// イベント名を指定して監視を解除
contact.off('change');

console.log('after');
contact.set('email', 'alice@example.com');

// 全てのイベントを監視解除
contact.off();

console.log('last');
contact.set('lastName', 'henderson');

