<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="wraper clearfix">

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <a href="${operoxUrl}/leaves"><i class="fa fa-arrow-left"></i>Back to Leave list</a>
    </div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
        <div>
    	<c:if test="${empty leaves.leaveName}">
    	<b>Leave name</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty leaves.leaveName}">
    	<b>Leave Name</b><span> ${leaves.leaveName}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        
        <div>
    	<c:if test="${empty leaves.description}">
    	<b>Description</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty leaves.description}">
    	<b>Description</b><span> ${leaves.description}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        <div>
    	<c:if test="${empty leaves.updatedBy}">
    	<b>Updated by</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty leaves.updatedBy}">
    	<b>Updated by</b><span> ${leaves.updatedBy}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>

		 <div>
    	<c:if test="${empty leaves.updatedDateStr}">
    	<b>Updated date</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty leaves.updatedDateStr}">
    	<b>Updated date</b><span> ${leaves.updatedDateStr}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        
    </div>
    <div class="clearfix"></div>
</div>	  	
  
  </div>
  
</section>
<!-- Content Area Ends--> 
</div>
