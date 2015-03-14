var Router = Backbone.Router.extend({
	routes: {
		'contacts/:id': 'showContact',
		'contacts/:id/edit': 'editContact',
		'search/:query/page:page': 'showSearchResult'
	},
	showContact: function(id) {
		// contacts/123 にアクセスすると
		// 123 というログが残る
		console.log(id);
	},
	editContact: function(id) {
		console.log(id + 'を編集します。');
	},
	showSearchResult: function(query, page) {
		console.log('query は' + query + ', page は' + page);
	}
});

var route = new Router();
Backbone.history.start();