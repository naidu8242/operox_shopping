<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Add Holiday</h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home </a>&raquo;<a href="${operoxUrl}/holiday">Holidays</a>&raquo; Add Holiday</div>
  </section>
  
  <!-- Content Area inner -->
  <form:form id="holiday_form" name="holiday_form" method="post">
  <div class="content-area clearfix">
  
    <div class="div50">
       <label class="labl">Holiday Name&nbsp;<b class="text-danger">*</b></label>
       <span>
       <input type="text" placeholder="Holiday Name" value="${holiday.holidayName}" name="holidayName" id="holidayName" field-name="Holiday name" data-validation="required validate_Space">
       </span>
    </div>
    <input type="hidden" id="holidayId" name="holidayId" value="${holiday.id}">
    <div class="div50">
       <label class="labl">Year</label>
       <span>
       <input type="text" placeholder="YYYY" name="year" value="${holiday.year}" id="year" field-name="Year" data-validation="validate_Number validate_Space validate_length length4-4" data-validation-optional="true">
       </span>
    </div>
    
    <div class="div50">
       <label class="labl">Date of holiday&nbsp;<b class="text-danger">*</b></label>
       <span>
       <input type="text" placeholder="MM/DD/YY" readonly="readonly" class="datefild" name="dateOfHoliday" value="${holiday.holidayDateStr}" id="dateOfHoliday" field-name="Date Of Holiday" data-validation="required">
       </span>
    </div>
    
    <div class="div50">
       <label class="labl">Description&nbsp;<b class="text-danger">*</b></label>
       <span>
       <textarea rows="2" placeholder="Description" name="description" id="description" value=" " field-name="Description" data-validation="required validate_Space validate_length length2-500">${holiday.description}</textarea>
       </span>
    </div>
    <div class="clearfix"></div>
      <!-- Content Area inner -->
  </div>
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right custom-buttons">
         <a href="#" onclick="saveHoliday();" id="add" class="btn btn-primary">Add</a>
         <a href="#" id="cancel" onClick="cancelHoliday();" class="btn btn-default">Cancel</a>
        </div>
      </div>
    </div>
   </form:form>
</section>
<!-- Content Area Ends--> 

<script type="text/javascript">
function cancelHoliday(){
    var operoxUrl ='${operoxUrl}';
    location.replace(operoxUrl+ "/holiday");
}
</script>

<script type="text/javascript">
function saveHoliday() {
 	var validationSettings = {
		errorMessagePosition : 'element',
		borderColorOnError : '',
		scrollToTopOnError : true,
		dateFormat : 'yyyy/mm/dd'
	}; 
 	if ($('#holiday_form').validate(false, validationSettings)) { 
		var frm = $('#holiday_form').serializeFormJSON();
		var con = JSON.stringify(frm);
		con = con.replace(/[{}]/g, "");
		  //It will escape all the special characters
        var jsonData = encodeURIComponent(con);
		var operoxUrl = '${operoxUrl}';
		 $("#add").attr("disabled", "disabled");
		 $("#add" ).addClass('button').val('Processing..');
		/* $("#loading").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("resources/images/loading.gif"); background-position:center; background-repeat:no-repeat; background-size:50px;'); */
 		$.ajax({
					type : "POST",
					url : operoxUrl+ "/saveHoliday?${_csrf.parameterName}=${_csrf.token}&json="+jsonData,
					success : function(result) {
                     if(result == 'success'){
                    	 location.replace(operoxUrl+ "/holiday");
                     }else{
                    	 location.replace(operoxUrl+ "/addHoilday");
                     }
					},
				}); 
		return true;
	 } else {
		return false;
	} 
}
$('body').on('blur', '#holiday_form', function() {
	$("#holiday_form").showHelpOnFocus();
	$("#holiday_form").validateOnBlur(false, validationSettings);
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

<script type="text/javascript">
  $( function() {
    $( "#dateOfHoliday" ).datepicker();
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

<jsp:include page="../masterFooter.jsp" />