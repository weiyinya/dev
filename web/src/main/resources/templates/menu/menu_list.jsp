<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>菜单列表</title>
<script src="../../../js/menu/menu.js"></script>

<STYLE type="text/css">
#top_alert{width:250px;margin-top:200px;padding:10px 0;background:#fefcef;border:1px solid #f8efb0;color:#e69041;position:absolute;text-align:center;left:35%;top:0;}
</STYLE>
</head>
<body>
  <%@include file="../common/header.html"%>
   <%@include file="../common/sysmenu.html"%>
	<div class="content myHeader">
        <div class="header">
        <ul class="breadcrumb">
            <li class="active">菜单列表</li>
        </ul>
        </div>
        <div class="main-content">
	<div class="btn-toolbar list-toolbar">
  <div class="btn-group">
  </div>
</div>
        <div class="container">
						<div class="row">
							        <div class="col-sm-1"></div>
							<div class="col-sm-6">
								<label for="treeview"></label>
								<div id="treeview" >
								</div>
							</div>
						</div>
					</div>
        </div>
    </div>

 		<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 300px;">
				<%@include file="menu_add.jsp" %>
			</div>
		</div>
 		<div class="modal fade" id="update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 300px;">
				<%@include file="menu_update.jsp" %>
			</div>
		</div>
		<script type="text/javascript">
			function buildDomTree() {
				var datas = [];
			    var index = 0;
			    function walk(nodes, datas) {
		          if (!nodes) { return; }
				      $.each(nodes, function (id, node) {
				        var obj = {
				          id: id,
				          text: node.id==5? 
				          node.name + "<div style='float:right'><button title='添加' class='btn btn-default btn-xs saveBut' data-backdrop='static' data-toggle='modal' data-target='#add' type='button' onclick='addParentId("+node.id+")' disabled='disabled'><i class='fa fa-plus'></i></button></div>"
				          :
				          node.name +"<div style='float:right'><button title='添加' class='btn btn-default btn-xs saveBut' data-backdrop='static' data-toggle='modal' data-target='#add' type='button' onclick='addParentId("+node.id+")' disabled='disabled'><i class='fa fa-plus'></i></button>"
				               +"<button title='修改' class='btn btn-default btn-xs updateBut' data-backdrop='static' data-toggle='modal' data-target='#update' type='button' onclick='getupMenu("+node.id+")' disabled='disabled'><i class='fa fa-pencil'></i></button>"
// 				               +"<button title='删除' class='btn btn-default btn-xs' type='button' onclick='del("+node.id+")'><i class='fa fa-trash-o'></i></button></div>"
				          ,
				             tags:[],
				        };
				        if (node.menuSet.length > 0) {
							obj.nodes = [];
							  	index = ++index;
							walk(node.menuSet, obj.nodes);
				        }
				        datas.push(obj);
				      });
					index = 1;
		        }
				var obj = '<%=request.getAttribute("menus")%>';
				var data = $.parseJSON(obj);
				walk(data, datas);
				return datas;
			}

			$(function() {
				var options = {
					bootstrap2 : false,
					showTags : false,
					levels : 2,
					nodeIcon : '',
					data : buildDomTree(),
					'onNodeExpanded': function(event, node) {
                        setTimeout(activeFlag, 1000);
                    },
                    'onNodeCollapsed': function(event, node) {
                        setTimeout(activeFlag, 1000);
                    }
				};
				$('#treeview').treeview(options);
				$('#add').on('hidden.bs.modal', function () {
				  activeFlag();
				});
			});
		</script>
</body>
</html>