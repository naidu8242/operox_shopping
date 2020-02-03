<!DOCTYPE html>
<html lang="en"> 
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="${operoxUrl}/resources/images/fav.png" />
<title>Welcome to Operox :: The Online Store Application</title>
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">

<link href="${operoxUrl}/resources/css/chosen.min.css" rel="stylesheet">
<link href="${operoxUrl}/resources/css/jquery-ui.css" rel="stylesheet">
<link href="${operoxUrl}/resources/css/jquery.timepicker.min.css" rel="stylesheet">
<link href="${operoxUrl}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${operoxUrl}/resources/css/operox-main.css" rel="stylesheet">
<link href="${operoxUrl}/resources/css/operox2.css" rel="stylesheet">
<link href="${operoxUrl}/resources/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="${operoxUrl}/resources/css/bootstrap-select.min.css" type="text/css" >
<link href="${operoxUrl}/resources/css/jquery.timepicker.min.css" rel="stylesheet">



<script src="${operoxUrl}/resources/js/jquery-1.11.3.min.js"></script> 
<script src="${operoxUrl}/resources/js/moment.min.js"></script>
<script src="${operoxUrl}/resources/js/chosen.jquery.min.js"></script>
<script src="${operoxUrl}/resources/js/jquery-ui.min.js"></script> 
<script src="${operoxUrl}/resources/js/bootstrap-datetimepicker.min.js"></script> 
<script src="${operoxUrl}/resources/js/bootstrap.min.js"></script>
<script src="${operoxUrl}/resources/js/jquery.timepicker.min.js"></script>
<script src="${operoxUrl}/resources/js/masterHeaderAction.js"></script> 
<script src="${operoxUrl}/resources/js/jquery.timepicker.min.js"></script> 

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script> 
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<script>
		function logoutFormSubmit() {
			document.getElementById("logoutForm").submit();
		}
</script>

<script>
		function linkFormSubmit(orgLocationId) {
			document.getElementById("logoutForm").action="${operoxUrl}/dashboard/"+orgLocationId
			document.getElementById("logoutForm").submit();
		}
</script>

<style type="text/css">
.cardType label{
display: inline-block;
margin-right: 20px;
}
.cardType input[type="radio"] {
   height: auto;
    margin-right:5px;
    position: relative;
    top: -2px;
    width: auto;
}
.cardInfo div.expdate{
   margin-right: 10px;
    width: 35%;
    float: left;
}
.cardInfo div.cvv{
width:23%;
float: left;
}
.cardInfo div.expdate input, .cardInfo div.cvv input{
width: 100%;
text-align: left;
}
.cardInfo .jquery_form_error_message{
margin-left: 0 !important;
position: static !important;
} 
.payment_detail_top input {
text-align: left;
}
.payment_detail_top .jquery_form_error_message{
margin-left: 30%;
}
.delete_pop.deletepop_ok a {
width: 100%;
background-color: #e78c24;
}
.delete_pop.deletepop_ok p{
padding:30px 15px;
}
.counter_pop_empty {
width: 90%;
margin: 10% auto;
border-radius:5px;
color: #fff;
font-size: 30px;
}
.counter_pop_empty p a{
padding:0;
font-size: 30px;
color: #fff;
}
.counter_pop_empty p a:hover{
color:#ddd;
}
</style>

</head>
<body>
<div class="wraper clearfix"></div>
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post" id="logoutForm">
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<section class="content-wraper billing_wraper"> 
<form:form id="billing_form" name="billing_form" method="post">
<!-- Content Area -->
	<div class="bill_head">
    	<ul> 
        	<li>Bill No:<input type="hidden" id="billNumber" name="billNumber" /><span id="billNumberSpan"></span></li>
            <li>Bill Date:<input type="hidden" id="billDate" name="billDate" /><span id="billDateSpan"></span></li>
            <li>&nbsp;</li>
            <li>Store Name:<input type="hidden" id="billStoreId" name="billStoreId" /><span id="billStoreSpan"></span></li>
            <li>Location:<input type="hidden" id="billLocation" name="billLocation" /><span id="billLocationSpan"></span></li>
        </ul>
        <div class="bill_head_right">
        	<a href="${operoxUrl}/dashboard" class="bill_back">Back To Home</a>
        	<a href="javascript:logoutFormSubmit();" class="bill_sign"><i class="fa fa-power-off"></i></a>
        </div>
    </div>
	<div class="bill_out">
    	<div class="bill_wrap">
        	<div class="bill_search">
                <div class="div50">
                   <label class="labl">Barcode</label>
                   <span>
                   <input type="text" id="barCode" placeholder="Search By Barcode">
                   </span>
                </div> 
                <div class="div50">
                   <label class="labl">Product</label>
                   <span>
                      	<input type="text" placeholder="Search By Product Name" class="icon_bill" id="productName" name="productName" onkeypress="getProductsByProductName();">
                   </span>
                </div>
                <div class="div50">
                   <label class="labl">Product Code</label>
                   <span>
                   <input type="text" id="prodCode" placeholder="Search By Product Code">
                   </span>
                </div>
                <div class="clearfix"></div>                         	
            </div>
        	<div class="bill_table">
        	    <input type="hidden" id="maxProdRowNum" name="maxProdRowNum" value="1" />
            	<table id="data" cellpadding="0" cellspacing="0" >
            	     <tbody> 
            	         <tr>
                        	<th>S.No</th>
                            <th>Product</th>
                            <th>Qty</th>
                            <th>MRP/unit</th>
                            <th>Tax</th>
                            <th>Discount</th>
                            <th>Total</th>
                         </tr>
                          <!--  <th class="action">Action</th> -->
                     </tbody>
                </table>
				<!--  popup  -->
				<div class="qty_pop">
					<p>Change Qty</p>
					<input type="hidden" id="isPopUpEnabled" value="false"/>
				    <input id="changeQuantity" min="1" type="number" value="">
				</div>
				<!--  popup  -->  
                
            </div>
           <div class="bill_short">
            	<ul> 
                	<li><a  href="javascript:removeProductRow();"><b>F1</b>Remove Product</a></li>
                    <li><a  href="javascript:showQuantityPop();"><b>F2</b>Change Qty</a></li>
                    <li><a  href="javascript:holdBillingDetails('onhold');"><b>F3</b>Hold Invoice</a></li>
                    <li><a  href="javascript:pickInvoice();"><b>F4</b>Pick Invoice</a></li>
                    <li><a  href="javascript:cancelBill();"><b>F5</b>Cancel Invoice</a></li>
                    <!-- <li><a id="f6" href="#"><b>F6</b>Show Offers</a></li>
                    <li><a href="#"><b>F12</b>Close Statement</a></li> -->
                    <div class="clearfix"></div>
                </ul>
            </div>
            
        </div>
        <div class="bill_right">
        	<div class="cashier">
            	<h2>Cashier Details:</h2> 
            	<div><b>Cashier:</b><input type="hidden" id="cashierUserId" name="cashierUserId"/> <span id="cashierSpan"></span><div class="clearfix"></div></div>
                <div><b>Shift:</b><input type="hidden" id="cashierShiftId" name="cashierShiftId"/><span id="cashierShiftSpan"></span><div class="clearfix"></div></div>
                <div><b>Counter No:</b><input type="hidden" name="counterId" id="counterId" value=""/><input type="hidden" name="counterNumber" id="counterNumber" value=""/><span id="counterNumberSpan"></span><div class="clearfix"></div></div>
            	<h2>Customer Details:</h2>
            	<div>
					<label class="labl pull-left">Name:</label>
					 <input type="text"	class="pull-right" placeholder="Customer name" name="customername">
					<div class="clearfix"></div>
				</div>
				<div>
					<label class="labl pull-left">Phone:</label>
					 <input type="text"	class="pull-right" placeholder="Customer phone" name="customerPhone">
					<div class="clearfix"></div>
				</div>
				<h3>Payment Details:</h3> 
				<div><b>No of products:</b><input type="hidden" name="noOfProducts" id="noOfProducts" value=""/><span id="noOfProductsSpan">0</span><div class="clearfix"></div></div>
				<div><b>Total(Inc.All Taxes):</b><input type="hidden" name="totalAmount" id="totalAmount" value=""/><span id="totalAmountSpan">0</span><div class="clearfix"></div></div>
				<div><b>Tax:</b><input type="hidden" name="tax" id="tax" value=""/><span id="taxSpan">0</span><div class="clearfix"></div></div>
				<div><b>Discount(-):</b><input type="hidden" id="discount"	name="discount" value=""><span id="discountSpan">0</span><div class="clearfix"></div></div>
				<div><b>Invoice amount:</b><input type="hidden" name="grossAmount" id="grossAmount" value=""/><span id="grossAmountSpan">0</span><div class="clearfix"></div></div>
				<div><b>Discount On Invoice amount:</b><input type="hidden" name="discountOnInvoice" id="discountOnInvoice" value=""/><span id="discountOnInvoiceSpan">0</span><div class="clearfix"></div></div>
                <div><b>Bill Amount(in USD):</b><input type="hidden" name="billAmount" id="billAmount" value=""/><span id="billAmountSpan">0</span><div class="clearfix"></div></div>
                <h4>Total amount to paid:</h4>
               <div class="currency"> 
                <div class="currencyType"> <select id="currencyType" name="currencyType" class="selList" onchange="changeCurrencyType();"><c:forEach var="report" items="${currencyList}"><option id="${report.id}" ${report.isDefault == 'Y' ? 'checked' : '' } value="${report.exchangeValue}">${report.symbol}</option></c:forEach></select> </div>
                   <div class="currencyAmount">
                     <input type="hidden" name="currencyId" id="currencyId" value=""/>
	                 <input type="hidden" name="actualBillAmount" id="actualBillAmount" value=""/>
	                 <strong id="actualBillAmountSpan">0</strong>
                  </div>
                <div class="clearfix"></div>
                </div>
            </div>
            <div class="bill_btn">
               <input type="button" value="Proceed" class="sav_bt">         
            </div>
        </div>
    	<div class="clearfix"></div>
    </div> 
  </form:form>
