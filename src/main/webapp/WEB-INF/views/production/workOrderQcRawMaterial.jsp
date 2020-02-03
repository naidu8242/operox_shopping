<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
   
<script>
function getQcRawMaterialList(id,type){
	var result = "";
	result = result +'<div class="qcqarawmaterial-list" id="qcqarawmaterial-list">';
	result = result +'<div class="data_table extra-table"> ';  
    result = result +'<p class="ml mt10">';
    result = result +'<strong>QC/QA Rawmaterial list</strong>';
    result = result +'<button type="button" title="Execute" class="btn btn-primary btn-sm pull-right mr" onClick="getQcExecution('+id+',\''+type+'\');">Execute</button>';
	result = result +'</p>  ';
	result = result +'<div id="qcqaRawList"> </div>';
	result = result +'</div>';
	result = result +'</div>';
	result = result +'<div id="qcExecution"> </div>';
	result = result +'<div id="qcViewDiv" class="qcqarawmaterial-list"> </div>';
	$("#qcRawMaterial").empty();  
	$("#qcRawMaterial").append(result);  
	getQcQaRawList(id,type);
}
</script>   

<script>
function getQcQaRawList(workOrderId,type){
	var operoxUrl = '${operoxUrl}';
	$.ajax({
	type : "GET",
	url : operoxUrl+ "/getQcQaRawList?&workOrderId="+workOrderId+"&type="+type,
	success : function(data) {
		var json = JSON.parse(data);
        	var result = "";
        	result = result + '<table id="qcqaRawListPagination" class="display" cellspacing="0" width="100%">';
        	result = result + '<thead>';		
        	result = result + '<tr>';
        	result = result + '<th>Sl.No</th>';
        	if(type=='RawMaterial'){
        		result = result + '<th>Raw Material</th>';
        	}
        	if(type=='Products'){
        		result = result + '<th>Products</th>';
        	}
        	result = result + ' <th>Checklist</th>';
        	result = result + '<th>Total Quantity</th>';
        	result = result + '<th>Passed Units</th>';
        	result = result + '<th>Failed Units</th>';
        	result = result + '<th>Status</th>';
        	result = result + '<th class="action">Action</th>';
        	result = result + ' </tr>';
        	result = result + '</thead>';
			result = result + '<tbody>';
        	var j=0;
        	for (var i=0; i<json.length; i++)
			{  
        		j++
        		var report = json[i];
        		result = result + '<tr>';
   	        	result = result + '<td><div class="ta-m">'+j+'</div></td>';
   	        	
   	        	if(type=='RawMaterial'){
   	        		if(report.rawMaterialName != null && report.rawMaterialName != '' && report.rawMaterialName != 'undefined'){
   	   	        		result = result + '<td><div class="ta-sm" onClick="viewQcExecution('+report.id+','+workOrderId+',\''+type+'\');">'+report.rawMaterialName+'</div></td>';
   	   	        	}else{
   	   	        		result = result + '<td><div class="ta-m">--</div></td>';
   	   	        	}
   	        	}
   	        	
   	        	if(type=='Products'){
   	        		if(report.productName != null && report.productName != '' && report.productName != 'undefined'){
   	   	        		result = result + '<td><div class="ta-sm" onClick="viewQcExecution('+report.id+','+workOrderId+',\''+type+'\');">'+report.productName+'</div></td>';
   	   	        	}else{
   	   	        		result = result + '<td><div class="ta-m">--</div></td>';
   	   	        	}
   	        	}
   	        	
   	        	if(report.qcCheckListName != null && report.qcCheckListName != '' && report.qcCheckListName != 'undefined'){
   	        		result = result + '<td><div class="ta-sm">'+report.qcCheckListName+'</div></td>';
   	        	}else{
   	        		result = result + '<td><div class="ta-sm">--</div></td>';
   	        	}
   	        	
   	        	if(report.totalQuantity != null && report.totalQuantity != '' && report.totalQuantity != 'undefined'){
   	        		result = result + '<td><div class="ta-sm">'+report.totalQuantity+'</div></td>';
   	        	}else{
   	        		result = result + '<td><div class="ta-sm">--</div></td>';
   	        	}
   	        	
   	        	if(report.noOfPassedUnits != null && report.noOfPassedUnits != '' && report.noOfPassedUnits != 'undefined'){
   	        		result = result + '<td><div class="ta-sm">'+report.noOfPassedUnits+'</div></td>';
   	        	}else{
   	        		result = result + '<td><div class="ta-sm">--</div></td>';
   	        	}
   	        	
   	        	if(report.noOfFailedUnits != null && report.noOfFailedUnits != '' && report.noOfFailedUnits != 'undefined'){
   	        		result = result + '<td><div class="ta-sm">'+report.noOfFailedUnits+'</div></td>';
   	        	}else{
   	        		result = result + '<td><div class="ta-sm">--</div></td>';
   	        	}
   	        	
   	        	if(report.qcStatus != null && report.qcStatus != '' && report.qcStatus != 'undefined'){
   	        		result = result + '<td><div class="ta-sm"><span class="status_process">'+report.qcStatus+'</span></div></td>';
   	        	}else{
   	        		result = result + '<td><div class="ta-sm">--</div></td>';
   	        	}
   	        	
   	        	result = result + '<td class="action"><div>';
   	        	result = result + '<i class="fa fa-pencil" onClick="editQcExecution('+report.id+','+workOrderId+',\''+type+'\');"></i><i class="glyphicon glyphicon-trash deme" onclick="deleteQcExecution('+report.id+','+workOrderId+');"></i></div>';
   	        	result = result + '</td>'; 
   	        	result = result + '</tr>';
	        	} 
				
	        	result = result +'</tbody>';
	        	result = result +'</table>';
//	        	result = result +'</div>';
	            $("#qcqaRawList").empty();  
	    	    $("#qcqaRawList").append(result);
	},
});
}
</script>

