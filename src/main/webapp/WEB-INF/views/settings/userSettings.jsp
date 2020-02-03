<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="resources/js/formvalidator.js"></script>

<script>
	function predefineProfileImage(imageId){
		$(imageId).attr('src','${operoxUrl}/resources/images/userprofileImg.jpg');
	}
</script>



<div class="wraper clearfix">

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Profie Settings</h2>
    </div>
  </section>
  
  <div class="mask"></div>
<div class="delete_pop">
		<p>Are you sure ?</p>
		<a class="close_color" href="javascript:void(0)" id="removeProfilePicConfirm">Yes</a><a href="javascript:void(0)">No</a> 
	</div>
  
<!-- Content Area inner -->
<div class="content-area clearfix">
    <div class="accountsettings-tab">
        <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#profile">Profile</a></li>
        <li><a data-toggle="tab" href="#password">Password</a></li>
        </ul>
        <div class="tab-content panel-body">
        <!-- Profie Tab -->
            <div id="profile" class="tab-pane fade in active">
            <form:form id="addUserProfile_form"  name="addUserProfile_form"  method="post">
            	<div class="div-col2">
		            <div class="image-parent">
		            <div class="imageParent">
		            <img id="userImage" onerror="predefineProfileImage(this);"  src="<c:url value='/displayUserImage/${OperoxWeb_userId}'/>" alt="productimg" id="userprofileImg">
		            </div>
		            
		            <!-- Upload file -->
		            <div class="upload-image">
		            <label>
		            <c:if test="${!empty OperoxWeb_user.fileContentType}">
		            <span class="btn btn-danger btn-sm"><i class="glyphicon glyphicon-trash"></i>
		            <input type="button" multiple id="deleteImg"  class="uploadd" onclick="removeProfilePic()" style="display: none;">
		            </span>
		            </c:if>
		            </label>
		            <label>
		            <span class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-open"></i>
		            <input type="file" name="file" id="uploadedImage"  onchange="loadFile(event)" class="upload" style="display: none;">
		            </span>
		            </label>
		            </div>
		            <!-- Upload file -->
		            </div>
		            
		            <p class="text-danger small" style="font-size: 11px; display: none;" id="errorImage" >Invalid Image/Size</p>
				  <p class="text-muted small" style="font-size: 11px;"><i class="fa fa-info-circle"></i>&nbsp;png, svg and gif images are not allowed</p>
				  <p class="text-muted small" style="font-size: 11px;"><i class="fa fa-info-circle"></i>&nbsp;Image size should not more than 200 KB</p>
            	</div>
	            <div class="div-col10">
		            <div class="div50">
		            <label class="labl">First Name<b class="text-danger">&nbsp;*</b></label>
		            <span>
		            <input type="text" id="firstName" name="firstName" class="form-control" value="${OperoxWeb_user.firstName}" field-name="First Name" data-validation="required validate_Alphanumeric validate_Space validate_length length2-100" >
		            </span>
		            </div>
	            
		            <div class="div50">
		            <label class="labl">Last Name</label>
		            <span>
		            <input type="text" placeholder="Last Name" name="lastName" class="form-control" id="lastName" value="${OperoxWeb_user.lastName}" field-name="Last Name" data-validation="validate_Alphanumeric validate_Space validate_length length2-100" data-validation-optional="true">
		            </span>
		            </div>
	            
		            <div class="div50">
		            <label class="labl">Email</label>
		            <span>
		            <input type="text" placeholder="Email" readonly="readonly" class="form-control" value="${OperoxWeb_user.email}">
		            </span>
		            </div>
	            </div>
            <div class="clearfix"></div>
            <div class="form-footer mt">
            <div class="col-lg-12">
            <div class="pull-right">
            <input type="button" value="Save" onclick="saveUserProfile();" id="profile_submit"  class="btn btn-primary" >
            <input type="button" value="Cancel" class="btn btn-default ml10">
            </div>
            </div>
            </div>
            </form:form> 
            </div>
           
            
            
        <!-- Profiel Tab Ends -->
                <!-- Password Tab -->
           <div id="password" class="tab-pane fade in">
           <form:form id="resetPassword_form"  name="resetPassword_form"  method="post">
                <div class="div-col2">
                <div class="image-parent">
                <div class="imageParent">
                <img src="resources/images/img-password.png" alt="Password img" id="passWord" >
                </div>
                </div>
                </div>
                
                
	            <div class="div-col10" id="passwordDiv">
		            <div class="div50">
		            <label class="labl">Current Password</label>
		            <span>
		            <input type="password" id="currentPassword" name="currentPassword" field-name="Current Password" value="${currentPassword}"  class="form-control" onblur="validateCurrentPassword()"  data-validation="required" >
		            </span>
		            </div>
		            
		            <div class="div50">
		            <label class="labl">New Password</label>
		            <span>
		            <input type="password" name="password"  value="${password}" id="password" field-name="New Password" class="form-control" data-validation="required validate_length length6-100">
		            </span>
		            </div>
		            
		            <div class="div50">
		            <label class="labl">Confirm Password</label>
		            <span>
		            <input type="password" value="" id="confirmPassword" class="form-control" field-name="Confirm Password" onblur="checkPass();" data-validation="required">
		            </span>
		            </div>
	            </div>
                <div class="clearfix"></div>
                <!-- Content Area inner -->
                <div class="form-footer mt">
	                <div class="col-lg-12">
	                <div class="pull-right">
	                <input type="button" value="Save" onclick="resetPassword();" class="btn btn-primary" id="save_button">
	                <input type="button" value="Cancel" class="btn btn-default ml10">
	                </div>
	                </div>
                </div>
                </form:form>
                </div>
                <!-- Password Tab Ends-->
        </div>
    </div>