</section>
<!--    Counter pop   -->
<div style="display:none;" class="counter_mask">
     <div class="counter_btn">
        <input  onClick="javascript:location.href = '${operoxUrl}/dashboard';" type="button" value="Home">	
         <c:if test="${!empty countersList}">
              <input id="counterGo" type="button" value="GO">
         </c:if>
    </div>
</div>    
<div style="display:none;" class="counter_pop">
    <c:if test="${!empty countersList}">
	      <c:forEach var="report" items="${countersList}" varStatus="counter"> 
		     <div class="counter_cta">
		   	  	<img src="${operoxUrl}/resources/images/counter.png" alt=""/>
		        <span>
		        	<input type="radio" name="counterRadio" id="counterNumber${counter.index+1}" value="${report.counterName}^${report.id}"  class="css-checkbox"  />
		            <label for="counterNumber${counter.index+1}" alt="${report.counterName}" class="css-label radGroup2">${report.counterName}</label>
		        </span>
		    </div>
		  </c:forEach>
        <div class="clearfix"></div>
   </c:if>
   <c:if test="${empty countersList}">
	   <div class="counter_pop_empty">
			 <p>No Counters Are Configured. <a href="javascript:location.href = '${operoxUrl}/addCounter'">Click Here To Configure The Counter</a></p>
		</div>
             
   </c:if>
</div>
<!--    Counter pop   -->   
<!--  Error Message  -->
<div class="error_mask"></div>
<div class="delete_pop deletepop_ok">
		<p>Please Select Atleast One Product ....</p>
		<a href="javascript:void(0)">Ok</a> 
	</div>
<!--  Error Message  -->


<!-- Payment Detail pop start-->
             <div class="payment_pop">
				<h1>Payment details</h1>
			    <div class="payment_detail_top">
			    	<div><b>No of products:</b><span id="noOfProductsInPop"></span><div class="clearfix"></div></div>
			    	<div><b>Total amount:</b><span id="totalAmountInPop"></span><div class="clearfix"></div></div>       
			    </div>
				<div class="withdraw_details">
				<ul class="withdraw_tabs">
					<li class="current" ><a href="#tab-1" onClick="resetForm();">Cash</a></li>
					<li><a href="#tab-2" onClick="resetForm();">Card</a></li>
				    <li><a href="#tab-3" onClick="resetForm();">Gift card</a></li>
				    <li><a href="#tab-4" onClick="resetForm();">Coupons</a></li>
				</ul>
				<div class="withdraw_tabs_show">
					<div id="tab-1" class="withdraw_tabs_show_cta">
						<form:form id="payment_cash_form" name="payment_cash_form" method="post">
						    <div class="payment_detail_top">
						    	<div>
						        	<b>Cash</b><input type="text" id="paymentCash" name="paymentCash" value="" field-name="Cash" onblur="paymentCashCalculation();" data-validation="required validate_Space validate_int validate_length length1-10"><div class="clearfix"></div>
						        </div>
						    	<div>
						        	<b>Change</b><input type="text" id="paymentChange" name="paymentChange" value="" ><div class="clearfix"></div>
						        </div>
						    	<div>
						        	<b>&nbsp;</b><input type="button" value="PAY" class="pay-btn" onClick="saveBillingDetails('paid');"><div class="clearfix"></div>
						        </div>                          
						    </div>
					    </form:form>
					</div>
					
					<div id="tab-2" class="withdraw_tabs_show_cta">  
						<form:form id="payment_card_form" name="payment_card_form" method="post">
						    <div class="payment_detail_top">
						    	  <div class="clearfix cardType">
							    	 <b>Card Type</b>
							    	   <label><input type="radio" value="Credit Card" name="cardType" id="cardType" onclick="availablityValidation(this);">Credit Card</label>
							    	   <label><input type="radio" value="Debit Card" name="cardType" id="cardType" onclick="availablityValidation(this);">Debit Card</label>
							    	    <input type="hidden" name="cardTypeName" value="" id="availablityValue" field-name="Card Type" data-validation="required"/>	 
						    	  </div>
						          <div class="clearfix">
						        	<b>Card Number</b><input type="text" placeholder="Card Number" id="cardNumber" name="cardNumber" value="" field-name="Card Number" data-validation="required validate_Space validate_int validate_length length5-16"><div class="clearfix"></div>
						          </div>
						        	
						           <div class="clearfix cardInfo">
							    	  <b>&nbsp;</b>
							    	  <div class="expdate">
							    	   <input type="text" placeholder="Expiry Date" class="" id="cardCxpdate" name="cardCxpdate" value="" field-name="Card Date" data-validation="required validate_Space  validate_length length5-10">
							    	   </div>
							    	  <div class="cvv">
							    	   <input type="text" placeholder="CVV Number" class="" id="cardCvv" name="cardCvv" value="" field-name="CVV" data-validation="required validate_Space validate_int validate_length length3-5">
							    	   </div>
						    	    </div>
						       
						    	<div>
						        	<b>&nbsp;</b><input type="button" value="PAY" class="pay-btn" onClick="saveBillingCardDetails('paid');"><div class="clearfix"></div>
						        </div>       
						    </div>   
					    </form:form>
					</div>
				    
					<div id="tab-3" class="withdraw_tabs_show_cta"> 
						<form:form id="payment_gift_form" name="payment_gift_form" method="post">
						    <div class="payment_detail_top">
						    	<div>
						        	<b>Gift card number</b><input type="text" id="giftCardNumber" name="giftCardNumber" value="" field-name="Gift card number" data-validation="required validate_Space validate_int validate_length length5-20"><div class="clearfix"></div>
						        </div> 
						    	<div>
						        	<b>Gift card amount</b><input type="text" id="giftCardAmount" name="giftCardAmount" value=""><div class="clearfix"></div>
						        </div>          
						    	<div>
						        	<b>Cash</b><input type="text" id="giftcash" name="giftcash" value="" onblur="paymentGiftCalculation();" ><div class="clearfix"></div>
						        </div>
						    	<div>
						        	<b>Change</b><input type="text" id="giftChange" name="giftChange" value=""><div class="clearfix"></div>
						        </div>
						    	<div>
						        	<b>&nbsp;</b><input type="button" value="PAY" class="pay-btn" onClick="saveBillingGiftDetails('paid');"><div class="clearfix"></div>
						        </div>                          
						    </div>
					    </form:form>
					</div>
				    
					<div id="tab-4" class="withdraw_tabs_show_cta"> 
						<form:form id="payment_coupon_form" name="payment_coupon_form" method="post"> 
						    <div class="payment_detail_top">
						    	<div>
						        	<b>Coupon Number</b><input type="text" id="coupon1Amount" name="coupon1Amount" onblur="getCoupounAmount();" value="" field-name="Coupon Number" data-validation="required validate_Space validate_length length5-20"><div class="clearfix"></div>
						        </div> 
						    	<div>
						        	<b>Coupon Amount</b><input type="text" readonly="readonly" id="coupon2Amount" name="coupon2Amount" value=""><div class="clearfix"></div>
						        </div>          
						    	<div>
						        	<b>Cash</b><input type="text" id="couponCash" name="couponCash" value="" onblur="paymentCouponCalculation();" ><div class="clearfix"></div>
						        </div>
						    	<div>
						        	<b>Change</b><input type="text" id="couponChange" name="couponChange" value=""><div class="clearfix"></div>
						        </div>
						    	<div>
						        	<b>&nbsp;</b><input type="button" value="PAY" class="pay-btn" onClick="saveBillingCouponDetails('paid');"><div class="clearfix"></div>
						        </div>                          
						    </div>
					    </form:form> 
					</div>    
				    
				    <div class="clearfix"></div>    
				</div>            
				
				<div class="clearfix"></div>
				</div>    
				    
				</div>


<!-- Payment Detail pop ends-->


<!-- Pick Invoice Pop starts-->
<div class="error_mask"></div>
     <div class="pickInvoice_pop">
		    <h1>On-Hold Invoice Details</h1>
		    <div class="pickInvoice_pop_top">
		    	<div><b>Pick Invoice:</b> <select id="pickInvoice" class="selList" onchange="getOnHoldList(this);"></select><div class="clearfix"></div></div>
		    	<div class="clearfix">
		    	<!-- <div class="pull-right">
		    		<input type="button" value="Submit" title="Submit" class="btn btn-warning mr10">
		    		<input type="button" value="Cancel" title="Cancel" class="btn btn-default pickInvoice-close">
		    	</div> -->
		    	</div>
		    </div>
    </div>
<!-- Pick Invoice pop ends-->



<script type="text/javascript">
function getCoupounAmount(){
	var couponName = document.getElementById("coupon1Amount").value 
	$("#coupon2Amount").val('');
	if(couponName){
		 var operoxUrl ='${operoxUrl}';
		 $.ajax({
		    	type: "POST",
		    	 url: operoxUrl+"/evaluateCoupon?${_csrf.parameterName}=${_csrf.token}&couponName="+couponName, 
		        success: function(result) {
		        	if(result == 'Invalid Coupon'){
				    		$('#couponNumberDiv').find('p.jquery_form_error_message').remove(); 
				    		$("input#coupon1Amount").after("<p class='jquery_form_error_message'>Invalid Coupon</p>");
							$('input#coupon1Amount').attr('record-exist','yes');
							$('input#coupon1Amount').attr('record-exist-errorMsg',' Invalid Coupon'); 
			    	}
		        	
		        	else if(result == 'Invalid Store'){
			    		$('#couponNumberDiv').find('p.jquery_form_error_message').remove(); 
			    		$("input#coupon1Amount").after("<p class='jquery_form_error_message'>Invalid Coupon Location</p>");
						$('input#coupon1Amount').attr('record-exist','yes');
						$('input#coupon1Amount').attr('record-exist-errorMsg',' Invalid Coupon Location'); 
			    		
			    	}  
		        	 
		        	else{
			    		$('#couponNumberDiv').find('p.jquery_form_error_message').remove(); 
			    		$('input#coupon1Amount').removeAttr( "class record-exist record-exist-errormsg");
						$('#couponNumberDiv').find('p.jquery_form_error_message').remove();
						$("#coupon2Amount").val(result);
			    	}
			    	
		        },
		    }); 
	}
	
}
</script>


