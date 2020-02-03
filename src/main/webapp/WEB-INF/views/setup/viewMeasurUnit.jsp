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
      <a href="${operoxUrl}/measuringUnit"><i class="fa fa-arrow-left"></i>Back to Measuringunit unit</a>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Measuring Unit</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
        <div>
        <b>Measuringunit Unit</b><span>${measuringUnit.measuringUnit}</span>
        <div class="clearfix"></div>
        </div>     
        
    <!-- 	<div>
        <b>Descrption</b><span>Descrption Descrption</span>
        <div class="clearfix"></div>
        </div> -->
        
         <div>
    	<c:if test="${empty measuringUnit.description}">
    	<b>Descrption</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty measuringUnit.description}">
    	<b>Search code</b><span> ${measuringUnit.description}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
    	<!-- <div>
        <b>Updated by</b><span>Vannam</span>
        <div class="clearfix"></div>
        </div>  -->
        
         <div>
    	<c:if test="${empty measuringUnit.updatedBy}">
    	<b>Updated by</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty measuringUnit.updatedBy}">
    	<b>Updated by</b><span> ${measuringUnit.updatedBy}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
    <!-- 	<div>
        <b>Updated date</b><span>25/09/2016</span>
        <div class="clearfix"></div>
        </div>   --> 
        
        <div>
    	<c:if test="${empty measuringUnit.dateStr}">
    	<b>Updated date</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty measuringUnit.dateStr}">
    	<b>Updated date</b><span> ${measuringUnit.dateStr}</span>
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
