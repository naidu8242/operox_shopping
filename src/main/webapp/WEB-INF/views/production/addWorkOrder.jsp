<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<div class="wraper clearfix">

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Add Work Order</h2>
    </div>
    <div class="pull-right brud-crum"><a href="javascript:location.href = '<c:url value='/workOrder'/>'">Work Order List</a> &raquo; Add Work Orde</div>
  </section>
  
  <!-- Content Area inner -->
  <form:form id="addWorkOrder_form"  name="addWorkOrder_form" method="post">
  <div class="content-area clearfix">
  
    <div class="div33" id="WorkOrderIdDiv">
       <label class="labl">Work order Id &nbsp;<b class="text-danger">*</b></label>
       <span>
       <input type="text" name="workOrderId" id="workOrderId" data-validation = "required validate_Space" onblur="validateWorkOrderNumber();" field-name="Work Order Id" placeholder="Work Order Id">
       </span>
    </div>

    
    <div class="div33">
       <label class="labl">Customer</label>
       <span>
         <select class="chosen-select" id="customer" name="customer" field-name="Customer" data-validation-optional="true" >
         <option value="">--Select Customer--</option>
         <c:forEach var="customer" items="${customersList}">
               <option value="${customer.id}">${customer.customerName}</option>
         </c:forEach>
         </select>
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Store&nbsp;<b class="text-danger">*</label>
       <span>
         <select class="chosen-select" id="store" name="store" field-name="Store" data-validation="required" >
         <option value="">-Select Store-</option>
         <c:forEach var="store" items="${storesList}">
               <option value="${store.id}">${store.storeName}</option>
         </c:forEach>
         </select>
       </span>
    </div>
    
      <div class="div33">
       <label class="labl">PO Id</label>
       <span>
       <input type="text" placeholder="Purchase order Id" id="purchaseOrderId" name="purchaseOrderId" field-name="Purchase order Id" data-validation = "validate_Space" data-validation-optional="true">
       </span>
    </div>
    
      <div class="div33">
       <label class="labl">Order Date &nbsp;<b class="text-danger">*</b></label>
       <span>
       <input type="text" placeholder="MM/DD/YYY" class="datefild" id="orderdate" name="orderDate" field-name="Order Date" readonly="readonly" data-validation="required">
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Due Date</label>
       <span>
       <input type="text" placeholder="MM/DD/YYY" class="datefild" id="dueDt" name="dueDate" field-name="Due Date" readonly="readonly">
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Despatched on</label>
       <span>
       <input type="text" placeholder="MM/DD/YYY" class="datefild" id="despatchedDt" name="dispatchedDate" readonly="readonly">
       </span>
    </div>
    
<div class="div33">
       <label class="labl">Approved by</label>
       <span>
         <select class="chosen-select" id="approver" name="approver" field-name="Approver" data-validation-optional="true" >
         <option value="">--Select Approver--</option>
         <c:forEach var="user" items="${approversList}">
               <option value="${user.id}">${user.firstName} ${user.lastName} </option>
         </c:forEach>
         </select>
       </span>
    </div>
        
    <div class="div33">
       <label class="labl">Status &nbsp;<b class="text-danger">*</b></label>
       <span>
       <select class="chosen-select" id="status" name="status" field-name="Status"  data-validation-optional="true"> 
          <option selected disabled>New</option>
          <option>New</option>
          <option>Inprogress</option>
          <option>Pending</option>
          <option>Cancel</option>
          <option>Completed</option>
         </select>
       </span>
    </div>
    <div class="div33">
       <label class="labl">Comments</label>
       <span>
      <textarea rows="2" id="commants" name="commants" field-name="Commants" placeholder="Comments"></textarea>
       </span>
    </div>

    <div class="clearfix"></div>
      <!-- Content Area inner -->
  <!-- Table Designa addstock -->
  <div class="clearfix">
  <div class="clearfix workorder mt">
    <div class="div33">
       <input type="hidden" id="maxTsRowNum" name="maxTsRowNum" value="1" />
       <label class="labl">Select Item</label>
       <span>
          <select class="chosen-select" onchange="addProduct(this);" id="productId" value="" >
          <option value="" selected>--Select Product--</option>
          <c:forEach var="product" items="${productsList}">
		     <option value="${product.id}&${product.productName}">${product.productName}
			</option>
		 </c:forEach>
         </select>
       </span>
    </div> 
    </div>
    
    <div class="clearfix">
    <div class="data_table more-table">
    	<table class="table table-bordered table-striped  clearfix" id="productTbl">
            	<tr id="divTsRow0">
                	<th>Sl.No</th>
                    <th>Product Name</th>
                	<th>Batch Id</th>
                    <th>Total Qty&nbsp;&nbsp;<span class="text-danger">*</span></th>
                    <th>Awaiting for QC</th>
                    <th>Status</th>
                    <th class="action">Action</th>
                </tr>
        </table>
        
