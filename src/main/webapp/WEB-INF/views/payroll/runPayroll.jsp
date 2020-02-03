<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<style type="text/css">
.addIcon {
padding: 5px 0px !important;
width: 30px;
margin-right: 5px;
top: 6px;
}
</style>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left"><h2>Run Payroll</h2></div>
    <div class="pull-right brud-crum">Home » Run Payroll</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="payroll_head">    
    <div class="div4" id = fromdatediv>
       <span class="addiconSpace">
         <input type="text" value="" id="fromDate" field-name="date of birth" placeholder="DD/MM/YYYY">
         <div class="addIcon"><i class="fa fa-calendar" title="Select Date"></i></div>
       </span>
    </div>
    
    <div class="div4" id= todatediv>
       <span class="addiconSpace">
         <input type="text" value="" id="toDate" placeholder="DD/MM/YYYY" >
         <div class="addIcon"><i class="fa fa-calendar" title="Select Date"></i></div>
       </span>
    </div> 

    <div class="div4">
		<span>
             <select id="payrollType" name="payrollType" field-name="payroll Type" onchange="getPayRollEmployees();">
               <option selected="selected" disabled>select type</option>
              <option value="weekly">weekly</option>
              <option value="bi-weekly">bi-weekly</option>
              <option value="monthly">monthly</option>
             </select>
         </span>
    </div>      
<div class="clearfix"></div>
</div>     
    <div id="payrollList"></div>
	<!-- <div class="data_table">
    	<table>
        	<tbody>
            	<tr>
                	<th>Usercode</th>
                    <th>User name</th>
                    <th>Compensation</th>
                    <th>Attendance</th>
                    <th>Leaves</th>
                    <th>LOP's</th>
                    <th>Tax</th>
                    <th>Total</th>            
                </tr>
            	<tr>
                    <td><div class="ta-m">USER53461</div></td>
                    <td><div class="ta-m">Rajashekar Rajashekar</div></td>
                    <td><div class="ta-m">26,000 / Month</div></td>
                    <td><div class="ta-sm"><input type="text" value="29"></div></td>
                    <td><div class="ta-sm"><input type="text" value="2"></div></td>
                    <td><div class="ta-sm"><input type="text" value="0"></div></td>
                    <td><div class="ta-sm"><input type="text" value="1,000/-"></div></td>
                    <td><div class="ta-sm">25,000/-</div></td>           
                </tr>
            	<tr>
                    <td><div class="ta-m">USER53461</div></td>
                    <td><div class="ta-m">Rajashekar</div></td>
                    <td><div class="ta-m">26,000 / Month</div></td>
                    <td><div class="ta-sm"><input type="text" value="29"></div></td>
                    <td><div class="ta-sm"><input type="text" value="2"></div></td>
                    <td><div class="ta-sm"><input type="text" value="0"></div></td>
                    <td><div class="ta-sm"><input type="text" value="1,000/-"></div></td>
                    <td><div class="ta-sm">25,000/-</div></td>           
                </tr>
            	<tr>
                    <td><div class="ta-m">USER53461</div></td>
                    <td><div class="ta-m">Rajashekar</div></td>
                    <td><div class="ta-m">26,000 / Month</div></td>
                    <td><div class="ta-sm"><input type="text" value="29"></div></td>
                    <td><div class="ta-sm"><input type="text" value="2"></div></td>
                    <td><div class="ta-sm"><input type="text" value="0"></div></td>
                    <td><div class="ta-sm"><input type="text" value="1,000/-"></div></td>
                    <td><div class="ta-sm">25,000/-</div></td>           
                </tr>
            	<tr>
                    <td><div class="ta-m">USER53461</div></td>
                    <td><div class="ta-m">Rajashekar</div></td>
                    <td><div class="ta-m">26,000 / Month</div></td>
                    <td><div class="ta-sm"><input type="text" value="29"></div></td>
                    <td><div class="ta-sm"><input type="text" value="2"></div></td>
                    <td><div class="ta-sm"><input type="text" value="0"></div></td>
                    <td><div class="ta-sm"><input type="text" value="1,000/-"></div></td>
                    <td><div class="ta-sm">25,000/-</div></td>           
                </tr>
            	<tr>
                    <td><div class="ta-m">USER53461</div></td>
                    <td><div class="ta-m">Rajashekar</div></td>
                    <td><div class="ta-m">26,000 / Month</div></td>
                    <td><div class="ta-sm"><input type="text" value="29"></div></td>
                    <td><div class="ta-sm"><input type="text" value="2"></div></td>
                    <td><div class="ta-sm"><input type="text" value="0"></div></td>
                    <td><div class="ta-sm"><input type="text" value="1,000/-"></div></td>
                    <td><div class="ta-sm">25,000/-</div></td>           
                </tr>
            	<tr>
                    <td><div class="ta-m">USER53461</div></td>
                    <td><div class="ta-m">Rajashekar</div></td>
                    <td><div class="ta-m">26,000 / Month</div></td>
                    <td><div class="ta-sm"><input type="text" value="29"></div></td>
                    <td><div class="ta-sm"><input type="text" value="2"></div></td>
                    <td><div class="ta-sm"><input type="text" value="0"></div></td>
                    <td><div class="ta-sm"><input type="text" value="1,000/-"></div></td>
                    <td><div class="ta-sm">25,000/-</div></td>           
                </tr>
            	<tr>
                    <td><div class="ta-m">USER53461</div></td>
                    <td><div class="ta-m">Rajashekar</div></td>
                    <td><div class="ta-m">26,000 / Month</div></td>
                    <td><div class="ta-sm"><input type="text" value="29"></div></td>
                    <td><div class="ta-sm"><input type="text" value="2"></div></td>
                    <td><div class="ta-sm"><input type="text" value="0"></div></td>
                    <td><div class="ta-sm"><input type="text" value="1,000/-"></div></td>
                    <td><div class="ta-sm">25,000/-</div></td>           
                </tr>
            	<tr>
                    <td><div class="ta-m">USER53461</div></td>
                    <td><div class="ta-m">Rajashekar</div></td>
                    <td><div class="ta-m">26,000 / Month</div></td>
                    <td><div class="ta-sm"><input type="text" value="29"></div></td>
                    <td><div class="ta-sm"><input type="text" value="2"></div></td>
                    <td><div class="ta-sm"><input type="text" value="0"></div></td>
                    <td><div class="ta-sm"><input type="text" value="1,000/-"></div></td>
                    <td><div class="ta-sm">25,000/-</div></td>           
                </tr>
            	<tr>
                    <td><div class="ta-m">USER53461</div></td>
                    <td><div class="ta-m">Rajashekar</div></td>
                    <td><div class="ta-m">26,000 / Month</div></td>
                    <td><div class="ta-sm"><input type="text" value="29"></div></td>
                    <td><div class="ta-sm"><input type="text" value="2"></div></td>
                    <td><div class="ta-sm"><input type="text" value="0"></div></td>
                    <td><div class="ta-sm"><input type="text" value="1,000/-"></div></td>
                    <td><div class="ta-sm">25,000/-</div></td>           
                </tr>
            	<tr>
                    <td><div class="ta-m">USER53461</div></td>
                    <td><div class="ta-m">Rajashekar</div></td>
                    <td><div class="ta-m">26,000 / Month</div></td>
                    <td><div class="ta-sm"><input type="text" value="29"></div></td>
                    <td><div class="ta-sm"><input type="text" value="2"></div></td>
                    <td><div class="ta-sm"><input type="text" value="0"></div></td>
                    <td><div class="ta-sm"><input type="text" value="1,000/-"></div></td>
                    <td><div class="ta-sm">25,000/-</div></td>           
                </tr>                                                                                                
			</tbody>
        </table>   
        
