<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	measuringUnitList();
}); 
</script> 

  <section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Measuring Unit List</h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; Measuring Unit</div>
  </section>
  
  <!-- Content Area inner -->
 <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <li><a href="addMeasuringUnit"><i class="fa fa-plus"></i>Add Measuring Unit</a></li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>    
            <div id="measuringUnitsList"></div>             	
    	</div>
  </div>
  
</section>
<!-- Content Area Ends--> 

<div class="mask"></div>
<div class="delete_pop">
		<p>Delete confirmation message....</p>
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

<script type="text/javascript">
  function measuringUnitList(){
	  var operoxUrl ='${operoxUrl}';
	  $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getmeasuringUnitsList?${_csrf.parameterName}=${_csrf.token}", 	   
 		    success: function(data) {
 		    	var json = JSON.parse(data);
   	        	var result = "";
   	        	result = result + '<div class="data_table">';
   	        	result = result + '<table id="measurePagination" class="display" cellspacing="0" width="100%">';
   	        	result = result + '<thead>';
   	        	result = result + '<tr>';
   	        	result = result + '<th>S.no</th>';
   	        	result = result + '<th>Measuring Unit</th>';
   	        	result = result + '<th>Description</th>';
   	        	result = result + '<th>Updated by</th>';
   	        	result = result + '<th class="action">Action</th>';
   	        	result = result + '</tr>';
   	        	result = result + '</thead>';
   	        	result = result + '<tbody>';
   	        	var j=0;
   	        	for (var i=0; i<json.length; i++)
   				{  
   	        		j++;
   	        		var report = json[i];
   	        		result = result + '<tr>';
	   	        	result = result + '<td><div class="ta-m">'+j+'</div></td>';
	   	        	if(report.measuringUnit != null && report.measuringUnit != '' && report.measuringUnit != 'undefined'){
	   	        		result = result + '<td><div class="ta-m"><a href= "${operoxUrl}/viewMeasuringunit/'+report.id+'">'+report.measuringUnit+'</a></div></td>';
	   	        	}else{
	   	        		result = result + '<td><div class="ta-m">--</div></td>';
	   	        	}

	   	        	if(report.description != null && report.description != "" && report.description != 'undefined'){
	   	        		result = result + '<td><div class="ta-sm">'+report.description+'</div></td>';
					  }else{
		   	        	result = result + '<td><div class="ta-m">--</div></td>';
		   	        }
	   	        	
	   	        	if(report.userName != null && report.userName != '' && report.userName != 'undefined'){
	   	        		result = result + '<td><div class="ta-m">'+report.userName+'</div></td>';
	   	        	}else{
	   	        		result = result + '<td><div class="ta-m">--</div></td>';
	   	        	}
	   	        	result = result + '<td class="action"><div>';
			     	result = result + '<a href="${operoxUrl}/editMeasuringUnit/'+report.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="deleteMeasuringUnit('+report.id+');"></i></div>';
	   	        	result = result + '</td>';
	   	        	result = result + '</tr>';
   	        	}
					
   	        	result = result +'</tbody>';
   	        	result = result +'</table>';
   	        	result = result +'</div>';
   	            $("#measuringUnitsList").empty();  
   	    	    $("#measuringUnitsList").append(result)
   	    	 measurePagination();
 		    }
	  }); 
  }

</script>
<script>
function measurePagination(){ 
    $('#measurePagination').DataTable( {
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
<script type="text/javascript">
  function deleteMeasuringUnit(measuringUnitId){
	 var operoxUrl ='${operoxUrl}';
	  var message = confirm("You are choosing to delete the MesuringUnits");
		if (message == true) {
			$.ajax({
			   	type: "POST",
			    url: operoxUrl+"/deleteMeasuringUnit?${_csrf.parameterName}=${_csrf.token}&measuringUnitId="+measuringUnitId, 	   
	   		    success: function(data) {
	   		    	measuringUnitList();
	   		    }
		     });
		}
  }
</script>

<!-- <script>
function pagination(){  
    $('#storePagination').DataTable( { 
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
</script> -->

  
 <jsp:include page="../masterFooter.jsp" />
