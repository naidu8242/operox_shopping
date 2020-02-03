<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   

<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>  

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Supplier List</h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; Supplier</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <li> <a href="<c:url value='/addSupplier'/>"><i class="fa fa-plus"></i>Add Supplier</a></li>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
	<div class="data_table">
	<div id="suppliersListDiv"></div>
    </div>  	
  
  </div>
  
</section>
<!-- Content Area Ends--> 
<!--  Error Message  -->
<div class="mask"></div>
<div class="delete_pop">
		<p>Delete Confirmation message....</p>
		<a class="close_color" href="javascript:void(0)" id="removeSupplierConfirm">Yes</a><a href="javascript:void(0)">No</a> 
	</div>
<!--  Error Message  -->

<script>
	$(document).ready(function() {	
 	suppliersListByStatus('Active');
 	//for inactive suppliers
 	//suppliersList('IN-Active');
});
</script>

<script>
function suppliersListByStatus(status){ 
	var operoxUrl = '${operoxUrl}';
    $.ajax({
 		   	type: "POST",
 		    url: operoxUrl+"/getSuppliersListByStatus?${_csrf.parameterName}=${_csrf.token}&status="+status, 	   
    		    success: function(data) {
    		    var result = '';
    		    var json = JSON.parse(data);
   		    	if(json != null && json != 'undefined' && json != "" && json != '[]'){
   		    	result = result +'	<table id="supplierListPagination" class="display" cellspacing="0" width="100%">';
   		    	result = result +'	<thead>';
   		    	result = result +'		<tr>';
   		    	result = result +'	    	<th>Supplier id</th>';
   		    	result = result +'	        <th>Supplier name</th>';
   		    	result = result +'	        <th>Contact Name</th>';                  
   		    	result = result +'	        <th>Contact number</th>';
   		    	result = result +'	        <th>Location</th>';
   		    	result = result +'	        <th>Updated by</th>';
   		    	result = result +'	        <th class="action">Action</th>';
   		    	result = result +'	    </tr>';
   		    	result = result +'	</thead>';
   		    	result = result +'  <tbody>';
			   	for (var i=0; i<json.length; i++)
   				{
   		    	var report = json[i];
   		    	var city = "";
   		    	var state = "";
   		    	var country = "";
   		    	if(report.address.city != null && report.address.city != 'undefined' && report.address.city != ""){
   		    		city = report.address.city+" ,";
   		    	}
   		    	
				if(report.address.state != null && report.address.state != 'undefined' && report.address.state != ""){
					state = report.address.state+" ,";
   		    	}
   		    	
				if(report.address.country != null && report.address.country != 'undefined' && report.address.country != ""){
					country = report.address.country;
				}
   		    	
				var index = i+1;
			   	result = result +'<tr>';
			   	result = result +'<td><div class="ta-m">'+index+'</div></td>';
			   	result = result +'<td><a href="${operoxUrl}/supplierView/'+report.id+'"><div class="ta-m" style="color:#87CEFA;">'+report.name+'</div></a></td>';
				
			   	if(report.supplierContactName != null && report.supplierContactName != 'undefined' && report.supplierContactName != ''){
			   	result = result +'<td><div class="ta-m">'+report.supplierContactName+'</div></td>';
				}else{
					result = result +'<td><div class="ta-m">----</div></td>';
				}
			   	
			   	if(report.supplierContactPhone != null && report.supplierContactPhone != 'undefined' && report.supplierContactPhone != ''){
			   	result = result +'<td><div class="ta-sm">'+report.supplierContactPhone+'</div></td>';
			   	}else{
			   		result = result +'<td><div class="ta-sm">----</div></td>';
			   	}
			   	
			   	result = result +'<td><div class="ta-m">'+city+''+state+''+country+'</div></td>';
			   	
				if(report.createdBy != null && report.createdBy != 'undefined' && report.createdBy != ''){
			   	result = result +'<td><div class="ta-sm">'+report.createdBy+'</div></td>';
				}else{
					result = result +'<td><div class="ta-sm">----</div></td>';
				}
				
			   	result = result +'<td class="action"><div>';
			   	result = result +'	<a href="${operoxUrl}/editSupplier/'+report.id+'"><i class="fa fa-pencil"></i></a><span id="remove_supplier"><i class="glyphicon glyphicon-trash deme" onClick="mydelPop('+report.id+');"></i></span></div>';
			   	result = result +'</td>';
			   	result = result +'</tr>';
   				}
			   	result = result +'      </tbody>';
			   	result = result +'  </table>';
			   	$("#suppliersListDiv").empty();
			    $("#suppliersListDiv").append(result);
			    SupplierPagination();
			    }else{
               	 $("#suppliersListDiv").removeAttr("style");
				 result = result+"			<div class='blank-pages'>";
				 result = result+"			        <p><center><i class='fa fa-5x fa-bar-chart'></i></center></p>";
				 result = result+"			        <p><center>No Suppliers Found</center></p>";
				 result = result+"			 </div>";
				$("#suppliersListDiv").empty();
			    $("#suppliersListDiv").append(result); 
			   
             }
 			}
    }); 
}	
</script>

<script>
function removeSupplier(supplierId){ 
	var operoxUrl = '${operoxUrl}';
    $.ajax({
 		   	type: "POST",
 		    url: operoxUrl+"/terminateSupplier?${_csrf.parameterName}=${_csrf.token}&supplierId="+supplierId, 	   
    		    success: function(data) {
    		    var result = '';
    		    suppliersListByStatus('Active');
 			}
    }); 
}	
</script>


<script>
// Error pop
function mydelPop(supplierId){
	$(".delete_pop").show();
	$(".mask").fadeIn(200);
	$("#removeSupplierConfirm").attr("onclick", "removeSupplier("+supplierId+");");
}
   /* $(".glyphicon-trash").click(function(){
		$(".delete_pop").show();
		$(".mask").fadeIn(200);
	}); */
	$(".delete_pop a").click(function(){
		$(".delete_pop").hide();
		$(".mask").fadeOut(200);
	});

</script>

<script>
function SupplierPagination(){ 
    $('#supplierListPagination').DataTable( {
        "scrollCollapse": false,
        "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
        "bLengthChange": false,
        "bDestroy": true,
        "ordering": false,
        "oLanguage": {
             "sSearch": ""
         }
       
    } );
}
</script>


</body>
</html>

<jsp:include page="../masterFooter.jsp" />
