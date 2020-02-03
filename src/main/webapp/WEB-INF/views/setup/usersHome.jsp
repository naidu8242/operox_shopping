<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>

<script type="text/javascript">
window.onload = function () {
	getUsersList();
 };
</script>

<script>
	function predefineUserImage(imageId){
		$(imageId).attr('src','${operoxUrl}/resources/images/userprofileImg.jpg');
	}
</script>


<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Users</h2>
    </div>
  </section>
  
   
       <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                <!-- <li><input type="text" placeholder="search" id="searchByName"></li>  -->           
                <li><a href="addUser"><i class="fa fa-plus"></i>Add User</a></li>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
    	
      <div class="data_table">
        <table id="usersListBody" class="display" cellspacing="0" width="100%"> </table>
      </div>
     
  
  </div>
  
</section>

<div class="mask"></div>
<div class="delete_pop">
		<p>Are you sure ?</p>
		<a class="close_color" href="javascript:void(0)" id="removeUserConfirm">Yes</a><a href="javascript:void(0)">No</a> 
	</div>
	
<!-- Content Area Ends--> 


<script type="text/javascript">
	function getUsersList(){
		
		var operoxUrl = '${operoxUrl}';
		var loginUserRole = '${OperoxWeb_roleName}';
		 
	    $('#usersListBody').empty();
		
		
		 $.ajax({ 
	    	type: "POST",
	    	url: operoxUrl+"/getUsersList?${_csrf.parameterName}=${_csrf.token}&loginUserRole="+loginUserRole,
	    			 
            success: function(data) {
	        var json = JSON.parse(data);
	        var result = "";
	        
	        if(json.length > 0)
	   		{
	        
	        	    result = result + '<thead>';
	 				result = result +'<tr>';
	 				result = result +'<th>Image</th>';
	 				result = result +' <th>Employee Id</th>';
	 				result = result +'<th>User name</th>';
	 				result = result +' <th>Email</th>';
	 				result = result +' <th>Phone</th>';
	 				result = result +'<th>Role</th>';
	 				result = result +'<th>Location</th>';
	 				result = result +'<th>Store</th>';
	 				result = result +'<th class="action">Action</th>';
	 				result = result +'</tr>';
	 				result = result + '</thead>';
	 				result = result +'<tbody>';
	 			for(var i=0; i<json.length; i++){
	 				
	 				var user = json[i];
	 				if(user.phone != null && user.phone != ''){
	 			    	mobilePhone = user.phone;
	 			    }else{
	 			    	mobilePhone = "No Data"
	 			    }
	 			    if(user.employeeId != null && user.employeeId != ''){
	 			    	employeeId = user.employeeId;
	 			    }else{
	 			    	employeeId = "--";
	 			    }
	 				if(user.lastName != null && user.lastName != ''){
	 					lastName = user.lastName;
	 				}else{
	 					lastName = "";
	 				}
	 				
	 				
	 				result = result +'<tr>';
	 				result = result +'<td class="image"><div><img onerror="predefineUserImage(this)"  src="'+operoxUrl+'/displayUserImage/'+user.id+'" alt=""/></div></td>';
	 				result = result +' <td><div class="ta-sm">'+employeeId+'</div></td>';
	 				result = result +'<td><a href="${operoxUrl}/userView/'+user.id+'"><div class="ta-sm">'+user.firstName+" "+lastName+'</div></a></td>';
	 				result = result +'<td><div class="ta-m">'+user.email+'</div></td>';
	 				result = result +'<td><div class="ta-sm">'+mobilePhone+'</div></td>';
	 				result = result +'<td><div class="ta-sm">'+user.roleName+'</div></td>';
	 				result = result +'<td><div class="ta-sm">'+user.location+'</div></td>';
	 				result = result +'<td><div class="ta-sm">'+user.storeName+'</div></td>';
	 				result = result +'<td class="action"><div>';
	 				result = result +'<a href="${operoxUrl}/editUser/'+user.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="mydelPop('+user.id+');"></i></div>';
	 				result = result +'</td>';
	 				result = result +'</tr>';
	       	 	   
				   }
	 			
	 			 result = result +'</tbody>';
	 			 
	 			 $('#usersListBody').append(result);
	       	 	 pagination();
	   		}else{
	    		result = "<div class='content-area mt text-center text-muted'><i class='mt fa fa-5x fa-user'></i><h3>Users List is Empty</h3></div>";
	    		$('#usersListBody').append(result);
	    	}

			 },
	        error: function(e){
	        }
	    });   
	}
</script>


<script>
function mydelPop(userId){
	$(".delete_pop").show();
	$(".mask").fadeIn(200);
	$("#removeUserConfirm").attr("onclick", "removeUser("+userId+");");
}
  
	$(".delete_pop a").click(function(){
		$(".delete_pop").hide();
		$(".mask").fadeOut(200);
	});

</script>


<script>
function pagination(){  
    $('#usersListBody').DataTable( { 
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
  function removeUser(id){
	  var operoxUrl ='${operoxUrl}';
			$.ajax({
			   	type: "POST",
			    url: operoxUrl+"/removeUser?${_csrf.parameterName}=${_csrf.token}&id="+id, 	   
	   		    success: function(data) {
	   		    	getUsersList();
	   		    }
		     });
  }
  </script>

<jsp:include page="../masterFooter.jsp" />