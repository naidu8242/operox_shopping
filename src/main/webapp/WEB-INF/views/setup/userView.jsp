<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
	function predefineProfileImage(imageId){
		$(imageId).attr('src','${operoxUrl}/resources/images/userprofileImg.jpg');
	}
</script>


<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <a href="javascript:location.href = '<c:url value='/users'/>'"><i class="fa fa-arrow-left"></i>Back to users</a>
    </div>
    <div class="pull-right brud-crum"><a href="javascript:location.href = '<c:url value='/users'/>'">Users List</a> &raquo;  View user</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
	<div class="view_wrap_img"><img id="userImage" onerror="predefineProfileImage(this);"  src="<c:url value='/displayUserImage/${user.id}'/>" alt="productimg" id="userprofileImg"></div>
    <div class="view_wrap_details">
    
    	<div>
        <b>Employee Id</b><span>
        <c:if test="${!empty user.employeeId}">
         ${user.employeeId}
        </c:if>
        <c:if test="${empty user.employeeId}">
         NO DATA
        </c:if>
        </span>
        <div class="clearfix"></div>
        </div>
        
        
        
    	<div>
        <b>Name</b><span>
        <c:if test="${!empty user.id}">
        ${user.firstName} 
        <c:if test="${!empty user.lastName}">
        ${user.lastName}
        </c:if>
        <c:if test="${!empty user.lastName}">
        
        </c:if>
        </c:if>
        </span>
        <div class="clearfix"></div>
        </div>
        
        
        
    	<div>
        <b>Email</b><span>${user.email}</span>
        <div class="clearfix"></div>
        </div>
        
        <div>
        <b>Gender</b><span>${user.gender}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Phone</b><span>
        <c:if test="${empty user.phone}">
       NO DATA
        </c:if>
         <c:if test="${!empty user.phone}">
        ${user.phone}
        </c:if>
        </span>
        <div class="clearfix"></div>
        </div>
        
        
    	<div>
        <b>Address</b><span>
        <c:if test="${empty user.address.address}">
         NO DATA
        </c:if>
        <c:if test="${not empty user.address.address}">
         ${user.address.address}
        </c:if>
        </span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Location</b><span>
        <c:if test="${!empty user.address.country}">
        ${user.address.country} 
        </c:if>
        <c:if test="${!empty user.address.state}">
        ${user.address.state} 
        </c:if>
        <c:if test="${!empty user.address.city}">
        ${user.address.city} 
        </c:if>
        <c:if test="${empty user.address.country && empty user.address.state &&  empty user.address.city}">
         NO DATA
        </c:if> 
        </span>
        <div class="clearfix"></div>
        </div>
        
        
        <div>
        <b>Zipcode</b><span>
        <c:if test="${!empty user.address.zipcode}">
        ${user.address.zipcode}
        </c:if>
        <c:if test="${empty user.address.zipcode}">
        NO DATA
        </c:if>
        </span>
        <div class="clearfix"></div>
        </div>
        
        
    	<div>
        <b>Department</b><span>
        <c:if test="${!empty user.department}">
        ${user.department.departmentName}
        </c:if>
        <c:if test="${empty user.department}">
        NO DATA
        </c:if>
        </span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Date of Join</b><span>
        <c:if test="${!empty user.doj}">
        <fmt:formatDate type="date" value="${user.doj}" />
        </c:if>
        <c:if test="${empty user.doj}">
        NO DATA
        </c:if>
        </span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Role</b><span>
        <c:if test="${!empty user.role}">
        ${user.role.displayName}
        </c:if>
        <c:if test="${empty user.shift}">
        NO DATA
        </c:if>
        </span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Shift</b><span>
        <c:if test="${!empty user.shift}">
        ${user.shift.shiftName}
        </c:if>
        <c:if test="${empty user.shift}">
        NO DATA
        </c:if>
        </span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Compensation</b><span>
        <c:if test="${!empty user.compensation}">
        ${user.compensation} / ${user.compensatationType}
        </c:if>
        <c:if test="${empty user.compensation}">
        NO DATA
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

</div>

<jsp:include page="../masterFooter.jsp" />

<!--  Error Message  -->
<div class="mask"></div>
<div class="delete_pop">
		<p>Delete conformation message....</p>
		<a class="close_color" href="javascript:void(0)">Yes</a><a href="javascript:void(0)">No</a> 
	</div>
<!--  Error Message  -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
<script src="js/jquery-1.11.3.min.js"></script> 
<script src="js/jquery-1.11.3.min.js"></script> 
<script src="js/moment.min.js"></script> 
<script src="js/bootstrap-datetimepicker.min.js"></script> 
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/masterHeaderAction.js"></script> 
<script>
// Error pop
$(document).ready(function() {
    $(".glyphicon-trash").click(function(){
		$(".delete_pop").show();
		$(".mask").fadeIn(200);
	});
	$(".delete_pop a").click(function(){
		$(".delete_pop").hide();
		$(".mask").fadeOut(200);
	});
});
</script>

</body>
</html>