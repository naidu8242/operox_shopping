<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>



<script type="text/javascript">
$(document).ready(function(){
	getCustomersList();
}); 
</script> 

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Customer list</h2>
    </div>
    <div class="pull-right brud-crum"><a href="dashboard">Home</a> &raquo; Customers</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <li><a href="<c:url value='/addCustomer'/>"><i class="fa fa-plus"></i>Add Customer</a></li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
    	<div id="customerList"></div>
  </div>
  
</section>

<script>
  function getCustomersList(){
				var operoxUrl = '${operoxUrl}';
		 				$.ajax({
							type : "GET",
							url : operoxUrl+ "/getCustomersList",
							success : function(data) {
								var json = JSON.parse(data);
				   	        	var result = "";
				   	        	result = result + '<div class="data_table">';
				   	        	result = result + '<table id="customerPagination" class="display" cellspacing="0" width="100%">';
				   	        	result = result + '<thead>';		
				   	        	result = result + '<tr>';
				   	        	result = result + '<th>S.no</th>';
				   	        	result = result + '<th>Customer name</th>';
				   	        	result = result + ' <th>Phone number</th>';
				   	        	result = result + '<th>Store loyalty number</th>';
				   	        	result = result + '<th>Updated by</th>';
				   	        	result = result + '<th class="action">Action</th>';
				   	        	result = result + ' </tr>';
				   	        	result = result + '</thead>';
								result = result + '<tbody>';
				   	        	var j=0;
				   	        	for (var i=0; i<json.length; i++)
				   				{  
				   	        		j++;
				   	        		var report = json[i];
				   	        		result = result + '<tr>';
					   	        	result = result + '<td><div class="ta-m">'+j+'</div></td>';
					   	        	if(report.customerName != null && report.customerName != '' && report.customerName != 'undefined'){
					   	        		result = result + '<td><div class="ta-m">'+report.customerName+'</div></td>';
					   	        	}else{
					   	        		result = result + '<td><div class="ta-m">--</div></td>';
					   	        	}
					   	        	result = result + '<td><div class="ta-sm"><a href= "${operoxUrl}/viewCustomer/'+report.id+'">'+report.phone+'</a></div></td>';
					   	        	result = result + '<td><div class="ta-m">'+report.customeruid+'</div></td>';
					   	        	
					   	        	if(report.userName != null && report.userName != '' && report.userName != 'undefined'){
					   	        		result = result + '<td><div class="ta-m">'+report.userName+'</div></td>';
					   	        	}else{
					   	        		result = result + '<td><div class="ta-m">--</div></td>';
					   	        	}
					   	        	result = result + '<td class="action"><div>';
					   	        	result = result + '<a href="${operoxUrl}/editCustomer/'+report.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="deleteCustomer('+report.id+');"></i></div>';
					   	        	result = result + '</td>';
					   	        	result = result + '</tr>';
				   	        	}
									
				   	        	result = result +'</tbody>';
				   	        	result = result +'</table>';
// 				   	        	result = result +'</div>';
				   	            $("#customerList").empty();  
				   	    	    $("#customerList").append(result)
				   	    	    customerPagination();
							},
						}); 
  }

</script>

<script>
function customerPagination(){ 
    $('#customerPagination').DataTable( {
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
function deleteCustomer(customerId){
	var operoxUrl = '${operoxUrl}';
	var message = confirm("Do you want to delete this Customer");
	if (message == true) {
		$.ajax({
			type : "GET",
			url : operoxUrl+ "/deleteCustomer?&customerId="+customerId,
			success : function(data) {
				getCustomersList();
			},
		}); 
	}
}
</script>

<!-- Content Area Ends--> 

<jsp:include page="../masterFooter.jsp" />

<!-- Footer Area -->