<script>
function deleteQcExecution(qcExecutionId,workOrderId){
	var operoxUrl = '${operoxUrl}';
	var message = confirm("Do you want to delete this Qc RawMaterial");
	if (message == true) {
		$.ajax({
			type : "GET",
			url : operoxUrl+ "/deleteQcExecution?&qcExecutionId="+qcExecutionId,
			success : function(data) {
				getQcQaRawList(workOrderId,type);
			},
		}); 
	}
}
</script>

<script>
function getQcExecution(id,type){
	var result = "";
	result = result + '<div class="clearfix qcqarawmt-execute" id="qcqarawmt-execute" >';
	result = result + '<form id="addCheckList_form" name="addCheckList_form" method="post">';
	if(type=='RawMaterial'){
	result = result + '<div class="div4">';
	result = result +'<label class="labl">Raw Material&nbsp;<b class="text-danger">*</b></label>';
	result = result + ' <select class="selList" id="materialId" name="materialId" onchange="getCheckList();" field-name="Raw Material" data-validation="required">';
	result = result + '<option value="">--Select Material--</option>';
	result = result + '<c:forEach var="material" items="${rawMaterialsList}">';
	result = result + '<option value="${material.id}" ${material.id  == opt.rawMaterial.id ? "selected" : ''}>${material.materialName}</option>';
	result = result + '</c:forEach>';
	result = result + '</select>';
	result = result + ' </div>	';
	}
	if(type=='Products'){
		result = result +'<div class="div4">';
		result = result +'<label class="labl">Product&nbsp;<b class="text-danger">*</b></label>';
		result = result +'<span>';
		result = result + ' <select class="selList" id="productId" name="productId" onchange="getCheckListForProductId();" field-name="Product" data-validation="required">';
		result = result + '<option value="">--Select Product--</option>';
		result = result + '<c:forEach var="product" items="${productList}">';
		result = result + '<option value="${product.id}" ${product.id  == opt.product.id ? "selected" : ''}>${product.productName}</option>';
		result = result + '</c:forEach>';
		result = result + '</select>';
		result = result +'</span> </div>';
	}
	  result = result + '<input type="hidden" name="workOrderId" id="workOrderId" value="'+id+'" >';
	  result = result + ' <input type="hidden" id="executionType" name="executionType" value="'+type+'"/>';
	result = result +'<div class="div4">';
	result = result +'<label class="labl">Checklist&nbsp;<b class="text-danger">*</b></label>';
	result = result +'<span>';
	result = result +'<select class="chosen-select" id ="checkList" name ="checkList" onchange="getTestList();" field-name="CheckList" data-validation="required">';
	result = result +' <option selected disabled>--Select CheckList--</option>';
	result = result +'</select>';
	result = result +'</span> </div>';
	
	result = result +'<div class="div4">';
	result = result +'<label class="labl">Start Date</label>';
	result = result +'<span>';
	result = result +'<input type="text" class="datefild" id="startDate" name="startDate" placeholder="MM/DD/YYYY">';
	result = result +'</span> </div>';
	
	result = result +'<div class="div4">';
	result = result +'<label class="labl">End Date</label>';
	result = result +'<span>';
	result = result +'<input type="text" class="datefild" id="endDate" name="endDate" placeholder="MM/DD/YYYY">';
	result = result +'</span> </div>';
	
	result = result +'<div class="div4">';
	result = result +'<label class="labl">Total Quantity&nbsp;<b class="text-danger">*</b></label>';
	result = result +'<span>';
	result = result +'<input type="text" placeholder="Total Quantity" id="totalQuantity" name="totalQuantity" field-name="Total Quantity" data-validation="required validate_int">';
	result = result +'</span> </div>';
	
	result = result +'<div class="div4">';
	result = result +'<label class="labl">Status&nbsp;<b class="text-danger">*</b></label>';
	result = result +'<span>';
	result = result +'<select id="status" name="status" field-name="Status" data-validation="required">';
	result = result +'  <option selected disabled>--Select Status--</option>';
	result = result +'  <option>New</option>';
	result = result +'  <option>Inprogress</option>';
	result = result +' <option>Completed</option>';
	result = result +'</select>';
	result = result +'</span> </div>';
	
	result = result +'<div class="div4">';
	result = result +'<label class="labl">No.of passed Units</label>';
	result = result +'<span>';
	result = result +'<input type="text" placeholder="No.of passed Units" id="noOfPassedUnits" name="noOfPassedUnits" data-validation="validate_int" data-validation-optional="true">';
	result = result +'</span> </div>';
    
	result = result +'<div class="div4">';
	result = result +'<label class="labl">No. of failed units</label>';
	result = result +'<span>';
	result = result +'<input type="text" placeholder="No. of failed units" id="noOfFailedUnits" name="noOfFailedUnits" data-validation="validate_int" data-validation-optional="true">';
	result = result +'</span> </div>';
    
	result = result +'<div class="clearfix"></div>';
	
	result = result +'<div class="data_table position-rel">';
	result = result +'<table>';
	result = result +'  <thead>';
	result = result +'    <tr>';
	result = result +'      <th>S.No.</th>';
	result = result +'      <th>Test&nbsp;<b class="text-danger">*</b></th>';
	result = result +'      <th>Procedure</th>';
	result = result +'      <th>Value</th>';
	result = result +'      <th>Actual Value</th>';
	result = result +'      <th>Result</th>';
	result = result +'      <th>Remarks</th>';
	result = result +'    </tr>';
	result = result +'  </thead>';
	result = result +'  <tbody id="testDiv">';
	
	result = result +'   </tr>';
	result = result +' </tbody>';
	result = result +'</table>';
	result = result +'</div>';
	
	result = result +'<div class="clearfix mt">';
	result = result +'<div class="div4" id="uploadFileGroup">';
	result = result +'<label class="labl">Attachment</label>';
	result = result +'<span class="custom-upload">';
	result = result +'     <input type="file" name="uploadBtn" id="uploadBtn" onclick = "uploadFileValue();" >';
	result = result +'     <div class="fake-file">';
	result = result +'        <input  type="text" class="form-control" readonly id="uploadFile" name="docFile"  field-name="Document"  placeholder="Upload File..." >';
	result = result +'     </div>';
	result = result +' </span>';
	result = result +'</div>';
    
    result = result +'<div class="div4">';
    result = result +'<label class="labl">Comment</label>';
    result = result +'<span><textarea rows="2" placeholder="Comment" id="#" name="description"></textarea></span>';
    result = result +'</div>';
    result = result +'</div>';
     result = result +'<div class="clearfix"></div>';
    result = result +'<div class="form-footer mt">';
    result = result +'<div class="col-lg-12">';
    result = result +' <div class="pull-right custom-buttons"> <a href="#" class="btn btn-primary" id ="saveExecutionValue" onClick="saveExecute('+id+',\''+type+'\')"> Save </a> <a href="#" id="can" class="btn btn-default" onClick="cancelExecution('+id+',\''+type+'\');"> Cancel </a> </div>';
    result = result +'</div>';
    result = result +'</div>';
    result = result +'<div class="clearfix"></div>';
     result = result +'</form>'; 
    result = result +'</div>';
    document.getElementById("qcqarawmaterial-list").style.display="none";
    $("#qcqaRawList").empty();  
    $("#qcExecution").empty();  
    $("#qcExecution").append(result);
    $( "#startDate" ).datepicker();
    $( "#endDate" ).datepicker();
}

