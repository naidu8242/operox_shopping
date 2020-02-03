<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="resources/js/formvalidator.js"></script>

<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Add Category</h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; Add Category</div>
  </section>
  
  <div class="content-area content-new clearfix">
	<div id="loading"></div> 
	<form:form id="addCategory_form" name="addCategory_form" method="post">
  <div class="content-area clearfix">
    <div class="div50" id="categoryDiv">
       <label class="labl">Category Name<b class="text-danger">&nbsp;*</b></label>
       <span>
       <input type="text" placeholder="Category Name" onblur="validateCategory();" class="form-control" id="categoryName" name="categoryName" field-name="Category Name" data-validation="required validate_Space " value="">
       </span>
    </div>
    <div class="div50">
       <label class="labl">Category Description</label>
       <span>
       <textarea placeholder="Category Description" id="Category Description" name="categoryDescription" rows="1"></textarea>
       </span>
    </div>
    <div class="clearfix"></div>
  <div class="clearfix headerh2">
   <h2 class="mb"><label class="mr"></label>Add Subcategory<i class="fa fa-plus addNew" onclick="addSub();" title="Add Subcategory"></i></h2>
   <div class="addSub" id="addSub">
        <input type="hidden" id="subDivLength" value="0"/>
         <!-- <div id="subDiv1">
		    <div class="div50">
		       <label class="labl">Sub Category</label>
		       <span>
		       <input type="text" id="subCategoryName1" name="subCategoryName1" placeholder="Sub Category">
		       </span>
		    </div>
		    <div class="div50">
		       <label class="labl">Description</label>
		       <span>
		      <textarea  id="subCategoryDescription1" name="subCategoryDescription1" placeholder="Sub Category Description" rows="1"></textarea>
		       </span>
		    </div>
	    </div> -->
	     <div id="divds" style="visibility: hidden;display:none;">
	       <div id="subDivREPLACEROWID">
	         <div class="div50">
		           <label class="labl">Sub Category<b class="text-danger">&nbsp;*</b></label>
			       <span>
			       <input type="text" id="subCategoryNameREPLACEROWID" name="subCategoryNameREPLACEROWID" placeholder="Sub Category" data-validation="ValidationCondition" field-name="Sub Category" />
			       </span>
		      </div>
              <div class="div50">
			      <label class="labl">Description</label>
			       <span>
			      <textarea  id="subCategoryDescriptionREPLACEROWID" name="subCategoryDescriptionREPLACEROWID" placeholder="Sub Category Description" rows="1"  field-name="Sub Category Description" ></textarea>
			       </span>
			        <i class="glyphicon glyphicon-trash deme" onclick="removeSubCategory(REPLACEROWID);"></i>
	           </div>
	       </div>
	    </div>
			     
    </div>
  </div>
    
    <div class="clearfix"></div>
  </div>
  <div class="form-footer clearfix">
        <div class="col-lg-12">
          <div class="pull-right">
            <input type="button" value="Add" onclick="addCategoryAndSubCat();" id="addCategory" class="btn btn-primary"> 
			<input type="button" value="Cancel" onClick="javascript:location.href = '<c:url value='/category'/>'" class="btn btn-default ml">
          </div>
        </div>
      </div>
      </form:form>
      </div>
  </section>

<jsp:include page="../masterFooter.jsp" />

<script type="text/javascript">
function addSub(){
   var subDivLength=$("#subDivLength").val();
   var maxRowNum = parseInt(subDivLength)+1;
   var divDsStr = document.getElementById("divds").innerHTML;
   divDsStr = divDsStr.replace(/REPLACEROWID/g, (maxRowNum));
   divDsStr = divDsStr.replace(/ValidationCondition/g,"required");
   $("#addSub").append(divDsStr);
   $("#subDivLength").val(maxRowNum);
}

</script>


<script type="text/javascript">
function addCategoryAndSubCat(){
	   var subDivLength=$("#subDivLength").val();
	var validationSettings = {
			errorMessagePosition : 'element',
			borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd'
		};
	
	if ($('#addCategory_form').validate(false, validationSettings)) {
		var frm = $('#addCategory_form').serializeFormJSON();
		var con = JSON.stringify(frm);
		con = con.replace(/[{}]/g, "");
		  //It will escape all the special characters
		var jsonData = encodeURIComponent(con);
		var operoxUrl = '${operoxUrl}';
		$("#addCategory").attr("disabled", "disabled");
		$("#addCategory").addClass('button').val('Processing..');

		$.ajax({
					type : "POST",
					url : operoxUrl+ "/saveCategory?${_csrf.parameterName}=${_csrf.token}&json="+ jsonData+"&subDivLength="+subDivLength,
					success : function(result) {
						if (result == 'success') {
							location.replace(operoxUrl
									+ "/category");
						}

					},
				});
		return true;
	} else {
		return false;
	}
}
$('body').on('blur', '#addCategory_form', function() {
	$("#addCategory_form").showHelpOnFocus();
	$("#addCategory_form").validateOnBlur(false, validationSettings);
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
function validateCategory(){
	var categoryName = $("#categoryName").val();
	var operoxUrl ='${operoxUrl}';
	$('#categoryDiv').find('p.jquery_form_error_message').remove();
	if (categoryName != null && categoryName != '') {
		 $("#addCategory").attr("disabled", "disabled");
		$.ajax({
			type : "POST",
			url : operoxUrl + "/validateCategory?${_csrf.parameterName}=${_csrf.token}&categoryName="+categoryName,
			success : function(data) {
				var result = data;
				if (result == false) {
					$('#categoryDiv').find('p.jquery_form_error_message').remove();
				    $("input#categoryName").after("<p class='jquery_form_error_message'>"+categoryName+" is Already Existed</span>");
				    document.getElementById("categoryName").setAttribute('record-exist','yes');
				    document.getElementById("categoryName").setAttribute('record-exist-errorMsg',categoryName+' is Already Existed');
					
				} else {
					//New WebSite class="form-control"
					$('input#categoryName').removeAttr("class record-exist record-exist-errormsg");
					$('input#categoryName').attr('class','valid form-control');
					$('#categoryDiv').find('p.jquery_form_error_message').remove();
					 $("#addCategory" ).addClass('btn btn-primary').val('Add');
                     $("#addCategory").removeAttr("disabled");
				}
			},

		});
			$("#addCategory_form").click(function() {
		if ($(this).validate(false, validationSettings)) {
		}
	}); 
	}
}
</script>

<script type="text/javascript">
function removeSubCategory(divId){
	$("#subDiv"+divId).empty();
}
</script>