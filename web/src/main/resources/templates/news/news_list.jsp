<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/14
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
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
    <script  src="../../../js/news/news_list.js"></script>
        <div class="content myHeader">
            <!--       <FORM action="department_delAll" method="post" name="memu">-->
            <div class="header">
                <ul class="breadcrumb">
                    <li class="active">资讯管理</li>
                </ul>
            </div>
            <div class="main-content">
                <div class="btn-toolbar list-toolbar">
                    <button id="addtimebtn" class="btn btn-primary saveBut" data-backdrop="static" data-toggle="modal" data-target="#newsAdd" type="button">
                        <i class="fa fa-plus"></i>增加
                    </button>
                </div>
                <table id="newsTable" data-toggle="table"
                       data-url="findByPage"
                       data-side-pagination="server"
                       data-pagination="true"
                       data-page-size="10">
                    <thead>
                    <tr>
                        <th data-field="orderNo1" data-formatter="orderNo" data-width="100">序号</th>
                        <th data-field="content" data-align="center">标题</th>
                        <th data-field="userName" data-align="center">发布者</th>
                        <th data-field="date" data-align="center" data-formatter="onDate">发布时间</th>
                        <th data-field="operate" data-formatter="onFormatter"
                            data-events="operateEvents" data-align="center">操作
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="modal fade" id="newsAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" style="width: 800px;">
                    <%@include file="news_update.jsp"%>
                </div>
            </div>
        </div>
    </body>
</html>