</script>

<script type="text/javascript">
window.onload = function () {
 };
</script>

<script type="text/javascript">
function cancelExecution(id,type){
	getQcRawMaterialList(id,type);
}
</script>

<script>
function editQcExecution(executionId,id,type){
	var operoxUrl = '${operoxUrl}';
	$.ajax({
	type : "GET",
	url : operoxUrl+ "/editExecution?&executionId="+executionId,
	success : function(data) {
		var json = JSON.parse(data);
		var selected ="";
			var result = "";
			result = result + '<div class="clearfix qcqarawmt-execute" id="qcqarawmt-execute" >';
			result = result + '<form id="addCheckList_form" name="addCheckList_form" method="post">';
			if(type=='RawMaterial'){
			result = result + '<div class="div4">';
			result = result +'<label class="labl">Raw Material&nbsp; <b class="text-danger">*</b></label>';
			result = result + ' <select class="selList" id="materialId" name="materialId" onchange="getCheckList();">';
			result = result + '<option value="">--Select Material--</option>';
			result = result + '<c:forEach var="material" items="${rawMaterialsList}">';
                var selected = '';
               if(json.rawMaterial.id != null && json.rawMaterial.id != "" && json.rawMaterial.id != 'undefined' && json.rawMaterial.id == "${material.id}"){
                  selected = 'selected';
              }
             result = result+"<option value='${material.id}'"+selected+">${material.materialName}</option>";
			result = result + '</c:forEach>';
			result = result + '</select>';
			result = result + ' </div>	';
			}
			if(type=='Products'){
				result = result +'<div class="div4">';
				result = result +'<label class="labl">Product&nbsp;<b class="text-danger">*</b></label>';
				result = result +'<span>';
				result = result + ' <select class="selList" id="productId" name="productId" onchange="getCheckListForProductId();">';
				result = result + '<option value="">--Select Product--</option>';
				result = result + '<c:forEach var="product" items="${productList}">';
				result = result + '<option value="${product.id}" ${product.id  == opt.product.id ? "selected" : ''}>${product.productName}</option>';
				result = result + '</c:forEach>';
				result = result + '</select>';
				result = result +'</span> </div>';
			}
			result = result + '<input type="hidden" name="executionId" id="executionId" value="'+executionId+'" >';
			  result = result + '<input type="hidden" name="workOrderId" id="workOrderId" value="'+id+'" >';
			  result = result + ' <input type="hidden" id="executionType" name="executionType" value="'+type+'"/>';
			result = result +'<div class="div4">';
			result = result +'<label class="labl">Checklist&nbsp;<b class="text-danger">*</b></label>';
			result = result +'<span>';
			result = result +'<select class="chosen-select" id ="checkList" name ="checkList" onchange="getTestList();">';
			result = result +' <option selected value='+json.qcCheckList.id+'>'+json.qcCheckList.checkName+'</option>';
			result = result +'</select>';
			result = result +'</span> </div>';
			
			result = result +'<div class="div4">';
			result = result +'<label class="labl">Start Date</label>';
			result = result +'<span>';
			result = result +'<input type="text" class="datefild" id="startDate" name="startDate" placeholder="MM/DD/YYYY" value="'+json.startDateStr+'">';
			result = result +'</span> </div>';
			
			result = result +'<div class="div4">';
			result = result +'<label class="labl">End Date</label>';
			result = result +'<span>';
			result = result +'<input type="text" class="datefild" id="endDate" name="endDate" placeholder="MM/DD/YYYY" value="'+json.endDateStr+'">';
			result = result +'</span> </div>';
			
			result = result +'<div class="div4">';
			result = result +'<label class="labl">Total Quantity&nbsp;<b class="text-danger">*</b></label>';
			result = result +'<span>';
			result = result +'<input type="text" placeholder="Total Quantity" id="totalQuantity" name="totalQuantity" value="'+json.totalQuantity+'">';
			result = result +'</span> </div>';
			
			result = result +'<div class="div4">';
			result = result +'<label class="labl">Status&nbsp;<b class="text-danger">*</b></label>';
			result = result +'<span>';
			result = result +'<select id="status" name="status">';
			if(json.qcStatus == "New"){
				result = result +'  <option selected >'+json.qcStatus+'</option>';
				result = result +'  <option>Inprogress</option>';
				result = result +' <option>Completed</option>'; 
			}
			if(json.qcStatus == "Inprogress"){
				result = result +'  <option selected >'+json.qcStatus+'</option>';
				result = result +'  <option>New</option>';
				result = result +' <option>Completed</option>'; 
			}
			if(json.qcStatus == "Completed"){
				result = result +'  <option selected >'+json.qcStatus+'</option>';
				result = result +'  <option>New</option>';
				result = result +' <option>Inprogress</option>'; 
			}
			result = result +'</select>';
			result = result +'</span> </div>';
			
			result = result +'<div class="div4">';
			result = result +'<label class="labl">No.of passed Units</label>';
			result = result +'<span>';
			 if(json.noOfPassedUnits != null && json.noOfPassedUnits != "" && json.noOfPassedUnits != 'undefined'){
				result = result +'<input type="text" placeholder="No.of passed Units" id="noOfPassedUnits" name="noOfPassedUnits" value="'+json.noOfPassedUnits+'">';
			 }
			 else{
				 result = result +'<input type="text" placeholder="No.of passed Units" id="noOfPassedUnits" name="noOfPassedUnits" value="">';
			 }
			result = result +'</span> </div>';
		    
			result = result +'<div class="div4">';
			result = result +'<label class="labl">No. of failed units</label>';
			result = result +'<span>';
			if(json.noOfFailedUnits != null && json.noOfFailedUnits != "" && json.noOfFailedUnits != 'undefined'){
				result = result +'<input type="text" placeholder="No. of failed units" id="noOfFailedUnits" name="noOfFailedUnits" value="'+json.noOfFailedUnits+'">';
			}
			else{
				result = result +'<input type="text" placeholder="No. of failed units" id="noOfFailedUnits" name="noOfFailedUnits" value="">';
			 }
			result = result +'</span> </div>';
		    
			result = result +'<div class="clearfix"></div>';
			
			result = result +'<div class="data_table position-rel">';
			result = result +'<table>';
			result = result +'  <thead>';
			result = result +'    <tr>';
			result = result +'      <th>S.No.</th>';
			result = result +'      <th>Test&nbsp;<b class="text-danger">*</b></th>';
			result = result +'      <th>Procedure</th>';
			result = result +'      <th>Value</th>';
			result = result +'      <th>Actual Value</th>';
			result = result +'      <th>Result</th>';
			result = result +'      <th>Remarks</th>';
			result = result +'    </tr>';
			result = result +'  </thead>';
			result = result +'  <tbody id="testDiv">';
			
			result = result +'   </tr>';
			result = result +' </tbody>';
			result = result +'</table>';
			result = result +'</div>';
			
			 result = result +'<div class="div33" id="uploadFileGroup">';
			result = result +'<label class="labl">Attachment</label>';
			result = result +'<span class="custom-upload">';
			result = result +'     <input type="file" name="uploadBtn" id="uploadBtn" onclick = "uploadFileValue();" >';
			result = result +'     <div class="fake-file">';
			if(json.fileName != null && json.fileName != "" && json.fileName != 'undefined'){
				result = result +'  <input  type="text" class="form-control" readonly id="uploadFile" name="docFile"  field-name="Document" value="'+json.fileName+'">';
			}
			else{
				result = result +'  <input  type="text" class="form-control" readonly id="uploadFile" name="docFile"  field-name="Document" placeholder="Upload File..." >';				
			}
			result = result +'     </div>';
			result = result +' </span>';
			result = result +'</div>';
		    
		    result = result +'<div class="div4">';
		    result = result +'<label class="labl">Comment</label>';
		    result = result +'<span><textarea rows="2" placeholder="Comment" id="#" name="description"value="">'+json.comment+'</textarea></span>';
		    result = result +'</div>';
		    
		     result = result +'<div class="clearfix"></div>';
		    result = result +'<div class="form-footer mt">';
		    result = result +'<div class="col-lg-12">';
		    result = result +' <div class="pull-right custom-buttons"> <a href="#" class="btn btn-primary" id ="saveExecutionValue" onClick="saveExecute('+id+',\''+type+'\')"> Save </a> <a href="#" id="can" class="btn btn-default" onClick="cancelExecution('+id+',\''+type+'\');"> Cancel </a> </div>';
		    result = result +'</div>';
		    result = result +'</div>';
		    result = result +'<div class="clearfix"></div>';
		     result = result +'</form>'; 
		    result = result +'</div>';
		    document.getElementById("qcqarawmaterial-list").style.display="none";
		    $("#qcqaRawList").empty();  
		    $("#qcExecution").empty();  
		    $("#qcExecution").append(result);
		    $( "#startDate" ).datepicker();
		    $( "#endDate" ).datepicker();
		    getEditTestList();

		},
	});
}

