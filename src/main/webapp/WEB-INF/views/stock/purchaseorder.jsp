<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>


<!-- This Script is for Validating Delivery Date with respect to Order Date  Script Starts-->

<script>
	$(function() {
		$('#orderDate').datepicker({
			dateFormat : 'mm/dd/yy',
			onSelect : function(selected) {
				var dt = new Date(selected);
				dt.setDate(dt.getDate() + 1);
				$("#deliveryDate").datepicker("option", "minDate", dt);
				addValidClass();
			}
		});

	});
</script>
<!-- This Script is for Validating Delivery Date with respect to Order Date Script  Ends-->

<script type="text/javascript"> 
	function addValidClass(){
		 var orderDate = document.getElementById("orderDate").value;
		 if(orderDate){
			 $('#orderDateDiv').find('p.jquery_form_error_message').remove(); 
		 }
}
</script>

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div id="loading"></div>
     <div class="pull-left">
      <h2>Purchase Order</h2>
     </div>
    <div class="pull-right brud-crum"><a href = "dashboard" >Home</a>&raquo;<a href = "purchaseorderDashboard" >Purchase Order List</a>  &raquo; Add Customer</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix">
   <form:form id="purchaseOrder_form" name="purchaseOrder_form"  enctype='multipart/form-data' >
    <div class="div33">
       <label class="labl">Purchase Id</label>
       <span>
            <input type="text"  placeholder="Purchase Id" class="form-control" id="purchaseId" name="purchaseId" value="${purchaseNumber}" readonly="readonly" field-name="Purchase Id" data-validation="required validate_Space validate_Alphanumeric validate_length length1-20" >
       </span>
    </div>
    
  
    
    <div class="div33" id="orderDateDiv">
       <label class="labl">Order Date &nbsp;<a class="text-danger" >*</a></label>
	       <span>
	        	  <input type="text"  placeholder="DD-MM-YYYY" class="datefild" id="orderDate"  class="form-control" name="orderDate" value="${orderDate}"  field-name="Order Date" data-validation="required" >
	        </span>
    </div>
    
    <div class="div33">
       <label class="labl">Delivery  Date&nbsp;<a class="text-danger" >*</a></label>
       <span>
           <input type="text"  placeholder="DD-MM-YYYY" class="datefild" id="deliveryDate"  class="form-control" name="deliveryDate" value="${deliveryDate}"  field-name="Delivery  Date" data-validation="required" > 
       </span>
    </div>            
    
    <div class="div33">
       <label class="labl">Supplier&nbsp;<a class="text-danger" >*</a></label>
        <span>
           <select class="chosen-select" name="Supplier" id="Supplier" data-validation="required" field-name="Supplier">
	          <option selected disabled>--Select Supplier--</option>
	          <c:forEach var="option" items="${supplierList}">
		    	  <option value="${option.id}">${option.name}
			  </option>
		 </c:forEach>
         </select>
       </span>
    </div> 
    
    <div class="div33">
       <label class="labl">Location&nbsp;<a class="text-danger" >*</a></label>
       <span>
            <select class="chosen-select" name=storeId id="storeId" field-name="Location" data-validation="required">
          <option selected disabled>--Select Location--</option>
          <c:forEach var="option" items="${storeList}">
		     <option value="${option.id}">${option.storeName}
			</option>
		 </c:forEach>
         </select>
       </span>
    </div> 
    
      <div class="div33">
     <label class="labl"></label>
       <span>
         
       </span>
      </div> 
    <div class="div33">
      <!--  <input type="hidden" name="maxTsRowNum" id="maxTsRowNum" value="0"/> -->
       <input type="hidden" id="maxTsRowNum" name="maxTsRowNum" value="1" />
       <label class="labl">Select Product</label>
       <span>
          <select class="chosen-select" onchange="addProduct(this);" id="productId" value="" >
          <option value="" selected>--Select Product--</option>
          <c:forEach var="option" items="${productList}">
		     <option value="${option.id}&${option.productName}">${option.productName}
			</option>
		 </c:forEach>
         </select>
       </span>
    </div> 
     
    
    <div class="div33">
       <label class="labl">Search Code</label>
       <span>
       <select class="chosen-select" onchange="addProduct(this);" id="searchId" value="">
          <option value="" selected>--Select Product--</option>
          <c:forEach var="option" items="${productList}">
		     <option value="${option.id}&${option.productName}">${option.productCode}
			</option>
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
            	<tr id="divTsRow0">
                	<th>Sl.No</th>
                    <th>Product Name</th>
                	<th>Qty / Weight</th>
                    <th class="action">Action</th> 
                </tr>
                <!-- <tbody>
                <div id="prodcutList"></div>
            	<tr>
                    <td><div class="tbldata-dotdot-vsm">0003</div></td>
                    <td><div class="tbldata-dotdot-me">Red Dall</div></td>
                    <td><div class="tbldata-dotdot-vsm">10000</div></td>
                    <td class="action"><div>
                    	<a href="add-category.html"><i class="fa fa-pencil"></i></a><i class="glyphicon glyphicon-trash deme"></i></div>
					</td>
                </tr> 
               </tbody> -->
        </table>
        
