<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8" />
<script src="../../../js/menu/role_update.js"></script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div class="panel panel-default" >
        <p class="panel-heading no-collapse">修改角色</p>
        <div class="panel-body">
                <input type="hidden" id="uprolid">
                <input type="hidden" id="origname">
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">角色名称:</span>
                    <input type="text" class="form-control" id="uprolname" name="roles.name" placeholder="请输入角色名称" >
                </div>
                <div id="uprolname_info"></div>
               <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">角色描述:</span>
<!--                    <input type="text" class="form-control" id="uproldescri" name="roles.descri" oninput="check_updescri()" onpropertychange="check_updescri()" placeholder="请输入角色描述" >-->
                    <textarea class="form-control" id="uproldescri" name="roles.descri" style='overflow:scroll;overflow-y:hidden;;overflow-x:hidden'
						onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},1);" 
						onblur="clearInterval(this.clock);"
					    placeholder="请输角色描述，不超过200个字！"  maxlength="200"></textarea>
                </div>
                <div id="uproldescri_info"></div>
                <div class="form-group">
                    <button class="btn btn-primary" onclick="upCheck()"><i class="fa fa-plus"></i>确定</button>
    				<button class="btn btn-default" data-dismiss="modal" type="button" onclick="upEmpty()">取消</button>
                </div>
                <div class="clearfix"></div>
        </div>
    </div>
    </body>
</html>