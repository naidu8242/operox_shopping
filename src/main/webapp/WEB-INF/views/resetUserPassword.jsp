
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
    	<div class="signin_wrap reset_wrap_a" id="signin">
        	<h1>Reset Password</h1>
        	<c:if test="${not empty msg}">
	        <div id="selector">
		         <span><font color="green">${msg}</font></span>
			</div>
           </c:if>
             <form class="login-form clearfix"  id="resetPassword_form"  name="resetPassword_form" >
             <div id="loading"></div>
            <div id=passwordDiv>
            <span>
            	<label>Verification Code</label>
                <input type="text" class="signin_fild" name="verificationCode" data-validation="required" field-name="Verification Code" id="verificationCode" value="${verificationCode}" />
                <b></b>
            </span>
            <div class="clearfix"></div>
            <span>
            	<label>Password</label>
                <input type="Password" class="signin_fild" name="password" field-name="Password" data-validation="required validate_length length6-100" value="${password}" id="password" onkeydown="$(this).clear();" />
                <b></b>
            </span>
            <div class="clearfix"></div>
            <span>
            	<label>Confirm Password</label>
                <input type="Password" class="signin_fild" value="" data-validation="required" field-name="Confirm Password" id="confirmPassword" onblur="$(this).checkPass();" />
                <b></b>
            </span>
            <div class="clearfix"></div>
            </div>
            <input type="submit" value="Save" id="submit_button"  class="sign_btn" />
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
    <script src="resources/js/formvalidator.js"></script>
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


<script type="text/javascript">
(function($) {
	$.fn.checkPass = function() {   
		var pass1 = document.getElementById('password');  
	    var pass2 = document.getElementById('confirmPassword');
	    if(pass1.value == pass2.value){
	    	 $('input#confirmPassword').removeAttr( "class pass-exist pass-exist-errormsg");
			 $('#passwordDiv').find('span.jquery_form_error_message').remove(); 
			 document.getElementById("confirmPassword").setAttribute('class','valid signin_fild');
			 $("input#confirmPassword").removeAttr("style");
			 document.getElementById('submit_button').disabled = false;
	    }
	    else{ 
	    	
		        $('#passwordDiv').find('span.jquery_form_error_message').remove();
		        document.getElementById("confirmPassword").setAttribute('class','error signin_fild');
		        $("input#confirmPassword").after("<span class='jquery_form_error_message'> <font color='red'>Passwords Not Matched</font></span>");
				document.getElementById("confirmPassword").setAttribute('pass-exist','yes');
				document.getElementById("confirmPassword").setAttribute('pass-exist-errorMsg', ' Passwords Not Matched'); 
				$("input#confirmPassword").removeAttr("style");
				document.getElementById('submit_button').disabled = true;
				
	    }
   }
	
    $.fn.clear = function() {   
         $('#confirmMessage').empty(); 
    	 $('#confirmPassword').val('');
    	
	 return false;
    }
})(jQuery);
</script>





<script type="text/javascript">
var validationSettings = {
        errorMessagePosition : 'element',
       borderColorOnError : '',
		scrollToTopOnError : true,
		dateFormat : 'yyyy/mm/dd' 
   };  
         $('body').on('submit', '#resetPassword_form', function(e){
        	  if ($(this).validate(false, validationSettings)){
        		e.preventDefault();
        		var form = $('#resetPassword_form').serializeFormJSON();
              	var con = JSON.stringify(form);
  			    con = con.replace(/[{}]/g, ""); 
  			    var jsonData = encodeURIComponent(con);
  			    var operoxUrl ='${operoxUrl}';
  			    $("#submit_button").attr("disabled", "disabled");
  			    $("#loading").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("resources/images/loading.gif"); background-position:center; background-repeat:no-repeat; background-size:50px;');
                  $.ajax({
              	      type: "GET",
              	      contentType : 'application/json; charset=utf-8',
              	      dataType : 'json',
                    	  url: operoxUrl+"/verifyResetPassword?${_csrf.parameterName}=${_csrf.token}&json="+con,
                      	success: function(result) {
                      		if((result == 'Invalid')){
  	                   			 $('#passwordDiv').find('span.jquery_form_error_message').remove();
  	             		         document.getElementById("verificationCode").setAttribute('class','error signin_fild');
  	             		         $("input#verificationCode").after("<span class='jquery_form_error_message'> <font color='red'>Invalid Verification Code</font></span>");
  	             				 document.getElementById("verificationCode").setAttribute('pass-exist','yes');
  	             				 document.getElementById("verificationCode").setAttribute('pass-exist-errorMsg', ' Invalid Verification Code'); 
  	             				 $("input#verificationCode").removeAttr("style");
  	             				$("#submit_button").removeAttr("disabled");
	    		         		$("#submit_button" ).addClass('sign_btn').val('Submit');
	    		         		$("#loading").removeAttr("style");
      			        	}else{
  								                   			     
      			        		location.replace(operoxUrl+"/login"); 
      			        	}
  			        },  
                  });
                
            	    return true;
            }
            else{
            	return false;	
               }
            });
         
         $('#resetPassword_form').showHelpOnFocus();
         
         
     
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

<script>
  $('#selector').delay(2000).fadeOut('slow');
</script> 


</html>
