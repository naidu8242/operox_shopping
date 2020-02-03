<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2><c:if test="${empty currency.id}">Add Curreny</c:if> <c:if test="${!empty currency.id}">Edit Curreny</c:if></h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; <c:if test="${empty currency.id}">Add Curreny</c:if> <c:if test="${!empty currency.id}">Edit Curreny</c:if></div>
  </section>
  
  <!-- Content Area inner -->
  <form:form id="addCurrency_form" name="addCurrency_form" method="post">
  <div class="content-area clearfix">
  
    
    <div class="div50" id="currencyNameDiv">
       <label class="labl">Currency &nbsp;<b class="text-danger">*</b></label>
       <span>
     <input type="text" placeholder="Currency Name" value="${currency.currency}" id="currencyName" name="currencyName"  placeholder="Currency Name" field-name="Currency Name" data-validation="required validate_Space validate_length length2-250" onblur="validateCurrency('${currency.currency}');" >
       </span>
    </div>

 	<input type="hidden" id="id" name="id" value="${currency.id}">
    
     <div class="div50">
       <label class="labl">Code &nbsp;<b class="text-danger">*</b></label>
       <span>
 			 <input type="text" placeholder="Symbol" value="${currency.symbol}" id="symbol" name="symbol"  placeholder="Code" field-name="Code" data-validation="required validate_Space" >
       </span>
    </div>
    
    <div class="div50">
       <label class="labl">Exchange Value<b class="text-danger">*</b>(${currencyValue.symbol})</label>
       <span>
 			<input type="text" placeholder="Exchange Value" id="exchangeValue" name="exchangeValue" data-validation="required validate_Space data-validation validate_float" value="${currency.exchangeValue}"/>
       </span>
    </div>
    
    <div class="div50">
       <label class="labl">Description </label>
       <span>
       <textarea rows="2" placeholder="Currency description" id="currencyDescription" name="currencyDescription" >${currency.description}</textarea>
       </span>
    </div>

    <div class="clearfix"></div>
      <!-- Content Area inner -->
  </div>
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right">
	        <c:if test="${empty currency.id}">
	          <input type="button" value="Add" id="addCurrency" onclick="saveCurrency();" class="btn btn-primary"> 
	        </c:if>
	        <c:if test="${!empty currency.id}">
	          <input type="button" value="Update" id="addCurrency" onclick="saveCurrency();" class="btn btn-primary"> 
	        </c:if>
			<input type="button" value="Cancel" onClick="cancelCurrency();" class="btn btn-default ml">
        </div>
      </div>
    </div>
     </form:form>
</section>
<!-- Content Area Ends--> 

<script type="text/javascript">
function cancelCurrency(){
    var operoxUrl ='${operoxUrl}';
    location.replace(operoxUrl+ "/currency");
}
</script>

<script type="text/javascript">
  $( function() {
    $( "#orderDate, #deliveryDate" ).datepicker();
  });
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
</script>

<script type="text/javascript">
function saveCurrency(){
	var validationSettings = {
			errorMessagePosition : 'element',
			borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd'
		};
	
	if ($('#addCurrency_form').validate(false, validationSettings)) {
		var frm = $('#addCurrency_form').serializeFormJSON();
		var con = JSON.stringify(frm);
		con = con.replace(/[{}]/g, "");
		//It will escape all the special characters
		var jsonData = encodeURIComponent(con);
		var operoxUrl = '${operoxUrl}';
		$("#addCurrency_form").attr("disabled", "disabled");
		$("#addCurrency_form").addClass('button').val('Processing..');

		$.ajax({
					type : "POST",
					url : operoxUrl+ "/saveCurrency?${_csrf.parameterName}=${_csrf.token}&json="+ jsonData,
					success : function(result) {
						if (result == 'success') {
							location.replace(operoxUrl
									+ "/currency");
						}

					},
				});
		return true;
	} else {
		return false;
	}
}
$('body').on('blur', '#addCurrency_form', function() {
	$("#addCurrency_form").showHelpOnFocus();
	$("#addCurrency_form").validateOnBlur(false, validationSettings);
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
function validateCurrency(currencyNameValue){
	var currencyName = $("#currencyName").val();
	var operoxUrl ='${operoxUrl}';
	$('#currencyNameDiv').find('p.jquery_form_error_message').remove();
	$('input#currencyName').removeAttr("class record-exist record-exist-errormsg");
	$('input#currencyName').attr('class','valid form-control');
	$('#currencyNameDiv').find('p.jquery_form_error_message').remove();
	 $("#addCurrency" ).addClass('btn btn-primary').val('Add');
     $("#addCurrency").removeAttr("disabled");
     if(currencyNameValue != currencyName){
	if (currencyName != null && currencyName != '') {
		 $("#addCurrency").attr("disabled", "disabled");
		$.ajax({
			type : "POST",
			url : operoxUrl + "/validateCurrency?${_csrf.parameterName}=${_csrf.token}&currencyName="+ currencyName,
			success : function(data) {
				var result = data;
				if (result == false) {
					$('#currencyNameDiv').find('p.jquery_form_error_message').remove();
				    $("input#currencyName").after("<p class='jquery_form_error_message'>"+currencyName+" is Already Existed</span>");
				    document.getElementById("currencyName").setAttribute('record-exist','yes');
				    document.getElementById("currencyName").setAttribute('record-exist-errorMsg',currencyName+' is Already Existed');
					
				} else {
					//New WebSite class="form-control"
					$('input#currencyName').removeAttr("class record-exist record-exist-errormsg");
					$('input#currencyName').attr('class','valid form-control');
					$('#currencyNameDiv').find('p.jquery_form_error_message').remove();
					 $("#addCurrency" ).addClass('btn btn-primary').val('Add');
                     $("#addCurrency").removeAttr("disabled");
				}
			},

		});
			$("#addCurrency_form").click(function() {
		if ($(this).validate(false, validationSettings)) {
		}
	}); 
	}
}
}
</script>

<jsp:include page="../masterFooter.jsp" />