<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	getProductList();
}); 
</script> 

<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Product list</h2>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Add Product</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">
  		<div class="data_table_head">
        	<ul>              
                 <li><a href="<c:url value='/addProduct'/>"><i class="fa fa-plus"></i>Add Product</a></li>
                <div class="clearfix"></div>
            </ul>
            <div class="clearfix"></div>                 	
    	</div>
    	<div id="productList"></div>
	
  </div>
</section>

<script>
	function predefineProductImage(imageId){
		$(imageId).attr('src','${operoxUrl}/resources/images/add-product.jpg');
	}
</script>

<script>

function getProductList(){
	var operoxUrl = '${operoxUrl}';
		$.ajax({
		type : "GET",
		url : operoxUrl+ "/getProductsList",
		success : function(data) {
			var json = JSON.parse(data);
	        	var result = "";
	        	result = result + '<div class="data_table">';
	        	result = result + '<table id="productPagination" class="display" cellspacing="0" width="100%">';
	        	result = result + '<thead>';
	        	result = result + '<tr>';
	        	result = result + '<th>Image</th>';
	        	result = result + '<th>Product name</th>';
	        	result = result + ' <th>Search code</th>';
	        	result = result + '<th>Category</th>';
	        	result = result + '<th>Brand</th>';
	        	result = result + '<th>Updated by</th>';
	        	result = result + '<th class="action">Action</th>';
	        	result = result + ' </tr>';
	        	result = result + '</thead>';
	        	result = result + '<tbody>';
	        	
	        	
	        	for (var i=0; i<json.length; i++)
				{  
	        		var report = json[i];
	        	result = result + '<tr>';
	        	result = result + '<td class="image"><div><img onerror="predefineProductImage(this)"  src="'+operoxUrl+'/displayProductImage/'+report.id+'" alt=""/></div></td>';
	        	
	     /*        result = result + '<td><div class="ta-m">'+report.productName+'</div></td>'; */
	            result = result + '<td><div class="ta-m"><a href= "${operoxUrl}/viewProduct/'+report.id+'">'+report.productName+'</a></div></td>';
	            if(report.productCode != null && report.productCode != '' && report.productCode != 'undefined'){
	            	result = result + '<td><div class="ta-m">'+report.productCode+'</div></td>';
   	        	}else{
   	        		result = result + '<td><div class="ta-m">--</div></td>';
   	        	}
	            if(report.catagoryName != null && report.catagoryName != '' && report.catagoryName != 'undefined'){
	            	result = result + '<td><div class="ta-sm">'+report.catagoryName+'</div></td>';
   	        	}else{
   	        		result = result + '<td><div class="ta-m">--</div></td>';
   	        	}
	            if(report.brandName != null && report.brandName != '' && report.brandName != 'undefined'){
	            	result = result + '<td><div class="ta-m">'+report.brandName+'</div></td>';
   	        	}else{
   	        		result = result + '<td><div class="ta-m">--</div></td>';
   	        	}
   	        	
   	        	if(report.updatedBy != null && report.updatedBy != '' && report.updatedBy != 'undefined'){
   	        		result = result + '<td><div class="ta-m">'+report.updatedBy+'</div></td>';
   	        	}else{
   	        		result = result + '<td><div class="ta-m">--</div></td>';
   	        	}
   	        	result = result + '<td class="action"><div>';
   	        	result = result + '<a href="${operoxUrl}/editProduct/'+report.id+'"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme" onclick="deleteProduct('+report.id+');"></i></div>';
   	        	result = result + '</td>';
   	        	result = result + '</tr>';
	        	}
				
	        	result = result +'</tbody>';
	        	result = result +'</table>';        
// 	        	result = result +'</div>';
	            $("#productList").empty();  
	    	    $("#productList").append(result);
	    	    productHomePagination();
		},
	}); 
}

</script>

<script>
function productHomePagination(){ 
    $('#productPagination').DataTable( {
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
function deleteProduct(productId){
	var operoxUrl = '${operoxUrl}';
	 var message = confirm("Do you want to delete this brand");
     if (message == true) {
	$.ajax({
		type : "POST",
		url : operoxUrl+ "/deleteProduct?${_csrf.parameterName}=${_csrf.token}&productId="+productId,
		success : function(data) {
			 getProductList();
		},
	}); 
     }
}
</script>

<jsp:include page="../masterFooter.jsp" />