<!-- =============================== AddTask Divs  ============================ -->
<div id="divTaskTd1" style="display: none; float: left;">
    <div id="REPLACEROWIDrowNumber" name="REPLACEROWIDrowNumber"  class="tbldata-dotdot-vsm"><span>ROWNUMBERVALUE</span></div>
</div>
<div id="divTaskTd2" style="display: none; float: left;">
    <div id="REPLACEROWIDproductName" title="PRODUCTNAMEVALUE" name="REPLACEROWIDproductName" class="tbldata-dotdot-vsm"><span>PRODUCTNAMEVALUE</span></div>
    <input type="hidden" id="REPLACEROWIDproductId" name="REPLACEROWIDproductId" value="PRODUCTIDVALUE" />
</div>
<div id="divTaskTd3" style="display: none; float: left;">  
   <input type="text" id="REPLACEROWIDquantity"
		name="REPLACEROWIDquantity" value="QUANTITYVALUE" /> 
   <!-- <span>QUANTITYVALUE</span> -->
</div>
<div id="divTaskTd4" style="display: none; float: left;">
    <div>
         <a href="#" onclick="removeProduct(REPLACEROWID);"><i class="glyphicon glyphicon-trash deme"></i></a>
    </div>
</div>
        
  </div> 
  </div>
 <!-- List view Table --> 
   </form:form>
  </div>
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right custom-buttons">
         <!-- <a href="javascript:void(0)" onClick ="storePurchaseOrder('draft');" id="draft" class="btn btn-danger">
           <i class="glyphicon glyphicon-pencil"></i>Draft
          </a> -->
          <a href="javascript:void(0)" onClick ="storePurchaseOrder('save');"  id="save_PurchaseOrder" class="btn btn-success">
           <i class="glyphicon glyphicon-floppy-disk"></i>Save
          </a>
         <a onClick="javascript:location.href = '<c:url value='/purchaseorderDashboard'/>'" id="can" class="btn btn-default">
           <i class="glyphicon glyphicon-remove"></i>Cancel
          </a>
        </div>
      </div>
   </div>
</section>
<!-- Content Area Ends--> 

<jsp:include page="../masterFooter.jsp" /> 
</div>

<script type="text/javascript">
  $( function() {
    $( "#orderDate, #deliveryDate" ).datepicker();
  });
</script>
<script type="text/javascript">
	function storePurchaseOrder(submissionType) {
		var validationSettings = {
				errorMessagePosition : 'element',
				borderColorOnError : '',
				scrollToTopOnError : true,
				dateFormat : 'yyyy/mm/dd'
			};
		
		if ($('#purchaseOrder_form').validate(false, validationSettings)) {	
			$("#save_PurchaseOrder").attr("disabled", "disabled");
     		$( "#save_PurchaseOrder" ).addClass('button').val('Processing..');
			$("#loading").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("resources/images/loading.gif"); background-position:center; background-repeat:no-repeat; background-size:50px;');

		var frm = $('#purchaseOrder_form').serializeFormJSON();
		 var con = JSON.stringify(frm);
		 con = con.replace(/[{}]/g, "");
		 
		 
		 var form = $('#purchaseOrder_form')[0];
  		 var formData = new FormData(form);
  		 var operoxUrl = '${operoxUrl}';
		 $.ajax({
				type : "POST",
     	    data: formData,
     	    contentType : 'application/json; charset=utf-8',
     	    enctype: 'multipart/form-data',
 	        processData: false,
 	        contentType:false,
     	    dataType : 'json',
				url : operoxUrl+"/savePurchaseOrder?${_csrf.parameterName}=${_csrf.token}&json="+con+"&submissionType="+submissionType,
				success : function(result) {
					location.replace(operoxUrl
							+ "/purchaseorderDashboard");

				},
			}) ;
		 
			return true;
		} else {
			return false;
		}
		 
	}
	$('body').on('blur', '#purchaseOrder_form', function() {
		$("#purchaseOrder_form").showHelpOnFocus();
		$("#purchaseOrder_form").validateOnBlur(false, validationSettings);
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
		divTsTd2Str = divTsTd2Str.replace(/PRODUCTNAMEVALUE/g,(productName)); 
		divTsTd2Str = divTsTd2Str.replace(/PRODUCTIDVALUE/g,(productId)); 
		cell1.innerHTML = divTsTd2Str;

		var cell2 = row.insertCell(2);
		var divTsTd3Str = document.getElementById("divTaskTd3").innerHTML;
		divTsTd3Str = divTsTd3Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd3Str = divTsTd3Str.replace(/QUANTITYVALUE/g,(quantity));
		cell2.innerHTML = divTsTd3Str;
		
		var cell3 = row.insertCell(3);
		var divTsTd4Str = document.getElementById("divTaskTd4").innerHTML;
		divTsTd4Str = divTsTd4Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell3.innerHTML = divTsTd4Str;
		renumberRows();
		
		$('select#searchId option').removeAttr("selected");
		$('select#productId option').removeAttr("selected");
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



