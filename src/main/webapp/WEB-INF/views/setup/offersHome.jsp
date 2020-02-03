<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>


<script type="text/javascript">
window.onload = function () {
	getOffersList();
 };
</script>


<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Offers</h2>
    </div>
  </section>
  
   
       <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <li><a href="addOffer"><i class="fa fa-plus"></i>Add Offer</a></li>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
    	
      <div class="data_table">
        <table id="offersListBody" class="display" cellspacing="0" width="100%"> </table>
      </div>
     
  
  </div>
  
</section>

<div class="mask"></div>
<div class="delete_pop">
		<p>Are you sure ?</p>
		<a class="close_color" href="javascript:void(0)" id="removeOfferConfirm">Yes</a><a href="javascript:void(0)">No</a> 
	</div>


<jsp:include page="../masterFooter.jsp" />




<script type="text/javascript">
	function getOffersList(){
		
		var operoxUrl = '${operoxUrl}';
		 
	    $('#offersListBody').empty();
		
		
		 $.ajax({ 
	    	type: "POST",
	    	url: operoxUrl+"/getOffersList?${_csrf.parameterName}=${_csrf.token}",
	    			 
            success: function(data) {
	        var json = JSON.parse(data);
	        var result = "";
	        
	        if(json.length > 0)
	   		{
	        
	        	    result = result + '<thead>';
	 				result = result +'<tr>';
	 				result = result +' <th>Offer Name</th>';
	 				result = result +'<th>Offer Type</th>';
	 				result = result +' <th>Start Date</th>';
	 				result = result +' <th>End Date</th>';
	 				result = result +'<th class="action">Action</th>';
	 				result = result +'</tr>';
	 				result = result + '</thead>';
	 				result = result +'<tbody>';
	 			for(var i=0; i<json.length; i++){
	 				
	 				var offer = json[i];
	 				var offerStartDate = '';
	 				var offerEndDate = '';
	 				
	 				if(offer.offerStartDateStr != null && offer.offerStartDateStr != ''){
	 					offerStartDate = offer.offerStartDateStr;
	 				}else{
	 					offerStartDate = "----"
	 				}
	 				
	 				if(offer.offerEndDateStr != null && offer.offerEndDateStr != ''){
	 					offerEndDate = offer.offerEndDateStr;
	 				}else{
	 					offerEndDate = "----"
	 				}
	 				
	 				
	 				
	 				result = result +'<tr>';
	 				result = result + '<td><div class="ta-m"><a href= "${operoxUrl}/viewOffer/'+offer.id+'">'+offer.offerName+'</a></div></td>';
	 				result = result +'<td><div class="ta-m">'+offer.offerType+'</div></td>';
	 				result = result +'<td><div class="ta-sm">'+offerStartDate+'</div></td>';
	 				result = result +'<td><div class="ta-sm">'+offerEndDate+'</div></td>';
	 				result = result +'<td class="action"><div>';
	 				result = result +'<a href="${operoxUrl}/editOffer/'+offer.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="mydelPop('+offer.id+');" ></i></div>';
	 				result = result +'</td>';
	 				result = result +'</tr>';
	       	 	   
				   }
	 			
	 			 result = result +'</tbody>';
	 			 
	 			 $('#offersListBody').append(result);
	       	 	 pagination();
	   		}else{
	    		result = "<div class='content-area mt text-center text-muted'><i class='mt fa fa-5x fa-user'></i><h3>Offers List is Empty</h3></div>";
	    		$('#offersListBody').append(result);
	    	}

			 },
	        error: function(e){
	        }
	    });   
	}
</script>


<script>
function pagination(){  
    $('#offersListBody').DataTable( { 
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
function mydelPop(userId){
	$(".delete_pop").show();
	$(".mask").fadeIn(200);
	$("#removeOfferConfirm").attr("onclick", "removeOffer("+userId+");");
}
  
	$(".delete_pop a").click(function(){
		$(".delete_pop").hide();
		$(".mask").fadeOut(200);
	});

</script>

<script type="text/javascript">
  function removeOffer(offerId){
	  var operoxUrl ='${operoxUrl}';
		if (offerId) {
			$.ajax({
			   	type: "POST",
			    url: operoxUrl+"/removeOffer?${_csrf.parameterName}=${_csrf.token}&offerId="+offerId, 	   
			    success: function(result) {
		        	if((result == 'offerHome')){
		        		location.replace(operoxUrl+"/offers");  
		        	}
			        
		        },
		     });
		}
  }
  </script>
