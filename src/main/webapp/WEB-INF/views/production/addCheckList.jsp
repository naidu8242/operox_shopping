<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<!-- Content Area -->
  
  <!-- Content Area inner -->
 <section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Add Checklist</h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; Add Checklist</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix">
  
    <div class="div33">
       <label class="labl">Checklist Type &nbsp;<b class="text-danger">*</b></label>
       
       <span class="select-fild">
                	<select id="addCheckLists" onchange="listChange(this);">
                  		 <option value="rawmaterial">Raw Material</option>
                        <option value="product" selected="selected">Product</option> 
                    </select>
                </span> 
                
       <!-- <span>
        <select id="checklistMT">
         <option value="rawmaterial">Raw Material</option>
         <option value="product">Product</option>
        </select>
       </span> -->
    </div>

      <!-- Raw Material -->
    <div class="clearfix"></div>
<div class="clearfix cklmt" id="rawmaterialDiv" style= "display:block";>
<form:form id="addRawMaterialCheckList_form" name="addRawMaterialCheckList_form" method="post">
 <input type="hidden" id="checkListType" name="checkListType" value="RawMaterial"/>
 <p class="mb"><strong>Checklist For Raw Material</strong></p>
    <div class="div33">
       <label class="labl">Checklist Name&nbsp;<b class="text-danger">*</b></label>
       <span>
         <input type="text" placeholder="Checklist Name" id="Raw Checklist Name" field-name="Checklist Name" name="rawChecklistName" data-validation="required validate_Space ">
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Store</label>
       <span>
         <select class="chosen-select" id="rawStore" name="rawStore" field-name="Store" data-validation="required validate_Space ">
         <option value="">--Select Store--</option>
         <c:forEach var="store" items="${storesList}">
               <option value="${store.id}">${store.storeName}</option>
         </c:forEach>
         </select>
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Raw Material&nbsp;<b class="text-danger">*</b></label>
       <span>
        <select id="rawMaterial" name="rawMaterial"  field-name="Raw Material">
        <option value="">---Select Raw Material--</option>
         <c:forEach var="rawMaterial" items="${rawMaterialList}">
               <option value="${rawMaterial.id}">${rawMaterial.materialName}</option>
         </c:forEach>
         </select>
       </span>
    </div>
    <div class="div33" id="uploadFileGroup">
      <label class="labl">Attachment</label>
      <!-- upload file -->
      <span class="custom-upload">
           <input type="file" name="uploadBtn" id="uploadBtn" >
           <div class="fake-file">
               <input  type="text" class="form-control" readonly id="uploadFile" name="docFile"  field-name="Document"  placeholder="Upload File..." value="">
           </div>
       </span>
      <!-- upload file -->
   </div>
   
    <div class="div33">
       <label class="labl">Description&nbsp;<b class="text-danger">*</b></label>
       <span>
      <textarea rows="2" placeholder="Description" id="rawDescription" field-name="Raw Description" name="rawDescription" data-validation="required validate_Space "></textarea>
       </span>
    </div>
      <div class="clearfix"></div>        
 <!-- Table Design Raw Material -->
 
    <p class="clearfix addTest"><a href="#" class="btn btn-primary btn-sm pull-right"  onclick="addRawMaterial(this);"  title="Add Test"><i class="glyphicon glyphicon-plus"></i>&nbsp;Add Test</a></p>
    <input type="hidden" id="maxTestRowNum" name="maxTestRowNum" value="1" />
    <div class="data_table more-table position-rel" >
    	<table id="RawMaterialTbl">
        	<tbody>
            	<tr>
                	<th>S.No.</th>
                	<th>Test&nbsp;<b class="text-danger">*</b></th>
                    <th>Procedure</th>
                    <th>Value</th>
                    <th class="action">Action</th>
                </tr>
            	<tr>
               <!--  <td><div class="ta-sm">004</div></td>
                <td><div class="ta-sm"><input type="text" placeholder="Test" id="rawTest" data-validation="required validate_Space"></div></td>
                <td><div class="ta-sl"><input type="text" placeholder="Procedure" id="rawProcedure"></div></td>
                <td><div class="ta-sm"><input type="text" placeholder="Value" id="rawValue"></div></td> -->
				</tr>
            </tbody>
        </table>
    </div> 
  <!--Table Design Raw Material ends -->
    <div class="clearfix"></div>
      <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right custom-buttons">
        <input type="button" value="Save" class="btn btn-primary" id="save_raw_material" onclick="saveRawMaterialTask();">
        <input type="button" value="Cancel" class="btn btn-default" id="can" onclick="cancelCheckList();">
