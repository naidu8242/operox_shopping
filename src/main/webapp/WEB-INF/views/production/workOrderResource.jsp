<link rel="stylesheet" href="${operoxUrl}/resources/css/chosen.min.css">
<script src="${operoxUrl}/resources/js/chosen.jquery.min.js"></script> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

 
<script>
function getResourcePanning(id)
{
   	    var result = ""
   	    	result = result + '<div class="clearfix"></div>';
   	    	result = result + '<div class="clearfix">';
   	    	result = result  +'	<form id="resurce_form" name="resurce_form" method="post">';
   			result = result + '<input type="hidden" id="maxTsRowNum" name="maxTsRowNum" value="${workOrderResourceManagementList.size()+1}" />';
   			result = result + '<input type="hidden" id="resourceType" name="resourceType" />';
   	    	result = result + '<div class="clearfix workorder">';
   	    	result = result + '<div class="div33">';
	   	    	result = result + '<label class="labl">Job Type</label>';
	   	    	result = result + '<span class="checkBox">';
	   	    	result = result + '<input name="radio" type="radio" value="1" checked id="resfulltime" onClick="showDetailsForCheck(this);"><label class="mr">Full Time</label>';
	   	    	result = result + '<input name="radio" type="radio" value="0" id="resContact" onClick="showDetailsForCheck(this);"><label>Contract</label>';
	   	    	result = result + '</span>';
   	    	    result = result + '</div>';
   	    	
   	    	   result = result + '<div class="div33 resContact" id="resourceDiv">';
   	    	   result = result + '<label class="labl">Select Resource</label>';
   	    	   result = result + '<span>';  
	   	     	result = result + '<select class="chosen-select" id="productionUser" name="productionUser" onChange="addResource(this);">';
	   	    	result = result + '<option selected disabled>Select Resource</option>';
	   	        <c:forEach var="produser" items="${productionUsersList}"> 
				result = result  +'<option value="${produser.id}&${produser.firstName}">${produser.firstName}</option>';
                </c:forEach> 
	   	    	result = result + '</select>';
   	    	result = result + '</span>';
   	    	result = result + '</div>';
   	    	result = result + '</div>';
          /*   result = result + '<div id="tablegrid"></div>';  */
   	    	 result = result + '<div class="data_table extra-table" >';
   	    	result = result + '<p class="ml mt10 position-rel"><strong>Resource Management</strong>';
   	    	result = result + '<a href="#" class="btn btn-primary btn-sm addContctfield" id="addContctfield" onclick="addRow();" ><i class="glyphicon glyphicon-plus" ></i>&nbsp;Add</a>';
   	    	result = result + '</p>';
   	    	result = result + '<table id="resourceTbl">';
   	    	result = result + '<tbody>';
   	    	result = result + '<tr id="divResourceRow0">';
   	    	result = result + '<th>Sl.No</th>';
   	    	result = result + '<th>Resource Name</th>';
   	    	result = result + '<th>Start Date</th>';
   	    	result = result + '<th>End Date</th>';
   	    	result = result + '<th>Total Hours</th>';
   	    	result = result + '<th class="action">Action</th>';
   	    	result = result + '</tr>'; 
   	    	<c:forEach var="opt" items="${workOrderResourceManagementList}" varStatus="count">
   	    	result = result + '<tr id="divResourceRow${count.index+1}">';
   	    	result = result + '<td><div class="ta-m">${count.index+1}</td>';
   	    	result = result + '<td><div class="ta-m">${opt.resourceName}</div></td>';
   	    	result = result + '<input type="hidden" id="${count.count}resourceName" name="${count.count}resourceName" value="${opt.resourceName}"/>';
			result = result + '<input type="hidden" id="${count.count}userId" name="${count.count}userId" value=""/>';
   	    	
			result = result + '<td><div class="ta-l"><input class="Datepicker" value="${opt.startDateStr}"  id="${count.count}startDate" name="${count.count}startDate" readonly="readonly"  type="text" data-validation="required" field-name="startdate"></div></td>';
			result = result + '<td><div class="ta-l"><input class="Datepicker" value="${opt.endDateStr}"  id="${count.count}endDate" name="${count.count}endDate" readonly="readonly"  type="text" data-validation="required" field-name="endDate"></div></td>';
			result = result + '<td><div class="ta-m"><input value="${opt.totalHours}" type="text" id="${count.count}totalHours" name="${count.count}totalHours"></div></td>';
   	    	result = result + '<td><div><a href="#" onclick="deleteResource(${opt.user.id},'+id+'); removeResource(${count.index+1});"><i class="glyphicon glyphicon-trash deme"></i></a></div></td>';
   	    	result = result + '</tr>';
   	    	
   	    	</c:forEach> 
   	    	result = result + '</tfoot>';
   	    	result = result + '</tbody>';
   	    	result = result + '</table>';
   	    	
   	    	result = result + '<input type="hidden" id="maxTsRowNum1" name="maxTsRowNum1" value="${workOrderContractManagementList.size()+1}" />';
   	    	result = result + '<table id="resourceTbl1" style="display:none">';
   	    	result = result + '<tbody>';
   	    	result = result + '<tr id="divResourceRow0">';
   	    	result = result + '<th>Sl.No</th>';
   	    	result = result + '<th>Resource Name</th>';
   	    	result = result + '<th>Start Date</th>';
   	    	result = result + '<th>End Date</th>';
   	    	result = result + '<th>Total Hours</th>';
   	    	result = result + '<th class="action">Action</th>';
   	    	result = result + '</tr>';
   	    	
   	    	<c:forEach var="contract" items="${workOrderContractManagementList}" varStatus="count">
   	    	result = result + '<tr id="divContractRow${count.index+1}">';
   	    	result = result + '<td><div class="ta-m">${count.index+1}</td>';
   	    	result = result + '<input type="hidden" id="${count.count}workOrderResourceId" value="${contract.id}" name="${count.count}workOrderResourceId" />';
   	    	/* result = result + '<td><div class="ta-m">${opt.resourceName}</div></td>'; */
   	    	result = result + '<td><div class="ta-l"><input  type="text" value="${contract.resourceName}"  id="${count.count}contractName" name="${count.count}contractName"  data-validation="required" field-name="contract name"></div></td>';
   	    	/* result = result + '<input type="hidden" id="${count.count}resourceName" name="${count1.count1}resourceName" value="${opt.resourceName}"/>';
			result = result + '<input type="hidden" id="${count.count}userId" name="${count1.count1}userId" value="${opt.user.id}"/>'; */
   	    	
			result = result + '<td><div class="ta-l"><input class="Datepicker" value="${contract.startDateStr}"  id="${count.count}contractStartdate" name="${count.count}contractStartdate" readonly="readonly"  type="text" data-validation="required" field-name="startdate"></div></td>';
			result = result + '<td><div class="ta-l"><input class="Datepicker" value="${contract.endDateStr}"  id="${count.count}contractEnddate" name="${count.count}contractEnddate" readonly="readonly"  type="text" data-validation="required" field-name="endDate"></div></td>';
			result = result + '<td><div class="ta-m"><input value="${contract.totalHours}" type="text" id="${count.count}contractTotalHours" name="${count.count}contractTotalHours"></div></td>';
   	    	result = result + '<td><div><a href="#" onclick="removeContract(${count.index+1});"><i class="glyphicon glyphicon-trash deme"></i></a></div></td>';
   	    	result = result + '</tr>';
   	    	
   	    	</c:forEach> 
   	    	
   	    	
   	    	result = result + '</tfoot>';
   	    	result = result + '</tbody>';
   	    	result = result + '</table>';
   	    	result = result + '</div> ';
   	    	result = result + '</div>';

   	    	result = result + '<div class="form-footer mt clearfix">';
   	    	result = result + '<div class="col-lg-12">';
   	    	result = result + '<div class="pull-right">';
   	    	result = result + '<input value="Save" class="btn btn-primary" onClick="saveResource('+id+');" id="resource_save" type="button">';
   	    	result = result + '<input value="Cancel" class="btn btn-default ml10" type="button">';
   	    	result = result + '</div>';
   	    	result = result + '</div>';
   	    	result = result + '</div>';  
   	    	result = result + '<div class="clearfix"></div>';
   	 	    result = result  +'</form> ';
   	    	$("#resourcePlanning").empty();  
   			$("#resourcePlanning").append(result);  
   			
   			
   		  
   	    	
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


   	// Tab script
   	$("#resContact input[type='radio']").prop('checked', false);
   	$("#rsname, #addContctfield").hide();
   	//Full Time
   	$("#resfulltime").click(function(){
   		$(".resContact").show();
   		$("#rsname").hide();
   		$(".rsname").show();
   		$("#addContctfield").hide();
   		
   	});
   	//contract
   	$("#resContact").click(function(){
   		$(".resContact").show();
   		$("#rsname").show();
   		$(".rsname").hide();
   		$("#resourceDiv").hide();
   		$("#addContctfield").show();
   	});
}

