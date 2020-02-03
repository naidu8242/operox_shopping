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
      <a href="receivedItemDashboard"><i class="fa fa-arrow-left"></i>Back to Received Stock</a>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Received Stock</div>
  </section>
  
<!-- Content Area inner -->
<div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
    
    	<div>
        <b>Received Stock id</b>
        		<span>
        			<c:choose>
	        				<c:when test="${receivedStock.receivedNumber != null && receivedStock.receivedNumber != ''}">
							   ${receivedStock.receivedNumber}
							</c:when>
							<c:otherwise>
							   ---
							</c:otherwise>
					</c:choose>
        		</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Received date</b><span>${receivedStock.deliveryDateStr}</span>
        <div class="clearfix"></div>
        </div>               
        
    	<div>
        <b>Location</b><span>
        				<c:choose>
	        				<c:when test="${receivedStock.storeId.storeName != null && receivedStock.storeId.storeName != ''}">
							   ${receivedStock.storeId.storeName}
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
		        				<c:when test="${receivedStock.supplier.name != null && receivedStock.supplier.name != ''}">
								   ${receivedStock.supplier.name}
								</c:when>
								<c:otherwise>
								   ---
								</c:otherwise>
							</c:choose>
						</span>
        <div class="clearfix"></div>
        </div>
        
    	<%-- <div>
        <b>Supplier invoice no</b><span>${receivedStock.supplierInvoiceNumber}</span>
        <div class="clearfix"></div>
        </div> --%>
        
    	<div>
    	<c:if test="${receivedStock.supplier.name}">
        <b>Warehouse</b><span>
        					<c:choose>
		        				<c:when test="${receivedStock.warehouse.storeName != null && receivedStock.warehouse.storeName != ''}">
								   ${receivedStock.supplier.name}
								</c:when>
								<c:otherwise>
								   ---
								</c:otherwise>
							</c:choose>
						</span>
        <div class="clearfix"></div>
        </c:if>
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
                <c:forEach var="rsiList" items="${receivedStockItems}" varStatus="loop">
                	<tr>
                		<td><div class="tbldata-dotdot-vsm">
                				<c:choose>
			        				<c:when test="${rsiList.id != null && rsiList.id != ''}">
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
			        				<c:when test="${rsiList.barCode != null && rsiList.barCode != ''}">
									   ${rsiList.barCode}
									</c:when>
									<c:otherwise>
									   ---
									</c:otherwise>
								</c:choose>
							</div>
						</td>
	                    <td><div class="tbldata-dotdot-me">
	                    		<c:choose>
			        				<c:when test="${rsiList.product.productName != null && rsiList.product.productName != ''}">
									   ${rsiList.product.productName}
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
				        				<c:when test="${rsiList.quantity != null && rsiList.quantity != ''}">
										   ${rsiList.quantity}
										</c:when>
										<c:otherwise>
										   ---
										</c:otherwise>
									</c:choose>
								</p>
	                    	</div>
	                    </td>
	                    <td><div class="tbldata-dotdot-me">
	                    		<c:choose>
			        				<c:when test="${rsiList.mrp != null && rsiList.mrp != ''}">
									   ${rsiList.mrp}
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
        
		<nav class="hidden">
			<ul id="paging" class="pagination">
		    	<li><a href="#">Previous</a></li>
		        <li><a href="#">2</a></li>
		        <li><a href="#">3</a></li>
		        <li><a href="#">4</a></li>
		        <li><a href="#">5</a></li>
		        <li class="disabled"><a href="#">6</a></li>
		        <li data-page="last"><a href="#">Next</a></li>
			</ul>
		    <div class="clearfix"></div>
		</nav>        
</div>
    
    
<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
        
    	<div>
        <b>No of Products</b><span>
        						<c:choose>
			        				<c:when test="${receivedStock.numberOfPackages != null && receivedStock.numberOfPackages != ''}">
									   ${receivedStock.numberOfPackages}
									</c:when>
									<c:otherwise>
									   ---
									</c:otherwise>
								</c:choose>
							</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Amount</b><span>
						<c:choose>
	        				<c:when test="${receivedStock.amount != null && receivedStock.amount != ''}">
							   ${receivedStock.amount}
							</c:when>
							<c:otherwise>
							   ---
							</c:otherwise>
						</c:choose>
					</span>
        <div class="clearfix"></div>
        </div>               
        
    	<div>
        <b>Tax</b>	<span>
        				<c:choose>
	        				<c:when test="${receivedStock.tax.taxValue != null && receivedStock.tax.taxValue != ''}">
							   ${receivedStock.tax.taxValue}
							</c:when>
							<c:otherwise>
							   ---
							</c:otherwise>
						</c:choose>
					</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Discount</b><span>
        				<c:choose>
	        				<c:when test="${receivedStock.discount != null && receivedStock.discount != ''}">
							    ${receivedStock.discount}
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
		        				<c:when test="${receivedStock.totalAmount != null && receivedStock.totalAmount != ''}">
								    ${receivedStock.totalAmount}
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
			        				<c:when test="${receivedStock.receivingUsers.firstName != null && receivedStock.receivingUsers.firstName != ''}">
									    ${receivedStock.receivingUsers.firstName}
									    <c:if test="${receivedStock.receivingUsers.lastName != null && receivedStock.receivingUsers.lastName != ''}">
										   &nbsp;${receivedStock.receivingUsers.lastName}
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
        <b>Comments</b>	<span>
        					<c:choose>
		        				<c:when test="${receivedStock.comment != null && receivedStock.comment != ''}">
								    ${receivedStock.comment}
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