<!--          <a href="#" id="can" class="btn btn-default"  onClick="cancelCheckList();" > -->
<!--            Cancel -->
<!--           </a> -->
        </div>
      </div>
    </div>
</form:form>
</div>
    <div class="clearfix"></div>
      <!-- Raw Material Ends -->
      
 <div id="divRawTaskTd1" style="display: none; float: left;">
   	<div class="ta-sm">ROWNUMBERVALUE</div>
</div>
<div id="divRawTaskTd2" style="display: none; float: left;">
   	<input type="text" id="REPLACEROWIDrawTest" name="REPLACEROWIDrawTest" value="" data-validation="required validate_Space " field-name ="Test Nmae"/>
</div> 
<div id="divRawTaskTd3" style="display: none; float: left;">
   <div>
      <input type="text" id="REPLACEROWIDProcedure" name="REPLACEROWIDrawProcedure"  value=""/>
	</div>  
</div> 
<div id="divRawTaskTd4" style="display: none; float: left;">
     <div>
      <textarea rows="2" id="REPLACEROWIDValue" name="REPLACEROWIDrawValue"></textarea>
     </div>		
</div>
<div id="divRawTaskTd5" style="display: none; float: left;">
	 <div>
         <a href="#" onclick="removeProduct(REPLACEROWID);"><i class="glyphicon glyphicon-trash deme"></i></a>
    </div>
</div>

      <!-- Product Material -->
<div class="clearfix"></div>
<div class="clearfix cklmt" id="productDiv" style= "display:none";>
<form:form id="addProductCheckList_form" name="addProductCheckList_form" method="post">
 <input type="hidden" id="checkListType" name="checkListType" value="Products"/>
 <p class="mb"><strong>Checklist For Product</strong></p>
    <div class="div33">
       <label class="labl">Checklist Name&nbsp;<b class="text-danger">*</b></label>
       <span>
        <input type="text" placeholder="Checklist Name" id="Product Checklist Name" name="productChecklistName">
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Store</label>
       <span>
         <select class="chosen-select" id="productStore" name="productStore" field-name="Store" >
         <option value="">--Select Store--</option>
         <c:forEach var="store" items="${storesList}">
               <option value="${store.id}">${store.storeName}</option>
         </c:forEach>
         </select>
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Product&nbsp;<b class="text-danger">*</b></label>
       <span>
        <select  id="products" name="products" field-name="Product">
         <option value="">--Select Products--</option>
         <c:forEach var="product" items="${products}">
               <option value="${product.id}">${product.productName}</option>
         </c:forEach>
        </select>
       </span>
    </div>
    <div class="div33" id="productUploadFileGroup">
      <label class="labl">Attachment</label>
      <!-- upload file -->
      <span class="custom-upload">
           <input type="file" name="productUploadBtn" id="productUploadBtn" >
           <div class="fake-file">
               <input  type="text" class="form-control" readonly id="productUploadFile" name="productDocFile" placeholder="Upload File...">
           </div>
       </span>
      <!-- upload file -->
   </div>
    <div class="div33">
       <label class="labl">Description&nbsp;<b class="text-danger">*</b></label>
       <span>
      <textarea rows="2" placeholder="Description" id="productDescription" name="productDescription"></textarea>
       </span>
    </div>
      <div class="clearfix"></div>        
 <!-- Table Design Raw Material -->
    <p class="clearfix addTest"><a href="#" class="btn btn-primary btn-sm pull-right" onclick="addProductTest(this);" title="Add Test"><i class="glyphicon glyphicon-plus"></i>&nbsp;Add Test</a></p>
     <input type="hidden" id="maxTestRowNumForProducts" name="maxTestRowNumForProducts" value="1" />
    <div class="data_table more-table position-rel" id="addTaskDiv">
    	<table id="ProductTestTbl">
        	<tbody>
            	<tr>
                	<th>S.No.</th>
                	<th>Test&nbsp;<b class="text-danger">*</b></th>
                    <th>Procedure</th>
                    <th>Value</th>
                    <th class="action">Action</th>
                </tr>
            	<tr>
          
                </tr>
            </tbody>
        </table>
    </div> 
  <!--Table Design Raw Material ends -->
    <div class="clearfix"></div>
      <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right custom-buttons">
         <a href="#" id="save_product" class="btn btn-primary" onclick="saveProductTask();">
           Save
          </a>
        <input type="button" value="Cancel" class="btn btn-default" id="can" onclick="cancelCheckList();">
        </div>
      </div>
    </div>
    </form:form>
