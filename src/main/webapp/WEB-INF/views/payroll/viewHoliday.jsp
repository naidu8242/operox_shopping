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
      <a href="${operoxUrl}/holiday"><i class="fa fa-arrow-left"></i>Back to Holiday</a>
    </div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
    	<div>
        <b>Holiday name</b><span> ${holiday.holidayName}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
    	<c:if test="${empty holiday.year}">
    	<b>Year</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty holiday.year}">
    	<b>Year</b><span> ${holiday.year}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>        
        
    	<div>
        <b>Holiday Date</b><span> ${holiday.holidayDateStr}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Description</b><span> ${holiday.description}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Updated by</b><span> ${holiday.updatedBy}</span>
        <div class="clearfix"></div>
        </div> 
        
    	<div>
        <b>Updated date</b><span> ${holiday.updatedDateStr}</span>
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
