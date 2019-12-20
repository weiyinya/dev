<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/13
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
</head>
<body>
    <form action="save" method="post" enctype="multipart/form-data">
        <div class="panel panel-default" >
            <p class="panel-heading no-collapse">增加</p>
            <div class="panel-body">
                <input type="hidden" value="${cookie.adminId.value }" name="userId"/>
                <input type="hidden" value="${param.menuId }" name="menuId">
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">发布者:</span>
                    <select class="form-control" id="userSel">
                        <c:forEach var="u" items="${userSel}">
                            <option value="${u.id}">${u.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">内容:</span>
                    <input type="text" class="form-control" id="menuname" name="content" required>
                </div>
                <div id="menuname_info"></div>
                <div class="input-group input-group-sm form-group">
                    <span class="input-group-addon">上传:</span>
                    <div class="col-md-4" style="padding-left: 0px;">
                        <button class="btn btn-default" type="button" onclick="addNewRow('news_filearea','','file')">
                            <i class="fa fa-plus"></i>手动增加
                        </button>
                        <div>
                            <table  border="0" id="news_filearea">
                            </table>
                        </div>
                    </div>
                </div>
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
