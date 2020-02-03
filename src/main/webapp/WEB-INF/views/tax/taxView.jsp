<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <a href="${operoxUrl}/tax"><i class="fa fa-arrow-left"></i>Back to Stores</a>
    </div>
    <div class="pull-right brud-crum">Home &raquo; View Store</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
    	<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
    	<div>
        <b>Tax id</b><span>${tax.id}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Tax name</b><span>${tax.taxName}</span>
        <div class="clearfix"></div>
        </div>        
        
    	<div>
        <b>Category</b><span>${tax.categoryName}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Value</b><span>${tax.taxValue}</span>
        <div class="clearfix"></div>
        </div>        
        
    	<div>
        <b>Updated by</b><span>${tax.updatedBy}</span>
        <div class="clearfix"></div>
        </div> 
        
    	<div>
        <b>Updated date</b><span>${tax.taxName}</span>
        <div class="clearfix"></div>
        </div>                                                                                              
        
    </div>
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

