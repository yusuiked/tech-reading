var Contact = Backbone.Model.extend({
	defaults: {
		firstName: '',
		lastName: '',
		email: ''
	},
	initialize: function() {
		this.on('invalid', function(model, err) {
			// invalid イベントに紐づくコールバック関数は
			// validate() メソッドが返すエラーメッセージを
			// 受け取ることができる
			// 
			// あるいはモデルの validationError プロパティを
			// 参照してもよい
			console.log(err);
		});
	},
	validate: function(attrs) {
		if (attrs.firstName || attrs.lastName) {
			return 'firstName属性とlastName属性の両方が必要です。';
		}
	}
});

var contact = new Contact({
	firstName: 'Alice',
	lastName: 'Henderson',
	email: 'alice@example.com'
});

// validate() メソッドによる検証を通過しない変更を
// { validate: true } オプションを付けてわざと行う
contact.set({
	lastName: ''
}, {
	validate: true
});

// モデルの属性が変化していないことを確認
console.log(JSON.stringify(contact, null, 2));