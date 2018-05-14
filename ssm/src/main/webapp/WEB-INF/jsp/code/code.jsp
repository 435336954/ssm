<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/14
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ly" uri="http://www.lei1tec.com/functions" %>
<html>
<head>
    <title>Title</title>
    <script>
        function changeCode() {
            $("#codeImg").attr("src", "code.do?t=" + genTimestamp());
        }
    </script>
</head>
<body>
<div class="yzm_wrap clearfix">
    <div class="login_tit">Verification Code</div>
    <div class="clearfix">
        <input class="yami" type="text" name="" id="code" maxlength="4" />
        <img alt="${ly:T("点击更换") }" title="${ly:T("点击更换") }" src="" class="img" id="codeImg" height="25" width="68"/>
    </div>
</div>
</body>
</html>
