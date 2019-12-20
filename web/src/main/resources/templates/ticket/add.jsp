<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" >
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, minimal-ui">
    <meta name="screen-orientation" content="portrait" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <meta name="full-screen" content="yes">
    <meta name="x5-fullscreen" content="true">
    <link rel="stylesheet" href="../../../lib/bootstrap/css/summernote.css"/>
    <script src="../../../js/ticket/ticket_add.js"></script>
</head>
<body>
<%@include file="../common/header.html"%>
<%@include file="../common/sysmenu.html"%>
<div class="content myHeader">
    <div class="header">
        <ul class="breadcrumb">
            <li>票列表</li>
            <li class="active">${title}票</li>
        </ul>
    </div>
    <form class="form-horizontal p-t-lg" action="${action}" onsubmit="toSubmit()" method="post" enctype="multipart/form-data">
        <input type="hidden" id="menuId" value="${param.menuId}" name="menuId">
        <input type="hidden" id="menuName" value="${param.name}" name="menuName">
        <input type="hidden" id="ticketId" value="${param.ticketId}" name="ticketId">
        <div id="div-box">
            <div class="form-group text-basic">
                <div class="col-lg-2 text-right">
                    <span>活动id<i class="red p-r-sm">*</i></span>
                </div>
                <div class="col-lg-5">
                    <input type="text" name="activityId" id="activity_id" class="form-control" placeholder="输入活动id">
                </div>
            </div>
            <div class="form-group text-basic">
                <div class="col-lg-2 text-right">
                    <span>票类型名称<i class="red p-r-sm">*</i></span>
                </div>
                <div class="col-lg-5">
                    <input type="text" name="ticketTypeName" id="ticket_name" class="form-control" placeholder="输入票类型名称">
                </div>
            </div>

            <c:if test="action == 'update'">
                <div class="form-group text-basic">
                    <div class="col-lg-2 text-right">
                        <span>票剩余量<i class="red p-r-sm">*</i></span>
                    </div>
                    <div class="col-lg-5">
                        <span id="ticket_surplus"></span>
                    </div>
                </div>
            </c:if>
            <div class="form-group text-basic">
                <div class="col-lg-2 text-right">
                    <span>票价格<i class="red p-r-sm">*</i></span>
                </div>
                <div class="col-lg-5">
                    <input type="text" name="ticketPrice" id="ticket_price" class="form-control" placeholder="输入票价格">
                </div>
            </div>
            <div class="form-group text-basic">
                <div class="col-lg-2 text-right">
                    <span>售卖数量<i class="red p-r-sm">*</i></span>
                </div>
                <div class="col-lg-5">
                    <input type="text" name="ticketCount" id="ticket_count" class="form-control" placeholder="输售卖数量">
                </div>
            </div>

            <div class="btn-group" style="position: absolute;top:18px;right:25px;">
                <input type="submit" id="submit-btn" value="保存" class="btn btn-primary m-r-lg text-basic">
                <a href="javascript:void(0)" class="btn btn-default m-r-lg text-basic" onclick="window.history.go(-1);">返回</a>
            </div>
        </div>
    </form>
</div>
</body>
<script src="../../../lib/bootstrap/js/summernote.js"></script>
<script type="text/javascript">
    $(function () {
        var ticketId = $("#ticketId").val();
        if (ticketId != null && ticketId != "") {
            $.post("getTicket", {
                "ticketId": $("#ticketId").val()
            }, function (data) {
                $("#activity_id").val(data.activityId);
                $("#ticket_name").val(data.ticketTypeName);
                $("#ticket_price").val(data.ticketPrice);
                $("#ticket_count").val(data.ticketCount);
            })
        }
    })
</script>
</html>