<!-- =============================== AddTask Divs  ============================ -->
<div id="divTaskTd1" style="display: none; float: left;">
    <div id="REPLACEROWIDrowNumber" name="REPLACEROWIDrowNumber"  class="tbldata-dotdot-vsm"><span>ROWNUMBERVALUE</span></div>
</div>
<div id="divTaskTd2" style="display: none; float: left;">
    <div id="REPLACEROWIDproductName" name="REPLACEROWIDproductName" class="tbldata-dotdot-vsm"><span>PRODUCTNAMEVALUE</span></div>
    <input type="hidden" id="REPLACEROWIDproductId" name="REPLACEROWIDproductId" value="PRODUCTIDVALUE" />
</div>
<div id="divTaskTd3" style="display: none; float: left;">  
   <input type="text" id="REPLACEROWIDbatchId"
		name="REPLACEROWIDbatchId" value="BATCHIDVALUE" /> 
</div>
<div id="divTaskTd4" style="display: none; float: left;">  
   <input type="text" id="REPLACEROWIDtotalqty"
		name="REPLACEROWIDtotalqty" value="TOTALQTYVALUE" data-validation="VALIDATIONCONDITION" field-name="Quantity" /> 
</div>
<div id="divTaskTd5" style="display: none; float: left;">  
   <input type="text" id="REPLACEROWIDwaitingforqc"
		name="REPLACEROWIDwaitingforqc" value="WAITINGFORQCVALUE" data-validation="VALIDATIONCONDITION" /> 
</div>
<div id="divTaskTd6" style="display: none; float: left;">  
   <div class="ta-m"><select type="select" id="REPLACEROWIDstatus" name="REPLACEROWIDstatus" value="STATUSVALUE">
		<option selected disabled>New</option>
		<option>New</option>
		<option>Inprogress</option>
		<option>Pending</option>
		<option>Cancel</option>
		<option>Completed</option>
	</select></div>
</div>
<div id="divTaskTd7" style="display: none; float: left;">
    <div>
         <a href="#" onclick="removeProduct(REPLACEROWID);"><i class="glyphicon glyphicon-trash deme"></i></a>
    </div>
</div>
        
  </div> 
  </div>
    
    
  </div>
  <!-- Table Designa addstock ends -->
    <div class="clearfix mt25"></div>
  </div>
  
  <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right">
          <input type="button" value="Save" onclick="addWorkOrder();"  id="add_workOrder" class="btn btn-success">
          <input type="button" value="Cancel" onClick="javascript:location.href = '${operoxUrl}/workOrder'" class="btn btn-default ml10">
        </div>
      </div>
    </div>
  </form:form>
</section>
<!-- Content Area Ends--> 

</div>


