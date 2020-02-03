<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script src="${operoxUrl}/resources/js/formvalidator.js"></script>

<!-- Content Area -->
  <script> 
$(document).ready(function(){
	var type = "${qcCheckList.checkListType}" ;
		if(type == 'RawMaterial'){
    		listChange("rawmaterial");
			document.getElementById('addCheckLists').value = 'rawmaterial';
		}
		if(type == 'Products'){
    		listChange("product");
			document.getElementById('addCheckLists').value = 'product';
		}
});
</script>
  <!-- Content Area inner -->
 <section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Edit Checklist</h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo; Edit Checklist</div> 
    <!-- <div class="pull-right brud-crum">Home &raquo; Edit Checklist</div> -->
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix">
  
    <div class="div33">
       <label class="labl">Checklist Type ${qcCheckList.checkListType} &nbsp;<b class="text-danger">*</b></label>
       
       <span class="select-fild">
                	<%-- <select id="addCheckLists" onchange="listChange(this);">
                	 <option value="rawmaterial" ${qcCheckList.checkListType  =='RawMaterial' ? 'selected' : ''}>Raw Material</option>
                     <option value="product" ${qcCheckList.checkListType  =='Products' ? 'selected' : ''}>Product</option> 
                    </select> --%>
                    <input type="text" placeholder="Checklist Name" readonly="readonly" value="${qcCheckList.checkListType}"/>
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
 <input type="hidden" id="id" name="id" value="${qcCheckList.id}">
 <p class="mb"><strong>Checklist For Raw Material</strong></p>
    <div class="div33">
       <label class="labl">Checklist Name&nbsp;<b class="text-danger">*</b></label>
       <span>
         <input type="text" placeholder="Checklist Name" id="Raw Checklist Name" field-name="Checklist Name" name="rawChecklistName" data-validation="required validate_Space " value="${qcCheckList.checkName}">
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Store</label>
       <span>
         <select class="chosen-select" id="rawStore" name="rawStore" field-name="Store" data-validation="required validate_Space " >
         <option value="">--Select Store--</option>
         <c:forEach var="store" items="${storesList}">
               <option value="${store.id}" ${store.id  == qcCheckList.store.id ? 'selected' : ''}>${store.storeName}</option>
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
               <option value="${rawMaterial.id}" ${rawMaterial.id  == qcCheckList.rawMaterial.id ? 'selected' : ''}>${rawMaterial.materialName}</option>
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
               <input  type="text" class="form-control" readonly id="uploadFile" name="docFile"  field-name="Document"  placeholder="Upload File..." value="${qcCheckList.fileName}">
           </div>
       </span>
      <!-- upload file -->
   </div>
   
    <div class="div33">
       <label class="labl">Description&nbsp;<b class="text-danger">*</b></label>
       <span>
      <textarea rows="2" placeholder="Description" id="rawDescription" field-name="Raw Description" name="rawDescription" data-validation="required validate_Space ">${qcCheckList.description}</textarea>
       </span>
    </div>
      <div class="clearfix"></div>        
 <!-- Table Design Raw Material -->
 
    <p class="clearfix addTest"><a href="#" class="btn btn-primary btn-sm pull-right"  onclick="addRawMaterial(this);"  title="Add Test"><i class="glyphicon glyphicon-plus"></i>&nbsp;Add Test</a></p>
    <input type="hidden" id="maxTestRowNum"  name = "maxTestRowNum" value="${checklistReport.size()+1}"/>
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
            	  <c:forEach var="option" items="${checklistReport}" varStatus="count">
            	     <tr id="divTestRow${count.index+1}">
		            	 <td><div class="ta-sm">${count.index+1}</div></td>
		                 <td><div class="ta-sl"><input type="text" placeholder="Test" name="${count.index+1}rawTest" id="${count.index+1}rawTest" data-validation="required validate_Space" value="${option.whatToCheck}"></div></td>
		                 <td><div class="ta-sl"><input type="text" placeholder="Procedure" name="${count.index+1}rawProcedure" id="${count.index+1}rawProcedure" value="${option.howToCheck}"></div></td>
		                 <td><div class="ta-sl"><textarea rows="2"  placeholder="Value" name="${count.index+1}rawValue" id="${count.index+1}rawValue" >${option.testDescription}</textarea></div></td>
	               <td><div><a href="#" onclick="removeProduct(${count.index+1});"><i class="glyphicon glyphicon-trash deme"></i></a></div></td>
	                </tr>
            	</c:forEach>
            </tbody>
        </table>
    </div> 
  <!--Table Design Raw Material ends -->
    <div class="clearfix"></div>
      <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right custom-buttons">
         <a href="#" id="save_raw_material" class="btn btn-primary" onclick="saveRawMaterialTask();"> Save
          </a>
         <input type="button" value="Cancel" class="btn btn-default" id="can" onclick="cancelCheckList();">
        </div>
      </div>
    </div>
