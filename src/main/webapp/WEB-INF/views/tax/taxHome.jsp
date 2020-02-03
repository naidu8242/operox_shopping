<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script> 



<script type="text/javascript">
window.onload = function () {
	getTaxList();
 };
</script>


<div class="mask"></div>
<div class="delete_pop">
		<p>Are you sure ?</p>
		<a class="close_color" href="javascript:void(0)" id="removeShiftConfirm">Yes</a><a href="javascript:void(0)">No</a> 
	</div>




<div class="wraper clearfix">

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Tax list</h2>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Tax</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <li><a href="addTax"><i class="fa fa-plus"></i>Add Tax</a></li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>  
            <div id="taxList"></div>               	
    	</div>
  </div>
</section>



<script type="text/javascript">
  function getTaxList(){
	
	  var operoxUrl ='${operoxUrl}';
	  $("#taxList").empty(); 
	  $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getTaxList?${_csrf.parameterName}=${_csrf.token}", 	   
 		    success: function(data) {
 		    	var json = JSON.parse(data);
				var result = "";
				if(json.length > 0)
			   	{
				  result = result + '<div class="data_table">';
				  result = result + '<table id="taxPagination" class="display" cellspacing="0" width="100%">';
				  result = result + '<thead>';
				  result = result + '<tr>';
				  result = result + '<th>Tax Name</th>';
				  result = result + '<th>Category</th>';
				  result = result + '<th>Tax Value</th>';
				  result = result + '<th>Updated by</th>';
				  result = result + '<th class="action">Action</th>';
				  result = result + '</tr>';
				  result = result + '</thead>';
				  result = result + '<tbody>';
				 
				  
				  for (var i=0; i<json.length; i++){
					  
					  var phone = "---";
					  var updatedBy = "---";
					  
					  var report = json[i];
					  result = result + '<tr>';
					  
					  if(report.taxName != null && report.taxName != "" && report.taxName != 'undefinde'){
						  result = result + '<td><div class="ta-m"><a href="${operoxUrl}/taxView/'+report.id+'">'+report.taxName+'</a></div></td>';
					  }else{
						  result = result + '<td><div class="ta-m">No Data</div></td>';
					  }
					  if(report.categoryName != null && report.categoryName != "" && report.categoryName != 'undefinde'){
						  result = result + '<td><div class="ta-sm">'+report.categoryName+'</div></td>';
					  }else{
						  result = result + '<td><div class="ta-m">No Data</div></td>';
					  }
					  if(report.taxValue != null && report.taxValue != "" && report.taxValue != 'undefinde'){
						  result = result + '<td><div class="ta-sm">'+report.taxValue+'</div></td>';
					  }else{
						  result = result + '<td><div class="ta-m">No Data</div></td>';
					  }
					  if(report.updatedBy != null && report.updatedBy != "" && report.updatedBy != 'undefinde'){
						  result = result + '<td><div class="ta-sm">'+report.updatedBy+'</div></td>';
					  }else{
						  result = result + '<td><div class="ta-m">No Data</div></td>';
					  }
					  result = result + '<td class="action"><div>';
					  result = result + '<a href="${operoxUrl}/editTax/'+report.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="mydelPop('+report.id+');"></i></div>';
					  result = result + '</td>';
					  result = result + '</tr>';
				  }
				  result = result + '</tbody>';
				  result = result + '</table>';
				  $("#taxList").append(result); 
				  pagination();	 
 		    }else{
	    		result = "<div class='content-area mt text-center text-muted'><i class='mt fa fa-5x fa-user'></i><h3>Tax List is Empty</h3></div>";
	    		$('#taxList').append(result);
	    	}
 		  }
	  }); 
  }

</script>

<script>
function pagination(){  
    $('#taxPagination').DataTable( { 
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
function mydelPop(taxId){
	$(".delete_pop").show();
	$(".mask").fadeIn(200);
	$("#removeShiftConfirm").attr("onclick", "removeTax("+taxId+");");
}
  
	$(".delete_pop a").click(function(){
		$(".delete_pop").hide();
		$(".mask").fadeOut(200);
	});

</script>

<script type="text/javascript">
  function removeTax(taxId){
	  var operoxUrl ='${operoxUrl}';
			$.ajax({
			   	type: "POST",
			    url: operoxUrl+"/removeTax?${_csrf.parameterName}=${_csrf.token}&taxId="+taxId, 	   
	   		    success: function(data) {
	   		    	getTaxList();
	   		    }
		     });
		
  }
</script>




<script type="text/javascript">

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