<div class="payroll_btns">
	<input type="button" value="Run payroll"> <input type="button" value="Cancel">
</div>
                
    </div>  --> 	
  
  </div>
  
</section>
<!-- Content Area Ends--> 


<script type="text/javascript">
window.onload = function() {
	disableDropdown();
	};
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

<script>
function getPayRollEmployees(){
	 $("#payroll_form").val('');
	var toDate = $("#toDate").val();
	var fromDate = $("#fromDate").val();
	if(toDate != null && toDate != "" && toDate != 'undefined' && fromDate != null && fromDate != "" && fromDate != 'undefined'){
		$('#fromdatediv').find('p.jquery_form_error_message').remove();
		$('#todatediv').find('p.jquery_form_error_message').remove();
	var payRollType = $("#payrollType").val();
	var operoxUrl = '${operoxUrl}';
		$.ajax({
		type : "GET",
		url : operoxUrl+ "/getPayRollEmployees?&payRollType="+payRollType+"&fromDate="+fromDate+"&toDate="+toDate,
		success : function(data) {
			   var json = JSON.parse(data);
			   var pay = payRollType;
			    var result = "";
			    result = result + '<div class="data_table">';
			     result = result + '<form id="payroll_form" name="payroll_form" method="post">';
	        	result = result + '<table id="payrollPagination" class="display" cellspacing="0" width="100%">';
	        	result = result + '<thead>';		
	        	result = result + '<tr>';
	        	result = result + '<th>Usercode</th>';
	        	result = result + '<th>User name</th>';
	        	result = result + ' <th>Compensation</th>';
	         	result = result + '<th>Tax</th>';
	        	result = result + '<th>Attendance</th>';
	        	result = result + '<th>Leaves</th>';
	        	result = result + '<th>LOP"s</th>';
	        	result = result + '<th>Net Pay</th>';
	        	result = result + ' </tr>';
	        	result = result + '</thead>';
			    result = result + '<tbody>';
	        	for (var i=0; i<json.length; i++)
				{  
	        	var report = json[i];
	        	if(report.role.displayName != "ADMIN"){
	        		result = result + '<tr>';
	   	        	result = result + '<td><div class="ta-m" id="userid'+i+'" name="userid'+i+1+'">'+report.id+'</div></td>';
	   	        	result = result + '<input type="hidden" id="userID'+report.id+'" name="userID'+i+'" value="'+report.id+'"/>';
	   	         	result = result + '<td><div class="ta-m" id="username'+i+'">'+report.firstName+' '+report.lastName+'</div></td>'; 
	   	        	result = result + '<td><div class="ta-m" id="compensation'+i+'">'+report.compensation+'</div></td>';
	   	        	result = result + '<td><div class="ta-sm"><input type="text" name="tax'+i+'" id="taxValue'+report.id+'" value="'+report.taxValue+'"></div></td>';
	   	        	result = result + '<td><div class="ta-sm"><input type="text" data-validation="required validate_Space" field-name="Attendance" name="attendance'+i+'" id="attendence'+report.id+'"></div></td>';
	   	        	result = result + '<td><div class="ta-sm" style="position:relative;"><span class="addiconSpace"><input type="text" field-name="leaves" data-validation="validate_Space" data-validation-optional="true" name="leave'+i+'" id="totalLeaves'+report.id+'"></span><div class="addIcon"><i class="fa fa-calculator" onclick="calculateNetPay('+report.id+',\''+pay+'\');"></i></div></div></td>';
	   	        	result = result + '<td><div class="ta-sm"><input type="text" readonly="readonly" name="lop'+i+'" id="lop'+report.id+'"></div></td>';
	   	        	result = result + '<td><div class="ta-sm"><input type="text" field-name="netPay" readonly="readonly" data-validation="required validate_Space" name="netPay'+i+'" id="netPay'+report.id+'"></div></td>';
	   	        	/* result = result + '<td><div class="ta-sm">'+report.taxValue+'</div></td>'; */
	   	        	result = result + '</tr>';
	        		}
	        	}
	        	result = result + '<input type="hidden" id="maxValue" name="maxValue" value="'+json.length+'"/>';
	        	result = result +'</tbody>';
	        	result = result +'</table>';
	        	result = result + '<div class="payroll_btns">';
	        	result = result + '<input type="button" value="Run payroll" onclick="runPayRoll();"> <input type="button" onclick="cancelPayroll();" value="Cancel">';
	        	result = result + '</div>';
	        	result = result + '</form>'; 
//	        	result = result +'</div>';
	            $("#payrollList").empty();  
	    	    $("#payrollList").append(result)
	    		/* payrollPagination();  */
		},
	}); 
	}else{
		$("#payroll_form").val('');
		$('#fromdatediv').find('p.jquery_form_error_message').remove();
		$('#todatediv').find('p.jquery_form_error_message').remove();
		$("#fromDate").after( "<p class='jquery_form_error_message'>Enter from Date</span>");
		$("#toDate").after( "<p class='jquery_form_error_message'>Enter to Date</span>");
	}
}