</div>
</section>
<!-- Content Area Ends--> 

</div>

<jsp:include page="../masterFooter.jsp" />

<script type="text/javascript">
	 function checkPass() {   
		var pass1 =$('input#password').val();  
	    var pass2 = $('input#confirmPassword').val();
	    if((pass1 != null && pass1 != '' && pass1 != 'undefined') && (pass2 != null && pass2 != '' && pass2 != 'undefined')){
	    	if(pass1 == pass2){
	    		$("#save_button").removeAttr("disabled");
	    		$('input#confirmPassword').removeAttr( "class record-exist record-exist-errormsg");
				$('#passwordDiv').find('p.jquery_form_error_message').remove();
				$('input#confirmPassword').attr('class','valid form-control');
				$( "#save_button" ).removeAttr("style");
				$( "#save_button" ).addClass('btn btn-primary mr').val('Save');
				$( "#save_button" ).click(function() {
				});
				
	    	}
	    	else{
	    		$("#save_button").attr("disabled", "disabled");
	    		$('#passwordDiv').find('p.jquery_form_error_message').remove(); 
	    		$('input#confirmPassword').attr('class','error form-control');
	    		$("input#confirmPassword").closest('b').after("<p class='jquery_form_error_message'> Passwords Do Not Match</p>");
				$('input#confirmPassword').attr('record-exist','yes');
				$('input#confirmPassword').attr('record-exist-errorMsg',' Passwords Do Not Match'); 
				$( "#save_button" ).addClass('btn btn-primary mr').val('Save');
				$("#save_button").removeAttr("disabled");
	    	
	    	}
	    }
   }
	
   
</script>


<script type="text/javascript">
	 function validateCurrentPassword() {   
		var currentPassword = $('input#currentPassword').val();
		if(currentPassword != null  && currentPassword != '' && currentPassword != 'undefined'){
			var operoxUrl ='${operoxUrl}';
			
		     $.ajax({
			    	type: "POST",
			    	 url: operoxUrl+"/validateCurrentPassword?${_csrf.parameterName}=${_csrf.token}&currentPassword="+currentPassword, 
			        success: function(result) {
			         	 if(result == 'valid'){
				    		$("#save_button").removeAttr("disabled");
				    		$('input#currentPassword').removeAttr( "class record-exist record-exist-errormsg");
							$('#passwordDiv').find('p.jquery_form_error_message').remove();
							$('input#currentPassword').attr('class','valid form-control');
							$( "#save_button" ).removeAttr("style");
							$( "#save_button" ).addClass('btn btn-primary mr').val('Save');
							$( "#save_button" ).click(function() {
							});
							
				    	}
				    	else if(result == 'invalid'){
			    		$("#save_button").attr("disabled", "disabled");
				    		$('#passwordDiv').find('p.jquery_form_error_message').remove(); 
				    		$('input#currentPassword').attr('class','error form-control');
				    		$("input#currentPassword").after("<p class='jquery_form_error_message'>Wrong Password Entered</p>");
							$('input#currentPassword').attr('record-exist','yes');
							$('input#currentPassword').attr('record-exist-errorMsg',' Wrong Password Entered'); 
							$("#save_button" ).addClass('btn btn-primary mr').val('Save');
				    	} 
			        },
			    }); 
				
		}
		
	 }
 </script>




