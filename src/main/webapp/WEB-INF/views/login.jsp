
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>::. Sign In .::</title>
    <link rel="icon" type="resources/image/png" href="resources/images/fav.png" />
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,300italic,300,400italic,500,700,900" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
     <link href="resources/css/bootstrap.min.css" rel="stylesheet">
     <link href="resources/css/register-style.css" rel="stylesheet">
     <link href="resources/css/jquery-ui.css" rel="stylesheet">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@page session="true"%>
</head>

<body>
  
<div class="regis_bg">

<div class="sign_head">
	<div class="regis_logo"><img src="resources/images/logo-operox-1.png" alt=""/></div>
    <ul>
    	<li><a href="javascript:void(0)">About us</a></li>
        <li><a href="javascript:void(0)">What we do</a></li>
        <li><a href="javascript:void(0)">Contact us</a></li>
    </ul>    
<div class="clear"></div>
</div>

<div class="sign_inn">

	<div class="sign_inn_text">
         <div class="clear"></div>
    </div>
	
    <div class="regis_wrap regisWrap">
    	<div class="signin_wrap" id="signin">
        	<h1>Sign In</h1>
	       	<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			
			<c:if test="${!empty login_blocked_error}">
					 <div class="error">Your user account is locked. Please contact your customer relationship manager</div>
	      	</c:if>
	      		
	      	<c:if test="${!empty login_inActive_error}">
	      			<div class="error"><font color="red">You are In active user</font></div>
	    	</c:if>
	        <c:if test="${!empty login_unRegistered_error}">
	         		<div class="error"><font color="red">You are not registered user. Please get register.</font></div>
	    	</c:if> 
	       <c:if test="${!empty login_password_error}">
	         		<div class="error"><font color="red">Invalid Username or Password.</font></div>
	    	</c:if> 
	    	<c:if test="${!empty login_blocked_user_error}">
	         		<div class="error"><font color="red">Your User has blocked. Please contact your admin</font></div>
	    	</c:if>
	    	<c:if test="${not empty msg}">
		        <div id="selector">
			         <span><font color="green">${msg}</font></span>
				</div>
           </c:if> 
    	<br/>
        	 <form class="login-form clearfix" action="<c:url value='/login' />" method='POST'>
	            <span>
	            	<label>User name</label>
	                <i class="fa fa-user"></i>
	                <input type="text" name="username"  value="${username}" class="signin_fild" required />
	                <b></b>
	            </span>
	            <span>
	            	<label>Password</label>
	                <i class="fa fa-key si-pass"></i>
	                <input type="Password"  name="password"  value="${password}"  class="signin_fild" required />
	                <b></b>
	            </span>
	            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	            
	             <div class="for-pas-text"><a href="<c:url value='/forgotUserPassword'/>">Forgot Password</a></div> 
	            <input type="submit" value="Submit" class="sign_btn" />
            </form>          
        </div>
    </div>
    
<div class="clear"></div>
</div>    
  
  <div class="clear">
   <ul class="features">
    <li><i class="fa fa-check"></i>&nbsp;&nbsp;Production Management</li>
    <li><i class="fa fa-check"></i>&nbsp;&nbsp;Quality Control</li>
    <li><i class="fa fa-check"></i>&nbsp;&nbsp;Inventory Management</li>
    <li><i class="fa fa-check"></i>&nbsp;&nbsp;Supplychain Management</li>
    <li><i class="fa fa-check"></i>&nbsp;&nbsp;POS Solutions</li>
   </ul>
  </div>  
</div>  
  
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/jquery-fun.js"></script> 
</body>



<script>
$(function () {
     applyInputSlide();
});

function applyInputSlide() {
    $(".signin_wrap span input").each(function (i) {
        if ($(this).val().trim() !== "")
            $(this).parent(".signin_wrap span").find('label').addClass('test');
    });
    $(".signin_wrap span input").on('focus', function () {
        $(this).parent(".signin_wrap span").find('label').addClass('test');
    });
    $(".signin_wrap span input").on('blur', function () {
        if ($(this).val().trim() === "")
            $(this).parent(".signin_wrap span").find('label').removeClass('test');
    });	    
}
</script>

<script>
  $('#selector').delay(2000).fadeOut('slow');
</script> 

</html>
