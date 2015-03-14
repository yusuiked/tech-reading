// モデルの定義
var Contact = Backbone.Model.extend({
	defaults: {
		firstName: '',
		lastName: '',
		email: ''
	}
});

// ビューの定義
var ContactView = Backbone.View.extend({
	render: function() {
		// HTML テンプレートを取得する
		var template = $('#contact-template').html();
		// HTML テンプレートにモデルのデータを適用する
		// モデルの toJSON() メソッドを使って属性を
		// オブジェクトの形式で書き出す
		var compiled = _.template(template);
		var html = compiled(this.model.toJSON());
		// 自身が保持している DOM 要素を更新する
		this.$el.html(html);
		return this;
	}
});

// モデルとビューの生成
var contact = new Contact({
	firstName: 'Alice',
	lastName: 'Henderson',
	email: 'alice@example.com'
});

// 初期化時の model オプションに生成したモデルの参照を渡す
// Backbone.View は自動的にその定義内でその参照を
// this.model に保持する
var contactView = new ContactView({
	model: contact
});

$(function() {
	// render() メソッドは生成したビュー自身を返すので
	// メソッドチェーンでビューが持つメソッドを続けて
	// 記述することができる
	contactView.render().$el.appendTo($(document.body))
});
