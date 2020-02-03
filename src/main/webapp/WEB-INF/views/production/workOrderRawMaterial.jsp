<link rel="stylesheet" href="${operoxUrl}/resources/css/chosen.min.css">
<script src="${operoxUrl}/resources/js/chosen.jquery.min.js"></script> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>
   
  

<script>
function getRawMaterial(workOrderId)
{
   	    var result = ""
		result = result + '<div class="clearfix">';
		result = result  +'	<form id="clinetContact_form" name="clinetContact_form" method="post">';
		result = result + '<input type="hidden" id="maxTsRowNum" name="maxTsRowNum" value="${workOrderRawMaterialsList.size()+1}" />';
		result = result + '<input type="hidden" id="workOrderId" name="workOrderId" value='+workOrderId+' />';
		result = result + '<div class="clearfix workorder mt">';
			result = result + '<div class="div33">';
				result = result + '<label class="labl">Select Product</label>';
				result = result + '<span>';
				result = result + '<select class="chosen-select" onchange="addProduct(this);" id="productId" value="">';
				result = result + '<option value="" selected>--Select Prodcut--</option>';
				result = result + "<c:forEach var='opt' items='${workOrderItemsList}'> ";
				result = result + "<option value='${opt.id}&${opt.product.productName}'>${opt.product.productName}</option>";
				result = result + "</c:forEach>";
				result = result + '</select>';
				result = result + '</span>';
			result = result + '</div>';
		result = result + '</div>';
		 
		result = result + '<div class="data_table extra-table" >';
				result = result + '<table id="productTbl">';
						result = result + '<tr id="divTsRow0">';
						result = result + '<th>Sl.No</th>';
						result = result + '<th>Product Name</th>';
						result = result + '<th>Material</th>';
						result = result + '<th>Supplier</th>';
						result = result + '<th>Qty</th>';
						result = result + '<th>Price</th>';
						result = result + '<th>Total Amount</th>';
						result = result + '<th class="action">Action</th>';
						result = result + '</tr>';
						result = result + '<div id="dataDiv">';
						<c:forEach var="opt" items="${workOrderRawMaterialsList}" varStatus="count">
							result = result + '<tr id="divTsRow${count.index+1}">';
							result = result + '<td><div class="ta-m">${count.index+1}</td>';
							result = result + '<td><div class="ta-m">${opt.productName}</div></td>';
							
							result = result + '<input type="hidden" id="${count.count}productName" name="${count.count}productName" value="${opt.productName}"/>';
							result = result + '<input type="hidden" id="${count.count}workOrderItemsId" name="${count.count}workOrderItemsId" value="${opt.workOrderItems.id}"/>';
							result = result + "<input type='hidden' class='productPriceCalculation' id='${count.index+1}' name='id' value='${opt.id}'/>";
							/* result = result + "<input type='hidden' id='${count.count}avilableId' name='${count.count}avilableId' value='${opt.id}'/>"; */
							
							result = result + '<td>';
						    result = result + '<div>';
								result = result + ' <select class="selList" id="${count.index+1}MaterialId" name="${count.count}MaterialId" >';
								result = result + '<option value="">--Select Material--</option>';
								result = result + '<c:forEach var="material" items="${rawMaterialsList}">';
								result = result + '<option value="${material.id}&${material.unitPrice}" ${material.id  == opt.rawMaterial.id ? "selected" : ''}>${material.materialName}</option>';
								result = result + '</c:forEach>';
								result = result + '</select>';
							result = result + ' </div>	';
						  result = result + '</td>'
							
							result = result + '<td>';
							    result = result + '<div>';
									result = result + ' <select class="selList" id="${count.count}supplierId" name="${count.count}supplierId" >';
									result = result + '<option value="">--Select Supplier--</option>';
									result = result + '<c:forEach var="option" items="${suppliersList}">';
									result = result + '<option value="${option.id}" ${option.id  == opt.supplier.id ? "selected" : ''}>${option.name}</option>';
									result = result + '</c:forEach>';
									result = result + '</select>';
								result = result + ' </div>	';
							result = result + '</td>';
							
							result = result + '<td><div class="ta-m"><input class="totalQuantityClass" value="${opt.quantity}" type="text" id="${count.index+1}quantity" name="${count.count}quantity" field-name="Quantity" data-validation="required validate_int"></div></td>';
							result = result + '<td><div class="ta-m"><input   value="${opt.price}" type="text" id="${count.index+1}price" name="${count.count}price" readonly="readonly"></div></td>';
							result = result + '<td><div class="ta-m"><input class="totalClass" value="${opt.totalAmount}" type="text" id="${count.count}totalAmount" name="${count.count}totalAmount" readonly="readonly"></div></td>';
							result = result + '<td><div><a href="#" onclick="removeProduct(${count.index+1});"><i class="glyphicon glyphicon-trash deme"></i></a></div></td>';
							result = result + '</tr>';
						</c:forEach> 
						result = result + '</tbody>';
						result = result + '</table>';
						result = result + '<table>';
						result = result + '<tfoot>';
						result = result + '<tr>';
						result = result + '<td><div class="ta-m pull-right"><strong>Total:</strong></div></td>';
						result = result + '<td><div class="ta-m"><span id="toatlQuantity"></span></div></td>';
						result = result + '<td><div class="ta-m"><span id="grandTotal"></span></div></td>';
						result = result + '</tr>';
						result = result + '</tfoot>';
						result = result + '</tbody>';
				result = result + '</table>';
		result = result + '</div> ';
		
		result = result + '<div class="form-footer mt clearfix">';
		result = result + '<div class="col-lg-12">';
		result = result + '<div class="pull-right">';
		result = result + '<input value="Save" class="btn btn-primary" id="save_rawMaterial" type="button" onclick="saveWorkOrderRawMaterial('+workOrderId+');">';
		/* result = result + '<input value="Cancel" class="btn btn-default ml10" type="button">'; */
		result = result + '</div>';
		result = result + '</div>';
		result = result + '</div>';
		
		
		result = result + '</div>';
		result = result  +'	        </form> ';
		$("#productionPlanning").empty();  
		$("#productionPlanning").append(result);  
		
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
}

