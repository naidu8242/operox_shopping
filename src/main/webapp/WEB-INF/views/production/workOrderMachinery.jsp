<link rel="stylesheet" href="${operoxUrl}/resources/css/chosen.min.css">
<script src="${operoxUrl}/resources/js/chosen.jquery.min.js"></script> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>


<script>
function getMachineryDetails(workOrderId)
{
   	    var result = ""
   	    	result = result + '<div class="clearfix"></div>';
   	    	result = result + '<div class="clearfix">';
   	    	result = result  +'	<form id="machinery_form" name="machinery_form" method="post">';
   	    	result = result + '<input type="hidden" id="maxMachineryRowNum" name="maxMachineryRowNum" value="${workOrderMachineryManagementsList.size()+1}" />';
   			result = result + '<input type="hidden" id="workOrderId" name="workOrderId" value="'+workOrderId+'"/>';
   	    	result = result + '<div class="clearfix">';
   	    	result = result + '<p class="ml mt10">';
	   	    	result = result + '<strong>Machinery Management</strong>';
	   	    	result = result + '<button type="button" title="addMachinery" class="btn btn-primary btn-sm pull-right mr" onClick="addMachinery();"><i class="fa fa-plus"></i>&nbsp;&nbsp;Add Row</button>';
   	    	    result = result + '</p>';
   	    	    result = result + '</div>';
   	    	 result = result + '<div class="data_table extra-table" >';
   	    	result = result + '<table id="machineryTbl">';
   	    	result = result + '<tbody>';
   	    	result = result + '<tr id="divMachineryRow0">';
   	    	result = result + '<th>Machinery Id</th>';
   	    	result = result + '<th>Machinery Name</th>';
   	    	result = result + '<th>Start Date</th>';
   	    	result = result + '<th>End Date</th>';
   	    	result = result + '<th>Total Hours</th>';
   	    	result = result + '<th class="action">Action</th>';
   	    	result = result + '</tr>';
   	    	
   	    	result = result + '<div id="dataDiv">';
			   <c:forEach var="opt" items="${workOrderMachineryManagementsList}" varStatus="count">
				
			
				    result = result + '<tr id="divMachineryRow${count.index+1}">';
					result = result + '<td><div class="ta-m"><input type="text" id="${count.count}machineryId" name="${count.count}machineryId" value="${opt.machineryId}"/></td>';
					result = result + '<td><div class="ta-m"><input type="text" id="${count.count}machineryName" name="${count.count}machineryName" value="${opt.machineryName}"/></div></td>';
					result = result + '<td><div class="ta-m"><input type="text" id="${count.count}startDate"  class="Datepicker" readonly="readonly" name="${count.count}startDate" value="${opt.startDateStr}"/></div></td>';
					result = result + '<td><div class="ta-m"><input type="text" id="${count.count}endDate" class="Datepicker" readonly="readonly" name="${count.count}endDate" value="${opt.endDateStr}"/></div></td>';
					result = result + '<td><div class="ta-m"><input type="text" id="${count.count}totalHours" name="${count.count}totalHours" value="${opt.totalHours}"/></div></td>';
					result = result + '<td><div><a href="#" onclick="removeMachinery(${count.index+1});"><i class="glyphicon glyphicon-trash deme"></i></a></div></td>';
					result = result + "<input type='hidden'  id='${count.index+1}' name='id' value='${opt.id}'/>";
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
   	    	result = result + '<input value="Save" class="btn btn-primary" onClick="saveMachinery('+workOrderId+');" id="save_machinery" type="button">';
   	    	result = result + '</div>';
   	    	result = result + '</div>';
   	    	result = result + '</div>';  
   	    	result = result + '<div class="clearfix"></div>';
   	 	    result = result  +'</form> ';
   	    	$("#workOrderMachinery").empty();  
   			$("#workOrderMachinery").append(result);   
   			$(".Datepicker").datepicker();
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


   
   
		
}

</script>


<div id="divMachineryTd1" style="display: none; float: left;">
   	<div class="ta-m">MACHINERYID
       	<input type="text" id="REPLACEROWIDmachineryId" name="REPLACEROWIDmachineryId" value="MACHINERYID"/>
    </div>   	
   	 </div>
<div id="divMachineryTd2" style="display: none; float: left;">
   	<div class="ta-m">MACHINERYNAME 
	<input type="text" id="REPLACEROWIDmachineryName" name="REPLACEROWIDmachineryName" value="MACHINERYNAME" field-name="Machinery Name" data-validation="required"/> 
</div>
</div> 


<div id="divMachineryTd5" style="display: none; float: left;">
		  <div class="ta-m"><input value="" type="text" id="REPLACEROWIDtotalHours" name="REPLACEROWIDtotalHours"></div>
</div> 

<div id="divMachineryTd6" style="display: none; float: left;">
	 <div>
         <a href="#" onclick="removeMachinery(REPLACEROWID);"><i class="glyphicon glyphicon-trash deme"></i></a>
    </div>
</div>