<script>
// Error pop
	function billValidatePop(supplierId){
		$(".delete_pop").show();
		$(".error_mask").fadeIn(200);
		//$("#removeSupplierConfirm").attr("onclick", "removeSupplier("+supplierId+");");
	}
	$(".delete_pop a").click(function(){
		$(".delete_pop").hide();
		$(".error_mask").fadeOut(200);
	});
	$("#f4").click(function(){
      $(".pickInvoice_pop").fadeIn(200);
		$(".error_mask").fadeIn(200);
	});
	$(".pickInvoice-close").click(function(){
		$(".pickInvoice_pop").fadeOut(200);
	});

</script>

<!-- =============================== AddProduct Divs  ============================ -->
 <input type="hidden" id="maxProdRowNum" name="maxProdRowNum" value="1" />
<div id="divProdTd1" style="display: none; float: left;">
	<div class="ta-sm">REPLACEROWID</div>
</div>  
 <div id="divProdTd2" style="display: none; float: left;">  
    <input type="hidden" id="REPLACEROWIDproductStockId"
		name="REPLACEROWIDproductStockId" value="PRODUCTSTOCKIDVALUE" /> 
	<div class="ta-sm">PRODUCTNAMEVALUE</div>
</div>
<div id="divProdTd3" style="display: none; float: left;">
   <input type="hidden" id="REPLACEROWIDquantity"
		name="REPLACEROWIDquantity" value="QUANTITYVALUE" /> 
	 <input type="hidden" id="REPLACEROWIDmaxQuantity"
		name="REPLACEROWIDmaxQuantity" value="MAXQUANTITY" /> 
	<div class="ta-sm" id="REPLACEROWIDquantitySpan">QUANTITYVALUE</div> 
</div> 
<div id="divProdTd4" style="display: none; float: left;">
   <input type="hidden" id="REPLACEROWIDunitPrice"
		name="REPLACEROWIDunitPrice" value="UNITPRICEVALUE" /> 
	<div class="ta-sm">UNITPRICEVALUE</div> 
</div>  
<div id="divProdTd5" style="display: none; float: left;">
   <input type="hidden" id="REPLACEROWIDtaxOnItem"
		name="REPLACEROWIDtaxOnItem" value="TAXONITEMVALUE" /> 
	<div class="ta-sm">TAXONITEMVALUE</div> 
</div> 
<div id="divProdTd6" style="display: none; float: left;">
   <input type="hidden" id="REPLACEROWIDdiscountOnItem"
		name="REPLACEROWIDdiscountOnItem" value="DISCOUNTONITEMVALUE" /> 
	<div class="ta-sm">DISCOUNTONITEMVALUE</div> 
</div> 

<div id="divProdTd7" style="display: none; float: left;">
     <input type="hidden" id="REPLACEROWIDtotal"
		name="REPLACEROWIDtotal" value="TOTALVALUE" /> 
	 <div class="ta-sm" id="REPLACEROWIDtotalSpan">TOTALVALUE</div>  
</div> 
<!-- <div id="divProdTd6" style="display: none; float: left;">
		<a href="#" onclick="removeProduct(REPLACEROWID);"><i class="glyphicon glyphicon-trash deme"></i></a>
</div> -->
<!-- =============================== AddProduct Divs  ============================ -->

<!-- Payment Cash Calculation part -->
<script type="text/javascript">
function paymentCashCalculation(){
	var paymentCash = document.getElementById("paymentCash").value 
	var paymentTotalAmount = document.getElementById("totalAmount").value 
	if(paymentCash != null && paymentCash != "undefined" && paymentCash != "" && paymentTotalAmount != null && paymentTotalAmount != "undefined" && paymentTotalAmount != "" ){
		$("#paymentChange").val(parseInt(paymentCash) - parseInt(paymentTotalAmount));  
	}
}
</script>

<!-- Payment Gift Calculation part -->
<script type="text/javascript">
function paymentGiftCalculation(){
	var paymentGiftCash = document.getElementById("giftcash").value 
	var paymentTotalAmount = document.getElementById("totalAmount").value 
	var giftCardAmount = document.getElementById("giftCardAmount").value 
	if(paymentGiftCash != null && paymentGiftCash != "undefined" && paymentGiftCash != "" && paymentTotalAmount != null && paymentTotalAmount != "undefined" && paymentTotalAmount != "" ){
		$("#giftChange").val(parseInt(paymentGiftCash) - (parseInt(paymentTotalAmount) - parseInt(giftCardAmount)));  
	}
}
</script>

<!-- Payment Coupon Calculation part -->
<script type="text/javascript">
function paymentCouponCalculation(){
	var paymentCouponCash = document.getElementById("couponCash").value 
	var paymentTotalAmount = document.getElementById("totalAmount").value 
	var couponAmount = document.getElementById("coupon2Amount").value 
	if(paymentCouponCash != null && paymentCouponCash != "undefined" && paymentCouponCash != "" && paymentTotalAmount != null && paymentTotalAmount != "undefined" && paymentTotalAmount != "" ){
		$("#couponChange").val(parseInt(paymentCouponCash) - (parseInt(paymentTotalAmount) - parseInt(couponAmount)));  
	}
}
</script>

 
<script type="text/javascript">
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script>  
<script type="text/javascript">
function availablityValidation(id){
	document.getElementById("availablityValue").value = id.value;
}  
</script>
<script type="text/javascript">
     function resetForm() {
    	  $('#payment_cash_form').each(function(){
              this.reset();
              $('#tab-1').find('p.jquery_form_error_message').remove();
              $('#tab-2').find('p.jquery_form_error_message').remove();
              $('#tab-3').find('p.jquery_form_error_message').remove();
              $('#tab-4').find('p.jquery_form_error_message').remove();
          });
          $('#payment_card_form').each(function(){
              this.reset();
              $('#tab-1').find('p.jquery_form_error_message').remove();
              $('#tab-2').find('p.jquery_form_error_message').remove();
              $('#tab-3').find('p.jquery_form_error_message').remove();
              $('#tab-4').find('p.jquery_form_error_message').remove();
          });
          $('#payment_gift_form').each(function(){
              this.reset();
              $('#tab-1').find('p.jquery_form_error_message').remove();
              $('#tab-2').find('p.jquery_form_error_message').remove();
              $('#tab-3').find('p.jquery_form_error_message').remove();
              $('#tab-4').find('p.jquery_form_error_message').remove();
          });
          $('#payment_coupon_form').each(function(){
              this.reset();
              $('#tab-1').find('p.jquery_form_error_message').remove();
              $('#tab-2').find('p.jquery_form_error_message').remove();
              $('#tab-3').find('p.jquery_form_error_message').remove();
              $('#tab-4').find('p.jquery_form_error_message').remove();
          });
     }
</script> 

<script>
function holdBillingDetails(status){
		var frm = $('#billing_form').serializeFormJSON();
		var con = JSON.stringify(frm);
		con = con.replace(/[{}]/g, "");
		var jsonData = encodeURIComponent(con);
		var jsonData1 = ""; 
		var operoxUrl = '${operoxUrl}';
		$.ajax({
				type : "POST", url : operoxUrl + "/saveBillingDetails?${_csrf.parameterName}=${_csrf.token}&json="+ jsonData+"&status="+status+"&paymentjson="+jsonData1,
				success : function(data) {
					if(data == "success"){
						$(".payment_pop").hide();
						cancelBill();
					}
		        },
			});
	} 

</script>
<script>
function saveBillingDetails(status){
	var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
	if ($('#payment_cash_form').validate(false,validationSettings)) {
		
		var frm = $('#billing_form').serializeFormJSON();
		var con = JSON.stringify(frm);
		con = con.replace(/[{}]/g, "");
		var jsonData = encodeURIComponent(con);
		//payment cash json object
		var frm1 = $('#payment_cash_form').serializeFormJSON();
		var con1 = JSON.stringify(frm1);
		con1 = con1.replace(/[{}]/g, "");
		var jsonData1 = encodeURIComponent(con1); 
		
		var operoxUrl = '${operoxUrl}';
			$.ajax({
					type : "POST", url : operoxUrl + "/saveBillingDetails?${_csrf.parameterName}=${_csrf.token}&json="+ jsonData+"&status="+status+"&paymentjson="+jsonData1,
					success : function(data) {
						if(data == "success"){
							$(".payment_pop").hide();
							cancelBill();
						}
			        },
				});
				 return true;
			} else {
				return false;
			}
	} 
$('body').on('blur', '#payment_cash_form', function() {
	$("#payment_cash_form").showHelpOnFocus();
	$("#payment_cash_form").validateOnBlur(false, validationSettings);
});

</script>

