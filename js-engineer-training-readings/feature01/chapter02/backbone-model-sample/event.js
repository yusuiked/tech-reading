// Backbone.Model オブジェクトの定義
var Contact = Backbone.Model.extend({
    defaults: {
        firstName: '',
        lastName: '',
        email: ''
    },
    initialize: function() {
        console.log('Contactが初期化されました。');
    }
});
// change イベントの監視
var contact = new Contact({
	firstName: 'Alice',
	lastName: 'Henderson',
	email: 'alice@example.com'
});

// change イベントですべての属性の変化を監視する
contact.on('change', function() {
	console.log('属性が変更されました。');
});

// change:属性名と記述することで特定の属性値の変化に絞って監視できる
contact.on('change:email', function() {
	console.log('email属性が変更されました。');
});

contact.set('email', 'henderson@example.com');