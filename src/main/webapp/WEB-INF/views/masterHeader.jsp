<!DOCTYPE html>
<html lang="en"> 
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="${operoxUrl}/resources/images/fav.png" />
<title>Welcome to Operox :: The Online Store Application</title>
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">

<link href="${operoxUrl}/resources/css/chosen.min.css" rel="stylesheet">
<link href="${operoxUrl}/resources/css/jquery-ui.css" rel="stylesheet">
<link href="${operoxUrl}/resources/css/jquery.timepicker.min.css" rel="stylesheet">
<link href="${operoxUrl}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${operoxUrl}/resources/css/operox-main.css" rel="stylesheet">
<link href="${operoxUrl}/resources/css/operox2.css" rel="stylesheet">
<link href="${operoxUrl}/resources/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="${operoxUrl}/resources/css/bootstrap-select.min.css" type="text/css" >
<link href="${operoxUrl}/resources/css/jquery.timepicker.min.css" rel="stylesheet">



<script src="${operoxUrl}/resources/js/jquery-1.11.3.min.js"></script> 
<script src="${operoxUrl}/resources/js/moment.min.js"></script>
<script src="${operoxUrl}/resources/js/chosen.jquery.min.js"></script>
<script src="${operoxUrl}/resources/js/jquery-ui.min.js"></script> 
<script src="${operoxUrl}/resources/js/bootstrap-datetimepicker.min.js"></script> 
<script src="${operoxUrl}/resources/js/bootstrap.min.js"></script>
<script src="${operoxUrl}/resources/js/jquery.timepicker.min.js"></script>
<script src="${operoxUrl}/resources/js/masterHeaderAction.js"></script> 
<script src="${operoxUrl}/resources/js/jquery.timepicker.min.js"></script> 


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<script>
		function logoutFormSubmit() {
			document.getElementById("logoutForm").submit();
		}
</script>

<script>
		function linkFormSubmit(orgLocationId) {
			document.getElementById("logoutForm").action="${operoxUrl}/dashboard/"+orgLocationId
			document.getElementById("logoutForm").submit();
		}
</script>

<script>
	function predefineProfileImage(imageId){
		$(imageId).attr('src','${operoxUrl}/resources/images/userprofileImg.jpg');
	}
</script>


<body>
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post" id="logoutForm">
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<form action="${logoutUrl}" method="post" id="logoutForm">
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>


</head>
<body>
<div class="wraper clearfix">
<!-- Header Area -->
<header class="main-header">
<span class="logo text-center">
    <a href="javascript:void(0)" title="Operox"> <i><img src="${operoxUrl}/resources/images/logo-operox.png" alt="Operox" title="Operox"></i></a>