</script>

<script type="text/javascript">
function uploadFileValue(){
 var selectedFile = document.getElementById("uploadBtn");
	    selectedFile.addEventListener("change", function() {
	      var docSize = this.files[0].size;
	      $("#uploadFileGroup").find('p.jquery_form_error_message').remove();
	      if(docSize > 2097152){
	    	  document.getElementById("uploadFile").value = '';
	    	  $("#uploadFileGroup").append("<p class='jquery_form_error_message'>Limit is exceeded than 2MB </p>");
	      }
	    });
	    document.getElementById("uploadBtn").onchange = function () {
		    document.getElementById("uploadFile").value = this.value;
		    };
		    $('.custom-upload input[type=file]').change(function(){
		    	   $(this).next().find('input').val($(this).val());
		    	});
}
</script>

<script type="text/javascript">
 function getCheckList() {
	 var materialId = document.getElementById("materialId").value; 
	 var operoxUrl ='${operoxUrl}';
	 if(materialId != null && materialId != "" && materialId != 'undefined'){
		 $.ajax({
		    	type: "POST",
		        url: operoxUrl+"/getCheckListByMaterialId?${_csrf.parameterName}=${_csrf.token}&materialId="+materialId, 
		        success: function(data) {
		        	var json = JSON.parse(data);
		        	var output = "";
		        	output = output + "<option value=''>--Select CheckList---</option>"; 
		        	for (var i=0; i<json.length; i++)
					{
			        		var opt = json[i];
			        		output = output + "<option value='" +opt.id+ "'>"+opt.checkName+"</option>";
					}
		        	$("#checkList").empty();
			        $("#checkList").append(output);
		        },
		    });
	 }else{
		 var output = "";
     	 output = output + "<option value=''>-- CheckList --</option>"; 
     	 $("#checkList").empty();
	     $("#checkList").append(output);
	 }
	
 }
