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
		<script src="../../../js/tage/list.js"></script>
		<div class="content myHeader">
			<!--       <FORM action="department_delAll" method="post" name="memu">-->
			<div class="header">
				<ul class="breadcrumb">
					<li class="active">应用列表</li>
				</ul>
			</div>
			<div class="main-content">
			    <div class="btn-toolbar list-toolbar">
			    <button id="addtimebtn" class="btn btn-primary saveBut" onclick="showAdd()" type="button">
			    	<i class="fa fa-plus"></i>增加
			    </button>
				</div>
				<input id="tageParentTableId" type="hidden"/>
				<table id="tageTable" data-toggle="table"
					data-url="findAll.do" 
					data-side-pagination="server"
					data-pagination="true"
					data-page-size="10">
					<thead>
						<tr>
							<th data-field="orderNo" data-formatter="orderNo" data-width="100">序号</th>
							<th data-field="name" data-align="center">名称</th>
							<th data-field="describes" data-align="center">描述</th>
							<th data-field="operate" data-formatter="onFormatter" 
							  data-events="operateEvents" data-align="center" data-width="150">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div class="modal fade" id="tageAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 800px;">
				<%@include file="add.jsp"%>
			</div>
		</div>
		<div class="modal fade" id="tageAddParent" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 800px;">
				<%@include file="addParent.jsp"%>
			</div>
		</div>
</body>
</html>