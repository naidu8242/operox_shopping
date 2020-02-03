<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script> 


<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <a href="${operoxUrl}/store"><i class="fa fa-arrow-left"></i>Back to Locations</a>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; View Location</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
    	<div>
        <b>Name</b><span>${store.storeName}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Type</b>
          <span>${store.storeType}</span>
        <div class="clearfix"></div>
        </div>        
        
    	<div>
        <b>Email</b>
           <span>
             <c:if test="${!empty store.email}">
                 ${store.email}
              </c:if>
              <c:if test="${empty store.email}">
                 ---
              </c:if>
           </span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Phone Number</b>
           <span>
             <c:if test="${!empty store.phoneNumber}">
                 ${store.phoneNumber}
              </c:if>
              <c:if test="${empty store.phoneNumber}">
                 ---
              </c:if>
           </span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Address</b>
            <span>
	              <c:if test="${!empty store.address.address}">
	                 ${store.address.address}
	              </c:if>
	              <c:if test="${empty store.address.address}">
	                 ---
	              </c:if>
           </span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Location</b><span>${store.address.country}, ${store.address.state}, ${store.address.city}<c:if test="${!empty store.address.zipcode}">, ${store.address.zipcode}</c:if></span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Updated By</b>
             <span>
	              <c:if test="${!empty store.updatedBy}">
	                 ${store.updatedBy}
	              </c:if>
	              <c:if test="${empty store.updatedBy}">
	                 ---
	              </c:if>
           </span>
        <div class="clearfix"></div>
        </div> 
        
    	<div>
        <b>Updated Date</b><span></span>
           <span>
	              <c:if test="${!empty store.updatedDate}">
	                 <fmt:formatDate pattern="yyyy/MM/dd" value="${store.updatedDate}"/>
	              </c:if>
	              <c:if test="${empty store.updatedDate}">
	                 ---
	              </c:if>
           </span>
        <div class="clearfix"></div>
        </div>                                                                                              
        
    </div>
    <div class="clearfix"></div>
</div>	  	
  
  </div>
  
</section>
<!-- Content Area Ends--> 

<!-- Footer Area -->
<jsp:include page="../masterFooter.jsp" />
<!-- Footer Area --> 

