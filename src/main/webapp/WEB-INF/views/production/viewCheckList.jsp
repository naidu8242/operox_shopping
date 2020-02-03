<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<script type="text/javascript">
window.onload = function () {
	var checkListId = "${checkList.id}";
	getSubCheckReportList(checkListId);
 };
 </script>
 
<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <a href="${operoxUrl}/qaCheckList"><i class="fa fa-arrow-left"></i>Back to CheckList Home</a>
    </div>
  </section>
  
  <!-- Content Area inner -->

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
    
    	<div>
        <b>Checklist name</b><span>${checkList.checkName}</span>
        <div class="clearfix"></div>
        </div>
        
        <div>
    	<c:if test="${empty checkList.checkListType}">
    	<b>Check List Type</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty checkList.checkListType}">
    	<b>Check List Type</b><span> ${checkList.checkListType}</span>
    	</c:if>
        
        <div class="clearfix"></div>
        </div>
        <div>
    	<c:if test="${empty checkList.fileName}">
    	<b>File Name</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty checkList.fileName}">
    	<b>fileName</b><span> ${checkList.fileName}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        <div>
    	<c:if test="${empty checkList.rawMaterial}">
    	<b>Name</b><span> ${checkList.productid.productName} </span>
    	</c:if>
    	<c:if test="${!empty checkList.rawMaterial}">
    	<b>Name</b><span> ${checkList.rawMaterial.materialName}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
         <div>
    	<c:if test="${empty checkList.description}">
    	<b>Description</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty checkList.description}">
    	<b>Description</b><span> ${checkList.description}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>                                                        
        
    </div>
    <div class="clearfix"></div>
</div>	  	
<div id="subCheckReportList"></div> 
</section>
<!-- Content Area Ends--> 

<script type="text/javascript">
  function getSubCheckReportList(checkListId){
	  var operoxUrl ='${operoxUrl}';
	   $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getSubCheckReportList?${_csrf.parameterName}=${_csrf.token}&checkListId="+checkListId, 	   
 		    success: function(data) {
 		    	var json = JSON.parse(data);
				  var result = "";
				  result = result + '<div class="data_table">';
				  result = result + '<table id="subCategoryPagination">';
				  result = result + '<thead>';				  
				  result = result + '<tr>';
				  result = result + ' <th>S.No.</th>';
				  result = result + '<th>Test</b></th>';
				  result = result + '<th>Procedure</th>';
				  result = result + '<th>Value</th>';
				  result = result + '</tr>';
				  result = result + '</thead>';
				  result = result + '<tbody>';				 
				  var j=0;
				  for (var i=0; i<json.length; i++){
					  j++;
					  var description = "No data";
					  
					  var report = json[i];
					  
					  if(report.testDescription != null && report.testDescription != "" && report.testDescription != 'undefind'){
						  description = report.testDescription;
					  }
					  result = result + '<tr>';
					  result = result + '<td><div class="ta-sm">'+j+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.whatToCheck+'</div></td>';
					  result = result + '<td><div class="ta-m">'+report.howToCheck+'</div></td>';
					  result = result + '<td><div class="ta-m">'+description+'</div></td>';
					  result = result + '</tr>';
				  }
				  result = result + '</tbody>';
				  result = result + '</table>';
				  $("#subCheckReportList").empty(); 
				  $("#subCheckReportList").append(result); 
				  subCategoryPagination();	 
 		    }
	  });  
  }
</script>

<script>
function subCategoryPagination(){  
    $('#subCategoryPagination').DataTable( { 
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








<jsp:include page="../masterFooter.jsp" />