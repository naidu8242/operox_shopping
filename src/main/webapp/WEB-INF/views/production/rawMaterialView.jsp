<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<script src="${operoxUrl}/resources/js/formvalidator.js"></script>
<link rel="stylesheet" href="${operoxUrl}/resources/css/chosen.min.css">
<script src="${operoxUrl}/resources/js/chosen.jquery.min.js"></script> 
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <a href="<c:url value="/rawMaterials"/>" title="Rawmaterial List"><i class="fa fa-arrow-left"></i>Back to Raw Material</a>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/rawMaterials">Raw Materials List </a>&raquo; Raw Material View</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
    	<div>
        <b>Raw Material Name</b><span>${rawMaterial.materialName}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Unit Price</b><span>${rawMaterial.unitPrice}</span>
        <div class="clearfix"></div>
        </div>        
        
    	<div>
        <b>Annul Order Qty</b><span>${rawMaterial.annualOrderQuantity}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Code</b><span>${rawMaterial.searchCode}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
    	<c:if test="${empty rawMaterial.discount}">
        <b>Discount</b><span>----</span>
        </c:if>
    	<c:if test="${!empty rawMaterial.discount}">
        <b>Discount</b><span>${rawMaterial.discount} &nbsp;%</span>
        </c:if>
        <div class="clearfix"></div>
        </div>
        
        
    	<div>
        <b>Production Unit</b><span>${rawMaterial.store.storeName}</span>
        <div class="clearfix"></div>
        </div> 
        
        
    	<div>
    	<c:if test="${empty rawMaterial.measuringUnit.measuringUnit}">
        <b>Measuring Unit</b><span>---</span>
        </c:if> 
    	<c:if test="${!empty rawMaterial.measuringUnit.measuringUnit}">
        <b>Measuring Unit</b><span>${rawMaterial.measuringUnit.measuringUnit}</span>
        </c:if> 
        <div class="clearfix"></div>
        </div> 
        
    	<div>
        <b>Available Inventory</b><span>${rawMaterial.availableInventory}</span>
        <div class="clearfix"></div>
        </div>                                                                                                          
        
    </div>
    <div class="clearfix"></div>
</div>	  	
  
  </div>
  
</section>
<!-- Content Area Ends--> 


</div>
<jsp:include page="../masterFooter.jsp" />