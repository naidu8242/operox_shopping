<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
window.onload = function () {
	getAllCountries();
 };
</script>
<style type="text/css">
.compen .div50{
min-height:49px;
}
</style>

<script src="${operoxUrl}/resources/js/formvalidator.js"></script>
<script type="text/javascript">
	function validateEmployeeId() {
		var EmployeeId = document.getElementById("employeeId").value;
		 //space value  consider this condition 
		if(EmployeeId.match(/^[^\s-](.*)*$/) !== null){
		var operoxUrl = '${operoxUrl}';
		$('#employeeIdDiv').find('p.jquery_form_error_message').remove();
		if (EmployeeId != null && EmployeeId != "") {
			$("#addUser_id").prop("disabled", false);
	     	//$( "#addUser_id" ).addClass('button').val('Processing..');
			$.ajax({
						type : "POST",
						url : operoxUrl+ "/validateEmployeeId?${_csrf.parameterName}=${_csrf.token}&EmployeeId="+ EmployeeId,
						success : function(data) {
							 if (data == true) {
								 $("#addUser_id").prop("disabled", true);
								$("input#employeeId").after("<p class='jquery_form_error_message'>"+ EmployeeId+ " is Already Existed</span>");
								document.getElementById("employeeId").setAttribute('record-exist', 'yes');
								document.getElementById("employeeId").setAttribute('record-exist-errorMsg',EmployeeId+ ' is Already Existed');
								$('input#employeeId').removeAttr( "record-exist record-exist-errorMsg");
							} else {
								$('input#employeeId').removeAttr("record-exist record-exist-errormsg");
								$('#employeeIdDiv').find('p.jquery_form_error_message').remove();
							} 
						},
					});
	     	}
		} 
	}
</script>

<script type="text/javascript">
function validEmail() {
	var EmailId = document.getElementById("email").value;
	 $('#EmailIdDiv').find('p.jquery_form_error_message').remove();
	 var operoxUrl = '${operoxUrl}';
	 if(EmailId != null && EmailId != ""){
		 $("#addUser_id").prop("disabled", false);
		 $.ajax({
		    	type: "POST",
		        url: operoxUrl+"/getEmailVerification?${_csrf.parameterName}=${_csrf.token}&EmailId="+EmailId, 
		        success: function(data) {
		        	if(data == "true"){
		        		 $("#addUser_id").prop("disabled", true);
		        		$("input#email").after("<p class='jquery_form_error_message'>"+ EmailId+ " is Already Existed</span>");
						document.getElementById("email").setAttribute('record-exist', 'yes');
						document.getElementById("email").setAttribute('record-exist-errorMsg',EmailId+ ' is Already Existed');
						$('input#email').removeAttr( "record-exist record-exist-errorMsg");
		        	}
		        	else{
		        		$('input#emial').removeAttr("record-exist record-exist-errormsg");
						$('#EmailIdDiv').find('p.jquery_form_error_message').remove();
		        	}
		        },
		    });
	 } 
}
</script>


