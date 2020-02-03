<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script> 
<script src="resources/js/formvalidator.js"></script>

<script type="text/javascript">
window.onload = function () {
	getDepartmentList();
 };
</script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Department list</h2>
    </div>
    <div class="pull-right brud-crum"><a href="dashboard">Home</a> &raquo; Departments List</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <!-- <li><input type="text" placeholder="search"></li>   -->          
                <li><a href="<c:url value='/addDepartment'/>"><i class="fa fa-plus"></i>Add Department</a></li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
	<div id="departmentsList"></div>
    	 	
  
  </div>
  
</section>
<!-- Content Area Ends--> 
<%-- 
     @author : Ajith Matta.
     this script is to get the  Department list.
     1.usage     :  used in getDepartmentList function
     3.ajax url implementation in DepartmentRestController.
--%>

<script type="text/javascript">
  function getDepartmentList(){
	  var operoxUrl ='${operoxUrl}';
	  $("#departmentsList").empty(); 
	  $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getDepartmentList?${_csrf.parameterName}=${_csrf.token}", 	   
 		    success: function(data) {
 		    	var json = JSON.parse(data);
			    var result = "";
			    if(json.length > 0)
				{
				  result = result + '<div class="data_table">';
				  result = result + '<table id="storePagination" class="display" cellspacing="0" width="100%">';
				  result = result + '<thead>';
				  result = result + '<tr>';
				  result = result + '<th>Department Id</th>';
				  result = result + '<th>Depertment Name</th>';
				  result = result + '<th>Description</th>';
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
					  if(report.departmentId != '' && report.departmentId != null){
					  result = result + '<td><div class="ta-m">'+report.departmentId+'</div></td>';
					  }
					  else{
					  result = result + '<td><div class="ta-m"> --- </div></td>';  
					  }
					  result = result + '<td><div class="ta-m"><a href="${operoxUrl}/viewDepartment/'+report.id+'">'+report.departmentName+'</a></div></td>';
					  if(report.description != '' && report.description != null){
					  result = result + '<td><div class="ta-sm">'+report.description+'</div></td>';
					  }
					  else{
					  result = result + '<td><div class="ta-sm"> --- </div></td>';
 
					  }
					  if(report.updatedBy != null && report.updatedBy != '' && report.updatedBy != 'undefined'){
					  result = result + '<td><div class="ta-m">'+updatedBy+'</div></td>';
					  }
					  else{
					  result = result + '<td><div class="ta-m"> --- </div></td>';  
					  }
					  result = result + '<td class="action"><div>';
					  result = result + '<a href="${operoxUrl}/editDepartment/'+report.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="removeDepartment('+report.id+');"></i></div>';
					  result = result + '</td>';
					  result = result + '</tr>';
				  }
				  result = result + '</tbody>';
				  result = result + '</table>';
				  $("#departmentsList").append(result); 
				  pagination();	
			 }else{
		    		result = "<div class='content-area mt text-center text-muted'><i class='mt fa fa-5x fa-user'></i><h3>Departments List is Empty</h3></div>";
		    		$('#departmentsList').append(result);
		    	 }
	 	  }
	  }); 
  }

</script>

<%-- 
     @author : Ajith Matta.
     this script is to delete the  Department.
     1.usage     :  used in getDepartmentList function
     2.parameter : id,csrf.token
     3.ajax url implementation in DepartmentRestController.
--%>

<script type="text/javascript">
  function removeDepartment(id){
	  var operoxUrl ='${operoxUrl}';
	  var message = confirm("You are choosing to delete the department");
		if (message == true) {
			$.ajax({
			   	type: "POST",
			    url: operoxUrl+"/removeDepartment?${_csrf.parameterName}=${_csrf.token}&id="+id, 	   
	   		    success: function(data) {
	   		    	getDepartmentList();
	   		    }
		     });
		}
  }
  </script>
  <%--
  		@author : Ajith Matta.
  		this method is used for pagination.
   --%>
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

<jsp:include page="../masterFooter.jsp" />