<script>
function saveBillingCardDetails(status){
	var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
	if ($('#payment_card_form').validate(false,validationSettings)) {
		
		var frm = $('#billing_form').serializeFormJSON();
		var con = JSON.stringify(frm);
		con = con.replace(/[{}]/g, "");
		var jsonData = encodeURIComponent(con);
		//payment cash json object
		var frm1 = $('#payment_card_form').serializeFormJSON();
		var con1 = JSON.stringify(frm1);
		con1 = con1.replace(/[{}]/g, "");
		var jsonData1 = encodeURIComponent(con1); 
		
		var operoxUrl = '${operoxUrl}';
			$.ajax({
					type : "POST", url : operoxUrl + "/saveBillingDetails?${_csrf.parameterName}=${_csrf.token}&json="+ jsonData+"&status="+status+"&paymentjson="+jsonData1,
					success : function(data) {
						if(data == "success"){
							$(".payment_pop").hide();
							cancelBill();
						}
			        },
				});
				 return true;
			} else {
				return false;
			}
	} 
$('body').on('blur', '#payment_card_form', function() {
	$("#payment_card_form").showHelpOnFocus();
	$("#payment_card_form").validateOnBlur(false, validationSettings);
});

</script>

<script>
function saveBillingGiftDetails(status){
	var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
	if ($('#payment_gift_form').validate(false,validationSettings)) {
		
		var frm = $('#billing_form').serializeFormJSON();
		var con = JSON.stringify(frm);
		con = con.replace(/[{}]/g, "");
		var jsonData = encodeURIComponent(con);
		//payment cash json object
		var frm1 = $('#payment_gift_form').serializeFormJSON();
		var con1 = JSON.stringify(frm1);
		con1 = con1.replace(/[{}]/g, "");
		var jsonData1 = encodeURIComponent(con1); 
		
		var operoxUrl = '${operoxUrl}';
			$.ajax({
					type : "POST", url : operoxUrl + "/saveBillingDetails?${_csrf.parameterName}=${_csrf.token}&json="+ jsonData+"&status="+status+"&paymentjson="+jsonData1,
					success : function(data) {
						if(data == "success"){
							$(".payment_pop").hide();
							cancelBill();
						}
			        },
				});
				 return true;
			} else {
				return false;
			}
	} 
$('body').on('blur', '#payment_gift_form', function() {
	$("#payment_gift_form").showHelpOnFocus();
	$("#payment_gift_form").validateOnBlur(false, validationSettings);
});

</script>

<script>
function saveBillingCouponDetails(status){
	var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
	if ($('#payment_coupon_form').validate(false,validationSettings)) {
		
		var frm = $('#billing_form').serializeFormJSON();
		var con = JSON.stringify(frm);
		con = con.replace(/[{}]/g, "");
		var jsonData = encodeURIComponent(con);
		//payment cash json object
		var frm1 = $('#payment_coupon_form').serializeFormJSON();
		var con1 = JSON.stringify(frm1);
		con1 = con1.replace(/[{}]/g, "");
		var jsonData1 = encodeURIComponent(con1); 
		
		var operoxUrl = '${operoxUrl}';
			$.ajax({
					type : "POST", url : operoxUrl + "/saveBillingDetails?${_csrf.parameterName}=${_csrf.token}&json="+ jsonData+"&status="+status+"&paymentjson="+jsonData1,
					success : function(data) {
						if(data == "success"){
							$(".payment_pop").hide();
							cancelBill();
						}
			        },
				});
				 return true;
			} else {
				return false;
			}
	} 
