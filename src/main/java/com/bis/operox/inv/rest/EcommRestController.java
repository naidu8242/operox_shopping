package com.bis.operox.inv.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.OperoxPasswordEncoder;
import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Address;
import com.bis.operox.inv.dao.entity.Bill;
import com.bis.operox.inv.dao.entity.BillItems;
import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.PasswordResets;
import com.bis.operox.inv.dao.entity.Price;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.ProductStock;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.AddressService;
import com.bis.operox.inv.service.BillItemsService;
import com.bis.operox.inv.service.BillService;
import com.bis.operox.inv.service.BrandService;
import com.bis.operox.inv.service.CategoryService;
import com.bis.operox.inv.service.PasswordResetsService;
import com.bis.operox.inv.service.PaymentDetailsService;
import com.bis.operox.inv.service.PriceService;
import com.bis.operox.inv.service.ProductService;
import com.bis.operox.inv.service.ProductStockService;
import com.bis.operox.inv.service.ShiftService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.AutoCompleteUtil;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;


@RestController
public class EcommRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;

	@Autowired
	ShiftService shiftService;
	
	@Autowired
	CommonUtil commonutill;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductStockService productStockService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private PaymentDetailsService paymentDetailsService;
	
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private BillItemsService billItemsService;
	
	@Autowired
	OperoxPasswordEncoder operoxPasswordEncoder;
	
	@Autowired
	private PasswordResetsService passwordResetsService;
	
	 @RequestMapping(value = "ecomm/getMenu")
	    public String getMenu(HttpServletRequest request) throws Exception {
	    	String resultJson = null;
	    	User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
			LinkedHashMap<String, AutoCompleteUtil> hm = new LinkedHashMap<String, AutoCompleteUtil>();
			List<Category> catList = categoryService.listCategories();
			if (catList!= null && !catList.isEmpty()) {
				for(Category category : catList) {		
						List<Category> subList = categoryService.getsubCategoriesList(category.getId());
						LinkedHashMap<String, List<Product>> hm1 = new LinkedHashMap<String, List<Product>>();
						if (subList!= null && !subList.isEmpty()) {
							for(Category sub : subList) {	
								 //List<Product> productList =	productService.getAll();
								 List<Product> productList =	productService.getProductsByCategoryId(sub.getId());
								 hm1.put(sub.getCategoryName()+"&"+sub.getId(), productList);
							}
						}	
						AutoCompleteUtil au = new AutoCompleteUtil();
						au.setSubCata(hm1);
						hm.put(category.getCategoryName()+"&"+category.getId(), au);
				}
			}	
			AutoCompleteUtil acUtil = new AutoCompleteUtil();
			acUtil.setCatAndSubCatAndProducts(hm);
			resultJson = toJSON(acUtil);
			return resultJson;
	}
	 
	 
	 @RequestMapping(value = "ecomm/getEcommersAllProductsList",  method = RequestMethod.GET)
		@ResponseBody
		public String getEcommersAllProductsList(String storeId, String categoryId, String searchKey,String[] brandSearchKeysArray, HttpServletRequest request){
				//List<ProductStock> productStockList = productStockService.listProductStock();
				
				Long storeID = null;
				Long categoryID = null;
				if(StringUtils.isNumeric(storeId) &&  StringUtils.isNotEmpty(storeId)){
					storeID = Long.parseLong(storeId);
				}
				if(StringUtils.isNumeric(categoryId) && StringUtils.isNotEmpty(categoryId)){
					categoryID = Long.parseLong(categoryId);
				}
				
				List<Long> brandIds = new ArrayList<Long>();
				if(brandSearchKeysArray != null){
				for(String brand :  brandSearchKeysArray){
					brandIds.add(Long.parseLong(brand));
				}
				}
				String minPrice = "0";
				String maxPrice = "0";
				
				
				
				List<ProductStock> productStockList = productStockService.listProductStockBySearchKeys(storeID, categoryID, searchKey,brandIds,minPrice,maxPrice); 
				
				for (ProductStock productStock : productStockList) {
					if(productStock.getProductId().getCategory()!=null){
						productStock.setCatagoryName(productStock.getProductId().getCategory().getCategoryName());
					}
					
					if(productStock.getProductId().getBrand()!=null){
						productStock.setBrandName(productStock.getProductId().getBrand().getBrandName());
					}
					
					if(productStock.getProductId()!=null){
						Price price = priceService.getPriceByProductId(productStock.getId());
						if(price != null ){
							productStock.setMrpStr(price.getMrp());
						}
						if(price != null && productStock.getDiscount() != null ){
							productStock.setPriceStr(Float.toString(Float.parseFloat(price.getMrp())-productStock.getDiscount()));
						}
					}
					
				}
				
				return toJSON(productStockList);
		 }
	 
	 
	@RequestMapping(value = "ecomm/getEcommersRecentProductsListByCount",  method = RequestMethod.GET)
	@ResponseBody
	public String getEcommersRecentProductsListByCount(int count, HttpServletRequest request){
			
			List<ProductStock> productStockList = productStockService.recentProductStockList(count); 
			
			for (ProductStock productStock : productStockList) {
				if(productStock.getProductId().getCategory()!=null){
					productStock.setCatagoryName(productStock.getProductId().getCategory().getCategoryName());
				}
				
				if(productStock.getProductId().getBrand()!=null){
					productStock.setBrandName(productStock.getProductId().getBrand().getBrandName());
				}
				
				if(productStock.getProductId()!=null){
					Price price = priceService.getPriceByProductId(productStock.getId());
					if(price != null ){
						productStock.setMrpStr(price.getMrp());
					}
					if(price != null && productStock.getDiscount() != null ){
						productStock.setPriceStr(Float.toString(Float.parseFloat(price.getMrp())-productStock.getDiscount()));
					}
				}
			}
			
			return toJSON(productStockList);
	 }
	
	
	@RequestMapping(value = "ecomm/getProductStockById",  method = RequestMethod.GET)
	@ResponseBody
	public String getProductStockById(String productId){
			//List<ProductStock> productStockList = productStockService.listProductStock();
			ProductStock productStock = productStockService.getProductStockById(Long.parseLong(productId));
			if(productStock.getProductId().getCategory()!=null){
				productStock.setCatagoryName(productStock.getProductId().getCategory().getCategoryName());
				if(productStock.getProductId().getCategory().getParentCategory() !=null){
					productStock.setParentCatagoryName(productStock.getProductId().getCategory().getParentCategory().getCategoryName());
				}
			}
			if(productStock.getProductId()!=null){
				productStock.setProductName(productStock.getProductId().getProductName());
				if(productStock.getProductId().getProductCode() != null){
					productStock.setProductCode(productStock.getProductId().getProductCode());
				}
			}
			if(productStock.getProductId().getBrand()!=null){
				productStock.setBrandName(productStock.getProductId().getBrand().getBrandName());
			}
			if(productStock.getProductId()!=null){
				Price price = priceService.getPriceByProductId(productStock.getId());
				if(price != null ){
					productStock.setMrpStr(price.getMrp());
				}
				if(price != null && productStock.getDiscount() != null ){
					productStock.setPriceStr(Float.toString(Float.parseFloat(price.getMrp())-productStock.getDiscount()));
				}
			}
			return toJSON(productStock);
	 }
	
	@RequestMapping(value = "ecomm/getEcommersProductsListByIds",  method = RequestMethod.GET)
	@ResponseBody
	public String getEcommersProductsListByIds(@RequestParam(value = "cartIds", required = false) String cartIds,HttpServletRequest request){
		 
		List<ProductStock> productStockList = null;
		if(cartIds != null && !cartIds.isEmpty()){
			List<Long> Ids = new ArrayList<Long>();
			for (String retval: cartIds.split(",")) {
				Ids.add(Long.parseLong(retval));
		    }
		productStockList = productStockService.getEcommersProductsStockListByIds(Ids);
		for (ProductStock productStock : productStockList) {
			if(productStock.getProductId().getCategory()!=null){
				productStock.setCatagoryName(productStock.getProductId().getCategory().getCategoryName());
			}
			if(productStock.getProductId()!=null){
				productStock.setProductName(productStock.getProductId().getProductName());
			}
			
			if(productStock.getProductId().getBrand()!=null){
				productStock.setBrandName(productStock.getProductId().getBrand().getBrandName());
			}
			
			if(productStock.getProductId()!=null){
				Price price = priceService.getPriceByProductId(productStock.getId());
				if(price != null ){
					productStock.setMrpStr(price.getMrp());
				}
				if(price != null && productStock.getDiscount() != null ){
					productStock.setPriceStr(Float.toString(Float.parseFloat(price.getMrp())-productStock.getDiscount()));
				}
			}
			if(productStock.getProductId()!=null){
					productStock.setProductIdStr(productStock.getProductId().getId().toString());
			}
			if(productStock.getTaxId()!=null){
				productStock.setTaxStr(productStock.getTaxId().getTaxValue());
			}
		}
		}
		return toJSON(productStockList);
	}
	
		
    @RequestMapping(value = "ecomm/displayProductImage/{productId}")
    public void displayProductImage(@PathVariable Long productId,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
   	 
    		Product product = null;
			try {
				product = productService.getProductById(productId);
				
				if(product != null && product.getDocFile() != null)
				{
					product.setFileContentType("image/jpeg");
					InputStream in = new ByteArrayInputStream(product.getDocFile());
					BufferedImage bi = ImageIO.read(in);
					if(bi != null){
						OutputStream out = response.getOutputStream();
						ImageIO.write(bi, "jpg", out);
						out.close();
					}
					
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
    }
    
    
    @RequestMapping(value = "ecomm/registerCustomer", method = RequestMethod.GET)
   	public @ResponseBody String addUser(@RequestParam(value = "json", required = false) String json,HttpServletRequest request) throws Exception {
   		String json1 = "{" + json + "}";
   		JSONObject jsonObj = new JSONObject(json1);
   		userService.storeEcommUser(jsonObj);
   		return "userHome";
   	} 
   	
       
   	@RequestMapping(value = "public/forgotPassword", method = RequestMethod.GET)
   	public @ResponseBody String resendPassword(@RequestParam(value = "json", required = false) String json,HttpServletRequest request) throws Exception {

   		String json1 = "{" + json + "}";
   		JSONObject jsonObj = new JSONObject(json1);
   		User user = userService.findByEmail(jsonObj.getString("emailId"));
   		if(user != null){
	         String randomNumber = RandomStringUtils.randomAlphanumeric(4);
	         PasswordResets passwordResets = new PasswordResets();
	         passwordResets.setUser(user);
	         passwordResets.setVerificationCode(randomNumber);
	         passwordResets.setEmail(user.getEmail());
	         passwordResets.setResetStatus("open");
	         passwordResets.setStatus(1);
	         passwordResets.setCreatedDate(new Date());
	         passwordResets.setCreatedBy(user.getFirstName());
	         passwordResets.setUpdatedBy(user.getFirstName());
	         passwordResets.setUpdatedDate(new Date());
	         passwordResetsService.savePasswordResets(passwordResets);
	         userService.mailSend(user,user,randomNumber);
           return "success";
   		}else{
   		    return "unsuccess";
   		}
   	}   
   	
   	
   	@RequestMapping(value = "ecomm/getUserDetails", method = RequestMethod.GET)
   	public @ResponseBody String getUserDetails(HttpServletRequest request,@RequestParam(value="userId", required=false) Long userId) throws Exception {
   		User user = userService.getUserById(userId);
   		return toJSON(user);
   	}
   	
   	@RequestMapping(value = "/public/forgotPasswordVerify", method = RequestMethod.GET)
	public @ResponseBody String forgotPasswordVerify(@RequestParam(value = "json", required = false) String json,HttpServletRequest request) throws Exception {
   		String json1 = "{" + json + "}";
   		JSONObject jsonObj = new JSONObject(json1);
   		PasswordResets passwordResets = passwordResetsService.getPasswordResetsByVerificationCode(jsonObj.getString("verificationCode"));
   		User user = userService.getUserById(passwordResets.getUser().getId());
   		user.setPassword(operoxPasswordEncoder.encode(jsonObj.getString("password")));
   		userService.addUser(user);
   	    return "success";
   } 
   	
	@RequestMapping(value = "ecomm/getAllBrands",  method = RequestMethod.GET)
	@ResponseBody String getBrandsList() throws Exception {
		List<Brand> brandList = brandService.listBrands();
		
		for (Brand brand : brandList) {
			User user2 = userService.getUserByUserCode(brand.getUpdatedBy());
			brand.setUserName(user2.getUsername());
			}
		return toJSON(brandList);
	}
	
	
	@RequestMapping(value = "ecomm/getAllUniqueBrands",  method = RequestMethod.GET)
	@ResponseBody
	public String getAllUniqueBrands(String storeId, String categoryId, String searchKey,String[] brandSearchKeysArray, HttpServletRequest request){
		
			Long storeID = null;
			Long categoryID = null;
			if(StringUtils.isNumeric(storeId) && StringUtils.isNotEmpty(storeId)){
				storeID = Long.parseLong(storeId);
			}
			if(StringUtils.isNumeric(categoryId) && StringUtils.isNotEmpty(categoryId)){
				categoryID = Long.parseLong(categoryId);
			}
			
			List<Long> brandIds = new ArrayList<Long>();
			if(brandSearchKeysArray != null){
			for(String brand :  brandSearchKeysArray){
				brandIds.add(Long.parseLong(brand));
			}
			}
			String minPrice = "0";
			String maxPrice = "0";
			
			List<ProductStock> productStockList = productStockService.listProductStockBySearchKeys(storeID, categoryID, searchKey,brandIds,minPrice,maxPrice); 

			HashMap<String,String> brandsList = new HashMap<String,String>();
			
			for (ProductStock productStock : productStockList) {
				if(productStock.getProductId().getBrand()!=null){
					productStock.setBrandName(productStock.getProductId().getBrand().getBrandName());
					brandsList.put(productStock.getBrandName(),productStock.getProductId().getBrand().getId().toString());
				}
			}
			
			return toJSON(brandsList);
	 }
	
	@RequestMapping(value = "public/getEcommersAllOrdersList",  method = RequestMethod.GET)
	@ResponseBody
	public String getEcommersAllOrdersList(@RequestParam(value = "userCode", required = false) String userCode, HttpServletRequest request){
   		String resultJson = null;
   		List<Bill> ordersList = billService.ecommOrderListByUserCode(userCode);
   		for(Bill bill:ordersList){
   			bill.setOrderDate(Constants.DF_DMY.format(bill.getBillDate()));
   			bill.setNumberOfProducts(bill.getNumberOfProducts());
   			bill.setBillNumber(bill.getBillNumber());
   			bill.setBillStatus(bill.getBillStatus());
   			bill.setBillAmount(bill.getTotalAmount());
   		}
		resultJson = toJSON(ordersList);
		return resultJson;
	 }
 	
 	@RequestMapping(value = "public/getEcommersAllOrderItemList",  method = RequestMethod.GET)
	@ResponseBody
	public String getEcommersAllOrderItemList(@RequestParam(value = "billId", required = false) Long billId,@RequestParam(value = "userCode", required = false) String userCode,HttpServletRequest request){
   		String resultJson = null;
   		List<BillItems> itemList = billItemsService.getOrderBillItemsByBillIdAndUseCode(billId, userCode);
   		for(BillItems items:itemList){
   			if(items.getProductId() != null){
   				items.setProductName(items.getProductId().getProductName());	
   			}
   			items.setQuantity(items.getQuantity());
   			items.setDiscount(items.getDiscount());
   			items.setPrice(items.getPrice());
   			items.setTotalAmount(items.getProductTotalAmount());
   			
   		}
		resultJson = toJSON(itemList);
		return resultJson;
	 }
 	
 	
 	@RequestMapping(value = "public/getEcommersOrdersListByBillId",  method = RequestMethod.GET)
	@ResponseBody
	public String getEcommersOrdersListByBillId(@RequestParam(value = "billId", required = false) Long billId,@RequestParam(value = "userCode", required = false) String userCode,HttpServletRequest request){
   		String resultJson = null;
   		    Bill bill = billService.ecommOrderListByUserId(billId, userCode);
   			bill.setOrderDate(Constants.DF_DMY.format(bill.getBillDate()));
   			bill.setNumberOfProducts(bill.getNumberOfProducts());
   			bill.setBillNumber(bill.getBillNumber());
   			bill.setBillStatus(bill.getBillStatus());
   			bill.setBillAmount(bill.getTotalAmount());
		resultJson = toJSON(bill);
		return resultJson;
	 }
   	
	@RequestMapping(value = "public/getUserAddress", method = RequestMethod.GET)
   	public @ResponseBody String getUserAddressDetails(@RequestParam(value = "userId", required = false) Long userId,HttpServletRequest request) throws Exception { 
   		User user = userService.getUserById(userId);
   		return toJSON(user);
   	}



    
    @RequestMapping(value = "ecomm/paymentAddressSaving")
	  public String paymentAddressSaving(@RequestParam(value="json", required=false) String json,@RequestParam(value="userId", required=false) Long userId,HttpServletRequest request ) throws Exception {
		   json = "{"+json+"}";
			JSONObject jsonObject = new JSONObject(json);
			
			Address address = new Address();
			if (jsonObject.has("name") && !jsonObject.getString("name").isEmpty()){
				address.setName(jsonObject.getString("name"));
			}
			if (jsonObject.has("pincode") && !jsonObject.getString("pincode").isEmpty()){
				address.setZipcode(jsonObject.getString("pincode"));
			}
			if (jsonObject.has("address") && !jsonObject.getString("address").isEmpty()){
				address.setAddress(jsonObject.getString("address"));
			}
			if (jsonObject.has("landMark") && !jsonObject.getString("landMark").isEmpty()){
				address.setLandmark(jsonObject.getString("landMark"));
			}
			if (jsonObject.has("phone") && !jsonObject.getString("phone").isEmpty()){
				address.setPhone(jsonObject.getString("phone"));
			}
			address.setStatus(Constants.Active);
			address.setCreatedDate(new Date());
			addressService.addAddress(address);
			
			if(userId != null){
				User user = userService.getUserById(userId);
				user.setId(userId);
				user.setAddress(address);
				userService.addUser(user);
			}
			return "paymentCheckout";
	  }
	 private String toJSON(Object obj){
	        XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
	            public HierarchicalStreamWriter createWriter(Writer writer) {
	                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
	            }
	        });
	        return xstream.toXML(obj);
	}
	 
	 
	 @RequestMapping(value = "public/validateEcommCurrentPassword",  method = RequestMethod.GET)
		public  String validateCurrentPassword(@RequestParam(value="currentPassword", required=false) String currentPassword,@RequestParam(value="userId", required=false) String userId,HttpServletRequest request) throws Exception {
		
			User user = userService.getUserById(Long.parseLong(userId));
			if(operoxPasswordEncoder.matches(currentPassword, user.getPassword())){
				return "valid";
			}else{
				return "invalid";
			}
		}
	  
		@RequestMapping(value = "public/resetEcommUserPassword",  method = RequestMethod.GET)
		public  String resetUserPassword(@RequestParam(value="json", required=false) String json, @RequestParam(value="userId", required=false) String userId,HttpServletRequest request) throws Exception {
		
			User user = userService.getUserById(Long.parseLong(userId));
	        json = "{"+json+"}";
			JSONObject jsonObj = new JSONObject(json);
			user.setPassword(operoxPasswordEncoder.encode(jsonObj.getString("password")));
			userService.addUser(user);
			OperoxSessionManager.setSessionAttribute(request, "user", user);
			
			return "success";
		}
		
		@RequestMapping(value = "public/validateEcommEmail",  method = RequestMethod.GET)
		public  String validateEcommEmail(@RequestParam(value="email", required=false) String email,HttpServletRequest request) throws Exception {
			User user = userService.findByEmail(email);
	   		if(user != null){
	           return "invalid";
	   		}else{
	   		    return "valid";
	   		}
		}
		
		
		@RequestMapping(value = "public/getCategoryList")
		@ResponseBody
		public String getCategoryList(HttpServletRequest request) throws Exception {
			String resultJson = null;
			List<Category> categoryList = categoryService.listCategories();
			resultJson = toJSON(categoryList);
			return resultJson;
		}
		
		
	 
}
