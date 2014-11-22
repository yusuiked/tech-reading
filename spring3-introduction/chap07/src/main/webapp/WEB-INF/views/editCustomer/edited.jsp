<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新完了</title>
</head>
<body>
	<h1>更新完了</h1>
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
</body>
</html>