var Contact = Backbone.Model.extend({
	defaults: {
		firstName: '',
		lastName: '',
		email: ''
	}
});
var ContactCollection = Backbone.Collection.extend({
	// model プロパティにどのモデルを管理するかを宣言する
	// この宣言によって、コレクションが保持するモデルは
	// Contact のインスタンスになる
	model: Contact,
	// initialize() メソッドを定義できる点は Backbone.Model と同じ
	initialize: function() {
		console.log('ContactCollectionが初期化されました。');
	}
});

var contactCollection = new ContactCollection();

contactCollection.add([{
	firstName: 'Alice',
	lastName: 'Henderson',
	email: 'alice@example.com'
}, {
	firstName: 'Bob',
	lastName: 'Sanders',
	email: 'bob@example.com'
}]);

console.log(JSON.stringify(contactCollection, null, 2));
console.log(contactCollection.length);

contactCollection.add({
	firstName: 'Chris',
	lastName: 'Redfield',
	email: 'chris@example.com'
});
console.log(JSON.stringify(contactCollection, null, 2));
console.log(contactCollection.length);
