var http = require('http');

var server = http.createServer();
server.on('request', function (request, response) {
	// ステータスコード 200 で HTTP レスポンスヘッダを出力
	response.writeHead(200);
	// リクエスト URL を出力する
	response.write('URL: ' + request.url + '\n');
	// HTTP メソッドを出力する
	response.write('Method: ' + request.method + '\n');
	// HTTP ヘッダーを出力する
	Object.keys(request.headers).forEach(function (key) {
		response.write(key + ': ' + request.headers[key] + '\n');
	});
	// レスポンスを終了する
	response.end();
});
// 127.0.0.1:8080 で listen する
server.listen(8080, '127.0.0.1');