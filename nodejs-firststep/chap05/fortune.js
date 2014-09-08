var http = require('http');
var querystring = require('querystring');
var crypto = require('crypto');

var htmlHeader = '<!DOCTYPE html>\
<html lang="ja">\
<head>\
<meta charset="utf-8">\
<title>ノード占い</title>\
<style>\
.content {\
	width: 480px;\
	text-align: center;\
	border: 4px solid lightblue;\
	padding: 4px;\
	margin: 16px auto;\
}\
.main-form div {margin-bottom: 4px;}\
.result {\
	display: block;\
	font-size: 200%;\
	color: red;\
	margin: 4px auto;\
	border: 1px solid;\
	width: 4em;\
}\
</style>\
</head>\
<body>\
	<div class="content">\
	<h1>ノード占い</h1>';

var htmlMainForm = '<div class="main-form">\
	<form method="post" action="/">\
		<div>\
			<label>名前：<input type="text" name="name" size="20"></label>\
		</div>\
		<div>\
			生年月日：\
			<label><input type="text" name="year" size="5">年</label>\
			<label><input type="text" name="month" size="3">月</label>\
			<label><input type="text" name="day" size="3">日</label>\
		</div>\
		<div>\
			性別：\
			<label><input type="radio" name="sex" value="male">男</label>\
			<label><input type="radio" name="sex" value="female">女</label>\
		</div>\
		<input type="submit" value="占う">\
	</form>\
	</div>';

var htmlFooter = '</div></body></html>';

function escapeHtmlSpecialChar(html) {
	if (html === undefined) {
		return '';
	} else {
		html = html.replace(/&/g, '&amp;');
		html = html.replace(/</g, '&lt;');
		html = html.replace(/>/g, '&gt;');
		return html;
	}
};

var server = http.createServer(onRequest);

function onRequest(request, response) {
	if (request.url != '/') {
		response.writeHead(404, {'Content-Type': 'text/plain; charset=UTF-8'});
		response.end('Error 404: Not Found.');
		return;
	}
	if (request.method != 'POST') {
		response.writeHead(200, {'Content-Type': 'text/html; charset=UTF-8'});
		response.write(htmlHeader);
		response.write(htmlMainForm);
		response.write(htmlFooter);
		response.end();
		return;
	}
	if (request.method == 'POST') {
		request.data = '';
		request.on('data', function (chunk) {
			request.data += chunk;
		});
		request.on('end', sendResponse);
		return;
	}
	function sendResponse() {
		var query = querystring.parse(request.data);
		var seed = query.name + query.year + query.month + query.day + query.sex;
		hash = crypto.createHash('md5');
		hash.update(seed);
		var hashValue = hash.digest('hex');
		var fortuneKey = Number('0x' + hashValue.slice(0,2));
		var result = '';
		if (fortuneKey < 10) {
			result = '大凶';
		} else if (fortuneKey < 50) {
			result = '凶';
		} else if (fortuneKey < 100) {
			result = '末吉';
		} else if (fortuneKey < 150) {
			result = '吉';
		} else if (fortuneKey < 245) {
			result = '大吉';
		}

		var resultStr = '<div><p>'
		 + escapeHtmlSpecialChar(query.year) + '年'
		 + escapeHtmlSpecialChar(query.month) + '月'
		 + escapeHtmlSpecialChar(query.day) + '日生まれの'
		 + escapeHtmlSpecialChar(query.name) + 'さん（'
		 + ((query.sex == 'male') ? '男性' : '女性')
		 + ') の運勢は……'
		 + '<span class="result">'
		 + result + '</span>'
		 + 'です。</p></div>'
		 + '<a href="/">トップに戻る</a>';

		response.writeHead(200, {'Content-Type': 'text/html; charset=UTF-8'});
		response.write(htmlHeader);
		response.write(resultStr);
		response.write(htmlFooter);
		response.end();
	}
}

var PORT = 8080;
var ADDRESS = '127.0.0.1';

server.listen(PORT, ADDRESS);
console.log('Server running at http://' + ADDRESS + ':' + PORT + '/');
