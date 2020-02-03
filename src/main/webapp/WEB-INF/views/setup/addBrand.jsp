<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2><c:if test="${empty brand.id}">Add Brand</c:if> <c:if test="${!empty brand.id}">Edit Brand</c:if></h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; <c:if test="${empty brand.id}">Add Brand</c:if> <c:if test="${!empty brand.id}">Edit Brand</c:if></div>
  </section>
  
  <!-- Content Area inner -->
  <form:form id="addBrand_form" name="addBrand_form" method="post">
  <div class="content-area clearfix">
  
    
   <%--  <div class="div33" >
       <label class="labl">Category</label>
       <span>
       <select class="chosen-select" id="category" name="category" field-name="Select Product" >
			<option value="">-- Select Category --</option>
				<c:forEach var="option" items="${categoryList}">
                    <option value="${option.id}" ${option.id  == brand.category.id ? 'selected' : ''}>${option.categoryName} </option>
                </c:forEach>
			</select>
       </span>
    </div> --%>
    
    <div class="div33" id="brandDiv">
       <label class="labl">Brand Name <b class="text-danger">*</b></label>
       <span>
       <input type="text" placeholder="Brand Name" value="${brand.brandName}" id="brandName" name="brandName"  placeholder="Brand Name" field-name="Brand Name" data-validation="required validate_Space validate_length length2-250"  onblur="validateBrand('${brand.brandName}');">
       </span>
    </div>
 	<input type="hidden" id="id" name="id" value="${brand.id}">
    
    <div class="div33">
       <label class="labl">Brand description</label>
       <span>
 			<textarea rows="2" placeholder="Brand description" id="brandDescription" name="brandDescription" data-validation="validate_Space validate_length length2-250" data-validation-optional="true">${brand.description}</textarea>
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Manufactured by</label>
       <span>
       <input type="text" placeholder="Manufatured by" id="manufacturedBy" name="manufacturedBy" value="${brand.manufacturedBy}" data-validation="validate_Space" data-validation-optional="true">
       </span>
    </div>
    <div class="div33">
       <label class="labl">Marketed by</label>
       <span>
       <input type="text" placeholder="Marketed by" id="marketedBy" name="marketedBy" value="${brand.marketedBy}" data-validation="validate_Space" data-validation-optional="true">
       </span>
    </div>

    <div class="clearfix"></div>
      <!-- Content Area inner -->
  </div>
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right">
	        <c:if test="${empty brand.id}">
	          <input type="button" value="Add" id="addBrand" onclick="saveBrand();" class="btn btn-primary"> 
	        </c:if>
	        <c:if test="${!empty brand.id}">
	          <input type="button" value="Update" id="addBrand" onclick="saveBrand();" class="btn btn-primary"> 
	        </c:if>
			<input type="button" value="Cancel" onClick="cancelBrand();" class="btn btn-default ml">
        </div>
      </div>
    </div>
     </form:form>
</section>
<!-- Content Area Ends--> 

<script type="text/javascript">
function cancelBrand(){
    var operoxUrl ='${operoxUrl}';
    location.replace(operoxUrl+ "/brandHome");
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
function saveBrand(){
	var validationSettings = {
			errorMessagePosition : 'element',
			borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd'
		};
	
	if ($('#addBrand_form').validate(false, validationSettings)) {
		 $("#addBrand").attr("disabled", "disabled");
		 $("#addBrand" ).addClass('button').val('Processing..');
		var frm = $('#addBrand_form').serializeFormJSON();
		var con = JSON.stringify(frm);
		con = con.replace(/[{}]/g, "");
		//It will escape all the special characters
		var jsonData = encodeURIComponent(con);
		var operoxUrl = '${operoxUrl}';
		$("#addBrand_form").attr("disabled", "disabled");
		$("#addBrand_form").addClass('button').val('Processing..');

		$.ajax({
					type : "POST",
					url : operoxUrl+ "/saveBrand?${_csrf.parameterName}=${_csrf.token}&json="+ jsonData,
					success : function(result) {
						if (result == 'brandHome') {
							location.replace(operoxUrl
									+ "/brandHome");
						}

					},
				});
		return true;
	} else {
		return false;
	}
}
$('body').on('blur', '#addBrand_form', function() {
	$("#addBrand_form").showHelpOnFocus();
	$("#addBrand_form").validateOnBlur(false, validationSettings);
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
function validateBrand(brandValue){
	var brandName = $("#brandName").val();
	var operoxUrl ='${operoxUrl}';
	$('#brandDiv').find('p.jquery_form_error_message').remove();
    if(brandName != brandValue){
	if (brandName != null && brandName != '') {
		 $("#addBrand").attr("disabled", "disabled");
		 $("#addBrand" ).addClass('button').val('Processing..');
		$.ajax({
			type : "POST",
			url : operoxUrl + "/validateBrand?${_csrf.parameterName}=${_csrf.token}&brandName="+ brandName,
			success : function(data) {
				var result = data;
				if (result == false) {
					$('#brandDiv').find('p.jquery_form_error_message').remove();
				    $("input#brandName").after("<p class='jquery_form_error_message'>"+brandName+" is Already Existed</span>");
				    document.getElementById("brandName").setAttribute('record-exist','yes');
				    document.getElementById("brandName").setAttribute('record-exist-errorMsg',brandName+' is Already Existed');
					
				} else {
					//New WebSite class="form-control"
					$('input#brandName').removeAttr("class record-exist record-exist-errormsg");
					$('input#brandName').attr('class','valid form-control');
					$('#brandDiv').find('p.jquery_form_error_message').remove();
					 $("#addBrand" ).addClass('btn btn-primary').val('Add');
                     $("#addBrand").removeAttr("disabled");
				}
			},

		});
			$("#addBrand_form").click(function() {
		if ($(this).validate(false, validationSettings)) {
		}
	}); 
	}
    }
}
</script>

<jsp:include page="../masterFooter.jsp" />