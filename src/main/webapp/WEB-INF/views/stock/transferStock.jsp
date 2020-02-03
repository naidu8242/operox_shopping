<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>



<script>
	$(function() {
		$('#transferDate').datepicker({
			dateFormat : 'mm/dd/yy',
			onSelect : function(selected) {
				var dt = new Date(selected);
				dt.setDate(dt.getDate() + 1);
				$("#receivedDate").datepicker("option", "minDate", dt);
				addValidClass();
			}
		});

	});
</script>
<!-- This Script is for Validating Delivery Date with respect to Order Date Script  Ends-->

<script type="text/javascript"> 
	function addValidClass(){
		 var transferDate = document.getElementById("transferDate").value;
		 if(transferDate){
			 $('#transferDateDiv').find('p.jquery_form_error_message').remove(); 
		 }
}
</script>



<form id="transferStock_form" name="transferStock_form" method="post">
<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Transfer Stock</h2>
    </div>
    <div class="pull-right brud-crum"><a href="dashboard" >Home</a> &raquo;<a href="transferStockDashboard" >Transfer Stock List</a> &raquo; Transfer Stock</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix">
  	<div id="loading"></div>
    <div class="div33">
       <label class="labl">Transfer Id</label>
       <span>
               <input type="text"  readonly="readonly" placeholder="Transfer Id" class="form-control" id="transferNumber" name="transferNumber" value="${transferNumber}"  field-name="Transfer Id" data-validation="required" >
       </span>
    </div>
    
    <div class="div33" id="transferDateDiv">
       <label class="labl">Date</label>
       <span>
                     <input type="text"  placeholder="DD-MM-YYYY" class="datefild" id="transferDate"  class="form-control" name="transferDate" value="${transferDate}"  field-name="Transfer Date"  >
       </span>
    </div>
    
    
    <div class="div33">
       <label class="labl">Received Date</label>
       <span>
                            <input type="text"  placeholder="DD-MM-YYYY" class="datefild" id="receivedDate"  class="form-control" name="receivedDate" value="${receivedDate}"  field-name="Received Date" >
       
       </span>
    </div>
    
    
    <div class="div33">
       <label class="labl">From Location&nbsp;<b class = "text-danger">*</b></label>
       <span>
		  <select class="chosen-select" name="storeReturnFrom" id="storeReturnFrom"  onChange="toStore(this);" field-name="Select Location" data-validation="required" >
										<option value="">-- Select Location --</option>
										<c:forEach var="store" items="${storeList}">
											<option value="${store.id}">${store.storeName}</option>
										</c:forEach>
		 </select> 
       </span>
    </div> 
    <div class="div33">
       <label class="labl">To Location&nbsp;<b class = "text-danger">*</b></label>
       <span>
          <select class="chosen-select" name="storeReturnTo" id="storeReturnTo" field-name="Select Location" data-validation="required" >
										<option value="">-- Select Location --</option>
										<c:forEach var="store" items="${storeList}">
											<option value="${store.id}">${store.storeName}</option>
										</c:forEach>
		 </select> 
		 
       </span>
    </div> 
    
    <div class="div33">
       <label class="labl">&nbsp;</label>
       <span>
         <input type="checkbox" placeholder="Search Code" class="mr10"><label>Send Email</label>
       </span>
    </div>
    
    
    <div class="div33">
       <label class="labl">Barcode</label>
       <span>
               <input type="text"  placeholder="Bar Code" class="form-control" id="barCode" name="barCode" value="${barCode}"  field-name="Bar Code"  >
       
       </span>
    </div> 
    
    
    
    
    <div class="div33">
       <input type="hidden" id="maxTsRowNum" name="maxTsRowNum" value="1" />
       <label class="labl">Select Product</label>
       <span>
         <select class="chosen-select" onchange="addProduct(this);" name="productId" id="productId" field-name="Select Product">
						<option value="" selected>-- Select Product --</option>
						<c:forEach var="product" items="${productStockList}">
							<option value="${product.productId.id}&${product.productId.productName}&${product.barCode}&${product.priceStr}">${product.productId.productName}</option>
						</c:forEach>
		 </select> 
       </span>
    </div> 
    
    <div class="div33">
       <label class="labl">Search Code</label>
       <span>
       <select class="chosen-select" onchange="addProduct(this);" name="searchId" id="searchId" field-name="Select Product" >
						<option value="" selected>-- Select Product --</option>
						<c:forEach var="product" items="${productStockSearchList}">
							<option value="${product.productId.id}&${product.productId.productName}&${product.barCode}&${product.priceStr}">${product.productId.productCode}</option>
						</c:forEach>
		 </select> 
		 
       </span>
    </div> 
    
    
    <div class="clearfix"></div>
      <!-- Content Area inner -->
 <!-- List view Table -->      
  <div class="clearfix">
    <div class="data_table more-table">
    	<table class="table table-bordered table-striped  clearfix" id="productTbl">
        	<!-- <tbody> -->
            	<tr id="divTsRow0">
                	<th>S.No</th>
                    <th>Barcode</th>
                    <th>Product Name</th>
                    <th>Qty/Weight</th>
                    <th>Amount</th>
                    <th>Action</th>
                </tr>
        </table>


