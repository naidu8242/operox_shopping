<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <a href="${operoxUrl}/offers"><i class="fa fa-arrow-left"></i>Back to Offers</a>
    </div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
	<div class="view_wrap_img"><img src="${operoxUrl}/resources/images/offers-image.jpg"></div>
    <div class="view_wrap_details">
    
    	<div>
        <b>Offer name :</b><span>${offer.offerName}</span>
        <div class="clearfix"></div>
        </div>
        
        <div>
    	<c:if test="${empty offer.offerStartDate}">
    	<b>Start Date : </b><span> NO DATA </span>
    	</c:if>
    	<c:if test="${!empty offer.offerStartDate}">
    	<b>Start Date : </b><fmt:formatDate type="date" value="${offer.offerStartDate}" />
    	</c:if>
        
        <div class="clearfix"></div>
        </div>
        
        <div>
    	<c:if test="${empty offer.offerEndDate}">
    	<b>End Date :</b><span> NO DATA </span>
    	</c:if>
    	<c:if test="${!empty offer.offerEndDate}">
    	<b>End Date : </b><fmt:formatDate type="date" value="${offer.offerEndDate}" />
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        
        
        <div>
        <b>Offer Days :</b>
        <span>
        <c:if test="${offer.sunday == 'Y'}">
        	 Sunday<br/>
        </c:if>
        
        <c:if test="${offer.monday == 'Y'}">
        	 Monday<br/>
        </c:if>
        
        <c:if test="${offer.tuesday == 'Y'}">
        	 Tuesday<br/>
        </c:if>
        
        <c:if test="${offer.wednesday == 'Y'}">
        	 Wednesday<br/>
        </c:if>
        
        <c:if test="${offer.thursday == 'Y'}">
        	 Thursday<br/>
        </c:if>
        
        <c:if test="${offer.friday == 'Y'}">
        	 Friday<br/>
        </c:if>
        
        <c:if test="${offer.saturday == 'Y'}">
        	Saturday
        </c:if>
       </span>
       
        <div class="clearfix"></div>
        </div>
        
        
        <div>
    	<c:if test="${empty offer.offerStartTime}">
    	<b>Start Time :</b><span> NO DATA </span>
    	</c:if>
    	<c:if test="${!empty offer.offerStartTime}">
    	<b>Start Time :</b><span> ${offer.offerStartTime}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        
        <div>
    	<c:if test="${empty offer.offerEndTime}">
    	<b>End Time :</b><span> NO DATA </span>
    	</c:if>
    	<c:if test="${!empty offer.offerEndTime}">
    	<b>End Time :</b><span> ${offer.offerEndTime}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        <div>
        <b>Offer Type :</b><span>${offer.offerType}</span>
        <div class="clearfix"></div>
        </div>
        
        <c:if test="${offer.offerType == 'Buy-X Get-Y'}">
        
        
	        <div>
	        <b>Product :</b><span>${offerBuyxGetY.buyItem}</span>
	        <div class="clearfix"></div>
	        </div>
	        
	        <div>
	        <b>Product Quantity :</b><span>${offerBuyxGetY.buyItemQuantity}</span>
	        <div class="clearfix"></div>
	        </div>
	        
	        <div>
	        <b>Free Product :</b><span>${offerBuyxGetY.freeItem}</span>
	        <div class="clearfix"></div>
	        </div>
	        
	        <div>
	        <b>Free Product Quantity :</b><span>${offerBuyxGetY.freeItemQuantity}</span>
	        <div class="clearfix"></div>
	        </div>
	        
        </c:if>
        
        
        <c:if test="${offer.offerType == 'Discount on Invoice'}">
        
	        <div>
	        <b>Invoice Amount :</b><span>${offerDiscountOnInvoice.invoiceAmount}</span>
	        <div class="clearfix"></div>
	        </div>
        
	        <div>
	        <b>Discount Amount :</b><span>${offerDiscountOnInvoice.discountAmount}</span>
	        <div class="clearfix"></div>
	        </div>
	        
	        <div>
	        <b>Is Percentage? :</b><span>${offerDiscountOnInvoice.isPercentage}</span>
	        <div class="clearfix"></div>
	        </div>
	        
	        
        </c:if>
        
        <c:if test="${offer.offerType == 'Coupons'}">
        
	        <div>
	        <b>Coupon Name :</b><span>${offerCoupons.couponName}</span>
	        <div class="clearfix"></div>
	        </div>
        
	        <div>
	        <b>Invoice Amount :</b><span>${offerCoupons.invoiceAmount}</span>
	        <div class="clearfix"></div>
	        </div>
	        
	        <div>
	        <b>Coupon Value :</b><span>${offerCoupons.couponValue}</span>
	        <div class="clearfix"></div>
	        </div>
	        
	        
        </c:if>
        
        <c:if test="${offer.offerType == 'Free Item on Invoice'}">
        
	        <div>
	        <b>Invoice Amount :</b><span>${offerFreeItemOnInvoice.totalInvoiceAmount}</span>
	        <div class="clearfix"></div>
	        </div>
        
	        <div>
	        <b>Free Item :</b><span>${offerFreeItemOnInvoice.freeItem}</span>
	        <div class="clearfix"></div>
	        </div>
	        
	        <div>
	        <b>Free Item Quantity :</b><span>${offerFreeItemOnInvoice.freeItemQuantity}</span>
	        <div class="clearfix"></div>
	        </div>
	        
	        
        </c:if>
        
        <c:if test="${offer.offerType == 'Loyalty points'}">
        
	        <div>
	        <b>Invoice Amount :</b><span>${offerLoyaltyPoints.invoiceAmount}</span>
	        <div class="clearfix"></div>
	        </div>
        
	        <div>
	        <b>Loyalty Points :</b><span>${offerLoyaltyPoints.loyalityPoints}</span>
	        <div class="clearfix"></div>
	        </div>
	        
	        <div>
	        <b>Loyalty Point Value :</b><span>${offerLoyaltyPoints.loyalityPointValue}</span>
	        <div class="clearfix"></div>
	        </div>
	        
	        
        </c:if>
        
        
   		<div>
        <b>Stores :</b>
         <span>
        	<c:forEach var="offerStores" items="${offerStroesList}">
              ${offerStores.store.storeName} <br/>
           </c:forEach>
           </span>
        <div class="clearfix"></div>
        </div>
        
       	<%-- <div>
        <b>Customer Level :</b>
        <span>
        
        <c:if test="${offerCustomerLevel.retailCustomer == 'Y'}">
            Retail Customer<br/>
        </c:if>
         <c:if test="${offerCustomerLevel.wholeSaleCustomer == 'Y'}">
            Wholesale Customer
        </c:if>
        
        </span>
        <div class="clearfix"></div>
        </div> --%>
        
        
                                                                           
        
    </div>
    <div class="clearfix"></div>
</div>	  	
  
  </div>
  
</section>
<!-- Content Area Ends--> 


<jsp:include page="../masterFooter.jsp" />