var Contact = Backbone.Model.extend({
	defaults: {
		firstName: '',
		lastName: '',
		email: ''
	}
});

var ContactCollection = Backbone.Collection.extend({
	// 永続化する先の url プロパティを用意する
	url: '/contacts',
	model: Contact
});

var contactCollection = new ContactCollection();
// fetch() メソッドを呼び出すことで、その URL へ GET リクエストが投げられる
contactCollection.fetch();

// success オプションにコールバック関数を渡して
// コレクションの fetch() が完了後に次の処理を行う例
contactCollection.fetch({
	success: function(collection) {
		// success オプションで受け取る引数は、Backbone.Model,
		// Backbone.Collection を継承したインスタンスが渡される
		showContact(collection);
	}
});

// jQuery Deferred が返す Promise オブジェクトを利用して
// コレクションの fetch() が完了後に次の処理を行う例
contactCollection.fetch().then(function(collection) {
	// then() メソッドに渡される引数は、単なる配列かオブジェクト
	showContact(collection);
});

// 複数のモデルとコレクションによる fetch() が完了した後
// 次の処理を行う例
var fetchingContactCollection = contactCollection.fetch();
var fetchingOtherData = otherData.fetch();

$.when(fetchingContactCollection, fetchingOtherData)
	.then(function(collection, otherData) {
	// ...
});

// fetch() の完了後は、コレクション自体が sync イベントを発生させる
// sync イベントは fetch() メソッドと save () メソッドの完了後にも発生する
// また、リクエストが始まったタイミングで発生する request イベントもある