</script>


<div id="divResourceTd1" style="display: none; float: left;">
   	<div class="ta-sm">ROWNUMBERVALUE</div>
</div>
<div id="divResourceTd2" style="display: none; float: left;">
   	<div class="ta-sm">RESOURCENAME</div> 
	<input type="hidden" id="REPLACEROWIDresourceName" name="REPLACEROWIDresourceName" value="RESOURCENAME"/> 
	<input type="hidden" id="REPLACEROWIDuserId" name="REPLACEROWIDuserId" value="USERID"/> 
</div> 


<div id="divResourceTd5" style="display: none; float: left;">
		  <div class="ta-m"><input value="" type="text" id="REPLACEROWIDtotalHours" name="REPLACEROWIDtotalHours"></div>
</div> 

<div id="divResourceTd6" style="display: none; float: left;">
	 <div>
         <a href="#" onclick="removeResource(REPLACEROWID);"><i class="glyphicon glyphicon-trash deme"></i></a>
    </div>
</div>






<script>
function addResource(local){
	//$("#resourceTbl").show();
	
	var users = [];
	users = local.value.split("&");
	var userId = users[0];
	var userName = users[1];
	var checkMaxTsRowNum = $("#maxTsRowNum").val();
	var tbl = document.getElementById("resourceTbl");
	var maxRowNum = parseInt($("input[name = 'maxTsRowNum']").val());
	$("input[name = 'maxTsRowNum']").val(maxRowNum + 1);
	
	var row = tbl.insertRow(maxRowNum);
	row.setAttribute("id", "divResourceRow" + maxRowNum); 
	
	var cell0 = row.insertCell(0);
	var divTsTd1Str = document.getElementById("divResourceTd1").innerHTML;
    divTsTd1Str = divTsTd1Str.replace(/ROWNUMBERVALUE/g, (maxRowNum)); 
	cell0.innerHTML = divTsTd1Str;
	
	var cell1 = row.insertCell(1);
	var divTsTd2Str = document.getElementById("divResourceTd2").innerHTML;
	divTsTd2Str = divTsTd2Str.replace(/REPLACEROWID/g, (maxRowNum));
	divTsTd2Str = divTsTd2Str.replace(/RESOURCENAME/g, (userName));
	divTsTd2Str = divTsTd2Str.replace(/USERID/g,(userId));
	cell1.innerHTML = divTsTd2Str;
	
	var cell2 = row.insertCell(2);
	cell2.innerHTML = "<input class='Datepicker' name='"+maxRowNum+"startdate' id='"+maxRowNum+"startdate'  readonly='readonly'  type='text' data-validation='required' field-name='startdate' >";
	$(".Datepicker").datepicker();
	
	var cell3 = row.insertCell(3);
	cell3.innerHTML = "<input class='Datepicker' name='"+maxRowNum+"enddate' id='"+maxRowNum+"enddate'  readonly='readonly'  type='text' data-validation='required' field-name='enddate' >";
	$(".Datepicker").datepicker();
	 
	var cell4 = row.insertCell(4);
	var divTsTd5Str = document.getElementById("divResourceTd5").innerHTML;
	divTsTd5Str = divTsTd5Str.replace(/REPLACEROWID/g, (maxRowNum));
	cell4.innerHTML = divTsTd5Str; 
	
	var cell5 = row.insertCell(5);
	var divTsTd6Str = document.getElementById("divResourceTd6").innerHTML;
	divTsTd6Str = divTsTd6Str.replace(/REPLACEROWID/g, (maxRowNum));
	cell5.innerHTML = divTsTd6Str;  
}

