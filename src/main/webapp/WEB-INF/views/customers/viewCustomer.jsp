<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="resources/css/bootstrap-select.min.css">
<link href="resources/css/font-awesome.min.css" rel="stylesheet">
<link href="resources/css/operox2.css" rel="stylesheet">
<link href="resources/css/operox-main.css" rel="stylesheet">

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <a href="${operoxUrl}/customers"><i class="fa fa-arrow-left"></i>Back to Customers</a>
    </div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
    	<div>
    	<c:if test="${empty customer.id}">
    	<b>Customer id</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty customer.id}">
    	<b>Customer id</b><span> ${customer.id}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
    	<div>
    	<c:if test="${empty customer.customerName}">
    	<b>Customer Name</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty customer.customerName}">
    	<b>Customer Name</b><span> ${customer.customerName}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>        
        
    	<div>
    	<c:if test="${empty customer.email}">
    	<b>Email</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty customer.email}">
    	<b>Email</b><span> ${customer.email}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Phone number</b><span> ${customer.phone}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
    	<c:if test="${empty customer.dateStr}">
    	<b>Date of birth</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty customer.dateStr}">
    	<b>Date of birth</b><span> ${customer.dateStr}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        <div>
    	<c:if test="${empty customer.customeruid}">
    	<b>Loyalty number</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty customer.customeruid}">
    	<b>Loyalty number</b><span> ${customer.customeruid}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
    	<div>
    	<c:if test="${empty customer.addressId.address}">
    	<b>Address</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty customer.addressId.address}">
    	<b>Address</b><span> ${customer.addressId.address}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
    	<div>
    	<c:if test="${empty customer.addressId.country && empty customer.addressId.state && empty customer.addressId.city}">
    	<b>Location</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty customer.addressId.country && empty customer.addressId.state && empty customer.addressId.city}">
    	<b>Location</b><span> ${customer.addressId.country} </span>
    	</c:if>
    	<c:if test="${!empty customer.addressId.country && !empty customer.addressId.state && empty customer.addressId.city}">
    	<b>Location</b><span> ${customer.addressId.country}, ${customer.addressId.state} </span>
    	</c:if>
    	<c:if test="${!empty customer.addressId.country && !empty customer.addressId.state && !empty customer.addressId.city}">
    	<b>Location</b><span> ${customer.addressId.country}, ${customer.addressId.state}, ${customer.addressId.city} </span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
    	<div>
    	<c:if test="${empty customer.customerTinNumber}">
    	<b>Tax number</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty customer.customerTinNumber}">
    	<b>Tax number</b><span> ${customer.customerTinNumber}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
    	<div>
    	<c:if test="${empty customer.updatedBy}">
    	<b>Updated by</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty customer.updatedBy}">
    	<b>Updated by</b><span> ${customer.updatedBy}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div> 
        
    	<div>
    	<c:if test="${empty customer.updatedDateStr}">
    	<b>Updated date</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty customer.updatedDateStr}">
    	<b>Updated date</b><span> ${customer.updatedDateStr}</span>
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

<!-- Footer Area -->
