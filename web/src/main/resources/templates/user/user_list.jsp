<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<%@include file="../common/header.html"%>
<%@include file="../common/sysmenu.html"%>
<script  src="../../../js/user/user_list.js"></script>
<div class="content myHeader">
    <!--       <FORM action="department_delAll" method="post" name="memu">-->
    <div class="header">
        <ul class="breadcrumb">
            <li class="active">用户管理</li>
        </ul>
    </div>
    <div class="main-content">
        <div class="btn-toolbar list-toolbar">
            <button class="btn btn-primary saveBut" data-backdrop="static" data-toggle="modal" data-target="#userAdd" type="button">
                <i class="fa fa-plus"></i>增加
            </button>
        </div>

        <div class="main-content">
            <input type="text" id="key"  name="key">
            <button  id="searchKey" type="button" onclick="searchByKey()">
                搜索
            </button>
        </div>

    </div>
        <table id="userTable"></table>
    </div>
 <div class="modal fade" id="userAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px;">
        <%@include file="user_update.jsp"%>
    </div> 
    </div>
    
    <div class="modal fade" id="forller" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px;">
        <%@include file="user_updat.jsp"%>
    </div>
</div>

</body>
<script type="text/javascript">

    // function searchByKey(){
    //
    //     var key = $("#key").val();
    //     $.ajax({
    //         type: 'post',
    //         url: 'listByKey',
    //         dataType: 'json',
    //         data: {
    //             'key':key
    //         }
    //     });
    //
    //     recordSearch();
    // }

</script>
</html>