</script>

 <script type="text/javascript">
	function removeResource(rowId) {
		var rowNum = $("#divResourceRow"+rowId).val();
		var maxRowNum = parseInt(rowNum);
		
		 document.getElementById("divResourceRow"+rowId).style.display = 'none';
		 document.getElementById("divResourceRow"+rowId).value= '';

		 renumberResourceRows(); 
		 var  rId = "divResourceRow"+rowId;
		  var r = document.getElementById("resourceTbl").rows[rId];
          var count = 2*(r.cells.length);
		    for(i=0;i<=count;i++){
		        r.deleteCell(0);
		    } 
		    
		    
		    
	}
</script>


<script type="text/javascript">
function renumberResourceRows() {
        $('#resourceTbl tr:visible').each(function(index, el){ 
            $(this).children('td').first().text(index++);
        });
    }
</script>


<script type="text/javascript">
function saveResource(workOrderId) {
	
    var radioValue = $("input[name='radio']:checked").val();
    $("#resourceType").val(radioValue);
 	var validationSettings = {
		errorMessagePosition : 'element',
		borderColorOnError : '',
		scrollToTopOnError : true,
		dateFormat : 'yyyy/mm/dd'
	}; 
 	if ($('#resurce_form').validate(false, validationSettings)) { 
		var frm = $('#resurce_form').serializeFormJSON();
		var con = JSON.stringify(frm);
		con = con.replace(/[{}]/g, "");
		  //It will escape all the special characters
        var jsonData = encodeURIComponent(con);
		var operoxUrl = '${operoxUrl}';
		 $("#resource_save").attr("disabled", "disabled");
		 $( "#resource_save" ).addClass('btn btn-primary').val('Processing..');
 		$.ajax({
					type : "POST",
					url : operoxUrl+ "/saveResource?${_csrf.parameterName}=${_csrf.token}&json="+jsonData+"&workOrderId="+workOrderId,
					success : function(result) {
						 location.replace('${operoxUrl}/viewWorkOrder/'+workOrderId+'/details');
					},
				}); 
		return true;
	 } else {
		return false;
	} 
}
$('body').on('blur', '#resurce_form', function() {
	$("#resurce_form").showHelpOnFocus();
	$("#resurce_form").validateOnBlur(false, validationSettings);
});

