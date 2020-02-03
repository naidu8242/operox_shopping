<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>

<!-- Content Area -->
<section class="content-wraper">
	<section class="content-header clearfix">
		<div class="pull-left">
			<h2>Raw Material List</h2>
		</div>
		<div class="pull-right brud-crum">
			<a href="${operoxUrl}/dashboard">Home </a>&raquo; Raw Material List
		</div>
	</section>

	<!-- Content Area inner -->
	<div class="content-area clearfix no_pad">
		<div class="data_table_head">
			<ul>
				<li><a href="<c:url value='/newRawMaterial'/>"
					title="AddRawMaterial"><i class="fa fa-plus"></i>Add Raw
						Material</a></li>
			</ul>
			<div class="clearfix"></div>
		</div>
		<div class="data_table">
			<div id="rawMaterialsListDiv"></div>
		</div>

	</div>

</section>
<!-- Content Area Ends-->
<!--  Error Message  -->
<div class="mask"></div>
<div class="delete_pop">
	<p>Delete Confirmation message....</p>
	<a class="close_color" href="javascript:void(0)"
		id="removeRawMaterialConfirm">Yes</a><a href="javascript:void(0)">No</a>
</div>
<!--  Error Message  -->

<script>
	$(document).ready(function() {	
		rawMatterialsListByStatus('Active');
});
</script>

<script>
function rawMatterialsListByStatus(status){ 
	var operoxUrl = '${operoxUrl}';
    $.ajax({
 		   	type: "POST",
 		    url: operoxUrl+"/getAllRawMaterialByStatus?${_csrf.parameterName}=${_csrf.token}&status="+status, 	   
    		    success: function(data) {
    		    var result = '';
    		    var json = JSON.parse(data);
   		    	if(json != null && json != 'undefined' && json != "" && json != '[]'){
   		    	
   		    	result = result +'	<table id="PaginationRawMaterial" class="display" cellspacing="0" width="100%">';
   		        result = result + '<thead>';
   		    	result = result +'		<tr>';
   		    	result = result +'	    	<th>Id</th>';
   		    	result = result +'	        <th>Name</th>';
   		    	result = result +'	        <th>Code</th>';                  
   		    	result = result +'	        <th>Price</th>';
   		    	result = result +'	        <th>Available Inventory</th>';
   		    	result = result +'	        <th>Updated By</th>';
   		    	result = result +'	        <th class="action">Action</th>';
   		    	result = result +'	    </tr>';
   		        result = result + '</thead>';
			    result = result + '<tbody>';
			   	for (var i=0; i<json.length; i++)
   				{
   		    	var report = json[i];
   		    	
   		    	var index = i+1;
			   	result = result +'<tr>';
			   	result = result +'<td><div class="ta-m">'+index+'</div></td>';
			   	
			   	result = result +'<td><a href="${operoxUrl}/viewRawMaterial/'+report.id+'"><div class="ta-m" style="color:#87CEFA;">'+report.materialName+'</div></a></td>';
			  
				if(report.searchCode != null && report.searchCode != '' && report.searchCode != 'undefined'){
			   	result = result +'<td><div class="ta-m">'+report.searchCode+'</div></td>';
				}else{
				result = result +'<td><div class="ta-m">----</div></td>';	
				}
			   	result = result +'<td><div class="ta-sm">'+report.unitPrice+'</div></td>';
			   	
			   	if(report.availableInventory != null && report.availableInventory != '' && report.availableInventory != 'undefined'){
			   	result = result +'<td><div class="ta-m">'+report.availableInventory+'</div></td>';
			   	}else{
			   		result = result +'<td><div class="ta-m">----</div></td>';
			   	}
			   	if(report.createdBy != null && report.createdBy != '' && report.createdBy != 'undefined'){
			   	result = result +'<td><div class="ta-sm">'+report.createdBy+'</div></td>';
			   	}else{
			   		result = result +'<td><div class="ta-m">----</div></td>';
			   	}
			   	
			   	result = result +'<td class="action"><div>';
			   	result = result +'<a href="${operoxUrl}/editRawMaterial/'+report.id+'"><i class="fa fa-pencil"></i></a><span id="remove_rawMaterial"><i class="glyphicon glyphicon-trash deme" onClick="mydelPop('+report.id+');"></i></span></div>';
			   	result = result +'</td>';
			   	result = result +'</tr>';
   				}
			   	result = result +'      </tbody>';
			   	result = result +'  </table>';
			   	$("#rawMaterialsListDiv").empty();
			    $("#rawMaterialsListDiv").append(result);
			    pagination();
			    }else{
               	 $("#rawMaterialsListDiv").removeAttr("style");
				 result = result+"			<div class='blank-pages text-center'>";
				 result = result+"			        <p><i class='fa fa-5x fa-bar-chart'></i></p>";
				 result = result+"			        <p>No Raw Materials Found</p>";
				 result = result+"			 </div>";
				$("#rawMaterialsListDiv").empty();
			    $("#rawMaterialsListDiv").append(result); 
             }
 			}
    }); 
}	
</script>

<script>
function pagination(){  
    $('#PaginationRawMaterial').DataTable( { 
    	"scrollCollapse": false,
    	"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
    	"bLengthChange": false,
    	"bDestroy": true,
    	"ordering": false,
    	"oLanguage": {
     		"sSearch": ""
     	}
		
    } );
}
</script>

<script>
function removeRawMaterial(rawMaterialId){ 
	var operoxUrl = '${operoxUrl}';
    $.ajax({
 		   	type: "POST",
 		    url: operoxUrl+"/terminateRawMaterial?${_csrf.parameterName}=${_csrf.token}&rawMaterialId="+rawMaterialId, 	   
    		    success: function(data) {
    		    var result = '';
    		    rawMatterialsListByStatus('Active');
 			}
    }); 
}	
</script>


<script>
// Error pop
	function mydelPop(rawMaterialId){
		$(".delete_pop").show();
		$(".mask").fadeIn(200);
		$("#removeRawMaterialConfirm").attr("onclick", "removeRawMaterial("+rawMaterialId+");");
	}
   
	$(".delete_pop a").click(function(){
		$(".delete_pop").hide();
		$(".mask").fadeOut(200);
	});

</script>

<jsp:include page="../masterFooter.jsp" />