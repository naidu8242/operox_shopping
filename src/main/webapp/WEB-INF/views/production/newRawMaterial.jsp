<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />


<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<link rel="stylesheet" href="${operoxUrl}/resources/css/chosen.min.css">
<script src="${operoxUrl}/resources/js/chosen.jquery.min.js"></script>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<!-- Content Area -->
<section class="content-wraper">
	<section class="content-header clearfix">
		<div class="pull-left">
			<h2>Add Raw Material</h2>
		</div>
		<div class="pull-right brud-crum">
			<a href="${operoxUrl}/rawMaterials">Raw Materials List </a>&raquo;
			Add Raw Material
		</div>
	</section>

	<!-- Content Area inner -->
	<div class="content-area clearfix">
		<form:form id="rawMaterial_form" name="rawMaterial_form" method="post">
			<div class="div33">
				<label class="labl">Raw Material Name&nbsp;<b
					class="text-danger">*</b></label> <span> <input type="text"
					placeholder="Raw Material Name" id="materialName"
					name="materialName" field-name="Raw Material Name"
					data-validation="required validate_Space validate_Alphanumeric validate_length length2-100"
					value="${rawMaterial.materialName}">
				</span>
			</div>
			<input type="hidden" id="rawMaterialId" name="rawMaterialId"
				value="${rawMaterial.id}">
			<div class="div33" id="rawMaterialCodeDiv">
				<label class="labl">Code</label> <span> <input type="text"
					placeholder="Code" id="searchCode" name="searchCode"
					field-name="Code"
					data-validation="validate_Space validate_Alphanumeric validate_length length2-20"
					data-validation-optional="true" value="${rawMaterial.searchCode}"
					onblur="validateRawMaterialCode();"
					onmouseout="validateRawMaterialCode();">
				</span>
			</div>

			<div class="div33">
				<label class="labl">Measuring Unit</label> <span> <select
					class="chosen-select" id="measuringUnit" name="measuringUnit"
					field-name="Select Measuring Unit">
						<option value="">-- Select Measuring Unit --</option>
						<c:forEach var="report" items="${measuringUnitList}">
							<option value="${report.id}"
								${report.id  == rawMaterial.measuringUnit.id ? 'selected' : ''}>${report.measuringUnit}</option>
						</c:forEach>
				</select>
				</span>
			</div>

			<div class="div33">
				<label class="labl">Unit Price&nbsp;<b class="text-danger">*</b></label>
				<span> <input type="text" placeholder="Unit Price"
					id="unitPrice" name="unitPrice" field-name="Unit Price"
					data-validation="required validate_Space validate_float validate_length length1-10"
					value="${rawMaterial.unitPrice}">
				</span>
			</div>

			<div class="div33">
				<label class="labl">Discount</label> <span> <input
					type="text" placeholder="Discount %" id="discount" name="discount"
					field-name="Discount %"
					data-validation="validate_Space validate_int validate_length length1-10"
					data-validation-optional="true" value="${rawMaterial.discount}">
				</span>
			</div>

			<div class="div33">
				<label class="labl">&nbsp;</label> <span class="checkBox"> <input
					type="checkbox" name="isPercentage" id="isPercentage"
					value="${rawMaterial.isPercentage}"
					${rawMaterial.isPercentage  == 'Y' ? "checked" : ''}
					onclick="ispercentage()">&nbsp;Is Percentage ?
				</span>
			</div>

			<div class="div33">
				<label class="labl">Annual Order Qty.&nbsp;<b
					class="text-danger">*</b></label> <span> <input type="text"
					placeholder="Annul Order Qty" id="annualOrderQuantity"
					name="annualOrderQuantity" field-name="Annul Order Qty"
					data-validation="required validate_Space validate_int validate_length length1-10"
					value="${rawMaterial.annualOrderQuantity}">
				</span>
			</div>

			<div class="div33">
				<label class="labl">Production Unit </label> <span> <select
					class="chosen-select" id="store" name="store"
					field-name="Production Unit" data-validation="validate_Space"
					data-validation-optional="true">
						<option selected disabled>Select Production Unit</option>
						<c:forEach var="store" items="${storeList}">
							<option value="${store.id}"
								${store.id  == rawMaterial.store.id ? 'selected' : ''}>${store.storeName}</option>
						</c:forEach>
				</select>
				</span>
			</div>

			<div class="div33">
				<label class="labl">Available Inventory</label> <span> <input
					type="text" placeholder="Available Inventory"
					id="availableInventory" name="availableInventory"
					field-name="Available Inventory"
					data-validation="validate_Space validate_Alphanumeric"
					data-validation-optional="true"
					value="${rawMaterial.availableInventory}">
				</span>
			</div>
			<div class="div33">
				<label class="labl">Description </label> <span> <textarea
						rows="2" placeholder="Description" id="description"
						name="description" field-name="Annul Order Qty"
						data-validation="validate_Space validate_Alphanumeric validate_length length3-500"
						data-validation-optional="true"><c:out
							value="${rawMaterial.description}" /></textarea>
				</span>
			</div>
		</form:form>
		<div class="clearfix"></div>
		<!-- Content Area inner -->
	</div>
	<div class="form-footer">
		<div class="col-lg-12">
			<div class="pull-right">
				<input type="button" value="Save" class="btn btn-primary"
					id="save_rawMaterial" onclick="storeRawMaterial();"> <input
					type="button" value="Cancel"
					onClick="javascript:location.href = '<c:url value='/rawMaterials'/>'"
					class="btn btn-default ml10">
			</div>
		</div>
	</div>
