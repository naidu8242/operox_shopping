<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script> 

<script type="text/javascript">
window.onload = function () {
	getStoresList();
 };
</script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Location list</h2>
    </div>
    <div class="pull-right brud-crum"><a href="dashboard">Home</a>&raquo; Locations List</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <!-- <li><input type="text" placeholder="search"></li>-->    
                <li><a href="<c:url value='/addStore'/>"><i class="fa fa-plus"></i>Add Location</a></li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
    	<div id="storesList"></div>
	 	
  
  </div>
  
</section>
<!-- Content Area Ends--> 


<script type="text/javascript">
  function getStoresList(){
	  var operoxUrl ='${operoxUrl}';
	  $("#storesList").empty(); 
	  $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getStoresList?${_csrf.parameterName}=${_csrf.token}", 	   
 		    success: function(data) {
 		    	var json = JSON.parse(data);
				var result = "";
				if(json.length > 0)
			   	{
				  result = result + '<div class="data_table">';
				  result = result + '<table id="storePagination" class="display" cellspacing="0" width="100%">';
				  result = result + '<thead>';
				  result = result + '<tr>';
				  result = result + '<th>S.NO</th>';
				  result = result + '<th>Name</th>';
				  result = result + '<th>Location</th>';
				  result = result + '<th>Type</th>';
				  result = result + '<th>Phone Number</th>';
				  result = result + '<th>Updated By</th>';
				  result = result + '<th class="action">Action</th>';
				  result = result + '</tr>';
				  result = result + '</thead>';
				  result = result + '<tbody>';
				 
				  
				  for (var i=0; i<json.length; i++){
					  
					  var phone = "---";
					  var updatedBy = "---";
					  
					  var report = json[i];
					  
					  if(report.phoneNumber != null && report.phoneNumber != "" && report.phoneNumber != 'undefinde'){
						  phone = report.phoneNumber;
					  }
					  if(report.updatedBy != null && report.updatedBy != "" && report.updatedBy != 'undefinde'){
						  updatedBy = report.updatedBy;
					  }
					  
					  result = result + '<tr>';
					  result = result + '<td><div class="ta-m">'+(i+1)+'</div></td>';
					  result = result + '<td><div class="ta-m"><a href="${operoxUrl}/viewStore/'+report.id+'">'+report.storeName+'</a></div></td>';
					  result = result + '<td><div class="ta-m">'+report.city+' ,'+report.state+', '+report.country+' </div></td>';
					  result = result + '<td><div class="ta-sm">'+report.storeType+'</div></td>';
					  result = result + '<td><div class="ta-sm">'+phone+'</div></td>';
					  result = result + '<td><div class="ta-m">'+updatedBy+'</div></td>';
					  result = result + '<td class="action"><div>';
					  result = result + '<a href="${operoxUrl}/editStore/'+report.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="removeStore('+report.id+');"></i></div>';
					  result = result + '</td>';
					  result = result + '</tr>';
				  }
				  result = result + '</tbody>';
				  result = result + '</table>';
				  $("#storesList").append(result); 
				  pagination();	 
 		    }else{
	    		result = "<div class='content-area mt text-center text-muted'><i class='mt fa fa-5x fa-user'></i><h3>Stores List is Empty</h3></div>";
	    		$('#storesList').append(result);
	    	}
 		  }
	  }); 
  }

</script>

<script>
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
</script>

<script type="text/javascript">
  function removeStore(storeId){
	  var operoxUrl ='${operoxUrl}';
	  var message = confirm("You are choosing to delete the store");
		if (message == true) {
			$.ajax({
			   	type: "POST",
			    url: operoxUrl+"/removeStore?${_csrf.parameterName}=${_csrf.token}&storeId="+storeId, 	   
	   		    success: function(data) {
	   		    	getStoresList();
	   		    }
		     });
		}
  }
</script>

<jsp:include page="../masterFooter.jsp" />
