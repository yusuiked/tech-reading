<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>入力画面</title>
</head>
<body>
	<h1>入力画面</h1>
	<form:form action="${flowExecutionUrl}" modelAttribute="customer">
		<dl>
			<dt>名前</dt>
			<dd>
				<form:input path="name" />
				<form:errors path="name" />
			</dd>
			<dt>住所</dt>
			<dd>
				<form:input path="address" />
				<form:errors path="address" />
			</dd>
			<dt>Eメールアドレス</dt>
			<dd>
				<form:input path="emailAddress" />
				<form:errors path="emailAddress" />
			</dd>
		</dl>
		<button type="submit" name="_eventId_proceed">次へ</button>
	</form:form>
	<a href="${flowExecutionUrl}&_eventId=cancel">キャンセル</a>
</body>
</html>