<!-- =============================== AddTask Divs  ============================ -->
<div id="divTaskTd1" style="display: none; float: left;">
    <div class="tbldata-dotdot-vsm" id="REPLACEROWIDrowNumber" name="REPLACEROWIDrowNumber"  class="tbldata-dotdot-vsm"><span>ROWNUMBERVALUE</span></div> 
</div>
<div id="divTaskTd2" style="display: none; float: left;">
    <div class="tbldata-dotdot-vsm" id="REPLACEROWIDbarCode" name="REPLACEROWIDbarCode"  class="tbldata-dotdot-vsm"><span>BARCODEVALUE</span></div>
    <input type="hidden" id="REPLACEROWIDpriceId" name="REPLACEROWIDpriceId" value="PRICEVALUE" />
     <input type="hidden" id="REPLACEROWIDbarCode" name="REPLACEROWIDbarCode" value="BARCODEVALUE"  /> 
</div>
<div id="divTaskTd3" style="display: none; float: left;">  
   <div class="tbldata-dotdot-vsm" id="REPLACEROWIDproductName" name="REPLACEROWIDproductName" class="tbldata-dotdot-vsm"><span>PRODUCTNAMEVALUE</span></div>
    <input type="hidden" id="REPLACEROWIDproductId" name="REPLACEROWIDproductId" value="PRODUCTIDVALUE" />

</div>
<div id="divTaskTd4" style="display: none; float: left;">
     <!-- <div id="REPLACEROWIDquantity" name="REPLACEROWIDquantity" class="tbldata-dotdot-vsm"></div> -->
     <input type="text" id="REPLACEROWIDquantity"
		name="REPLACEROWIDquantity" value="QUANTITYVALUE" onblur="calculateAmount(REPLACEROWID);" /> 
</div>  

<div id="divTaskTd5" style="display: none; float: left;">
    <!-- <div id="REPLACEROWIDamount" name="REPLACEROWIDamount" class="tbldata-dotdot-vsm"><span>AMOUNTVALUE </span></div> -->
    <input type="text" id="REPLACEROWIDamount" name="REPLACEROWIDamount" value="AMOUNTVALUE" readonly="readonly"  /> 
</div>

<div id="divTaskTd6" style="display: none; float: left;">
    <div>
         <a href="#" onclick="removeProduct(REPLACEROWID);"><i class="glyphicon glyphicon-trash deme"></i></a>
    </div>
