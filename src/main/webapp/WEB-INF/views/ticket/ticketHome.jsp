<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>



<script type="text/javascript">
$(document).ready(function(){
	getTicketsList();
}); 
</script> 

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Ticket list</h2>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Ticket</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <li><a href="<c:url value='/addTicket'/>"><i class="fa fa-plus"></i>Add Ticket</a></li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
    	<div id="ticketList"></div>
  </div>
  
</section>


<script>
  function getTicketsList(){
				var operoxUrl = '${operoxUrl}';
				$.ajax({
					type : "GET",
					url : operoxUrl+ "/getTicketsList",
					success : function(data) {
						var json = JSON.parse(data);
		   	        	var result = "";
		   	        	result = result + '<div class="data_table">';
		   	        	result = result + '<table id="ticketPagination" class="display" cellspacing="0" width="100%">';
		   	        	result = result + '<thead>';		
		   	        	result = result + '<tr>';
		   	        	result = result + '<th>Ticket id</th>';
		   	        	result = result + '<th>Subject</th>';
		   	        	result = result + ' <th>Source Type</th>';
		   	        	result = result + '<th>Store</th>';
		   	        	result = result + '<th>Customer Name</th>';
		   	        	result = result + '<th>Assign To</th>';
		   	        	result = result + '<th>Status</th>';
		   	        	result = result + '<th class="action">Action</th>';
		   	        	result = result + ' </tr>';
		   	        	result = result + '</thead>';
						result = result + '<tbody>';
		   	        	
		   	        	for (var i=0; i<json.length; i++)
		   				{  
		   	        		var report = json[i];
		   	        		result = result + '<tr>';
			   	        	result = result + '<td><div class="ta-m">'+report.id+'</div></td>';
			   	        	result = result + '<td><div class="ta-m"><a href= "${operoxUrl}/ticketView/'+report.id+'">'+report.subject+'</a></div></td>';
			   	        	if(report.sourceType != null && report.sourceType != '' && report.sourceType != 'undefined'){
			   	        		result = result + '<td><div class="ta-m">'+report.sourceType+'</div></td>';
			   	        		}else{
			   	        		result = result + '<td><div class="ta-m">--</div></td>';
			   	        		}
			   	        	result = result + '<td><div class="ta-sm">'+report.store.id+'</div></td>';
			   	        	if(report.customerName != null && report.customerName != '' && report.customerName != 'undefined'){
			   	        		result = result + '<td><div class="ta-m">'+report.customerName+'</div></td>';
			   	        	}
			   	        	else{
			   	        		result = result + '<td><div class="ta-m">--</div></td>';
			   	        	}
			   	        	result = result + '<td><div class="ta-m">'+report.assignedUserName+'</div></td>';
			   	        	result = result + '<td><div class="ta-m">'+report.ticketStatus+'</div></td>';
			   	        	result = result + '<td class="action"><div>';
			   	        	result = result + '<a href="${operoxUrl}/editTicket/'+report.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="deleteTicket('+report.id+');"></i></div>';
			   	        	result = result + '</td>';
			   	        	result = result + '</tr>';
		   	        	}
							
		   	        	result = result +'</tbody>';
		   	        	result = result +'</table>';
		   	            $("#ticketList").empty();  
		   	    	    $("#ticketList").append(result)
		   	    	    ticketPagination();
							},
						}); 
  }

</script>

<script>
function deleteTicket(ticketId){
	var operoxUrl = '${operoxUrl}';
	var message = confirm("Do you want to delete this Ticket");
	if (message == true) {
		$.ajax({
			type : "GET",
			url : operoxUrl+"/deleteTicket?&ticketId="+ticketId,
			success : function(data) {
				getTicketsList();
			},
		}); 
	}
}
</script>

<!-- <script>
function removeTicket(ticketId){
	alert("ticketId..."+ticketId)
	var operoxUrl = '${operoxUrl}';
	var message = confirm("Do you want to delete this Ticket");
	if (message == true) {
		$.ajax({
			type : "GET",
			url : operoxUrl+ "/removeTicket?&ticketId="+ticketId,
			success : function(data) {
				getTicketsList();
			},
		}); 
	}else{
		alert()
	}
}
</script>  -->

<script>
function ticketPagination(){ 
    $('#ticketPagination').DataTable( {
        "scrollCollapse": false,
        "lengthMenu": [[5, 15, 20, -1], [5, 15, 20, "All"]],
        "bLengthChange": false,
        "bDestroy": true,
        "ordering": false,
        "oLanguage": {
             "sSearch": ""
         }
       
    } );
}
</script>

!--  Error Message  -->
<div class="mask"></div>
<div class="delete_pop">
		<p>Delete confirmation message....</p>
		<a class="close_color" href="javascript:void(0)">Yes</a><a href="javascript:void(0)">No</a> 
	</div>

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
  
<jsp:include page="../masterFooter.jsp" />

<!-- Footer Area -->