</script> 
<script type="text/javascript">
 function getTestList() {
	 
	var checkList = document.getElementById("checkList").value;
	var operoxUrl ='${operoxUrl}';
	
	 if(checkList != null && checkList != "" && checkList != 'undefined'){
		 $.ajax({
		    	type: "POST",
		        url: operoxUrl+"/getTestListByCheckListId?${_csrf.parameterName}=${_csrf.token}&checkList="+checkList, 
		        success: function(data) {
		        	var json = JSON.parse(data);
		        	var result="";
		        	for (var i=0; i<json.length; i++)
					{
			        var opt = json[i];
			        result = result +'      <tr id="trDiv'+opt.id+'">';
		        	result = result +'      <td><div class="ta-sm">'+(i+1)+'</div></td>';
		        	result = result +'      <td><div class="ta-sm">'+opt.whatToCheck+'</div></td>';
		        	result = result +'     <td><div class="ta-sm">'+opt.howToCheck+'</div></td>';
		        	result = result +'      <td><div class="ta-sm">'+opt.testDescription+'</div></td>';
		          	result = result + '<input type="hidden" id="checkReportId'+(i+1)+'" name="checkReportId'+(i+1)+'" value="'+opt.id+'"/>';
		        	result = result +'     <td><div class="ta-sm">';
		        	result = result +'         <input type="text" placeholder="Actual Value" id="actualValue'+(i+1)+'" name="actualValue'+(i+1)+'">';
		        	result = result +'        </div></td>';
		        	result = result +'     <td><div class="ta-sm">';
		        	//result = result +'          <input type="text" placeholder="Result" id="result'+(i+1)+'" name="result'+(i+1)+'">';
			        result = result +'<select id="result'+(i+1)+'" name="result'+(i+1)+'">';
						result = result +'  <option selected disabled>-Select Result-</option>';
						result = result +'  <option>Pass</option>';
						result = result +'  <option>Fail</option>';
					result = result +'</select>';
		        	result = result +'        </div></td>';
		        	result = result +'     <td><div class="ta-sl">';
		        	result = result +'         <input type="text" placeholder="Remarks" id="remarks'+(i+1)+'" name="remarks'+(i+1)+'">';
		        	result = result +'       </div></td>';
		        	result = result +'      </tr>';
					}
		        	 result = result + '<input type="hidden" id="maxValue" name="maxValue" value="'+json.length+'"/>'; 
		        	$("#testDiv").empty();
			        $("#testDiv").append(result);
		        },
		    });
	 }else{
		 var output = "";
     	 output = output + "<option value=''>-- CheckList --</option>"; 
     	 $("#testDiv").empty();
	     $("#testDiv").append(output);
	 }
	 
	 
	 
	 $("#testDiv").empty();
     $("#testDiv").append("added");
 }
 </script> 
 
 <script type="text/javascript">
 function getEditTestList() {
	var executionId = document.getElementById("executionId").value; 
	var checkList = document.getElementById("checkList").value;
	var operoxUrl ='${operoxUrl}';
	 if(checkList != null && checkList != "" && checkList != 'undefined'){
		 $.ajax({
		    	type: "POST",
		        url: operoxUrl+"/getTestListByExecutionId?${_csrf.parameterName}=${_csrf.token}&executionId="+executionId+"&checkList="+checkList, 
		        success: function(data) {
		        	var json = JSON.parse(data);
		        	var result="";
		        	for (var i=0; i<json.length; i++)
					{
			        var opt = json[i];
			        result = result +'      <tr id="trDiv'+opt.id+'">';
		        	result = result +'      <td><div class="ta-sm">'+(i+1)+'</div></td>';
		        	result = result +'      <td><div class="ta-sm">'+opt.whatToCheck+'</div></td>';
		        	result = result +'     <td><div class="ta-sm">'+opt.howToCheck+'</div></td>';
		        	result = result +'      <td><div class="ta-sm">'+opt.testDescription+'</div></td>';
		          	result = result + '<input type="hidden" id="checkReportId'+(i+1)+'" name="checkReportId'+(i+1)+'" value="'+opt.qcCheckListReport.id+'"/>';
		        	result = result +'     <td><div class="ta-sm">';
		        	result = result +'         <input type="text" placeholder="Actual Value" id="actualValue'+(i+1)+'" name="actualValue'+(i+1)+'" value="'+opt.actualValue+'">';
		        	result = result +'        </div></td>';
		        	result = result +'     <td><div class="ta-sm">';
		        	//result = result +'          <input type="text" placeholder="Result" id="result'+(i+1)+'" name="result'+(i+1)+'" value="'+opt.result+'">';
		        	result = result +'<select id="result'+(i+1)+'" name="result'+(i+1)+'">';
		        	if(opt.result == "Pass"){
						result = result +'  <option selected >'+opt.result+'</option>';
						result = result +'  <option>Fail</option>';
					}
		        	if(opt.result == "Fail"){
						result = result +'  <option selected >'+opt.result+'</option>';
						result = result +'  <option>Pass</option>';
		        	}
					result = result +'</select>';
		        	result = result +'        </div></td>';
		        	result = result +'     <td><div class="ta-sl">';
		        	result = result +'         <input type="text" placeholder="Remarks" id="remarks'+(i+1)+'" name="remarks'+(i+1)+'" value="'+opt.remarks+'">';
		        	result = result +'       </div></td>';
		        	result = result +'      </tr>';
					}
		        	 result = result + '<input type="hidden" id="maxValue" name="maxValue" value="'+json.length+'"/>'; 
		        	$("#testDiv").empty();
			        $("#testDiv").append(result);
		        },
		    });
	 }else{
		 var output = "";
     	 output = output + "<option value=''>-- CheckList --</option>"; 
     	 $("#testDiv").empty();
	     $("#testDiv").append(output);
	 }
	 
	 
	 
	 $("#testDiv").empty();
     $("#testDiv").append("added");
 }
 </script> 