</form:form>
</div>
<div class="clearfix cklmt" id="productDiv" style= "display:none";>
<form:form id="addProductCheckList_form" name="addProductCheckList_form" method="post">
 <input type="hidden" id="checkListType" name="checkListType" value="Products"/>
 <input type="hidden" id="id" name="id" value="${qcCheckList.id}">
 <p class="mb"><strong>Checklist For Product</strong></p>
    <div class="div33">
       <label class="labl">Checklist Name&nbsp;<b class="text-danger">*</b></label>
       <span>
        <input type="text" placeholder="Checklist Name" id="Product Checklist Name" name="productChecklistName" value="${qcCheckList.checkName}">
       </span>
    </div>
    
    <div class="div33">
       <label class="labl">Store</label>
       <span>
         <select class="chosen-select" id="productStore" name="productStore" field-name="Store" >
         <option value="">--Select Store--</option>
         <c:forEach var="store" items="${storesList}">
               <option value="${store.id}" ${store.id  == qcCheckList.store.id ? 'selected' : ''}>${store.storeName}</option>
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
               <option value="${product.id}" ${product.id  == qcCheckList.productid.id ? 'selected' : ''}>${product.productName}</option>
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
               <input  type="text" class="form-control" readonly id="productUploadFile" name="productDocFile" placeholder="Upload File..." value="${qcCheckList.fileName}">
           </div>
       </span>
      <!-- upload file -->
   </div>
    <div class="div33">
       <label class="labl">Description&nbsp;<b class="text-danger">*</b></label>
       <span>
      <textarea rows="2" placeholder="Description" id="productDescription" name="productDescription">${qcCheckList.description}</textarea>
       </span>
    </div>
      <div class="clearfix"></div>        
 <!-- Table Design Raw Material -->
    <p class="clearfix addTest"><a href="#" class="btn btn-primary btn-sm pull-right" onclick="addProductTest(this);" title="Add Test"><i class="glyphicon glyphicon-plus"></i>&nbsp;Add Test</a></p>
     <input type="hidden" id="maxTestRowNumForProducts" name="maxTestRowNumForProducts" value="${checklistReport.size()+1}" />
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
                <c:forEach var="option" items="${checklistReport}" varStatus="count">
            	<tr id="divTestRowForProducts${count.index+1}">
         				 <td><div class="ta-sm">${count.index+1}</div></td>
		                 <td><div class="ta-sl"><input type="text" placeholder="Test" name="${count.index+1}ProductsTest" id="${count.index+1}ProductsTest" data-validation="required validate_Space" value="${option.whatToCheck}"></div></td>
		                 <td><div class="ta-sl"><input type="text" placeholder="Procedure" name="${count.index+1}ProductsProcedure" id="${count.index+1}ProductsTest" value="${option.howToCheck}"></div></td>
		                 <td><div class="ta-sl"><textarea rows="2" placeholder="Value" name="${count.index+1}ProductsValue" id="${count.index+1}ProductsValue">${option.testDescription}</textarea></div></td>
	               <td><div><a href="#" onclick="removeProductTest(${count.index+1});"><i class="glyphicon glyphicon-trash deme"></i></a></div></td>
                </tr>
                </c:forEach>
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
      <!-- Raw Material Ends -->
      
 <div id="divRawTaskTd1" style="display: none; float: left;">
   	<div class="ta-sm">ROWNUMBERVALUE</div>
</div>
<div id="divRawTaskTd2" style="display: none; float: left;">
   	<input type="text" id="REPLACEROWIDrawTest" name="REPLACEROWIDrawTest" value="" data-validation="required validate_Space " field-name ="Test Name"/>
</div> 
<div id="divRawTaskTd3" style="display: none; float: left;">
   <div>
      <input type="text" id="REPLACEROWIDProcedure" name="REPLACEROWIDrawProcedure"  value=""/>
	</div>  
</div> 
<div id="divRawTaskTd4" style="display: none; float: left;">
     <div>
      <textarea rows="2" id="REPLACEROWIDValue" name="REPLACEROWIDrawValue" ></textarea>
     </div>		
</div>
<div id="divRawTaskTd5" style="display: none; float: left;">
	 <div>
         <a href="#" onclick="removeProduct(REPLACEROWID);"><i class="glyphicon glyphicon-trash deme"></i></a>
    </div>
