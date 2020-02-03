<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>


<style type="text/css">
.more-table.more-table{
	overflow-y: auto;
}
</style>
<!-- Content Area -->
<section class="content-wraper">
<form id="receivedStock_form" name="receivedStock_form" method="post">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Return Stock</h2>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Return Stock</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix">
            <div class="div33">
       <label class="labl">Location From &nbsp;<b class="text-danger"> *</b> </label>
       <span>
         <select class="chosen-select"  name="storeReturnFrom" id="storeReturnFrom"  onChange="toStore(this);"field-name="Select Location" data-validation="required" >
										<option value="">-- Select Location --</option>
										<c:forEach var="store" items="${storeList}">
											<option value="${store.id}">${store.storeName}</option>
										</c:forEach>
		 </select> 
       </span>
    </div>
    
      <div class="div33">
       <label class="labl">Date &nbsp;<b class="text-danger"> *</b></label>
       <span>
                 <input type="text"  placeholder="DD-MM-YYYY" class="datefild" id="receivedDate"  class="form-control" name="receivedDate" value="${receivedDate}"  field-name="Received  Date" data-validation="required" > 
      
       </span>
    </div>
    
      <div class="div33">
       <label class="labl">To Location &nbsp;<b class="text-danger"> *</b> </label>
	       <span>
	        <select class="chosen-select"  name="storeReturnTo" id="storeReturnTo" field-name="Select Location" data-validation="required" >
											<option value="">-- Select Location --</option>
											<c:forEach var="store" items="${storeList}">
												<option value="${store.id}">${store.storeName}</option>
											</c:forEach>
			 </select> 
	       </span>
    </div>
    
     
    
     <div class="div33">
       <label class="labl">Supplier&nbsp;</label>
       <span>
       <select class="chosen-select"  name="supplier" id="supplier" field-name="Select Supplier" data-validation-optional="true">
										<option value="">-- Select Supplier --</option>
										<c:forEach var="store" items="${supplierList}">
											<option value="${store.id}">${store.name}</option>${store.name}
										</c:forEach>
		 </select> 
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Customer&nbsp;</label>
       <span>
       <select class="chosen-select"  name="customer" id="customer" field-name="Select Customer" data-validation-optional="true" >
										<option value="">-- Select Customer --</option>
										<c:forEach var="store" items="${cusromerList}">
											<option value="${store.id}">${store.customerName}</option>
										</c:forEach>
		 </select>
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Barcode</label>
       <span>
       <input type="text" placeholder="Barcode">
       </span>
    </div>
    
    
    <div class="div33">
        <input type="hidden" id="maxTsRowNum" name="maxTsRowNum" value="1" />
       <label class="labl">Select Product&nbsp;</label>
       <span>
      <select class="chosen-select"  onchange="addProduct(this);" name="productId" id="productId" field-name="Select Product" data-validation-optional="true">
						<option value="" >-- Select Product --</option>
						<c:forEach var="product" items="${productList}"> 
							<option value="${product.id}&${product.productName}&${product.barCode}&${product.batchNumber}&${product.priceStr}&${product.expireDateStr}">${product.productName}</option>
						</c:forEach>
		 </select> 
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Search Code &nbsp;</b></label>
       <span>
      <select class="chosen-select"  onchange="addProduct(this);" name="productId" id="productId" field-name="Select Product Code" data-validation-optional="true" >
						<option value="">-- Select ProductCode --</option>
						<c:forEach var="product" items="${productList}">
							<option value="${product.id}&${product.productName}&${product.barCode}&${product.batchNumber}&${product.priceStr}&${product.expireDateStr}">${product.productId.productCode}</option>
						</c:forEach>
		 </select> 
       </span>
    </div>
    
    <div class="clearfix"></div>
      <!-- Content Area inner -->
  <!-- Table Designa addstock -->
  <div class="clearfix">
    <div class="data_table more-table scrl-dataTable">
    	<table  class="table table-bordered table-striped clearfix" id="productTbl">
        	<tbody>
            	<tr>
                	<th>Sl.No</th>
                    <th>Barcode</th>
                    <th>Product Name</th>
                    <th>Batch Id&nbsp;&nbsp;<span class="text-danger">*</span></th>
                    <th>Qty&nbsp;&nbsp;<span class="text-danger">*</span></th>
                    <th>Price&nbsp;&nbsp;<span class="text-danger">*</span></th>
                    <th>Expire Date&nbsp;&nbsp;<span class="text-danger">*</span></th>
                    <th>Reason</th>
                    <th>Total</th>
                    <th class="action">Action</th>
                </tr>
            </tbody>
        </table>
    </div> 
  </div>
  
  
