<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<s:form action="binding">
		<s:textfield name="userName" label="ユーザ名" />
		<s:password name="password" label="パスワード" />
		<s:submit value="ログイン" />
	</s:form>
	<p><a href="menu">メニューへ戻る</a></p>
</body>
</html>