$('body').on('blur', '#payment_coupon_form', function() {
	$("#payment_coupon_form").showHelpOnFocus();
	$("#payment_coupon_form").validateOnBlur(false, validationSettings);
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
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
<script src="js/jquery-1.11.3.min.js"></script> 
<script src="js/jquery-1.11.3.min.js"></script> 
<script src="js/moment.min.js"></script> 
<script src="js/bootstrap-datetimepicker.min.js"></script> 
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/masterHeaderAction.js"></script> 


<script>
$(document).ready(function() {
    $("#counterGo").click(function(){
    	var counterNumberWithId = $("input[name=counterRadio]:checked").val();
    	if(counterNumberWithId != null && counterNumberWithId != 'undefined' && counterNumberWithId != ''){
    		 var counterNumber = counterNumberWithId.split("^")[0];
    		 var counterId = counterNumberWithId.split("^")[1];
    		 $("#counterNumber").val(counterNumber);
    		 $("#counterId").val(counterId);
    		 $("#counterNumberSpan").html(counterNumber);
    		 $(".counter_mask").hide();
    		 $(".counter_pop").hide();
    	}
    	
	});
    
    $(document).on('keyup',function(evt) {
        if (evt.keyCode == 27) {
   		$(".qty_pop").hide();
   		$(".payment_pop").hide();
   		$(".mask").hide();
   		$(".pickInvoice_pop").hide();
       }	
   });
   
   $(document).ready(function() {
   	$("#tab-1").show()
       $(".withdraw_tabs a").click(function(event) {
           event.preventDefault();
           $(this).parent().addClass("current");
           $(this).parent().siblings().removeClass("current");
           var tab = $(this).attr("href");
           $(".withdraw_tabs_show_cta").not(tab).css("display", "none");
           $(tab).fadeIn();
       });
   });
   
// Payment pop	
   $(".sav_bt").click(function(){
	    var noOfProducts =  $("#noOfProducts").val();
	    if(noOfProducts != null && noOfProducts != 'undefined' && noOfProducts != '' && noOfProducts != '0'){
	    	$("#noOfProductsInPop").html($("#noOfProducts").val()); 
		   	$("#totalAmountInPop").html($("#actualBillAmount").val());
		   	$("#billAmountInPop").html($("#actualBillAmount").val());
			$(".payment_pop").show();
			$(".mask").fadeIn(200);
	    }
	    else{
	    	billValidatePop();
	    }
	});
	
});
</script>
<script>
window.onload = function() {
	$("#maxProdRowNum").val(1);
	$("#barCode").val('');
	$("#prodCode").val('');
	$("#productName").val('');
	 
	$("#customername").val('');
	$("#customerPhone").val('');
	
	$("#noOfProducts").val('');
	$("#totalAmount").val('');
	$("#grossAmount").val('');
	$("#billAmount").val('');
	
	var counterNumber = $("#counterNumber").val();
	prepareBillSetup();
	
	if(counterNumber != null && counterNumber != 'undefined' && counterNumber != ''){
		 $(".counter_mask").removeAttr('style');
		 $(".counter_pop").removeAttr('style');
		 $(".counter_mask").attr('style','display:none;');
		 $(".counter_pop").attr('style','display:none;');
	}
	else{
		 $(".counter_mask").removeAttr('style');
		 $(".counter_pop").removeAttr('style');
		 $(".counter_mask").attr('style','display:block;');
		 $(".counter_pop").attr('style','display:block;');
	}
	 
	
    var rowCount = $('#data >tbody >tr').length; 
    var $tbody = $("#data tbody").on('click', 'tr',  function() {
            highlight($(this));
    });
	function gotoNext(){
	    var $next = $tbody.find('.highlight').nextAll('tr:visible:first');
	        highlight($next);
	}
	function gotoPrevious () {
	    var $prev = $tbody.find('.highlight').prevAll('tr:visible:first');
	        highlight($prev);
	}
	function showQuantityPop () {
		var $row = $tbody.find('.highlight');
		var rowId = $row.attr("id").split('divTsRow')[1];
		var currentQuantity = $("#"+rowId+"quantity").val();
		var maxRowQuantity = $("#"+rowId+"maxQuantity").val();
		$("#changeQuantity").val(currentQuantity);
		$("#changeQuantity").removeAttr("max");
		$("#changeQuantity").attr("max",parseFloat(maxRowQuantity));
		$("#isPopUpEnabled").val('true');
		$(".qty_pop").show();
		$("#changeQuantity").focus();
	}
	function removeProductRow(){
		var $row = $tbody.find('.highlight');
		var rowId = $row.attr("id").split('divTsRow')[1];
		if(rowId != null && rowId != 'undefined' && rowId != ''){
			removeProduct(rowId);
		}
		
	}
	function hideQuantityPop () {
		$("#changeQuantity").val('');
		$("#isPopUpEnabled").val('false');
		$(".qty_pop").hide();
	}
	
	

	function  changeQuantityAndPopUp(rowId){
		
	    var changedQuantity = $("#changeQuantity").val();
	    var currentQuantity = $("#"+rowId+"quantity").val();
	    var maxQuantity = $("#"+rowId+"maxQuantity").val();
	    if(parseFloat(changedQuantity)<=parseFloat(maxQuantity)){
			/**
			* change quantity and payment details
			**/
		   
			var unitPrice = $("#"+rowId+"unitPrice").val();
			
		    $("#"+rowId+"quantity").val(changedQuantity);
			$("#"+rowId+"quantitySpan").html(changedQuantity);
			
			
			
			//caluclating total mrp(including taxes)
			var currentMrpTotal = parseFloat(currentQuantity)*parseFloat(unitPrice);
			var changedMrpTotal = parseFloat(changedQuantity)*parseFloat(unitPrice);
			$("#"+rowId+"total").val(changedMrpTotal);
			
			var totalAmount =  $("#totalAmount").val();
		    if(totalAmount != null && totalAmount != 'undefined' && totalAmount != ''){
		    	totalAmount = parseFloat(totalAmount)-parseFloat(currentMrpTotal)+parseFloat(changedMrpTotal);
		    	$("#totalAmount").val(totalAmount);
		    }
		    else{
		    	totalAmount = changedMrpTotal;
		    	$("#totalAmount").val(totalAmount);
		    }
		    //caluclating tax total 
		    var currentTaxOnItem = $("#"+rowId+"taxOnItem").val();
		    var currentTaxTotal = parseFloat(currentQuantity)*parseFloat(currentTaxOnItem);
		    var changedTaxTotal = parseFloat(changedQuantity)*parseFloat(currentTaxOnItem);
		    var taxTotal =  $("#tax").val();
		    if(taxTotal != null && taxTotal != 'undefined' && taxTotal != ''){
		    	taxTotal = parseFloat(taxTotal)-parseFloat(currentTaxTotal)+parseFloat(changedTaxTotal);
		    	$("#tax").val(taxTotal);
		    }else{
		    	taxTotal = changedTaxTotal;
		    	$("#tax").val(taxTotal);
		    }
		    
		    //caluclating discount total 
		    var currentDiscountOnItem = $("#"+rowId+"discountOnItem").val();
		    var currentDiscountTotal = parseFloat(currentQuantity)*parseFloat(currentDiscountOnItem);
		    var changedDiscountTotal = parseFloat(changedQuantity)*parseFloat(currentDiscountOnItem);
		    var discountTotal =  $("#discount").val();
		    if(discountTotal != null && discountTotal != 'undefined' && discountTotal != ''){
		    	discountTotal = parseFloat(discountTotal)-parseFloat(currentDiscountTotal)+parseFloat(changedDiscountTotal);
		    	$("#discount").val(discountTotal);
		    }
		    else{
		    	discountTotal = changedDiscountTotal; 
		    	$("#discount").val(discountTotal);
		    }
		    
		    
		     //caluclating gross amount (total mrp-total discount)
		    var grossAmount =  $("#grossAmount").val();
		    var currentGrossAmount = parseFloat(currentQuantity)*parseFloat(unitPrice-currentDiscountOnItem);
		    var changedGrossAmount = parseFloat(changedQuantity)*parseFloat(unitPrice-currentDiscountOnItem);
		   if(grossAmount != null && grossAmount != 'undefined' && grossAmount != ''){
		    	grossAmount = parseFloat(grossAmount)-parseFloat(currentGrossAmount)+parseFloat(changedGrossAmount);
		    	$("#grossAmount").val(grossAmount);
		    }
		    else{
		    	grossAmount = changedGrossAmount; 
		    	$("#grossAmount").val(grossAmount);
		    }
		   
		    $("#totalAmountSpan").html(totalAmount);
		    $("#grossAmountSpan").html(grossAmount);
		    $("#taxSpan").html(taxTotal);
		    $("#discountSpan").html(discountTotal);
		    evaluateDiscountOnInvoice(grossAmount);
		    var changedTotal = parseFloat(changedQuantity)*parseFloat(unitPrice-currentDiscountOnItem);
		    $("#"+rowId+"total").val(changedTotal);
			$("#"+rowId+"totalSpan").html(changedTotal);
	     }
	    /**
		* change quantity and payment details
		**/
		
	    $("#isPopUpEnabled").val('false');
		$(".qty_pop").hide();
	}
	$(document).keydown(function (e) {
	  if ( $tbody.find('.highlight').length ) {
	    if (e.which == 40) {//down arrow
	        if($("#isPopUpEnabled").val()=='false'){
	        	e.preventDefault();
	        	gotoNext();
	        }
	    }
	    else if (e.which == 38) {//up arrow
	    	if($("#isPopUpEnabled").val()=='false'){
	    		e.preventDefault();
	    		 gotoPrevious();
	        }
	    }
	    else if (e.which == 112) {
	    	 if($("#isPopUpEnabled").val()=='false'){
	    		 e.preventDefault();
	    		 removeProductRow();
	    	 }
	    	
	    }
	    else if (e.which == 113) {
	    	e.preventDefault();
	    	showQuantityPop();
	    }
	    else if (e.which == 114) {
	    	 if($("#isPopUpEnabled").val()=='false'){
	    		 e.preventDefault();
	    		  holdBillingDetails("onhold");
	    	 }
	    }
	    else if (e.which == 115) {
	    	 if($("#isPopUpEnabled").val()=='false'){
	    		 e.preventDefault();
	    		  pickInvoice();
	    	 }
	    }
	    else if (e.which == 116) {
	    	if($("#isPopUpEnabled").val()=='false'){
	    		e.preventDefault();
	    		cancelBill();
	    	}
	    }
	    else if (e.which == 27) {
	    	  hideQuantityPop();
	    }
	    else if (e.which == 13) {
	    	if($("#isPopUpEnabled").val()=='true'){
	    		e.preventDefault();
	    		 var $row = $tbody.find('.highlight');
	    		 var rowId = $row.attr("id").split('divTsRow')[1];
	    		  changeQuantityAndPopUp(rowId);
	    	}
	    	
	    }
	    
	    
	  }
	 });
	    function highlight($row) {
	            if ($row.length) {
	                $tbody.children().removeClass("highlight");
	                $row.addClass('highlight');
	            }
	    }
	    
	  
	
	}
</script>
!--    Key functions    --> 
<script>
$(document).on('keyup',function(evt) {
   
    if (evt.keyCode == 13) {
    	var counterNumberWithId = $("input[name=counterRadio]:checked").val();
    	if(counterNumberWithId != null && counterNumberWithId != 'undefined' && counterNumberWithId != ''){
    		 var counterNumber = counterNumberWithId.split("^")[0];
    		 var counterId = counterNumberWithId.split("^")[1];
    		 $("#counterNumber").val(counterNumber);
    		 $("#counterId").val(counterId);
    		 $("#counterNumberSpan").html(counterNumber);
    		 $(".counter_mask").hide();
    		 $(".counter_pop").hide();
    	}
    }
    else if (evt.keyCode == 114) {
   	 if($("#isPopUpEnabled").val()=='false'){
   		  holdBillingDetails("onhold");
   	 }
   }
    else if (evt.keyCode == 115) {
      	 if($("#isPopUpEnabled").val()=='false'){
      		pickInvoice();
      	 }
      }
});
</script>
<script type="text/javascript">
function disableF5(e) { 
	   if(((e.which || e.keyCode) == 116) && ($("#isPopUpEnabled").val()=='false'))
	   {
		e.preventDefault();
		cancelBill();
	  } 
};
$(document).ready(function(){
     $(document).on("keydown", disableF5);
});
</script>

<script type="text/javascript">
function evaluateDiscountOnInvoice(grossAmount) {
	var operoxUrl = '${operoxUrl}';
	$.ajax({
			type : "POST", url : operoxUrl + "/evaluateDiscountOnInvoice?${_csrf.parameterName}=${_csrf.token}&invoiceAmount="+ grossAmount,
			success : function(data) {
				if(data){
					$("#discountOnInvoice").val(data);
					$("#discountOnInvoiceSpan").html(data);
					
				    var billAmount =  $("#billAmount").val();
				    var actualBillAmount =  0;
				    var currencyType = $("#currencyType").val();
				    if(billAmount != null && billAmount != 'undefined' && billAmount != ''){
				    	billAmount = parseFloat(grossAmount)-parseFloat(data);
				    	actualBillAmount = billAmount * parseFloat(currencyType);
				    	$("#billAmount").val(billAmount);
				    	$("#actualBillAmount").val(actualBillAmount);
				    }
				    else{
				    	 billAmount = parseFloat(grossAmount)-parseFloat(data);
				    	 actualBillAmount = billAmount * parseFloat(currencyType);
				    	 $("#billAmount").val(billAmount);
				    	 $("#actualBillAmount").val(actualBillAmount);
				    }
				    $("#billAmountSpan").html(billAmount);
				    $("#actualBillAmountSpan").html(actualBillAmount);
				}
	        },
		});
}
</script>


<script type="text/javascript">
	function addProduct(productStock) {
	    var productStockId =productStock.id;
	    var productName =productStock.productId.productName;
	    var maxQuantity = parseFloat(productStock.currentQuantity);
	    var currentQuantity = parseFloat(1);
	    var unitPrice = parseFloat(productStock.mrpStr);
	    var taxOnItemValue = 0;
        if(productStock.taxId != null && productStock.taxId != 'undefined' && productStock.taxId != '' && productStock.taxId.taxValue != null 
        		&& productStock.taxId.taxValue != 'undefined'&& productStock.taxId.taxValue != ''){
        	taxOnItemValue = productStock.taxId.taxValue;
        }
	    var discountItemValue = 0;
	    if(productStock.discount != null && productStock.discount != 'undefined' && productStock.discount != '' ){
	    	discountItemValue = productStock.discount;
        }
	    var totalValue = parseFloat(currentQuantity*(unitPrice-discountItemValue));
	    var totalMrpValue = parseFloat(currentQuantity*(unitPrice));
	    var totalDiscountValue = parseFloat(currentQuantity*(discountItemValue));
	    var totalTaxValue = parseFloat(currentQuantity*(taxOnItemValue));
	    
	   
	    
		var checkMaxTsRowNum = $("#maxProdRowNum").val();
	    for(var i = 1;i<checkMaxTsRowNum;i++){
	    	var productStockIdValue =  $("#"+i+"productStockId").val();
	    	if(productStockIdValue != null && productStockIdValue != 'undefined' && productStockIdValue != '' && productStockIdValue == productStockId){
	    		var currentRowQuantity = $("#"+i+"quantity").val();
	    		var maxRowQuantity = $("#"+i+"maxQuantity").val();
	    		var currentRowTotal = $("#"+i+"total").val();
	    		
	    		var changedQuantity =parseInt(currentRowQuantity)+parseInt(currentQuantity);
	    		var changedTotal =parseFloat(totalValue)+parseFloat(currentRowTotal);
	    		$("#changeQuantity").val(changedQuantity);
	    		$("#changeQuantity").removeAttr("max");
	    		$("#changeQuantity").attr("max",parseFloat(maxRowQuantity));
	    		var rowId = i;
    		    $("#"+rowId+"quantity").val(changedQuantity);
    		 	$("#"+rowId+"quantitySpan").html(changedQuantity);
    		 	
     		 	
    			var unitPrice = $("#"+rowId+"unitPrice").val();
	    			
    			//caluclating total mrp(including taxes)
    			var changedMrpTotal = parseFloat(currentQuantity)*parseFloat(unitPrice);
    			var totalAmount =  $("#totalAmount").val();
    		    if(totalAmount != null && totalAmount != 'undefined' && totalAmount != ''){
    		    	totalAmount = parseFloat(totalAmount)+parseFloat(changedMrpTotal);
    		    	$("#totalAmount").val(totalAmount);
    		    }
    		    else{
    		    	totalAmount = changedMrpTotal;
    		    	$("#totalAmount").val(totalAmount);
    		    }
    		    
    		    //caluclating tax total 
    		    var currentTaxOnItem = $("#"+rowId+"taxOnItem").val();
    		    if(currentTaxOnItem == null || currentTaxOnItem == 'undefined' || currentTaxOnItem == ''){
    		    	currentTaxOnItem = parseFloat(0);
    		    }
    		    var taxTotal =  $("#tax").val();
    		    var changedTaxTotal = parseFloat(currentQuantity)*parseFloat(currentTaxOnItem);
    		    if(taxTotal != null && taxTotal != 'undefined' && taxTotal != ''){
    		    	taxTotal = parseFloat(taxTotal)+parseFloat(changedTaxTotal);
    		    	$("#tax").val(taxTotal);
    		    }
    		    else{
    		    	taxTotal = changedTaxTotal;
    		    	$("#tax").val(taxTotal);
    		    }
    		    
    		    //caluclating discount total 
    		    var currentDiscountOnItem = $("#"+rowId+"discountOnItem").val();
    		    if(currentDiscountOnItem == null || currentDiscountOnItem == 'undefined' || currentDiscountOnItem == ''){
    		    	currenttaxonitem = parseFloat(0);
    		    }
    		    var discountTotal =  $("#discount").val();
    		    var changedDiscountTotal = parseFloat(currentQuantity)*parseFloat(currentDiscountOnItem);
    		    if(discountTotal != null && discountTotal != 'undefined' && discountTotal != ''){
    		    	discountTotal = parseFloat(discountTotal)+parseFloat(changedDiscountTotal);
    		    	$("#discount").val(discountTotal);
    		    }
    		    else{
    		    	discountTotal = changedDiscountTotal;
    		    	$("#discount").val(discountTotal);
    		    }
    		    
    		    
    		     //caluclating gross amount (total mrp-total discount)
    		    var grossAmount =  $("#grossAmount").val();
    		    var changedGrossAmount = parseFloat(currentQuantity)*parseFloat(unitPrice-discountItemValue);
    		    if(grossAmount != null && grossAmount != 'undefined' && grossAmount != ''){
    		    	grossAmount = parseFloat(grossAmount)+parseFloat(changedGrossAmount);
    		    	$("#grossAmount").val(grossAmount);
    		    } else{
    		    	grossAmount = changedGrossAmount;
    		    	$("#grossAmount").val(grossAmount);
    		    }
    		    $("#totalAmountSpan").html(totalAmount);
    		    $("#grossAmountSpan").html(grossAmount);
    		    $("#taxSpan").html(taxTotal);
    		    $("#discountSpan").html(discountTotal);
	    	    
	    	    $("#"+rowId+"total").val(changedTotal);
	    		$("#"+rowId+"totalSpan").html(changedTotal);
    		    $("#productName").blur();
    		    $("#barCode").blur();
    		    $("#prodCode").blur();
    		    evaluateDiscountOnInvoice(grossAmount);
	    		return;
	    	}
	    }
		var tbl = document.getElementById("data");
		var maxRowNum = parseInt($("input[name = 'maxProdRowNum']").val());
		$("input[name = 'maxProdRowNum']").val(maxRowNum + 1);
		
		var row = tbl.insertRow(maxRowNum);
		row.setAttribute("id", "divTsRow" + maxRowNum);

	    
		var cell0 = row.insertCell(0);
		var divTsTd1Str = document.getElementById("divProdTd1").innerHTML;
		divTsTd1Str = divTsTd1Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell0.innerHTML = divTsTd1Str;
		
	 	var cell1 = row.insertCell(1);
		var divTsTd2Str = document.getElementById("divProdTd2").innerHTML;
		divTsTd2Str = divTsTd2Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd2Str = divTsTd2Str.replace(/PRODUCTSTOCKIDVALUE/g,(productStockId)); 
		divTsTd2Str = divTsTd2Str.replace(/PRODUCTNAMEVALUE/g,(productName)); 
		cell1.innerHTML = divTsTd2Str;

		var cell2 = row.insertCell(2);
		var divTsTd3Str = document.getElementById("divProdTd3").innerHTML;
		divTsTd3Str = divTsTd3Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd3Str = divTsTd3Str.replace(/QUANTITYVALUE/g, (currentQuantity));
		divTsTd3Str = divTsTd3Str.replace(/MAXQUANTITY/g, (maxQuantity));
		cell2.innerHTML = divTsTd3Str;
		
		var cell3 = row.insertCell(3);
		var divTsTd4Str = document.getElementById("divProdTd4").innerHTML;
		divTsTd4Str = divTsTd4Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd4Str = divTsTd4Str.replace(/UNITPRICEVALUE/g, (unitPrice));
		cell3.innerHTML = divTsTd4Str;  
		 
		var cell4 = row.insertCell(4);
		var divTsTd5Str = document.getElementById("divProdTd5").innerHTML;
		divTsTd5Str = divTsTd5Str.replace(/TAXONITEMVALUE/g, (taxOnItemValue));
		divTsTd5Str = divTsTd5Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell4.innerHTML = divTsTd5Str; 
		
	    var cell5 = row.insertCell(5);
		var divTsTd6Str = document.getElementById("divProdTd6").innerHTML;
		divTsTd6Str = divTsTd6Str.replace(/DISCOUNTONITEMVALUE/g, (discountItemValue));
		divTsTd6Str = divTsTd6Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell5.innerHTML = divTsTd6Str;  
		
		var cell6 = row.insertCell(6);
		var divTsTd7Str = document.getElementById("divProdTd7").innerHTML;
		divTsTd7Str = divTsTd7Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd7Str = divTsTd7Str.replace(/TOTALVALUE/g, (totalValue));
		cell6.innerHTML = divTsTd7Str;
		
		renumberRows(); 
		
	    for(var i = 1;i<maxRowNum;i++){
	    	 $("#divTsRow"+ i).removeClass('highlight');
	    }
		$("#divTsRow"+ maxRowNum).addClass('highlight');
        
		/**@
		        Payment Details Calculation
		**/
		var totalRow =  $('#data tr:visible').length;
		var noOfProducts =  $("#noOfProducts").val();
	    if(noOfProducts != null && noOfProducts != 'undefined' && noOfProducts != ''){
	    	noOfProducts = parseFloat(noOfProducts)+1;
	    	$("#noOfProducts").val(noOfProducts);
	    }
	    else{
	    	noOfProducts = maxRowNum;
	    	$("#noOfProducts").val(totalRow-1);
	    }
	    var totalAmount =  $("#totalAmount").val();
	    if(totalAmount != null && totalAmount != 'undefined' && totalAmount != ''){
	    	totalAmount = parseFloat(totalAmount)+parseFloat(totalMrpValue);
	    	$("#totalAmount").val(totalAmount);
	    }
	    else{
	    	totalAmount = totalMrpValue;
	    	$("#totalAmount").val(totalAmount);
	    }
	    
	    var totalTaxAmount =  $("#tax").val();
	    if(totalTaxAmount != null && totalTaxAmount != 'undefined' && totalTaxAmount != ''){
	    	totalTaxAmount = parseFloat(totalTaxAmount)+parseFloat(totalTaxValue);
	    	$("#tax").val(totalTaxAmount);
	    }
	    else{
	    	totalTaxAmount = totalTaxValue;
	    	$("#tax").val(totalTaxAmount);
	    }
	    
	    var totalDisoucntAmount =  $("#discount").val();
	    if(totalDisoucntAmount != null && totalDisoucntAmount != 'undefined' && totalDisoucntAmount != ''){
	    	totalDisoucntAmount = parseFloat(totalDisoucntAmount)+parseFloat(totalDiscountValue);
	    	$("#discount").val(totalDisoucntAmount);
	    }
	    else{
	    	totalDisoucntAmount = totalDiscountValue;
	    	$("#discount").val(totalDisoucntAmount);
	    }
	    
	    var grossAmount =  $("#grossAmount").val();
	    if(grossAmount != null && grossAmount != 'undefined' && grossAmount != ''){
	    	grossAmount = parseFloat(grossAmount)+parseFloat(totalValue);
	    	$("#grossAmount").val(grossAmount);
	    }
	    else{
	    	grossAmount = totalValue;
	    	$("#grossAmount").val(grossAmount);
	    }
	   
	    $("#noOfProductsSpan").html(totalRow-1);
	    $("#totalAmountSpan").html(totalAmount);
	    $("#grossAmountSpan").html(grossAmount);
	    $("#taxSpan").html(totalTaxAmount);
	    $("#discountSpan").html(totalDisoucntAmount);
	    
	    evaluateDiscountOnInvoice(grossAmount);
	   
	    $("#productName").blur();
	    $("#barCode").blur();
	    $("#prodCode").blur();
	}
	
	function renumberRows() {
        $('#data tr:visible').each(function(index, el){
            $(this).children('td').first().text(index++);
        });
    }
	
	
</script>

<script type="text/javascript">
	function pickInvoice() {
		    var operoxUrl = '${operoxUrl}';
	        var counterId = $("#counterId").val();
	        if(counterId != null && counterId != 'undefined' && counterId != ''){
	        	$.ajax({ 
			    	type: "POST",
			    	url: operoxUrl+"/pickInvoice?${_csrf.parameterName}=${_csrf.token}&counterId="+counterId, 
			        success: function(data) {
			        	$(".pickInvoice_pop").show();
			        	$('#pickInvoice').empty();
			        	$('#pickInvoice').append("<option value=''>--select invoice--</option>");
			        	var json = JSON.parse(data);
			        	for(var i=0;i<json.onHoldBillList.length;i++){
			        		 var billNumber = json.onHoldBillList[i][0];
	   	 			         var displayBillDate = json.onHoldBillList[i][1].displayBillDate;
	   	 			         $('#pickInvoice').append("<option value='"+json.onHoldBillList[i][1].id+"'>"+billNumber+'('+displayBillDate+')'+"</option>");
			        	}
			        	
			        },
			        error: function(e){
			        }
			    });
	        }
	}
</script>
<script type="text/javascript">
	function removeProduct(rowId) {
		$("#barCode").val('');
		$("#prodCode").val('');
		$("#productName").val('');
		var rowNum = $("#divTsRow"+rowId).val();
		 document.getElementById("divTsRow"+rowId).style.display = 'none';
		 document.getElementById("divTsRow"+rowId).value= '';
		 $("#divTsRow"+rowId).removeAttr('class');
		 renumberRows(); 
		 
		 
		 var nextVisibleRowId = $("#divTsRow"+rowId).next('tr:visible').attr("id");
		 if(nextVisibleRowId !=null && nextVisibleRowId != 'undefined' && nextVisibleRowId != ''){
			 $("#divTsRow"+rowId).next('tr:visible').attr("class","highlight");
		 }
		 else{
			 $("#divTsRow"+rowId).prev('tr:visible').attr("class","highlight");
		 }
		 
			/**@
			        Payment Details Calculation
			**/
			var totalRow =  $('#data tr:visible').length;
			var removeTotal = $("#"+rowId+"total").val();
			
			var removedQuantity = $("#"+rowId+"quantity").val();
			var removedUnitPrice = $("#"+rowId+"unitPrice").val();
			var removedTax = $("#"+rowId+"taxOnItem").val();
			var removedDiscount = $("#"+rowId+"discountOnItem").val();
			var removeMrpTotal = parseFloat(removedQuantity)* parseFloat(removedUnitPrice);
			var removeTaxTotal = parseFloat(removedQuantity)* parseFloat(removedTax);
			var removeDiscountTotal = parseFloat(removedQuantity)* parseFloat(removedDiscount);
			
			var noOfProducts =  $("#noOfProducts").val();
		    if(noOfProducts != null && noOfProducts != 'undefined' && noOfProducts != ''){
		    	noOfProducts = parseFloat(noOfProducts)-1;
		    	$("#noOfProducts").val(noOfProducts);
		    }
		    else{
		    	noOfProducts = maxRowNum;
		    	$("#noOfProducts").val(totalRow-1);
		    }
		    var taxAmount =  $("#tax").val();
		    if(taxAmount != null && taxAmount != 'undefined' && taxAmount != ''){
		    	taxAmount = parseFloat(taxAmount)-parseFloat(removeTaxTotal);
		    	$("#tax").val(taxAmount);
		    }
		    else{
		    	taxAmount = removeTaxTotal;
		    	$("#tax").val(taxAmount);
		    }
		    
		    var discountAmount =  $("#discount").val();
		    if(discountAmount != null && discountAmount != 'undefined' && discountAmount != ''){
		    	discountAmount = parseFloat(discountAmount)-parseFloat(removeDiscountTotal);
		    	$("#discount").val(discountAmount);
		    }
		    else{
		    	discountAmount = removeDiscountTotal;
		    	$("#discount").val(discountAmount);
		    }
		    
		    
		    
		    var totalAmount =  $("#totalAmount").val();
		    if(totalAmount != null && totalAmount != 'undefined' && totalAmount != ''){
		    	totalAmount = parseFloat(totalAmount)-parseFloat(removeMrpTotal);
		    	$("#totalAmount").val(totalAmount);
		    }
		    else{
		    	totalAmount = 0;
		    	$("#totalAmount").val(totalAmount);
		    }
		    
		    
		    var grossAmount =  $("#grossAmount").val();
		    if(grossAmount != null && grossAmount != 'undefined' && grossAmount != ''){
		    	grossAmount = parseFloat(grossAmount)-parseFloat(removeTotal);
		    	$("#grossAmount").val(grossAmount);
		    }
		    else{
		    	grossAmount = 0;
		    	$("#grossAmount").val(grossAmount);
		    }
		    evaluateDiscountOnInvoice(grossAmount);
		    $("#noOfProductsSpan").html(totalRow-1);
		    $("#totalAmountSpan").html(totalAmount);
		    $("#grossAmountSpan").html(grossAmount);
		    $("#discountSpan").html(discountAmount);
		    $("#taxSpan").html(taxAmount);
		   
		    
		   var  rId = "divTsRow"+rowId;
		   var r = document.getElementById("data").rows[rId];
           var count = 2*(r.cells.length);
		    for(i=0;i<=count;i++){
		        r.deleteCell(0);
		    } 
			   
		   
	}
</script>
<script type="text/javascript">
  function prepareBillSetup(){
	  var operoxUrl ='${operoxUrl}';
	  $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/prepareBillSetup?${_csrf.parameterName}=${_csrf.token}", 	   
 		    success: function(data) {
 		    	 var json = JSON.parse(data);
 		         var result = "";
 		         if(json.length > 0)
 		   		 {
 		        	for(var i=0; i<json.length; i++){
 		        		 var user = json[i];
 	 		        	
 	 		        	 $("#billNumber").val(user.billNumber);
 	 		    		 $("#billNumberSpan").html(user.billNumber);
 	 		    		 $("#billDate").val(user.billingDate);
 	 		    		 $("#billDateSpan").html(user.billingDate);
 	 		    		 
 	 		    		 $("#billStoreId").val(user.store.id);
 	 		    		 $("#billStoreSpan").html(user.store.storeName);
 	 		    		 
 	 		    		 $("#billLocation").val(user.storeAddress);
 	 		    		 if(user.storeAddress != null && user.storeAddress != 'undefined' && user.storeAddress != ''){
 	 		    			 $("#billLocationSpan").html(user.storeAddress);
 	 		    		 }
	 		    		 $("#cashierUserId").val(user.id);
	 		    		 var fullName = user.firstName;
	 		    		 if(user.lastName != null && user.lastName != 'undefined' && user.lastName != ''){
	 		    			fullName = fullName+" "+user.lastName;
	 		    		 }
	 		    		 $("#cashierSpan").html(fullName);
	 		    		 $("#cashierShiftId").val(user.shift.id);
	 		    		 $("#cashierShiftSpan").html(user.shift.shiftName);
	 		    		 
	 		    		var counterNumber = $("#counterNumber").val();
	 		    		if(counterNumber != null && counterNumber != 'undefined' && counterNumber != ''){
	 		    			 $("#counterNumberSpan").html(counterNumber);
	 		    		}
	 		    		   
 		        	}
 		   		 }
 		    }
	  }); 
  }