</div>

      <!-- Product Material -->
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
<div class="clearfix"></div>

</section>

<script type="text/javascript">
function renumberRows() {
        $('#RawMaterialTbl tr:visible').each(function(index, el){ 
            $(this).children('td').first().text(index++);
        });
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
	function removeProductTest(rowId) {
		var rowNum = $("#divTestRowForProducts"+rowId).val();
		var maxRowNumForProducts = parseInt(rowNum);
		 document.getElementById("divTestRowForProducts"+rowId).style.display = 'none';
		 document.getElementById("divTestRowForProducts"+rowId).value= '';
		 renumberRowsForProducts();
		 var  rId = "divTestRowForProducts"+rowId;
		  var r = document.getElementById("ProductTestTbl").rows[rId];
          var count = 2*(r.cells.length);
		    for(i=0;i<=count;i++){
		        r.deleteCell(0);
		    } 
		    
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
		 
		
}		
		
</script>
<!-- <script> 
$(document).ready(function(){
    listChange("rawmaterial");
	document.getElementById('addCheckLists').value = 'rawmaterial';
});
</script> -->
<script>
function listChange(sel){
	 if(sel == "product"){
		 document.getElementById("productDiv").style.display="block";
		 document.getElementById("rawmaterialDiv").style.display="none";
	 }
	 if(sel =="rawmaterial"){
		 document.getElementById("rawmaterialDiv").style.display="block";
		 document.getElementById("productDiv").style.display="none";
	 }
	 
}
</script>
<script type="text/javascript">
function cancelCheckList(){
    var operoxUrl ='${operoxUrl}';
    location.replace(operoxUrl+ "/qaCheckList");
}
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
	function addRawMaterial(local) {
		
		var quantity = 1;
		var checkMaxTsRowNum = $("#maxTestRowNum").val();
		var tbl = document.getElementById("RawMaterialTbl");
		var maxTestRowNum = parseInt($("input[name = 'maxTestRowNum']").val());
		$("input[name = 'maxTestRowNum']").val(maxTestRowNum + 1);
		
		var row = tbl.insertRow(maxTestRowNum);
		row.setAttribute("id", "divTestRow" + maxTestRowNum);
		
		var cell0 = row.insertCell(0);
		var divTsTd1Str = document.getElementById("divRawTaskTd1").innerHTML;
		divTsTd1Str = divTsTd1Str.replace(/REPLACEROWID/g, (maxTestRowNum));
	    divTsTd1Str = divTsTd1Str.replace(/ROWNUMBERVALUE/g, (maxTestRowNum)); 
		cell0.innerHTML = divTsTd1Str;
		
	 	var cell1 = row.insertCell(1);
		var divTsTd2Str = document.getElementById("divRawTaskTd2").innerHTML;
		divTsTd2Str = divTsTd2Str.replace(/REPLACEROWID/g, (maxTestRowNum));
		//divTsTd2Str = divTsTd2Str.replace(/TEST/g,("")); 
		cell1.innerHTML = divTsTd2Str;

		var cell2 = row.insertCell(2);
		var divTsTd3Str = document.getElementById("divRawTaskTd3").innerHTML;
		divTsTd3Str = divTsTd3Str.replace(/REPLACEROWID/g, (maxTestRowNum));
		//divTsTd2Str = divTsTd2Str.replace(/PROCEDURE/g,("")); 
		cell2.innerHTML = divTsTd3Str;
		
		var cell3 = row.insertCell(3);
		var divTsTd3Str = document.getElementById("divRawTaskTd4").innerHTML;
		divTsTd3Str = divTsTd3Str.replace(/REPLACEROWID/g, (maxTestRowNum));
		//divTsTd2Str = divTsTd2Str.replace(/VALUE/g,("")); 
		cell3.innerHTML = divTsTd3Str;
		
		var cell4 = row.insertCell(4);
		var divTsTd8Str = document.getElementById("divRawTaskTd5").innerHTML;
		divTsTd8Str = divTsTd8Str.replace(/REPLACEROWID/g, (maxTestRowNum));
		cell4.innerHTML = divTsTd8Str;   
		
		 renumberRows(); 
		 
		
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
	  		$("#addRawMaterialCheckList_form").attr("disabled", "disabled");
			 var jsonData = encodeURIComponent(con);
			 var operoxUrl ='${operoxUrl}';
			 $("#save_raw_material").attr("disabled", "disabled");
	     		$( "#save_raw_material" ).addClass('button').val('Processing..');
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