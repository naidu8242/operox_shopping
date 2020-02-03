<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>
<link rel="stylesheet" href="${operoxUrl}/resources/css/chosen.min.css">
<script src="${operoxUrl}/resources/js/chosen.jquery.min.js"></script> 

<script type="text/javascript">
window.onload = function () {
	getAllCountries();
 };
</script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2><c:if test="${empty store.id}">Add Location</c:if> <c:if test="${!empty store.id}">Edit Location</c:if></h2>
    </div>
    <div class="pull-right brud-crum"><a href="dashboard">Home</a> &raquo; <a href=store>Location List</a>&raquo; <c:if test="${empty store.id}">Add Location</c:if> <c:if test="${!empty store.id}">Edit Location</c:if></div>
  </section>
  
  <!-- Content Area inner -->
  <form:form id="addStore_from"  name="addStore_from" method="post">
  <div class="content-area clearfix">
    <div class="div33" id="storeNameDiv">
       <label class="labl">Name&nbsp;<b class="text-danger"> *</b></label>
       <span>
       <input type="text" placeholder="Name" value="${store.storeName}" id="storeName" name="storeName" field-name="Name" onblur="validateStoreName();" onmouseout="validateStoreName()";
       data-validation="required validate_Space validate_AlphaNumber validate_length length2-100">
       </span>
    </div>
    <input type="hidden" id="storeId" name="storeId" value="${store.id}">
    <div class="div33">
       <label class="labl">Type&nbsp; <b class="text-danger"> *</b></label>
       <span>
         <select id="storeType" name="storeType" field-name="Type" data-validation="required">
	          <option selected disabled>--Select Option--</option>
	          <option value="Warehouse" ${store.storeType  =='Warehouse' ? 'selected' : ''}>Warehouse</option>
	          <option value="Store" ${store.storeType  == 'Store' ? 'selected' : ''}>Store</option>
	          <option value="Head office" ${store.storeType  == 'Head office' ? 'selected' : ''}>Head office</option>
	          <option value="Production Unit" ${store.storeType  == 'Production Unit' ? 'selected' : ''}>Production Unit</option>
         </select>
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Address</label>
       <span>
       <input type="text" id="address" name="address" value="${store.address.address}" placeholder="Address" data-validation-optional="true" data-validation="validate_Space length2-250" field-name="Address">
       </span>
    </div> 
    
    <div class="div33">
       <label class="labl">Country&nbsp;<b class="text-danger"> *</b></label>
       <span>
          <select class="chosen-select" id="country" name="country" data-validation="required" field-name="Country">
         </select>
       </span>
       <input type="hidden" id="existingCountry" value="${store.address.country}" >
    </div> 
    
    <div class="div33">
       <label class="labl">State&nbsp;<b class="text-danger"> *</b></label>
       <span>
          <select class="chosen-select" id="state" name="state" data-validation="required" field-name="State">
         </select>
       </span>
       <input type="hidden" id="existingState" value="${store.address.state}" >
    </div>
    
    <div class="div33">
       <label class="labl">City&nbsp; <b class="text-danger"> *</b></label>
       <span>
          <select class="chosen-select" id="city" name="city" data-validation="required" field-name="City">
         </select>
       </span>
       <input type="hidden" id="existingCity" value="${store.address.city}">
    </div>  
    
    <div class="div33">
       <label class="labl">Zipcode</label>
       <span>
       <input type="text" id="zipCode" name="zipCode" value="${store.address.zipcode}" data-validation-optional="true" data-validation="validate_Space validate_int validate_length length5-10" placeholder="Zipcode" field-name="Zipcode">
       </span>
    </div> 

    <div class="div33" id="storeEmailDiv">
       <label class="labl">Email&nbsp;<b class="text-danger"> *</b></label>
       <span>
       <input type="text" id="email" name="email" value="${store.email}" data-validation="required validate_Space validate_length length2-100 validate_email" onblur="validateStoreEmail();" onmouseout="validateStoreEmail();"  placeholder="Email" field-name="Email">
       </span>
    </div> 
        
    <div class="div33">
       <label class="labl">Phone</label>
       <span>
       <input type="text" id="phone" name="phone" value="${store.phoneNumber}" data-validation-optional="true" data-validation="validate_Space validate_phone validate_length length9-20" placeholder="Phone" field-name="Phone">
       </span>
    </div>                      
    
    <div class="clearfix"></div>
      <!-- Content Area inner -->
  </div>
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right">
	         <c:if test="${empty store.id}">
	             <input type="button" value="Add" class="btn btn-primary" id="add_store" onclick="addStore();">
	         </c:if>
	          <c:if test="${!empty store.id}">
	             <input type="button" value="Update" class="btn btn-primary" id="add_store" onclick="addStore();">
	         </c:if>
          <input type="button" value="Cancel" onClick="javascript:location.href = '<c:url value='/store'/>'" class="btn btn-default ml10">
        </div>
      </div>
    </div>
    </form:form>
