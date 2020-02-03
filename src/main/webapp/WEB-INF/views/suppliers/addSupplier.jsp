<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  


<link rel="stylesheet" href="${operoxUrl}/resources/css/chosen.min.css">
<script src="${operoxUrl}/resources/js/chosen.jquery.min.js"></script> 
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<script type="text/javascript">
window.onload = function () {
	getAllCountries();
 };
</script> 

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Add Supplier</h2>
    </div>
    <div class="pull-right brud-crum"><a href="<c:url value='/suppliers'/>">Suppliers List </a>&raquo; Add Supplier</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix">
  <form:form id="supplier_form" name="supplier_form" method="post">
 
    <div class="div33" id="supplierNameDiv">
       <label class="labl">Supplier Name&nbsp; *</label>
       <span>
       <input type="text" placeholder="Supplier Name" id="name" name="name" value="${supplier.name}" field-name="Supplier Name" data-validation="required validate_Space validate_Alphanumeric validate_length length2-100" onblur="validateSupplierName();" onmouseout="validateSupplierName();"
    	   <c:if test="${not empty supplier.name}">
		       disabled="disabled"
		   </c:if>
		>
       </span>
    </div>
     <input type="hidden" id="supplierId" name="supplierId" value="${supplier.id}">
    
    <div class="div33">
       <label class="labl">Contact Name</label>
       <span>
       <input type="text" placeholder="Contact Name" value="${supplier.supplierContactName}"  name="supplierContactName" data-validation="validate_Space validate_length length2-20" data-validation-optional="true">
       </span>
    </div> 

    <div class="div33">
       <label class="labl">Email&nbsp; *</label>
       <span>
       <input type="text" placeholder="Email" name="supplierContactEmail" value="${supplier.supplierContactEmail}"  field-name="Supplier Contact Email" data-validation="required validate_Space validate_email validate_length length2-100" >
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Phone</label>
       <span>
       <input type="text" placeholder="Phone" name="supplierContactPhone" value="${supplier.supplierContactPhone}" field-name="Supplier Contact Phone" data-validation="validate_Space validate_phone validate_length length2-20" data-validation-optional="true"> 
       </span>
    </div>          
    
    <div class="div33">
       <label class="labl">Address</label>
       <span>
       <input type="text" placeholder="Address" id="address" name="address" value="${supplier.address.address}" field-name="Address" data-validation="validate_Space validate_Alphanumeric validate_length length2-500" data-validation-optional="true">
       </span>
    </div> 
    
   <div class="div33">
       <label class="labl">Country</label>
       <span>
          <select class="chosen-select" id="country" name="country" >
         </select>
       </span>
       <input type="hidden" id="existingCountry" value="${supplier.address.country}" >
    </div> 
    
    <div class="div33">
       <label class="labl">State</label>
       <span>
          <select class="chosen-select" id="state" name="state" >
         </select>
       </span>
       <input type="hidden" id="existingState" value="${supplier.address.state}" >
    </div>
    
    <div class="div33">
       <label class="labl">City</label>
       <span>
          <select class="chosen-select" id="city" name="city" >
         </select>
       </span>
       <input type="hidden" id="existingCity" value="${supplier.address.city}">
    </div>  
    
    <div class="div33">
       <label class="labl">Zipcode</label>
       <span>
       <input type="text" id="zipCode" name="zipCode" value="${supplier.address.zipcode}" placeholder="Zip Code" field-name="Zip Code" data-validation="validate_Space validate_zipcode validate_length length2-8" data-validation-optional="true">
       </span>
    </div> 
    
    <div class="div33">
       <label class="labl">TIN</label>
       <span>
       <input type="text" placeholder="TIN" name="tin" value="${supplier.tin}" id="tin" field-name="TIN" data-validation="validate_Space validate_Alphanumeric validate_length length2-50" data-validation-optional="true">
       </span>
    </div>                          
    
    <div class="clearfix"></div>
      <!-- Content Area inner -->
      </form:form>
     
  </div>
 
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right">
          <input type="button" value="Save" class="btn btn-primary" id="save_supplier" onclick="storeSupplier();">
          <input type="button" value="Cancel" onClick="javascript:location.href = '<c:url value='/suppliers'/>'" class="btn btn-default ml10">
        </div>
      </div>
    </div>
   
</section>
<!-- Content Area Ends--> 

<!-- Supplier Store/add Script Starts here -->
<script type="text/javascript">
    function storeSupplier() {
    	  var validationSettings = {
    	            errorMessagePosition : 'element',
    	            borderColorOnError : '',
    				scrollToTopOnError : true,
    				dateFormat : 'yyyy/mm/dd' 
    	            };
    	 if ($('#supplier_form').validate(false, validationSettings)){
            var frm = $('#supplier_form').serializeFormJSON();
            var con = JSON.stringify(frm);
            con = con.replace(/[{}]/g, "");
              //It will escape all the special characters
            var operoxUrl = '${operoxUrl}';
            $.ajax({
                        type : "POST",
                        url : operoxUrl+"/storeSupplier?${_csrf.parameterName}=${_csrf.token}&json="+con,
                        		
                        success : function(result) {
                                location.replace(operoxUrl+"/suppliers");
                        },
                    });
            return true;
    	  }else{
  		return false;
  	  }
      }
     $('body').on('blur', '#supplier_form', function() {
  		$("#supplier_form").showHelpOnFocus();
  		$("#supplier_form").validateOnBlur(false, validationSettings);  
     });
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
<!-- Supplier Store/add Script Ends here -->

<!-- Country,State,City autocomplete scripts Starts here -->
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


<!-- Country,State,City autocomplete scripts Ends here -->
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

<!-- Supplier Duplicate Check script Starts Here -->

 <script type="text/javascript">
function validateSupplierName(){
	
	var supplierName = document.getElementById("name").value;
	var operoxUrl = '${operoxUrl}';
	if(supplierName.match(/^[^\s-](.*)*$/) != null){
		

		$('#supplierNameDiv').find('p.jquery_form_error_message').remove();
	if (supplierName != null && supplierName != '') {
		$.ajax({
			type : "GET",
			url : operoxUrl+"/isSupplierNameValidated?${_csrf.parameterName}=${_csrf.token}&supplierName="+supplierName,
			success : function(data) {
				var result = data;
				$("#save_supplier").prop('disabled', false);
				if (result == true) {
					//duplicate supplier
					$("#save_supplier").prop('disabled', true);
					$("input#name").after( "<p class='jquery_form_error_message'>" + supplierName + " is already existed as supplier</span>");
					document.getElementById("supplierName").setAttribute( 'record-exist', 'yes');
					document.getElementById("supplierName").setAttribute( 'record-exist-errorMsg', supplierName +"  already existed as supplier");
				}else {
					$('input#name').removeAttr("class record-exist record-exist-errormsg");
					$('input#name').attr('class','valid form-control');
					$('#supplierNameDiv').find('p.jquery_form_error_message').remove();
					$("#save_supplier").click(function() {
						if ($(this).validate(false, validationSettings)) {
						}
					});
				}
			},

		});
	}
  }
}
</script>  

<!-- Supplier Duplicate Check script Ends Here -->
 
<jsp:include page="../masterFooter.jsp" />
