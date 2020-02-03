<jsp:include page="../masterHeader.jsp" />
<jsp:include page="../masterSideNav.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${operoxUrl}/resources/js/formvalidator.js"></script>


<script>
	function predefineProductImage(imageId){
		$(imageId).attr('src','${operoxUrl}/resources/images/add-product.jpg');
	}
</script>

<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <h2>Add Product</h2>
    </div>
    <div class="pull-right brud-crum"><a href="${operoxUrl}/dashboard">Home</a> &raquo;<c:if test="${empty brand.id}">Add Product</c:if><c:if test="${!empty brand.id}">Edit Product</c:if></div>
  </section>
  
  <!-- Content Area inner -->
 
  <div class="content-area clearfix">
   <form:form id="addProduct_form" name="addProduct_form" method="post">
  <div class="div-col2">
    <div class="image-parent">
      <div class="imageParent">
    	 <img id="uploadProductImage" onerror="predefineProductImage(this)"  src="${operoxUrl}/displayProductImage/${product.id}" alt="productimg" id="image_upload_preview">
      </div>

  <!-- Upload file -->
      <div class="upload-image">
         <c:if test="${!empty product.fileContentType}">
         <label>
          <span class="btn btn-danger btn-sm"><i class="glyphicon glyphicon-trash"></i>
             <input type="button" multiple id="deleteImg"  class="uploadd" onclick="deleteProductImage(${product.id})" style="display: none;">
         </span>
         </label>
         </c:if>
         <label>
         <span class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-open"></i>
             <input type="file" name="file" id="uploadedImage"  onchange="loadFile(event)" class="upload" style="display: none;">
         </span>
         </label>
      </div>
  <!-- Upload file -->
    </div>
	  <p class="text-danger small" style="font-size: 11px; display: none;" id="errorImage" >Invalid Image/Size</p>
	  <p class="text-muted small" style="font-size: 11px;"><i class="fa fa-info-circle"></i>&nbsp;png, svg and gif images are not allowed</p>
	  <p class="text-muted small" style="font-size: 11px;"><i class="fa fa-info-circle"></i>&nbsp;Image size should not more than 200 KB</p>
  </div>
  <div class="div-col10">

 	<%--  <div class="div33"  >
       <label class="labl">Product Id*</span></label>
       <span>
       <input type="text" id="productId" value="${product.id}" name="productId"  placeholder="Product Id" field-name="Product Id" data-validation="required validate_Space validate_length length2-250">
       </span>
        <input type="hidden" id="pid" name="pid" value="${product.id}">
    </div> --%>
  <input type="hidden" id="pid" name="pid" value="${product.id}">
    <div class="div33" id="productDiv" >
       <label class="labl">Product Name*</span></label>
       <span>
       <input type="text" value="${product.productName}" name="productName" id="productName" placeholder="Product Name" field-name="Product Name" data-validation="required validate_Space validate_length length2-250" onblur="validateProduct('${product.productName}');">
       </span>
    </div>
    
    <div class="div33" >
       <label class="labl">Search Code</label>
       <span>
       <input type="text" id="searchCode" value="${product.productCode}" name="searchCode" placeholder="Search Code">
       </span>
    </div>
    
    <div class="div33" >
       <label class="labl">Category<b class="text-danger">&nbsp;*</b></label>
       <span>
       <select class="chosen-select"  id="category" name="category" data-validation="required validate_Space" field-name="Select Product" onchange="getsubCategory();">
			<option value="">-- Select Category --</option>
				<c:forEach var="option" items="${categoryList}">
					<option value="${option.id}" ${option.id  == product.category.id ? 'selected' : ''}>${option.categoryName}</option>
				</c:forEach>
			</select>
       </span>
       
    </div>
    
    <div class="div33" >
       <label class="labl">Sub Category</label>
       <span>
         <select class="chosen-select" id ="subCategory" name ="subCategory">
         <c:if test="${empty product}">
             <option selected value="">-- Select SubCategory --</option>
           </c:if>
           <c:if test="${!empty product}">
			  <option selected value="${option.id}">${product.subCategory.categoryName}</option>
		   </c:if>
         </select>
         <input type="hidden" id="subCategoryValue" value="${product.subCategory.categoryName}" >
       </span>
    </div>
        <div class="div33" id ="measuringUnitDiv" name ="measuringUnit">
       <label class="labl">Measuring Unit</label>
       <span>
         <select class="chosen-select" id="measuringUnit"  name="measuringUnit" field-name="Select Measuring Unit"  > 
			<option value="">-- Select Measuring Unit --</option>
				 <c:forEach var="option" items="${measuringUnitList}">
					<option value="${option.id}" ${option.id  == product.measuringUnit.id ? 'selected' : ''}>${option.measuringUnit}</option>
				</c:forEach> 
         </select>
       </span>
    </div>
    <div class="div33" >
       <label class="labl">Brand<b class="text-danger">&nbsp;*</b></label>
       <span>
           <select class="chosen-select" id="brand"  name="brand" field-name="Select Brand" data-validation="required validate_Space" onchange="getsubBrands();" > 
          <%--  <c:if test="${empty product}">
             <option selected value="">-- Select Brands --</option>
           </c:if>
           <c:if test="${!empty product}">
			 <option selected value="${option.id}">${product.brand.brandName}</option>
		   </c:if> --%>
		   <option selected value="">-- Select Brands --</option>
				<c:forEach var="option" items="${brandsList}">
					<option value="${option.id}" ${option.id  == product.brand.id ? 'selected' : ''}>${option.brandName}</option>
				</c:forEach>  
         </select>
       </span>
    </div>
        
    <div class="div33" >
       <label class="labl">Manufatured by</label>
       <span>
       <input type="text" id="manufaturedBy" value="${product.manufacturedBy}" name="manufaturedBy" placeholder="Manufatured by">
       </span>
    </div>
    
    <div class="div33" >
       <label class="labl">Marketed by</label>
       <span>
         <input type="text" id ="marketedBy" value="${product.marketedBy}" name ="marketedBy" placeholder="Marketed by">
       </span>
    </div>

    </div>
    <div class="clearfix"></div>
      <!-- Content Area inner -->
      <div class="form-footer">
      <div class="col-lg-12">
        <div class="pull-right">
        <c:if test="${empty brand.id}">
          <input type="button" value="Add" class="btn btn-primary" id="product_submit" onclick="saveProduct()">
          </c:if>
          <c:if test="${!empty brand.id}">
           <input type="button" value="Update" class="btn btn-primary" id="product_submit" onclick="saveProduct()">
          </c:if>
          <input type="button" value="Cancel" class="btn btn-default ml10" onClick="javascript:location.href = '<c:url value='/product'/>'">
        </div>
      </div>
    </div>
    
    </form:form>
  </div>
  
