<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>


<script type="text/javascript">
window.onload = function () {
	if('${offer.id}' != null && '${offer.id}' != '' && '${offer.id}' != 'undefined'){
		var offerType = $("#offerType").val('${offer.offerType}');
		getOfferType()
	}
 };
</script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Add Offer</h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/offers">Home</a> &raquo; Add Offer</div>
  </section>
  
  <!-- Content Area inner -->
  
  <form:form id="addOffer_form"  name="addOffer_form" method="post">
  <div class="content-area clearfix">
   
    <div class="div50">
       <label class="labl">Offer Name</label>
       <span>
       <input type="text" placeholder="Offer Name" value="${offer.offerName}" name="offerName" id="offerName" field-name="Offer Name" data-validation="required validate_Space validate_length length2-50">
       </span>
    </div>
    <input type="hidden" name="offerId" value="${offer.id}"/>
    
    <div class="div50">
       <label class="labl">Offer Type</label>
        <span>
          
          <c:if test="${empty offer.offerType}">
	          <select id="offerType" name="offerType" data-validation="required" field-name="Offer Type" onclick="getOfferType()">
		          <option value="">--Select Offer Type--</option>
		          <option value="Buy-X Get-Y">Buy-X || Get-Y</option>
		          <option value="Discount on Invoice">Discount on Invoice</option>
		          <!-- <option value="Discount on Items">Discount on Items</option> -->
		          <option value="Free Item on Invoice">Free Item on Invoice</option>
		          <option value="Coupons">Coupons</option>
		          <option value="Loyalty points">Loyalty points</option>
	         </select>
         </c:if>
         
         <c:if test="${!empty offer.offerType}">
         <input type="text" id="offerType" readonly="readonly" value="${offer.offerType}"  >
         </c:if>
       </span>
    </div>
    
    <div class="div50">
       <label class="labl">Start Date</label>
       <span>
       <input type="text" placeholder="MM/DD/YYYY" value="${offer.offerStartDateStr}" id="startDate" field-name="Start Date" data-validation="required" readonly="readonly" name="startDate" class="datefild" id="startDate">
       </span>
    </div>
    
    <div class="div50">
       <label class="labl">End Date</label>
       <span>
       <input type="text" placeholder="MM/DD/YYYY" value="${offer.offerEndDateStr}"  id="endDate" readonly="readonly"  name="endDate" class="datefild" id="endDate">
       </span>
    </div>            

<!-- Is Repeated ? -->
    <div class="isrequired">
       <div class="isrequired-inner clearfix">
       <ul class="mb10 days">
           <li><input  value="Y" id="sunday" name="sunday" ${offer.sunday == 'Y' ? 'checked' : ''} type="checkbox">Sunday</li>
           <li><input  value="Y" id="monday" name="monday" ${offer.monday == 'Y' ? 'checked' : ''} type="checkbox">Monday</li>
           <li><input  value="Y" id="tuesday" name="tuesday" ${offer.tuesday == 'Y' ? 'checked' : ''} type="checkbox">Tuesday</li>
           <li><input  value="Y" id="wednesday" name="wednesday" ${offer.wednesday == 'Y' ? 'checked' : ''} type="checkbox">Wednesday</li>
           <li><input  value="Y" id="thursday" name="thursday" ${offer.thursday == 'Y' ? 'checked' : ''} type="checkbox">Thursday</li>
           <li><input  value="Y" id="friday" name="friday" ${offer.friday == 'Y' ? 'checked' : ''} type="checkbox">Friday</li>
           <li><input  value="Y"id="saturday" name="saturday" ${offer.saturday == 'Y' ? 'checked' : ''} type="checkbox">Saturday</li>
       </ul>
       <p id="daysRadios" style="display: none;"><font color="red">Day is required</font></p>
                  <div class="div33">
                  <label class="labl">Start Time</label>
                  <span><input type="text" placeholder="Start Time" value="${offer.offerStartTime}" data-validation="required" field-name="Start Time" id="startTime" name="startTime" readonly="readonly" class="stdt timefild"></span>
                  </div>
        
                  <div class="div33">
                  <label class="labl">End Time</label>
                  <span><input type="text" placeholder="End Time"  value="${offer.offerEndTime}" data-validation="required" field-name="End Time" id="endTime" name="endTime" readonly="readonly" class="endt timefild"></span>
                  </div>
       </div>

       </div>
<!-- Is Repeated ? Ends -->


