<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
    </head>
    <body>
        <div class="content myHeader" style="padding-left: 0px; padding-right: 0px;border-top-width: 0px;">

                  <input type="hidden" name="userId" id="userId">


                <div class="col-sm-12 col-md-12" style="margin-bottom: 30px">
                    添加粉丝：
                    <div class="input-group input-group-sm form-group">
                        <input type="text" class="form-control" id="fansNum" name="fansNum" value="0">
                    </div>
                </div>
              
                <div class="col-sm-12 col-md-12"  style="margin-bottom: 20px">
                 <button class="btn btn-default" data-dismiss="modal" type="button" onclick="addClickNum()">确认添加</button>
           
                </div>
            </div>
 <script type="text/javascript">
      function addClickNum(){
    	
	//关闭弹窗
    hideModal('preview')
	var userId = $("#userId").val()
	var fansNum = $("#fansNum").val()
	//alert(clickNum + ' ' + contentId)
    $.ajax({
        type: 'get',
        url: 'listByfoller',
        dataType: 'json',
        data: {
        	'userId':userId,
			'fansNum':fansNum
		},
        success: function (data) {
            showMsg('添加成功')
        }
    });

	//刷新页面
    recordSearch();
}
      </script>    
    </body>
</html>