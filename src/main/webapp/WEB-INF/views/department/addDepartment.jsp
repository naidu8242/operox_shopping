<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>


<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
       <h2><c:if test="${empty department.id}">Add Department</c:if> <c:if test="${!empty department.id}">Edit Department</c:if></h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; <a href="${operoxUrl}/departmentList">Departments List</a> &raquo; <c:if test="${empty department.id}">Add Department</c:if> <c:if test="${!empty department.id}">Edit Department</c:if></div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix">
  <form id="Department_Form" name="Department_Form" method="post">  
  <input type="hidden" id="deptId" name="deptId" value="${department.id}">
    <div class="div50" id = "departmentIdDiv">
       <label class="labl" >Department Id</label>
       <span>
       <input type="text" value="${department.departmentId}" name = "departmentId" id= "departmentId" data-validation-optional="true" data-validation="validate_Space validate_Alphanumeric validate_length length2-100" onblur="validateDepartmentId();" onmouseout="validateDepartmentId();" placeholder="Department Id">
       </span>
    </div>
    <div class="div50" id = "departmentNameDiv">
       <label class="labl">Department Name <b class="text-danger"> *</b></label>
       <span>
       <input type="text" value="${department.departmentName}" name = "departmentName" id= "departmentName"  data-validation="required validate_Space validate_AlphaNumber validate_length length2-100" field-name="Department Name" onblur="validateDepartmentName();" onmouseout="validateDepartmentName();" placeholder="Department Name">
       </span>
    </div>    
    <div class="div50">
       <label class="labl">Department Description</label>
       <span>
       <textarea placeholder="Department Description"  value=" " name = "description" data-validation-optional="true" data-validation="validate_Space validate_length length5-500" id= "description" rows="3">${department.description}</textarea>
       </span>
    </div>
    <div class="clearfix"></div>
 
      <!-- Content Area inner -->
 
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right">
          
          <c:if test="${empty department.id}">
                    <input type="button" onclick = "storeDepartment();" value="Add" class="btn btn-primary" id="save">
	         </c:if>
	          <c:if test="${!empty department.id}">
                    <input type="button" onclick = "storeDepartment();" value="Update" class="btn btn-primary" id="save">
	         </c:if>
          
          <input type="button" value="Cancel"  onclick = "javascript:location.href = '<c:url value='/departmentList'/>'" class="btn btn-default ml10">
          
        </div>
      </div>
    </div>
     </form>
     </div>
</section>
<!-- Content Area Ends--> 

<!-- Footer Area -->
<jsp:include page="../masterFooter.jsp" />
<!-- Footer Area --> 

<script type="text/javascript">
function addSub(){
  if ($("#addSub").prop('checked') ) {
		$(".addSub").show();
} 
else {
	$(".addSub").hide();
}
};
</script>

<!-- <script type="text/javascript">
function cancel(){
	var operoxUrl = '${operoxUrl}';
	location.replace(operoxUrl+"/departmentList"); 
}
</script> -->

<%-- 
     @author : Ajith Matta.
     this script is to Store/Update the  Department.
     1.usage     :  used in storeDepartment function
     2.parameter : jsonData,csrf.token
     3.ajax url implementation in DepartmentRestController.
--%>
<script>
function storeDepartment(){
	if ($('#Department_Form').validate(false,validationSettings)) {
			var frm = $('#Department_Form').serializeFormJSON();
			var con = JSON.stringify(frm);
			con = con.replace(/[{}]/g, "");
			var jsonData = encodeURIComponent(con);
			var operoxUrl = '${operoxUrl}';
			$("#save").attr("disabled", "disabled");
			$( "#save" ).addClass('button').val('Processing..');
				$.ajax({
						type : "POST",
						url : operoxUrl
								+ "/storeDepartmentById?${_csrf.parameterName}=${_csrf.token}&json="+ jsonData,
								 
						success : function(data) {
				        	if((data == "departmentList")){
				        		location.replace(operoxUrl+"/departmentList");  
				        	}
				        },
					});
			return true;
		} else {
			return false;
		}
	}
$('body').on('blur', '#Department_Form', function() {
	$("#Department_Form").showHelpOnFocus();
	$("#Department_Form").validateOnBlur(false, validationSettings);  
});
	