<!-- Offer type content -->

<!-- Buy one get one -->
<div class="clearfix offerDiv boneGoneItem" id="BuyXGetY" style="display: none;">

  <h3>Buy X Get Y</h3>
<input type="hidden" name="offerBuyxGetYId" value="${offerBuyxGetY.id}"/>
    <div class="div4">
        <label class="labl">Buy Item</label>
     <span>
         <c:if test="${empty offerBuyxGetY.buyItem}">
	        <select class="chosen-select" id="buyItem" name="buyItem" field-name="Buy Item" >
	         <option value="">--Select Product--</option>
	         <c:forEach var="product" items="${buyItemsProductsList}">
	               <option value="${product.id},${product.productName}" ${product.id  == offerBuyxGetY.buyItemProduct.id ? 'selected' : ''}>${product.productName}</option>
	         </c:forEach>
	         </select>
         </c:if>
         <c:if test="${!empty offerBuyxGetY.buyItem}">
         <input type="text" readonly="readonly" value="${offerBuyxGetY.buyItem}"  >
         </c:if>
	</span>
    </div>
    
    <div class="div4">
        <label class="labl">Quantity</label>
        <span><input type="text" name="buyItemQuantity" value="${offerBuyxGetY.buyItemQuantity}" field-name="Quantity" id="buyItemQuantity" placeholder="Quantity"></span>
    </div>
    
    <div class="div4">
     <label class="labl">Free Item</label>
    <span>
        <select class="chosen-select" id="freeItem" name="freeItem" field-name="Free Item" >
         <option value="">--Select Product--</option>
         <c:forEach var="product" items="${productsList}">
               <option value="${product.id},${product.productName}" ${product.id  == offerBuyxGetY.freeItemProduct.id ? 'selected' : ''}>${product.productName}</option>
         </c:forEach>
         </select>
	</span>
	</div>
	
    <div class="div4">
        <label class="labl">Quantity</label>
        <span><input type="text" name="freeItemQuantity" value="${offerBuyxGetY.freeItemQuantity}" id="freeItemQuantity" field-name="Quantity" placeholder="Quantity"></span>
    </div>
  <div class="clearfix"></div>
</div>
<!-- Buy one get one -->


<!-- Free Item invoice -->
<div class="clearfix offerDiv freeItem" id="FreeItemOnInvoice" style="display: none;">

  <h3>Free Item on Invoice</h3>
<input type="hidden" name="offerFreeItemOnInvoiceId" value="${offerFreeItemOnInvoice.id}"/>
    <div class="div4">
        <label class="labl">Invoice Total</label>
        <span><input type="text" id="totalFreeItemInvoice" value="${offerFreeItemOnInvoice.totalInvoiceAmount}" name="totalFreeItemInvoice" field-name="Invoice Total" placeholder="Invoice Total"></span>
    </div>
    
    <div class="div4">
        <label class="labl">Free Item</label>
        <span>
        <select class="chosen-select" id="freeItemforInvoice" name="freeItemOnInvoice" field-name="Free Item" >
         <option value="">--Select Product--</option>
         <c:forEach var="product" items="${productsList}">
               <option value="${product.id},${product.productName}" ${product.id  == offerFreeItemOnInvoice.freeItemProduct.id ? 'selected' : ''}>${product.productName}</option>
         </c:forEach>
         </select>
	  </span>
	 
    </div>
    <div class="div4">
        <label class="labl">Free Item Quantity</label>
        <span><input type="text" id="freeItemQuantityOnInvoice" value="${offerFreeItemOnInvoice.freeItemQuantity}" name="freeItemQuantityOnInvoice" field-name="Free Item Quantity" placeholder="Free Item Quantity"></span>
    </div>
  <div class="clearfix"></div>
</div>
<!-- Free Item invoice Ends -->

<!-- Discount on Item -->
<div class="clearfix offerDiv discountItem" id="DiscountOnInvoice" style="display: none;">
 <h3>Discount on Invoice</h3>
 <input type="hidden" name="offerDiscountOnInvoiceId" value="${offerDiscountOnInvoice.id}"/>
