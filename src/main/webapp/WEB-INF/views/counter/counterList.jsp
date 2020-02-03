<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script> 

<script type="text/javascript">
window.onload = function () {
	getCountersList();
 };
</script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Counter list</h2>
    </div>
    <div class="pull-right brud-crum"><a href="dashboard">Home</a> &raquo; Counters List</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <li><a href="<c:url value='/addCounter'/>"><i class="fa fa-plus"></i>Add Counter</a></li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
	<div id="countersList"></div>	
  
  </div>
  
</section>
<!-- Content Area Ends--> 


<script type="text/javascript">
  function getCountersList(){
	  var operoxUrl ='${operoxUrl}';
	  $("#countersList").empty(); 
	  $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getCountersList?${_csrf.parameterName}=${_csrf.token}", 	   
 		    success: function(data) {
 		    	var json = JSON.parse(data);
				var result = "";
				if(json.length > 0)
				{
				  result = result + '<div class="data_table">';
				  result = result + '<table id="counterPagination" class="display" cellspacing="0" width="100%">';
				  result = result + '<thead>';				  
				  result = result + '<tr>';
				  result = result + '<th>S.NO</th>';
				  result = result + '<th>Location Name</th>';
				  result = result + '<th>Counter Name</th>';
				  result = result + '<th>Counter Type</th>';
				  result = result + '<th>Updated By</th>';
				  result = result + '<th class="action">Action</th>';
				  result = result + '</tr>';
				  result = result + '</thead>';
				  result = result + '<tbody>';
				 
				  var updatedBy = "---";
				  for (var i=0; i<json.length; i++){
					  
					  var report = json[i];
					  if(report.updatedBy != null && report.updatedBy != "" && report.updatedBy != 'undefinde'){
						  updatedBy = report.updatedBy;
					  }
					  
					  result = result + '<tr>';
					  result = result + '<td><div class="ta-m">'+(i+1)+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.storeName+'</div></td>';
					  result = result + '<td><div class="ta-m"><a href="${operoxUrl}/viewCounter/'+report.id+'">'+report.counterName+'</a></div></td>';
					  result = result + '<td><div class="ta-sm">'+report.counterType+'</div></td>';
					  result = result + '<td><div class="ta-m">'+updatedBy+'</div></td>';
					  result = result + '<td class="action"><div>';
					  result = result + '<a href="${operoxUrl}/editCounter/'+report.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="removeCounter('+report.id+');"></i></div>';
					  result = result + '</td>';
					  result = result + '</tr>';
				  }
				  result = result + '</tbody>';
				  result = result + '</table>';
				  $("#countersList").append(result); 
				  pagination();	 
			 }else{
		    		result = "<div class='content-area mt text-center text-muted'><i class='mt fa fa-5x fa-user'></i><h3>Counters List is Empty</h3></div>";
		    		$('#countersList').append(result);
		    	}
	 	   }
	  }); 
  }

</script>

<script>
function pagination(){  
    $('#counterPagination').DataTable( { 
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
  function removeCounter(counterId){
	  var operoxUrl ='${operoxUrl}';
	  var message = confirm("You are choosing to delete the counter");
		if (message == true) {
			$.ajax({
			   	type: "POST",
			    url: operoxUrl+"/removeCounter?${_csrf.parameterName}=${_csrf.token}&counterId="+counterId, 	   
	   		    success: function(data) {
	   		    	getCountersList();
	   		    }
		     });
		}
  }
</script>

<jsp:include page="../masterFooter.jsp" />
