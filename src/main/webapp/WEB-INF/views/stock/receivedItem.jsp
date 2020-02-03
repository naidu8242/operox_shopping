<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>




<script>
$(document).ready(function() {	
	getSuppliersByPodId("");
});
</script> 

<script type="text/javascript">
  $( function() { 
    $( "#deliveryDate" ).datepicker(); 
    $(".datefild" ).datepicker();
  });
</script>

<form id="receivedProduct_form" name="receivedProduct_form" method="post">
<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Received Stock</h2>
    </div>
    <div class="pull-right brud-crum"><a href="dashboard">Home</a> &raquo;<a href="receivedItemDashboard">Received Stock List</a> &raquo; Add Stock</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix">
  	<div id="loading"></div>
	  <div class="div33">
	       <label class="labl">Received Id</label>
	       <span>
	       <input type="text" placeholder="Received Id" readonly="readonly" name="receivedNumber" value="${receivedNumber}" field-name="Received Id" data-validation="validate_Space validate_Alphanumeric" data-validation-optional="true">
	       </span>
	  </div>
    
      <div class="div33">
       <label class="labl"><abbr title="P.O Id" >P.O Id</abbr></label>
       <span>
       
       <select class="chosen-select" onchange="getPurchaseOrderItems(this);getSuppliersByPodId(this);" name="podId" id="podId" field-name="Select Purchase order Id" data-validation="validate_Space" data-validation-optional="true">
		     <option value="">-- Select P.O Id  --</option>
				<c:forEach var="purchaseOrder" items="${purchaseOrderList}">
					<option value="${purchaseOrder.id}">${purchaseOrder.purchaseNumber}</option>
				</c:forEach>
		  </select> 
       
       </span>
    </div>
    
    <div class="div33">
       <label class="labl"> Date &nbsp;<b class= "text-danger" >*</b></label>
       <span>
       <!-- <input type="text" placeholder="Delivery Date::Datepicker" name="deliveryDate"> -->
       <input type="text"  placeholder="DD-MM-YYYY" class="datefild" id="deliveryDate" name="deliveryDate" value=""  field-name="delivery Date" data-validation="required" >
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Location &nbsp;<b class= "text-danger" >*</b></label>
       <span> 
          <select class="chosen-select" name="storeId" id="storeId" field-name="Select Location" data-validation="required" >
										<option value="">-- Select Location --</option>
										<c:forEach var="store" items="${storeList}">
											<option value="${store.id}">${store.storeName}</option>
										</c:forEach>
		  </select> 
       </span>
    </div>
     <div class="div33">
       <label class="labl">Recieve Type &nbsp;<b class= "text-danger" >*</b></label>
        <span> 
              <select class="chosen-select" id="recieveTypeId" data-validation="required"  onchange="recieveType();">
                       <option value="fg">Select Recieve Type</option>
                       <option value="wareType">Ware House</option>
                       <option value="suppliertype">Supplier</option>
               </select> 
        </span>
    </div>
    <div id="supplierRecieveType">
    <div class="div33">
       <label class="labl">Supplier</label>
       <span id="purchaseOrderId">
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Supplier Invoice No &nbsp;<b class= "text-danger" >*</b></label>
       <span>
       <input type="text" placeholder="Supplier Invoice Number" id="supplierInvoiceNumber" name="supplierInvoiceNumber" field-name="Supplier Invoice Number" data-validation="required">
       </span>
    </div>
    </div>
    <div class="div33" id ="wareHouseRecieveType">
       <label class="labl" >Warehouse</label>
       <span>
        <select class="chosen-select" name="warehouse" id="warehouse" field-name="Select Warehouse" data-validation="validate_Space" data-validation-optional="true">
			<option value="">-- Select Store --</option>
		    <c:forEach var="store" items="${wareHouseList}">
				<option value="${store.id}">${store.storeName}</option>
			</c:forEach> 
		</select> 
		  
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Payment Status</label>
       <span>
       <select name="paymentStatus">
          <option selected disabled>--Select Payment Status --</option>
          <option>Payment Done</option>
          <option>Payment in progress</option>
         </select>
       </span>
    </div>
    
    <div class="div33">
       <label class="labl"></label>
       <span>
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">BarCode</label>
       <span>
       <input type="text" placeholder="Barcode" field-name="BarCode" data-validation="validate_Space" data-validation-optional="true">
       </span>
    </div>
    
    
    <div class="div33">
       <label class="labl">Product Name</label>
       <span>
          <input type="hidden" id="maxTsRowNum" name="maxTsRowNum" value="1" />
          <select class="chosen-select" onchange="addProduct(this);" name="productId" id="productId" field-name="Select Product Name" data-validation="validate_Space" data-validation-optional="true">
						<option value="">-- Select Store --</option>
						<c:forEach var="product" items="${productList}">
							<option value="${product.id}&${product.productName}">${product.productName}</option>
						</c:forEach>
		 </select> 
       </span>
    </div>  
    <div class="div33">
       <label class="labl">Search Code</label>
       <span>
         <select class="chosen-select" onchange="addProduct(this);" id="productId" value="" data-validation="validate_Space" data-validation-optional="true" field-name="Search Code">
          				<option value="">-- Select Search Code --</option>
						<c:forEach var="product" items="${productList}">
							<option value="${product.id}&${product.productCode}">${product.productCode}</option>
						</c:forEach>
         </select>
       </span>
    </div>
    <div class="clearfix"></div>
  <!-- Content Area inner -->
  <!-- Table Designa addstock -->
  <div class="clearfix">
    <div class="data_table more-table">
    	<table class="table table-bordered table-striped  clearfix" id="productTbl">
        		<!-- <tbody> -->
            	<tr>
                	<th>Sl.No</th>
                    <th>Barcode</th>
                    <th>Product Name</th>
                    <th>Batch Id&nbsp;&nbsp;<span class="text-danger">*</span></th>
                    <th>Manufature Date&nbsp;&nbsp;<span class="text-danger">*</span></th>
                    <th>Expire Date&nbsp;&nbsp;</th>
                    <th>Qty / Weight&nbsp;&nbsp;<span class="text-danger">*</span></th>
                    <th>Product Price&nbsp;&nbsp;<span class="text-danger">*</span></th>
                    <th>Discount on Product</th>
                    <th>Tax on Product</th>
                    <th>Mrp on Product</th>
                    <th>Total Amount</th>
                    <th class="action">Action</th>
                </tr>
                <%-- <c:if test="${!empty receivedStockItemsList}">
		               <c:forEach items="${receivedStockItemsList}" var="receivedItem" varStatus="atId">
	                        		<tr id="divTsRow${atId.index+1}">
	                        			<td>
											<input type="hidden" id="${atId.index+1}rowNumber"
												name="${atId.index+1}rowNumber" value="${receivedItem.id}" /> 
											<span>${receivedItem.id}</span>
										</td>
	                        		<td>
									   <div class="tbldata-dotdot-me">
										   <input type="text" id="${atId.index+1}barCode"
												  name="${atId.index+1}barCode" value="${receivedItem.id}" /> 
									   </div>
								    </td>
								    
									<td>
										<div class="tbldata-dotdot-me">
									    <input type="hidden" id="${atId.index+1}productName"
											name="${atId.index+1}productName" value="${receivedItem.id}" /> 
										<span>${receivedItem.id}</span>
										</div>
									</td>
									<td>
									    <input type="text" id="${atId.index+1}batchId"
											name="${atId.index+1}batchId" value="${receivedItem.id}" /> 
									</td> 
									<td>
									    <input type="text" id="${atId.index+1}manuFatureDate"
									           name="${atId.index+1}manuFatureDate" placeholder="DD/MM/YYYY" class="datefild" >
									</td> 
									
									<td>
									   <input type="text" id="${atId.index+1}expiredate"  
									          name="${atId.index+1}expiredate" placeholder="DD/MM/YYYY" class="datefild" >
									</td>   
									
									<td>
									    <input type="text" id="${atId.index+1}quantity" 
											name="${atId.index+1}quantity" value="${receivedItem.id}" /> 
									</td>   
									
									<td>
									    <input type="text" id="${atId.index+1}productPrice" 
											name="${atId.index+1}productPrice" value="" /> 
									</td>
									
									<td>
									    <input type="text" id="${atId.index+1}discount" 
											name="${atId.index+1}discount" value="" /> 
									</td>
									
									<td>
									    <input type="text" id="${atId.index+1}tax" name="${atId.index+1}tax" value="${atId.index+1}" />
									</td>
									
									<td>
											<div id="${atId.index+1}mrp" name="${atId.index+1}mrp"  class="tbldata-dotdot-vsm"><span>${atId.index+1}</span></div>
									</td>
									 
									<td>
											<div id="${atId.index+1}totalAmount" name="${atId.index+1}totalAmount"  class="tbldata-dotdot-vsm"><span>${atId.index+1}</span></div>
									</td>
									  
									<td>
											<div class="tbldata-dotdot-vsm"> <a href="#" onclick="removeProduct(${atId.index+1});"><i class="glyphicon glyphicon-trash deme"></i></a>
									</td>
								  </tr>  
								    
								    
		              </c:forEach>
		     </c:if> --%>
        </table>
    </div> 
  </div>
 
 <!-- =============================== AddProduct Divs  ============================ -->