<script>
function validateWorkOrderNumber(){
	var workOrderId = document.getElementById("workOrderId").value;
	if(workOrderId){
		 var operoxUrl ='${operoxUrl}';
		 $.ajax({
		    	type: "POST",
		    	 url: operoxUrl+"/validateWorkOrderNumber?${_csrf.parameterName}=${_csrf.token}&workOrderId="+workOrderId, 
		        success: function(result) {
		        	if(result == 'valid'){
			    		$("#add_workOrder").removeAttr("disabled");
			    		$('input#workOrderId').removeAttr( "class record-exist record-exist-errormsg");
						$('#WorkOrderIdDiv').find('p.jquery_form_error_message').remove();
						$('input#workOrderId').attr('class','form-control');
						$( "#add_workOrder" ).removeAttr("style");
						$( "#add_workOrder" ).addClass('btn btn-success').val('Save');
						$( "#add_workOrder" ).click(function() {
						});
						
			    	}
			    	else if(result == 'invalid'){
		    		$("#add_workOrder").attr("disabled", "disabled");
			    		$('#WorkOrderIdDiv').find('p.jquery_form_error_message').remove(); 
			    		$('input#workOrderId').attr('class','error form-control');
			    		$("input#workOrderId").after("<p class='jquery_form_error_message'>Existed Work Order Id</p>");
						$('input#workOrderId').attr('record-exist','yes');
						$('input#workOrderId').attr('record-exist-errorMsg',' Existed Work Order Id'); 
						$("#add_workOrder" ).addClass('btn btn-success').val('Save');
			    	} 
		        },
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
		
		var quantity = '1';
		var batchId = '';
		var waitingfoqc = '0';
		
		
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
		divTsTd2Str = divTsTd2Str.replace(/PRODUCTNAMEVALUE/g,(productName)); 
		divTsTd2Str = divTsTd2Str.replace(/PRODUCTIDVALUE/g,(productId)); 
		cell1.innerHTML = divTsTd2Str;

		var cell2 = row.insertCell(2);
		var divTsTd3Str = document.getElementById("divTaskTd3").innerHTML;
		divTsTd3Str = divTsTd3Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd3Str = divTsTd3Str.replace(/BATCHIDVALUE/g,(batchId));
		cell2.innerHTML = divTsTd3Str;
		
		
		var cell3 = row.insertCell(3);
		var divTsTd4Str = document.getElementById("divTaskTd4").innerHTML;
		divTsTd4Str = divTsTd4Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd4Str = divTsTd4Str.replace(/VALIDATIONCONDITION/g,("required validate_int"));
		divTsTd4Str = divTsTd4Str.replace(/TOTALQTYVALUE/g,(quantity));
		cell3.innerHTML = divTsTd4Str;
		
		var cell4 = row.insertCell(4);
		var divTsTd5Str = document.getElementById("divTaskTd5").innerHTML;
		divTsTd5Str = divTsTd5Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd5Str = divTsTd5Str.replace(/VALIDATIONCONDITION/g,("validate_int"));
		divTsTd5Str = divTsTd5Str.replace(/WAITINGFORQCVALUE/g,(waitingfoqc));
		cell4.innerHTML = divTsTd5Str;
		
		
		var cell5 = row.insertCell(5);
		var divTsTd6Str = document.getElementById("divTaskTd6").innerHTML;
		divTsTd6Str = divTsTd6Str.replace(/REPLACEROWID/g, (maxRowNum));
		divTsTd6Str = divTsTd6Str.replace(/STATUSVALUE/g,(status));
		cell5.innerHTML = divTsTd6Str;
		
		
		
		var cell6 = row.insertCell(6);
		var divTsTd7Str = document.getElementById("divTaskTd7").innerHTML;
		divTsTd7Str = divTsTd7Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell6.innerHTML = divTsTd7Str;
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
	function addWorkOrder() {
		var validationSettings = {
			errorMessagePosition : 'element',
			borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd'
		};
		
		if ($('#addWorkOrder_form').validate(false, validationSettings)) {
			
		    var frm = $('#addWorkOrder_form').serializeFormJSON();
	        var con = JSON.stringify(frm);
	        con = con.replace(/[{}]/g, "");
	        var jsonData = encodeURIComponent(con);
	        
	        var operoxUrl ='${operoxUrl}';
     		$( "#add_workOrder" ).addClass('button').val('Processing..');
	         $.ajax({
		    	type: "POST",
		    	 url: operoxUrl+"/saveWorkOrder?${_csrf.parameterName}=${_csrf.token}&json="+jsonData, 
		        success: function(result) {
		        	if((result == 'Success')){
		        		location.replace(operoxUrl+"/workOrder");  
		        	}
			        
		        },
		    });  
			
			return true;
		} else {
			return false;
		}
	}
	$('body').on('blur', '#addWorkOrder_form', function() {
		$("#addWorkOrder_form").showHelpOnFocus();
		$("#addWorkOrder_form").validateOnBlur(false, validationSettings);
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
  $( function() {
    $( "#appddate, #orderdate, #despatchedDt, #dueDt" ).datepicker();
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


<jsp:include page="../masterFooter.jsp" />