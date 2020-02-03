
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>::. Forget Password .::</title>
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
    	<div class="signin_wrap">
        	<h1>Forgot Password</h1>
            <form class="login-form clearfix"  id="forgetPassword_form"  name="forgetPassword_form" method="post" >
             <div id="loading"></div>
	            <div id="forgotPasswordDiv">
	            <span>
	            	<label>Email/User Name</label>
	                <i class="fa fa-envelope"></i>
	                <input type="text" name="email" value="${email}" id="email" class="signin_fild" required />
	            </span>
	            </div>
	            <input type="submit" value="Submit" id="submit_button" class="sign_btn sign_btn_for" />
	            <div class="cancel_for"><a href="<c:url value='/login'/>">Cancel</a></div>  
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



<script type="text/javascript">
        $('#forgetPassword_form')
           .submit(function(e) {
        	    e.preventDefault(); 
        		var form = $('#forgetPassword_form').serializeFormJSON();
            	var con = JSON.stringify(form);
			    con = con.replace(/[{}]/g, ""); 
			    var jsonData = encodeURIComponent(con);
			    
			    var operoxUrl ='${operoxUrl}';
			    $("#submit_button").attr("disabled", "disabled");
			    $( "#submit_button" ).addClass('sign_btn sign_btn_for').val('Processing..');
        		$('#forgotPasswordDiv').find('span.jquery_form_error_message').remove();
        		$("#loading").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("resources/images/loading.gif"); background-position:center; background-repeat:no-repeat; background-size:50px;');
                $.ajax({
            	      type: "GET",
            	      contentType : 'application/json; charset=utf-8',
            	      dataType : 'json',
                  	  url: operoxUrl+"/resetPasswordWithUserName?${_csrf.parameterName}=${_csrf.token}&json="+jsonData,
                    	success: function(result) {
                    		if((result == 'Invalid')){
	                   			 $('#forgotPasswordDiv').find('span.jquery_form_error_message').remove();
	             		         document.getElementById("email").setAttribute('class','error signin_fild');
	             		         $("input#email").after("<span class='jquery_form_error_message'> <font color='red'>Invalid Email</font></span>");
	             				 document.getElementById("email").setAttribute('pass-exist','yes');
	             				 document.getElementById("email").setAttribute('pass-exist-errorMsg', ' Invalid Email'); 
	             				 $("input#email").removeAttr("style");
	             				$("#submit_button").removeAttr("disabled");
	    		         		$("#submit_button" ).addClass('sign_btn sign_btn_for').val('Submit');
	    		         		$("#loading").removeAttr("style");
    			        	}
                    		else if((result == 'login')){
    			        		  location.replace(operoxUrl+"/login"); 
    			        	}
                    		else{
                    			  location.replace(operoxUrl+"/resetUserPassword"); 
    			        	}
			        },  
                });
            });
</script>


<script type="text/javascript">
(function ($) {
   $.fn.serializeFormJSON = function () {

       var o = {};
       var a = this.serializeArray();
       $.each(a, function () {
           if (o[this.name]) {
               if (!o[this.name].push) {
                   o[this.name] = [o[this.name]];
               }
               o[this.name].push(this.value || '');
           } else {
               o[this.name] = this.value || '';
           }
       });
       return o;
   };
})(jQuery);
</script>

</html>
