<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>


<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <a href="openingStockDashboard"><i class="fa fa-arrow-left"></i>Back to Opening Stock</a>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Opening Stock</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

	<div class="view_wrap">
	    <div class="view_wrap_details view_wrap_details_a">
	        
	    	<div>
	        <b>Opening Stock id</b><span>${productStock.id}</span>
	        <div class="clearfix"></div>
	        </div>
	        
	    	<div>
	        <b>Received date</b><span>--</span>
	        <div class="clearfix"></div>
	        </div>
	        
	    	<div>
	        <b>Arrival date</b><span>--</span>
	        <div class="clearfix"></div>
	        </div>                
	        
	    	<div>
	        <b>From store</b><span>---</span>
	        <div class="clearfix"></div>
	        </div>
	        
	    	<div>
	        <b>To store</b><span>---</span>
	        <div class="clearfix"></div>
	        </div>
	        
	    	<div>
	        <b>No of products</b><span>---</span>
	        <div class="clearfix"></div>
	        </div>
	        
	    	<div>
	        <b>Updated by</b><span>${updatedUser.firstName} 
	        <c:if test="${not empty var1}">
			    ${updatedUser.lastName}
			</c:if></span>
	        <div class="clearfix"></div>
	        </div> 
	        
	    	<div>
	        <b>Updated date</b><span>--</span>
	        <div class="clearfix"></div>
	        </div>                                                                                       
	        
	    </div>
	    <div class="clearfix"></div>
	</div>	
	</div>
	  
	</section>
	<!-- Content Area Ends-->

<jsp:include page="../masterFooter.jsp" /> 
</div>

<script type="text/javascript">
	(function($) {
		$.fn.serializeFormJSON = function() {

			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		};
	})(jQuery);
</script>


<script type="text/javascript">
			var config = {
			  '.chosen-select'           : {},
			  '.chosen-select-deselect'  : {allow_single_deselect:true},
			  '.chosen-select-no-single' : {disable_search_threshold:10},
			  '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
			  '.chosen-select-width'     : {width:"95%"}
			}
			for (var selector in config) {
			  $(selector).chosen(config[selector]);
			}
			$('.chosen-select').each(function() {
				var $this = $(this);
				$this.next().css({'width': '100%'});

});
</script>

</body>
</html>