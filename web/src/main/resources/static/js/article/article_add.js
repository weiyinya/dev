function toSubmit() {
    $("#articleText").val($("#summernote").summernote("code"));
    return true;
}

//下面用于多图片上传预览功能
function setImagePreviews(avalue) {
    var docObj = avalue;
    var dd = docObj.nextElementSibling || docObj.nextSibling;
    dd.innerHTML = "";
    var imgSrc = "";
    if (docObj.files && docObj.files[0]) {
        imgSrc = window.URL.createObjectURL(docObj.files[0]);
    } else {
        imgSrc = document.selection.createRange().text;
    }
    dd.innerHTML += "<div style='float:left' > <img src='" + imgSrc + "' width='150px' height='180px' display = 'block'/> </div>";
    return true;
}