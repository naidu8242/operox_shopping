<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<div class="wraper clearfix">

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Add Shift</h2>
    </div>
    <div class="pull-right brud-crum"><a href="javascript:location.href = '${operoxUrl}/shift'">Shift List</a> &raquo; Add Shift</div>
  </section>
  
  <form:form id="addShift_form"  name="addShift_form" method="post">
  <div class="content-area clearfix">
    <input type="hidden" name="shiftId" value="${shift.id}">
    <div class="div33">
       <label class="labl">Shift Name<b class="text-danger">&nbsp;*</b></label>
       <span>
       <input type="text"  id="shiftName" name="shiftName" value="${shift.shiftName}" field-name="Shift Name" data-validation="required validate_Space validate_length length2-20">
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">In Time <b class="text-danger">&nbsp;*</b></label>
       <span>
       <input type="text" id="intime" readonly="readonly" name="intime" value="${shift.intime}" field-name="In Time" placeholder="HH:MM" class="timepicker" data-validation="required">
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Out Time<b class="text-danger">&nbsp;*</b></label>
       <span>
       <input type="text" id="outtime" readonly="readonly" name="outtime" value="${shift.outtime}" field-name="Out Time" placeholder="HH:MM" class="timepicker" data-validation="required">
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Description<b class="text-danger">&nbsp;*</b></label>
       <span>
 			<textarea rows="2" field-name="Description"  id="description" name="description" data-validation="required" >${shift.description}</textarea>
       </span>
    </div>
    
    
    <div class="clearfix"></div>
      <!-- Content Area inner -->
  </div>
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right">
          <input type="button" value="Add" onclick="addShift();"  id="add_shift" class="btn btn-success">
          <input type="button" value="Cancel" onClick="javascript:location.href = '${operoxUrl}/shift'" class="btn btn-default ml10">
        </div>
      </div>
    </div>
    </form:form>
</section>
<!-- Content Area Ends--> 

</div>

<jsp:include page="../masterFooter.jsp" />


<script type="text/javascript">
	function addShift() {
		var validationSettings = {
			errorMessagePosition : 'element',
			borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd'
		};
		
		if ($('#addShift_form').validate(false, validationSettings)) {
			
		    var frm = $('#addShift_form').serializeFormJSON();
	        var con = JSON.stringify(frm);
	        con = con.replace(/[{}]/g, "");
	        var jsonData = encodeURIComponent(con);
	        
	        var operoxUrl ='${operoxUrl}';
	        $("#add_shift").attr("disabled", "disabled");
     		$( "#add_shift" ).addClass('button').val('Processing..');
	        $.ajax({
		    	type: "POST",
		    	 url: operoxUrl+"/storeShift?${_csrf.parameterName}=${_csrf.token}&json="+jsonData, 
		        success: function(result) {
		        	if((result == 'shiftHome')){
		        		location.replace(operoxUrl+"/shift");  
		        	}
			        
		        },
		    }); 
			
			return true;
		} else {
			return false;
		}
	}
	$('body').on('blur', '#addShift_form', function() {
		$("#addShift_form").showHelpOnFocus();
		$("#addShift_form").validateOnBlur(false, validationSettings);
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







