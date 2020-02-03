<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- <script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <a href="javascript:location.href = '<c:url value='/shift'/>'"><i class="fa fa-arrow-left"></i>Back to Shifts</a>
    </div>
    <div class="pull-right brud-crum"><a href="javascript:location.href = '<c:url value='/shift'/>'">Shift List</a> &raquo; View Shift</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
    	<div>
        <b>Shift name</b><span>
        ${shift.shiftName}
        </span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Time in</b><span>${shift.intime}</span>
        <div class="clearfix"></div>
        </div>        
        
    	<div>
        <b>Time out</b><span>${shift.outtime}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Shift description</b><span>${shift.description}</span>
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