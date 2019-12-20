<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8" />
    <script type="text/javascript">
		function addParentId(id) {
			$("#parentmenu").val(id);
// 			var cObj = document.getElementById("url");
// 			if (id == 1) {
// 				cObj.setAttribute("readOnly", "true");
// 			} else {
// 				cObj.removeAttribute("readOnly");
// 			}
		}
	</script>
    </head>
    <body>
        <!--Logo区域开始-->
        <form action="save.do" method="post" onsubmit="return addCheck()" enctype="multipart/form-data">
        <div class="panel panel-default" >
        <p class="panel-heading no-collapse">增加</p>
	        <div class="panel-body">
	             <input type="hidden" id="parentmenu" name="parentid"/>
	             <input type="hidden" value="${param.menuId }" name="activeMenuId">
	             <div class="input-group input-group-sm form-group">
	                 <span class="input-group-addon">名称:</span>
	                 <input type="text" class="form-control" id="menuname" name="name" oninput="check_name()" onpropertychange="check_name()" placeholder="请输入菜单名称" required>
	             </div>
	             <div id="menuname_info"></div>
	             <div class="input-group input-group-sm form-group" style="display: none">
	                 <span class="input-group-addon">路径:</span>
	                 <input type="text" class="form-control" id="url" name="url" placeholder="如:xx/xx.action">
	             </div>
	             <div class="input-group input-group-sm form-group">
	                 <span class="input-group-addon">图标:</span>
	                 <input type="file" class="form-control" id="image" name="image">
	             </div>
	             <div id="url_info"></div>
	             <div class="form-group">
	                 <button class="btn btn-primary" type="submit" id="add"><i class="fa fa-plus"></i>确定</button>
	 				<button class="btn btn-default" data-dismiss="modal" type="button" onclick="addEmpty()">取消</button>
	             </div>
	             <div class="clearfix"></div>
	        </div>
    	</div>
    	</form>
    </body>
</html>