<div class="newrow clearfix">
    <div class="div4" id="discountOnInvoiceAmountDiv">
        <label class="labl">Invoice Amount</label>
        <span><input type="text" name="discountOnInvoiceAmount" value="${offerDiscountOnInvoice.invoiceAmount}" id="discountOnInvoiceAmount" onblur="validateInvoiceAmount()" field-name="Invoice Amount" placeholder="Invoice Amount"></span>
    </div>
    <div class="div4">
        <label class="labl">Discount Amount</label>
        <span><input type="text" name="discountOnInvoiceDiscountAmount" value="${offerDiscountOnInvoice.discountAmount}" id="discountOnInvoiceDiscountAmount" field-name="Discount Amount" placeholder="Discount Item"></span>
    </div>
        <div class="div4">
        <label class="labl">&nbsp;&nbsp;</label>
        <span class="checkBox"><input type="checkbox" value="Y" ${offerDiscountOnInvoice.isPercentage == 'Y' ? 'checked' : ''} id="discountOnInvoicePercentage" name="discountOnInvoicePercentage">Is Percentage ?</span>
    </div>
    </div>
  <div class="clearfix"></div>
</div>
<!-- Discount on Item Ends -->



<!-- Loyalty Points -->
<div class="clearfix offerDiv invoiceItem" id="LoyalityPoints" style="display: none;">
<p><strong>Loyalty Points</strong></p>
<input type="hidden" name="offerLoyaltyPointsId" value="${offerLoyaltyPoints.id}"/>
<div class="div33">
       <label class="labl">Invoice Amount</label>
       <span>
          <input type="text" id="loyaltyInvoiceAmount" value="${offerLoyaltyPoints.invoiceAmount}" name="loyaltyInvoiceAmount" field-name="Invoice Amount" placeholder="Invoice Amount">
       </span>
</div>

<div class="div33">
       <label class="labl">Loyalty Points</label>
       <span>
          <input type="text" id="invoiceLoyaltyPoints" value="${offerLoyaltyPoints.loyalityPoints}" name="invoiceLoyaltyPoints" field-name="Loyalty Points" placeholder="Loyalty Points">
       </span>
</div>
<div class="div33">
       <label class="labl">One Loyalty Point =</label>
       <span>
          <input type="text"  id="oneLoyaltyPoint" value="${offerLoyaltyPoints.loyalityPointValue}" name="oneLoyaltyPoint" field-name="Loyalty Point" placeholder="One Loyalty Point">
       </span>
</div>
  <div class="clearfix"></div>
</div>
<!-- Loyalty Points Ends -->


<!-- Coupon -->
<div class="clearfix offerDiv invoiceItem" id="CouponsName" style="display: none;">
<h3>Coupons</h3>
<input type="hidden" name="offerCouponsId" value="${offerCoupons.id}"/>
<div class="div33" id="couponNameDiv">
       <label class="labl">Coupon Name/Code</label>
       <c:if test="${empty offerCoupons.couponName}">
	       <span>
	          <input type="text" id="couponName" value="${offerCoupons.couponName}" name="couponName" field-name="Coupon Name" onblur="validateCouponName()" placeholder="Coupon Name">
	       </span>
       </c:if>
       <c:if test="${!empty offerCoupons.couponName}">
	       <span>
	          <input type="text" id="couponName" value="${offerCoupons.couponName}" readonly="readonly" field-name="Coupon Name"  placeholder="Coupon Name">
	       </span>
       </c:if>
       
</div>

<div class="div33">
       <label class="labl">Invoice Amount</label>
       <span>
          <input type="text" id="couponInvoiceAmount" value="${offerCoupons.invoiceAmount}" name="couponInvoiceAmount" field-name="Invoice Amount"  placeholder="Invoice Amount">
       </span>
</div>

<div class="div33">
       <label class="labl">Coupon Value</label>
       <span>
          <input type="text" id="couponValue" name="couponValue" value="${offerCoupons.couponValue}" field-name="Coupon Value" placeholder="Coupon Value">
       </span>
</div>
  <div class="clearfix"></div>
</div>
<!-- Coupon Ends -->

<!-- Items list div All and Selected items -->


<!-- is Store Level ? -->
<div class="div100">
    <div class="clearfix listul">
        <ul>
            <li><label><h6>is Location Level ?</h6></label></li>
            <li>
            <input type="radio" name="isl" id="islallSI" checked>&nbsp;&nbsp;Select Location
            <input type="radio" name="isl" id="islAll" >&nbsp;&nbsp;All
            </li>
            <!-- <li>
            <label>
            <input type="search" name="searchStore" placeholder="Search store..." id="isl-srch"></label>
            </li> -->
        </ul>
    </div>
    <p id="storeLevelRadios" style="display: none;"><font color="red">Store is required</font></p>
