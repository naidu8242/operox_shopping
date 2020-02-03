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
      <a href="purchaseorderDashboard"><i class="fa fa-arrow-left"></i>Back to Purchase Order</a>
    </div>
    <div class="pull-right brud-crum"><a href="dashboard">Home</a> &raquo; Purchase order</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
    	<div>
        <b>Purchase id</b><span> ${stats[i.index].No} ${purchaseOrder.purchaseNumber}</span>
        <div class="clearfix"></div>
        </div>        
        
    	<div>
        <b>Order date</b><span>${purchaseOrder.orderDateStr}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Delivery date</b><span>${purchaseOrder.deliveryDateStr}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Supplier</b><span>${purchaseOrder.supplierName}</span>
        <div class="clearfix"></div>
        </div>

    	<div>
        <b>Email id</b><span>${purchaseOrder.supplier.supplierContactEmail}</span>
        <div class="clearfix"></div>
        </div>
                
        
    	<div>
        <b>Location</b><span>${purchaseOrder.storeId.storeName}</span>
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
        <b>Updated date</b><span>${purchaseOrder.updatedDateStr}</span>
        <div class="clearfix"></div>
        </div>                                                                                                                           
        
    </div>
    <div class="clearfix"></div>
</div>	  	
  
<div class="clearfix">
    <div class="data_table">
    	<table>
        	<tbody>
            	<tr>
                	<th>Sl.No</th>
                    <th>Prodcut Name</th>
                	<th>Qty / Weight</th>
                </tr>
                <c:forEach var="poiList" items="${purchaseOrderItems}" varStatus="loop">
                	<tr>
	                    <td><div class="tbldata-dotdot-vsm"> ${loop.count}</div></td>
	                    <td><div class="tbldata-dotdot-me">${poiList.product.productName}</div></td>
	                    <td><div class="tbldata-dotdot-vsm">${poiList.quantity}</div></td>
	                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div> 
  </div>  
</div>
  
</section>
<!-- Content Area Ends--> 

<jsp:include page="../masterFooter.jsp" /> 
 

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