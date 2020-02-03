<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script> 

<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

 <script>
$(document).ready(function() {	
	returnStockList();
});
</script> 

 <script>
function returnStockList(){
	
	var operoxUrl = '${operoxUrl}';  
    $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getStockReturnsList?${_csrf.parameterName}=${_csrf.token}", 	   
   		    success: function(data) {
   		    	if(data != null && data != 'undefined' && data != "" && data != '[]') {
   		    	var json = JSON.parse(data);
   		    	var result = '';
			   	result = result +'  	<table id="transferList" class="display table table-stripped table-bordered" cellspacing="0" width="100%" >';
			   	result = result +'  	<thead>';
			   	result = result +'      	<tr>';
			   	result = result +'          	<th>Return stock Id</th>';
			   	result = result +'              <th>Return Date</th>';
			   	result = result +'              <th>To Location</th>';
			   	result = result +'              <th>From Location</th>';
			   	result = result +'              <th>No Of Products</th>';
			   	result = result +'              <th>Total Amount</th>';
				result = result +'              <th>Return User</th>';
			   	result = result +'          </tr>';
			  	result = result +'  	</thead>';
			  	result = result +'  	<tbody>';
			   	for (var i=0; i<json.length; i++)
   				{   
	   		    	var report = json[i];
				   	result = result +'      	<tr>';
				   	result = result +'              <td><a href="returnStockView?stockReturnsId='+report.id+'"><div class="ta-m">'+report.id+'</div></a></td>';
				   	if(report.returnDateStr !=null && report.returnDateStr !='' && report.returnDateStr !='undefined'){
				   		result = result +'              <td><div class="ta-m">'+report.returnDateStr+'</div></td>';
				   	}else{
				   		result = result +'              <td><div class="ta-m">No Data</div></td>';
				   	}
					if(report.toStoreStr !=null && report.toStoreStr !='' && report.toStoreStr !='undefined'){
				   		result = result +'              <td><div class="ta-m">'+report.toStoreStr+'</div></td>';
				   	}else{
				   		result = result +'              <td><div class="ta-m">No Data</div></td>';
				   	}
				   	if(report.fromStoreStr !=null && report.fromStoreStr !='' && report.fromStoreStr !='undefined'){
				   		result = result +'              <td><div class="ta-m">'+report.fromStoreStr+'</div></td>';
				   	}else{
				   		result = result +'              <td><div class="ta-m">No Data</div></td>';
				   	}
				   	if(report.totalNumberOfProducts !=null && report.totalNumberOfProducts !='' && report.totalNumberOfProducts !='undefined'){
				   		result = result +'              <td><div class="ta-m">'+report.totalNumberOfProducts+'</div></td>';
				   	}else{
				   		result = result +'              <td><div class="ta-m">No Data</div></td>';
				   	}
					if(report.totalAmount !=null && report.totalAmount !='' && report.totalAmount !='undefined'){
				   		result = result +'              <td><div class="ta-m">'+report.totalAmount+'</div></td>';
				   	}else{
				   		result = result +'              <td><div class="ta-m">No Data</div></td>';
				   	}
				   	if(report.stockReturnByName !=null && report.stockReturnByName !='' && report.stockReturnByName !='undefined'){
				   		result = result +'              <td><div class="ta-m">'+report.stockReturnByName+'</div></td>';
				   	}else{
				   		result = result +'              <td><div class="ta-m">No Data</div></td>';
				   	}
				   	result = result +'          </tr>'; 
   				}
			   	result = result +'      </tbody>';
			   	result = result +'  </table>';
			    $("#returnStockList").append(result);
			    pagination();
			    }else{
               	 $("#returnStockList").removeAttr("style");
	    	         var result ="";
				 result = result+"			<div class='blank-page text-center'>";
				 result = result+"			        <p><i class='fa fa-5x fa-bar-chart'></i></p>";
				 result = result+"			        <p>No Return Stock Found</p>";
				 result = result+"			 </div>";
				
				$("#returnStockList").empty();
			    $("#returnStockList").append(result); 
             }

			}
    }); 
    
}	
</script>

<script>
function pagination(){
    $("#transferList").DataTable({
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

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Return Stock List</h2>
    </div>
    <div class="pull-right brud-crum"><a href = "dashboard" >Home</a> &raquo;Return Stock List</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <li><a href="returnStock"><i class="fa fa-plus"></i>Add Return Stock</a></li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
	<div class="data_table">
    <div id="returnStockList"></div>
    </div>  	
  
  </div>
  
</section>
<!-- Content Area Ends--> 

<jsp:include page="../masterFooter.jsp" /> 