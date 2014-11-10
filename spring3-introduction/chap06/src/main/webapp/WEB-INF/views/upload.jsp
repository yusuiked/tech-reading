<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ファイルアップロード</title>
</head>
<body>
<h1>ファイルアップロード</h1>
<form method="post" enctype="multipart/form-data">
    <dl>
        <dt>アップロードファイル</dt>
        <dd><input type="file" name="uploadFile"></dd>
    </dl>
    <button type="submit">アップロード</button>
</form>
</body>
</html>