<script>
function addMachinery(local){
	//$("#machineryTbl").show();
	var checkMaxTsRowNum = $("#maxMachineryRowNum").val();
	var tbl = document.getElementById("machineryTbl");
	var maxRowNum = parseInt($("input[name = 'maxMachineryRowNum']").val());
	$("input[name = 'maxMachineryRowNum']").val(maxRowNum + 1);
	
	var row = tbl.insertRow(maxRowNum);
	row.setAttribute("id", "divMachineryRow" + maxRowNum); 
	
	var cell0 = row.insertCell(0);
	var divTsTd1Str = document.getElementById("divMachineryTd1").innerHTML;
	divTsTd1Str = divTsTd1Str.replace(/REPLACEROWID/g, (maxRowNum));
    divTsTd1Str = divTsTd1Str.replace(/MACHINERYID/g, (" "));
	cell0.innerHTML = divTsTd1Str;
	
	var cell1 = row.insertCell(1);
	var divTsTd2Str = document.getElementById("divMachineryTd2").innerHTML;
	divTsTd2Str = divTsTd2Str.replace(/REPLACEROWID/g, (maxRowNum));
	divTsTd2Str = divTsTd2Str.replace(/MACHINERYNAME/g, (" "));
	cell1.innerHTML = divTsTd2Str;
	
	var cell2 = row.insertCell(2);
	cell2.innerHTML = "<div class='ta-m'><input class='Datepicker' name='"+maxRowNum+"startDate' id='"+maxRowNum+"startDate'  readonly='readonly'  type='text' data-validation='required' field-name='startDate' ></div>";
	$(".Datepicker").datepicker();
	
	var cell3 = row.insertCell(3);
	cell3.innerHTML = "<div class='ta-m'><input class='Datepicker' name='"+maxRowNum+"endDate' id='"+maxRowNum+"endDate'  readonly='readonly'  type='text' data-validation='required' field-name='endDate' ></div>";
	$(".Datepicker").datepicker();
	 
	var cell4 = row.insertCell(4);
	var divTsTd5Str = document.getElementById("divMachineryTd5").innerHTML;
	divTsTd5Str = divTsTd5Str.replace(/REPLACEROWID/g, (maxRowNum));
	cell4.innerHTML = divTsTd5Str; 
	
	var cell5 = row.insertCell(5);
	var divTsTd6Str = document.getElementById("divMachineryTd6").innerHTML;
	divTsTd6Str = divTsTd6Str.replace(/REPLACEROWID/g, (maxRowNum));
	cell5.innerHTML = divTsTd6Str;  
	
}
</script>

 <script type="text/javascript">
	function removeMachinery(rowId) {
		var rowNum = $("#divMachineryRow"+rowId).val();
		var maxRowNum = parseInt(rowNum);
		
		 document.getElementById("divMachineryRow"+rowId).style.display = 'none';
		 document.getElementById("divMachineryRow"+rowId).value= '';

		 //renumberMachineryRows(); 
		 
		 var  rId = "divMachineryRow"+rowId;
		  var r = document.getElementById("machineryTbl").rows[rId];
          var count = 2*(r.cells.length);
		    for(i=0;i<=count;i++){
		        r.deleteCell(0);
		    } 
		    
	}
</script>

<!-- <script type="text/javascript">
function renumberMachineryRows() {
        $('#machineryTbl tr:visible').each(function(index, el){ 
            $(this).children('td').first().text(index++);
        });
    }
</script> -->

<script type="text/javascript">
function deleteMachinery(id){
var operoxUrl ='${operoxUrl}';
$.ajax({
   	type: "POST",
    url: operoxUrl+"/removeMachinery?${_csrf.parameterName}=${_csrf.token}&id="+id, 	
	    success: function(data) {
	    	location.replace('${operoxUrl}/viewWorkOrder/'+workorder.id+'/machinery');
	    }
 });
}
</script>


<script type="text/javascript">
function saveMachinery(workOrderId) {
    
 	var validationSettings = {
		errorMessagePosition : 'element',
		borderColorOnError : '',
		scrollToTopOnError : true,
		dateFormat : 'yyyy/mm/dd'
	}; 
 	if ($('#machinery_form').validate(false, validationSettings)) { 
		var frm = $('#machinery_form').serializeFormJSON();
		var con = JSON.stringify(frm);
		con = con.replace(/[{}]/g, "");
		  //It will escape all the special characters
        var jsonData = encodeURIComponent(con);
		var operoxUrl = '${operoxUrl}';
 		$.ajax({
					type : "POST",
					url : operoxUrl+ "/saveMachinery?${_csrf.parameterName}=${_csrf.token}&json="+jsonData+"&workOrderId="+workOrderId,
					success : function(result) {
						$("#save_machinery").removeAttr("disabled");
			        	 $("#save_machinery" ).addClass('btn btn-primary').val('Save');
			        	 if((result == 'Success')){
				        		location.replace(operoxUrl+"/viewWorkOrder/"+workOrderId+"/machinery"); 
				          }
					},
				}); 
		return true;
	 } else {
		return false;
	} 
}
$('body').on('blur', '#machinery_form', function() {
	$("#machinery_form").showHelpOnFocus();
	$("#machinery_form").validateOnBlur(false, validationSettings);
});

</script>

<script type="text/javascript">
function showDetails(radio){
	if(radio.value =='1'){
		$("#machineryTbl").removeAttr("style");
		$("#machineryTbl1").attr("style","display:none");
	}else{
		$("#machineryTbl1").removeAttr("style");
		$("#machineryTbl").attr("style","display:none");
	}  
	
}
</script> 

