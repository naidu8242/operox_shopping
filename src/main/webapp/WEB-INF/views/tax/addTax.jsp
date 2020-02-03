<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>
<link rel="stylesheet" href="${operoxUrl}/resources/css/chosen.min.css">
<script src="${operoxUrl}/resources/js/chosen.jquery.min.js"></script> 



<div class="wraper clearfix">

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Add Tax</h2>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Add Tax</div>
  </section>
  
  <!-- Content Area inner -->
   <form:form id="addTax_from"  name="addTax_from" method="post">
	  <div class="content-area clearfix">
	  <div class="div33" id="taxNameDiv">
	       <label class="labl">Tax Name<b class="text-danger">&nbsp;*</b></label>
	       <span>
	       <input type="text" placeholder="Tax Name" name="taxName" id="taxName" value="${tax.taxName}"  onkeyup="validateTaxName();"  field-name="Tax Name"   data-validation="required validate_Space validate_Alphanumeric validate_length length2-100">
	       </span>
	       <input type="hidden"  name="taxid" id="taxid" value="${tax.id}">
	    </div>
	    <div class="div33">
	       <label class="labl">Tax Value (Flat)<b class="text-danger">&nbsp;*</b></label>
	       <span>
	       	       <input type="text" placeholder="Tax Value" name="taxValue" id="taxValue" value="${tax.taxValue}"  field-name="Tax Value"   data-validation="required validate_Float" >
	       </span>
	    </div> 
	    
	    <div class="div33">
	       <label class="labl">Category</label>
	       <span>
	        <select id="category" name="category" field-name="Category Type"  class="chosen-select"  data-validation="required" >
	          <option value="">-- Select Category --</option>
				<c:forEach var="category" items="${categoryList}">
					<option value="${category.id}" ${category.id  == tax.category.id ? 'selected' : ''}>${category.categoryName}</option>
				</c:forEach>
         </select>

	         </select>
	       </span>
	    </div>
	       
	    <div class="clearfix"></div>
	 
	      <!-- Content Area inner -->
	  </div>
	  <div class="form-footer">
	      <div class="col-lg-12">
	        <div class="pull-right">
	          <input type="button" value="Add" id="add_Tax" onclick="addTax();" class="btn btn-primary">
	          <input type="button" value="Cancel" onClick="javascript:location.href = '${operoxUrl}/tax'" class="btn btn-default ml10">
	        </div>
	      </div>
	    </div>
   </form:form>   
</section>
<!-- Content Area Ends--> 

<script type="text/javascript">
 function addTax() {
	 
	 var validationSettings = {
	            errorMessagePosition : 'element',
	            borderColorOnError : '',
				scrollToTopOnError : true,
				dateFormat : 'yyyy/mm/dd' 
	            };

	 
	   if ($('#addTax_from').validate(false, validationSettings)){
		    var frm = $('#addTax_from').serializeFormJSON();
	        var con = JSON.stringify(frm);
	        con = con.replace(/[{}]/g, "");
	        //It will escape all the special characters
	        var jsonData = encodeURIComponent(con);
	        var operoxUrl ='${operoxUrl}';
	        $("#add_Tax ").attr("disabled", "disabled");
     		$( "#add_Tax " ).addClass('button').val('Processing..');
     		
	         $.ajax({
		    	type: "POST",
		    	 url: operoxUrl+"/addTax?${_csrf.parameterName}=${_csrf.token}&json="+jsonData, 
		        success: function(result) {
		        	if((result == 'taxHome')){
		        		location.replace(operoxUrl+"/tax");  
		        	}
			        
		        },
		    }); 
	        return true; 
		
	  }else{
		return false;
	  }
    }
 
   $('body').on('blur', '#addTax_from', function() {
		$("#addTax_from").showHelpOnFocus();
		$("#addTax_from").validateOnBlur(false, validationSettings);  
   });
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
 function validateTaxName(){
	 var taxName = document.getElementById("taxName").value;
	 $('#taxNameDiv').find('p.jquery_form_error_message').remove();
		var operoxUrl ='${operoxUrl}';
		if(taxName != null && taxName != ""){
			 $("#add_Tax ").attr("disabled", "disabled");
			$.ajax({
			   	type: "POST",
			    url: operoxUrl+"/getTaxByTaxName?${_csrf.parameterName}=${_csrf.token}&taxName="+taxName, 	   
				    success: function(data) {
				    	if(data == false){
			        		  $('#taxNameDiv').find('p.jquery_form_error_message').remove();
							  $("input#taxName").after("<p class='jquery_form_error_message'>"+taxName+" is Already Existed</span>");
							  document.getElementById("taxName").setAttribute('record-exist','yes');
							  document.getElementById("taxName").setAttribute('record-exist-errorMsg',taxName+' is Already Existed'); 
			        	}
			        	else{
			        		 $('input#taxName').removeAttr( "record-exist record-exist-errormsg");
			        		 $('#taxNameDiv').find('p.jquery_form_error_message').remove(); 
	                         $("#add_Tax ").removeAttr("disabled");
			        	} 
				    }
		     });
		}else{
			$('input#taxName').removeAttr( "record-exist record-exist-errormsg");
			$('#taxNameDiv').find('p.jquery_form_error_message').remove(); 
			if(storeId != null && storeId != "" && storeId != "undefined"){
				$("#add_Tax " ).addClass('btn btn-primary').val('Update');
			}else{
				$("#add_Tax " ).addClass('btn btn-primary').val('Add');
			}
	        $("#add_Tax ").removeAttr("disabled");
		}
 }
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
	
	function addSub(){
	  if ($("#addSub").prop('checked') ) {
			$(".addSub").show();
	} 
	else {
		$(".addSub").hide();
	}
};
</script>

</div>
