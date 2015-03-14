var Router = Backbone.Router.extend({
	initialize: function() {
		console.log('初期化されました。');
	},
	// 例：http://example.com/#state1
	//     http://example.com/#state2
	routes: {
		'state1': 'state1',
		'state2': 'state2'
	},

	// http://example.com/#state1 に
	// アクセスした場合に呼び出される
	state1: function() {
		console.log('state1');
	},

	// http://example.com/#state2 に
	// アクセスした場合に呼び出される
	state2: function() {
		console.log('state2');
	}
});

// ルータの初期化
var router = new Router();

// Backbone.history ルータの初期化
// これが済んでいないとルーティングが機能しない
Backbone.history.start();