<div id="divTaskTd1" style="display: none; float: left;">
   <!--  <input type="hidden" id="REPLACEROWIDrowNumber"
		name="REPLACEROWIDrowNumber" value="ROWNUMBERVALUE" />  -->
	<!--<span> ROWNUMBERVALUE</span> -->
	    ROWNUMBERVALUE
</div>  
 <div id="divTaskTd2" style="display: none; float: left;">
    <input type="hidden" class="PRICECALCULATION" id="REPLACEROWID" /> 
    <input type="text" id="REPLACEROWIDbarCode" name="REPLACEROWIDbarCode" value="BARCODEVALUE" /> 
</div>
 <div id="divTaskTd3" style="display: none; float: left;">  
    <input type="hidden" id="REPLACEROWIDitemProductName"
		name="REPLACEROWIDitemProductName" value="PRODUCTNAMEVALUE" /> 
  <input type="hidden" id="REPLACEROWIDproductId" name="REPLACEROWIDproductId" value="PRODUCTNAMEVALUE" />
	<span>PRODUCTNAME</span>
</div>
<div id="divTaskTd4" style="display: none; float: left;">
    <input type="text" id="REPLACEROWIDbatchId"
		name="REPLACEROWIDbatchId" value="BATCHIDVALUE" /> 
</div> 

