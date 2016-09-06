//去空格
function trimStr(str) {
    return str.replace(/^\s+|\s+$/g, ''); 
}

//验证手机号或者电话 /^0(([1-9]\d)|([3-9]\d{2}))\d{8}$/
function isMobel(value){
  if(/^13\d{9}$/g.test(value)||(/^15[0-9]\d{8}$/g.test(value))|| (/^18[0-9]\d{8}$/g.test(value))){ 
    return true;
  }else if(/\d{3}-\d{8}|\d{4}-\d{7}/.test(value)){ 
	  return true; 
  }else if(/^0(([1-9]\d)|([3-9]\d{2}))\d{7,8}$/.test(value)){
	  return true; 
  }else{
	  return false;  
  }
}

//字符串长度
function getByteLen(val) {
    var len = 0;
    for (var i = 0; i < val.length; i++) {
        if (val[i].match(/[^\x00-\xff]/ig) != null) //全角
            len += 2;
        else
            len += 1;
    }
    return len;
}

//转换imgUrl大小写
function validateImg(fileName){
	fileName = fileName.toUpperCase();
	if(fileName.indexOf(".PNG")>=0 || fileName.indexOf(".JPG")>=0 || fileName.indexOf(".GIF")>=0) {
		return true;
	}else{
		return false;
	}
}

//js修改地址栏URL参数
function changeURLPar(destiny, par, par_value) { 
	var pattern = par+'=([^&]*)'; 
	var replaceText = par+'='+par_value; 
	if (destiny.match(pattern)) { 
		var tmp = '/\\'+par+'=[^&]*/'; 
		tmp = destiny.replace(eval(tmp), replaceText); 
		return (tmp); 
	} else { 
		if (destiny.match('[\?]')) { 
			return destiny+'&'+ replaceText; 
		} else { 
			return destiny+'?'+replaceText; 
		} 
	} 
	return destiny+'\n'+par+'\n'+par_value; 
} 

//格式化时间
function format(f) {  
	if((f.getMonth() + 1) < 10){
		 return f.getFullYear() + '-' +'0'+(f.getMonth() + 1) + '-' + f.getDate() + ' ' + f.getHours() + ':' + f.getMinutes();  
	}else{
		 return f.getFullYear() + '-' + (f.getMonth() + 1) + '-' + f.getDate() + ' ' + f.getHours() + ':' + f.getMinutes();  
	}
  
} 

//2个时间比较
function compareTime2(endDate,currDate) {   
	 if (endDate.length > 0 && currDate.length > 0) {   
	    var endDateTemp = endDate.split(" ");  
	    var currDateTemp = currDate.split(" ");   
	                   
	    var arrEndDate = endDateTemp[0].split("-");  
	    var arrCurrDate = currDateTemp[0].split("-");   
	  
	    var arrEndTime = endDateTemp[1].split(":");  
	    var arrCurrTime = currDateTemp[1].split(":");   
	  
		var allEndDate = new Date(arrEndDate[0], arrEndDate[1], arrEndDate[2], arrEndTime[0], arrEndTime[1]);   
		var allCurrDate = new Date(arrCurrDate[0], arrCurrDate[1], arrCurrDate[2], arrCurrTime[0], arrCurrTime[1]);   
		          
		if (allEndDate.getTime() <= allCurrDate.getTime() ) {   
		    return true;   
		} else {   
		    return false;   
		}   
	  }   
	}  
//3个时间比较
function compareTime(startDate,endDate,currDate) {   
	 if (startDate.length > 0 && endDate.length > 0 && currDate.length > 0) {   
	    var startDateTemp = startDate.split(" ");   
	    var endDateTemp = endDate.split(" ");  
	    var currDateTemp = currDate.split(" ");   
	                   
	    var arrStartDate = startDateTemp[0].split("-");   
	    var arrEndDate = endDateTemp[0].split("-");  
	    var arrCurrDate = currDateTemp[0].split("-");   
	  
	    var arrStartTime = startDateTemp[1].split(":");  
	    var arrEndTime = endDateTemp[1].split(":");  
	    var arrCurrTime = currDateTemp[1].split(":");   
	  
		var allStartDate = new Date(arrStartDate[0], arrStartDate[1], arrStartDate[2], arrStartTime[0], arrStartTime[1]);   
		var allEndDate = new Date(arrEndDate[0], arrEndDate[1], arrEndDate[2], arrEndTime[0], arrEndTime[1]);   
		var allCurrDate = new Date(arrCurrDate[0], arrCurrDate[1], arrCurrDate[2], arrCurrTime[0], arrCurrTime[1]);   
		                   
		if (allStartDate.getTime() <= allCurrDate.getTime() && allCurrDate.getTime() <= allEndDate.getTime()) {   
		    return true;   
		} else {   
		    return false;   
		}   
	  }   
	}   