<div class="wraper clearfix">

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Add User</h2>
    </div>
    <div class="pull-right brud-crum"><a href="javascript:location.href = '<c:url value='/users'/>'">Users List</a> &raquo; Add User</div>
  </section>
  
  <!-- Content Area inner -->
  <form:form id="addUser_form"  name="addUser_form" method="post">
  <div id="loading"></div> 
  <input type="hidden" id="userId" name="userId" value="${user.id}" >
  <div class="content-area clearfix">
    <div class="div33" id="employeeIdDiv">
       <label class="labl">Employee Id</label>
       <span>
       <input type="text" placeholder="Employee Id" id="employeeId" name="employeeId" value="${user.employeeId}" onblur="validateEmployeeId();" data-validation="validate_Space validate_length length2-20" data-validation-optional="true">
       </span>
    </div>
    <div class="div33">
       <label class="labl">First Name<b class="text-danger">&nbsp;*</b></label>
       <span>
       <input type="text" id="FirstName" name="firstName"  placeholder="First Name" value="${user.firstName}" field-name="First Name" data-validation="required validate_Space validate_Alphanumeric validate_length length2-100">
       </span>
    </div>
    <div class="div33">
       <label class="labl">Last Name</label>
       <span>
       <input type="text" placeholder="Last Name" name="lastName" value="${user.lastName}"  data-validation="validate_Space validate_Alphanumeric validate_length length2-100" data-validation-optional="true">
       </span>
    </div>
    
    
     <div class="div33" id="EmailIdDiv">
	       <label class="labl">Email<b class="text-danger">&nbsp;*</b></label>
	       <span>
	       <c:if test="${not empty user.id}">
	       <input type="text" placeholder="Email" field-name="Email" id ="email" name="emial" value="${user.email}"  data-validation="required validate_Space validate_length length2-100 validate_email" readonly="readonly">
	       </c:if>
	        <c:if test="${empty user.id}">
	         <input type="text" placeholder="Email" field-name="Email" id ="email" name="emial" value="${user.email}" onblur="validEmail();"  data-validation="required validate_Space validate_length length2-100 validate_email">
	        </c:if>
	      
	       </span>
	    </div>
	   
	   <div class="div33">
       <label class="labl">Gender<b class="text-danger">&nbsp;*</b></label>
       <span>
         <select class="chosen-select" id="gender" name="gender" data-validation="required" field-name="Gender" >
         <option value="">--Select Gender--</option>
         <option value="male" ${'male'  == user.gender ? 'selected' : ''}>Male</option>
         <option value="female" ${'female'  == user.gender ? 'selected' : ''}>Female</option>
         </select>
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Phone</label>
       <span>
       <input type="text" placeholder="Phone" id="phone" name="phoneNumber" value="${user.phone}" data-validation="validate_Space validate_phone validate_length length9-20" data-validation-optional="true">
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Department</label>
       <span>
         <select class="chosen-select" id="department" name="department" field-name="Department" >
         <option value="">--Select Department--</option>
         <c:forEach var="department" items="${departmentList}">
               <option value="${department.id}" ${department.id  == user.department.id ? 'selected' : ''}>${department.departmentName}</option>
         </c:forEach>
         </select>
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Address</label>
       <span>
       <input type="text" placeholder="Address" name="address" name="address" value="${user.address.address}">
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Country</label>
       <span>
          <select class="chosen-select" name="country" id="country" field-name="Country">
         </select>
       </span>
       <input type="hidden" id="existingCountry" value="${user.address.country}" >
    </div>
    <div class="div33">
       <label class="labl">State</label>
       <span>
        <select class="chosen-select" name="state" id="state" field-name="State">
         </select>
       </span>
       <input type="hidden" id="existingState" value="${user.address.state}" >
    </div>
    
    <div class="div33">
       <label class="labl">City</label>
       <span>
        <select class="chosen-select" name="city" id="city" field-name="City">
         </select>
       </span>
       <input type="hidden" id="existingCity" value="${user.address.city}" >
    </div>
    
    <div class="div33">
       <label class="labl">Zip Code</label>
       <span>
       <input type="text" placeholder="Zip Code" id="zipCode" name="zipCode"  value="${user.address.zipcode}" data-validation="validate_Space validate_int validate_length length5-10" data-validation-optional="true">
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Role<b class="text-danger">&nbsp;*</b></label>
       <span>
       <c:if test="${not empty user.id}">
       <select class="chosen-select" id="roleType" name="roleType" field-name="Role" data-validation="required" disabled="disabled">
          <option selected disabled>--Select Role--</option>
          <c:forEach var="role" items="${roleList}">
               <option value="${role.id},${role.displayName}" ${role.id  == user.role.id ? 'selected' : ''}>${role.displayName}</option>
         </c:forEach>
         </select>
       </c:if>
       <c:if test="${empty user.id}">
         <select class="chosen-select" id="roleType" name="roleType" field-name="Role" data-validation="required">
          <option selected disabled>--Select Role--</option>
          <c:forEach var="role" items="${roleList}">
               <option value="${role.id},${role.displayName}" ${role.id  == user.role.id ? 'selected' : ''}>${role.displayName}</option>
         </c:forEach>
         </select>
         </c:if>
       </span>
    </div>

	<div class="div33">
       <label class="labl">Date of Join</label>
       <span>
         <input type="text" id="datejoin" class="datefild" placeholder="DD/MM/YYYY" name="dateofJoin" value="<fmt:formatDate pattern="yyyy/MM/dd" value="${user.doj}"/>">
         
       </span>
    </div>
	    
    <div class="div33 compen">
       <label class="labl">Compensation<b class="text-danger">&nbsp;*</b></label>
       <span>
       <div class="div50">
         <input type="text" placeholder="Compensation" name="compensation" value="${user.compensation}" field-name="Field" data-validation="required validate_Space validate_Alphanumeric validate_length length2-100" >
       </div>
       <div class="div50">
       <c:if test="${!empty user.id}">
       <select name="compensatationType" id="compensatationType" value="${user.compensatationType}" data-validation="required"  field-name="Type">
	    
	    <c:if test="${user.compensatationType  == 'weekly'}">
	    <option value="${user.compensatationType}" selected="" >${user.compensatationType}</option>
	    <option value="bi-weekly">bi-Weekly</option>
	     <option value="monthly">monthly</option>
	    </c:if>
	     <c:if test="${user.compensatationType  == 'byWeekly'}">
	    <option value="${user.compensatationType}" selected="" >${user.compensatationType}</option>
	     <option value="weekly">weekly</option>
	     <option value="monthly">monthly</option>
	    </c:if>
	     <c:if test="${user.compensatationType  == 'monthly'}">
	    <option value="${user.compensatationType}" selected="" >${user.compensatationType}</option>
	     <option value="weekly">weekly</option>
	     <option value="bi-weekly">bi-Weekly</option>
	    </c:if>
	     </select>  
       </c:if>

       <c:if test="${empty user.id}">
       <select name="compensatationType" id="compensatationType" data-validation="required"  field-name="Type">
	    <option value="" selected="" disabled>Select Type</option>
	     <option value="weekly">weekly</option>
	     <option value="bi-weekly">bi-Weekly</option>
	     <option value="monthly">monthly</option>
	    </select>  
       </c:if>
	           
       </div>
       </span>
    </div>
	    
    <div class="div33">
       <label class="labl">Shift<b class="text-danger">&nbsp;*</b></label>
       <span>
         <select  class="chosen-select" id="shiftType" name="shiftType" field-name="Shift" data-validation="required">
         <option value="">--Select Shift--</option>
          <c:forEach var="shift" items="${shiftList}">
               <option value="${shift.id}" ${shift.id  == user.shift.id ? 'selected' : ''}>${shift.shiftName}</option>
         </c:forEach>
         </select>
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Location<b class="text-danger">&nbsp;*</b></label>
       <span>
         <select  class="chosen-select" id="storestoreType" name="storeType" field-name="Store" data-validation="required">
         <option value="">--Select Location--</option>
         <c:forEach var="store" items="${storeList}">
               <option value="${store.id}" ${store.id  == user.store.id ? 'selected' : ''}>${store.storeName}</option>
         </c:forEach>
         </select>
       </span>
    </div>
    
     <div class="div33">
       <label class="labl">&nbsp;</label>
       <span>
       <span class="checkBox">
	       <c:if test="${empty user.id}">
	          <input type="checkbox" value="Y" name="isMailSent" checked="checked" ><label>Send Email To User</label>
	       </c:if>
       </span>
       </span>
    </div>
    <div class="clearfix"></div>
    
      <!-- Content Area inner -->
  </div>
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right">
          <input type="button"  id="addUser_id" value="Add" onclick="addUser();"  class="btn btn-success">
          <input type="button" value="Cancel" onClick="javascript:location.href = '<c:url value='/users'/>'" class="btn btn-default ml10">
        </div>
      </div>
    </div>
    </form:form>