</section>
<!-- Content Area Ends-->

<!-- Raw Material Store/add Script Starts here -->
<script type="text/javascript">
    function storeRawMaterial() {
    	 if ($('#rawMaterial_form').validate(false, validationSettings)){
            var frm = $('#rawMaterial_form').serializeFormJSON();
            var con = JSON.stringify(frm);
            con = con.replace(/[{}]/g, "");
              //It will escape all the special characters
            var operoxUrl = '${operoxUrl}';
            $.ajax({
                        type : "POST",
                        url : operoxUrl+"/saveRawMaterial?${_csrf.parameterName}=${_csrf.token}&json="+con,
                        		
                        success : function(result) {
                                location.replace(operoxUrl+"/rawMaterials");
                        },
                    });
            return true;
    	  }else{
  		return false;
  	  }
      }
     $('body').on('blur', '#rawMaterial_form', function() {
  		$("#rawMaterial_form").showHelpOnFocus();
  		$("#rawMaterial_form").validateOnBlur(false, validationSettings);  
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
<!-- Raw Material Store/add Script Ends here -->

<script type="text/javascript">
  $( function() {
    $( "#appddate, #orderdate, #despatchedDt, #dueDt" ).datepicker();
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

<script>
    function ispercentage() {
      if (document.getElementById('isPercentage').checked) {
             $("#isPercentage").val('Y');
      } else {
        	 $("#isPercentage").val('N');
         
      }
    }        
</script>


<!-- RawMaterialCode(searchCode) Duplicate Check script Starts Here -->

<script type="text/javascript">
function validateRawMaterialCode(){
	
	var rawMaterialCode = document.getElementById("searchCode").value;
	var operoxUrl = '${operoxUrl}';
	if(rawMaterialCode.match(/^[^\s-](.*)*$/) != null){

		$('#rawMaterialCodeDiv').find('p.jquery_form_error_message').remove();
	if (rawMaterialCode != null && rawMaterialCode != '') {
		$.ajax({
			type : "GET",
			url : operoxUrl+"/isRawMaterialCodeValidated?${_csrf.parameterName}=${_csrf.token}&rawMaterialCode="+rawMaterialCode,
			success : function(data) {
				var result = data;
				$("#save_rawMaterial").prop('disabled', false);
				if (result == true) {
					//duplicate supplier
					$("#save_rawMaterial").prop('disabled', true);
					$("input#searchCode").after( "<p class='jquery_form_error_message'>" + rawMaterialCode + " is already existed as Code</span>");
					document.getElementById("rawMaterialCode").setAttribute( 'record-exist', 'yes');
					document.getElementById("rawMaterialCode").setAttribute( 'record-exist-errorMsg', rawMaterialCode +"  already existed as Code");
				}else {
					$('input#searchCode').removeAttr("class record-exist record-exist-errormsg");
					$('input#searchCode').attr('class','valid form-control');
					$('#rawMaterialCodeDiv').find('p.jquery_form_error_message').remove();
					$("#save_rawMaterial").click(function() {
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

<!-- RawMaterialCode Duplicate Check script Ends Here -->
<jsp:include page="../masterFooter.jsp" />