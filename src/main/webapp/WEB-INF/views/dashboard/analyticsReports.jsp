<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script> 


<script src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script> 

<script src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.flash.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script> 
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script> 
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script> 
<script src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script> 
<script src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script> 

<link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.2.2/css/buttons.dataTables.min.css">

<style type="text/css">
div.dt-buttons {
float: none; clear:both; display: inline-block;}
.dataTables_filter {
top:0px !important;
}
.buttons-copy, .buttons-print, .buttons-pdf, .buttons-excel, .buttons-csv {
    font-family: fontawesome; color:#333;
}
.buttons-copy:before{
    content:"\f0c5";
    padding-right: 5px;
}
.buttons-print:before{
    content:"\f02f";
    padding-right: 5px;
}
.buttons-pdf:before{
    content:"\f1c1";
    padding-right: 5px;
}
.buttons-excel:before{
    content:"\f1c3";
    padding-right: 5px;
}
.buttons-csv:before{
    content:"\f0f6";
    padding-right: 5px;
}
</style>

<script type="text/javascript">
window.onload = function () {
	$('#reportFilterType').val('${type}');
	getAnalyticsReports();
 };
</script>
<section class="content-wraper">
<!-- Content Area -->

  <section class="content-header clearfix">
    <div class="pull-left">
		<h2>Reports
            <span class="select-fild">
                <select onchange="getAnalyticsReports();flushData();" id="reportFilterType">
                <option value="daily">Daily sales</option>
                <option value="counter">Counter sales</option>
                <option value="shift">Shift sales</option>
                <option value="user">User sales</option>
                <option value="store">Location sales</option>
                </select>
            </span>
        </h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; Reports</div>
  </section>
   <div id="loadingDiv"></div> 
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
		<div class="report_head">   
		<form:form id="reports_from"  name="reports_from" method="post"> 
		
		     <div class="div4" id="counterDiv1" style="display: none;">
			       <label class="labl">Location</label>
			       <span>
			             <select id="storeDropDown" name="storeDropDown" onchange="getAllCountersByStore();">
			                <option value="">--Select Location--</option>
			                  <c:forEach var="store" items="${storesList}">
	                            <option value="${store.id}">${store.storeName}</option>
                              </c:forEach>
			             </select>
			         </span>
			    </div> 
		
		      <div class="div4" id="counterDiv2" style="display: none;">
			       <label class="labl">Counter</label>
			       <span>
			             <select id="counterSales" name="counterSales">
			              <option value="">--Select Counter--</option>
			             </select>
			         </span>
			    </div>
			    
			     
		      
		   <div class="div4" id="shiftDiv" style="display: none;">
		       <label class="labl">Shift</label>
		       <span>
		                <select id="shiftId" name="shiftId">
			                <option value="">--Select Shift--</option>
			                  <c:forEach var="shift" items="${shiftsList}">
	                            <option value="${shift.id}">${shift.shiftName}</option>
                              </c:forEach>
			           </select>
		         </span>
             </div>
             
			 <div class="div4" id="userDiv" style="display: none;">
			       <label class="labl">Users</label>
			       <span>
			             <select id="userId" name="userId">
			                <option value="">--Select User--</option>
			                  <c:forEach var="user" items="${usersList}">
	                            <option value="${user.id}">${user.firstName} ${user.lastName}</option>
                              </c:forEach>
			           </select>
			         </span>
			  </div>
			  
			  
			  <div class="div4" id="storeDiv1" style="display: none;">
		       <label class="labl">Location type</label>
		       <span>
		             <select id="storeTypeDropDown" name="storeTypeDropDown" onchange="getStoreByStoreType();">
			              <option selected disabled>--Select Location type--</option>
			              <option value="Warehouse">Warehouse</option>
		                  <option value="Store">Store</option>
		                  <option value="Head office">Head office</option>
		             </select>
		         </span>
		    </div>
		    
		    <div class="div4" id="storeDiv2" style="display: none;">
		       <label class="labl">Location</label>
		       <span>
		             <select id="storeSales" name="storeSales">   
		                 <option value="">--Select Location--</option>
		             </select>
		         </span>
		    </div> 
		
		
		    <div class="div4">
		       <label class="labl">From</label>
		       <span class="addiconSpace">
		         <input type="text" value="" placeholder="Select Date" id="fromDate" name="fromDate" readonly="readonly"/>
		         <div class="addIcon"><i class="fa fa-calendar" title="Select Date"></i></div>
		       </span>
		    </div>
		    
		    <div class="div4">
		       <label class="labl">To</label>
		       <span class="addiconSpace">
		         <input type="text" value="" placeholder="Select Date" id="toDate" name="toDate" readonly="readonly"/>
		         <div class="addIcon"><i class="fa fa-calendar" title="Select Date"></i></div>
		       </span>
		    </div>   
		    
		    <input type="button" value="Submit" class="btn btn-primary my-btn" id="add_counter" onclick="getAnalyticsReports();">
		<div class="clearfix"></div>
		
	  </form:form>	
		</div>     
	  <div id="analyticsReportsList"></div>
  </div>
  
