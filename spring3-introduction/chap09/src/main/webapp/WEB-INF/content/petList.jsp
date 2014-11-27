<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<s:iterator value="%{pets}">
		<ul>
			<li><s:property value="%{petName}" /></li>
			<li><s:property value="%{ownerName}" /></li>
			<li><s:property value="%{price}" /></li>
			<li><s:property value="%{birthDate}" /></li>
		</ul>
	</s:iterator>
	<p><a href="menu">メニューへ戻る</a></p>
</body>
</html>