</script>

<script type="text/javascript">
function cancelPayroll(){
    var operoxUrl ='${operoxUrl}';
    location.replace(operoxUrl+ "/payroll");
}
</script>


 <!-- <script>
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
</script>  -->

<script type="text/javascript">
function runPayRoll(){
	var validationSettings = {
			errorMessagePosition : 'element',
			borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd'
		}; 
	
	if ($('#payroll_form').validate(false, validationSettings)) { 
				var frm = $('#payroll_form').serializeFormJSON();
				var fromDate = $("#fromDate").val();
				var toDate = $("#toDate").val();
				var con = JSON.stringify(frm);
				con = con.replace(/[{}]/g, "");
				  //It will escape all the special characters
			    var jsonData = encodeURIComponent(con);
				var operoxUrl = '${operoxUrl}';
				$.ajax({
				type : "GET",
				url : operoxUrl+ "/runPayRoll?${_csrf.parameterName}=${_csrf.token}&jsonData="+jsonData+"&fromDate="+fromDate+"&toDate="+toDate,
				success : function(data) {
					location.replace(operoxUrl+ "/payroll");
				},
			}); 
	    return true;
		} else {
			return false;
		} 
	$('body').on('blur', '#payroll_form', function() {
		$("#payroll_form").showHelpOnFocus();
		$("#payroll_form").validateOnBlur(false, validationSettings);
	});
}
</script>

<script type="text/javascript">
	(function($) {
		$.fn.serializeFormJSON = function() {
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		};
	})(jQuery);
</script>

<script type="text/javascript">
function calculateNetPay(employeeID,payType){
	var attendance = $("#attendence"+employeeID).val();
	var totalLeaves = $("#totalLeaves"+employeeID).val();
	var taxValue = $("#taxValue"+employeeID).val();
	var operoxUrl = '${operoxUrl}';
		$.ajax({
		type : "GET",
		url : operoxUrl+ "/calculateNetPay?&attendance="+attendance+"&totalLeaves="+totalLeaves+"&userId="+employeeID+"&payType="+payType+"&taxValue="+taxValue,
		success : function(data) {
			 var json = JSON.parse(data);
			$("#lop"+employeeID).val(json.lop);
			$("#netPay"+employeeID).val(json.netPay);
			  /*  var json = JSON.parse(data); */
			    
		},
	}); 
}
</script> 

<jsp:include page="../masterFooter.jsp" />