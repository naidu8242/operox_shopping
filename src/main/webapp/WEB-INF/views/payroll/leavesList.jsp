<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="resources/js/formvalidator.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>


<script type="text/javascript">
$(document).ready(function(){
	getLeavesList();
}); 
</script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Leave list</h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home </a>&raquo; Leaves</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>                      
                <li><a href="<c:url value='/addLeaves'/>"><i class="fa fa-plus"></i>Add Leave</a></li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
    	<div>
    	<div id="leavesListDiv"></div>
    	</div>
  </div>
  
</section>
<!-- Content Area Ends--> 

<script type="text/javascript">
function getLeavesList(){
	var operoxUrl = '${operoxUrl}';
		$.ajax({
		type : "GET",
		url : operoxUrl+ "/getLeavesList",
		success : function(data) {
			var json = JSON.parse(data);
	        	var result = "";
	        	var json = JSON.parse(data);
   		    	if(json != null && json != 'undefined' && json != "" && json != '[]'){

   		    	result = result +'  <div class="data_table">';
				result = result +'	<table id="leaveListPagination" class="display" cellspacing="0" width="100%">';
				result = result +'  	<thead>';
				result = result +'      	<tr>';
				result = result +'          	<th>Sl no</th>';
				result = result +'              <th>Leave name</th>';
				result = result +'              <th>Description</th>';
				result = result +'              <th>Updated by</th>';
				result = result +'              <th class="action">Action</th>';
				result = result +'          </tr>';
				result = result +'</thead>';
				result = result +'      <tbody>';
				var j =0;
				for (var i=0; i<json.length; i++)
					{  
				   j++;
			   		var report = json[i];
			   		result = result + '	    <tr>';
					result = result + ' 	<td class="image"><div>'+j+'</div></td>';
					if(report.leaveName != null && report.leaveName != '' && report.leaveName != 'undefined'){
					result = result + '     <td><a href="${operoxUrl}/viewLeave/'+report.id+'"><div class="ta-m">'+report.leaveName+'</div></a></td>';
					}else{
					result = result + '<td><div class="ta-m">--</div></td>';
					}
					if(report.description != null && report.description != '' && report.description != 'undefined'){
					result = result + '     <td><div class="ta-l">'+report.description+'</div></td>';
					}else{
					result = result + '<td><div class="ta-m">--</div></td>';	
					}
					if(report.userName != null && report.userName != '' && report.userName != 'undefined'){
					result = result + '     <td><div class="ta-m">'+report.userName+'</div></td>';
					}else{
					result = result + '<td><div class="ta-m">--</div></td>';	
					}
					result = result + '     <td class="action"><div>';
					result = result + '     <a href="${operoxUrl}/editLeave/'+report.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onClick="deleteLeave('+report.id+');"></i></div>';
					result = result + '		</td>';
					result = result + ' 	</tr>';
			   		}
				result = result +'</tbody>';
			   	result = result +'</table>';
			    $("#leavesListDiv").empty();  
				$("#leavesListDiv").append(result)
				leavePagination();
   		    	}else{
   		    	$("#leavesListDiv").removeAttr("style");
   				 result = result+"			<div class='blank-pages'>";
   				 result = result+"			        <p><center><i class='fa fa-5x fa-bar-chart'></i></center></p>";
   				 result = result+"			        <p><center>No Suppliers Found</center></p>";
   				 result = result+"			 </div>";
   				$("#leavesListDiv").empty();
   			    $("#leavesListDiv").append(result); 
   		    	}
 },
}); 
	        	
}
</script>

<script>
function leavePagination(){ 
    $('#leaveListPagination').DataTable( {
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
function deleteLeave(leaveId){
	var operoxUrl = '${operoxUrl}';
	var message = confirm("Do you want to delete this Leave");
	if (message == true) {
	$.ajax({
	type : "GET",
	url : operoxUrl+ "/deleteLeaves?&leaveId="+leaveId,
	success : function(data) {
		getLeavesList();
	}
	});
	}
	}
</script>

<jsp:include page="../masterFooter.jsp" />