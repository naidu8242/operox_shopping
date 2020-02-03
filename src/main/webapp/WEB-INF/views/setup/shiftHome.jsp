<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>


<script type="text/javascript">
window.onload = function () {
	getShiftsList();
 };
</script>


<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Shift</h2>
    </div>
  </section>
  
   
       <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <li><a href="addShift"><i class="fa fa-plus"></i>Add Shift</a></li>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
    	
      <div class="data_table">
        <table id="shiftListBody" class="display" cellspacing="0" width="100%"> </table>
      </div>
     
  
  </div>
  
</section>
<!-- Content Area Ends--> 

<div class="mask"></div>
<div class="delete_pop">
		<p>Are you sure ?</p>
		<a class="close_color" href="javascript:void(0)" id="removeShiftConfirm">Yes</a><a href="javascript:void(0)">No</a> 
	</div>

<script type="text/javascript">
	function getShiftsList(){
		
		var operoxUrl = '${operoxUrl}';
		var loginUserRole = '${OperoxWeb_roleName}';
		 
	    $('#usersListBody').empty();
		
		
		 $.ajax({ 
	    	type: "POST",
	    	url: operoxUrl+"/getShiftsList?${_csrf.parameterName}=${_csrf.token}",
	    			 
            success: function(data) {
	        var json = JSON.parse(data);
	        var result = "";
	        
	        if(json.length > 0)
	   		{
	        
	        	    result = result + '<thead>';
	 				result = result +'<tr>';
	 				result = result +'<th>Shift Name</th>';
	 				result = result +' <th>Time In</th>';
	 				result = result +'<th>Time Out</th>';
	 				result = result +' <th>Description</th>';
	 				result = result +'<th class="action">Action</th>';
	 				result = result +'</tr>';
 				    result = result + '</thead>';
 				    result = result +'<tbody>';
	 			for(var i=0; i<json.length; i++){
	 				
	 				var shift = json[i];
	 			    
	 				
	 				result = result +'<tr>';
	 				result = result +' <td><a href="${operoxUrl}/shiftView/'+shift.id+'"><div class="ta-sm">'+shift.shiftName+'</div></a></td>';
	 				result = result +'<td><div class="ta-m">'+shift.intime+'</div></td>';
	 				result = result +'<td><div class="ta-sm">'+shift.outtime+'</div></td>';
	 				result = result +'<td><div class="ta-sm" title="'+shift.description+'">'+shift.description+'</div></td>';
	 				result = result +'<td class="action"><div>';
	 				result = result +'<a href="${operoxUrl}/editShift/'+shift.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="mydelPop('+shift.id+')"></i></div>';
	 				result = result +'</td>';
	 				result = result +'</tr>';
	       	 	   
				   }
	 			
	 			 result = result +'</tbody>';
	 			 
	 			 $('#shiftListBody').append(result);
	       	 	 pagination();
	   		}else{
	    		result = "<div class='content-area mt text-center text-muted'><i class='mt fa fa-5x fa-user'></i><h3>Shift List is Empty</h3></div>";
	    		$('#shiftListBody').append(result);
	    	}

			 },
	        error: function(e){
	        }
	    });   
	}
</script>

<script>
function mydelPop(shiftId){
	$(".delete_pop").show();
	$(".mask").fadeIn(200);
	$("#removeShiftConfirm").attr("onclick", "removeShift("+shiftId+");");
}
  
	$(".delete_pop a").click(function(){
		$(".delete_pop").hide();
		$(".mask").fadeOut(200);
	});

</script>

<script>
function removeShift(shiftId){  
	
	if(shiftId){
	      var operoxUrl ='${operoxUrl}';
	        $.ajax({
		    	type: "POST",
		    	 url: operoxUrl+"/removeShift?${_csrf.parameterName}=${_csrf.token}&shiftId="+shiftId, 
		        success: function(result) {
		        	if((result == 'shiftHome')){
		        		location.replace(operoxUrl+"/shift");  
		        	}
			        
		        },
		    });
	}
}
</script>


<script>
function pagination(){  
    $('#shiftListBody').DataTable( { 
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