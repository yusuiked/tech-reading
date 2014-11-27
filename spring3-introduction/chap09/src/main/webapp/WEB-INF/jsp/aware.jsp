<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<s:property value="%{#attr.val1}" />
	<br>
	<s:property value="%{#attr.val2}" />
	<br>
	<s:property value="%{#attr.val3}" />
	<br>
	<br>
	<a href="menu">メニューへ戻る</a>
</body>
</html>