<script type="text/javascript">
	function resetPassword() {
		var validationSettings = {
			errorMessagePosition : 'element',
			borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd'
		};
		
		if ($('#resetPassword_form').validate(false, validationSettings)) {
			
		    var frm = $('#resetPassword_form').serializeFormJSON();
	        var con = JSON.stringify(frm);
	        con = con.replace(/[{}]/g, "");
	        var jsonData = encodeURIComponent(con);
	        
	        var operoxUrl ='${operoxUrl}';
     		$( "#save_button" ).addClass('button').val('Processing..');
	        $.ajax({
		    	type: "POST",
		    	 url: operoxUrl+"/resetUserPassword?${_csrf.parameterName}=${_csrf.token}&json="+jsonData, 
		        success: function(result) {
		        	if((result == 'success')){
		        		location.replace(operoxUrl+"/userSettings");  
		        	}
			        
		        },
		    }); 
			
			return true;
		} else {
			return false;
		}
	}
	$('body').on('blur', '#resetPassword_form', function() {
		$("#resetPassword_form").showHelpOnFocus();
		$("#resetPassword_form").validateOnBlur(false, validationSettings);
	});
</script>


<script type="text/javascript">
  var loadFile = function(event) {
    var output = document.getElementById('userImage');
    
    var uploadedImage = document.getElementById('uploadedImage').value;
    var sizeinbytes = document.getElementById('uploadedImage').files[0].size;
    document.getElementById("errorImage").style.display="none"; 
    
    if(uploadedImage.substring(uploadedImage.lastIndexOf(".")+1) != 'jpg'){
    	output.src = URL.createObjectURL(event.target.files[0]);
    	
    	var extensionName = uploadedImage.substring(uploadedImage.lastIndexOf(".")+1);
 		var msg = "."+extensionName+" extension not allowed";
 		document.getElementById("errorImage").style.display=""; 
 		$("#profile_submit").attr("disabled", "disabled");
    	
    }else if(sizeinbytes > 200000){
    	output.src = URL.createObjectURL(event.target.files[0]);
 		var msg = "Image size should be less than 200 KB";
 		document.getElementById("errorImage").style.display=""; 
 		$("#profile_submit").attr("disabled", "disabled");
    	
    }else{
    	output.src = URL.createObjectURL(event.target.files[0]);
    	$("#profile_submit").removeAttr("disabled")
    }
    
  };
</script>


<script>
function removeProfilePic(){
	$(".delete_pop").show();
	$(".mask").fadeIn(200);
	$("#removeProfilePicConfirm").attr("onclick", "deleteProfileImage();");
}
  
	$(".delete_pop a").click(function(){
		$(".delete_pop").hide();
		$(".mask").fadeOut(200);
	});

</script>

<script type="text/javascript">
function deleteProfileImage(){
	var operoxUrl ='${operoxUrl}';
	
	 $.ajax({
		    type: "GET",
            url: operoxUrl+"/deleteProfileImage",
	        success: function(data) {
	        	 var result = data;
     		if(result == "success"){
     			location.replace(operoxUrl+"/userSettings"); 
        	 }
	        
	       },
	        
     }); 
}
</script>

<script type="text/javascript">
	function saveUserProfile() {
		var validationSettings = {
			errorMessagePosition : 'element',
			borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd'
		};
		
		if ($('#addUserProfile_form').validate(false, validationSettings)) {
			
		    var frm = $('#addUserProfile_form').serializeFormJSON();
	        var con = JSON.stringify(frm);
	        con = con.replace(/[{}]/g, "");
	        
	        var jsonData = encodeURIComponent(con);
	        var form = $('#addUserProfile_form')[0];
     		var formData = new FormData(form);
     		
	        
	        var operoxUrl ='${operoxUrl}';
     		$("#profile_submit" ).addClass('button').val('Processing..');
     		
	        $.ajax({
		    	 type: "POST",
		    	 data: formData,
       	         contentType : 'application/json; charset=utf-8',
       	         enctype: 'multipart/form-data',
   	             processData: false,
   	             contentType:false,
       	         dataType : 'json',
       	         
		    	 url: operoxUrl+"/saveUserProfile?${_csrf.parameterName}=${_csrf.token}&json="+jsonData, 
		        success: function(result) {
		        	if((result == 'profileHome')){
		        		location.replace(operoxUrl+"/userSettings");  
		        	}
			        
		        },
		    });  
			
			return true;
		} else {
			return false;
		}
	}
	$('body').on('blur', '#addUserProfile_form', function() {
		$("#addUserProfile_form").showHelpOnFocus();
		$("#addUserProfile_form").validateOnBlur(false, validationSettings);
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



<script type="text/javascript">
 (function($) {
    $(function() {
        $('input.timepicker').timepicker();
    });
})(jQuery);
</script>

<script type="text/javascript">
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script> 