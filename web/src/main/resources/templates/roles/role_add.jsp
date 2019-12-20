<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
	<script src="../../../js/menu/role_add.js"></script>
</head>
<body>
	<!--Logo区域开始-->
	<div class="panel panel-default">
		<p class="panel-heading no-collapse">增加角色</p>
		<div class="panel-body">
			<div class="input-group input-group-sm form-group">
				<span class="input-group-addon">角色名称:</span> <input type="text"
					class="form-control" id="rolname" placeholder="请输入角色名称" required>
			</div>
			<div class="input-group input-group-sm form-group">
				<span class="input-group-addon">角色描述:</span>
				<textarea class="form-control" id="roldescri"
					style='overflow:scroll;overflow-y:hidden;;overflow-x:hidden'
					onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},1);"
					onblur="clearInterval(this.clock);" placeholder="请输角色描述，不超过200个字！" maxlength="200"></textarea>
			</div>
			<div id="_info"></div>
			<div class="form-group">
				<button class="btn btn-primary" id="add" onclick="addCheck()">
					<i class="fa fa-plus"></i>确定
				</button>
				<button class="btn btn-default" data-dismiss="modal" type="button"
					onclick="addEmpty()">取消</button>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</body>
</html>