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

<!-- Content Area -->
<script type="text/javascript">
window.onload = function () {
	var category = "${category.id}";
	getSubCategoryList(category);
 };
 </script>
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <a href="${operoxUrl}/category"><i class="fa fa-arrow-left"></i>Back to Category</a>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Category</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
    	<div>
    	<c:if test="${empty category.id}">
    	<b>category id</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty category.id}">
    	<b>category id</b><span> ${category.id}</span>
    	</c:if>
        
        <div class="clearfix"></div>
        </div>
        
    	<div>
    	<c:if test="${empty category.categoryName}">
    	<b>Category Name</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty category.categoryName}">
    	<b>Category Name</b><span> ${category.categoryName}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>        
        
    	<div>
    	<c:if test="${empty category.description}">
    	<b>Category description</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty category.description}">
    	<b>Category description</b><span> ${category.description}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        
    	<div>
    	<c:if test="${empty category.userName}">
    	<b>Updated by</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty category.userName}">
    	<b>Updated by</b><span> ${category.userName}</span>
    	</c:if>
        <div class="clearfix"></div>                                                                                 
        
    </div>
    <div class="clearfix"></div>
</div>	  	
  <div id="subCategoryList"></div>  

  
</section>
<!-- Content Area Ends--> 


<script type="text/javascript">
  function getSubCategoryList(categoryId){
	  var operoxUrl ='${operoxUrl}';
	   $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getSubCategoryList?${_csrf.parameterName}=${_csrf.token}&categoryId="+categoryId, 	   
 		    success: function(data) {
 		    	var json = JSON.parse(data);
				  var result = "";
				  result = result + '<div class="data_table">';
				  result = result + '<table id="subCategoryPagination">';
				  result = result + '<thead>';				  
				  result = result + '<tr>';
				  result = result + '<th>Sl.no</th>';
				  result = result + '<th>Subcategory Name</th>';
				  result = result + '<th>Subcategory Description</th>';
				  result = result + '</tr>';
				  result = result + '</thead>';
				  result = result + '<tbody>';				 
				  var j=0;
				  for (var i=0; i<json.length; i++){
					  j++;
					  var description = "---";
					  
					  var report = json[i];
					  
					  if(report.description != null && report.description != "" && report.description != 'undefinde'){
						  description = report.description;
					  }
					  result = result + '<tr>';
					  result = result + '<td><div class="ta-sm">'+j+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.categoryName+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.description+'</div></td>';
					  result = result + '</tr>';
				  }
				  result = result + '</tbody>';
				  result = result + '</table>';
				  $("#subCategoryList").empty(); 
				  $("#subCategoryList").append(result); 
				  subCategoryPagination();	 
 		    }
	  });  
  }
</script>

<script>
function subCategoryPagination(){  
    $('#subCategoryPagination').DataTable( { 
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

<!-- Footer Area -->
