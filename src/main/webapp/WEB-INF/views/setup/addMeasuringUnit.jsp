<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2><c:if test="${empty measuringUnit.id}">Add Measuring Unit</c:if> <c:if test="${!empty measuringUnit.id}">Edit Measuring Unit</c:if></h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo;<a href="${operoxUrl}/measuringUnit">Measuring Unit</a> &raquo;<c:if test="${empty measuringUnit.id}">Add Measuring Unit</c:if> <c:if test="${!empty measuringUnit.id}">Edit Measuring Unit</c:if> </div>
  </section>
  
  <!-- Content Area inner -->
   <form:form id="addMeasuringUnit_form" name="addMeasuringUnit_form" method="post">
   <input type="hidden" value="${measuringUnit.id}" id="measuringUnitId" name="measuringUnitId"/>
  <div class="content-area clearfix">
    <div class="div33" id="measuringUnitDiv">
       <label class="labl">Measuring Unit<b class="text-danger">*</b></label>
       <span>
       <input type="text" placeholder="Measuring Unit" class="form-control" id="measuringUnit" name="measuringUnit" field-name="Measuring Unit" onblur="validateMeasuringUnit('${measuringUnit.measuringUnit}');" data-validation="required validate_Space " value="${measuringUnit.measuringUnit}">
       </span>
    </div>

    <div class="div33">
       <label class="labl">Description</label>
       <span>
       <textarea placeholder="Description"  value=" " name ="description" id="description" data-validation-optional="true" data-validation="validate_Space validate_length length5-500" rows="3">${measuringUnit.description}</textarea>
<%--  			<textarea rows="2" placeholder="MeasuringUnit description" id="description" name="description" field-name="Description" data-validation="required validate_Space " data-validation-optional="true" value=" ">${measuringUnit.description}</textarea> --%>
<%--  			<input type="text" placeholder="MeasuringUnit description" name="description" id="description" field-name="Description" value="${measuringUnit.description}" data-validation="required validate_Space" data-validation-optional="true"> --%>
       </span>
    </div>
    <div class="clearfix"></div>
      <!-- Content Area inner -->
  </div>
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right">
	        <c:if test="${empty measuringUnit.id}">
	             <input type="button" value="Add" class="btn btn-primary" id="save" onclick="saveMeasuringUnit()">
	        </c:if>
	        <c:if test="${!empty measuringUnit.id}">
	           <input type="button" value="Update" class="btn btn-primary" id="save" onclick="saveMeasuringUnit()">
	        </c:if>
           <input type="button" value="Cancel" class="btn btn-default ml10" onclick="cancelMeasuringUnit()">
          </div>
      </div>
    </div>
    </form:form>
</section>

<script>
function cancelMeasuringUnit(){
	var operoxUrl ='${operoxUrl}';
	location.replace(operoxUrl+ "/measuringUnit");
}
</script>
<script type="text/javascript">
  $( function() {
    $( "#orderDate, #deliveryDate" ).datepicker();
  });
</script>

<script type="text/javascript">
	function validateMeasuringUnit(measuringUnitValue){
		var measuringUnit = $("#measuringUnit").val();
		var operoxUrl ='${operoxUrl}';
		$('input#measuringUnit').removeAttr("class record-exist record-exist-errormsg");
		$('input#measuringUnit').attr('class','valid form-control');
		$('#measuringUnitDiv').find('p.jquery_form_error_message').remove();
		$("#save" ).addClass('btn btn-primary').val('Add');
        $("#save").removeAttr("disabled");
		$('#measuringUnitDiv').find('p.jquery_form_error_message').remove();
		
		if(measuringUnitValue != measuringUnit){
		if (measuringUnit != null && measuringUnit != '') {
			$("#save").attr("disabled", "disabled");
			 $("#save" ).addClass('button').val('Processing..');
			$.ajax({
				type : "POST",
				url : operoxUrl + "/validateMeasuringUnit?${_csrf.parameterName}=${_csrf.token}&measuringUnit="+ measuringUnit,
				success : function(data) {
					var result = data;
					if (result == false) {
						$("input#measuringUnit").after("<p class='jquery_form_error_message'>"+ measuringUnit+ " is already existed"+"</span>");
						document.getElementById("measuringUnit").setAttribute('record-exist', 'yes');
						document.getElementById("measuringUnit").setAttribute('record-exist-errorMsg',measuringUnit + ' is already existed');
						$('input#measuringUnit').removeAttr( "record-exist record-exist-errormsg");
						
					} else {
						//New WebSite class="form-control"
						$('input#measuringUnit').removeAttr("class record-exist record-exist-errormsg");
						$('input#measuringUnit').attr('class','valid form-control');
						$('#measuringUnitDiv').find('p.jquery_form_error_message').remove();
						$("#save" ).addClass('btn btn-primary').val('Add');
                        $("#save").removeAttr("disabled");
					}
				},
			});
			$("#addMeasuringUnit_form").click(function() {
				if ($(this).validate(false, validationSettings)) {
				}
			});
		}
		}
	}
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
	function saveMeasuringUnit() {
		
		 var validationSettings = {
	           errorMessagePosition : 'element',
	           borderColorOnError : '',
			   scrollToTopOnError : true,
			   dateFormat : 'yyyy/mm/dd' 
	           };
		
		if ($('#addMeasuringUnit_form').validate(false, validationSettings)) {
			$("#save").attr("disabled", "disabled");
			 $("#save" ).addClass('button').val('Processing..');
			var frm = $('#addMeasuringUnit_form').serializeFormJSON();
			var con = JSON.stringify(frm);
			con = con.replace(/[{}]/g, "");
			  //It will escape all the special characters
	        var jsonData = encodeURIComponent(con);
	        var operoxUrl ='${operoxUrl}';
		$.ajax({
			type : "POST",
			url : operoxUrl
					+ "/saveMeasuringUnit?${_csrf.parameterName}=${_csrf.token}&json="+jsonData,
					success : function(result) {
						if (result == 'success') {
							location.replace(operoxUrl + "/measuringUnit");
						}

					},
				});
		return true;
	} else {
		return false;
	}
}
	$('body').on('blur', '#addMeasuringUnit_form', function() {
		$("#addMeasuringUnit_form").showHelpOnFocus();
		$("#addMeasuringUnit_form").validateOnBlur(false, validationSettings);
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
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script> 

<jsp:include page="../masterFooter.jsp" />