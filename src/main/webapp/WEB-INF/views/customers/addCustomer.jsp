<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
      <h2>Add Customer</h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; <a href="${operoxUrl}/customers">Customers</a> &raquo; Add Customer</div>
  </section>
  
  <!-- Content Area inner -->
<form:form id="customer_form" name="customer_form" method="post">
  <div class="content-area clearfix">
  
    <div class="div33">
       <label class="labl">Name</label>
       <span>
       <input type="text" placeholder="Name" value="${customer.customerName}" name="customerName" id="customerName" field-name="customer name" data-validation="required validate_Space" data-validation-optional="true">
       </span>
    </div>
     <input type="hidden" id="customerId" name="customerId" value="${customer.id}">
    <div class="div33">
       <label class="labl">Email</label>
       <span>
       <input type="text" placeholder="Email" value="${customer.email}" name="email" id="email" field-name="email" data-validation="required validate_Space validate_length length2-100 validate_email" data-validation-optional="true">
       </span>
    </div> 
    
    <div class="div33" id="phoneDiv">
       <label class="labl">Phone<b class="text-danger">&nbsp;*</b></label>
       <span>
       <input type="text" placeholder="Phone" value="${customer.phone}" name="phone" id="phone" field-name="Phone number" data-validation="validate_NumberAlphabet validate_phone validate_length length9-20" onblur="validatePhone('${customer.phone}');">
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Date of birth</label>
       <span>
       <input type="text" placeholder="DD/MM/YYYY" class="datefild" id="datebirth" value="${customer.dateStr}" name="datebirth" field-name="date of birth" data-validation-optional="true">
       </span>
    </div>            
    
    <div class="div33">
       <label class="labl">Address</label>
       <span>
       <input type="text" placeholder="Address" name="address" id="address" field-name="address" value="${customer.addressId.address}" data-validation="required validate_Space" data-validation-optional="true">
       </span>
    </div> 
    
    <div class="div33">
       <label class="labl">Country</label>
       <span>
         <select class="chosen-select" name="country" id="country"  field-name="country" data-validation="required validate_Space" data-validation-optional="true">
         </select>
       </span>
        <input type="hidden" id="existingCountry" value="${customer.addressId.country}" >
    </div> 
    
    <div class="div33">
       <label class="labl">State</label>
       <span>
         <select class="chosen-select" name="state" id="state"  field-name="state" data-validation="required validate_Space" data-validation-optional="true">
         </select>
       </span>
        <input type="hidden" id="existingState" value="${customer.addressId.state}" >
    </div>
    
    <div class="div33">
       <label class="labl">City</label>
       <span>
         <select class="chosen-select" name="city" id="city"  field-name="city" data-validation="required validate_Space" data-validation-optional="true">
         </select>
       </span>
       <input type="hidden" id="existingCity" value="${customer.addressId.city}">
    </div>  
    
    <div class="div33">
       <label class="labl">Zipcode</label>
       <span>
       <input type="text" placeholder="Zipcode" name="zipcode" id="zipcode" value="${customer.addressId.zipcode}" field-name="zip code" data-validation="validate_int validate_Space validate_length length5-10" data-validation-optional="true">
       </span>
    </div> 
    <div class="div33">
       <label class="labl">TIN number</label>
       <span>
       <input type="text" placeholder="Tin number" value="${customer.customerTinNumber}" name="tinNumber" id="tinNumber" field-name="Tin Number" data-validation="validate_int validate_Space validate_length length5-20" data-validation-optional="true">
       </span>
    </div>   
    
    <div class="div33">
       <label class="labl">Customer Type<b class="text-danger">&nbsp;*</b></label>
       <span>
         <select class="chosen-select" value="${customer.customerType}" name="customerType" id="customerType" field-name="customer type" data-validation="required">
          <option selected disabled>--Select Customer Type--</option>
          <option value="Retail" ${customer.customerType  =='Retail' ? 'selected' : ''}>Retail</option>
          <option value="Whole Sale" ${customer.customerType  == 'Whole Sale' ? 'selected' : ''}>Whole Sale</option>
          <!-- <option>Retail</option>
          <option>Whole Sale</option> -->
         </select>
       </span>
    </div>                         
    
    <div class="clearfix"></div>
   </div>
       <!-- Content Area inner -->
      <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right">
          <input type="button" value="Add"  id="save_customer" onclick="saveCustomer();" class="btn btn-primary">
          <input type="button" value="Cancel" class="btn btn-default ml10" onClick="cancelCustomer();" >
        </div>
      </div>
    </div>
   </form:form>
