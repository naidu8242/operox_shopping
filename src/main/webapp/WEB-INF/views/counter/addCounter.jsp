<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>
<link rel="stylesheet" href="${operoxUrl}/resources/css/chosen.min.css">
<script src="${operoxUrl}/resources/js/chosen.jquery.min.js"></script> 


<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2><c:if test="${empty counter.id}">Add Counter</c:if> <c:if test="${!empty counter.id}">Edit Counter</c:if></h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; <a href="${operoxUrl}/counterList">Counters List</a> &raquo; <c:if test="${empty counter.id}">Add Counter</c:if> <c:if test="${!empty counter.id}">Edit Counter</c:if></div>
  </section>
  
  <!-- Content Area inner -->
 <form:form id="addCounter_from"  name="addCounter_from" method="post">
 <input type="hidden" id="counterId" name="counterId" value="${counter.id}">
  <div class="content-area clearfix">
    <div class="div50">
       <label class="labl">Location Name&nbsp;<b class="text-danger"> *</b></label>
       <span>
         <select class="chosen-select" id="storeId" name="storeId" field-name="Store Name" data-validation="required" onchange="validateCounterName();">
              <option value="">--Select Location Name--</option>
               <c:forEach var="store" items="${storesList}">
	              <option value="${store.id}" ${store.id  == counter.store.id ? 'selected' : ''} >${store.storeName}</option>
               </c:forEach>
         </select>
       </span>
    </div> 
  
    <div class="div50" id="counterNameDiv">
       <label class="labl">Counter Name / Id&nbsp; <b class="text-danger"> *</b></label>
       <span>
       <input type="text" value="${counter.counterName}" id="counterName" name="counterName" field-name="Counter Name / Id" onblur="validateCounterName();" onmouseout="validateCounterName();"
             data-validation="required validate_Space validate_Alphanumeric validate_length length2-100" placeholder="Conter Name/Id">
       </span>
    </div>
    
    <div class="div50">
       <label class="labl">Counter Type&nbsp; <b class="text-danger"> *</b></label>
       <span>
         <select id="counterType" name="counterType" field-name="Counter Type" data-validation="required">
              <option selected disabled>--Select Counter Type--</option>
              <option value="Cash Counter" ${counter.counterType  =='Cash Counter' ? 'selected' : ''}>Cash Counter</option>
	          <option value="Cash & Card Counter" ${counter.counterType  == 'Cash & Card Counter' ? 'selected' : ''}>Cash & Card Counter</option>
	          <option value="Express Counter" ${counter.counterType  == 'Express Counter' ? 'selected' : ''}>Express Counter</option>
	          <option value="Customer Service" ${counter.counterType  == 'Customer Service' ? 'selected' : ''}>Customer Service</option>
         </select>
       </span>
    </div> 
      
      <div class="div50">
       <label class="labl">Description</label>
       <span>
          <textarea placeholder="Description"  value=" " name ="description" id="description" data-validation-optional="true" data-validation="validate_Space validate_length length5-500" rows="3">${counter.description}</textarea>
       </span>
    </div>
    <div class="clearfix"></div>
      <!-- Content Area inner -->
  </div>
  
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right">
          <c:if test="${empty counter.id}">
              <input type="button" value="Add" class="btn btn-primary" id="add_counter" onclick="addCounter();">
          </c:if>
          <c:if test="${!empty counter.id}">
             <input type="button" value="Update" class="btn btn-primary" id="add_counter" onclick="addCounter();">
          </c:if>
          <input type="button" value="Cancel" onClick="javascript:location.href = '<c:url value='/counterList'/>'" class="btn btn-default ml10">
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
 function addCounter() {
	 var operoxUrl ='${operoxUrl}';
	   if ($('#addCounter_from').validate(false, validationSettings)){
		    var frm = $('#addCounter_from').serializeFormJSON();
	        var con = JSON.stringify(frm);
	        con = con.replace(/[{}]/g, "");
	        //It will escape all the special characters
	        var jsonData = encodeURIComponent(con);
	        $("#add_counter").attr("disabled", "disabled");
     		$("#add_counter" ).addClass('button').val('Processing..');
     		//$("#loading").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("resources/images/loading.gif"); background-position:center; background-repeat:no-repeat; background-size:50px;');
	        $.ajax({
		    	type: "POST",
		    	 url: operoxUrl+"/addCounter?${_csrf.parameterName}=${_csrf.token}&json="+jsonData, 
		        success: function(result) {
		        	if((result == 'counterList')){
		        		location.replace(operoxUrl+"/counterList");  
		        	}
			        
		        },
		    }); 
	        return true;
		
	  }else{
		return false;
	  }
    }
 
   $('body').on('blur', '#addCounter_from', function() {
		$("#addCounter_from").showHelpOnFocus();
		$("#addCounter_from").validateOnBlur(false, validationSettings);  
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
function validateCounterName(){
	var storeId = document.getElementById("storeId").value;
	var counterName = document.getElementById("counterName").value;
	var counterId = document.getElementById("counterId").value;
	$('#counterNameDiv').find('p.jquery_form_error_message').remove();
	var operoxUrl ='${operoxUrl}';
	if(storeId != null && storeId != "" && counterName != null && counterName != ""){
		 $("#add_counter").attr("disabled", "disabled");
		$.ajax({
		   	type: "POST",
		    url: operoxUrl+"/validateCounterName?${_csrf.parameterName}=${_csrf.token}&storeId="+storeId+"&counterName="+counterName+"&counterId="+counterId, 	   
			    success: function(data) {
			    	if(data == true){
		        		  $('#counterNameDiv').find('p.jquery_form_error_message').remove();
						  $("input#counterName").after("<p class='jquery_form_error_message'>"+counterName+" is Already Existed</span>");
						  document.getElementById("counterName").setAttribute('record-exist','yes');
						  document.getElementById("counterName").setAttribute('record-exist-errorMsg',counterName+' is Already Existed'); 
		        	}
		        	else{
		        		 $('input#counterName').removeAttr( "record-exist record-exist-errormsg");
		        		 $('#counterNameDiv').find('p.jquery_form_error_message').remove(); 
		        		 if(counterId != null && counterId != "" && counterId != "undefined"){
		        			 $("#add_counter" ).addClass('btn btn-primary').val('Update');
		     			 }else{
		     				$("#add_counter" ).addClass('btn btn-primary').val('Add');
		     			 }
                         $("#add_counter").removeAttr("disabled");
		        	} 
			    }
	     });
	}else{
		$('input#counterName').removeAttr( "record-exist record-exist-errormsg");
		$('#counterNameDiv').find('p.jquery_form_error_message').remove(); 
		if(counterId != null && counterId != "" && counterId != "undefined"){
			 $("#add_counter" ).addClass('btn btn-primary').val('Update');
	    }else{
		   $("#add_counter" ).addClass('btn btn-primary').val('Add');
	    }
        $("#add_counter").removeAttr("disabled");
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
	       $("#storeId").chosen();
	      
	       $('.chosen-select').each(function() {
				var $this = $(this);
				$this.next().css({'width': '100%'});
		  })
});
</script>

<jsp:include page="../masterFooter.jsp" />
