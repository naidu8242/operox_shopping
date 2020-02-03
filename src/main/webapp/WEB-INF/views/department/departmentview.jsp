<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <a href="${operoxUrl}/departmentList"><i class="fa fa-arrow-left"></i>Back to Department</a>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; View Department</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
    	<div>
        <b>Department ID:</b>
           <span>
             <c:if test="${!empty department.departmentId}">
                 ${department.departmentId}
              </c:if>
              <c:if test="${empty department.departmentId}">
                 ---
              </c:if>
           </span>
        
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Department Name:</b> <span>${department.departmentName}</span>
        <div class="clearfix"></div>
        </div>        
        
    	<div>
        <b>Department Description:</b>
           <span>
             <c:if test="${!empty department.description}">
                 ${department.description}
              </c:if>
              <c:if test="${empty department.description}">
                 ---
              </c:if>
           </span>
        
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Updated By:</b>  <span>${department.updatedBy}</span>
        <div class="clearfix"></div>
        </div> 
        
        <div>
        <b>Updated Date:</b><span><fmt:formatDate pattern="yyyy/MM/dd" value="${department.updatedDate}"/></span>
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