<!-- =============================== AddProduct Divs  ============================ -->
<div id="divTaskTd1" style="display: none; float: left;">
    <input type="hidden" id="REPLACEROWIDrowNumber"
		name="REPLACEROWIDrowNumber" value="ROWNUMBERVALUE" /> 
	<span>ROWNUMBERVALUE</span>
   
</div>  
 <div id="divTaskTd2" style="display: none; float: left;">
    <input type="text" id="REPLACEROWIDbarCode"
		name="REPLACEROWIDbarCode" readonly='readonly' value="BARCODEVALUE" /> 
</div>
 <div id="divTaskTd3" style="display: none; float: left;">  
    <input type="hidden" id="REPLACEROWIDproductId"
		name="REPLACEROWIDproductId"  readonly='readonly' value="PRODUCTIDVALUE" /> 
	<span>PRODUCTNAMEVALUE</span>
</div>
<div id="divTaskTd4" style="display: none; float: left;">
    <input type="text" id="REPLACEROWIDbatchId"
		name="REPLACEROWIDbatchId" readonly='readonly'  value="BATCHIDVALUE" /> 
</div> 

<div id="divTaskTd5" style="display: none; float: left;">
    <input type="hidden" class="PRICECALCULATION" id="REPLACEROWID" /> 
    <input type="text" id="REPLACEROWIDquantity" 
		name="REPLACEROWIDquantity"  value="QUANTITYVALUE" /> 
</div> 


<div id="divTaskTd6" style="display: none; float: left;">
    <input type="text" id="REPLACEROWIDproductPrice" 
		name="REPLACEROWIDproductPrice" readonly='readonly' value="PRICEVALUE" /> 
</div> 

<div id="divTaskTd7" style="display: none; float: left;">
   <input type="text" id="REPLACEROWIDexpiredate" readonly='readonly'  value="EXPIRYDATE" name="REPLACEROWIDexpiredate" placeholder="DD/MM/YYYY"  >
</div>    

<div id="divTaskTd8" style="display: none; float: left;">
    <input type="text" id="REPLACEROWIDreason" 
		name="REPLACEROWIDreason" value="" /> 
</div> 

 
<div id="divTaskTd9" style="display: none; float: left;">
		<!-- <div id="REPLACEROWIDtotal" name="REPLACEROWIDtotal"  class="tbldata-dotdot-vsm"></div> -->
		<input type="text" id="REPLACEROWIDtotal"  
				name=REPLACEROWIDtotal value="TOTALVALUE" readonly='readonly'  class="totalClass" /><span>TOTALVALUE</span> 
</div>
  
<div id="divTaskTd10" style="display: none; float: left;">
		<div class="tbldata-dotdot-vsm"><a href="#" onclick="removeProduct(REPLACEROWID);"><i class="glyphicon glyphicon-trash deme"></i></a>
</div>
</div>

<!-- =============================== AddProduct Divs  ============================ -->
 
  <!-- Table Designa addstock ends -->
    <div class="clearfix mt25"></div>
     
        <div class="div50">
       	 <label class="labl">Number of Products:</label>
            <span>
            <input type="text" placeholder="Number of Products"  name="totalNumberOfProducts"  readOnly="readonly" id="totalNumberOfProducts" field-name="Number of Products">
            </span>
        </div>

        <div class="div50">
       	 <label class="labl">Total Amount</label>
            <span>
            <input type="text" placeholder="Total Amount"  name="totalAmount" id="totalAmount" field-name="Total Amount"  >
            </span>
        </div>
        
        <div class="div50">
       	 <label class="labl">Users</label>
            <span>
            <select class="form-control select-bg"  name="stockReturnBy" id="stockReturnBy" field-name="Select Users" data-validation="required">
						<option  value="">-- Select User --</option>
						<c:forEach var="user" items="${userList}">
							<option value="${user.id}">${user.firstName} ${user.lastName}</option>
						</c:forEach>
		 </select>
            </span>
        </div>
        
        <div class="div50">
       	 <label class="labl">Comment</label>
            <span>
             <textarea rows="4" placeholder="Comment" name="comment" id="comment"  field-name="Comment"></textarea>
            </span>
        </div>



  </div>
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right custom-buttons">
        <!--  <a href="#" id="draft" class="btn btn-danger">
           <i class="glyphicon glyphicon-retweet"></i>Hold
          </a>
          <a href="#" id="sv" class="btn btn-success">
           <i class="glyphicon glyphicon-share-alt"></i>Return
          </a> -->
         <a href="#" id="sp" onClick="storeReturnStock();" class="btn btn-primary">
           <i class="glyphicon glyphicon-print"></i>Save
          </a>
         <a href="#" id="can"  onClick="javascript:location.href = '<c:url value='/returnStockDashboard'/>'"    class="btn btn-default">
           <i class="glyphicon glyphicon-remove"></i>Cancel
          </a>
        </div>
      </div>
    </div>
