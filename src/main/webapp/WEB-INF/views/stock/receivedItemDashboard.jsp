<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script> 

<script>
$(document).ready(function() {	
	receivedItemList();
});
</script> 
 <script>
function receivedItemList(){ 
	
	var operoxUrl = '${operoxUrl}';   
    $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getReceivedItemList?${_csrf.parameterName}=${_csrf.token}", 	   
   		    success: function(data) {
   		    	if(data != null && data != 'undefined' && data != "" && data != '[]'){
   		    	var json = JSON.parse(data);
   		    	var result = '';
			   	result = result +'  	<table id="example" class="display table table-stripped table-bordered" cellspacing="0" width="100%">';
			   	result = result +'  	<thead>';
			   	result = result +'      	<tr>';
			   	result = result +'          	<th>Received  Id</th>';
			   	result = result +'              <th>Delivery Date</th>';
			   	result = result +'              <th>Location</th>';
			   	result = result +'              <th>Supplier</th>';
			   	result = result +'              <th>Warehouse</th>';
			   	result = result +'              <th>No of products</th>';
			   	result = result +'          </tr>';
			   	result = result +'  	</thead>';
				result = result +'  	<tbody>';
			   	for (var i=0; i<json.length; i++)
   				{   
   		    	var report = json[i];
			   	result = result +'      	<tr>';
			   	if(report.receivedNumber != null && report.receivedNumber != '' && report.receivedNumber != 'undefined'){
			   	result = result +'<td><a href="receivedStockView?receivedStockId='+report.id+'"><div class="ta-m">'+report.receivedNumber+'</div></a></td>';
			   	}else{
			   	result = result +'<td><div class="ta-m">No Data</div></td>';	
			   	}
			   	
			   	if(report.deliveryDateStr != null && report.deliveryDateStr != '' && report.deliveryDateStr != 'undefined'){
			   	result = result +'<td><div class="ta-m">'+report.deliveryDateStr+'</div></td>';
			   	}
			   	else{
				result = result +'<td><div class="ta-m">No Data</div></td>';	
				}
			   	
			   	if(report.storeName != null && report.storeName != '' && report.storeName != 'undefined'){
			   	result = result +'<td><div class="ta-m">'+report.storeName+'</div></td>';
			   	}else{
				result = result +'<td><div class="ta-m">No Data</div></td>';	
			   	}
			   	
			   	if(report.supplierName != null && report.supplierName != '' && report.supplierName != 'undefined'){
			   	result = result +'<td><div class="ta-m">'+report.supplierName+'</div></td>';
			   	}else{
			   	result = result +'<td><div class="ta-m">No Data</div></td>';		
			   	}
			   	
				if(report.wareHouseName != null && report.wareHouseName != '' && report.wareHouseName != 'undefined'){
				result = result +'<td><div class="ta-m">'+report.wareHouseName+'</div></td>';
				}else{
			   	result = result +'<td><div class="ta-m">No Data</div></td>';		
			   	}
				
			 	result = result +'<td><div class="ta-m">'+report.numberOfPackages+'</div></td>';
			   	result = result +'</tr>';
   				}
			   	result = result +'      </tbody>';
			   	result = result +'  </table>';
			    $("#receivedItemList").append(result);
			    pagination();
			    }else{
               	 $("#receivedItemListt").removeAttr("style");
	    	         var result ="";
	    	    
				 result = result+"			<div class='blank-page text-center'>";
				 result = result+"			        <p><i class='fa fa-5x fa-bar-chart'></i></p>";
				 result = result+"			        <p>No Received Item Found</p>";
				 result = result+"			 </div>";
				
				$("#receivedItemListt").empty();
			    $("#receivedItemList").append(result); 
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
	      <h2>Received Stock List</h2>
	    </div>
	    <div class="pull-right brud-crum"><a href="dashboard">Home</a> &raquo; Received Stock List</div>
	  </section>
  
	  <div class="content-area clearfix no_pad">
	  		<div class="data_table_head">
	        	<ul>              
	                <!-- <li><input type="text" placeholder="search"></li> -->            
	                <li><a href="receivedItem"><i class="fa fa-plus"></i>Add Received Stock</a></li>
	                <div class="clearfix"></div>
	            </ul>
	            <div class="clearfix"></div>                 	
	    	</div>
			<div class="data_table">
		    	<div id="receivedItemList"></div>
		    </div>  	
	  </div>
</section>
<!-- Content Area Ends--> 
<jsp:include page="../masterFooter.jsp" /> 