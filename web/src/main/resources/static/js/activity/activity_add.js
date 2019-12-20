function save() {
    // $("#content").val($("#summernote").summernote("code"));
    var activityHead = trim($("#head").val());
    var activityArea = trim($("#area").find("option:selected").val());
    var activityAddress = trim($("#address").val());
    var activitySponsor = trim($("#sponsor").val());
    var activityTimes = trim($("#times").val());
    var activityContent = trim($("#content").val());
    if (activityHead == "") {
        showErrorMsg("请输入活动名称")
    } else if (activityArea == "") {
        showErrorMsg("请选择活动地点")
    } else if (activityAddress == "") {
        showErrorMsg("请输入详细地址")
    } else if (activitySponsor == "") {
        showErrorMsg("请输入活动主办方")
    } else if (activityTimes == "") {
        showErrorMsg("请选择活动时间")
    } else if (activityContent == "") {
        showErrorMsg("请输入活动介绍")
    } else {
        var times = activityTimes.split(" - ");
        $("#a_startTime").val(times[0]);
        $("#a_endTime").val(times[1]);
        $("#a_areaId").val(activityArea);
        $("#a_area").val($("#area").find("option:selected").text());
        $("#a_city").val($("#city").find("option:selected").text());
        $("#a_province").val($("#province").find("option:selected").text());
        $("#activityForm").submit();
    }

}