</span>
  
  
  <nav class="header-nav navbar"> <a href="javascript:void(0)" class="side-menu hidden"> <i class="fa fa-bars pull-left"></i> </a>
    <div class="custom-navbar">
      <div class="search-area">
        <div class="search-area-txt"> <i class="fa fa-search"></i>
          <input type="text" placeholder="Search ..." class="form-control">
        </div>
      </div>
      <!-- login User details -->
      <div class="login-user custom-navbar-active">
      <span class="image">
         <img class="" alt="Admin" title="Pratap Reddy" onerror="predefineProfileImage(this);"   src="${operoxUrl}/displayUserImage/${OperoxWeb_userId}" >
      </span>
      <span class="pull-left tbldata-dotdot-vsm">${OperoxWeb_user.firstName} ${OperoxWeb_user.lastName}</span>
        <div class="user-details">
          <div class="user-media">
            <div class="logn-image">
            <img class="" title="Pratap Reddy" alt="Admin" onerror="predefineProfileImage(this);"  src="${operoxUrl}/displayUserImage/${OperoxWeb_userId}"> </div>
            <div class="user-media-body">
              <p><span class="tbldata-dotdot-me">${OperoxWeb_user.firstName} ${OperoxWeb_user.lastName}</span></p>
              <p>${OperoxWeb_roleDisplayName}</p>
            </div>
          </div>
          <ul class="list-unstyled">
            <li><a href="${operoxUrl}/userSettings"><span><i class="fa fa fa-user"></i></span>&nbsp;View Profile</a></li>
            <li><a href="javascript:logoutFormSubmit()"><span><i class="fa fa-sign-out"></i></span>&nbsp;Log out</a></li>
          </ul>
        </div>
      </div>
      <!-- login User details -->
      
      <ul class="list-inline list-unstyled pull-left custom-navbar-inner">
        <li> <a href="javascript:void(0)" title="Quick Links" id="qlinks"> <i class="fa fa-link"></i> </a>
           <div class="qlinks-area">
             <ul class="list-unstyled">
               <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_OPERATION_MANAGER','ROLE_QC_MANAGER','ROLE_PRODUCTION_MANAGER')">
               <li><a href="${operoxUrl}/addWorkOrder">Work Order</a></li>
               <li><a href="${operoxUrl}/newRawMaterial">Raw Material</a></li>
               <li><a href="${operoxUrl}/addCheckList">QC CheckList</a></li>
               </sec:authorize>
               
               <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_OPERATION_MANAGER','ROLE_STORE_MANAGER','ROLE_PRODUCTION_MANAGER','ROLE_CASHIER')">
               <li><a href="${operoxUrl}/addCustomer">Customer</a></li>
               <li><a href="${operoxUrl}/addTicket">Tickets</a></li>
               </sec:authorize>
               
               <sec:authorize access="!hasAnyRole('ROLE_HR_MANAGER','ROLE_CASHIER')"> 
                <li><a href="${operoxUrl}/purchaseorder">Purchase Order</a></li>
                <li><a href="${operoxUrl}/receivedItem">Received Stock</a></li>
                <li><a href="${operoxUrl}/returnStock">Return Stock</a></li>
                <li><a href="${operoxUrl}/transferStock">Transfer Stock</a></li>
               </sec:authorize>
               
               <sec:authorize access="!hasAnyRole('ROLE_HR_MANAGER','ROLE_QC_MANAGER')"> 
         		 <li><a href="${operoxUrl}/billing">Billing</a></li>
          	   </sec:authorize>
          	   <li><a href="${operoxUrl}/addUser">User</a></li>
          		
          		<sec:authorize access="!hasAnyRole('ROLE_HR_MANAGER')">
          		 <li><a href="${operoxUrl}/addOffer">Offer</a></li>
          		</sec:authorize>
             </ul>
           </div>
		</li>
        <li> <a href="#" title="Notifications" id="notification"> <i class="fa fa-envelope"></i> <span class="label label-primary">4</span> </a>
          <div class="header-widget note-area">
            <ul class="list-unstyled">
              <li>
                <div class="clearfix msg">
                  <div class="msg-img"> <span> <img src="images/pratap-bis.jpg" title="Pratap Reddy" alt="Admin" class="img-circle"> </span> </div>
                  <div class="msg-content">
                    <p>Earlier this week, I was doing some work on a project that was using</p>
                    <span>3 days ago at 7:58 pm - 2.07.2016</span> </div>
                  <div class="msg-time"> <span> <i class="fa fa-clock-o"></i><br>
                    1hr ago </span> </div>
                </div>
              </li>
              <li>
                <div class="clearfix msg">
                  <div class="msg-img"> <span> <img src="images/pratap-bis.jpg" title="Pratap Reddy" alt="Admin" class="img-circle"> </span> </div>
                  <div class="msg-content">
                    <p>Earlier this week, I was doing some work on a project that was using</p>
                    <span>3 days ago at 7:58 pm - 2.07.2016</span> </div>
                  <div class="msg-time"> <span> <i class="fa fa-clock-o"></i><br>
                    1hr ago </span> </div>
                </div>
              </li>
              <li>
                <div class="clearfix msg">
                  <div class="msg-img"> <span> <img src="images/pratap-bis.jpg" title="Pratap Reddy" alt="Admin" class="img-circle"> </span> </div>
                  <div class="msg-content">
                    <p>Earlier this week, I was doing some work on a project that was using</p>
                    <span>3 days ago at 7:58 pm - 2.07.2016</span> </div>
                  <div class="msg-time"> <span> <i class="fa fa-clock-o"></i><br>
                    1hr ago </span> </div>
                </div>
              </li>
              <li>
                <div class="clearfix msg">
                  <div class="msg-img"> <span> <img src="images/pratap-bis.jpg" title="Pratap Reddy" alt="Admin" class="img-circle"> </span> </div>
                  <div class="msg-content">
                    <p>Earlier this week, I was doing some work on a project that was using</p>
                    <span>3 days ago at 7:58 pm - 2.07.2016</span> </div>
                  <div class="msg-time"> <span> <i class="fa fa-clock-o"></i><br>
                    1hr ago </span> </div>
                </div>
              </li>
              <li>
                <div class="clearfix msg">
                  <div class="msg-img"> <span> <img src="images/pratap-bis.jpg" title="Pratap Reddy" alt="Admin" class="img-circle"> </span> </div>
                  <div class="msg-content">
                    <p>Earlier this week, I was doing some work on a project that was using</p>
                    <span>3 days ago at 7:58 pm - 2.07.2016</span> </div>
                  <div class="msg-time"> <span> <i class="fa fa-clock-o"></i><br>
                    1hr ago </span> </div>
                </div>
              </li>
            </ul>
            <div class="headerwidget-footer"> <a href="javascript:void(0)">View All</a> </div>
          </div>
        </li>
        <li> <a href="#" title="Alerts" id="alert" onclick="notificationStatusAsRead();" > <i class="fa fa-bell bell" ></i> <span class="label label-warning">${OperoxWeb_notificationCount}</span></a>
            <div class="header-widget alert-area">
              <ul class="list-unstyled">
              <c:if test="${empty OperoxWeb_notificationList}">
              	<div class="clearfix msg">
                     All Caught UP!!! 
                  </div>
              	</c:if>
               <c:if test="${!empty OperoxWeb_notificationList}">
                <c:forEach var="report" items="${OperoxWeb_notificationList}" end="4">
                <li>
                  <div class="clearfix msg">
                    <div class="msg-img"> <span>
                    <img class="img-circle" title="${report.notificationFrom.firstName} ${report.notificationFrom.lastName}" alt="${report.notificationFrom.firstName}" onerror="predefineProfileImage(this);" src="<c:url value='/displayUserImage/${report.notificationFrom.id}'/>">
                    </span> </div>
                    <div class="msg-content">
                      <p><a href="${report.actionLink}"><span>${report.body}</span></a></p>
                     <c:out value="${userId}" />
                      <span>${report.calDays} days ago at ${report.time} ${report.dateValue}</span> </div>
                  </div>
                </li>
                </c:forEach>
                </c:if>
              </ul>
             <%--  <div class="headerwidget-footer"> <a href="<c:url value='/notificationsList'/>">View All</a> </div> --%>
            </div>
          </li>
        <!-- <li> <a href="#" title="Settings" class="settings"> <i class="fa fa-cog fa-fw"></i></a>
          <div class="setting-area">
            <ul class="list-unstyled">
              <li><a href="orgsettings.html"><i aria-hidden="true" class="fa fa-gears"></i>Org. Settings</a></li>
              <li><a href="org-hierarchy.html"><i aria-hidden="true" class="fa fa-sitemap"></i>Org. Hierarchy</a></li>
              <li><a href="javascript:void(0)"><i aria-hidden="true" class="fa fa-power-off"></i>Log Out</a></li>
            </ul>
          </div>
        </li> -->
      </ul>
    </div>
  </nav>
</header>

<script>
function notificationStatusAsRead(){   
	 var operoxUrl ='${operoxUrl}';
	var  orgCode ='operox';
	 var locationCode = 'operoxIN';
	 $.ajax({
	      type: "POST",
	  	  url: operoxUrl+"/notificationStatusAsRead?${_csrf.parameterName}=${_csrf.token}&orgCode="+orgCode+"&locationCode="+locationCode,
	  			  success :function(data) {
	    	      			
      			 }							     
	}); 
	 $("#alert").click(function(){
         $("span.label-warning").text(0);
	 });
}
</script>
</body>
</html>