<script type="text/javascript">
  $( function() {
    $( "#startDate" ).datepicker();
    $( "#endDate" ).datepicker();
    
  });
</script>

<script type="text/javascript">
	function saveExecute(id,type) {
		var validationSettings = {
				errorMessagePosition : 'element',
				borderColorOnError : '',
				scrollToTopOnError : true,
				dateFormat : 'yyyy/mm/dd'
			};
		if ($('#addCheckList_form').validate(false, validationSettings)) {
			var frm = $('#addCheckList_form').serializeFormJSON();
			var con = JSON.stringify(frm);
			con = con.replace(/[{}]/g, "");
			var form = $('#addCheckList_form')[0];
	  		var formData = new FormData(form); 
	  		$("#addCheckList_form").attr("disabled", "disabled");
			 var jsonData = encodeURIComponent(con);
			 var operoxUrl ='${operoxUrl}';
			 $("#saveValue").attr("disabled", "disabled");
	     		$( "#saveValue" ).addClass('button').val('Processing..');
		         $.ajax({
		        	type : "POST",
		  			data: formData,
		     	    contentType : 'application/json; charset=utf-8',
		     	    enctype: 'multipart/form-data',
		 	        processData: false,
		 	        contentType:false,
		     	    dataType : 'json',
			    	 url: operoxUrl+"/saveExecute?${_csrf.parameterName}=${_csrf.token}&json="+jsonData, 
			        success: function(result) {
			        	 $("#saveValue").removeAttr("disabled");
			        	 $("#saveValue" ).addClass('btn btn-primary').val('Save');
			        	 if (result == 'Success') {
			        		 getQcRawMaterialList(id,type);
					}
			        },
			    }); 
			
			
			return true;
		} else {
			return false;
		}
		
	}
	$('body').on('blur', '#addCheckList_form', function() {
		$("#addCheckList_form").showHelpOnFocus();
		$("#addCheckList_form").validateOnBlur(false, validationSettings);
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
 function getCheckListForProductId() {
	 var productId = document.getElementById("productId").value;
	 var operoxUrl ='${operoxUrl}';
	 if(productId != null && productId != "" && productId != 'undefined'){
		 $.ajax({
		    	type: "POST",
		        url: operoxUrl+"/getCheckListByProductId?${_csrf.parameterName}=${_csrf.token}&productId="+productId, 
		        success: function(data) {
		        	var json = JSON.parse(data);
		        	var output = "";
		        	output = output + "<option value=''>--Select CheckList---</option>"; 
		        	for (var i=0; i<json.length; i++)
					{
			        		var opt = json[i];
			        		output = output + "<option value='" +opt.id+ "'>"+opt.checkName+"</option>";
					}
		        	$("#checkList").empty();
			        $("#checkList").append(output);
		        },
		    });
	 }else{
		 var output = "";
     	 output = output + "<option value=''>-- CheckList --</option>"; 
     	 $("#checkList").empty();
	     $("#checkList").append(output);
	 }
	
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
