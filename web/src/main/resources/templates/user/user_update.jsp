<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/13
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="update" method="post" enctype="multipart/form-data">
        <div class="panel panel-default" >
            <p class="panel-heading no-collapse">增加</p>
            <div class="panel-body">
                <input type="hidden" value="${param.menuId }" name="menuId">
                <input type="hidden" name="id" id="user_id">
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">名字:</span>
                    <input type="text" class="form-control" id="user_name" name="name">
                </div>
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">手机:</span>
                    <input type="text" class="form-control" id="user_phone" name="phone">
                </div>
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">密码:</span>
                    <input type="text" class="form-control" id="user_password" name="password">
                </div>
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">性别:</span>
                    <input type="radio" name="gender" value="1" checked="checked">男
                    <input type="radio" name="gender" value="2">女
                </div>
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">IMEI:</span>
                    <input type="text" class="form-control" id="user_imei" name="imei">
                </div>
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">IMSI:</span>
                    <input type="text" class="form-control" id="user_imsi" name="imsi">
                </div>
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">国家:</span>
                    <input type="text" class="form-control" id="user_country" name="country">
                </div>
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">省份:</span>
                    <input type="text" class="form-control" id="user_province" name="province">
                </div>
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">城市:</span>
                    <input type="text" class="form-control" id="user_city" name="city">
                </div>
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">地点:</span>
                    <input type="text" class="form-control" id="user_area" name="area">
                </div>
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">签名:</span>
                    <input type="text" class="form-control" id="user_signature" name="signature">
                </div>
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">头像:</span>
                    <input type="file" class="form-control" id="user_image" name="image">
                </div>
                <img src="" id="user_img">
                <div c lass="form-group">
                    <button class="btn btn-primary" type="submit" id="add"><i class="fa fa-plus"></i>确定</button>
                    <button class="btn btn-default" data-dismiss="modal" type="button" onclick="addEmpty()">取消</button>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </form>
</body>
</html>
