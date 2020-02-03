<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <a href="${operoxUrl}/counterList"><i class="fa fa-arrow-left"></i>Back to Counter</a>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; View Counter</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
    	<div>
        <b>Counter Name</b><span>${counter.counterName}</span>
        <div class="clearfix"></div>
        </div>        
        
    	<div>
        <b>Counter Type</b><span>${counter.counterType}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Location Name</b><span>${counter.store.storeName}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Description</b>
           <span>
              <c:if test="${!empty counter.description}">
                 ${counter.description}
              </c:if>
               <c:if test="${empty counter.description}">
                 ---
              </c:if>
            </span>  
        <div class="clearfix"></div>
        </div>

    	<div>
        <b>Updated By</b><span>${counter.updatedBy}</span>
        <div class="clearfix"></div>
        </div>
                
    	<div>
        <b>Updated Date</b><span><fmt:formatDate pattern="yyyy/MM/dd" value="${counter.updatedDate}"/></span>
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