</div>

    <div class="clearfix"></div>
      <!-- Product Material -->
<div id="divProductTaskTd1" style="display: none; float: left;">
   	<div class="ta-sm">ROWNUMBERVALUE</div>
</div>
<div id="divProductTaskTd2" style="display: none; float: left;">
   	<input type="text" id="REPLACEROWIDProductsTest" name="REPLACEROWIDProductsTest" value="" data-validation="required validate_Space " field-name="Test Name"/>
</div> 
<div id="divProductTaskTd3" style="display: none; float: left;">
   <div>
      <input type="text" id="REPLACEROWIDProductsTest" name="REPLACEROWIDProductsProcedure"  value=""/>
	</div>  
</div> 
<div id="divProductTaskTd4" style="display: none; float: left;">
     <div>
      <textarea rows="2" id="REPLACEROWIDProductsValue" name="REPLACEROWIDProductsValue"></textarea>
     </div>		
</div>
<div id="divProductTaskTd5" style="display: none; float: left;">
	 <div>
         <a href="#" onclick="removeProductTest(REPLACEROWID);"><i class="glyphicon glyphicon-trash deme"></i></a>
    </div>
</div>
      <!-- Content Area inner -->
  </div>

</section>

<script type="text/javascript">
function renumberRows() { 
        $('#RawMaterialTbl tr:visible').each(function(index, el){ 
            $(this).children('td').first().text(index++);
        });
    }
</script>
<script type="text/javascript">
function updateNumberOfPackages() {
	var totalRow =  $('#RawMaterialTbl tr:visible').length; 
	$("#totalNumberOfProducts").val(totalRow-1);
}
</script>



<script type="text/javascript">
function renumberRowsForProducts() {
        $('#ProductTestTbl tr:visible').each(function(index, el){ 
            $(this).children('td').first().text(index++);
        });
    }
</script>
<script type="text/javascript">
function updateNumberOfPackagesForProducts() {
	var totalRow =  $('#ProductTestTbl tr:visible').length; 
	$("#totalNumberOfProduct").val(totalRow-1);
}
</script>
<script> 
$(document).ready(function(){
    listChange("rawmaterial");
	document.getElementById('addCheckLists').value = 'rawmaterial';
});
</script>
<script>
function listChange(sel){
	 if(sel.value=="product"){
		 document.getElementById("productDiv").style.display="block";
		 document.getElementById("rawmaterialDiv").style.display="none";
	 }
	 if(sel.value=="rawmaterial"){
		 document.getElementById("rawmaterialDiv").style.display="block";
		 document.getElementById("productDiv").style.display="none";
	 }
	 
}
</script>



<script type="text/javascript">
 var selectedFile = document.getElementById("productUploadBtn");
	    selectedFile.addEventListener("change", function() {
	      var docSize = this.files[0].size;
	      $("#productUploadFileGroup").find('p.jquery_form_error_message').remove();
	      if(docSize > 2097152){
	    	  document.getElementById("productUploadFile").value = '';
	    	  $("#productUploadFileGroup").append("<p class='jquery_form_error_message'>Limit is exceeded than 2MB </p>");
	      }
	    });
	    document.getElementById("productUploadBtn").onchange = function () {
		    document.getElementById("productUploadFile").value = this.value;
		    };
		    $('.custom-upload input[type=file]').change(function(){
		    	   $(this).next().find('input').val($(this).val());
		    	});
