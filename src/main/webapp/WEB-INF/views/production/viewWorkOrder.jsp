<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="resources/css/bootstrap-select.min.css">
<link href="resources/css/font-awesome.min.css" rel="stylesheet">
<link href="resources/css/operox2.css" rel="stylesheet">
<link href="resources/css/operox-main.css" rel="stylesheet">
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>


<script>
	$(document).ready(function() {
		var type = '${type}';
		if(type == 'raw'){
			// To select the second tab
			 $('.nav-tabs li:eq(1) a').tab('show');
			 $('#myAnchor').click(); 
		}else if(type == 'machinery'){
			
			 $('.nav-tabs li:eq(4) a').tab('show');
			 $('#myAnchorOne').click(); 
		}
	});
</script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Work order: Summary</h2>
    </div>
    <div class="pull-right"><a href="${operoxUrl}/workOrder">Work Order List</a></div>
  </section>
  
  <!-- Content Area inner Tab -->
  <div class="content-area clearfix">
    <div class="accountsettings-tab">
        <ul class="nav nav-tabs" id="myTabs">
        <li id="one"  class="active"><a data-toggle="tab" href="#details">Details</a></li>
        <li id="two"><a id="myAnchor" data-toggle="tab" href="#productionPlanning" onclick="getRawMaterial('${workOrder.id}');">Raw Material</a></li>
        <li id="three"><a data-toggle="tab" href="#resourcePlanning" onclick="getResourcePanning('${workOrder.id}');">Resource</a></li>
        <li id="four"><a id="myAnchorOne" data-toggle="tab" href="#workOrderMachinery" onclick="getMachineryDetails('${workOrder.id}');">Machinery</a></li>
		<li id="five"><a data-toggle="tab" href="#qcRawMaterial" onclick="getQcRawMaterialList('${workOrder.id}','RawMaterial');">QA/QC-Raw Material</a></li>
        <li id="six"><a data-toggle="tab" href="#qcRawMaterial" onclick="getQcRawMaterialList('${workOrder.id}','Products');">QA/QC-Product</a></li>
         </ul>
        <div class="tab-content panel-body">
        <!-- Details Tab -->
            <div id="details" class="tab-pane fade in active">
<div class="view_wrap no_pad">
    <div class="view_wrap_details view_wrap_details_a view_wrap_details100">
    
    	<div class="clearfix">
    	<c:if test="${empty workOrder.workOrderNumber}">
    	<b>Work order Number</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty workOrder.workOrderNumber}">
    	<b>Work order Number</b><span> ${workOrder.workOrderNumber}</span>
    	</c:if>
        </div>
        
    	<div class="clearfix">
    	<c:if test="${empty workOrder.customer.customerName}">
    	<b>Customer</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty workOrder.customer.customerName}">
    	<b>Customer</b><span> ${workOrder.customer.customerName}</span>
    	</c:if>
        </div>        
        
    	<div class="clearfix">
    	<c:if test="${empty workOrder.purchaseOrderId}">
    	<b>PO Number</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty workOrder.purchaseOrderId}">
    	<b>PO Number</b><span> ${workOrder.purchaseOrderId}</span>
    	</c:if>
        </div>
        
    	<div class="clearfix">
    	<c:if test="${empty workOrder.orderDateStr}">
    	<b>Order Date</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty workOrder.orderDateStr}">
    	<b>Order Date</b><span> ${workOrder.orderDateStr}</span>
    	</c:if>
        </div>        
        
    	<div class="clearfix">
        <c:if test="${empty workOrder.dueDateStr}">
    	<b>Due Date</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty workOrder.dueDateStr}">
    	<b>Due Date</b><span> ${workOrder.dueDateStr}</span>
    	</c:if>
        </div> 
        
    	<div class="clearfix">
       <c:if test="${empty workOrder.despatchedOnDatesStr}">
    	<b>Despatched Date</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty workOrder.despatchedOnDatesStr}">
    	<b>Despatched Date</b><span> ${workOrder.despatchedOnDatesStr}</span>
    	</c:if>
        </div>

        <div class="clearfix">
        <c:if test="${empty workOrder.approvedUser.firstName}">
    	<b>Approved by</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty workOrder.approvedUser.firstName}">
    	<b>Approved by</b><span> ${workOrder.approvedUser.firstName}</span>
    	</c:if>
        </div>                                                                                                

        <div class="clearfix">
        <c:if test="${empty workOrder.workOrderStatus}">
    	</c:if>
    	<c:if test="${!empty workOrder.workOrderStatus}">
    	<b>Status</b><span> ${workOrder.workOrderStatus}</span>
    	</c:if>
        </div>  
        
         <div class="clearfix">
         <c:if test="${empty workOrder.commants}">
    	<b>Comments</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty workOrder.commants}">
    	<b>Comments</b><span> ${workOrder.commants}</span>
    	</c:if>
        </div>
  </div>
  <div class="clearfix"></div>        
 <!-- Table Designa Resource Management -->
 <div id="productsList"></div> 
    <div class="data_table more-table mt">
    	<table>
        	<thead>
            	<tr>
                	<th>Sl.No</th>
                    <th>Product Name</th>
                    <th>Batch Id</th>
                    <th>Total Qty</th>
                    <th>Awaiting for QC</th>
                    <th>Status</th>
                </tr>
             </thead>
             <tbody>
             	<c:forEach var="workOrder" items="${workOrderItemsList}" varStatus="count">
            	<tr>
                <td><div class="ta-sm">${count.index+1}</div></td>
                <td><div class="ta-l">${workOrder.product.productName}</div></td>
                <td><div class="ta-sm">${workOrder.batchId}</div></td>
                <td><div class="ta-sm">${workOrder.totalQty}</div></td>
                <td><div class="ta-m">${workOrder.totalQtyForQC}</div></td>
                <td><div class="ta-m">${workOrder.workOrder.workOrderStatus}</div></td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div> 
  <!--Table Designa Resource Management ends -->
    
    <div class="clearfix"></div>
  </div>
  </div>
   <div id="productionPlanning" class="tab-pane fade in">
   </div>
  <div id="resourcePlanning" class="tab-pane fade in">
    </div>
    <div id="workOrderMachinery" class="tab-pane fade in">
    </div>
    <div id="qcRawMaterial" class="tab-pane fade in">
    </div>
    
    <div id="qcProduct" class="tab-pane fade in">
    </div>

   
            <!-- planning Tab Ends-->
            
            </div>
            </div>
            </div>
        <!-- Details Tab Ends -->
