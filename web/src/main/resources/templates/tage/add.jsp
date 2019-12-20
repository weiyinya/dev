<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
    </head>
    <body >
        <!--Logo区域开始-->
        <div class="panel panel-default">
        <p class="panel-heading no-collapse">增加:</p>
        <div class="panel-body">
             <input id="tageId" type="hidden"/>
             <input id="tageParenId" type="hidden"/>
             <div class="input-group input-group-sm form-group">
                 <span class="input-group-addon">名称:</span>
                 <input type="text" class="form-control" id="tageName">
             </div>
             <div class="input-group input-group-sm form-group">
                 <span class="input-group-addon">描述:</span>
                 <input type="text" class="form-control" id="tageDescribe">
             </div>
             <div class="form-group">
                 <button class="btn btn-primary" onclick="save()" type="button"><i class="fa fa-plus"></i>确定</button>
 				<button class="btn btn-default" data-dismiss="modal" type="button" onclick="upEmpty()">取消</button>
             </div>
             <div class="clearfix"></div>
        </div>
    </div>
        
    </body>
</html>