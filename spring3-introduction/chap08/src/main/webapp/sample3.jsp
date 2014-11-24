<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>サンプル</title>
<style type="text/css">
form {
	text-align: center
}
</style>
</head>
<body>
	<form action="sample3.do" method="post">
		AutowiringRequestProcessorを使用したサンプル<br> 姓：<input type="text"
			name="firstName" /><br> 名：<input type="text" name="lastName" /><br>
		<input type="submit" value="フルネーム" /><br> ${fullName} <br> <a
			href="menu.do">メニューへ戻る</a>
	</form>


</body>
</html>
