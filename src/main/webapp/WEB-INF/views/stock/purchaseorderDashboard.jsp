<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />


<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script> 


 <script>
// Edit pop
$(document).ready(function() {	
	
	purchaseOrderList();
		
});
</script> 

 <script>
function purchaseOrderList(){ 
	var operoxUrl = '${operoxUrl}';  
    $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getPurchaseOrderList?${_csrf.parameterName}=${_csrf.token}", 	   
   		    success: function(data) {
   		    	if(data != null && data != 'undefined' && data != "" && data != '[]'){
   		    	var json = JSON.parse(data);
   		    	var result = '';
			   	result = result +'  	<table id="example" class="display table table-stripped table-bordered" cellspacing="0" width="100%">';
			   	result = result +'  	<thead>';
			   	result = result +'      	<tr>';
			   	result = result +'          	<th>Purchase Id</th>';
			   	result = result +'              <th>Order Date</th>';
			   	result = result +'              <th>Delivery Date</th>';
			   	result = result +'              <th>Supplier</th>';
			   	result = result +'              <th>Status</th>';
			   	result = result +'              <th>Updated By</th>';
			   	result = result +'          </tr>';
				result = result +'  	</thead>';
				result = result +'  	<tbody>';
			   	for (var i=0; i<json.length; i++)
   				{   
   		    	var report = json[i];
			   	result = result +'      	<tr>';
			   	result = result +'              <td><a href="purchaseorderView?productId='+report.id+'"><div class="ta-m">'+report.purchaseNumber+'</div></a></td>';
			   if(report.orderDateStr != null && report.orderDateStr != '' && report.orderDateStr != 'undefined'){
			   	result = result +'<td><div class="ta-m">'+report.orderDateStr+'</div></td>';
			   }else{
				result = result +'<td><div class="ta-m">NO DATA</div></td>';
			   }
			   if(report.deliveryDateStr != null && report.deliveryDateStr != '' && report.deliveryDateStr != 'undefined'){
			   	result = result +'              <td><div class="ta-m">'+report.deliveryDateStr+'</div></td>';
			   }else{
				result = result +'<td><div class="ta-m">----</div></td>';
			   }
			   
			   if(report.supplierName != null && report.supplierName != '' && report.supplierName != 'undefined'){
			   	result = result +'              <td><div class="ta-m">'+report.supplierName+'</div></td>';
			   }else{
				result = result +'<td><div class="ta-m">----</div></td>';
			   }
			   	result = result +'              <td class="action"><div class ="tbldata-dotdot center-div">';
				result = result +' 				<select class="selList status" onchange="changeOrderStatus(this.value,'+report.id+')">';
				
				if(report.orderStatus != "" && report.orderStatus != "undefined" && report.orderStatus == "PENDING"){
					result = result +'					<option selected="selected" value="pending">Pending</option>';
					result = result +'					<option value="complete">Complete</option>';
				}
				if(report.orderStatus != "" && report.orderStatus != "undefined" && report.orderStatus == "COMPLETE"){
					result = result +'					<option value="pending">Pending</option>';
					result = result +'					<option selected="selected" value="complete">Complete</option>';
				}
				result = result +'				</select>';
			   	result = result +'				</div></td>';
			   	
			    if(report.updatedBy != null && report.updatedBy != '' && report.updatedBy != 'undefined'){
			 	result = result +'              <td><div class="ta-m">'+report.updatedBy+'</div></td>';
			    }else{
			    result = result +'<td><div class="ta-m">----</div></td>';
			    }
			    
			   	result = result +'          </tr>';
   				}
			   	result = result +'      </tbody>';
			   	result = result +'  </table>';
			    $("#purchaseOrderList").append(result);
			    pagination();
			    }else{
               	 $("#purchaseOrderList").removeAttr("style");
	    	         var result ="";
	    	    
				 result = result+"			<div class='blank-page text-center'>";
				 result = result+"			        <p><i class='fa fa-5x fa-bar-chart'></i></p>";
				 result = result+"			        <p>No Purchase Order Found</p>";
				 result = result+"			 </div>";
				
				$("#purchaseOrderList").empty();
			    $("#purchaseOrderList").append(result); 
			    
			    
             }

			}
    }); 
    

}	

</script>

<script>
function pagination(){
    $("#example").DataTable( { 
    	"scrollCollapse": false,
    	"lengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
    	"bLengthChange": false,
    	"bDestroy": true,
    	"ordering": false,
    	"oLanguage": {
     		"sSearch": ""
     	}
    } );
}
</script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Purchase Order List</h2>
    </div>
    <div class="pull-right brud-crum"><a href="dashboard">Home</a> &raquo; Purchase Order List</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <!-- <li><input type="text" placeholder="search"></li>      -->       
                <li><a href="purchaseorder"><i class="fa fa-plus"></i>Add Purchase Order</a></li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
	<div class="data_table">
    <div id="purchaseOrderList"></div>
<!-- <nav>
	<ul id="paging" class="pagination">
    	<li><a href="#">Previous</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li class="disabled"><a href="#">6</a></li>
        <li data-page="last"><a href="#">Next</a></li>
	</ul>
    <div class="clearfix"></div>
</nav>      -->   
        
    </div>  	
  
  </div>
  
</section>
<!-- Content Area Ends--> 

 <jsp:include page="../masterFooter.jsp" /> 

<!--  Error Message  -->
<div class="mask"></div>
<div class="delete_pop">
		<p>Delete conformation message....</p>
		<a class="close_color" href="javascript:void(0)">Yes</a><a href="javascript:void(0)">No</a> 
	</div>
<!--  Error Message  -->

<script>
// Error pop
$(document).ready(function() {
    $(".glyphicon-trash").click(function(){
		$(".delete_pop").show();
		$(".mask").fadeIn(200);
	});
	$(".delete_pop a").click(function(){
		$(".delete_pop").hide();
		$(".mask").fadeOut(200);
	});
});
</script>

<script>
function changeOrderStatus(local, orderId){
	var operoxUrl = '${operoxUrl}';
	var orderStatus = local;
    $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/changeOrderStatus?${_csrf.parameterName}=${_csrf.token}&orderStatus="+orderStatus+"&orderId="+orderId, 	   
   		    success: function(data) {
   		    	
   		    }
    });
}
</script>

</body>
</html>