</section> 
<!-- Content Area Ends--> 

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
 function addStore() {
	   if ($('#addStore_from').validate(false, validationSettings)){
		    var frm = $('#addStore_from').serializeFormJSON();
	        var con = JSON.stringify(frm);
	        con = con.replace(/[{}]/g, "");
	        //It will escape all the special characters
	        var jsonData = encodeURIComponent(con);
	        
	        var operoxUrl ='${operoxUrl}';
	        $("#add_store").attr("disabled", "disabled");
     		$( "#add_store" ).addClass('button').val('Processing..');
     		//$("#loading").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("resources/images/loading.gif"); background-position:center; background-repeat:no-repeat; background-size:50px;');
	        $.ajax({
		    	type: "POST",
		    	 url: operoxUrl+"/addStore?${_csrf.parameterName}=${_csrf.token}&json="+jsonData, 
		        success: function(result) {
		        	if((result == 'storeHome')){
		        		location.replace(operoxUrl+"/store");  
		        	}
			        
		        },
		    }); 
	        return true;
		
	  }else{
		return false;
	  }
    }
 
   $('body').on('blur', '#addStore_from', function() {
		$("#addStore_from").showHelpOnFocus();
		$("#addStore_from").validateOnBlur(false, validationSettings);  
   });
  
 
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
 function validateStoreName(){
	 var storeName = document.getElementById("storeName").value;
	 var storeId = document.getElementById("storeId").value;
	 $('#storeNameDiv').find('p.jquery_form_error_message').remove();
		var operoxUrl ='${operoxUrl}';
		if(storeName != null && storeName != ""){
			 $("#add_store").attr("disabled", "disabled");
			$.ajax({
			   	type: "POST",
			    url: operoxUrl+"/validateStoreName?${_csrf.parameterName}=${_csrf.token}&storeName="+storeName+"&storeId="+storeId, 	   
				    success: function(data) {
				    	if(data == true){
			        		  $('#storeNameDiv').find('p.jquery_form_error_message').remove();
							  $("input#storeName").after("<p class='jquery_form_error_message'>"+storeName+" is Already Existed</span>");
							  document.getElementById("storeName").setAttribute('record-exist','yes');
							  document.getElementById("storeName").setAttribute('record-exist-errorMsg',storeName+' is Already Existed'); 
			        	}
			        	else{
			        		 $('input#storeName').removeAttr( "record-exist record-exist-errormsg");
			        		 $('#storeNameDiv').find('p.jquery_form_error_message').remove(); 
			        		 if(storeId != null && storeId != "" && storeId != "undefined"){
			     				$("#add_store" ).addClass('btn btn-primary').val('Update');
			     			}else{
			     				$("#add_store" ).addClass('btn btn-primary').val('Add');
			     			}
	                         $("#add_store").removeAttr("disabled");
			        	} 
				    }
		     });
		}else{
			$('input#storeName').removeAttr( "record-exist record-exist-errormsg");
			$('#storeNameDiv').find('p.jquery_form_error_message').remove(); 
			if(storeId != null && storeId != "" && storeId != "undefined"){
				$("#add_store" ).addClass('btn btn-primary').val('Update');
			}else{
				$("#add_store" ).addClass('btn btn-primary').val('Add');
			}
	        $("#add_store").removeAttr("disabled");
		}
 }
</script> 

<script type="text/javascript">
 function validateStoreEmail(){
	 var email = document.getElementById("email").value;
	 var storeId = document.getElementById("storeId").value;
	 $('#storeEmailDiv').find('p.jquery_form_error_message').remove();
		var operoxUrl ='${operoxUrl}';
		if(storeName != null && storeName != ""){
			 $("#add_store").attr("disabled", "disabled");
			$.ajax({
			   	type: "POST",
			    url: operoxUrl+"/validateStoreEmail?${_csrf.parameterName}=${_csrf.token}&email="+email+"&storeId="+storeId, 	   
				    success: function(data) {
				    	if(data == true){
			        		  $('#storeEmailDiv').find('p.jquery_form_error_message').remove();
							  $("input#email").after("<p class='jquery_form_error_message'>"+email+" is Already Existed</span>");
							  document.getElementById("email").setAttribute('record-exist','yes');
							  document.getElementById("email").setAttribute('record-exist-errorMsg',email+' is Already Existed'); 
			        	}
			        	else{
			        		 $('input#email').removeAttr( "record-exist record-exist-errormsg");
			        		 $('#storeEmailDiv').find('p.jquery_form_error_message').remove(); 
			        		 if(storeId != null && storeId != "" && storeId != "undefined"){
			     				$("#add_store" ).addClass('btn btn-primary').val('Update');
			     			}else{
			     				$("#add_store" ).addClass('btn btn-primary').val('Add');
			     			}
	                         $("#add_store").removeAttr("disabled");
			        	} 
				    }
		     });
		}else{
			$('input#email').removeAttr( "record-exist record-exist-errormsg");
			$('#storeEmailDiv').find('p.jquery_form_error_message').remove(); 
			if(storeId != null && storeId != "" && storeId != "undefined"){
				$("#add_store" ).addClass('btn btn-primary').val('Update');
			}else{
				$("#add_store" ).addClass('btn btn-primary').val('Add');
			}
	        $("#add_store").removeAttr("disabled");
		}
 }
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
<script type="text/javascript">
$(function(){
	       var config = {
			  '.chosen-select'           : {},
			  '.chosen-select-deselect'  : {allow_single_deselect:true},
			  '.chosen-select-no-single' : {disable_search_threshold:10},
			  '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
			  '.chosen-select-width'     : {width:"95%"}
			}
	       $("#country").chosen();
	       $("#state").chosen();
	       $("#city").chosen();
	       
	       $("#country").on('change', function (e) {
	    	   getStates();
	       });
	       $("#state").on('change', function (e) {
	    	   getCities();
	       });
	      
	       $('.chosen-select').each(function() {
				var $this = $(this);
				$this.next().css({'width': '100%'});
		  })
});
</script>

<jsp:include page="../masterFooter.jsp" />
