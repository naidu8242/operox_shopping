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
      <a href="returnStockDashboard"><i class="fa fa-arrow-left"></i>Back to Return Stock</a>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Return Stock</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

	<div class="view_wrap">
	    <div class="view_wrap_details view_wrap_details_a">
	        
	    	<div>
	        <b>Return Stock id</b><span>
					        		<c:choose>
					        				<c:when test="${stockReturns.id != null && stockReturns.id != ''}">
											   ${stockReturns.id}
											</c:when>
											<c:otherwise>
											   ---
											</c:otherwise>
									</c:choose>
								</span>
	        <div class="clearfix"></div>
	        </div>
	        
	    	<div>
	        <b>Return date </b><span>
					        		<c:choose>
					        				<c:when test="${stockReturns.id != null && stockReturns.id != ''}">
											   ${stockReturns.receivedDateStr}
											</c:when>
											<c:otherwise>
											   ---
											</c:otherwise>
									</c:choose>
							</span>
	        <div class="clearfix"></div>
	        </div>               
	        
	    	<div>
	        <b>To store</b><span>
				        		<c:choose>
				        				<c:when test="${stockReturns.storeReturnTo.storeName != null && stockReturns.storeReturnTo.storeName != ''}">
										   ${stockReturns.storeReturnTo.storeName}
										</c:when>
										<c:otherwise>
										   ---
										</c:otherwise>
								</c:choose>
							</span>
	        <div class="clearfix"></div>
	        </div>
	        
	    	<div>
	        <b>From store</b><span>
				        		<c:choose>
				        				<c:when test="${stockReturns.storeReturnFrom.storeName != null && stockReturns.storeReturnFrom.storeName != ''}">
										   ${stockReturns.storeReturnFrom.storeName}
										</c:when>
										<c:otherwise>
										   ---
										</c:otherwise>
								</c:choose>
							</span>
	        <div class="clearfix"></div>
	        </div>         
	        
	    	<div>
	        <b>Supplier</b><span>
				        		<c:choose>
				        				<c:when test="${stockReturns.supplier.name != null && stockReturns.supplier.name != ''}">
										   ${stockReturns.supplier.name}
										</c:when>
										<c:otherwise>
										   ---
										</c:otherwise>
								</c:choose>
							</span>
	        <div class="clearfix"></div>
	        </div>
	        
	    	<div>
	        <b>Supplier invoice no</b><span>----</span>
	        <div class="clearfix"></div>
	        </div>                                                                                                    
	        
	    </div>
	    <div class="clearfix"></div>
	</div>	
	
	<div class="data_table">
	    	<table>
	        	<tbody>
	            	<tr>
	                	<th>S.No</th>
	                    <th>Barcode</th>
	                    <th>Product Name</th>
	                    <th>Qty/Weight</th>
	                    <th>Amount</th>
	                </tr>
	                <c:forEach var="sriList" items="${stockReturnsItems}" varStatus="loop">
	                	<tr>
		                    <td><div class="tbldata-dotdot-vsm" >
		                    		<c:choose>
				        				<c:when test="${sriList.id != null && sriList.id != ''}">
										  ${loop.count}
										</c:when>
										<c:otherwise>
										   ---
										</c:otherwise>
									</c:choose>
								</div>
							</td>
		                    <td><div class="tbldata-dotdot-vsm">
		                    		<c:choose>
				        				<c:when test="${sriList.barCode != null && sriList.barCode != ''}">
										   ${sriList.barCode}
										</c:when>
										<c:otherwise>
										   ---
										</c:otherwise>
									</c:choose>
								</div>
							</td>
		                    <td><div class="tbldata-dotdot-me">
		                    		<c:choose>
				        				<c:when test="${sriList.product.productName != null && sriList.product.productName != ''}">
										   ${sriList.product.productName}
										</c:when>
										<c:otherwise>
										   ---
										</c:otherwise>
									</c:choose>
								</div>
							</td>
		                    <td><div class="tbldata-dotdot-vsm">
			                    	<input type="hidden" name="hiddenField">
			                    	<p id="field1">
			                    		<c:choose>
					        				<c:when test="${sriList.quantity != null && sriList.quantity != ''}">
											   ${sriList.quantity}
											</c:when>
											<c:otherwise>
											   ---
											</c:otherwise>
										</c:choose>
									</p>
		                    	</div>
		                    </td>
		                    <td><div class="tbldata-dotdot-vsm">
		                    			<c:choose>
					        				<c:when test="${sriList.amount != null && sriList.amount != ''}">
											   ${sriList.amount}
											</c:when>
											<c:otherwise>
											   ---
											</c:otherwise>
										</c:choose>
								</div>
							</td>
		                </tr>
	                </c:forEach>
	            </tbody>
	        </table>       
	        
	  </div>
    
	  <div class="view_wrap">
	    <div class="view_wrap_details view_wrap_details_a">
	        
	    	<div>
	        <b>No of products</b><span>
	        						<c:choose>
				        				<c:when test="${stockReturns.totalNumberOfProducts != null && stockReturns.totalNumberOfProducts != ''}">
										   ${stockReturns.totalNumberOfProducts}
										</c:when>
										<c:otherwise>
										   ---
										</c:otherwise>
									</c:choose>
								</span>
	        <div class="clearfix"></div>
	        </div>
	        
	        
	    	<div>
	        <b>Total amount</b><span>
	       						<c:choose>
			        				<c:when test="${stockReturns.totalAmount != null && stockReturns.totalAmount != ''}">
									   ${stockReturns.totalAmount}
									</c:when>
									<c:otherwise>
									   ---
									</c:otherwise>
								</c:choose>
							</span>
	        <div class="clearfix"></div>
	        </div>
	        
	    	<div>
	        <b>Received user</b><span>
	       						<c:choose>
			        				<c:when test="${stockReturns.stockReturnBy.firstName != null && stockReturns.stockReturnBy.firstName != ''}">
									   ${stockReturns.stockReturnBy.firstName}
									   <c:if test="${stockReturns.stockReturnBy.lastName != null && stockReturns.stockReturnBy.lastName != ''}">
										   &nbsp;${stockReturns.stockReturnBy.lastName}
										</c:if>
									</c:when>
									<c:otherwise>
									   ---
									</c:otherwise>
								</c:choose>
							</span>
	        <div class="clearfix"></div>
	        </div>  
	
	    	<div>
	        <b>Comments</b><span>
	       						<c:choose>
			        				<c:when test="${stockReturns.comment != null && stockReturns.comment != ''}">
									   ${stockReturns.comment}
									</c:when>
									<c:otherwise>
									   ---
									</c:otherwise>
								</c:choose>
							</span>
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