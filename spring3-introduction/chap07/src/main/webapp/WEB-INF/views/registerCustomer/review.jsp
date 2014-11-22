<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>確認画面</title>
</head>
<body>
	<h1>確認画面</h1>
	<form action="${flowExecutionUrl}" method="post">
		<dl>
			<dt>名前</dt>
			<dd>
				<c:out value="${customer.name}" />
			</dd>
			<dt>住所</dt>
			<dd>
				<c:out value="${customer.address}" />
			</dd>
			<dt>Eメールアドレス</dt>
			<dd>
				<c:out value="${customer.emailAddress}" />
			</dd>
		</dl>
		<button type="submit" name="_eventId_confirmed">登録</button>
		<button type="submit" name="_eventId_revise">再入力</button>
	</form>
	<a href="${flowExecutionUrl}&_eventId=cancel">キャンセル</a>
</body>
</html>