</script>


<script type="text/javascript">
 function validateDepartmentName(){
	 var departmentName = document.getElementById("departmentName").value;
	 var id = document.getElementById("deptId").value;
	 $('#departmentNameDiv').find('p.jquery_form_error_message').remove();
		var operoxUrl ='${operoxUrl}';
		if(departmentName != null && departmentName != ""){
			 $("#save").attr("disabled", "disabled");
			$.ajax({
			   	type: "POST",
			    url: operoxUrl+"/validateDepartmentNameAndId?${_csrf.parameterName}=${_csrf.token}&departmentName="+departmentName+"&id="+id, 	   
				    success: function(data) {
				    	if(data == true){
				    		$('#departmentNameDiv').find('p.jquery_form_error_message').remove();
							  $("input#departmentName").after("<p class='jquery_form_error_message'>"+departmentName+" is Already Existed</span>");
							  document.getElementById("departmentName").setAttribute('record-exist','yes');
							  document.getElementById("departmentName").setAttribute('record-exist-errorMsg',departmentName+' is Already Existed'); 
							  $("#save").attr("disabled", "disabled");
				    	}
			        	else{
			        		 $('input#departmentName').removeAttr( "record-exist record-exist-errormsg");
			        		 $('#departmentNameDiv').find('p.jquery_form_error_message').remove(); 
			        		 if(id != null && id != "" && id != "undefined"){
			     				$("#save").addClass('btn btn-primary').val('Update');
			     			}else{
			     				$("#save").addClass('btn btn-primary').val('Add');
			     			}
	                         $("#save").removeAttr("disabled");
			        	} 
				    }
		     });
		}else{
			$('input#departmentName').removeAttr( "record-exist record-exist-errormsg");
			$('#departmentNameDiv').find('p.jquery_form_error_message').remove(); 
			if(id != null && id != "" && id != "undefined"){
				$("#save").addClass('btn btn-primary').val('Update');
			}else{
				$("#save" ).addClass('btn btn-primary').val('Add');
			}
	        $("#save").removeAttr("disabled");
		}
 }
</script> 
<script type="text/javascript">
 function validateDepartmentId(){
	 var departmentId = document.getElementById("departmentId").value;
	 var id = document.getElementById("deptId").value;
	 $('#departmentIdDiv').find('p.jquery_form_error_message').remove();
		var operoxUrl ='${operoxUrl}';
		if(departmentId != null && departmentId != ""){
			 $("#save").attr("disabled", "disabled");
			$.ajax({
			   	type: "POST",
			    url: operoxUrl+"/validateDepartmentNameAndId?${_csrf.parameterName}=${_csrf.token}&departmentId="+departmentId+"&id="+id, 	   
				    success: function(data) {
				    	if(data == true){
				    		$('#departmentIdDiv').find('p.jquery_form_error_message').remove();
							  $("input#departmentId").after("<p class='jquery_form_error_message'>"+departmentId+" is Already Existed</span>");
							  document.getElementById("departmentId").setAttribute('record-exist','yes');
							  document.getElementById("departmentId").setAttribute('record-exist-errorMsg',departmentId+' is Already Existed'); 
			        	}
			        	else{
			        		 $('input#departmentId').removeAttr( "record-exist record-exist-errormsg");
			        		 $('#departmentIdDiv').find('p.jquery_form_error_message').remove(); 
			        		 if(id != null && id != "" && id != "undefined"){
			     				$("#save").addClass('btn btn-primary').val('Update');
			     			}else{
			     				$("#save").addClass('btn btn-primary').val('Add');
			     			}
	                         $("#save").removeAttr("disabled");
			        	} 
				    }
		     });
		}else{
			$('input#departmentId').removeAttr( "record-exist record-exist-errormsg");
			$('#departmentIdDiv').find('p.jquery_form_error_message').remove(); 
			if(id != null && id != "" && id != "undefined"){
				$("#save").addClass('btn btn-primary').val('Update');
			}else{
				$("#save" ).addClass('btn btn-primary').val('Add');
			}
	        $("#save").removeAttr("disabled");
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

<script type="text/javascript">
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };

        
</script> 
