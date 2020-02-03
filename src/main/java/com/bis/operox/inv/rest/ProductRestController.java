package com.bis.operox.inv.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.BrandService;
import com.bis.operox.inv.service.CategoryService;
import com.bis.operox.inv.service.ProductService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

@RestController
@PropertySource("${propertyLocation:operoxapp.properties}")
public class ProductRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	ProductService productService;
	
	
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	CommonUtil commonUtil;
	
	@RequestMapping(value = "/saveProduct",  method = RequestMethod.POST)
	@ResponseBody String saveProduct(@RequestParam(value = "json", required = false) String json,MultipartHttpServletRequest request) throws Exception {
		String json1 = "{" + json + "}";
		JSONObject jsonObj = new JSONObject(json1);
		 Iterator<String> itrator = request.getFileNames();
	     MultipartFile multiFile = request.getFile(itrator.next());
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		productService.addProductDetails(jsonObj,user,multiFile);
		return "success";
	}

	
	@RequestMapping(value = "/getsubCategory",  method = RequestMethod.POST)
	@ResponseBody String getsubCategory(@RequestParam(value = "categoryId", required = false) String categoryId) throws Exception {
		List<Category> subCategoryList = categoryService.getsubCategoriesList(Long.parseLong(categoryId));
		return toJSON(subCategoryList);
	}
	
	

	@RequestMapping(value = "/getProductsList",  method = RequestMethod.GET)
	@ResponseBody
	public String getAllProductsList(HttpServletRequest request){
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		List<Product> productList = productService.getAllProducts(user.getStore().getCompany().getId());
		for (Product product : productList) {
			if(product.getCategory()!=null){
				product.setCatagoryName(product.getCategory().getCategoryName());
			}
			if(product.getBrand()!=null){
				product.setBrandName(product.getBrand().getBrandName());
			}
		}
		return toJSON(productList);
	}
	
	
	@RequestMapping(value = "/myList/", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> listAllUsers() {
		List<Product> productList = productService.getAll();
	        if(productList.isEmpty()){
	            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	 }
	
	public String toJSON(Object obj){
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
            public HierarchicalStreamWriter createWriter(Writer writer) {
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }
        });
        return xstream.toXML(obj);
    }

	@RequestMapping(value = "/validateProduct",  method = RequestMethod.GET)
	@ResponseBody Boolean validateProduct(@RequestParam(value = "productName", required = false) String productName) throws Exception {
	logger.info("Entered product name is "+productName);
	return productService.getProductByProductName(productName);
	}
		
	
	 @RequestMapping(value = "/deleteProduct")
	 @ResponseBody
		public String deleteProduct(@RequestParam(value="productId", required=false) String productId,HttpServletRequest request){
		Product product = productService.getProductById(Long.parseLong(productId));
		if(product!=null){
			product.setStatus(0);
			product = productService.addProduct(product);
		}
		return "success";
		}
	 
	 @RequestMapping(value = "/displayProductImage/{productId}")
     public void displayProductImage(@PathVariable Long productId,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	 
    	 Product product = null;
			try {
				product = productService.getProductById(productId);
				
				if(product != null && product.getDocFile() != null)
				{
					product.setFileContentType("image/jpeg");
					InputStream in = new ByteArrayInputStream(product.getDocFile());
					BufferedImage bi = ImageIO.read(in);
					OutputStream out = response.getOutputStream();
					ImageIO.write(bi, "jpg", out);
					out.close();
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
     }

	 @RequestMapping(value = "/deleteProductImage" , method = RequestMethod.GET)
	   public String deleteProductImage(@RequestParam(value="productId", required=false) String productId,HttpServletRequest request) throws Exception {
		   
		  Product product = productService.getProductById(Long.parseLong(productId));
		  product.setDocFile(null);
		  product.setFileContentType(null);
		  productService.addProduct(product);
		   
		   return "success";
		   
	   }
	   
	 
	 @RequestMapping(value = "/getSubBrands",  method = RequestMethod.GET)
		@ResponseBody String getSubBrands(@RequestParam(value = "brandId", required = false) String brandId) throws Exception {
		                  Brand brand =  brandService.getBrandById(Long.parseLong(brandId));
			             return toJSON(brand);
		}
	 
	 @RequestMapping(value = "/getBrandsOfCategory",  method = RequestMethod.GET)
		@ResponseBody String getBrandsOfCategory(@RequestParam(value = "categoryId", required = false) String categoryId) throws Exception {
		                  List<Brand> brand =  brandService.getBrandByCatagoryId(Long.parseLong(categoryId));
			             return toJSON(brand);
		}
	 

}
