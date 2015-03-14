// そのビューが責務を持つDOM要素を保持する
// el プロパティをビューの定義時に指定
var ContactView = Backbone.View.extend({
	el: '#contactView-container'
});

// そのビューが責務を持つDOM要素を保持する
// el プロパティをビューの生成時に指定
var contactView = new ContactView({
	el: '#contactView-container'
});

// 指定しなければ自動的に <div> 要素が生成される

// タグ名の指定
var Paragraph = Backbone.View.extend({
	tagName: 'p'
});

var p = new Paragraph();
console.log(p.el.tagName);
// => P

// 属性の指定
var ContactView = Backbone.View.extend({
	attributes: {
		'data-attribute': 'someData',
		'data-other-attribute': 'otherData'
	}
});
var contactView = new ContactView();
console.log(contactView.el);
// => <div data-attribute="someData" data-other-attribute="otherData"></div>

// クラス名の指定
var ContactView = Backbone.View.extend({
	className: 'contact'
});
var contactView = new ContactView();
console.log(contactView.el);
// => <div class="contact"></div>

// 複数のクラス名の指定
var ContactView = Backbone.View.extend({
	className: 'box box-contact'
});
var contactView = new ContactView();
console.log(contactView.el);
// => <div class="box box-contact"></div>

var M = Backbone.Model.extend({
	defaults: {
		id: ''
	}
});
var m = new M({
	id: '1'
});
var ContactView = Backbone.View.extend({
	model: m,
	id: function() {
		// HTML の id 属性は一意であり、複数の生成が行われる
		// インスタンスに対して１つの定義で対応するのはよくない
		// そのため、id 属性には関数を指定する
		// 評価の戻り値がid属性の値となる
		return 'contact-' + this.model.get('id');
	}
});
var contactView = new ContactView();
console.log(contactView.el);
// => <div id="contact-1"></div>