</script>

<script type="text/javascript">
function showDetailsForCheck(radio){
	if(radio.value =='1'){
		$("#resourceTbl").removeAttr("style");
		$("#resourceTbl1").attr("style","display:none");
	}else{
		$("#resourceTbl1").removeAttr("style");
		$("#resourceTbl").attr("style","display:none");
	}  
	
}
</script>

<div id="divContractTd1" style="display: none; float: left;">
   	<div class="ta-sm">CONTRACTVALUE</div>
</div>
<div id="divContractTd2" style="display: none; float: left;">
<!--    	<div class="ta-sm">CONTRACTNAME</div>  -->
	<!-- <input type="hidden" id="REPLACEROWIDresourceName" name="REPLACEROWIDresourceName" value="RESOURCENAME"/>  -->
	<div class="ta-m"><input value="" type="text" id="REPLACECONTRACTROWIDcontractName" name="REPLACECONTRACTROWIDcontractName"></div>
</div> 


<div id="divContractTd5" style="display: none; float: left;">
		  <div class="ta-m"><input value="" type="text" id="REPLACECONTRACTROWIDcontractTotalHours" name="REPLACECONTRACTROWIDcontractTotalHours"></div>
</div> 

<div id="divContractTd6" style="display: none; float: left;">
	 <div>
         <a href="#" onclick="removeContract(REPLACECONTRACTROWID);"><i class="glyphicon glyphicon-trash deme"></i></a>
    </div>
