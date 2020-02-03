<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>

  <script type="text/javascript">
window.onload = function () {
	getCategoryList();
 };
 </script>
 
 <!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Category list</h2>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Add Category</div>
  </section>

  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <li><a href="<c:url value='/addCategory'/>"><i class="fa fa-plus"></i>Add Category</a></li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>    
            <div id="categoryList"></div>             	
    	</div>
  
  </div>
  
</section>
<!-- Content Area Ends--> 

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
  function getCategoryList(){
	  var operoxUrl ='${operoxUrl}';
	  $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getCategoryList?${_csrf.parameterName}=${_csrf.token}", 	   
 		    success: function(data) {
 		    	var json = JSON.parse(data);
				  var result = "";
				  result = result + '<div class="data_table">';
				  result = result + '<table id="categoryPagination">';
				  result = result + '<thead>';				  
				  result = result + '<tr>';
				  result = result + '<th>S.no</th>';
				  result = result + '<th>Category name</th>';
				  result = result + '<th>Description</th>';
				  result = result + '<th>Updated by</th>';
				  result = result + '<th class="action">Action</th>';
				  result = result + '</tr>';
				  result = result + '</thead>';
				  result = result + '<tbody>';				 
				  var j=0;
				  for (var i=0; i<json.length; i++){
					  var description = "---";
					  
					  var report = json[i];
					  
					  if(report.description != null && report.description != "" && report.description != 'undefind'){
						  description = report.description;
					  }
					  result = result + '<tr>';
					  result = result + '<td><div class="ta-m">'+(i+1)+'</div></td>';
					  result = result + '<td><div class="ta-m"><a href= "${operoxUrl}/viewCategory/'+report.id+'">'+report.categoryName+'</a></div></td>';
					  result = result + '<td><div class="ta-m">'+report.description+'</div></td>';
					  result = result + '<td><div class="ta-sm">'+report.userName+'</div></td>';
					  result = result + '<td class="action"><div>';
					  result = result + '<a href="${operoxUrl}/editCategory/'+report.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="removeCategory('+report.id+');"></i></div>';
					  result = result + '</td>';
					  result = result + '</tr>';
				  }
				  result = result + '</tbody>';
				  result = result + '</table>';
				  $("#categoryList").empty(); 
				  $("#categoryList").append(result); 
				  categoryPagination();	 
 		    }
	  }); 
  }
</script>

<script>
function categoryPagination(){  
    $('#categoryPagination').DataTable( { 
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
  function removeCategory(categoryId){
	  var operoxUrl ='${operoxUrl}';
	  var message = confirm("Do you want to delete this category");
		if (message == true) {
			$.ajax({
			   	type: "POST",
			    url: operoxUrl+"/removeCategory?${_csrf.parameterName}=${_csrf.token}&categoryId="+categoryId, 	   
	   		    success: function(data) {
	   		    	getCategoryList();
	   		    }
		     });
		}
  }
</script>


<jsp:include page="../masterFooter.jsp" />
