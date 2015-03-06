// Backbone.Model オブジェクトの定義
var Contact = Backbone.Model.extend({
    defaults: {
        firstName: '',
        lastName: '',
        email: ''
    },
    initialize: function() {
    	this.on('select', function(selected) {
    		console.log('select イベントが発生しました。value : ' + selected);
    	});
    },
    select: function() {
    	// 選択中フラグを立てる。連絡先データではないので
    	// 属性ではなく単なるプロパティとして扱う
    	this.selected = true;

    	// 独自イベントの select を発生させる
    	// trigger() メソッドの第2引数以降の指定は
    	// コールバック関数が受け取れるパラメータとなる
    	this.trigger('select', this.selected);
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

var contact2 = new Contact();
contact.select();

// listenTo メソッドは指定したインスタンスを監視する側が呼び出す主体となる
var ContactView = Backbone.View.extend({
	initialize: function() {
		this.listenTo(this.model, 'change', function() {
			console.log('モデルの属性が変更されました。');
		});
	}
});

// listenTo を使うと、view オブジェクトを破棄する際に remove メソッドを呼べば内部で
// イベントの監視を解除する stopListening() メソッドも呼んでくれるため、参照が切れて
// メモリリークを防いでくれる。
// on() メソッドだとイベントの監視は個別に解除する必要がある。多くの場合、view より先に
// model が削除されることはないはずなので、view で listenTo メソッドを使うのがよい。

// 独自処理の実装
var ContactAlter = Backbone.Model.extend({
    defaults: {
        firstName: '',
        lastName: '',
        email: ''
    },
    fullName: function() {
        return this.get('firstName') + ' ' + this.get('lastName');
    }
});
var contactAlter = new ContactAlter({
    firstName: 'Alice',
    lastName: 'Henderson'
});
console.log('fullNameメソッド:' + contactAlter.fullName());
