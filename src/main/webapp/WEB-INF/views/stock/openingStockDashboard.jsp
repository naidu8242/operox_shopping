<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script> 
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script> 


 <script>
$(document).ready(function() {	
	openingStockList();
});
</script> 

 <script>
function openingStockList(){ 
	
	var operoxUrl = '${operoxUrl}';  
    $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getOpeningStockList?${_csrf.parameterName}=${_csrf.token}", 	   
   		    success: function(data) {
   		    	if(data != null && data != 'undefined' && data != "" && data != '[]'){
   		    	var json = JSON.parse(data);
   		    	var result = '';
			   	result = result +'  	<table id="example" class="display table table-stripped table-bordered" cellspacing="0" width="100%">';
			   	result = result +'  	<thead>';
			   	result = result +'      	<tr>';
			   	result = result +'          	<th>Product name</th>';
			   	result = result +'              <th>Barcode</th>';
			   	result = result +'              <th>Search code</th>';
			   	result = result +'              <th>Qty</th>';
			   	result = result +'              <th>Price</th>';
			   	result = result +'              <th>Tax</th>';
			   	result = result +'          </tr>';
			 	result = result +'  	</thead>';
			 	result = result +'  	<tbody>';
			   	for (var i=0; i<json.length; i++)
   				{   
   		    	var report = json[i];
			   	result = result +'      	<tr>';
			   	if(report.id != null && report.id !='' && report.id != 'undefined'){
			   	result = result +'<td><div class="ta-m">'+report.productName+'</div></td>';
			   	}else{
			   		result = result +'<td><div class="ta-m">No Data</div></a></td>';	
			   	}
			   	
			   	
			   	if(report.barCode != null && report.barCode !='' && report.barCode != 'undefined'){
			   	result = result +'<td><div class="ta-m">'+report.barCode+'</div></td>';
			   	}else{
			   	result = result +'<td><div class="ta-m">No Data</div></a></td>';	
			   	}
			   	
				
				if(report.searchCodeStr != null && report.searchCodeStr !='' && report.searchCodeStr != 'undefined'){
			   	result = result +'              <td><div class="ta-m">'+report.searchCodeStr+'</div></td>';
				}else{
			   	result = result +'<td><div class="ta-m">No Data</div></a></td>';	
			   	}
				
				if(report.qtyStr != null && report.qtyStr !='' && report.qtyStr != 'undefined'){
			   	result = result +'              <td><div class="ta-m">'+report.qtyStr+'</div></td>';
				}else{
			   	result = result +'<td><div class="ta-m">No Data</div></a></td>';	
			   	}
				if(report.priceStr != null && report.priceStr !='' && report.priceStr != 'undefined'){
				   	result = result +'              <td><div class="ta-m">'+report.priceStr+'</div></td>';
				}else{
				   	result = result +'<td><div class="ta-m">No Data</div></a></td>';	
			   	}
			   	if(report.taxStr != null && report.taxStr !='' && report.taxStr != 'undefined'){
			   	result = result +'              <td><div class="ta-m">'+report.taxStr+'</div></td>';
			   	}else{
			   	result = result +'<td><div class="ta-m">No Data</div></a></td>';	
			   	}
			   	result = result +'          </tr>';
   				}
			   	result = result +'      </tbody>';
			   	result = result +'  </table>';
			    $("#openingStockList").append(result);
			    pagination();
			    }else{
               	 $("#openingStockList").removeAttr("style");
	    	         var result ="";
	    	    
				 result = result+"			<div class='blank-page text-center'>";
				 result = result+"			        <p><i class='fa fa-5x fa-bar-chart'></i></p>";
				 result = result+"			        <p>No Opening Stock Found</p>";
				 result = result+"			 </div>";
				
				$("#openingStockList").empty();
			    $("#openingStockList").append(result); 
             }

			}
    }); 
    

}	

</script>

<script>
function pagination(){
    $("#example").DataTable( { 
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
      <h2>Opening Stock List</h2>
    </div>
    <div class="pull-right brud-crum"><a href="dashboard">Home</a> &raquo; Opening Stock List</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <!-- <li><input type="text" placeholder="search"></li>   -->          
                <!-- <li><a href="openingstock"><i class="fa fa-plus"></i>Add openingStock</a></li> -->
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
	<div class="data_table">
    <div id="openingStockList"></div>
    </div>  	
  
  </div>
  
</section>
<!-- Content Area Ends--> 
<jsp:include page="../masterFooter.jsp" /> 