<div class="innerdiv clearfix">
<i class="fa fa-caret-down"></i>
<div class="clearfix selectitems-isl">

	
	     <c:forEach var="store" items="${storesList}">
	     <div class="div2">
		      <label><input type="checkbox" ${store.isStoreSelected == 'Y' ? 'checked' : ''} value="${store.id}"  name="selectedStores">${store.storeName}</label>
		 </div>
		 </c:forEach>
	


</div>
</div>
</div>
<!-- is Store Level ? Ends -->

<!-- is Customer Level ? -->
<%-- <div class="div100">
    <div class="clearfix listul">
        <ul>
            <li><label><h6>is Customer Level ?</h6></label></li>
            <li>
            <input type="radio" name="item" id="iclSI" checked>&nbsp;&nbsp;Selected Item
            <input type="radio" name="item" id="iclAll" >&nbsp;&nbsp;All
            </li>
        </ul>
    </div>
<div class="innerdiv clearfix">
<i class="fa fa-caret-down"></i>
<div class="clearfix selectitems-icl">
<input type="hidden" name="offerCustomerId" value="${offerCustomerLevel.id}"/>
    <div class="div2">
    <label><input type="checkbox" ${offerCustomerLevel.retailCustomer == 'Y' ? 'checked' : ''} id="retail" name="retail">Retail</label>
    </div>
    <div class="div2">
    <label><input type="checkbox" ${offerCustomerLevel.wholeSaleCustomer == 'Y' ? 'checked' : ''} id="wholesale" name="wholesale">Wholesale</label>
    </div>
</div>
<p id="customerLevelRadios" style="display: none;"><font color="red">Customer Level is required</font></p>
</div>
</div> --%>
<!-- is Customer Level ? Ends -->

<!-- Items list div All and Selected items -->


    <div class="clearfix"></div>
      <!-- Content Area inner -->
  </div>
  
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right">
           <input type="button" value="Add" onclick="addOffer();"  id="add_offer" class="btn btn-primary">
          <input type="button" value="Cancel" onClick="javascript:location.href = '${operoxUrl}/offers'" class="btn btn-default ml10">
        </div>
      </div>
    </div>
  </form:form>
  
</section>
<!-- Content Area Ends--> 




<script type="text/javascript">
	function addOffer() {
		var validationSettings = {
			errorMessagePosition : 'element',
			borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd'
		};
		
		if ($('#addOffer_form').validate(false, validationSettings)) {
			
		    var frm = $('#addOffer_form').serializeFormJSON();
	        var con = JSON.stringify(frm);
	        con = con.replace(/[{}]/g, "");
	        var jsonData = encodeURIComponent(con);
	        
	        var operoxUrl ='${operoxUrl}';
	        $("#add_offer").attr("disabled", "disabled");
     		$( "#add_offer" ).addClass('button').val('Processing..');
     		document.getElementById("storeLevelRadios").style.display="none";
     		document.getElementById("daysRadios").style.display="none";
     		/* document.getElementById("customerLevelRadios").style.display="none"; */
	        $.ajax({
		    	type: "POST",
		    	 url: operoxUrl+"/storeOffer?${_csrf.parameterName}=${_csrf.token}&json="+jsonData, 
		        success: function(result) {
		        	if((result == 'offerHome')){
		        		location.replace(operoxUrl+"/offers");  
		        	}else if(result == 'Invalid Stores'){
		        		document.getElementById("storeLevelRadios").style.display="";
		        		$("#add_offer").removeAttr("disabled");
		         		$( "#add_offer" ).addClass('button').val('Add');
		        	}
		        	else if(result == 'Invaid Day'){
		        		document.getElementById("daysRadios").style.display="";
		        		$("#add_offer").removeAttr("disabled");
		         		$( "#add_offer" ).addClass('button').val('Add');
		        	}
		        	/* else if(result == 'Invalid Customer Level'){
		        		document.getElementById("customerLevelRadios").style.display="";
		        		$("#add_offer").removeAttr("disabled");
		         		$( "#add_offer" ).addClass('button').val('Add');
		        	} */
			        
		        },
		    }); 
			
			return true;
		} else {
			return false;
		}
	}
	$('body').on('blur', '#addOffer_form', function() {
		$("#addOffer_form").showHelpOnFocus();
		$("#addOffer_form").validateOnBlur(false, validationSettings);
	});
</script>

