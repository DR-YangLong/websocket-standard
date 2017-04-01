<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta lang="zh-cn"/>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
</head>
<body style="text-align: center">
<h4>users：</h4>
<table border="1" style="align-content: center;text-align: center">
    <tr>
        <th>token</th>
        <th>userName</th>
        <th>sessionId</th>
        <th>operate</th>
    </tr>
<#if users?exists && users?size gt 0>
    <#list users?keys as key>
        <tr>
            <td>${key}</td>
            <td>${users[key].userName}</td>
            <td>
                <#if users[key].socketSession?exists>
                    ${users[key].socketSession.id}
                </#if>
            </td>
            <td>
                <#if users[key].socketSession?exists>
                    <a href="sendMsg?token=${key}" target="_blank">send message</a>
                    <a href="javascript:void(0)" data-token="${key}" onclick="refresh(this)">kick</a>
                </#if>
            </td>
        </tr>
    </#list>
</#if>
</table>
<script type="text/javascript">
    function refresh(_this) {
        $.ajax(
                {
                    url: "/web/offLine",
                    data: {token: $(_this).attr("data-token")},
                    dataType: "text",
                    type: "post",
                    success: function (data) {
                        alert(data);
                        window.location.reload();
                    },
                    error: function (error) {
                        console.log(error)
                    }
                }
        );
    }
</script>
</body>
</html>