</script>
<script type="text/javascript">
 var selectedFile = document.getElementById("uploadBtn");
	    selectedFile.addEventListener("change", function() {
	      var docSize = this.files[0].size;
	      $("#uploadFileGroup").find('p.jquery_form_error_message').remove();
	      if(docSize > 2097152){
	    	  document.getElementById("uploadFile").value = '';
	    	  $("#uploadFileGroup").append("<p class='jquery_form_error_message'>Limit is exceeded than 2MB </p>");
	      }
	    });
	    document.getElementById("uploadBtn").onchange = function () {
		    document.getElementById("uploadFile").value = this.value;
		    };
		    $('.custom-upload input[type=file]').change(function(){
		    	   $(this).next().find('input').val($(this).val());
		    	});
</script>
<script type="text/javascript">
function addSub(){
   var subDivLength=$("#subDivLength").val();
   var maxRowNum = parseInt(subDivLength)+1;
   var divDsStr = document.getElementById("divds").innerHTML;
   divDsStr = divDsStr.replace(/REPLACEROWID/g, (maxRowNum));
   divDsStr = divDsStr.replace(/ValidationCondition/g,"required");
   $("#addSub").append(divDsStr);
   $("#subDivLength").val(maxRowNum);
}
</script>

<script type="text/javascript">
	function removeProduct(rowId) {
		
		var rowNum = $("#divTestRow"+rowId).val();
		var maxRowNum = parseInt(rowNum);
		 document.getElementById("divTestRow"+rowId).style.display = 'none';
		 document.getElementById("divTestRow"+rowId).value= '';
		 renumberRows(); 
		 var  rId = "divTestRow"+rowId;
		  var r = document.getElementById("RawMaterialTbl").rows[rId];
          var count = 2*(r.cells.length);
		    for(i=0;i<=count;i++){
		        r.deleteCell(0);
		    } 
		    
	}
</script>

<script type="text/javascript">
	function removeProductTest(rowId) {
		var rowNum = $("#divTestRowForProducts"+rowId).val();
		var maxRowNumForProducts = parseInt(rowNum);
		 document.getElementById("divTestRowForProducts"+rowId).style.display = 'none';
		 document.getElementById("divTestRowForProducts"+rowId).value= '';
		 renumberRows(); 
		 var  rId = "divTestRowForProducts"+rowId;
		  var r = document.getElementById("ProductTestTbl").rows[rId];
          var count = 2*(r.cells.length);
		    for(i=0;i<=count;i++){
		        r.deleteCell(0);
		    } 
		    
	}
</script>