<script type="text/javascript">
(function ($) {
   $.fn.serializeFormJSON = function () {

       var o = {};
       var a = this.serializeArray();
       $.each(a, function () {
           if (o[this.name]) {
               if (!o[this.name].push) {
                   o[this.name] = [o[this.name]];
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
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script>


<script>
function getOfferType(){
	
	
	
	var offerType = $("#offerType").val();
		if(offerType == 'Buy-X Get-Y'){
			document.getElementById("BuyXGetY").style.display=""; 
			$("#buyItem").attr("data-validation", "required");
			$("#buyItemQuantity").attr("data-validation", "required validate_int");
			$("#freeItem").attr("data-validation", "required");
			$("#freeItemQuantity").attr("data-validation", "required validate_int");
			
		}else {
			document.getElementById("BuyXGetY").style.display="none"; 
			$("#buyItem").removeAttr("data-validation");
			$("#buyItemQuantity").removeAttr("data-validation");
			$("#freeItem").removeAttr("data-validation");
			$("#freeItemQuantity").removeAttr("data-validation");
		}
		
		if(offerType == 'Free Item on Invoice'){
			document.getElementById("FreeItemOnInvoice").style.display=""; 
			$("#totalFreeItemInvoice").attr("data-validation", "required");
			$("#freeItemforInvoice").attr("data-validation", "required");
			$("#freeItemQuantityOnInvoice").attr("data-validation", "required");
		}else{
			document.getElementById("FreeItemOnInvoice").style.display="none"; 
			$("#totalFreeItemInvoice").removeAttr("data-validation");
			$("#freeItemforInvoice").removeAttr("data-validation");
			$("#freeItemQuantityOnInvoice").removeAttr("data-validation");
		}
		
		if(offerType == 'Discount on Invoice'){
			document.getElementById("DiscountOnInvoice").style.display=""; 
			$("#discountOnInvoiceAmount").attr("data-validation", "required validate_float");
			$("#discountOnInvoiceDiscountAmount").attr("data-validation", "required validate_float");
			
			
		}else{
			document.getElementById("DiscountOnInvoice").style.display="none"; 
			$("#discountOnInvoiceAmount").removeAttr("data-validation");
			$("#discountOnInvoiceDiscountAmount").removeAttr("data-validation");
		}
		
		
		if(offerType == 'Loyalty points'){
			document.getElementById("LoyalityPoints").style.display=""; 
			$("#loyaltyInvoiceAmount").attr("data-validation", "required");
			$("#invoiceLoyaltyPoints").attr("data-validation", "required");
			$("#oneLoyaltyPoint").attr("data-validation", "required");
			
		}else{
			document.getElementById("LoyalityPoints").style.display="none"; 
			$("#loyaltyInvoiceAmount").removeAttr("data-validation");
			$("#invoiceLoyaltyPoints").removeAttr("data-validation");
			$("#oneLoyaltyPoint").removeAttr("data-validation");
		}
		
		if(offerType == 'Coupons'){
			document.getElementById("CouponsName").style.display=""; 
			$("#couponName").attr("data-validation", "required validate_length length5-20");
			$("#couponInvoiceAmount").attr("data-validation", "required validate_float");
			$("#couponValue").attr("data-validation", "required validate_float");
			
		}else{
			document.getElementById("CouponsName").style.display="none"; 
			$("#couponName").removeAttr("data-validation");
			$("#couponInvoiceAmount").removeAttr("data-validation");
			$("#couponValue").removeAttr("data-validation");
		}
		
}
</script>


<script type="text/javascript">
  $( function() {
    $("#startDate, #endDate").datepicker();
  });


//Time picker
(function($) {
    $(function() {
        $('input.stdt, input.endt').timepicker();
    });
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

<script>
$('input[type=radio]#selectitems').attr('checked',true);
$("input[type=search]#selectitems-srch").attr('disabled', 'disabled');
$("input[type=search]#isl-srch").attr('disabled', 'disabled');
$("input[type=search]#icl-srch").attr('disabled', 'disabled');


//Select Items
$('.selectitems-si input[type=checkbox]').attr('checked',true);
   $("#selectitemsall").change(function(){
	$('.selectitems-si input[type=checkbox]').attr('checked',false);
	$("input[type=search]#selectitems-srch").removeAttr('disabled');
	});

   $("#selectitems").change(function(){
	$(".selectitems-si input[type=checkbox]").prop('checked', $(this).prop("checked"));
	$("input[type=search]#selectitems-srch").attr('disabled', 'disabled');
	});


//is Store Level ?
   $("#islallSI").change(function(){
	$('.selectitems-isl input[type=checkbox]').attr('checked',false);
	$("input[type=search]#isl-srch").removeAttr('disabled');
	});

   $("#islAll").change(function(){
	$(".selectitems-isl input[type=checkbox]").prop('checked', $(this).prop("checked"));
	$("input[type=search]#isl-srch").attr('disabled', 'disabled');
	});

//is Customore Level ?
   $("#iclSI").change(function(){
	$('.selectitems-icl input[type=checkbox]').attr('checked',false);
	$("input[type=search]#icl-srch").removeAttr('disabled');
	});

   $("#iclAll").change(function(){
	$(".selectitems-icl input[type=checkbox]").prop('checked', $(this).prop("checked"));
	$("input[type=search]#icl-srch").attr('disabled', 'disabled');
	});


</script>


<script>
function validateInvoiceAmount(){
	var invoiceAmount = document.getElementById("discountOnInvoiceAmount").value;
	if(invoiceAmount){
		 var operoxUrl ='${operoxUrl}';
		 $.ajax({
		    	type: "POST",
		    	 url: operoxUrl+"/validateInvoiceAmount?${_csrf.parameterName}=${_csrf.token}&invoiceAmount="+invoiceAmount, 
		        success: function(result) {
		        	if(result == 'valid'){
			    		$("#add_offer").removeAttr("disabled");
			    		$('#discountOnInvoiceAmountDiv').find('p.jquery_form_error_message').remove(); 
			    		$('input#discountOnInvoiceAmount').removeAttr( "class record-exist record-exist-errormsg");
						$('#WorkOrderIdDiv').find('p.jquery_form_error_message').remove();
						$('input#discountOnInvoiceAmount').attr('class','form-control');
						$( "#add_offer" ).removeAttr("style");
						$( "#add_offer" ).addClass('btn btn-primary').val('Add');
						$( "#add_offer" ).click(function() {
						});
						
			    	}
			    	else if(result == 'invalid'){
		    		$("#add_offer").attr("disabled", "disabled");
			    		$('#discountOnInvoiceAmountDiv').find('p.jquery_form_error_message').remove(); 
			    		$('input#discountOnInvoiceAmount').attr('class','error form-control');
			    		$("input#discountOnInvoiceAmount").after("<p class='jquery_form_error_message'>Duplicate Invoice Amount</p>");
						$('input#discountOnInvoiceAmount').attr('record-exist','yes');
						$('input#discountOnInvoiceAmount').attr('record-exist-errorMsg',' Duplicate Invoice Amount'); 
						$("#add_offer" ).addClass('btn btn-primary').val('Add');
			    	} 
		        },
		    }); 
	}
}
</script>



<script>
function validateCouponName(){
	var couponName = document.getElementById("couponName").value;
	if(couponName){
		 var operoxUrl ='${operoxUrl}';
		 $.ajax({
		    	type: "POST",
		    	 url: operoxUrl+"/validateCouponName?${_csrf.parameterName}=${_csrf.token}&couponName="+couponName, 
		        success: function(result) {
		        	if(result == 'valid'){
			    		$("#add_offer").removeAttr("disabled");
			    		$('#couponNameDiv').find('p.jquery_form_error_message').remove(); 
			    		$('input#couponName').removeAttr( "class record-exist record-exist-errormsg");
						$('#couponNameDiv').find('p.jquery_form_error_message').remove();
						$('input#couponName').attr('class','form-control');
						$( "#add_offer" ).removeAttr("style");
						$( "#add_offer" ).addClass('btn btn-primary').val('Add');
						$( "#add_offer" ).click(function() {
						});
						
			    	}
			    	else if(result == 'invalid'){
		    		$("#add_offer").attr("disabled", "disabled");
			    		$('#couponNameDiv').find('p.jquery_form_error_message').remove(); 
			    		$('input#couponName').attr('class','error form-control');
			    		$("input#couponName").after("<p class='jquery_form_error_message'>Duplicate Coupon Name/Code</p>");
						$('input#couponName').attr('record-exist','yes');
						$('input#couponName').attr('record-exist-errorMsg',' Duplicate Coupon Name/Code'); 
						$("#add_offer" ).addClass('btn btn-primary').val('Add');
			    	} 
		        },
		    }); 
	}
}
</script>


<jsp:include page="../masterFooter.jsp" />