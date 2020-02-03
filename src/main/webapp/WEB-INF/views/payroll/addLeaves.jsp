<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>
<!-- Content Area -->
<section class="content-wraper">
<form:form id="leave_form" name="leaveform" method="post">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Add Leave</h2>
    </div>
    <div class="pull-right brud-crum"><a href="/dashboard">Home</a>&raquo;<a href="leaves">Leave</a>&raquo; Add Leave</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix">
    <div class="div33">
       <label class="labl">Leave Name&nbsp;<b class="text-danger">*</b></label>
       <span>
       <input type="text" placeholder="leave Name"  value ="${leaves.leaveName}" name="leaveName" field-name="leave Name" data-validation="required validate_Space validate_length length2-100 " >
       </span>
    </div>
    <input type="hidden" id="leaveId" value="${leaves.id}" name=leaveId>
    <!-- <div class="div33">
       <label class="labl">Abbreviation</label>
       <span>
       <input type="text" placeholder="Abbreviations">
       </span>
    </div> -->
    <div class="div33">
       <label class="labl">Description</label>
       <span>
       <textarea rows="2" placeholder="Description" name="description" field-name="Description" value ="" data-validation="required validate_Space" data-validation-optional="true">${leaves.description}</textarea>
       </span>
    </div>
    <div class="clearfix"></div>
      <!-- Content Area inner -->
  </div>
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right custom-buttons">
        <input type="button" value="Add"  id="add_leave" onclick="addLeave();" class="btn btn-primary">
         <a href="#" onClick="cancelLeave();" id="can" class="btn btn-default">Cancel</a>
        </div>
      </div>
    </div>
    </form:form>
</section>
<!-- Content Area Ends--> 

<script type="text/javascript">
function cancelLeave(){
    var operoxUrl ='${operoxUrl}';
    location.replace(operoxUrl+ "/leaves");
}
</script>

<script type="text/javascript">
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script>

<script>
function addLeave(){
	var validationSettings = {
			errorMessagePosition : 'element',
			borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd'
		};
	if ($('#leave_form').validate(false, validationSettings)) { 
			var frm = $('#leave_form').serializeFormJSON();
			var con = JSON.stringify(frm);
			con = con.replace(/[{}]/g, "");
			  //It will escape all the special characters
	        var jsonData = encodeURIComponent(con);
			var operoxUrl = '${operoxUrl}';
			 $("#add_leave").attr("disabled", "disabled");
			 $("#add_leave" ).addClass('button').val('Processing..');
	 		$.ajax({
						type : "POST",
						url : operoxUrl+ "/addLeaves?${_csrf.parameterName}=${_csrf.token}&json="+jsonData,
						success : function(result) {
	                     if(result == 'success'){
	                    	 location.replace(operoxUrl+ "/leaves");
	                     }
						},
					}); 
			return true;
	}else{
		return false;
	}

}
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

<jsp:include page="../masterFooter.jsp" />