<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <a href="${operoxUrl}/ticketHome"><i class="fa fa-arrow-left"></i>Back to Ticket List</a>
    </div>
    <div class="pull-right brud-crum">Home &raquo; View Ticket</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
    	<div>
        <b>Subject</b><span>${ticket.subject}</span>
        <div class="clearfix"></div>
        </div>        
        
     	<div>
        <b>Source type</b>
           <span>
              <c:if test="${!empty ticket.sourceType}">
                 ${ticket.sourceType}
              </c:if>
               <c:if test="${empty ticket.sourceType}">
                 ---
              </c:if>
            </span>  
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Store</b><span>
        <c:forEach var="option" items="${storeList}">
			${option.storeName}
		</c:forEach>
		</span>
        <div class="clearfix"></div>
        </div>
        
        <div>
        <b>Assigned To</b><span>
        <c:forEach var="option" items="${userList}">
        <c:if test="${option.id  == ticket.user.id}">
        ${option.firstName}${option.lastName}
		</c:if>
		</c:forEach>
		</span>
        <div class="clearfix"></div>
        </div>
        
        
       <div>
        <b>CustomerName</b><span>
         <c:forEach var="option" items="${customersList}">
			${option.customerName} 
		</c:forEach>
        </span>
        <div class="clearfix"></div>
        </div>
        
     	<div>
        <b>Status</b><span>${ticket.ticketStatus}</span>
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

