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
      <a href="${operoxUrl}/brandHome"><i class="fa fa-arrow-left"></i>Back to Brand</a>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Brand</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
        <div>
    	<c:if test="${empty brand.id}">
    	<b>Brand id</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty  brand.id}">
    	<b>Brand id</b><span> ${brand.id}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        <div>
    	<c:if test="${empty brand.brandName}">
    	<b>Brand name</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty  brand.brandName}">
    	<b>Brand name</b><span> ${brand.brandName}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        <div>
    	<c:if test="${empty brand.description}">
    	<b>Description</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty  brand.description}">
    	<b>Description</b><span> ${brand.description}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        <div>
    	<c:if test="${empty brand.manufacturedBy}">
    	<b>Manufactured by</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty  brand.manufacturedBy}">
    	<b>Manufactured by</b><span> ${brand.manufacturedBy}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        <div>
    	<c:if test="${empty brand.marketedBy}">
    	<b>Marketed by</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty  brand.marketedBy}">
    	<b>Marketed by</b><span> ${brand.marketedBy}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        <div>
    	<c:if test="${empty brand.updatedBy}">
    	<b>Updated by</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty  brand.updatedBy}">
    	<b>Updated by</b><span> ${brand.updatedBy}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        <div>
    	<c:if test="${empty brand.updateDateStr}">
    	<b>Updated date</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty  brand.updateDateStr}">
    	<b>Updated date</b><span> ${brand.updateDateStr}</span>
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