<script type="text/javascript">
	function addRawMaterial(local) {
		
		var quantity = 1;
		var checkMaxTsRowNum = $("#maxTestRowNum").val();
		var tbl = document.getElementById("RawMaterialTbl");
		var maxRowNum = parseInt($("input[name = 'maxTestRowNum']").val());
		$("input[name = 'maxTestRowNum']").val(maxRowNum + 1);
		
		var row = tbl.insertRow(maxRowNum);
		row.setAttribute("id", "divTestRow" + maxRowNum);

	    
		var cell0 = row.insertCell(0);
		var divTsTd1Str = document.getElementById("divRawTaskTd1").innerHTML;
		divTsTd1Str = divTsTd1Str.replace(/REPLACEROWID/g, (maxRowNum));
	    divTsTd1Str = divTsTd1Str.replace(/ROWNUMBERVALUE/g, (maxRowNum)); 
		cell0.innerHTML = divTsTd1Str;
		
	 	var cell1 = row.insertCell(1);
		var divTsTd2Str = document.getElementById("divRawTaskTd2").innerHTML;
		divTsTd2Str = divTsTd2Str.replace(/REPLACEROWID/g, (maxRowNum));
		//divTsTd2Str = divTsTd2Str.replace(/TEST/g,("")); 
		cell1.innerHTML = divTsTd2Str;

		var cell2 = row.insertCell(2);
		var divTsTd3Str = document.getElementById("divRawTaskTd3").innerHTML;
		divTsTd3Str = divTsTd3Str.replace(/REPLACEROWID/g, (maxRowNum));
		//divTsTd2Str = divTsTd2Str.replace(/PROCEDURE/g,("")); 
		cell2.innerHTML = divTsTd3Str;
		
		var cell3 = row.insertCell(3);
		var divTsTd3Str = document.getElementById("divRawTaskTd4").innerHTML;
		divTsTd3Str = divTsTd3Str.replace(/REPLACEROWID/g, (maxRowNum));
		//divTsTd2Str = divTsTd2Str.replace(/VALUE/g,("")); 
		cell3.innerHTML = divTsTd3Str;
		
		var cell4 = row.insertCell(4);
		var divTsTd8Str = document.getElementById("divRawTaskTd5").innerHTML;
		divTsTd8Str = divTsTd8Str.replace(/REPLACEROWID/g, (maxRowNum));
		cell4.innerHTML = divTsTd8Str;   
		
		 renumberRows(); 
		 updateNumberOfPackages();
		 
		
}		
		
</script>


<script type="text/javascript">
	function addProductTest(local) {
		
		var quantity = 1;
		var checkMaxTsRowNum = $("#maxTestRowNumForProducts").val();
		var tbl = document.getElementById("ProductTestTbl");
		var maxRowNumForProducts = parseInt($("input[name = 'maxTestRowNumForProducts']").val());
		$("input[name = 'maxTestRowNumForProducts']").val(maxRowNumForProducts + 1);
		
		var row = tbl.insertRow(maxRowNumForProducts);
		row.setAttribute("id", "divTestRowForProducts" + maxRowNumForProducts);

		var cell0 = row.insertCell(0);
		var divTsTd1Str = document.getElementById("divProductTaskTd1").innerHTML;
		divTsTd1Str = divTsTd1Str.replace(/REPLACEROWID/g, (maxRowNumForProducts));
	    divTsTd1Str = divTsTd1Str.replace(/ROWNUMBERVALUE/g, (maxRowNumForProducts)); 
		cell0.innerHTML = divTsTd1Str;
		
	 	var cell1 = row.insertCell(1);
		var divTsTd2Str = document.getElementById("divProductTaskTd2").innerHTML;
		divTsTd2Str = divTsTd2Str.replace(/REPLACEROWID/g, (maxRowNumForProducts));
		divTsTd2Str = divTsTd2Str.replace(/TEST/g,("")); 
		cell1.innerHTML = divTsTd2Str;

		var cell2 = row.insertCell(2);
		var divTsTd3Str = document.getElementById("divProductTaskTd3").innerHTML;
		divTsTd3Str = divTsTd3Str.replace(/REPLACEROWID/g, (maxRowNumForProducts));
		divTsTd2Str = divTsTd2Str.replace(/PROCEDURE/g,("")); 
		cell2.innerHTML = divTsTd3Str;
		
		var cell3 = row.insertCell(3);
		var divTsTd3Str = document.getElementById("divProductTaskTd4").innerHTML;
		divTsTd3Str = divTsTd3Str.replace(/REPLACEROWID/g, (maxRowNumForProducts));
		divTsTd2Str = divTsTd2Str.replace(/VALUE/g,("")); 
		cell3.innerHTML = divTsTd3Str;
		
		var cell4 = row.insertCell(4);
		var divTsTd8Str = document.getElementById("divProductTaskTd5").innerHTML;
		divTsTd8Str = divTsTd8Str.replace(/REPLACEROWID/g, (maxRowNumForProducts));
		cell4.innerHTML = divTsTd8Str;   
		
		renumberRowsForProducts(); 
		updateNumberOfPackagesForProducts();
		 
		
}		
		
</script>