</section>
<script type="text/javascript">
window.onload = function () {
	var workOrderId = "${workOrder.id}";
	getProductsList(workOrderId);
 };
 </script>

<script type="text/javascript">
  function getProductsList(workOrderId){
	  var operoxUrl ='${operoxUrl}';
	   $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getProductsList?${_csrf.parameterName}=${_csrf.token}&workOrderId="+workOrderId, 	   
 		    success: function(data) {
 		    	var json = JSON.parse(data);
				  var result = "";
				  result = result + '<div class="data_table">';
				  result = result + '<table id="productsListPagination">';
				  result = result + '<thead>';				  
				  result = result + '<tr>';
				  result = result + '<th>Sl.No</th>';
				  result = result + '<th>Product Name</th>';
				  result = result + '<th>Batch Id</th>';
				  result = result + '<th>Total Qty</th>';
				  result = result + '<th>Awaiting for QC</th>';
				  result = result + '<th>QC Cleared</th>';
				  result = result + '<th>Status</th>';
				  result = result + '</tr>';
				  result = result + '</thead>';
				  result = result + '<tbody>';				 
				  
				  for (var i=0; i<json.length; i++){
					  
					  var description = "---";
					  
					  var report = json[i];
					  if(report.description != null && report.description != "" && report.description != 'undefinde'){
						  description = report.description;
					  }
					  result = result + '<tr>';
					  result = result + '<td><div class="ta-sm">'+report.id+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.productName+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.batchId+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.totalQty+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.totalQtyForQC+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.workOrderItemStatus+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.status+'</div></td>';
					  
					  result = result + '</tr>';
				  }
				  result = result + '</tbody>';
				  result = result + '</table>';
				  $("#productsList").empty(); 
				  $("#productsList").append(result); 
				  productsListPagination();	 
 		    }
	  });  
  }
</script>
<script>
function productsListPagination(){  
    $('#productsListPagination').DataTable( { 
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
<!-- Content Area Ends--> 
<jsp:include page="workOrderRawMaterial.jsp" />
<jsp:include page="workOrderResource.jsp" />
<jsp:include page="workOrderQcRawMaterial.jsp" />

<jsp:include page="workOrderMachinery.jsp" />
<jsp:include page="../masterFooter.jsp" />

<!-- Footer Area -->
