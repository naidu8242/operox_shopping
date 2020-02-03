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
	getProductReports();
 };
</script>
<section class="content-wraper">
<!-- Content Area -->

  <section class="content-header clearfix">
    <div class="pull-left">
		<h2>Product Reports </h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; Product Reports</div>
  </section>
   <div id="loading"></div> 
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
		<div class="report_head">   
		<form:form id="reports_from"  name="reports_from" method="post"> 
		
		     <div class="div4">
			       <label class="labl">Location</label>
			       <span>
			             <select id="locationId" name="locationId">
			                <option value="">--Select Location--</option>
			                  <c:forEach var="store" items="${storesList}">
	                            <option value="${store.id}">${store.storeName}</option>
                              </c:forEach>
			             </select>
			         </span>
			    </div> 
		
		    <div class="div4">
		       <label class="labl">Expired Date From</label>
		       <span class="addiconSpace">
		         <input type="text" value="" placeholder="Select Date" id="fromDate" name="fromDate" readonly="readonly"/>
		         <div class="addIcon"><i class="fa fa-calendar" title="Select Date"></i></div>
		       </span>
		    </div>
		    
		    <div class="div4">
		       <label class="labl">Expired Date To</label>
		       <span class="addiconSpace">
		         <input type="text" value="" placeholder="Select Date" id="toDate" name="toDate" readonly="readonly"/>
		         <div class="addIcon"><i class="fa fa-calendar" title="Select Date"></i></div>
		       </span>
		    </div>   
		    
		    <input type="button" value="Submit" class="btn btn-primary my-btn" id="add_counter" onclick="getProductReports();">
		<div class="clearfix"></div>
		
	  </form:form>	
		</div>     
	  <div id="productReportsList"></div>
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
  function getProductReports(){
	  var operoxUrl ='${operoxUrl}';
	  var frm = $('#reports_from').serializeFormJSON();
      var con = JSON.stringify(frm);
      con = con.replace(/[{}]/g, "");
      var jsonData = encodeURIComponent(con);
	  
      $("#productReportsList").empty(); 
	  $("#loading").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("resources/images/loading.gif"); background-position:center; background-repeat:no-repeat; background-size:50px;');
	  
	  $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getProductReportsList?${_csrf.parameterName}=${_csrf.token}&json="+jsonData, 	   
 		    success: function(data) {
 		    	var json = JSON.parse(data);
				var result = "";
				if(json.length > 0)
			   	{
				  result = result + '<div class="data_table">';
				  result = result + '<table id="productReportsPagination" class="display" cellspacing="0" width="100%">';
				  result = result + '<thead>';
				  result = result + '<tr>';
				  result = result + '<th>Barcode</th>';
				  result = result + '<th>Product name</th>';
				  result = result + '<th>MRP</th>';
				  result = result + '<th>Qty</th>';
				  result = result + '<th>Exire Date</th>';
				  result = result + '</tr>';
				  result = result + '</thead>';
				  result = result + '<tbody>';
				 
				  
				  for (var i=0; i<json.length; i++){
					  
					  var report = json[i];
					  result = result + '<tr>';
					  result = result + '<td><div class="ta-m">'+report.barCode+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.productName+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.priceStr+' </div></td>';
					  result = result + '<td><div class="ta-sm">'+report.currentQuantity+'</div></td>';
					  result = result + '<td><div class="ta-sm">'+report.expireDateStr+'</div></td>';
					  result = result + '</tr>';
				  }
				  result = result + '</tbody>';
				  result = result + '</table>';
				  result = result + '<div class="clearfix"></div>';
				  $("#productReportsList").empty(); 
				  $("#productReportsList").append(result); 
				  $("#loading").removeAttr("style");
				  pagination();	 
 		    }else{
	    		result = "<div class='content-area mt text-center text-muted'><i class='mt fa fa-5x fa-user'></i><h3>No Products available</h3></div>";
	    		$("#productReportsList").empty(); 
	    		$('#productReportsList').append(result);
	    		$("#loading").removeAttr("style");
	    	}
 		  }
	  }); 
  }

</script>

<!-- <script>
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
</script> -->

<script>
function pagination(){  
    $('#productReportsPagination').DataTable( { 
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

