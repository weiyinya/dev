<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8" />
    </head>
    <body>
        <!--Logo区域开始-->
        <div class="panel panel-default" >
        <p class="panel-heading no-collapse">修改</p>
        <div class="panel-body">
            <form action="save.do" method="post"  name="update" onsubmit="return updateCheck()" enctype="multipart/form-data">
                <input type="hidden" name="id" id="upid">
                <input type="hidden" id="upparentid" name="parentid"/>
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">名称:</span>
                    <input type="text" class="form-control" id="upmenuname" name="name" oninput="check_upname();" onpropertychange="check_upname();" placeholder="请输入菜单名称" required>
                </div>
                <div class="input-group input-group-sm form-group" style="display: none">
                    <span class="input-group-addon">路径:</span>
                    <input type="text" class="form-control" id="upurl" name="url" placeholder="如：xx/xx.action">
                </div>
                <div class="input-group input-group-sm form-group">
	                 <span class="input-group-addon">图标:</span>
	                 <input type="file" class="form-control" id="upimage" name="image">
	             </div>
                <div class="form-group">
                    <button class="btn btn-primary" type="submit" id="update"><i class="fa fa-plus"></i>确定</button>
    				<button class="btn btn-default" data-dismiss="modal" type="button" onclick="upEmpty()">取消</button>
                </div>
                <div class="clearfix"></div>
            </form>
        </div>
    </div>
    </body>
</html>