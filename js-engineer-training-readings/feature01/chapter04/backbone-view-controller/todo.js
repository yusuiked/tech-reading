var Todo = Backbone.Model.extend({
	defaults: {
		title: '',
		completed: false
	}
});

var TodoView = Backbone.View.extend({
	template: '<label>' +
	          '  <input class="toggle" type="checkbox">' +
	          '  <span><%= title %></span>' +
	          '</label>',
	events: {
		// '.toggle' セレクタで特定d切る要素のクリックイベントを
		// 監視して toggleCompleted() メソッドを呼び出す
		// 内部では this.$el.on() が実行されている
		'click.toggle': 'toggleCompleted'
	},

	render: function() {
		var compiled = _.template(this.template);
		var html = compiled(this.model.toJSON());
		this.$el.html(html);
		return this;
	},
	toggleCompleted: function(e) {
		// jQuery のしくみで動いているので引数 e は
		// jQuery のイベントオブジェクトを参照している
		console.log('チェックボックスがクリックされました。');
		// コールバック関数の this はビューインスタンスを指す
		console.log(this instanceof TodoView);
		// => true
	}
});

var todo = new Todo({ title: '牛乳を買う' });
var todoView = new TodoView({
	model: todo
});

$(function() {
	todoView.render().$el.appendTo($(document.body));
});