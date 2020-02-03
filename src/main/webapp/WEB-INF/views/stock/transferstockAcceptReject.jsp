<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>


<form id="transferStock_form" name="transferStock_form" method="post">
<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Accept or Reject Stock</h2>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Transfer Stock</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad" style="position:relative;">
<!-- Accept & Reject Button -->
<div class="clearfix btn-acceptreject">
 <div class="pull-right">
  <ul class="list-inline list-unstyled">
   <li><a href="receivedItem" class="btn btn-primary btn-sm"><i class="fa fa-check"></i>&nbsp;Accept Stock</a></li>
   <li><a href="javascript:void(0)" class="btn btn-danger btn-sm"><i class="fa fa-close"></i>&nbsp;Reject Stock</a></li>
  </ul>
 </div>
</div>
<!-- Accept & Reject Button -->

<div class="view_wrap">
    <div class="view_wrap_details view_wrap_details_a">
    	<div>
        <b>Transfer Stock id</b><span>${stockReturns.id}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Received date</b><span>${stockReturns.receivedDateStr}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>Arrival date</b><span>${stockReturns.transferDateStr}</span>
        <div class="clearfix"></div>
        </div>                
        
    	<div>
        <b>From store</b><span>${stockReturns.storeReturnFrom.storeName}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>To store</b><span>${stockReturns.storeReturnTo.storeName}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
        <b>No of products</b><span>${itemsCount}</span>
        <div class="clearfix"></div>
        </div>
        
    	<div>
	        <b>Updated by</b><span>${updatedUser.firstName} 
	        <c:if test="${not empty updatedUser.lastName}">
			    ${updatedUser.lastName}
			</c:if></span>
	        <div class="clearfix"></div>
	        </div> 
        
    	<div>
        <b>Updated date</b><span>${stockReturns.updatedDateStr}</span>
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
            	<c:forEach var="sriList" items="${stockReturnsItems}">
	                <tr>
	                    <td><div class="tbldata-dotdot-vsm">${sriList.id}</div></td>
	                    <td><div class="tbldata-dotdot-vsm">${sriList.barCode}</div></td>
	                    <td><div class="tbldata-dotdot-me">${sriList.product.productName}</div></td>
	                    <td><div class="tbldata-dotdot-vsm">
	                    <input type="hidden" name="hiddenField">
	                    <p id="field1">${sriList.quantity}</p>
	                    </div>
	                    </td>
	                    <td><div class="tbldata-dotdot-vsm">---</div></td>
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
  
  </div>
</section>
</form>
<!-- Content Area Ends-->

<jsp:include page="../masterFooter.jsp" /> 

<script type="text/javascript">
  $( function() {
    $( "#transferDate, #receivedDate" ).datepicker();
  });
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
 