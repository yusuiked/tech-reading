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
// create() メソッドでモデルの生成、コレクションへの追加
// サーバへの送信がまとめて行われる
contactCollection.create({
	firstName: 'Alice',
	lastName: 'Henderson',
	email: 'alice@example.com'
});
// クライアント側で新しいデータが作られた
// (idをまだ持たない)ので POST リクエストになる
// 例：POST http://localhost:4567/contacts

var contact = contactCollection.get(1233);

// save() に直接オブジェクトを渡して更新可能
contact.save({
	lastName: 'Sanders'
});
// id 属性を持つ、サーバ側のリソースが作成済みなので
// その URL への更新のための PUT リクエストが行われる
// 例：PUT http://localhost:4567/contacts/123