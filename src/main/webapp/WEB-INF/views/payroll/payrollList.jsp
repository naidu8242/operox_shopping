<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>


<script type="text/javascript">
$(document).ready(function(){
	getpayrollList();
}); 
</script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left"><h2>Payroll list</h2></div>
    <div class="pull-right brud-crum">Home » Payroll list</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  
<div class="payroll_head">    
    <a href="<c:url value='/runPayroll'/>">Run Payroll</a>
    <a href="#" onclick="getpayrollList();">Get List</a>
    <div class="div4">
       <span class="addiconSpace">
         <input type="text" value="" id="fromDate" placeholder="DD/MM/YYYY">
         <div class="addIcon"><i class="fa fa-calendar" title="Select Date"></i></div>
       </span>
    </div>
    
    <div class="div4">
       <span class="addiconSpace">
         <input type="text" value="" id="toDate" placeholder="DD/MM/YYYY">
         <div class="addIcon"><i class="fa fa-calendar" title="Select Date"></i></div>
       </span>
    </div>  
    
     <div class="div4">
		<span>
             <select id="payrollType" name="payrollType" field-name="payroll Type" onchange="getpayrollList();">
               <option selected="selected" disabled>select type</option>
              <option value="weekly">weekly</option>
              <option value="bi-weekly">bi-weekly</option>
              <option value="monthly">monthly</option>
             </select>
         </span>
    </div> 
<div class="clearfix"></div>
</div>     

	<div id="payrollListDiv">  </div>  	
  </div>
  
</section>
<!-- Content Area Ends--> 

<script>
  function getpayrollList(){
				var operoxUrl = '${operoxUrl}';
				var fromDate = $("#fromDate").val();
				var toDate = $("#toDate").val();
				var payRollType = $("#payrollType").val();
		 				$.ajax({
							type : "GET",
							url : operoxUrl+ "/getpayrollList?&fromDate="+fromDate+"&toDate="+toDate+"&payRollType="+payRollType,
							success : function(data) {
								var json = JSON.parse(data);
				   	        	var result = "";
				   	        	result = result + '<div class="data_table">';
				   	        	result = result + '<table id="payrollPagination" class="display" cellspacing="0" width="100%">';
				   	        	result = result + '<thead>';		
				   	        	result = result + '<tr>';
				   	        	result = result + '<th>Sl no</th>';
				   	        	result = result + '<th>Employee Id</th>';
				   	        	result = result + ' <th>User name</th>';
				   	        	result = result + '<th>No of days</th>';
				   	        	result = result + '<th>Leaves</th>';
				   	        	result = result + '<th>Pay</th>';
				   	        	result = result + '<th class="action">Action</th>';
				   	        	result = result + ' </tr>';
				   	        	result = result + '</thead>';
								result = result + '<tbody>';
								var j =0;
				   	        	for (var i=0; i<json.length; i++)
				   				{  
				   	        		j++;
										
				   	        		var report = json[i];
				   	        		result = result + '<tr>';
					   	        	result = result + '<td><div class="ta-m">'+j+'</div></td>';
					   	        	if(report.user.employeeId != null && report.user.employeeId != '' && report.user.employeeId != 'undefined'){
					   	        		result = result + '<td><div class="ta-m">'+report.user.employeeId+'</div></td>';
					   	        	}else{
					   	        		result = result + '<td><div class="ta-m">--</div></td>';
					   	        	}
					   	        	if(report.user.username != null && report.user.username != '' && report.user.username != 'undefined'){
					   	        		result = result + '<td><div class="ta-m">'+report.user.username+'</div></td>';
					   	        	}else{
					   	        		result = result + '<td><div class="ta-m">--</div></td>';
					   	        	}
					   	        	
					   	        	
					   	         if(report.compensationType != null && report.compensationType != '' && report.compensationType != 'undefined'){
					   	        		if(report.compensationType == 'weekly'){
					   	        			result = result + '<td><div class="ta-m">5</div></td>';
					   	        		}else if(report.compensationType == 'bi-weekly'){
					   	        			result = result + '<td><div class="ta-m">10</div></td>';
					   	        		}else if(report.compensationType == 'monthly'){
					   	        			result = result + '<td><div class="ta-m">30</div></td>';
					   	        		}
					   	        	}
					   	        	 
					   	        	if(report.totalLeaves != null && report.totalLeaves != '' && report.totalLeaves != 'undefined'){
					   	        		result = result + '<td><div class="ta-m">'+report.totalLeaves+'</div></td>';
					   	        	}else{
					   	        		result = result + '<td><div class="ta-m">--</div></td>';
					   	        	}
					   	        	
					   	        	if(report.netPay != null && report.netPay != '' && report.netPay != 'undefined'){
					   	        		result = result + '<td><div class="ta-m">'+report.netPay+'</div></td>';
					   	        	}else{
					   	        		result = result + '<td><div class="ta-m">--</div></td>';
					   	        	}
					   	        	result = result + '<td class="action"><div>';
					   	        	result = result + '<a href="${operoxUrl}/downloadPayslip/'+report.id+'/'+report.user.id+'"><i class="glyphicon glyphicon-download-alt"></i></div>';
					   	        	
					   	        	result = result + '</td>';
					   	        	result = result + '</tr>';
				   	        	} 
									
				   	        	result = result +'</tbody>';
				   	        	result = result +'</table>';
// 				   	        	result = result +'</div>';
				   	            $("#payrollListDiv").empty();  
				   	    	    $("#payrollListDiv").append(result)
							},
						});  
  }

</script>

<script>
function payrollPagination(){ 
    $('#payrollPagination').DataTable( {
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
$( function() {
  $( "#fromDate" ).datepicker();
});
</script>

<script type="text/javascript">
$( function() {
  $( "#toDate" ).datepicker();
});
</script>

<jsp:include page="../masterFooter.jsp" />