</section>
<!-- Content Area Ends--> 

<script type="text/javascript">
(function ($) {
   $.fn.serializeFormJSON = function () {

       var o = {};
       var a = this.serializeArray();
       $.each(a, function () {
           if (o[this.name]) {
               if (!o[this.name].push) {
                   o[this.name] = [o[this.name]];
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
  function getAnalyticsReports(){
	  var operoxUrl ='${operoxUrl}';
	  
	  var reportFilterType = document.getElementById("reportFilterType").value;
	  if(reportFilterType == 'counter'){
		  document.getElementById("counterDiv1").style.display="";
		  document.getElementById("counterDiv2").style.display="";
		  document.getElementById("storeDiv1").style.display="none";
		  document.getElementById("storeDiv2").style.display="none";
		  document.getElementById("shiftDiv").style.display="none";
		  document.getElementById("userDiv").style.display="none";
	  }else if(reportFilterType == 'shift'){
		  document.getElementById("counterDiv1").style.display="none";
		  document.getElementById("counterDiv2").style.display="none";
		  document.getElementById("userDiv").style.display="none";
		  document.getElementById("storeDiv1").style.display="none";
		  document.getElementById("storeDiv2").style.display="none";
		  document.getElementById("shiftDiv").style.display="";
	  }else if(reportFilterType == 'daily'){
		  document.getElementById("counterDiv1").style.display="none";
		  document.getElementById("counterDiv2").style.display="none";
		  document.getElementById("shiftDiv").style.display="none";
		  document.getElementById("userDiv").style.display="none";
		  document.getElementById("storeDiv1").style.display="none";
		  document.getElementById("storeDiv2").style.display="none";
	  }else if(reportFilterType == 'user'){
		  document.getElementById("counterDiv1").style.display="none";
		  document.getElementById("counterDiv2").style.display="none";
		  document.getElementById("shiftDiv").style.display="none";
		  document.getElementById("storeDiv1").style.display="none";
		  document.getElementById("storeDiv2").style.display="none";
		  document.getElementById("userDiv").style.display="";
	  }else if(reportFilterType == 'store'){
		  document.getElementById("shiftDiv").style.display="none";
		  document.getElementById("userDiv").style.display="none";
		  document.getElementById("counterDiv1").style.display="none";
		  document.getElementById("counterDiv2").style.display="none";
		  document.getElementById("storeDiv1").style.display="";
		  document.getElementById("storeDiv2").style.display="";
	  }
	  
	  var frm = $('#reports_from').serializeFormJSON();
      var con = JSON.stringify(frm);
      con = con.replace(/[{}]/g, "");
      var jsonData = encodeURIComponent(con);
	  
	  $("#analyticsReportsList").empty(); 
	  $("#loadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("resources/images/loading.gif"); background-position:center; background-repeat:no-repeat; background-size:50px;');
	  $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getAnalyticsReports?${_csrf.parameterName}=${_csrf.token}&json="+jsonData+"&reportFilterType="+reportFilterType, 	   
 		    success: function(data) {
 		    	var json = JSON.parse(data);
				var result = "";
				if(json.length > 0)
			   	{
				  result = result + '<div class="data_table">';
				  result = result + '<table id="reportsPagination" class="display" cellspacing="0" width="100%">';
				  result = result + '<thead>';
				  result = result + '<tr>';
				  result = result + '<th>Barcode</th>';
				  result = result + '<th>Product name</th>';
				  result = result + '<th>Search code</th>';
				  result = result + '<th>Qty</th>';
				  result = result + '<th>Amount</th>';
				  result = result + '</tr>';
				  result = result + '</thead>';
				  result = result + '<tbody>';
				 
				  
				  for (var i=0; i<json.length; i++){
					  
					  var report = json[i];
					  result = result + '<tr>';
					  result = result + '<td><div class="ta-m">'+report.barCode+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.productName+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.productCode+' </div></td>';
					  result = result + '<td><div class="ta-sm">'+report.noOfQuantity+'</div></td>';
					  result = result + '<td><div class="ta-sm">'+report.billAmount+'</div></td>';
					  result = result + '</tr>';
				  }
				  result = result + '</tbody>';
				  result = result + '</table>';
				  result = result + '<div class="clearfix"></div>';
				  var res = "";
				  for (var i=(json.length-1); i<json.length; i++){
					  var report = json[i];
					  result = result + '<div class="total_text">Total amount <span>'+report.totalAmount+'</span></div> ';       
					  result = result + '</div>';
				  }
				  $("#analyticsReportsList").empty(); 
				  $("#analyticsReportsList").append(result); 
				  $("#loadingDiv").removeAttr("style");
				  pagination();	 
 		    }else{
	    		result = "<div class='content-area mt text-center text-muted'><i class='mt fa fa-5x fa-user'></i><h3>Reports List is Empty</h3></div>";
	    		$("#analyticsReportsList").empty(); 
	    		$('#analyticsReportsList').append(result);
	    		$("#loadingDiv").removeAttr("style");
	    	}
 		  }
	  }); 
  }

</script>

<script>
function flushData(){ 
	document.getElementById("reports_from").reset();
	$("#counterSales").empty();
	var counterOutput = "";
	counterOutput = counterOutput + "<option value=''>-- Select Counter --</option>"; 
	$("#counterSales").append(counterOutput);
	
	$("#storeSales").empty();
	var storeOutput = "";
	storeOutput = storeOutput + "<option value=''>-- Select Store --</option>"; 
	$("#storeSales").append(storeOutput);
	
	$("#fromDate").datepicker("option", "minDate", null);
	$("#fromDate").datepicker("option", "maxDate", null);
	$("#toDate").datepicker("option", "minDate", null);
	$("#toDate").datepicker("option", "maxDate", null);
	
}
</script>

<script>
function getAllCountersByStore(){
	var storeId = document.getElementById("storeDropDown").value; 
	var operoxUrl ='${operoxUrl}';
	 if(storeId != null && storeId != "" && storeId != 'undefined'){
		 $.ajax({
		    	type: "POST",
		        url: operoxUrl+"/getCountersListByStoreId?${_csrf.parameterName}=${_csrf.token}&storeId="+storeId, 
		        success: function(data) {
		        	var json = JSON.parse(data);
		        	var output = "";
		        	output = output + "<option value=''>-- Select Counter --</option>"; 
		        	for (var i=0; i<json.length; i++)
					{
			        		var opt = json[i];
			        		output = output + "<option value='" +opt.id+ "'>"+opt.counterName+"</option>";
					}
		        	$("#counterSales").empty();
			        $("#counterSales").append(output);
		        },
		    });
	 }else{
		 var output = "";
    	 output = output + "<option value=''>-- Select Counter --</option>"; 
    	 $("#counterSales").empty();
	     $("#counterSales").append(output);
	 }
}
</script>

<script>
function getStoreByStoreType(){
	var storeType = document.getElementById("storeTypeDropDown").value; 
	var operoxUrl ='${operoxUrl}';
	 if(storeType != null && storeType != "" && storeType != 'undefined'){
		 $.ajax({
		    	type: "POST",
		        url: operoxUrl+"/getStoresListByStoreType?${_csrf.parameterName}=${_csrf.token}&storeType="+storeType, 
		        success: function(data) {
		        	var json = JSON.parse(data);
		        	var output = "";
		        	output = output + "<option value=''>-- Select Store --</option>"; 
		        	for (var i=0; i<json.length; i++)
					{
			        		var opt = json[i];
			        		output = output + "<option value='" +opt.id+ "'>"+opt.storeName+"</option>";
					}
		        	$("#storeSales").empty();
			        $("#storeSales").append(output);
		        },
		    });
	 }else{
		 var output = "";
    	 output = output + "<option value=''>-- Select Store --</option>"; 
    	 $("#storeSales").empty();
	     $("#storeSales").append(output);
	 }
}
</script>

<script>
function pagination(){  
    $('#reportsPagination').DataTable( { 
    	"scrollCollapse": false,
    	"lengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
    	dom: 'Bfrtip',
        buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ],
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
    $('#fromDate').datepicker({
	      dateFormat: 'mm/dd/yy',
	      onSelect: function (selected) {
	            var dt = new Date(selected);
	            dt.setDate(dt.getDate() + 1);
	            $("#toDate").datepicker("option", "minDate", dt);
	        }
	  });
	  
	  $('#toDate').datepicker({
	      dateFormat: 'mm/dd/yy',
	        onSelect: function (selected) {
	            var dt = new Date(selected);
	            dt.setDate(dt.getDate() - 1);
	            $("#fromDate").datepicker("option", "maxDate", dt);
	        }
	  }); 
  });
</script>

<jsp:include page="../masterFooter.jsp" />

