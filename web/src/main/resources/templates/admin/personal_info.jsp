<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <script src="../../../js/admin/personal.js"></script>
    </head>
    <body >
        <!--Logo区域开始-->
        <div class="panel panel-default">
        <p class="panel-heading no-collapse">个人信息:</p>
        <div class="panel-body">
             <input id="adminId" type="hidden"/>
             <div class="input-group input-group-sm form-group">
                 <span class="input-group-addon">账号:</span>
                 <input type="text" class="form-control" id="personal_username" readonly="readonly">
             </div>
             <div class="input-group input-group-sm form-group">
                 <span class="input-group-addon">名称:</span>
                 <input type="text" class="form-control" id="personal_nikename">
             </div>
             <div class="input-group input-group-sm form-group">
                 <span class="input-group-addon">原密码:</span>
                 <input type="password" class="form-control" id="oldPassword">
             </div>
             <div class="input-group input-group-sm form-group">
                 <span class="input-group-addon">新密码:</span>
                 <input type="password" class="form-control" id="password1">
             </div>
             <div class="input-group input-group-sm form-group">
                 <span class="input-group-addon">确认新密码:</span>
                 <input type="password" class="form-control" id="password2">
             </div>
             <div id="role_info"></div>
             <div class="form-group">
                 <button class="btn btn-primary" onclick="savePersonal()" type="button"><i class="fa fa-plus"></i>确定</button>
 				<button class="btn btn-default" data-dismiss="modal" type="button" onclick="personalEmpty()">取消</button>
             </div>
             <div class="clearfix"></div>
        </div>
    </div>
        
    </body>
</html>