</section>
<!-- Content Area Ends--> 

<script type="text/javascript">
function cancelCustomer(){
    var operoxUrl ='${operoxUrl}';
    location.replace(operoxUrl+ "/customers");
}
</script>

<script type="text/javascript">
function saveCustomer() {
	
 	var validationSettings = {
		errorMessagePosition : 'element',
		borderColorOnError : '',
		scrollToTopOnError : true,
		dateFormat : 'yyyy/mm/dd'
	}; 
 	if ($('#customer_form').validate(false, validationSettings)) { 
		var frm = $('#customer_form').serializeFormJSON();
		var con = JSON.stringify(frm);
		$("#save_customer").attr("disabled", "disabled");
 		$("#save_customer" ).addClass('button').val('Processing..');
		con = con.replace(/[{}]/g, "");
		  //It will escape all the special characters
        var jsonData = encodeURIComponent(con);
		var operoxUrl = '${operoxUrl}';
		/* $("#loading").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("resources/images/loading.gif"); background-position:center; background-repeat:no-repeat; background-size:50px;'); */
 		$.ajax({
					type : "POST",
					url : operoxUrl+ "/saveCustomer?${_csrf.parameterName}=${_csrf.token}&json="+jsonData,
					success : function(result) {
                     if(result == 'success'){
                    	 location.replace(operoxUrl+ "/customers");
                     }else{
                    	 location.replace(operoxUrl+ "/addCustomer");
                     }
					},
				}); 
		return true;
	 } else {
		return false;
	} 
}
$('body').on('blur', '#customer_form', function() {
	$("#customer_form").showHelpOnFocus();
	$("#customer_form").validateOnBlur(false, validationSettings);
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
  $( function() {
    $( "#datebirth" ).datepicker();
  });
</script>

<script type="text/javascript">
	function validatePhone(mobileNumber){
		var phone = $("#phone").val();
		var operoxUrl ='${operoxUrl}';
		$('input#phone').removeAttr("class record-exist record-exist-errormsg");
		$('input#phone').attr('class','valid form-control');
		$('#phoneDiv').find('p.jquery_form_error_message').remove();
		$("#save_customer" ).addClass('btn btn-primary').val('Add');
        $("#save_customer").removeAttr("disabled");
		
        if(mobileNumber != phone){
        $('#phoneDiv').find('p.jquery_form_error_message').remove();
		$('#phoneDiv').find('p.jquery_form_error_message').remove();
		if (phone != null && phone != '') {
			$("#save_customer").attr("disabled", "disabled");
			$.ajax({
				type : "GET",
				url : operoxUrl + "/validatePhone?&phone="+ phone,
				success : function(data) {
					var result = data;
					if (result == false) {
						
						 $('#phoneDiv').find('p.jquery_form_error_message').remove();
						 $("input#phone").after("<p class='jquery_form_error_message'>"+phone+" is Already Existed</span>");
						 document.getElementById("phone").setAttribute('record-exist','yes');
						 document.getElementById("phone").setAttribute('record-exist-errorMsg',phone+' is Already Existed'); 
						
					} else {
						//New WebSite class="form-control"
						$('input#phone').removeAttr("class record-exist record-exist-errormsg");
						$('input#phone').attr('class','valid form-control');
						$('#phoneDiv').find('p.jquery_form_error_message').remove();
						$("#save_customer" ).addClass('btn btn-primary').val('Add');
                        $("#save_customer").removeAttr("disabled");
					}
				},
			});
			$("#customer_form").click(function() {
				if ($(this).validate(false, validationSettings)) {
				}
			});
		}
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
