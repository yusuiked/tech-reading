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

var chris = new Contact({
	firstName: 'Chris',
	lastName: 'Redfield',
	email: 'chris@example.com'
})
contactCollection.add(chris);
console.log(JSON.stringify(contactCollection, null, 2));
console.log(contactCollection.length);

contactCollection.remove(chris);
console.log(JSON.stringify(contactCollection, null, 2));
console.log(contactCollection.length);

// モデルのリセット
var john = new Contact({
	firstName: 'John',
	lastName: 'Doe',
	email: 'john@example.com'
});
var jane = new Contact({
	firstName: 'Jane',
	lastName: 'Doe',
	email: 'jane@example.com'
});
contactCollection.reset([john, jane]);

console.log(JSON.stringify(contactCollection, null, 2));