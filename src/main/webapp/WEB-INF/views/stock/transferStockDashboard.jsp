<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script> 


 <script>
// Edit pop
$(document).ready(function() {	
	transferStockList();
		
});
</script> 

<%-- 
     @author :  Sarath,Venkat.s,Prasad,Teja,David.
    this script is for transfer Stock List .
     1.usage     :   used in in This jsp at  line 29
--%>
 <script>
function transferStockList(){ 
	var operoxUrl = '${operoxUrl}';  
    $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getTransferStockList?${_csrf.parameterName}=${_csrf.token}", 	   
   		    success: function(data) {
   		        if(data != null && data != 'undefined' && data != "" && data != '[]'){
   		    	var json = JSON.parse(data);
   		    	var result = '';
   		    	result = result +'  	<table  id="transferList" class="display table table-stripped table-bordered" cellspacing="0" width="100%">';
			   	result = result +'  	<thead>';
			   	result = result +'      	<tr>';
			   	result = result +'          	<th>Transfer Stock Id</th>';
			   	result = result +'              <th>Received Date</th>';
			   	result = result +'              <th>From Store</th>';
			   	result = result +'              <th>To Store</th>';
			   	result = result +'              <th>Updated By</th>';
			   	result = result +'          </tr>';
			   	result = result +'  	</thead>';
				result = result +'  	<tbody>';
			   	for (var i=0; i<json.length; i++)
   				{   
   		    	var report = json[i];
	   		 	result = result +'      	<tr>';
			   	result = result +'              <td><a href="transferStockView?stockReturnsId='+report.id+'"><div class="ta-m">'+report.transferNumber+'</div></a></td>';
			   	if(report.receivedDateStr !=null && report.receivedDateStr !='' && report.receivedDateStr !='undefined'){
				   	result = result +'              <td><div class="ta-m">'+report.receivedDateStr+'</div></td>';
			   	}else{
				   	result = result +'              <td><div class="ta-m">No Data</div></td>';
			   	}
				if(report.fromStoreStr !=null && report.fromStoreStr !='' && report.fromStoreStr !='undefined'){
				   	result = result +'              <td><div class="ta-m">'+report.fromStoreStr+'</div></td>';
			   	}else{
				   	result = result +'              <td><div class="ta-m">No Data</div></td>';
			   	}
				if(report.toStoreStr !=null && report.toStoreStr !='' && report.toStoreStr !='undefined'){
				   	result = result +'              <td><div class="ta-m">'+report.toStoreStr+'</div></td>';
			   	}else{
				   	result = result +'              <td><div class="ta-m">No Data</div></td>';
			   	}
				if(report.updatedBy !=null && report.updatedBy !='' && report.updatedBy !='undefined'){
				   	result = result +'              <td><div class="ta-m">'+report.updatedBy+'</div></td>';
			   	}else{
				   	result = result +'              <td><div class="ta-m">No Data</div></td>';

			   	}
			   	result = result +'          </tr>'; 
   				}
			   	result = result +'      </tbody>';
			   	result = result +'  </table>';
			    $("#transferStockList").append(result);
			    pagination();
			    
			    }else{
               	 $("#transferStockList").removeAttr("style");
               	 var result ="";
				 result = result+"			<div class='blank-page text-center'>";
				 result = result+"			        <p><i class='fa fa-5x fa-bar-chart'></i></p>";
				 result = result+"			        <p>No Transfer Stock Found</p>";
				 result = result+"			 </div>";
				
				$("#transferStockList").empty();
			    $("#transferStockList").append(result); 
             } 

			}
    }); 
    
}	

</script>


<%-- 
     @author :  Sarath,Venkat.s,Prasad,Teja,David.
    this script is for transfer Stock List .
     1.usage     :   used in in This jsp transferStockList() method at  line 61
--%>

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
      <h2>Transfer Stock List</h2>
    </div>
    <div class="pull-right brud-crum"><a href = "dashboard" >Home</a> &raquo; Transfer Stock List</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <li><a href="transferStock"><i class="fa fa-plus"></i>Add Transfer Stock</a></li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
	<div class="data_table">
    <div id="transferStockList"></div>
    </div>  	
  
  </div>
  
</section>
<!-- Content Area Ends--> 

 <jsp:include page="../masterFooter.jsp" /> 

<!--  Error Message  -->
<div class="mask"></div>
<div class="delete_pop">
		<p>Delete conformation message....</p>
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

</body>
</html>