</script>
<script type="text/javascript">
  function cancelBill(){
		$("#barCode").val('');
		$("#prodCode").val('');
		$("#productName").val('');
		 
		$("#customername").val('');
		$("#customerPhone").val('');
		
		$("#noOfProducts").val('');
		$("#totalAmount").val('');
		$("#grossAmount").val('');
		$("#billAmount").val(''); 
		$("#actualBillAmount").val(''); 
		$("#tax").val('');
		$("#discount").val('');
		
		$("#noOfProductsSpan").html('0');
		$("#totalAmountSpan").html('0');
		$("#grossAmountSpan").html('0');
		$("#billAmountSpan").html('0'); 
		$("#actualBillAmountSpan").html('0'); 
		$("#taxSpan").html('0'); 
		$("#discountSpan").html('0'); 
		var counterNumber = $("#counterNumber").val();
		prepareBillSetup();
		
		if(counterNumber != null && counterNumber != 'undefined' && counterNumber != ''){
			 $(".counter_mask").removeAttr('style');
			 $(".counter_pop").removeAttr('style');
			 $(".counter_mask").attr('style','display:none;');
			 $(".counter_pop").attr('style','display:none;');
		}
		else{
			 $(".counter_mask").removeAttr('style');
			 $(".counter_pop").removeAttr('style');
			 $(".counter_mask").attr('style','display:block;');
			 $(".counter_pop").attr('style','display:block;');
		}
		
		 $("#data").find("tr:gt(0)").remove();
		 $("#maxProdRowNum").val(1);
  }