<div id="divTaskTd5" style="display: none; float: left;">
    <input type="text" id="REPLACEROWIDmanuFatureDate"  name="REPLACEROWIDmanuFatureDate" placeholder="DD/MM/YYYY" class="datefild" >
</div> 

<div id="divTaskTd6" style="display: none; float: left;">
   <input type="text" id="REPLACEROWIDexpiredate"  name="REPLACEROWIDexpiredate" placeholder="DD/MM/YYYY" class="datefild" >
   <!-- <input type="text" placeholder="DD/MM/YYYY" class="datefild" id="expdate"> -->
</div>    

<div id="divTaskTd7" style="display: none; float: left;">
    <input type="text" id="REPLACEROWIDquantity" 
		name="REPLACEROWIDquantity" value="QUANTITYVALUE" /> 
</div>  

<div id="divTaskTd8" style="display: none; float: left;">
    <input type="text" id="REPLACEROWIDproductPrice" 
		name="REPLACEROWIDproductPrice" value="" /> 
</div> 

<div id="divTaskTd9" style="display: none; float: left;">
    <input type="text" id="REPLACEROWIDdiscount"   
		name="REPLACEROWIDdiscount" value="" /> 
</div> 


<div id="divTaskTd10" style="display: none; float: left;">
   <!--  <input type="text" id="REPLACEROWIDtax"   name="REPLACEROWIDtax" value="" /> --> 
	<select id="REPLACEROWIDtax" name="REPLACEROWIDtax" field-name="Tax" >
		        <option value="">---Select tax--</option>
		     	<c:forEach var="tax" items="${taxList}" >
		     		<option value="${tax.id}&${tax.taxValue}">${tax.taxName}-${tax.taxValue}</option>	
				</c:forEach>
   </select>
</div>   

<div id="divTaskTd11" style="display: none; float: left;">
        <input type="text" id="REPLACEROWIDmrp" readonly='readonly'
		name="REPLACEROWIDmrp" value="MRPVALUE" /> 
</div> 
 
<div id="divTaskTd12" style="display: none; float: left;">
		<input type="text" class="totalAmountClass" id="REPLACEROWIDtotalAmount" readonly='readonly'
		name="REPLACEROWIDtotalAmount" value="TOTALAMOUNTVALUE" /> 
</div>
  
<div id="divTaskTd13" style="display: none; float: left;">
		<div class="tbldata-dotdot-vsm"><a href="#" onclick="removeProduct(REPLACEROWID);"><i class="glyphicon glyphicon-trash deme"></i></a>
</div>
</div>