</div>
        
    </div> 
  </div>
 <!-- List view Table -->      
  </div>
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right">
        <!-- <a href="#" onclick="saveTransferStock('draft');" id="draft" class="btn btn-danger">
           <i class="glyphicon glyphicon-pencil"></i>Draft
          </a> -->
          <input type="button" value="Save" class="btn btn-primary" id="save" onclick="saveTransferStock('save');">
                    <input type="button"  value="Cancel" onClick="javascript:location.href = '<c:url value='/transferStockDashboard'/>'"   class="btn btn-default ml10">

        </div>
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
	function addProduct(local) {
		if(local.value != null && local.value != 'undefined' && local.value != ''){	
		var products = [];
		products = local.value.split("&");
       
		var productId = products[0];
		var productName = products[1];
		var barCode = products[2];
		var price = products[3];
		
		
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
		divTsTd2Str = divTsTd2Str.replace(/BARCODEVALUE/g,(barCode)); 
		divTsTd2Str = divTsTd2Str.replace(/PRICEVALUE/g,(price)); 
		cell1.innerHTML = divTsTd2Str;

		var cell2 = row.insertCell(2);
		var divTsTd3Str = document.getElementById("divTaskTd3").innerHTML;
		divTsTd3Str = divTsTd3Str.replace(/REPLACEROWID/g, (maxRowNum));
		//divTsTd3Str = divTsTd3Str.replace(/PRODUCTNAMEVALUE/g,(maxRowNum));
		divTsTd3Str = divTsTd3Str.replace(/PRODUCTNAMEVALUE/g,(productName)); 
		divTsTd3Str = divTsTd3Str.replace(/PRODUCTIDVALUE/g,(productId)); 
		
		cell2.innerHTML = divTsTd3Str;
		
		var cell3 = row.insertCell(3);
		var divTsTd4Str = document.getElementById("divTaskTd4").innerHTML;
		divTsTd4Str = divTsTd4Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd4Str = divTsTd4Str.replace(/QUANTITYVALUE/g,(quantity));
		cell3.innerHTML = divTsTd4Str;
		
		var cell4 = row.insertCell(4);
		var divTsTd5Str = document.getElementById("divTaskTd5").innerHTML;
		divTsTd5Str = divTsTd5Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd5Str = divTsTd5Str.replace(/AMOUNTVALUE/g,(quantity * price)); 
		
		cell4.innerHTML = divTsTd5Str;
		
		var cell5 = row.insertCell(5);
		var divTsTd6Str = document.getElementById("divTaskTd6").innerHTML;
		divTsTd6Str = divTsTd6Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell5.innerHTML = divTsTd6Str;
		
        renumberRows();
		/* $('select#searchId option').removeAttr("selected");
		$('select#productId option').removeAttr("selected"); */
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
	function saveTransferStock(submissionType) {  
		var validationSettings = {
			errorMessagePosition : 'element',
			borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd'
		};
		if ($('#transferStock_form').validate(false, validationSettings)) { 
			var frm = $('#transferStock_form').serializeFormJSON();
			var con = JSON.stringify(frm);
			con = con.replace(/[{}]/g, "");
			//It will escape all the special characters
			var jsonData = encodeURIComponent(con);

			var operoxUrl = '${operoxUrl}';
			$("#save").attr("disabled", "disabled");
			$("#save").addClass('button').val('Processing..');
			$("#loading").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("resources/images/loading.gif"); background-position:center; background-repeat:no-repeat; background-size:50px;');
			$.ajax({
						type : "POST",
						url : operoxUrl+"/storeTransferStock?${_csrf.parameterName}=${_csrf.token}&json="+jsonData+"&submissionType="+submissionType,
						success : function(result) {
								location.replace(operoxUrl
										+ "/transferStockDashboard");

						},
		     }); 
			return true;
		} else {
			return false;
		}
	}
	$('body').on('blur', '#project_form', function() {
		$("#transferStock_form").showHelpOnFocus();
		$("#transferStock_form").validateOnBlur(false, validationSettings);
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

function calculateAmount(localId){
	var quantity = $("#"+localId+"quantity").val();
	var price = $("#"+localId+"priceId").val();
	if(quantity != null && quantity != 'undefined' && quantity != '' && price != null && price != 'undefined' && price != ''){
		   $("#"+localId+"amount").val(parseInt(quantity) * parseFloat(price)); 
	} 
	
}
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
 