</section>
<!-- Content Area Ends--> 

</div>

<jsp:include page="../masterFooter.jsp" />

<script type="text/javascript">
			var config = {
			  '.chosen-select'           : {},
			  '.chosen-select-deselect'  : {allow_single_deselect:true},
			  '.chosen-select-no-single' : {disable_search_threshold:10},
			  '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
			  '.chosen-select-width'     : {width:"95%"}
			}
			for (var selector in config) {
			  $(selector).chosen(config[selector]);
			}
			$('.chosen-select').each(function() {
				var $this = $(this);
				$this.next().css({'width': '100%'});

});
</script> 
<script type="text/javascript">
	function addUser() {
		var validationSettings = {
	            errorMessagePosition : 'element',
	            borderColorOnError : '',
				scrollToTopOnError : true,
				dateFormat : 'yyyy/mm/dd' 
	            };
		if ($('#addUser_form').validate(false,validationSettings)) {
			 $("#addUser_id").attr("disabled", "disabled");
			var frm = $('#addUser_form').serializeFormJSON();
			var con = JSON.stringify(frm);
			con = con.replace(/[{}]/g, "");
			var jsonData = encodeURIComponent(con);
			var operoxUrl = '${operoxUrl}';
			$("#addUser_id").addClass('button').val('Processing..');
			$("#loading").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("resources/images/loading.gif"); background-position:center; background-repeat:no-repeat; background-size:50px;');
				$.ajax({
						type : "POST",
						url : operoxUrl + "/addSaveUser?${_csrf.parameterName}=${_csrf.token}&json="+ jsonData,
						success : function(data) {
				        	if((data == "userHome")){
				        		location.replace(operoxUrl+"/users");  
				        	}
					        
				        },
					});
			return true;
		} else {
			return false;
		}
	}
	$('body').on('blur', '#addUser_form', function() {
		$("#addUser_form").showHelpOnFocus();
		$("#addUser_form").validateOnBlur(false, validationSettings);
	});
