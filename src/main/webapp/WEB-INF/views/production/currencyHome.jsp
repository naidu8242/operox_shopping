<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>

   <script type="text/javascript">
window.onload = function () {
	getCurrencyList();
 };
</script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Currency list</h2>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Currency</div>
  </section>
  

  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>           
        	    <li><a href="<c:url value='/addCurrency'/>"><i class="fa fa-plus"></i>Add Currency</a></li>         
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>    
    	</div>

	<div id="currencyList"></div>  
</div>
</section>

<!--  Error Message  -->
<div class="mask"></div>
<div class="delete_pop">
		<p>Delete confirmation message....</p>
		<a class="close_color" href="javascript:void(0)">Yes</a><a href="javascript:void(0)">No</a> 
	</div>

  
 <script type="text/javascript">
  function getCurrencyList(){
	  var operoxUrl ='${operoxUrl}';
	  $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getCurrencyList?${_csrf.parameterName}=${_csrf.token}", 	   
 		    success: function(data) {
 		    	var json = JSON.parse(data);
				  var result = "";
				  result = result + '<div class="data_table">';
				  result = result + '<table id="currencyPagination" class="display" cellspacing="0" width="100%">';
				  result = result + '<thead>';	
				  result = result + '<tr>';
				  result = result + '<th>Currency id</th>';
				  result = result + '<th>Currency</th>';
				  result = result + '<th>Description</th>';
				  result = result + '<th>Symbol</th>';
				  result = result + '<th>Exchange Value</th>';
				  result = result + '<th class="action">Action</th>';
				  result = result + '</tr>';
				  result = result + '</thead>';
				  result = result + '<tbody>';
				  
				  
				  for (var i=0; i<json.length; i++){
					  var report = json[i];
					  result = result + '<tr>';
					  result = result + '<td><div class="ta-m">'+(i+1)+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.currency+'</div></td>';
					  if(report.description != null && report.description != '' && report.description != 'undefined'){
						  result = result + '<td><div class="ta-m"> '+report.description+' </div></td>';
					  }else{
						  result = result + '<td><div class="ta-m">--</div></td>';
					  }
					  if(report.symbol != null && report.symbol != '' && report.symbol != 'undefined'){
						  result = result + '<td><div class="ta-sm">'+report.symbol+'</div></td>';
					  }else{
						  result = result + '<td><div class="ta-m">--</div></td>';
					  }
					  result = result + '<td><div class="ta-m">'+report.exchangeValue+'</div></td>';
					  result = result + '<td class="action"><div>';
					  result = result + '<a href="${operoxUrl}/editCurrency/'+report.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="removeCurrency('+report.id+');"></i></div>';
					  result = result + '</td>';
					  result = result + '</tr>';
				  }
				  result = result + '</tbody>';
				  result = result + '</table>';
				  $("#currencyList").empty(); 
				  $("#currencyList").append(result); 
				  currencyPagination();	 
 		    }
	  }); 
  }

</script>

<script>
function currencyPagination(){  
    $('#currencyPagination').DataTable( { 
    	"scrollCollapse": false,
    	"lengthMenu": [[5, 15, 20, -1], [5, 15, 20, "All"]],
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
  function removeCurrency(currencyId){
	  var operoxUrl ='${operoxUrl}';
	  var message = confirm("Do you want to delete this Currency");
		if (message == true) {
			$.ajax({
			   	type: "POST",
			    url: operoxUrl+"/removeCurrency?${_csrf.parameterName}=${_csrf.token}&currencyId="+currencyId, 	   
	   		    success: function(data) {
	   		    	getCurrencyList();
	   		    }
		     });
		}
  }
</script>

 <jsp:include page="../masterFooter.jsp" />