</script>
<script type="text/javascript">
function getProductsByProductName(){
	        var operoxUrl = '${operoxUrl}';
	        var productName = $("#productName").val();
	        if(productName != null && productName != 'undefined' && productName != ''){
	        	$.ajax({ 
			    	type: "POST",
			    	url: operoxUrl+"/getProductsByProductName?${_csrf.parameterName}=${_csrf.token}&productName="+productName, 
			        success: function(data) {
			        	var json = JSON.parse(data);
			        	var projects = [];
			        	for(var i=0;i<json.productStockList.length;i++){
			        		 var prodName = json.productStockList[i][0];
	   	 			         var barCode = json.productStockList[i][1].barCode;
	   	 			         var prodCode = json.productStockList[i][1].productId.productCode; 
	   	 			         var prodStock = json.productStockList[i][1];
			        	     tmp = {
			        	        'label': prodName,
			        	        'barCode': barCode,
			        	        'prodCode': prodCode,
			        	        'prodStock': prodStock,
			        	      };
			        	     projects.push(tmp);
			        	}
			        	$("#productName").autocomplete({
			        		 minLength: 0,
			                 source: projects,
			                 focus: function( event, ui ) {
			                        $( "#productName" ).val( ui.item.label);
			                        return false;
			                    },
			                 select: function( event, ui ) {
			                     $('#barCode').val(ui.item.barCode);
			                     $('#prodCode').val(ui.item.prodCode);
			                     addProduct(ui.item.prodStock)
			                     return false;
			                 }  
			              })
			        },
			        error: function(e){
			        }
			    });
	        }
	        else{
	        	     $("#barCode").val('');
		    		 $("#prodCode").val('');
	        }
			
	}
