<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/7
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<title>验证成功</title>
<head>
    <%--<script  type="text/javascript">--%>
        <%--var cTime=3;--%>
        <%--function TimeClose(){--%>
            <%--window.setTimeout("TimeClose()",1000); //2秒钟自动关闭--%>
            <%--if(cTime<=0){--%>
                <%--window.opener.location.reload();--%>
                <%--window.close();--%>
            <%--}--%>
            <%--this.ShowTime.innerHTML="验证成功，"+cTime+"秒后跳转";--%>
            <%--cTime--;--%>
        <%--}--%>
        <%--function myClose(){--%>
            <%--window.close();--%>
        <%--}--%>
    <%--</script>--%>
</head>
<body onLoad="TimeClose();">
<c:if test="${status == 1}">
    <h1 id="ShowTime">验证成功！</h1>
</c:if>
<c:if test="${status == 2}">
    <h1 id="ShowTime">重复验证！</h1>
</c:if>
<%--<input type="button" name="CloseWindow" onClick="myClose();" value="立即关闭当前页面">--%>
</body>
</html>
