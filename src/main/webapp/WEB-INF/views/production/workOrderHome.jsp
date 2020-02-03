<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>



<script type="text/javascript">
window.onload = function () {
	getWorkOrderList();
 };
</script>

<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Work Order</h2>
    </div>
  </section>
  
   
       <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <li><a href="addWorkOrder"><i class="fa fa-plus"></i>Add Work Order</a></li>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
    	
      <div class="data_table">
        <table id="workOrderListBody" class="display" cellspacing="0" width="100%"> </table>
      </div>
     
  
  </div>
  
</section>

<jsp:include page="../masterFooter.jsp" />






<script type="text/javascript">
	function getWorkOrderList(){
		
		var operoxUrl = '${operoxUrl}';
		 
	    $('#workOrderListBody').empty();
		
		
		 $.ajax({ 
	    	type: "POST",
	    	url: operoxUrl+"/getWorkOrderList?${_csrf.parameterName}=${_csrf.token}",
	    			 
            success: function(data) {
	        var json = JSON.parse(data);
	        var result = "";
	        
	        if(json.length > 0)
	   		{
	        
	        	    result = result + '<thead>';
	 				result = result +'<tr>';
	 				result = result +'<th>Work Order Id</th>';
	 				result = result +' <th>Order Date</th>';
	 				result = result +'<th>Due Date</th>';
	 				result = result +' <th>Customer</th>';
	 				result = result +' <th>Status</th>'
	 				result = result +'<th class="action">Action</th>';
	 				result = result +'</tr>';
 				    result = result + '</thead>';
 				    result = result +'<tbody>';
	 			for(var i=0; i<json.length; i++){
	 				
	 				var workorder = json[i];
	 			    
	 				var orderDate = '';
	 				var dueDate = '';
	 				var status = '';
	 				var customer = '';
	 				
	 				if(workorder.orderDateStr != null && workorder.orderDateStr != '' && workorder.orderDateStr != 'undefined'){
	 					orderDate = workorder.orderDateStr;
	 				}else{
	 					orderDate = "----"
	 				}
	 				
	 				if(workorder.dueDateStr != null && workorder.dueDateStr != '' && workorder.dueDateStr != 'undefined'){
	 					dueDate = workorder.dueDateStr;
	 				}else{
	 					dueDate = "----"
	 				}
	 				
	 				if(workorder.workOrderStatus != null && workorder.workOrderStatus != '' && workorder.workOrderStatus != 'undefined'){
	 					status = workorder.workOrderStatus;
	 				}else{
	 					status = "----"
	 				}
	 				
	 				if(workorder.customerName != null && workorder.customerName != '' && workorder.customerName != 'undefined'){
	 					customer = workorder.customerName;
	 				}else{
	 					customer = "----"
	 				}
	 				
	 				
	 				result = result +'<tr>';
	 				result = result +' <td><a href="#"><div class="ta-sm"><a href= "${operoxUrl}/viewWorkOrder/'+workorder.id+'/details">'+workorder.workOrderNumber+'</a></div></a></td>';
	 				result = result +'<td><div class="ta-m">'+orderDate+'</div></td>';
	 				result = result +'<td><div class="ta-sm">'+dueDate+'</div></td>';
	 				result = result +'<td><div class="ta-sm">'+customer+'</div></td>';
	 				result = result +'<td><div class="ta-sm">'+status+'</div></td>';
	 				result = result +'<td class="action"><div>';
	 				result = result +'<i class="glyphicon glyphicon-trash deme" onclick="removeWorkOrder('+workorder.id+')"></i></div>';
	 				result = result +'</td>';
	 				result = result +'</tr>';
				   }
	 			
	 			 result = result +'</tbody>';
	 			 
	 			 $('#workOrderListBody').append(result);
	       	 	 pagination();
	   		}else{
	    		result = "<div class='content-area mt text-center text-muted'><i class='mt fa fa-5x fa-user'></i><h3>Work Order List is Empty</h3></div>";
	    		$('#workOrderListBody').append(result);
	    	}

			 },
	        error: function(e){
	        }
	    });   
	}
</script>

<script>
function pagination(){  
    $('#workOrderListBody').DataTable( { 
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

<script>
function removeWorkOrder(workorderId){  
	
	if(workorderId){
	      var operoxUrl ='${operoxUrl}';
	        $.ajax({
		    	type: "POST",
		    	 url: operoxUrl+"/removeWorkorder?${_csrf.parameterName}=${_csrf.token}&workorderId="+workorderId, 
		        success: function(result) {
		        	if((result == 'success')){
		        		location.replace(operoxUrl+"/workOrder");  
		        	}
			        
		        },
		    });
	}
}
</script>

