// Backbone.Model オブジェクトの定義
var Contact = Backbone.Model.extend({
    defaults: {
        firstName: '',
        lastName: '',
        email: ''
    },
    initialize: function() {
    }
});
// コールバック関数を特定して解除
var onChange = function() {
	console.log('属性が変更されました。');
};
var onChangeEmail = function() {
	console.log('email属性が変更されました。');
};
var contact = new Contact({
	firstName: 'Alice',
	lastName: 'Henderson',
	email: 'alice@example.com'
});
contact.on('change', onChange);
contact.on('change:email', onChangeEmail);

console.log('before');
contact.set('email', 'henderson@example.com');

// 'change' イベントに対して onChange() メソッドを紐付けた監視だけを解除する
contact.off('change', onChange);

console.log('after');
// この属性値の変更に反応するのは onChangeEmail() メソッドのみとなる
contact.set('email', 'alice@example.com');
