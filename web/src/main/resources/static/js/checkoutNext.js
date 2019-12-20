//删除左右两端的空格
function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
//非空的校验
function trimFrom(obj,str){
	if(obj.value==null||trim(obj.value)==""){
		 showErrorMsg(str);
		return false;
	}
	return true;
}
//姓名的校验
function nameFrom(name,str){
	var check = /[^\u4e00-\u9fa5]/g;
	var check_e = /[^a-zA-Z]/g;
    if(trim(name.value).match(check)&&trim(name.value).match(check_e)){
    	showErrorMsg(str);
 	   	return false;
    }
    return true;
}
//账号的校验
function emailFrom(email,str){
	var check =  /^[a-zA-Z0-9_+.-]+\@+[a-zA-Z0-9.]+$/;
	var val = trim(email.value);
	if(!check.exec(trim(email.value))){
		showErrorMsg(str);
		return false;
	}
	if(val.lastIndexOf(".")+1 == val.length){
		showErrorMsg(str);
		return false;
	}
	return true;
}
//手机的校验
function phoneFrom(phone,str){
	 var check= /^(1)\d{10}$/;
     if (!check.exec(trim(phone.value))){
    	 showErrorMsg(str);
  	     return false;
     }
     return true;
}
//域名的校验
function domainFrom(domain,str){
	var check = /^[0-9a-zA-Z.]{26}$/;
	var val = trim(domain.value);
	if(!check.test(trim(domain.value))){
		showErrorMsg(str);
		return false;
	}
	if(val.lastIndexOf(".")+1 == val.length){
		showErrorMsg(str);
		return false;
	}
	return true;
}
//名称的校验
function nicknameFrom(nickname,str){
	var check = /^[0-9a-zA-Z\u4e00-\u9fa5()（）_]+$/;
	if (!check.test(trim(nickname.value))) {
		showErrorMsg(str);
		return false;
	}
	return true;
}
//sip短号的校验
function shortNumberFrom(shortNumber,str){
	var check=/^[2-8]\d{3}$/;
	if(!check.exec(trim(shortNumber.value))){
		showErrorMsg(str);
		return false;
	}
	return true;
}
//时间的校验
function timeFrom(start,end,str){
	var a = start.value.split(" ");
	var b = a[0].split("-");
	var c = a[1].split(":");
	var starttime = new Date(b[0],b[1],b[2],c[0],c[1]);
	
	var d = end.value.split(" ");
	var e = d[0].split("-");
	var f = d[1].split(":");
	var endtime = new Date(e[0],e[1],e[2],f[0],f[1]);
	if(starttime.getTime()>=endtime.getTime()){
		showErrorMsg(str);
		return false;
	}
	return true;
}
//IP的校验
function ipFrom(ip,str){
	var check = /^((?:(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))\.){3}(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d))))$/;
    if(!check.exec(ip.value)){
    	showErrorMsg(str);
 	   	return false;
    }
    return true;
}
//端口的校验
function portFrom(port,str){
	 if(!(port.value<65536&&port.value>0)){
		 showErrorMsg(str);
 	   return false;
	 }
	 return true;
}
//中文的校验
function CHNFrom(name,str){
	var check = /[^\u4e00-\u9fa5]/g;
	if(check.exec(trim(name.value))){
		showErrorMsg(str);
 	   	return false;
	}
	return true;
}
//密码的校验
function passWordFrom(password){
	var password_reg = /^[A-Za-z0-9\_\@\#]{8,20}$/;
	var simple_num=/^[0-9]{8,20}$/;
	if (!password_reg.test(trim(password.value))) {
		showErrorMsg("密码不符合规则");
		return false;
	}
	if(simple_num.test(trim(password.value))){
		showErrorMsg("密码不能全为数字");
		return false;
	}
	return true;
}
//数字的校验
function numberFrom (number, str) {
	var number_reg = /[^\d]/g;
	if (number_reg.test(trim(number.value))) {
		showErrorMsg(str);
		return false;
	}
	return true;
}
//判断是否全为零
function zeroFrom (number, str) {
	var zero_reg = /^0+$/;
	if (zero_reg.test(trim(number.value))) {
		showErrorMsg(str);
		return false;
	}
	return true;
}

