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
      <a href="${operoxUrl}/suppliers"><i class="fa fa-arrow-left"></i>Back to Suppliers</a>
    </div>
    <div class="pull-right brud-crum"></div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
    	<div>
    	<c:if test="${empty supplier.name}">
        <b>Supplier Name</b><span>----</span>
        </c:if>
        <c:if test="${!empty supplier.name}">
        <b>Supplier Name</b><span>${supplier.name}</span>
        </c:if>
        <div class="clearfix"></div>
        </div>
        
    	<div>
    	<c:if test="${empty supplier.supplierContactName}">
        <b>Contact Name</b><span>----</span>
        </c:if>
        <c:if test="${!empty supplier.supplierContactName}">
        <b>Contact Name</b><span>${supplier.supplierContactName}</span>
        </c:if>
        <div class="clearfix"></div>
        </div>        
        
    	<div>
    	<c:if test="${empty supplier.supplierContactEmail}">
        <b>Email</b><span>----</span>
        </c:if>
        <c:if test="${!empty supplier.supplierContactEmail}">
        <b>Email</b><span>${supplier.supplierContactEmail}</span>
        </c:if>
        <div class="clearfix"></div>
        </div>
        
    	<div>
    	<c:if test="${empty supplier.supplierContactPhone}">
        <b>Phone number</b><span>----</span>
        </c:if>
        <c:if test="${!empty supplier.supplierContactPhone}">
        <b>Phone number</b><span>${supplier.supplierContactPhone}</span>
        </c:if>
        <div class="clearfix"></div>
        </div>
        
    	<div>
    	<c:if test="${empty supplier.address.address}">
        <b>Address</b><span>----</span>
        </c:if>
        <c:if test="${!empty supplier.address.address}">
        <b>Address</b><span>${supplier.address.address}</span>
        </c:if>
        <div class="clearfix"></div>
        </div>
        
    	<div>
    	<c:if test="${!empty supplier.address.country && !empty supplier.address.zipcode}">
        <b>Location</b><span>${supplier.address.country}, ${supplier.address.state}, ${supplier.address.city}, ${supplier.address.zipcode}</span>
        </c:if>
        <c:if test="${empty supplier.address.country && empty supplier.address.zipcode}">
        <b>Location</b><span>----</span>
        </c:if>
        <div class="clearfix"></div>
        </div>
        
    	<div>
    	<c:if test="${empty supplier.tin}">
        <b>TIN</b><span>---</span>
        </c:if>
        <c:if test="${!empty supplier.tin}">
        <b>TIN</b><span>${supplier.tin}</span>
        </c:if>
        <div class="clearfix"></div>
        </div> 
        
    	<div>
    	<c:if test="${empty supplier.updatedBy}">
        <b>Updated by</b><span>----</span>
        </c:if>
        <c:if test="${!empty supplier.updatedBy}">
        <b>Updated by</b><span>${supplier.updatedBy}</span>
        </c:if>
        <div class="clearfix"></div>
        </div> 
        
    	<div>
    	<c:if test="${empty supplier.updatedDateStr}">
        <b>Updated date</b><span>----</span>
        </c:if>
        <c:if test="${!empty supplier.updatedDateStr}">
        <b>Updated date</b><span>${supplier.updatedDateStr}</span>
        </c:if>
        <div class="clearfix"></div>
        </div>                                                                                              
        
    </div>
    <div class="clearfix"></div>
</div>	  	
  
  </div>
  
</section>
<!-- Content Area Ends--> 


<jsp:include page="../masterFooter.jsp" />
