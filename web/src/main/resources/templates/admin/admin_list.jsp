<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<%@include file="../common/header.html"%>
		<%@include file="../common/sysmenu.html"%>
		<script  src="../../../js/admin/admin_list.js"></script>
		<div class="content myHeader">
			<!--       <FORM action="department_delAll" method="post" name="memu">-->
			<div class="header">
				<ul class="breadcrumb">
					<li class="active">后台用户管理</li>
				</ul>
			</div>
			<div class="main-content">
			    <div class="btn-toolbar list-toolbar">
			    <button id="addtimebtn" class="btn btn-primary saveBut" data-backdrop="static" data-toggle="modal" data-target="#adminAdd" type="button">
			    	<i class="fa fa-plus"></i>增加
			    </button>
				</div>
				<table id="adminTable" data-toggle="table"
					data-url="list.do" 
					data-side-pagination="server"
					data-pagination="true"
					data-page-size="10">
					<thead>
						<tr>
							<th data-field="orderNo" data-formatter="orderNo" data-width="100">序号</th>
							<th data-field="nikename" data-align="center">名称</th>
							<th data-field="username" data-align="center">账号</th>
							<th data-field="rolesName" data-align="center">角色</th>
							<th data-field="operate" data-formatter="onFormatter" 
							  data-events="operateEvents" data-align="center" data-width="150">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div class="modal fade" id="adminAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 800px;">
				<%@include file="admin_add.jsp"%>
			</div>
		</div>
</body>
</html>