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
// Model オブジェクトの初期化
var contact = new Contact({
    firstName: 'Alice',
    lastName: 'Henderson',
    email: 'alice@example.com'
});
// 出力
console.log(JSON.stringify(contact, null, 2));

var contact2 = new Contact();
// 初期値で出力
console.log(JSON.stringify(contact2, null, 2));
// null で生成
var contact3 = new Contact();
contact3.set('firstName', 'Alice');
contact3.set({
    'lastName': 'Henderson',
    'email': null
});

console.log(JSON.stringify(contact3, null, 2));

console.log(contact3.get('firstName'));
// プロパティが存在するかを確認
console.log(contact3.has('lastName'));
console.log(contact3.has('email'));

// attributes への直接アクセス
contact3.attributes.email = 'yukung.i@gmail.com';
console.log(contact3.get('email'));
// set()された値を attributes から直接取得
contact3.set('lastName', 'Sanders');
console.log(contact3.attributes.lastName);