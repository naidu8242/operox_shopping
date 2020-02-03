<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>

  <script type="text/javascript">
window.onload = function () {
	getBrandsList();
 };
</script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Brand list</h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo;  Brand</div>
  </section>
  

  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>           
        	    <li><a href="<c:url value='/addBrand'/>"><i class="fa fa-plus"></i>Add Brand</a></li>         
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>    
    	</div>

	<div id="brandsList"></div>  
</div>
</section>

<!--  Error Message  -->
<div class="mask"></div>
<div class="delete_pop">
		<p>Delete confirmation message....</p>
		<a class="close_color" href="javascript:void(0)">Yes</a><a href="javascript:void(0)">No</a> 
	</div>

  
  <script type="text/javascript">
  function getBrandsList(){
	  var operoxUrl ='${operoxUrl}';
	  $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getBrandsList?${_csrf.parameterName}=${_csrf.token}", 	   
 		    success: function(data) {
 		    	var json = JSON.parse(data);
				  var result = "";
				  result = result + '<div class="data_table">';
				  result = result + '<table id="brandPagination" class="display" cellspacing="0" width="100%">';
				  result = result + '<thead>';	
				  result = result + '<tr>';
				  result = result + '<th>Brand id</th>';
				  result = result + '<th>Brand Name</th>';
				  result = result + '<th>Manufactured by</th>';
				  result = result + '<th>Market by</th>';
				  result = result + '<th>Updated by</th>';
				  result = result + '<th class="action">Action</th>';
				  result = result + '</tr>';
				  result = result + '</thead>';
				  result = result + '<tbody>';
				  
				 
				  
				  for (var i=0; i<json.length; i++){
					  var report = json[i];
					  result = result + '<tr>';
					  result = result + '<td><div class="ta-m">'+report.id+'</div></td>';
					  result = result + '<td><div class="ta-m"><a href= "${operoxUrl}/viewBrand/'+report.id+'">'+report.brandName+'</div></td>';
					  if(report.manufacturedBy != null && report.manufacturedBy != '' && report.manufacturedBy != 'undefined'){
						  result = result + '<td><div class="ta-m"> '+report.manufacturedBy+' </div></td>';
					  }else{
						  result = result + '<td><div class="ta-m">--</div></td>';
					  }
					  if(report.marketedBy != null && report.marketedBy != '' && report.marketedBy != 'undefined'){
						  result = result + '<td><div class="ta-sm">'+report.marketedBy+'</div></td>';
					  }else{
						  result = result + '<td><div class="ta-m">--</div></td>';
					  }
					  result = result + '<td><div class="ta-m">'+report.userName+'</div></td>';
					  result = result + '<td class="action"><div>';
					  result = result + '<a href="${operoxUrl}/editBrands/'+report.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="removeBrand('+report.id+');"></i></div>';
					  result = result + '</td>';
					  result = result + '</tr>';
				  }
				  result = result + '</tbody>';
				  result = result + '</table>';
				  $("#brandsList").empty(); 
				  $("#brandsList").append(result); 
				  brandPagination();	 
 		    }
	  }); 
  }

</script>

<script>
function brandPagination(){  
    $('#brandPagination').DataTable( { 
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
  function removeBrand(brandId){
	  var operoxUrl ='${operoxUrl}';
	  var message = confirm("Do you want to delete this brand");
		if (message == true) {
			$.ajax({
			   	type: "POST",
			    url: operoxUrl+"/removeBrand?${_csrf.parameterName}=${_csrf.token}&brandId="+brandId, 	   
	   		    success: function(data) {
	   		    	getBrandsList();
	   		    }
		     });
		}
  }
</script>


 <jsp:include page="../masterFooter.jsp" />
