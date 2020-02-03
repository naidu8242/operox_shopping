<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	getHolidaysList();
}); 
</script> 

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Holiday list</h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; Holidays</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>                      
                <li><a href="<c:url value='/addHoilday'/>"><i class="fa fa-plus"></i>Add Holiday</a></li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
	<div id="holidayListDiv"> </div>
  </div>
</section>

<script>
  function getHolidaysList(){
				var operoxUrl = '${operoxUrl}';
		 				$.ajax({
							type : "GET",
							url : operoxUrl+ "/getHolidaysList",
							success : function(data) {
								var json = JSON.parse(data);
				   	        	var result = "";
				   	        	result = result + '<div class="data_table">';
				   	        	result = result + '<table id="holidayPagination" class="display" cellspacing="0" width="100%">';
				   	        	result = result + '<thead>';		
				   	        	result = result + '<tr>';
				   	        	result = result + '<th>Holiday id</th>';
				   	        	result = result + '<th>Holiday name</th>';
				   	        	result = result + '<th>Year</th>';
				   	        	result = result + '<th>Holiday Date</th>';
				   	        	result = result + '<th>Updated by</th>';
				   	        	result = result + '<th class="action">Action</th>';
				   	        	result = result + ' </tr>';
				   	        	result = result + '</thead>';
								result = result + '<tbody>';
								var j =0;
				   	        	for (var i=0; i<json.length; i++)
				   				{  
				   	        		j++;
				   	        		var report = json[i];
				   	        		result = result + '<tr>';
					   	        	result = result + '<td><div class="ta-m">'+j+'</div></td>';
					   	        	result = result + '<td><div class="ta-m"><a href= "${operoxUrl}/viewHoliday/'+report.id+'">'+report.holidayName+'</a></div></td>';
					   	        	if(report.year != null && report.year != '' && report.year != 'undefined'){
					   	        		result = result + '<td><div class="ta-m">'+report.year+'</div></td>';
					   	        	}else{
					   	        		result = result + '<td><div class="ta-m">--</div></td>';
					   	        	}
					   	        	result = result + '<td><div class="ta-sm">'+report.holidayDateStr+'</div></td>';
					   	        	
					   	        	if(report.userName != null && report.userName != '' && report.userName != 'undefined'){
					   	        		result = result + '<td><div class="ta-m">'+report.userName+'</div></td>';
					   	        	}else{
					   	        		result = result + '<td><div class="ta-m">--</div></td>';
					   	        	}
					   	        	result = result + '<td class="action"><div>';
					   	        	result = result + '<a href="${operoxUrl}/editHoliday/'+report.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="deleteHoliday('+report.id+');"></i></div>';
					   	        	result = result + '</td>';
					   	        	result = result + '</tr>';
				   	        	}
									
				   	        	result = result +'</tbody>';
				   	        	result = result +'</table>';
				   	        	result = result +'</div>'; 
				   	            $("#holidayListDiv").empty();  
				   	    	    $("#holidayListDiv").append(result);
				   	    	    holidayPagination(); 
							},
						}); 
  }

</script>

<script>
function holidayPagination(){ 
    $('#holidayPagination').DataTable( {
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
function deleteHoliday(holidayId){
	var operoxUrl = '${operoxUrl}';
	var message = confirm("Do you want to delete this Holiday");
	if (message == true) {
		$.ajax({
			type : "GET",
			url : operoxUrl+ "/deleteHoliday?&holidayId="+holidayId,
			success : function(data) {
				getHolidaysList();
			},
		}); 
	}
}
</script>
<!-- Content Area Ends--> 

<jsp:include page="../masterFooter.jsp" />