</script>
<script type="text/javascript">
  $( function() {
    $( "#datejoin" ).datepicker();
  });
  
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
<script type="text/javascript">
	(function($) {
		$.fn.serializeFormJSON = function() {
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
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
function getAllCountries(){
	var countryName = $("#existingCountry").val();
	$.ajax({
    	type: "POST",
    	dataType: "json",
    	 url: '${operoxUrl}'+"/countryAutoComplete?${_csrf.parameterName}=${_csrf.token}",
        success: function(data) {
        	var json = JSON.parse(data);
        	$('#country').empty();
        	$('#country').append('<option value="">---select---</option>');
            for(var i=0;i<json.length;i++){
            	if(json[i] == countryName){
            		$('#country').append('<option value="'+json[i]+'" selected>'+json[i]+'</option>');
            	}
            	else if(json[i] == '${loginCountryName}'){
            		$('#country').append('<option value="'+json[i]+'" selected>'+json[i]+'</option>');
            	}
            	else{
            		$('#country').append('<option value="'+json[i]+'">'+json[i]+'</option>');
            	}
            	
            }
            $('#country').trigger("chosen:updated");
            getStates();
        },
        error: function(e){
        }
    });
} 
</script>


<script>
function getStates(){
    var countryName = $("#country").val();
	
	//to set existing state value(Auto populate)
	var stateName = $("#state").val();
	if(stateName == null || stateName == 'undefined' || stateName == ''){
		stateName = document.getElementById("existingState").value;
	}
	//end state Auto populate
	
	if(countryName != null && countryName != "" && countryName !="undefined"){
		$.ajax({
	    	type: "POST",
	    	dataType: "json",
	    	 url: '${operoxUrl}'+"/getStatesByCountry?${_csrf.parameterName}=${_csrf.token}&countryName="+countryName, 
	        success: function(data) {
	        	var json = JSON.parse(data);
	        	$('#state').empty();
	        	$('#state').append('<option value="">---select---</option>');
	            for(var i=0;i<json.length;i++){
	            	if(json[i] == stateName){
	            		$('#state').append('<option value="'+json[i]+'" selected>'+json[i]+'</option>');
	            	}
	            	else{ 
	            		$('#state').append('<option value="'+json[i]+'">'+json[i]+'</option>');
	            	} 
	            }
	            $('#state').trigger("chosen:updated");
	            getCities();
	        },
	        error: function(e){
	        }
	    });
	}else{
		$('#state').empty();
    	$('#state').append('<option value="">---select---</option>');
    	$('#state').trigger("chosen:updated");
    	$('#city').empty();
    	$('#city').append('<option value="">---select---</option>');
        $('#city').trigger("chosen:updated");
	}
}
</script> 


<script>
function getCities(){
	var countryName = $("#country").val();
	var stateName = $("#state").val();
	
	//to set existing city value(Auto populate)
	var cityName = $("#city").val();
	if(cityName == null || cityName == 'undefined' || cityName == ''){
		cityName = document.getElementById("existingCity").value;
	}
	//end city Auto populate
	
	if(countryName != null && countryName != "" && countryName !="undefined" && stateName != null && stateName != "" && stateName !="undefined" ){
		$.ajax({
	    	type: "POST",
	    	dataType: "json",
	    	 url: '${operoxUrl}'+"/getCitiesByStateAndCountry?${_csrf.parameterName}=${_csrf.token}&countryName="+countryName+"&stateName="+stateName, 
	        success: function(data) {
	        	var json = JSON.parse(data);
	        	$('#city').empty();
	        	$('#city').append('<option value="">---select---</option>');
	            for(var i=0;i<json.length;i++){
	            	if(json[i] == cityName){
	            		$('#city').append('<option value="'+json[i]+'" selected>'+json[i]+'</option>');
	            	}
	            	else{ 
	            		$('#city').append('<option value="'+json[i]+'">'+json[i]+'</option>');
	            	} 
	            }
	            $('#city').trigger("chosen:updated");
	        },
	        error: function(e){
	        }
	    });
	}else{
		$('#city').empty();
    	$('#city').append('<option value="">---select---</option>');
    	$('#city').trigger("chosen:updated");
	}
}
</script>