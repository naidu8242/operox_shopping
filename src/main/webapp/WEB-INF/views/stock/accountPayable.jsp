<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<script src="${operoxUrl}/resources/js/formvalidator.js"></script>
<link rel="stylesheet" href="${operoxUrl}/resources/css/chosen.min.css">
<script src="${operoxUrl}/resources/js/chosen.jquery.min.js"></script> 
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<div class="wraper clearfix">
<!-- Header Area -->

<script type="text/javascript">
  $( function() { 
    $( "#dateOfPay" ).datepicker(); 
    $(".datefild" ).datepicker();
  });
</script>

<!-- this script for get -->


<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Received Product Payment</h2>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Received Product Payment</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix">
   <form:form id="accountPayable_form" name="accountPayable_form" method="post">
    <div class="div33">
       <label class="labl">Received Stock Id</label>
       <span>
       <input type="text" placeholder="Received Stock Id" name="" value="${receivedStock.id}">
       </span>
    </div>
    
     <div class="div33">
       <label class="labl">Received Stock Date</label>
       <span>
       <input type="text"  placeholder="DD-MM-YYYY" class="datefild" id="dateOfPay" name="dateOfPay" value=""  field-name="Day Of Pay">
       </span>
    </div>
    
        
     <div class="div33">
       <label class="labl">Supplier</label>
       <span>
       <select class="chosen-select" name="" id="" >
          <option>${receivedStock.supplier.name}</option>
         </select>
       </span>
    </div>
    
      <div class="div33">
       <label class="labl">Invoice Number </label>
       <span>
       <input type="text" placeholder="Invoice Number" name="" id="" value="${supplierInvoiceNumber}">
       </span>
    </div>
    <div class="clearfix"></div>
        <div class="div33">
        <label class="labl">Number of Products</label>
        <span>
        <input type="text" placeholder="Number of Products">
        </span>
        </div>
        
      <div class="div33">
        <label class="labl">Discount</label>
        <span>
        <input type="text" placeholder="Discount" value="${receivedStock.discount}">
        </span>
        </div>
      <div class="div33">
       <label class="labl">Total Amount</label>
       <span>
       <input type="text" placeholder="Total Amount" value="${receivedStock.totalAmount}">
       </span>
    </div>
    <div class="div33">
       <label class="labl">Payment</label>
       <span>
        <select name="paymentStatus" id="paymentStatus">
            <option selected disabled>--Select Payment --</option>
            <option value="Part Payment">Part Payment</option>
            <option value="Full Payment">Full Payment</option>
            <option value="Spot Payment">Spot Payment</option>
         </select>
       </span>
    </div>
    <div class="div33">
       <label class="labl">Payment Mode</label>
       <span>
        <select name="paymentMode" id="paymentMode">
          <option selected disabled>--Select Payment Mode --</option>
          <option value="Cheque">Cheque</option>
          <option value="Transfer">Transfer</option>
          <option value="Card">Card</option>
         </select>
       </span>
    </div>
    <div class="clearfix"></div>
      <!-- Content Area inner -->
  </div>
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right custom-buttons">
         <a href="<c:url value='/receivedItemDashboard'/>" id="payment" class="btn btn-primary"  onclick='storeAccountPayable();'>
           Pay
          </a>
         <a href="<c:url value='/receivedItemDashboard'/>" id="can" class="btn btn-default">
           Cancel
          </a>
        </div>
      </div>
    </div>
     </form:form>
</section>
<!-- Content Area Ends--> 

<script type="text/javascript">
  $( function() {
    $( "#mfddate, #expdate, #rsDate" ).datepicker();
  });
</script>

<script type="text/javascript">

    function storeAccountPayable() {
    	
    	 if ($('#accountPayable_form').validate(false, validationSettings)){
            var frm = $('#accountPayable_form').serializeFormJSON();
            var con = JSON.stringify(frm);
            con = con.replace(/[{}]/g, "");
              //It will escape all the special characters
            var operoxUrl = '${operoxUrl}';
            $.ajax({
                        type : "POST",
                        url : operoxUrl+"/saveAccountPayable?${_csrf.parameterName}=${_csrf.token}&json="+con,
                        		
                        success : function(result) {
                                location.replace(operoxUrl+"/suppliers");
                        },
                    });
            return true;
    	  }else{
  		return false;
  	  }
      }
   
     $('body').on('blur', '#accountPayable_form', function() {
  		$("#accountPayable_form").showHelpOnFocus();
  		$("#accountPayable_form").validateOnBlur(false, validationSettings);  
     });
    
   
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

<jsp:include page="../masterFooter.jsp" />