<script type="text/javascript">
	function saveProductTask() {
		var maxTestRowNum=$("#maxTestRowNumForProducts").val();
		var validationSettings = {
				errorMessagePosition : 'element',
				borderColorOnError : '',
				scrollToTopOnError : true,
				dateFormat : 'yyyy/mm/dd'
			};
		if ($('#addProductCheckList_form').validate(false, validationSettings)) {
			var frm = $('#addProductCheckList_form').serializeFormJSON();
			var con = JSON.stringify(frm);
			con = con.replace(/[{}]/g, "");
			var form = $('#addProductCheckList_form')[0];
	  		var formData = new FormData(form); 
	  		$("#addProductCheckList_form").attr("disabled", "disabled");
			 var jsonData = encodeURIComponent(con);
			 var operoxUrl ='${operoxUrl}';
			 $("#save_product").attr("disabled", "disabled");
	     		$( "#save_product" ).addClass('button').val('Processing..');
		         $.ajax({
		        	type : "POST",
		  			data: formData,
		     	    contentType : 'application/json; charset=utf-8',
		     	    enctype: 'multipart/form-data',
		 	        processData: false,
		 	        contentType:false,
		     	    dataType : 'json',
			    	 url: operoxUrl+"/saveProductTask?${_csrf.parameterName}=${_csrf.token}&json="+jsonData, 
			        success: function(result) {
			        	 $("#save_product").removeAttr("disabled");
			        	 $("#save_product" ).addClass('btn btn-primary').val('Save');
			        	 if (result == 'Success') {
								location.replace(operoxUrl
										+ "/qaCheckList");
							}
			        },
			    }); 
			
			
			return true;
		} else {
			return false;
		}
		
	}
	$('body').on('blur', '#addProductCheckList_form', function() {
		$("#addProductCheckList_form").showHelpOnFocus();
		$("#addProductCheckList_form").validateOnBlur(false, validationSettings);
	}); 
</script>

<script type="text/javascript">
function cancelCheckList(){
    var operoxUrl ='${operoxUrl}';
    location.replace(operoxUrl+ "/qaCheckList");
}
</script>

<script type="text/javascript">
	function saveRawMaterialTask() {
		var maxTestRowNum=$("#maxTestRowNum").val();
		var validationSettings = {
				errorMessagePosition : 'element',
				borderColorOnError : '',
				scrollToTopOnError : true,
				dateFormat : 'yyyy/mm/dd'
			};
		if ($('#addRawMaterialCheckList_form').validate(false, validationSettings)) {
			var frm = $('#addRawMaterialCheckList_form').serializeFormJSON();
			var con = JSON.stringify(frm);
			con = con.replace(/[{}]/g, "");
			var form = $('#addRawMaterialCheckList_form')[0];
	  		var formData = new FormData(form); 
			 var jsonData = encodeURIComponent(con);
			 var operoxUrl ='${operoxUrl}';
			 $("#save_raw_material").attr("disabled", "disabled");
	     	 $("#save_raw_material").addClass('button').val('Processing..');
		         $.ajax({
		        	type : "POST",
		  			data: formData,
		     	    contentType : 'application/json; charset=utf-8',
		     	    enctype: 'multipart/form-data',
		 	        processData: false,
		 	        contentType:false,
		     	    dataType : 'json',
			    	 url: operoxUrl+"/saveRawMaterialTask?${_csrf.parameterName}=${_csrf.token}&json="+jsonData, 
			        success: function(result) {
			        	 $("#save_raw_material").removeAttr("disabled");
			        	 $("#save_raw_material" ).addClass('btn btn-primary').val('Save');
			        	 if (result == 'Success') {
								location.replace(operoxUrl
										+ "/qaCheckList");
							}
			        },
			    }); 
			
			
			return true;
		} else {
			return false;
		}
		
	}
	$('body').on('blur', '#addRawMaterialCheckList_form', function() {
		$("#addRawMaterialCheckList_form").showHelpOnFocus();
		$("#addRawMaterialCheckList_form").validateOnBlur(false, validationSettings);
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
<jsp:include page="../masterFooter.jsp" />