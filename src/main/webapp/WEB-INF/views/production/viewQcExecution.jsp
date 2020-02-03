<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
   

<script>
function viewQcExecution(executionId,id,type){
	var operoxUrl = '${operoxUrl}';
	$.ajax({
	type : "GET",
	url : operoxUrl+ "/viewExecution?&executionId="+executionId,
	success : function(data) {
		var json = JSON.parse(data);
		
		var rawMaterial = '';
		var products = '';
		var checkList = '';
		var totalQuantity = '';
		var startDate ='';
		var endDate ='';
		var status ='';
		var noOfPassedUnits ='';
		var noOfFailedUnits ='';
		var attachment ='';
		var comment ='';
		var checkList = '';
		var productId = '';
		
		if(json.rawMaterialName != null && json.rawMaterialName != "" && json.rawMaterialName != 'undefined'){
			rawMaterial = json.rawMaterialName;
		}else{
			rawMaterial = '-----';
		}
		
		if(json.qcCheckListName != null && json.qcCheckListName != "" && json.qcCheckListName != 'undefined'){
			checkList = json.qcCheckListName;
		}else{
			checkList = '-----';
		}
		
		if(json.productName != null && json.productName != "" && json.productName != 'undefined'){
			products = json.productName;
		}else{
			products = '-----';
		}
		
		if(json.totalQuantity != null && json.totalQuantity != "" && json.totalQuantity != 'undefined'){
			totalQuantity = json.totalQuantity;
		}else{
			totalQuantity = '-----';
		}
		
		if(json.startDateStr != null && json.startDateStr != "" && json.startDateStr != 'undefined'){
			startDate = json.startDateStr;
		}else{
			startDate = '-----';
		}
		
		if(json.endDateStr != null && json.endDateStr != "" && json.endDateStr != 'undefined'){
			endDate = json.endDateStr;
		}else{
			endDate = '-----';
		}
		
		if(json.qcStatus != null && json.qcStatus != "" && json.qcStatus != 'undefined'){
			status = json.qcStatus;
		}else{
			status = '-----';
		}
		
		if(json.noOfPassedUnits != null && json.noOfPassedUnits != "" && json.noOfPassedUnits != 'undefined'){
			noOfPassedUnits = json.noOfPassedUnits;
		}else{
			noOfPassedUnits = '-----';
		}
		
		if(json.noOfFailedUnits != null && json.noOfFailedUnits != "" && json.noOfFailedUnits != 'undefined'){
			noOfFailedUnits = json.noOfFailedUnits;
		}else{
			noOfFailedUnits = '-----';
		}
		
		if(json.fileName != null && json.fileName != "" && json.fileName != 'undefined'){
			attachment = json.fileName;
		}else{
			attachment = '-----';
		}
		
		if(json.comment != null && json.comment != "" && json.comment != 'undefined'){
			comment = json.comment;
		}else{
			comment = '-----';
		}
		
		if(json.checkListId != null && json.checkListId != "" && json.checkListId != 'undefined'){
			checkList = json.checkListId;
		}else{
			checkList = null;
		}
		
		if(json.productId != null && json.productId != "" && json.productId != 'undefined'){
			productId = json.productId;
		}else{
			productId = null;
		}
		
		var result = "";
		result = result +' <section class="content-header clearfix">';
		result = result +'   <div class="pull-left">';
		result = result +'     <a href="#" onClick="getQcRawMaterialList('+id+',\''+type+'\');"><i class="fa fa-arrow-left"></i>Back to List</a>';
		result = result +'   </div>';
		result = result +' </section>';
		
		result = result +' <div class="content-area clearfix no_pad">';
		result = result +'<div class="view_wrap">';
		result = result +'<div class="view_wrap_details view_wrap_details_a">';
		
		if(type=='RawMaterial'){
    		result = result +'<div>';
    		result = result +'<b>Raw Material</b><span>'+rawMaterial+'</span>';
    		result = result +'<div class="clearfix"></div>';
    		result = result +'</div>';
    	}
    	if(type=='Products'){
    		result = result +'<div>';
    		result = result +'<b>Products</b><span>'+products+'</span>';
    		result = result +'<div class="clearfix"></div>';
    		result = result +'</div>';
    	}
    	
    	result = result +'<div>';
		result = result +'<b>Check List</b><span>'+checkList+'</span>';
		result = result +'<div class="clearfix"></div>';
		result = result +'</div>';
		
		result = result +'<div>';
		result = result +'<b>Start Date</b><span>'+startDate+'</span>';
		result = result +'<div class="clearfix"></div>';
		result = result +'</div>';
		
		result = result +'<div>';
		result = result +'<b>End Date</b><span>'+endDate+'</span>';
		result = result +'<div class="clearfix"></div>';
		result = result +'</div>';
		
		result = result +'<div>';
		result = result +'<b>Total Quantity</b><span>'+totalQuantity+'</span>';
		result = result +'<div class="clearfix"></div>';
		result = result +'</div>';
		
		result = result +'<div>';
		result = result +'<b>Status</b><span>'+status+'</span>';
		result = result +'<div class="clearfix"></div>';
		result = result +'</div>';
		
		result = result +'<div>';
		result = result +'<b>No.of Passed Units</b><span>'+noOfPassedUnits+'</span>';
		result = result +'<div class="clearfix"></div>';
		result = result +'</div>';
		
		result = result +'<div>';
		result = result +'<b>No.of Failed Units</b><span>'+noOfFailedUnits+'</span>';
		result = result +'<div class="clearfix"></div>';
		result = result +'</div>';
		
		result = result +'<div>';
		result = result +'<b>Attached File Name</b><span>'+attachment+'</span>';
		result = result +'<div class="clearfix"></div>';
		result = result +'</div>';
		
		result = result +'<div>';
		result = result +'<b>Comment</b><span>'+comment+'</span>';
		result = result +'<div class="clearfix"></div>';
		result = result +'</div>';
		
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
		result = result +'<tbody id=viewTestListDiv>';
		
		result = result +' </tbody>';
		result = result +'</table>';
		result = result +'</div>';
		
		
    	/* result = result + ' <th>Checklist</th>';
    	result = result + '<th>Total Quantity</th>';
    	result = result + '<th>Passed Units</th>';
    	result = result + '<th>Failed Units</th>';
    	result = result + '<th>Status</th>'; */
		
		
		
		result = result +'<div class="clearfix"></div>';
		result = result +'</div>';
		result = result +'</div>';
		document.getElementById("qcqarawmaterial-list").style.display="none";
		$("#qcViewDiv").empty();  
	    $("#qcViewDiv").append(result);
	    getviewTestList(executionId,checkList);
		},
	}); 
}

</script>

<script type="text/javascript">
 function getviewTestList(executionId,checkList) {
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
			        result = result +'<tr id="trDiv'+opt.id+'">';
		        	result = result +'<td><div class="ta-sm">'+(i+1)+'</div></td>';
		        	result = result +'<td><div class="ta-sm">'+opt.whatToCheck+'</div></td>';
		        	result = result +'<td><div class="ta-sm">'+opt.howToCheck+'</div></td>';
		        	result = result +'<td><div class="ta-sm">'+opt.testDescription+'</div></td>';
		        	result = result +'<td><div class="ta-sm">'+opt.actualValue+'</div></td>';
		        	result = result +'<td><div class="ta-sm">'+opt.result+'</div></td>';
		        	result = result +'<td><div class="ta-sl">'+opt.remarks+'</div></td>';
		        	result = result +'</tr>';
					}
		        	$("#viewTestListDiv").empty();
			        $("#viewTestListDiv").append(result);
		        },
		    });
	 }else{
		 var output = "";
     	 $("#viewTestListDiv").empty();
	     $("#viewTestListDiv").append(output);
	 }
	 
	 
	 
	 $("#viewTestListDiv").empty();
     $("#viewTestListDiv").append("added");
 }
 </script> 