</div>


<script>
function addRow(){
	
	var checkMaxTsRowNum = $("#maxTsRowNum1").val();
	var tbl = document.getElementById("resourceTbl1");
	var maxRowNum = parseInt($("input[name = 'maxTsRowNum1']").val());
	$("input[name = 'maxTsRowNum1']").val(maxRowNum + 1);
	
	var row = tbl.insertRow(maxRowNum);
	row.setAttribute("id", "divContractRow" + maxRowNum); 
	
	
	
	var cell0 = row.insertCell(0);
	var divTsTd1Str = document.getElementById("divContractTd1").innerHTML;
    divTsTd1Str = divTsTd1Str.replace(/CONTRACTVALUE/g, (maxRowNum)); 
	cell0.innerHTML = divTsTd1Str;
	
	var cell1 = row.insertCell(1);
	var divTsTd2Str = document.getElementById("divContractTd2").innerHTML;
	divTsTd2Str = divTsTd2Str.replace(/REPLACECONTRACTROWID/g, (maxRowNum));
	cell1.innerHTML = divTsTd2Str;
	
	var cell2 = row.insertCell(2);
	cell2.innerHTML = "<input class='Datepicker' name='"+maxRowNum+"contractStartdate' id='"+maxRowNum+"contractstartdate'  readonly='readonly'  type='text' data-validation='required' field-name='startdate' >";
	$(".Datepicker").datepicker();
	
	var cell3 = row.insertCell(3);
	cell3.innerHTML = "<input class='Datepicker' name='"+maxRowNum+"contractEnddate' id='"+maxRowNum+"contractEnddate'  readonly='readonly'  type='text' data-validation='required' field-name='enddate' >";
	$(".Datepicker").datepicker();
	 
	var cell4 = row.insertCell(4);
	var divTsTd5Str = document.getElementById("divContractTd5").innerHTML;
	divTsTd5Str = divTsTd5Str.replace(/REPLACECONTRACTROWID/g, (maxRowNum));
	cell4.innerHTML = divTsTd5Str; 
	
	var cell5 = row.insertCell(5);
	var divTsTd6Str = document.getElementById("divContractTd6").innerHTML;
	divTsTd6Str = divTsTd6Str.replace(/REPLACECONTRACTROWID/g, (maxRowNum));
	cell5.innerHTML = divTsTd6Str;  
}
</script>


<script type="text/javascript">
	function removeContract(rowId) {
		var rowNum = $("#divContractRow"+rowId).val();
		var maxRowNum = parseInt(rowNum);
		
		 document.getElementById("divContractRow"+rowId).style.display = 'none';
		 document.getElementById("divContractRow"+rowId).value= '';

		 renumberContractRows(); 
		 var  rId = "divContractRow"+rowId;
		  var r = document.getElementById("resourceTbl1").rows[rId];
          var count = 2*(r.cells.length);
		    for(i=0;i<=count;i++){
		        r.deleteCell(0);
		    } 
		    
	}
</script>

<script type="text/javascript">
function renumberContractRows() {
        $('#resourceTbl1 tr:visible').each(function(index, el){ 
            $(this).children('td').first().text(index++);
        });
    }
</script>

<script type="text/javascript">
function deleteResource(userid,id){
var operoxUrl ='${operoxUrl}';
$.ajax({
   	type: "POST",
    url: operoxUrl+"/removeResource?${_csrf.parameterName}=${_csrf.token}&userid="+userid, 	
	    success: function(data) {
	    	location.replace('${operoxUrl}/viewWorkOrder/'+workorder.id+'/details');
	    }
 });
}
</script>

<!-- <script type="text/javascript">
  $( function() {
    $( "#datebirth" ).datepicker();
  });
</script> -->