</form>
</section>

<!-- Content Area Ends--> 




<script type="text/javascript">
	function storeReturnStock() {  
		var validationSettings = {
			errorMessagePosition : 'element',
			borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd'
		};
		if ($('#receivedStock_form').validate(false, validationSettings)) { 
			var frm = $('#receivedStock_form').serializeFormJSON();
			var con = JSON.stringify(frm);
			con = con.replace(/[{}]/g, "");
			
			//It will escape all the special characters
			var jsonData = encodeURIComponent(con);
			var operoxUrl = '${operoxUrl}';
		
			$.ajax({
						type : "POST",
						url : operoxUrl+"/storeReturnStock?${_csrf.parameterName}=${_csrf.token}&json="+ jsonData,
						success : function(result) {
								location.replace(operoxUrl
										+ "/returnStockDashboard");

						},
		     }); 
			return true;
		} else {
			return false;
		}
	}
	$('body').on('blur', '#project_form', function() {
		$("#receivedStock_form").showHelpOnFocus();
		$("#receivedStock_form").validateOnBlur(false, validationSettings);
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
  $( function() {
    $( "#receivedDate").datepicker();
  });
</script>


<script type="text/javascript">
function toStore(local) {
	
	var storeReturnTo = $("#storeReturnTo").val();
	
	var fromStore  = local.value;
	 if(fromStore != null && fromStore != ''  && fromStore != 'undefined'){
	var operoxUrl = '${operoxUrl}'; 
		$.ajax({
		   	type: "POST",
		   	url: operoxUrl+"/getStoreListExcludingFromStore?${_csrf.parameterName}=${_csrf.token}&fromStore="+fromStore, 	   
		   			success: function(data) {
			    	if(data != null && data != 'undefined' && data != "" && data != '[]'){
			    		var json = JSON.parse(data);
                        var output = "";
    					output = output	+ "<option value=''>-- Select Store --</option>";
    					for (var i = 0; i < json.length; i++) {
    						var opt = json[i];
    						output = output + "<option value='" +opt.id+ "'>"+opt.storeName+"</option>";
    					}
    					$('#storeReturnTo').empty();
    			    	$('#storeReturnTo').append(output);
    			    	$('#storeReturnTo').trigger("chosen:updated");   
			    }  
			}, error: function(e){
	            
	        }
	});
	 }
}
</script>




<script type="text/javascript">
	function addProduct(local) {
   if(local.value != null && local.value != 'undefined' && local.value != ''){	
		var products = [];
		products = local.value.split("&");
		var productId = products[0];
		var productName = products[1];
		var productbarCode = products[2];
		var productbatchNumber = products[3];
	    var productPrice = products[4];
	    var productexpiryDate = products[5];
	    var from = productexpiryDate.split("-");
	    
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
		divTsTd2Str = divTsTd2Str.replace(/BARCODEVALUE/g,(productbarCode)); 
		cell1.innerHTML = divTsTd2Str;

		var cell2 = row.insertCell(2);
		var divTsTd3Str = document.getElementById("divTaskTd3").innerHTML;
		divTsTd3Str = divTsTd3Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd3Str = divTsTd3Str.replace(/PRODUCTNAMEVALUE/g,(productName));
		divTsTd3Str = divTsTd3Str.replace(/PRODUCTIDVALUE/g,(productId));
		cell2.innerHTML = divTsTd3Str;
		
		var cell3 = row.insertCell(3);
		var divTsTd4Str = document.getElementById("divTaskTd4").innerHTML;
		divTsTd4Str = divTsTd4Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd4Str = divTsTd4Str.replace(/BATCHIDVALUE/g,(productbatchNumber));
		cell3.innerHTML = divTsTd4Str;  
		
		var cell4 = row.insertCell(4);
		var divTsTd5Str = document.getElementById("divTaskTd5").innerHTML;
		divTsTd5Str = divTsTd5Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd5Str = divTsTd5Str.replace(/QUANTITYVALUE/g,(quantity));
		divTsTd5Str = divTsTd5Str.replace(/PRICECALCULATION/g,("productPriceCalculation")); 
		cell4.innerHTML = divTsTd5Str;  
		
		var cell5 = row.insertCell(5);
		var divTsTd6Str = document.getElementById("divTaskTd6").innerHTML;
		divTsTd6Str = divTsTd6Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd6Str = divTsTd6Str.replace(/PRICEVALUE/g,(productPrice));
		cell5.innerHTML = divTsTd6Str;  
		
		var cell6 = row.insertCell(6);
		/* var divTsTd7Str = document.getElementById("divTaskTd7").innerHTML;
		divTsTd7Str = divTsTd7Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell6.innerHTML = divTsTd7Str;  */
		var divTsTd7Str = document.getElementById("divTaskTd7").innerHTML;
		divTsTd7Str = divTsTd7Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd7Str = divTsTd7Str.replace(/EXPIRYDATE/g,(productexpiryDate));
		cell6.innerHTML = divTsTd7Str; 
		
		
		
		/* cell6.innerHTML = "<input class='Datepicker' name='"+maxRowNum+"expiredate' id='"+maxRowNum+"expiredate' value="+productexpiryDate+" readonly='readonly'  type='text' data-validation-optional='true' field-name='Expiredate' >";
		$(".Datepicker").datepicker(); */
	    
		
		var cell7 = row.insertCell(7);
		var divTsTd8Str = document.getElementById("divTaskTd8").innerHTML;
		divTsTd8Str = divTsTd8Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell7.innerHTML = divTsTd8Str;  
		
		 
	    var cell8 = row.insertCell(8);
		var divTsTd9Str = document.getElementById("divTaskTd9").innerHTML;
		divTsTd9Str = divTsTd9Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd9Str = divTsTd9Str.replace(/TOTALVALUE/g, (""));
		cell8.innerHTML = divTsTd9Str;  
		
		var cell9 = row.insertCell(9);
		var divTsTd10Str = document.getElementById("divTaskTd10").innerHTML;
		divTsTd10Str = divTsTd10Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell9.innerHTML = divTsTd10Str;  
		
		 renumberRows(); 
		 updateNumberOfPackages();
		 
	}
		
}		
		
</script>

<script type="text/javascript">
function getDate(rowId) {
             $("#divTsRow1").find("rowId#" ).datepicker();
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
	$("#totalNumberOfProducts").val(totalRow-1);
}
</script>

<!-- this is for price calculation --> 
<script>
$(document).click(function(){ 
	$('.productPriceCalculation').each(function() {
			var localId = this.id;
			var price = $("#"+localId+"productPrice").val();
			var quantity = $("#"+localId+"quantity").val();
			if(quantity != null && quantity != 'undefined' && quantity != '' && price != null && price != 'undefined' && price != ''){
				   $("#"+localId+"total").val(parseInt(quantity) * parseFloat(price).toFixed(2)); 
			} 
			grandTotal();
			
	});  
	
});

</script>	

<script>
function grandTotal (){
	//grand Total
	var grandAmount=0.00; 
	$('.totalClass').each(function() {
		 var localValue = this.value;
		 if(localValue != null && localValue != '' && localValue !='undefined' && !isNaN(localValue)){
		  		 grandAmount = parseFloat(grandAmount) + parseFloat(localValue);
		 }
	 });  
	 if(grandAmount != null && grandAmount != '' && grandAmount !='undefined' && !isNaN(grandAmount)){
		 $("#totalAmount").val(parseFloat(grandAmount).toFixed(2));
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



<jsp:include page="../masterFooter.jsp" /> 