<!-- =============================== AddProduct Divs  ============================ -->
 
  <!-- Table Designa addstock ends -->
        <div class="clearfix mt25"></div>
     
        <div class="div50">
       	 <label class="labl">Number of Products:</label>
            <span>
            <input type="text" placeholder="Number of Products"  name="numberOfPackages" id="numberOfProducts" class="disabled">
            </span>
        </div>

        <div class="div50">
       	 <label class="labl">Amount</label>
            <span>
            <input type="text" placeholder="Amount" name="amount" id="amount">
            </span>
        </div>
        
        <div class="div50">
       	 <label class="labl">Receiving User&nbsp;<b class= "text-danger" >*</b></label>
            <span>
             <select class="chosen-select"  id="receivingUsers" name="receivingUsers" value="" data-validation="required" field-name="Receiving User">
			          <option value=""  selected >--Select User--</option>
			          <c:forEach var="user" items="${userList}">
					     <option value="${user.id}">${user.username}
						</option>
					 </c:forEach>
		       </select>
            </span>
        </div>
        
        <div class="div50">
       	 <label class="labl">Tax</label>
            <span>
             <select class="chosen-select"  name="tax" id="grandTax" field-name="Select Tax" data-validation-optional="true" >
					<option value="">-- Select Tax --</option>
					<c:forEach var="tax" items="${taxList}">
						<option value="${tax.id}&${tax.taxValue}">${tax.taxName} - ${tax.taxValue}</option>
					</c:forEach>
		 	</select> 
             
            </span>
        </div>
        
        <div class="div50">
       	 <label class="labl">Comment</label>
            <span>
             <textarea rows="2" placeholder="Comment" name="comment"></textarea>
            </span>
        </div>

        <div class="div50">
       	 <label class="labl">Discount</label> 
            <div class="div33 div33-custom80">
            <span>
             <input type="text" placeholder="Discount" name="discount" id="grandDiscount">
            </span>
            </div>
            <div class="div4 div4-custom35">
            <span>
             <select name="discountType" id="grandDiscountType">
             <!--  <option disabled selected>--Select Discount Type--</option> -->
              <option selected  value="Percentage" >Percentage</option>
               <option selected  value="Value" >Value</option>
             </select>
            </span>
            </div>
        </div>
                <div class="div50">
       	 <label class="labl">Total Amount</label>
            <span>
             <input type="text" placeholder="Total Amount" name="totalAmount" id="grandTotalAmount">
            </span>
        </div>
     
      
  </div>
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right custom-buttons">
          <!-- <a href="#" onclick="saveReceivedItems('draft');" id="draft" class="btn btn-danger">
           <i class="glyphicon glyphicon-pencil"></i>Draft
          </a> -->
          <!--  <a href="#" id="sp" class="btn btn-primary">
           <i class="glyphicon glyphicon-print"></i>Save & Print
          </a> -->
          <a href="#" onclick="saveReceivedItems('payment');" id="payment" class="btn btn-primary">
           <i class="glyphicon glyphicon-usd"></i>Payment
          </a>
          <a href="#" onclick="saveReceivedItems('save');"  id="save" class="btn btn-success">
           <i class="glyphicon glyphicon-floppy-disk"></i>Save
          </a>
         <a href="<c:url value='/receivedItemDashboard'/>" id="can" class="btn btn-default">
           <i class="glyphicon glyphicon-remove"></i>Cancel
          </a>
        </div>
      </div>
    </div>
</section>
</form>
<!-- Content Area Ends--> 

 <jsp:include page="../masterFooter.jsp" />
 
 

<script type="text/javascript">
	function recieveType() {
		 var recieveType = document.getElementById("recieveTypeId").value;
		 if(recieveType!=null && recieveType!='' && recieveType!="undefined" && recieveType =='wareType'){
			 document.getElementById("supplierRecieveType").style.display = 'none';
			 document.getElementById("wareHouseRecieveType").style.display = 'block';
			 $('#supplierInvoiceNumber').removeAttr('data-validation');
			 $('#supplierId').removeAttr('data-validation');
		 }else if(document.getElementById("supplierRecieveType").style.display == 'none' && recieveType =='suppliertype' ){
			       document.getElementById("wareHouseRecieveType").style.display = 'none';
			       document.getElementById("supplierRecieveType").style.display = 'block';
				   $('#warehouse').removeAttr('data-validation');
		 }
	}
