<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <script src="../../../js/menu/setRole.js"></script>
    </head>
    <body >
        <!--Logo区域开始-->
        <div class="panel panel-default">
        <p class="panel-heading no-collapse">角色设置:</p>
        <div class="panel-body">
             <input id="adminId" type="hidden"/>
             <div class="input-group input-group-sm form-group">
                 <span class="input-group-addon">名称:</span>
                 <input type="text" class="form-control" id="nikename">
             </div>
             <div class="input-group input-group-sm form-group">
                 <span class="input-group-addon">账号:</span>
                 <input type="text" class="form-control" id="username">
             </div>
             <div class="input-group input-group-sm form-group">
                 <span class="input-group-addon">密码:</span>
                 <input type="password" class="form-control" id="password">
             </div>
             <div class="input-group input-group-sm form-group">
                 <span class="input-group-addon" data-style="btn-primary">角色选择:</span>
             	<select class="form-control" id="rolesId">
               	<option value="">-请选择-</option>
               	<c:forEach items="${rolesList}" var="roles">
                	<option value="${roles.id }">-${roles.name }-</option>
               	</c:forEach>
               	</select>
             </div>
             <div id="role_info"></div>
             <div class="form-group">
                 <button class="btn btn-primary" onclick="save()" type="button"><i class="fa fa-plus"></i>确定</button>
 				<button class="btn btn-default" data-dismiss="modal" type="button" onclick="upEmpty()">取消</button>
             </div>
             <div class="clearfix"></div>
        </div>
    </div>
        
    </body>
</html>