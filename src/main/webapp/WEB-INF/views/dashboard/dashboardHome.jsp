<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Dashboard</h2>
    </div>
    <div class="pull-right brud-crum"></div>
  </section>
  
<!-- Content Area inner -->
<div class="content-area clearfix">
<div class="bill_short dashboard-short clearfix">
  <h4><a href="<c:url value='/analyticsReports/daily'/>" title="Click here"><strong>Reports</strong></a></h4>
  <ul>
  <li>
    <a href="<c:url value='/analyticsReports/daily'/>" title="Daily Sales">
        <p class="fa fa-area-chart"></p>
        <p>Daily Sales</p>
   </a>
  </li>
  <li>
  <a href="<c:url value='/analyticsReports/counter'/>" title="Counter Sales">
   <p class="fa fa-line-chart"></p>
   <p>Counter Sales</p>
   </a>
  </li>
  <li>
  <a href="<c:url value='/analyticsReports/user'/>" title="User Sales">
   <p class="fa fa-area-chart"></p>
   <p>User Sales</p>
   </a>
  </li>
  <li>
  <a href="<c:url value='/analyticsReports/shift'/>" title="Shift Sales">
   <p class="fa fa-bar-chart-o"></p>
   <p>Shift Sales</p>
   </a>
  </li>
  <li>
  <a href="<c:url value='/analyticsReports/store'/>" title="Store Sales">
   <p class="fa fa-pie-chart"></p>
   <p>Location Sales</p>
   </a>
  </li>
  <li>
  <a href="<c:url value='/productReports'/>" title="Product Reports">
   <p class="fa fa-signal"></p>
   <p>Product Reports</p>
  </a>
  </li>
   </ul>
</div>


  
  
     <div class="div100 clearfix">
      <img src="${operoxUrl}/resources/images/bar-chart.jpg" class="img-responsive" alt="barchart">
     </div>
</div>
</section>
<!-- Content Area Ends--> 


<jsp:include page="../masterFooter.jsp" />
