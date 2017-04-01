<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta lang="zh-cn"/>
</head>
<body style="text-align: center">
<form action="submitMsg" method="post">
    <input name="message" value="" placeholder="input a message"/>
    <input name="token" type="hidden" value="${token}">
    <button type="submit">send</button>
</form>
</body>
</html>