</script>
<script type="text/javascript">
$("#barCode").keypress(function(event) {
    if (event.which == 13) {
	        var operoxUrl = '${operoxUrl}';
	        var barCode = $("#barCode").val();
	        if(barCode != null && barCode != 'undefined' && barCode != ''){
	        	$.ajax({ 
			    	type: "POST",
			    	url: operoxUrl+"/getProductsByBarCode?${_csrf.parameterName}=${_csrf.token}&barCode="+barCode, 
			        success: function(data) {
			        	var json = JSON.parse(data);
			        	for(var i=0;i<json.productStockList.length;i++){
			        		 var prodName = json.productStockList[i][0];
	   	 			         var barCode = json.productStockList[i][1].barCode;
	   	 			         var prodCode = json.productStockList[i][1].productId.productCode; 
	   	 			         var prodStock = json.productStockList[i][1];
	   	 			         $('#productName').val(prodName);
	                         $('#prodCode').val(prodCode);
	   	 			         addProduct(prodStock)
			        	}
			        },
			        error: function(e){
			        }
			    });
	        }
	        else{
	        	     $("#productName").val('');
		    		 $("#prodCode").val('');
	        }
	}
});
</script>

<script type="text/javascript">
$("#prodCode").keypress(function(event) {
       if (event.which == 13) {
	        var operoxUrl = '${operoxUrl}';
	        var prodCode = $("#prodCode").val();
	        if(prodCode != null && prodCode != 'undefined' && prodCode != ''){
	        	$.ajax({ 
			    	type: "POST",
			    	url: operoxUrl+"/getProductsBySearchCode?${_csrf.parameterName}=${_csrf.token}&prodCode="+prodCode, 
			        success: function(data) {
			        	var json = JSON.parse(data);
			        	for(var i=0;i<json.productStockList.length;i++){
			        		 var prodName = json.productStockList[i][0];
	   	 			         var barCode = json.productStockList[i][1].barCode;
	   	 			         var prodCode = json.productStockList[i][1].productId.productCode; 
	   	 			         var prodStock = json.productStockList[i][1];
	   	 			         $('#barCode').val(barCode);
	                         $('#productName').val(prodName);
	   	 			         addProduct(prodStock)
			        	}
			        },
			        error: function(e){
			        }
			    });
	        }
	        else{
	        	     $("#barCode").val('');
		    		 $("#productName").val('');
	        }
     }
});
</script>
<script>
function getOnHoldList(local){
	  $(".pickInvoice_pop").fadeOut(200);
	   var billId = local.value;
	   var operoxUrl ='${operoxUrl}';
	    // $("#data").find('tbody').empty();
	  
		$("#billNumber").val('');
		$("#billNumberSpan").val('');
		
	    $("#noOfProducts").val('');
		$("#totalAmount").val('');
		$("#grossAmount").val('');
		$("#billAmount").val(''); 
		$("#actualBillAmount").val(''); 
		$("#tax").val('');
		$("#discount").val('');
		
		$("#noOfProductsSpan").html('0');
		$("#totalAmountSpan").html('0');
		$("#grossAmountSpan").html('0');
		$("#billAmountSpan").html('0'); 
		$("#actualBillAmountSpan").html('0'); 
		$("#taxSpan").html('0'); 
		$("#discountSpan").html('0'); 
		$("#data").find("tr:gt(0)").remove();
	  $.ajax({
		   	type: "POST",
		    url: operoxUrl+"/getonHoldBillItems?${_csrf.parameterName}=${_csrf.token}&billId="+billId, 	   
		    success: function(data) {
		    	var json = JSON.parse(data);
				var result = "";
				if(json.length > 0)
			   	{
					$("#maxProdRowNum").val(json.length+1);
					
					/* To increase maxProdRowNum with the exisitng vlaue
					var checkMaxTsRowNum = $("#maxProdRowNum").val();
				    $("#maxProdRowNum").val(parseInt(checkMaxTsRowNum) + parseInt(json.length));
				    var newval = $("#maxProdRowNum").val(); */
				    
				  for (var i=0; i<json.length; i++){
					 
					  var report = json[i];
					  var isClassHighlight = "";
					  if(i==0){
						  var numberOfProducts = report.billId.numberOfProducts;
						  $("#noOfProducts").val(numberOfProducts);
						  $("#noOfProductsSpan").html(numberOfProducts);
						  
						  var totalAmount = report.billId.totalAmount;
						  $("#totalAmount").val(totalAmount);
						  $("#totalAmountSpan").html(totalAmount);
						  
						  var grossAmount = report.billId.grossAmount;
						  $("#grossAmount").val(grossAmount);
						  $("#grossAmountSpan").html(grossAmount);
						  
						  var billAmount = report.billId.billAmount;
						  $("#billAmount").val(billAmount);
						  $("#billAmountSpan").html(billAmount);
						  
					      var actualBillAmount = 0;
					      if(report.billId.actualBillAmount){
					    	  actualBillAmount = parseFloat(report.billId.actualBillAmount)
					    	  $('#actualBillAmount').val(actualBillAmount);
						      $('#actualBillAmountSpan').html(actualBillAmount);
					      }
					      if(report.billId.currency){
					    	  var currencyId = report.billId.currency.id;
					    	  $('#currencyId').val(currencyId);
					    	  $("#currencyType option[id='"+currencyId+"']").attr('selected', 'selected');
					      }
						  
						  var tax = report.billId.tax;
						  $("#tax").val(tax);
						  $("#taxSpan").html(tax);
						  
						  var discount = report.billId.discount;
						  $("#discount").val(discount);
						  $("#discountSpan").html(discount);
						  
						  var billNumber = report.billId.billNumber;
						  $("#billNumber").val(billNumber);
						  $("#billNumberSpan").html(billNumber);
						  isClassHighlight ="class='highlight'";
						  
					  }
					  //<div class='ta-sm'>
					  
					  result = result + '<tr  id="divTsRow'+(i+1)+'">';
					  result = result + '<td><div class="ta-m">'+(i+1)+'</div></td>';
					  result = result + '<td><input type="hidden" id="'+(i+1)+'productStockId" name="'+(i+1)+'productStockId" value='+report.productStockId.id+' />'; 
					  result = result + '<div class="ta-m">'+report.productName+'</div></td>';
					  result = result + '<td><div class="ta-sm" id="'+(i+1)+'quantitySpan">'+report.quantity+'</div>';
					  result = result + '<input type="hidden" id="'+(i+1)+'itemId" name="'+(i+1)+'itemId" value="'+report.id+'" />';
					  result = result + '<input type="hidden" id="'+(i+1)+'quantity" name="'+(i+1)+'quantity" value="'+report.quantity+'" />';
					  result = result + '<input type="hidden" id="'+(i+1)+'maxQuantity" name="'+(i+1)+'maxQuantity" value="'+report.maxQuantity+'" />';
					  result = result + '</td>';
					  
					  result = result + '<td><div class="ta-sm">'+report.price+'</div>';
					  result = result + '<input type="hidden" id="'+(i+1)+'unitPrice" name="'+(i+1)+'unitPrice" value="'+report.price+'" />';
					  result = result + '</td>';
					  
					  result = result + '<td><div class="ta-sm">'+report.tax+'</div>';
					  result = result + '<input type="hidden" id="'+(i+1)+'taxOnItem" name="'+(i+1)+'taxOnItem" value="'+report.tax+'" />';
					  result = result + '</td>';
					  
					  result = result + '<td><div class="ta-sm">'+report.discount+'</div>';
					  result = result + '<input type="hidden" id="'+(i+1)+'discountOnItem" name="'+(i+1)+'discountOnItem" value="'+report.discount+'" />'; 
					  result = result + '</td>';
					  result = result + '<td> <div class="ta-sm" id="'+(i+1)+'totalSpan">'+report.productTotalAmount+'</div>';
					  result = result + '<input type="hidden" id="'+(i+1)+'total" name="'+(i+1)+'total" value="'+report.productTotalAmount+'" />'; 
					  result = result + '</td>';
					  
					  result = result + '</tr>';
				  }
				    //$("#tdata").find('tbody').append(result); 
				  $("#data").append(result); 
				 
		    }
		  }
	  });
}
</script>
<script type="text/javascript">
   function changeCurrencyType(){
	   var currencyType = $('#currencyType').val();
	   var billAmount = $('#billAmount').val();
	   if(billAmount != null && billAmount != 'undefined' && billAmount != ''){
		   billAmount = parseFloat(billAmount);
	   }
	   else{
		   billAmount = 0;
	   }
	   
	   if(currencyType != null && currencyType != 'undefined' && currencyType != ''){
		   currencyType = parseFloat(currencyType);
	   }
	   else{
		   currencyType = 0;
	   }
	   var currencyId =  $("#currencyType option:selected").attr("id")
	   if(currencyId != null && currencyId !='' && currencyId != 'undefined'){
		  $('#currencyId').val(currencyId);
	   }  
	   var actualBillAmount = billAmount*currencyType;
	   $('#actualBillAmount').val(actualBillAmount);
	   $('#actualBillAmountSpan').html(actualBillAmount);
   }

</script>
<jsp:include page="../masterFooter.jsp" /> 
