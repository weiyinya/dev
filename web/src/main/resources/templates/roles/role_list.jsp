<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="../../../js/menu/role.js"></script>
</head>
<body>
  <%@include file="../common/header.html"%>
   <%@include file="../common/sysmenu.html"%>
	<div class="content myHeader">
       <FORM action="" method="post" name="memu">
        <div class="header">
        <ul class="breadcrumb">
            <li class="active">权限管理</li>
        </ul>
        </div>
	<div class="btn-toolbar list-toolbar">
	    <button class="btn btn-primary saveBut" data-backdrop="static" data-toggle="modal" data-target="#add" type="button"  disabled="disabled">
	    	<i class="fa fa-plus"></i>增加角色
	    </button>
	  <div class="btn-group">
	  </div>
	</div>
<div class="container">

     <table id="tbl" data-toggle="table"
			data-url="../roles/list.do" 
			data-side-pagination="server"
			data-pagination="true"
			data-page-size="10">
			<thead>
				<tr>
					<th data-field="orderNo1" data-formatter="orderNo1">序号</th>
					<th data-field="name" data-align="center" >名称</th>
					<th data-field="descri" data-align="left" data-width="450">描述</th>
					<th data-field="operate" data-formatter="operateFormatter"
						data-events="operateEvents" data-align="center">操作</th>
				</tr>
			</thead>
		</table>

        </div>
        </FORM>
    </div>
 		<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 450px;">
			  <%@include file="role_add.jsp"%>
			</div>
		</div>
 		<div class="modal fade" id="update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 450px;">
			 <%@include file="role_update.jsp"%>
			</div>
		</div>
</body>
</html>