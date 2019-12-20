<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../../../lib/bootstrap/css/bootstrap-editable.css">
</head>
<body>
  <%@include file="../common/header.html"%>
   <%@include file="../common/sysmenu.html"%>
   		<script src="../../../lib/bootstrap/js/bootstrap-editable.js"></script>
		<script src="../../../lib/bootstrap/js/bootstrap-table-editable.js"></script>
<script src="../../../js/menu/roleMenu.js"></script>
	<div class="content myHeader">
        <div class="header">
        </div>
        <input type="hidden" id="roleId" value="${param.roleId }">
		<div class="container">
			<table id="roleMenuTable">
			</table>
	        </div>
    </div>
</body>
</html>