</script>


 <script type="text/javascript">
	function saveReceivedItems(submissionType) {  
		var validationSettings = {
			errorMessagePosition : 'element',
			borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd'
		};
		if ($('#receivedProduct_form').validate(false, validationSettings)) { 
			var frm = $('#receivedProduct_form').serializeFormJSON();
			$("#save").attr("disabled", "disabled");
			$("#save").addClass('button').val('Processing..');
			$("#loading").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("resources/images/loading.gif"); background-position:center; background-repeat:no-repeat; background-size:50px;');
			var con = JSON.stringify(frm);
			con = con.replace(/[{}]/g, "");
			var jsonData = encodeURIComponent(con);
			var operoxUrl = '${operoxUrl}';
			/* $("#save").attr("disabled", "disabled");
			$("#save").addClass('button').val('Processing..'); */
			$.ajax({
						type : "GET",
						url : operoxUrl+"/saveReceivedProduct?json="+jsonData+"&submissionType="+submissionType,
						success : function(result) {
							if(submissionType == "payment"){
								var url = operoxUrl+"/accountPayable?stockReceivedId="+result;
								location.replace(url);
							}else{
								location.replace(operoxUrl+"/receivedItemDashboard");
							} 
						},
		     }); 
			return true;
		} else {
			return false;
		}
	}
	$('body').on('blur', '#project_form', function() {
		$("#receivedProduct_form").showHelpOnFocus();
		$("#receivedProduct_form").validateOnBlur(false, validationSettings);
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

<script type="text/javascript">
	function addProduct(local) {
		if(local.value != null && local.value != 'undefined' && local.value != ''){
			
		var products = [];
		products = local.value.split("&");
		var productId = products[0];
		var productName = products[1];
		
		var quantity = 1;
		var checkMaxTsRowNum = $("#maxTsRowNum").val();
		
		for(var i = 1;i<checkMaxTsRowNum;i++){
	    	var productIdValue =  $("#"+i+"productId").val();
	    	if(productIdValue != null && productIdValue != 'undefined' && productIdValue != '' && productIdValue == productId){
	    		var currentRowQuantity = $("#"+i+"quantity").val();
	    		var changedQuantity  = parseInt(currentRowQuantity)+parseInt(quantity)
	    		$("#"+i+"quantity").val(changedQuantity);
	    		return;
	    	}
	    }
		var tbl = document.getElementById("productTbl");
		var maxRowNum = parseInt($("input[name = 'maxTsRowNum']").val());
		$("input[name = 'maxTsRowNum']").val(maxRowNum + 1);
		
		var row = tbl.insertRow(maxRowNum);
		row.setAttribute("id", "divTsRow" + maxRowNum);

		var cell0 = row.insertCell(0);
		var divTsTd1Str = document.getElementById("divTaskTd1").innerHTML;
		divTsTd1Str = divTsTd1Str.replace(/REPLACEROWID/g, (maxRowNum));
	    divTsTd1Str = divTsTd1Str.replace(/ROWNUMBERVALUE/g, (maxRowNum)); 
		cell0.innerHTML = divTsTd1Str;
		
	 	var cell1 = row.insertCell(1);
		var divTsTd2Str = document.getElementById("divTaskTd2").innerHTML;
		divTsTd2Str = divTsTd2Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd2Str = divTsTd2Str.replace(/BARCODEVALUE/g,("")); 
		divTsTd2Str = divTsTd2Str.replace(/PRICECALCULATION/g,("productPriceCalculation")); 
		cell1.innerHTML = divTsTd2Str;

		var cell2 = row.insertCell(2);
		var divTsTd3Str = document.getElementById("divTaskTd3").innerHTML;
		divTsTd3Str = divTsTd3Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd3Str = divTsTd3Str.replace(/PRODUCTNAMEVALUE/g,(productId));
		divTsTd3Str = divTsTd3Str.replace(/PRODUCTNAME/g,(productName));
		cell2.innerHTML = divTsTd3Str;
		
		var cell3 = row.insertCell(3);
		var divTsTd4Str = document.getElementById("divTaskTd4").innerHTML;
		divTsTd4Str = divTsTd4Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd4Str = divTsTd4Str.replace(/BATCHIDVALUE/g,(""));
		cell3.innerHTML = divTsTd4Str;  
		
		var cell4 = row.insertCell(4);
		/* var divTsTd5Str = document.getElementById("divTaskTd5").innerHTML;
		divTsTd5Str = divTsTd5Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell4.innerHTML = divTsTd5Str;  */ 
		cell4.innerHTML = "<input class='Datepicker' name='"+maxRowNum+"manuFatureDate' id='"+maxRowNum+"manuFatureDate'  readonly='readonly'  type='text' data-validation='required' field-name='ManufacturDate' >";
		
		var cell5 = row.insertCell(5);
		/* var divTsTd6Str = document.getElementById("divTaskTd6").innerHTML;
		divTsTd6Str = divTsTd6Str.replace(/REPLACEROWID/g, (""));
		cell5.innerHTML = divTsTd6Str;   */
		cell5.innerHTML = "<input class='Datepicker' name='"+maxRowNum+"expiredate' id='"+maxRowNum+"expiredate' readonly='readonly'  type='text'  field-name='ExpireDate' >";
		
		$(".Datepicker").datepicker();
		
		var cell6 = row.insertCell(6);
		var divTsTd7Str = document.getElementById("divTaskTd7").innerHTML;
		divTsTd7Str = divTsTd7Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd7Str = divTsTd7Str.replace(/QUANTITYVALUE/g, (quantity));
		cell6.innerHTML = divTsTd7Str;  
		
		var cell7 = row.insertCell(7);
		var divTsTd8Str = document.getElementById("divTaskTd8").innerHTML;
		divTsTd8Str = divTsTd8Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell7.innerHTML = divTsTd8Str;  
		
	    var cell8 = row.insertCell(8);
		var divTsTd9Str = document.getElementById("divTaskTd9").innerHTML;
		divTsTd9Str = divTsTd9Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell8.innerHTML = divTsTd9Str;  
		
		
		var cell9 = row.insertCell(9);
		var divTsTd10Str = document.getElementById("divTaskTd10").innerHTML;
		divTsTd10Str = divTsTd10Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell9.innerHTML = divTsTd10Str;  
		
		var cell10 = row.insertCell(10);
		var divTsTd11Str = document.getElementById("divTaskTd11").innerHTML;
		divTsTd11Str = divTsTd11Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd11Str = divTsTd11Str.replace(/MRPVALUE/g, (""));
		cell10.innerHTML = divTsTd11Str; 
		
		var cell11 = row.insertCell(11);
		var divTsTd12Str = document.getElementById("divTaskTd12").innerHTML;
		divTsTd12Str = divTsTd12Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd12Str = divTsTd12Str.replace(/TOTALAMOUNTVALUE/g, (""));
		cell11.innerHTML = divTsTd12Str; 
		
		var cell12 = row.insertCell(12);
		var divTsTd13Str = document.getElementById("divTaskTd13").innerHTML;
		divTsTd13Str = divTsTd13Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell12.innerHTML = divTsTd13Str; 
		
		renumberRows(); 
		updateNumberOfPackages();
		
	}
	}		
</script>

<script type="text/javascript">
	function removeProduct(rowId) {
		
		var rowNum = $("#divTsRow"+rowId).val();
		var maxRowNum = parseInt(rowNum);
		 document.getElementById("divTsRow"+rowId).style.display = 'none';
		 document.getElementById("divTsRow"+rowId).value= '';
		 
		 renumberRows(); 
		 updateNumberOfPackages();
		 
		 var  rId = "divTsRow"+rowId;
		  var r = document.getElementById("productTbl").rows[rId];
          var count = 2*(r.cells.length);
		    for(i=0;i<=count;i++){
		        r.deleteCell(0);
		    } 
	}
</script>
<script type="text/javascript">
function renumberRows() {
        $('#productTbl tr:visible').each(function(index, el){ 
            $(this).children('td').first().text(index++);
        });
    }
</script>   
<script type="text/javascript">
function updateNumberOfPackages() {
	var totalRow =  $('#productTbl tr:visible').length; 
	$("#numberOfProducts").val(totalRow-1);
}
</script>   

var totalRow =  $('#data tr:visible').length;
 

<script type="text/javascript">
function getPurchaseOrderItems(local){
	var purchaseOrderId  = local.value;
	var operoxUrl = '${operoxUrl}';  
    $.ajax({
		   	type: "POST",
		   	url: operoxUrl+"/getPurchaseOrderItems?${_csrf.parameterName}=${_csrf.token}&purchaseOrderId="+purchaseOrderId, 	   
   		    success: function(data) {
   		    	if(data != null && data != 'undefined' && data != "" && data != '[]'){
   		    		var json = JSON.parse(data);
   		    		var result = '';
				   	for (var i=0; i<json.length; i++)
	   				{   
		   				
		   				var quantity = 1;
		   				var checkMaxTsRowNum = $("#maxTsRowNum").val();
		   				var tbl = document.getElementById("productTbl");
		   				var maxRowNum = parseInt($("input[name = 'maxTsRowNum']").val());
		   				$("input[name = 'maxTsRowNum']").val(maxRowNum + 1);
		   				
		   				var row = tbl.insertRow(maxRowNum);
		   				row.setAttribute("id", "divTsRow" + maxRowNum);
		   				
		   				var report = json[i];
		   				
		   				var cell0 = row.insertCell(0);
		   				var divTsTd1Str = document.getElementById("divTaskTd1").innerHTML;
		   				divTsTd1Str = divTsTd1Str.replace(/REPLACEROWID/g, (maxRowNum));
		   			    divTsTd1Str = divTsTd1Str.replace(/ROWNUMBERVALUE/g, (maxRowNum)); 
		   				cell0.innerHTML = divTsTd1Str;
		   				
		   				var cell1 = row.insertCell(1);
		   				var divTsTd2Str = document.getElementById("divTaskTd2").innerHTML;
		   				divTsTd2Str = divTsTd2Str.replace(/REPLACEROWID/g, (maxRowNum));
		   				divTsTd2Str = divTsTd2Str.replace(/BARCODEVALUE/g,("")); 
		   				divTsTd2Str = divTsTd2Str.replace(/PRICECALCULATION/g,("productPriceCalculation")); 
		   				cell1.innerHTML = divTsTd2Str;
		   				
		   				var cell2 = row.insertCell(2);
		   				var divTsTd3Str = document.getElementById("divTaskTd3").innerHTML;
		   				divTsTd3Str = divTsTd3Str.replace(/REPLACEROWID/g, (maxRowNum));
		   				divTsTd3Str = divTsTd3Str.replace(/PRODUCTNAMEVALUE/g,(report.productId));
		   				divTsTd3Str = divTsTd3Str.replace(/PRODUCTNAME/g,(report.productName));
		   				cell2.innerHTML = divTsTd3Str;
		   				
		   				var cell3 = row.insertCell(3);
		   				var divTsTd4Str = document.getElementById("divTaskTd4").innerHTML;
		   				divTsTd4Str = divTsTd4Str.replace(/REPLACEROWID/g, (maxRowNum));
		   				divTsTd4Str = divTsTd4Str.replace(/BATCHIDVALUE/g,(""));
		   				cell3.innerHTML = divTsTd4Str; 
		   				
		   				/* var cell4 = row.insertCell(4);
		   				var divTsTd5Str = document.getElementById("divTaskTd5").innerHTML;
		   				divTsTd5Str = divTsTd5Str.replace(/REPLACEROWID/g, (maxRowNum));
		   				cell4.innerHTML = divTsTd5Str;  
		   				
		   				var cell5 = row.insertCell(5);
		   				var divTsTd6Str = document.getElementById("divTaskTd6").innerHTML;
		   				divTsTd6Str = divTsTd6Str.replace(/REPLACEROWID/g, (maxRowNum));
		   				cell5.innerHTML = divTsTd6Str;   */
		   				
		   				
		   				var cell4 = row.insertCell(4);
		   				cell4.innerHTML = "<input class='Datepicker' name='"+maxRowNum+"manuFatureDate' id='"+maxRowNum+"manuFatureDate' readonly='readonly'  type='text' data-validation='required' field-name='Manufactur' >";
		   				
		   				var cell5 = row.insertCell(5);
		   				cell5.innerHTML = "<input class='Datepicker' name='"+maxRowNum+"expiredate' id='"+maxRowNum+"expiredate' readonly='readonly'  type='text' field-name='ExpireDate' >";
		   				
		   				$(".Datepicker").datepicker();
		   				
		   				var cell6 = row.insertCell(6);
		   				var divTsTd7Str = document.getElementById("divTaskTd7").innerHTML;
		   				divTsTd7Str = divTsTd7Str.replace(/REPLACEROWID/g, (maxRowNum));
		   				divTsTd7Str = divTsTd7Str.replace(/QUANTITYVALUE/g, (report.quantity));
		   				cell6.innerHTML = divTsTd7Str;  
		   				
		   				var cell7 = row.insertCell(7);
		   				var divTsTd8Str = document.getElementById("divTaskTd8").innerHTML;
		   				divTsTd8Str = divTsTd8Str.replace(/REPLACEROWID/g, (maxRowNum));
		   				cell7.innerHTML = divTsTd8Str;  
		   				
		   			    var cell8 = row.insertCell(8);
		   				var divTsTd9Str = document.getElementById("divTaskTd9").innerHTML;
		   				divTsTd9Str = divTsTd9Str.replace(/REPLACEROWID/g, (maxRowNum));
		   				cell8.innerHTML = divTsTd9Str;  
		   				
		   				
		   				var cell9 = row.insertCell(9);
		   				var divTsTd10Str = document.getElementById("divTaskTd10").innerHTML;
		   				divTsTd10Str = divTsTd10Str.replace(/REPLACEROWID/g, (maxRowNum));
		   				cell9.innerHTML = divTsTd10Str;  
		   				
		   				var cell10 = row.insertCell(10);
		   				var divTsTd11Str = document.getElementById("divTaskTd11").innerHTML;
		   				divTsTd11Str = divTsTd11Str.replace(/REPLACEROWID/g, (maxRowNum));
		   				divTsTd11Str = divTsTd11Str.replace(/MRPVALUE/g, (""));
		   				cell10.innerHTML = divTsTd11Str; 
		   				
		   				var cell11 = row.insertCell(11);
		   				var divTsTd12Str = document.getElementById("divTaskTd12").innerHTML;
		   				divTsTd12Str = divTsTd12Str.replace(/REPLACEROWID/g, (maxRowNum));
		   				divTsTd12Str = divTsTd12Str.replace(/TOTALAMOUNTVALUE/g, (""));
		   				cell11.innerHTML = divTsTd12Str; 
		   				
		   				var cell12 = row.insertCell(12);
		   				var divTsTd13Str = document.getElementById("divTaskTd13").innerHTML;
		   				divTsTd13Str = divTsTd13Str.replace(/REPLACEROWID/g, (maxRowNum));
		   				cell12.innerHTML = divTsTd13Str;
		   				
		   			 	renumberRows(); 
		   			    updateNumberOfPackages();
		   				
	   				}
			    }  
			}
    }); 
}
</script>

<!-- this is for price calculation --> 
<script>
$(document).click(function(){ 
	$('.productPriceCalculation').each(function() {
			var localId = this.id;
			
			var productPrice = $("#"+localId+"productPrice").val();
			var discount = $("#"+localId+"discount").val();
			var taxIdAndValue = $("#"+localId+"tax").val();
			
			var taxs = [];
			taxs = taxIdAndValue.split("&");
			var tax  = taxs[1];
			
			//this block is for mrp.
			var mrp;
			if(productPrice != null && productPrice != '' && productPrice !='undefined'){
				mrp = parseFloat(productPrice);
			}
			if(discount != null && discount != '' && discount !='undefined'){
				mrp = parseFloat(mrp) - parseFloat(discount);
			}
			if(tax != null && tax != '' && tax !='undefined'){
				mrp = parseFloat(mrp) + parseFloat(tax);
			}
			if(!isNaN(mrp)){
				$("#"+localId+"mrp").val(parseFloat(mrp));
			}
			
			//this block is for totalAmout
			var totalAmount;
			var quantity = $("#"+localId+"quantity").val();
			if(quantity != null && quantity != '' && quantity !='undefined' && !isNaN(quantity)){
				var mrp = $("#"+localId+"mrp").val();
				if(mrp != null && mrp != '' && mrp !='undefined' && !isNaN(mrp)){
					totalAmount = parseFloat(quantity) * parseFloat(mrp);
					$("#"+localId+"totalAmount").val(parseFloat(totalAmount));
				}
			}
			
			//grand Total
			var grandAmount=0; 
			$('.totalAmountClass').each(function() {
				 var iValue = this.value;
				 if(iValue != null && iValue != '' && iValue !='undefined' && !isNaN(iValue)){
				  		 grandAmount = parseFloat(grandAmount) + parseFloat(iValue);
				  		 if(grandAmount != null && grandAmount != '' && grandAmount !='undefined' && !isNaN(grandAmount)){
							 $("#amount").val(parseFloat(grandAmount));
				         }
				 }
			 });  
			grandTotal ();
			
	});  
	
});

</script>	


<script type="text/javascript">
function getSuppliersByPodId(local){
	var purchaseOrderId  = local.value;
	var operoxUrl = '${operoxUrl}';  
	if(local.value!=null && local.value!='' && local.value!='undefined'){
		
			$.ajax({
			   	type: "POST",
			   	url: operoxUrl+"/getSupplierBypurchaseOrderId?${_csrf.parameterName}=${_csrf.token}&purchaseOrderId="+purchaseOrderId, 	   
				    success: function(data) {
				    	if(data != null && data != 'undefined' && data != "" && data != '[]'){
				    		var json = JSON.parse(data);
	                        var result ="";
							result = result + '<input type="text"  readOnly ="readOnly" id="supplierName" placeholder="Supplier" name="supplierName" value="'+json.supplierName+'" field-name="Supplier  Name" >';
							result = result + '<input type="hidden"   id="supplierId"  name="supplierId" value="'+json.Supplier.id+'" >';
							$("#purchaseOrderId").empty();
							$("#purchaseOrderId").append(result);
							document.getElementById("wareHouseRecieveType").style.display = 'none';
				    }  
				}
		 });
	}else{
		var operoxUrl = '${operoxUrl}'; 
		
		$.ajax({
		   	type: "POST",
		   	url: operoxUrl+"/getSupplierListInRecievedItem?${_csrf.parameterName}=${_csrf.token}", 	   
			    success: function(data) {
			    	if(data != null && data != 'undefined' && data != "" && data != '[]'){
			    		var json = JSON.parse(data);
                       
			    		var output = "";
			    		output = output + " <select class='chosen-select' name='supplierId' id='supplierId' field-name='Select Supplier' >";
			        	output = output + "<option value=''>-Select Supplier-</option>"; 
			        	for (var i=0; i<json.length; i++)
						{
				        		var opt = json[i];
				        		output = output + "<option value='" +opt.id+ "'>"+opt.name+"</option>";
				        		
						}
			        	output = output + "</select>"; 
						$("#purchaseOrderId").empty();
						$("#purchaseOrderId").append(output);
			    }  
			}
	}); 
		
	}
	
	

}
</script>


<script>
function grandTotal (){
	 var grandAmount =  $("#amount").val();
	 var grandDiscount =  $("#grandDiscount").val();
	 var grandDiscountType =  $("#grandDiscountType").val();
	 
	 var grandTaxIdAndValue = $("#grandTax").val();
	 if(grandTaxIdAndValue != null && grandTaxIdAndValue != '' && grandTaxIdAndValue != 'undefined'){
		 var grandTaxs = [];
		 grandTaxs = grandTaxIdAndValue.split("&");
		 var grandtax  = grandTaxs[1];
		 grandtax = grandtax.trim();
	 }
		
	 var grandFinalAmount = 0.0;
	 if(grandAmount != null && grandAmount != '' && grandAmount != 'undefined' && !isNaN(grandAmount)){
		     grandFinalAmount =  parseFloat(grandAmount);
		     if(grandTax != null && grandTax != '' && grandTax != 'undefined' && !isNaN(grandTax)){
				 grandFinalAmount =  grandFinalAmount + parseFloat(grandTax);
			 }
			 if(grandDiscount != null && grandDiscount != '' && grandDiscount != 'undefined' && !isNaN(grandDiscount)){
					 
				     if(grandDiscountType == "Percentage"){
						   var percentageValue = grandFinalAmount / 100;
						   grandFinalAmount =  grandFinalAmount - parseFloat(percentageValue); 
					 } 
					 if(grandDiscountType == "Value"){
						 grandFinalAmount =  grandFinalAmount - parseFloat(grandDiscount); 
					 } 
				    
			 } 
			 if(grandFinalAmount != null && grandFinalAmount != '' && grandFinalAmount !='undefined' && !isNaN(grandFinalAmount)){
				 $("#grandTotalAmount").val((grandFinalAmount).toFixed(2));    
			 }
	 }
	 
	
}

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
