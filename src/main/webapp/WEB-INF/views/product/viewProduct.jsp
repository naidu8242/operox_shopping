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

<!-- Content Area -->
<section class="content-wraper">
  <section class="content-header clearfix">
    <div class="pull-left">
      <a href="${operoxUrl}/product"><i class="fa fa-arrow-left"></i>Back to products</a>
    </div>
    <div class="pull-right brud-crum">Home &raquo; Add Product</div>
  </section>
  
  <!-- Content Area inner -->
  <div class="content-area clearfix no_pad">

<div class="view_wrap">
	<div class="view_wrap_img"><img id="uploadProductImage" onerror="predefineProductImage(this)"  src="${operoxUrl}/displayProductImage/${product.id}" alt="productimg" id="image_upload_preview"></div>
    <div class="view_wrap_details">
    
    	<div>
        <b>Product name</b><span>${product.productName}</span>
        <div class="clearfix"></div>
        </div>
        
        <div>
    	<c:if test="${empty product.productCode}">
    	<b>Search code</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty product.productCode}">
    	<b>Search code</b><span> ${product.productCode}</span>
    	</c:if>
        
        <div class="clearfix"></div>
        </div>
        <div>
    	<c:if test="${empty product.catagoryName}">
    	<b>Category</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty product.catagoryName}">
    	<b>Category</b><span> ${product.catagoryName}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        <div>
    	<c:if test="${empty product.subCatagoryName}">
    	<b>Sub Category</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty product.subCatagoryName}">
    	<b>Sub Category</b><span> ${product.subCatagoryName}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
<%--          <div>
    	<c:if test="${empty product.catagoryName}">
    	<b>Subcategory</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty product.catagoryName}">
    	<b>Subcategory</b><span> ${product.catagoryName}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div> --%>
        
         <div>
    	<c:if test="${empty product.measuringUnit}">
    	<b>Measuring unit</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty product.measuringUnit}">
    	<b>Measuring unit</b><span> ${product.measuringUnit.measuringUnit}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        
          <div>
    	<c:if test="${empty product.brand.brandName}">
    	<b>Brand</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty product.brand.brandName}">
    	<b>Brand</b><span> ${product.brand.brandName}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        
           <div>
    	<c:if test="${empty product.manufacturedBy}">
    	<b>Manufacture by</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty product.manufacturedBy}">
    	<b>Manufacture by</b><span> ${product.manufacturedBy}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        
         <div>
    	<c:if test="${empty product.marketedBy}">
    	<b>Marketed by</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty product.marketedBy}">
    	<b>Marketed by</b><span> ${product.marketedBy}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        
         <div>
    	<c:if test="${empty product.updatedBy}">
    	<b>Updated by</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty product.updatedBy}">
    	<b>Updated by</b><span> ${product.updatedBy}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>
        
        
        <div>
    	<c:if test="${empty product.dateStr}">
    	<b>Updated on</b><span> ----- </span>
    	</c:if>
    	<c:if test="${!empty product.updatedDate}">
    	<b>Updated on</b><span> ${product.dateStr}</span>
    	</c:if>
        <div class="clearfix"></div>
        </div>                                                                              
        
    </div>
    <div class="clearfix"></div>
</div>	  	
  
  </div>
  
</section>
<!-- Content Area Ends--> 










<jsp:include page="../masterFooter.jsp" />