</script>



<div id="divTaskTd1" style="display: none; float: left;">
   	<div class="ta-sm">ROWNUMBERVALUE</div>
</div>
<div id="divTaskTd2" style="display: none; float: left;">
   	<div class="ta-sm">PRODUCTNAME</div>
   	<input type="hidden" id="REPLACEROWIDproductName" name="REPLACEROWIDproductName" value="PRODUCTNAME"/>
   	<input type="hidden" id="REPLACEROWIDworkOrderItemsId" name="REPLACEROWIDworkOrderItemsId" value="WORKORDERITEMSIDVALUE"/> 
</div> 
<div id="divTaskTd3" style="display: none; float: left;">
   <div>
       <select class="selList" id="REPLACEROWIDMaterialId" name="REPLACEROWIDMaterialId" field-name="Material" data-validation="required">
			<option value="">--Select Material--</option>
			 <c:forEach var="material" items="${rawMaterialsList}">
				<option value="${material.id}&${material.unitPrice}">${material.materialName}</option>
			</c:forEach>
	  </select>
	</div>  
</div> 
<div id="divTaskTd4" style="display: none; float: left;">
     <div>
       <select class="selList" id="REPLACEROWIDsupplierId" name="REPLACEROWIDsupplierId" >
		<option value="">--Select Supplier--</option>
	         <c:forEach var="option" items="${suppliersList}">
				<option value="${option.id}">${option.name}</option>
			</c:forEach>
		</select>
     </div>		
</div>

<div id="divTaskTd5" style="display: none; float: left;">
		 <div class="ta-m"> 
		  <input type="hidden" class="PRICECALCULATION" id="REPLACEROWID" /> 
		  <input value="" type="text" id="REPLACEROWIDquantity" name="REPLACEROWIDquantity" class="totalQuantityClass" field-name="Quantity" data-validation="required validate_int"></div>
</div> 

<div id="divTaskTd6" style="display: none; float: left;">
		  <div class="ta-m"><input value="" type="text" id="REPLACEROWIDprice" name="REPLACEROWIDprice" readonly="readonly"></div>
</div> 

<div id="divTaskTd7" style="display: none; float: left;">
		  <div class="ta-m"><input value="" type="text" id="REPLACEROWIDtotalAmount" name="REPLACEROWIDtotalAmount" class="totalClass" readonly="readonly"></div>
</div> 

<div id="divTaskTd8" style="display: none; float: left;">
	 <div>
         <a href="#" onclick="removeProduct(REPLACEROWID);"><i class="glyphicon glyphicon-trash deme"></i></a>
    </div>
</div>



