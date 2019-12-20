<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>嗨够</title>
    <script src="../../../lib/jquery-2.1.1.min.js"></script>
    <link rel="stylesheet" href="../../../lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="../../../css/theme.css">
    <link rel="stylesheet" href="../../../css/premium.css">
    <style>
        @media (max-width: 767px) {
            .login-dialog {
                width: 100%;
                margin-top: 10px;
                padding-left: 10px;
                padding-right: 10px;
            }
        }
    </style>
    <script src="../../../lib/bootstrap/js/bootstrap.js"></script>
    <script src="../../../js/admin/admin.js"></script>
    <script  src="../../../js/checkoutNext.js"></script>
</head>
<body class="theme-blue" style="background-color: #F3F3F3;">
<div class="navbar navbar-default login-navbar">
    <div class="navbar-header">
        <span class="navbar-brand" style=" padding-top: 10px;"></span></div>
</div>

<div class="dialog login-dialog">
    <div class="panel panel-default">
        <!--<p class="panel-heading no-collapse">登录</p>-->
        <div class="avtar login-avtar">
        	<span style="border-radius: 40px; display: inline-block; overflow: hidden;">
        		<img style="width: 80px;" src="../../../img/avtar1.png" />
        	</span>
        </div>
        <div class="panel-body">
            <c:if test="${not empty param.error }">
                <div align="center" id="repeatPassValidMsg">
                    <p id="login.info" style="color:red">登陆超时,请重新登录</p>
                </div>
            </c:if>
            <div class="form-group">
                <label>用户名</label>
                <input type="text" class="form-control login-form-control span12" id="username" placeholder="请输入账户">
            </div>
            <div class="form-group">
                <label>密码</label>
                <input type="password" class="form-control login-form-control span12 form-control" id="password" placeholder="请输入密码">
            </div>
            <div class="row">
	            <div class="form-group col-md-8">
	                <label>验证码</label><br>
	                <input type="text" class="form-control login-form-control span12" id="auth" placeholder="请输入验证码">
	            </div>
	            <div class="form-group col-md-4">
	                <img alt="" src="../AuthImage" style="width: 95%; margin-top: 20px;" onclick="hrefImg(this)">
	            </div>
            </div>
        </div>
        <div class="loginbtn-wrap" style="text-align: right;">
            <button class="btn btn-primary" id="login-submit" type="button" onclick="check_login()">登 录</button>
        </div>
    </div>
</div>
<script type="text/javascript">
	var i = 1
	function hrefImg(obj) {
		$(obj).attr('src', '../AuthImage?' + i)
		++i
	}
</script>
</body>
</html>
