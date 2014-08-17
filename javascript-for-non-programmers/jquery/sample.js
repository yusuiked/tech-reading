$(function() {
	// 要素の取得
	// #sample-slideshow 要素以下のみ走査対象とする
	var $container = $('#sample-slideshow');
	var $title = $container.find('.title');
	var $imgs = $container.find('.photo img');
	var $nextBtn = $container.find('.nextBtn');
	var $prevBtn = $container.find('.prevBtn');

	// 画像をアニメーションして表示するための関数
	function showPhoto(index) {
		var $current = $imgs.filter(':visible');
		// 表示する対象の要素を取得
		var $target = $imgs.eq(index);
		var title = $target.attr('alt');
		var viewNumber = index + 1;
		// タイトルの表示
		$title.text('[' + viewNumber + '] ' + title);
		// 画像の表示
		$current.fadeOut();
		$target.fadeIn();
	}

	// 画像の数の取得
	var len = $imgs.length;
	// インデックスの初期値
	var currentIndex = 0;

	// next ボタンを押した時の動作
	$nextBtn.click(function() {
		// 表示する画像のインデックスを計算
		currentIndex++;
		if (currentIndex >= len) {
			currentIndex = 0;
		};
		// 画像の切り替え
		showPhoto(currentIndex);
	});

	// prev ボタンを押した時の動作
	$prevBtn.click(function() {
		currentIndex--;
		if (currentIndex < 0) {
			currentIndex = len - 1;
		}
		showPhoto(currentIndex);
	});

	// 初期表示
	showPhoto(currentIndex);
});
