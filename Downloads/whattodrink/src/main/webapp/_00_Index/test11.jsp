<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<a href="<c:url value="/_05_Order/preConfirmOrderServlet"/>">再次確認訂單</a>
	<a href="<c:url value="/AddCommentsServlet?order_id=A0120210913001"/>">測試</a>
	<a href="<c:url value="/AddCommentsServlet?order_id=A0120210913002"/>">測試2</a>
	<a href="<c:url value="/_04_ShoppingCart/saveOrderServlet"/>">存訂單</a>
	
	
	
<input class="form-control" id="blockimg" type="file" >
<button id="upload">Upload</button>


 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
function checkAuditTime(beginTime,endTime) {
	let nowDate=new Date();
	let beginDate=new Date(nowDate);
	let endDate=new Date(nowDate);
	
	let beginIndex=beginTime.lastIndexOf("\:");
	let beginHour =beginTime.substring(0,beginIndex);
	let beginMinute=beginTime.substring(beginIndex+1,beginIndex.length);
	
	beginDate.setHours(beginHour,beginMinute,0,0);
	
	
	let endIndex=endTime.lastIndexOf("\:");
	let endHour =endTime.substring(0,endIndex);
	let endMinute=endTime.substring(endIndex+1,endIndex.length);
	
	endDate.setHours(endHour,endMinute,0,0);
	
	if(nowDate.getTime()-beginDate.getTime()>=0&&nowDate.getTime()<=endDate.getTime()){
		return true;
	}else{
		return false;
	}
		
		
	}

	
if(checkAuditTime("10:00","15:48")){
	alert("在區間內");
}else{
	alert("不再區間內");
	
}

</script>
</body>
</html>