window.onload = function() {
	// 画像のリストの定義
	var photoList = [
		{ src: 'img/spring.jpg', title: '春の桜' },
		{ src: 'img/summer.jpg', title: '夏のひまわり' },
		{ src: 'img/autumn.jpg', title: '秋の紅葉' },
		{ src: 'img/winter.jpg', title: '冬の山' }
	];
	var photoLength = photoList.length;

	// 要素の取得
	var photo = document.getElementById('photo');
	var nextBtn = document.getElementById('nextBtn');
	var title = document.getElementById('title');

	// img 要素を HTML に追加
	var item, img;
	for (var i = 0; i < photoLength; i++) {
		item = photoList[i];
		// img 要素の作成
		img = document.createElement('img');
		// 作成した img 要素に属性を設定
		img.src = item.src;
		img.alt = item.title;
		img.style.display = 'none';
		// 作成した img 要素を HTML に追加
		photo.appendChild(img);
	};

	// タイトルの初期表示
	title.innerHTML = '[1] ' + photoList[0].title;

	// 画像の初期表示
	var imgs = photo.getElementsByTagName('img');
	imgs[0].style.display = 'inline';
};