<script type="text/javascript">
	function addProduct(local) {
		
		if(local.value != null && local.value != 'undefined' && local.value != ''){
		
		var products = [];
		products = local.value.split("&");
		var workOrderItemsId = products[0];
		var productName = products[1];
		
		var checkMaxTsRowNum = $("#maxTsRowNum").val();
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
		divTsTd2Str = divTsTd2Str.replace(/PRODUCTNAME/g,(productName)); 
		divTsTd2Str = divTsTd2Str.replace(/WORKORDERITEMSIDVALUE/g,(workOrderItemsId)); 
		
		cell1.innerHTML = divTsTd2Str;

		var cell2 = row.insertCell(2);
		var divTsTd3Str = document.getElementById("divTaskTd3").innerHTML;
		divTsTd3Str = divTsTd3Str.replace(/REPLACEROWID/g, (maxRowNum));
		/* divTsTd3Str = divTsTd3Str.replace(/PRODUCTNAMEVALUE/g,(productName));
		divTsTd3Str = divTsTd3Str.replace(/PRODUCTIDVALUE/g,(productId)); */
		cell2.innerHTML = divTsTd3Str;
		
		var cell3 = row.insertCell(3);
		var divTsTd4Str = document.getElementById("divTaskTd4").innerHTML;
		divTsTd4Str = divTsTd4Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd4Str = divTsTd4Str.replace(/BATCHIDVALUE/g,(""));
		cell3.innerHTML = divTsTd4Str;  
		
		var cell4 = row.insertCell(4);
		var divTsTd5Str = document.getElementById("divTaskTd5").innerHTML;
		divTsTd5Str = divTsTd5Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd5Str = divTsTd5Str.replace(/PRICECALCULATION/g,("productPriceCalculation")); 
		cell4.innerHTML = divTsTd5Str;  
		
		var cell5 = row.insertCell(5);
		var divTsTd6Str = document.getElementById("divTaskTd6").innerHTML;
		divTsTd6Str = divTsTd6Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell5.innerHTML = divTsTd6Str;  
		
		var cell6 = row.insertCell(6);
		var divTsTd7Str = document.getElementById("divTaskTd7").innerHTML;
		divTsTd7Str = divTsTd7Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell6.innerHTML = divTsTd7Str; 
	    
		
		var cell7 = row.insertCell(7);
		var divTsTd8Str = document.getElementById("divTaskTd8").innerHTML;
		divTsTd8Str = divTsTd8Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell7.innerHTML = divTsTd8Str;   
		 
		renumberRows(); 
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
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };

        
</script> 


<script type="text/javascript">
	function saveWorkOrderRawMaterial(id) {
		if ($('#clinetContact_form').validate(false, validationSettings)) {
			var frm = $('#clinetContact_form').serializeFormJSON();
			var con = JSON.stringify(frm);
			con = con.replace(/[{}]/g, "");
			 var jsonData = encodeURIComponent(con);
			 var operoxUrl ='${operoxUrl}';
			 $("#save_rawMaterial").attr("disabled", "disabled");
	     		$( "#save_rawMaterial" ).addClass('button').val('Processing..');
		         $.ajax({
			    	type: "POST",
			    	 url: operoxUrl+"/saveworkOrderRawMaterial?${_csrf.parameterName}=${_csrf.token}&json="+jsonData, 
			        success: function(result) {
			        	 $("#save_rawMaterial").removeAttr("disabled");
			        	 $("#save_rawMaterial" ).addClass('btn btn-primary').val('Save');
			        	if((result == 'Success')){
				        	location.replace(operoxUrl+"/viewWorkOrder/"+id+"/raw"); 
				          } 
				          
			        },
			    }); 
			
			
			return true;
		} else {
			return false;
		}
		
	}
	$('body').on('blur', '#clinetContact_form', function() {
		$("#clinetContact_form").showHelpOnFocus();
		$("#clinetContact_form").validateOnBlur(false, validationSettings);
	}); 
</script>




<!-- this is for price calculation --> 
<script>
 $(document).click(function(){ 
	$('.productPriceCalculation').each(function() {
			var localId = this.id;
			var price = $("#"+localId+"price").val();
			var quantity = $("#"+localId+"quantity").val();
			
			var materialDropDown = $("#"+localId+"MaterialId").val();
			if(materialDropDown != null && materialDropDown != 'undefined' && materialDropDown != ''){
				var splitValue = materialDropDown.split("&")
				var materialUnitPrice = splitValue[1];
				
				if(quantity != null && quantity != 'undefined' && quantity != '' && materialUnitPrice != null && materialUnitPrice != 'undefined' && materialUnitPrice != ''){
					$("#"+localId+"price").val(materialUnitPrice);  
					$("#"+localId+"totalAmount").val(parseFloat(quantity) * parseFloat(materialUnitPrice));  
				} 
				
			}else if(quantity != null && quantity != 'undefined' && quantity != '' && price != null && price != 'undefined' && price != ''){
				 $("#"+localId+"totalAmount").val(parseFloat(quantity) * parseFloat(price));  
			}
			grandTotal ();
			
	});  
}); 

</script>	

<script>
function grandTotal (){
	//grand Total
	var grandAmount=0; 
	$('.totalClass').each(function() {
		 var localValue = this.value;
		 if(localValue != null && localValue != '' && localValue !='undefined' && !isNaN(localValue)){
		  		 grandAmount = parseFloat(grandAmount) + parseFloat(localValue);
		 }
	 });  
	 if(grandAmount != null && grandAmount != '' && grandAmount !='undefined' && !isNaN(grandAmount)){
		 $("#grandTotal").html(parseFloat(grandAmount));
     }
	 
	 var grandQuantity=0; 
		$('.totalQuantityClass').each(function() {
			 var localValue = this.value;
			 if(localValue != null && localValue != '' && localValue !='undefined' && !isNaN(localValue)){
				 grandQuantity = parseInt(grandQuantity) + parseInt(localValue);
			 }
		 });  
		
		 if(grandQuantity != null && grandQuantity != '' && grandQuantity !='undefined' && !isNaN(grandQuantity)){
			 $("#toatlQuantity").html(parseInt(grandQuantity));
	     }
	 
	 
}

</script>