</section>



<script type="text/javascript">
  var loadFile = function(event) {
	  
    var output = document.getElementById('uploadProductImage');
    
    var uploadedImage = document.getElementById('uploadedImage').value;
    var sizeinbytes = document.getElementById('uploadedImage').files[0].size;
    document.getElementById("errorImage").style.display="none"; 
    
    if(uploadedImage.substring(uploadedImage.lastIndexOf(".")+1) != 'jpg'){
    	output.src = URL.createObjectURL(event.target.files[0]);
    	
    	var extensionName = uploadedImage.substring(uploadedImage.lastIndexOf(".")+1);
 		var msg = "."+extensionName+" extension not allowed";
 		document.getElementById("errorImage").style.display=""; 
 		$("#product_submit").attr("disabled", "disabled");
    	
    }else if(sizeinbytes > 200000){
    	output.src = URL.createObjectURL(event.target.files[0]);
 		var msg = "Image size should be less than 200 KB";
 		document.getElementById("errorImage").style.display=""; 
 		$("#product_submit").attr("disabled", "disabled");
    	
    }else{
    	output.src = URL.createObjectURL(event.target.files[0]);
    	$("#product_submit").removeAttr("disabled")
    };
    
  };
</script>


<script type="text/javascript">
	function saveProduct() {
		var operoxUrl ='${operoxUrl}';
		var validationSettings = {
			errorMessagePosition : 'element',
			borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd'
		};
		if ($('#addProduct_form').validate(false, validationSettings)) {
			$("#product_submit").attr("disabled", "disabled");
     		$( "#product_submit" ).addClass('button').val('Processing..');
			var frm = $('#addProduct_form').serializeFormJSON();
			var con = JSON.stringify(frm);
			con = con.replace(/[{}]/g, "");
			  //It will escape all the special characters
	        var jsonData = encodeURIComponent(con);
	        var form = $('#addProduct_form')[0];
     		var formData = new FormData(form);
		$.ajax({
			 type: "POST",
	    	 data: formData,
   	         contentType : 'application/json; charset=utf-8',
   	         enctype: 'multipart/form-data',
	         processData: false,
	         contentType:false,
   	         dataType : 'json',
			url : operoxUrl
					+ "/saveProduct?${_csrf.parameterName}=${_csrf.token}&json="+jsonData,
			success : function(result) {
				if((result == 'success')){
	        		location.replace(operoxUrl+"/product");  
	        	}
			},
		}); 
		return true;
		} else {
			return false;
		}
	}
	$('body').on('blur', '#addProduct_form', function() {
		$("#addProduct_form").showHelpOnFocus();
		$("#addProduct_form").validateOnBlur(false, validationSettings);
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
 function getsubCategory() {
	 var categoryId = document.getElementById("category").value; 
	 var subcategoryName = $("#subCategory").val();
		if(subcategoryName == null || subcategoryName == 'undefined' || subcategoryName == ''){
			subcategoryName = document.getElementById("subCategoryValue").value;
		}
	 var operoxUrl ='${operoxUrl}';
	 if(categoryId != null && categoryId != "" && categoryId != 'undefined'){
		 $.ajax({
		    	type: "POST",
		        url: operoxUrl+"/getsubCategory?${_csrf.parameterName}=${_csrf.token}&categoryId="+categoryId, 
		        success: function(data) {

		        	var json = JSON.parse(data);
		        	$('#subCategory').empty();
		        	$('#subCategory').append('<option value="">-- Select SubCategory --</option>');
		            for(var i=0;i<json.length;i++){
		            	var report = json[i];
		            	if(report.categoryName == subcategoryName){
		            		$('#subCategory').append('<option value="'+report.id+'" selected>'+report.categoryName+'</option>');
		            	}
		            	else{ 
		            		$('#subCategory').append('<option value="'+report.id+'">'+report.categoryName+'</option>');
		            	} 
		            }
		            $('#subCategory').trigger("chosen:updated");
		        },
		        error: function(e){
		        }
		    });
		}else{
			$('#subCategory').empty();
	    	$('#subCategory').append('<option value="">-- Select SubCategory --</option>');
	    	$('#subCategory').trigger("chosen:updated");
		}
 }
</script> 


<script type="text/javascript">
function deleteProductImage(productId){
	var operoxUrl ='${operoxUrl}';
	
	 $.ajax({
		    type: "GET",
		    url: operoxUrl+"/deleteProductImage?${_csrf.parameterName}=${_csrf.token}&productId="+productId,
	        success: function(data) {
	        	 var result = data;
     		if(result == "success"){
     			location.replace(operoxUrl+"/editProduct/"+productId+""); 
        	 }
	        
	       },
	        
     }); 
}
</script>


<script>
function getsubBrands(){
	var brandId = $("#brand").val(); 
	 var operoxUrl ='${operoxUrl}';
	 if(brandId != null && brandId != "" && brandId != 'undefined'){
		 $.ajax({
		    	type: "GET",
		        url: operoxUrl+"/getSubBrands?${_csrf.parameterName}=${_csrf.token}&brandId="+brandId, 
		        success: function(data) {
		        	var json = JSON.parse(data);
		        	$("#manufaturedBy").val(json.manufacturedBy);
		        	$("#marketedBy").val(json.marketedBy);
		        },
		    });
	 }else{
		 $("#manufaturedBy").val('');
     	$("#marketedBy").val('');
	 }
}

</script>


<script type="text/javascript">
	function validateProduct(pname){
		var productName = $("#productName").val();
		var operoxUrl ='${operoxUrl}';
		$('input#productName').removeAttr("class record-exist record-exist-errormsg");
		$('input#productName').attr('class','valid form-control');
		$('#productDiv').find('p.jquery_form_error_message').remove();
		$("#product_submit" ).addClass('btn btn-primary').val('Add');
        $("#product_submit").removeAttr("disabled");
		
        if(pname != productName){
        $('#productDiv').find('p.jquery_form_error_message').remove();
		if (productName != null && productName != '') {
			$("#addProduct_form").attr("disabled", "disabled");
			$.ajax({
				type : "GET",
				url : operoxUrl + "/validateProduct?&productName="+ productName,
				success : function(data) {
					var result = data;
					if (result == false) {
						
						 $('#productDiv').find('p.jquery_form_error_message').remove();
						 $("input#productName").after("<p class='jquery_form_error_message'>"+productName+" is Already Existed</span>");
						 document.getElementById("productName").setAttribute('record-exist','yes');
						 document.getElementById("productName").setAttribute('record-exist-errorMsg',productName+' is Already Existed'); 
						
					} else {
						//New WebSite class="form-control"
						$('input#productName').removeAttr("class record-exist record-exist-errormsg");
						$('input#productName').attr('class','valid form-control');
						$('#productDiv').find('p.jquery_form_error_message').remove();
						$("#product_submit" ).addClass('btn btn-primary').val('Add');
                        $("#product_submit").removeAttr("disabled");
					}
				},
			});
			$("#addProduct_form").click(function() {
				if ($(this).validate(false, validationSettings)) {
				}
			});
		}
        }
	}
</script>

<script type="text/javascript">
$(function(){
	       var config = {
			  '.chosen-select'           : {},
			  '.chosen-select-deselect'  : {allow_single_deselect:true},
			  '.chosen-select-no-single' : {disable_search_threshold:10},
			  '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
			  '.chosen-select-width'     : {width:"95%"}
			}
	       $("#category").chosen();
	       $("#subCategory").chosen();
	       $("#measuringUnit").chosen();
	       $("#brand").chosen();
	       
	       $("#category").on('change', function (e) {
	    	   getsubCategory();
	       });
	       
	       $('.chosen-select').each(function() {
				var $this = $(this);
				$this.next().css({'width': '100%'});
		  })
});
</script>

<jsp:include page="../masterFooter.jsp" />