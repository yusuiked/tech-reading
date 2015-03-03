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

var contact = new Contact({
    firstName: 'Alice',
    lastName: 'Henderson',
    email: 'alice@example.com'
});

console.log(JSON.stringify(contact, null, 2));

var contact2 = new Contact();

console.log(JSON.stringify(contact2, null, 2));

var contact3 = new Contact();
contact3.set('firstName', 'Alice');

console.log(JSON.stringify(contact3, null, 2));