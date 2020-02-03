package com.bis.operox.inv.rest;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.Company;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.Shift;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.CategoryService;
import com.bis.operox.inv.service.ProductService;
import com.bis.operox.inv.service.ShiftService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.production.service.CurrencyService;

import com.bis.operox.util.AutoCompleteUtil;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;


@RestController
public class SetupRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;

	@Autowired
	ShiftService shiftService;
	
	@Autowired
	CurrencyService currencyService;
	
	
	@Autowired
	CommonUtil commonutill;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/getUsersList")
	@ResponseBody
	public String getUsersList(HttpServletRequest request,
			@RequestParam(value="loginUserRole", required=false) String loginUserRole) {
		
	 	String resultJson = null;
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		Company company = (Company) OperoxSessionManager.getSessionAttribute(request, "company");
		List<User> usersList = new ArrayList<User>();
		ArrayList<User> userList = new ArrayList<User>();
		
		try {
			usersList = userService.getUserListByStoreId(company.getId(), user.getUserCode(), loginUserRole);
			for(User users : usersList){
				users.setLocation(users.getAddress().getCountry());
				users.setRoleName(users.getRole().getDisplayName());
				users.setStoreName(users.getStore().getStoreName());
				userList.add(users);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		 resultJson = toJSON(userList);
		 return resultJson;

	}
	

	@RequestMapping(value = "/saveCategory")
	@ResponseBody
	String saveCategory(HttpServletRequest request,@RequestParam(value = "json", required = false) String json,@RequestParam(value = "subDivLength", required = false) String subDivLength) throws Exception{
		String json1 = "{" + json + "}";
		JSONObject jsonObj = new JSONObject(json1);
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		categoryService.addCategoryDetailsandSubCatDetails(jsonObj,Integer.parseInt(subDivLength),user);
		return "success";

	}
	
	
	
	@RequestMapping(value = "/addSaveUser", method = RequestMethod.POST)
	public @ResponseBody String addUser(@RequestParam(value = "json", required = false) String json,HttpServletRequest request) throws Exception {
		String json1 = "{" + json + "}";
		JSONObject jsonObj = new JSONObject(json1);
		User loginUser = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		userService.storeUser(jsonObj,loginUser);
		return "userHome";
	} 
	
	
	@RequestMapping(value = "/removeUser", method = RequestMethod.POST)
	 public @ResponseBody String removeUser(@RequestParam(value = "id", required = false) Long id) throws Exception{
		
		String resultJson;

		 User  department = userService.removeUserById(id);
		 resultJson = commonutill.toJSON(department);
		 return resultJson;
		
	}
	
	//to validate EmployeeId
	 @RequestMapping(value = "/validateEmployeeId" , method = RequestMethod.POST)
	 public @ResponseBody  Boolean validateEmployeeId(@RequestParam(value="EmployeeId", required=false) String employeeId, HttpServletRequest request) throws Exception {
		    return userService.validateEmployeeId(employeeId);
	   }
	 
	 //to  validate user email
	 @RequestMapping(value = "/getEmailVerification" , method = RequestMethod.POST)
	 public @ResponseBody  String validateUserEmail(@RequestParam(value="EmailId", required=false) String EmailId) throws Exception {
		 	
		 	String result;
		 	User user = userService.findByEmail(EmailId);
		 	
		 	if (user != null) {
				result = "true";
			}else{
				result = "false";
			}
			return result;
	   }
	 
	
	@RequestMapping(value = "/getShiftsList")
	@ResponseBody
	public String getShiftsList(HttpServletRequest request) {
		
	 	String resultJson = null;
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		List<Shift> shiftsList = new ArrayList<Shift>();
		
		try {
			shiftsList = shiftService.shiftListByStoreId(user.getStore().getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		 resultJson = toJSON(shiftsList);
		 return resultJson;

	}
	
	
	@RequestMapping(value = "/storeShift",  method = RequestMethod.POST)
	public  String storeShift(@RequestParam(value="json", required=false) String json, HttpServletRequest request) throws Exception {
	
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
         json = "{"+json+"}";
		 
		 JSONObject jsonObj = new JSONObject(json);
		 shiftService.addShiftDetails(jsonObj, user);
		
		return "shiftHome";
	}
	
	@RequestMapping(value = "/removeShift",  method = RequestMethod.POST)
	public  String removeShift(@RequestParam(value="shiftId", required=false) Long shiftId, HttpServletRequest request) throws Exception {
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 Shift shift = shiftService.getShiftById(shiftId);
		 shift.setStatus(0);
		 shift.setUpdatedBy(user.getUserCode());
		 shift.setUpdatedDate(new Date());
		 shiftService.storeShift(shift);
		
		return "shiftHome";
	}
	
	private String toJSON(Object obj){
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
            public HierarchicalStreamWriter createWriter(Writer writer) {
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }
        });
        return xstream.toXML(obj);
    }

	@RequestMapping(value = "/getCategoryList")
	@ResponseBody
	public String getCategoryList(HttpServletRequest request) throws Exception {
		String resultJson = null;
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		List<Category> categoryList = categoryService.listOfCategories(user);
		
		for (Category category : categoryList) {
			User user2 = userService.getUserByUserCode(category.getUpdatedBy());
			category.setUserName(user2.getFirstName());
			}
		resultJson = toJSON(categoryList);
		return resultJson;
	}
	
	
	@RequestMapping(value = "/removeCategory", method = RequestMethod.POST)
	@ResponseBody String removeCategory(@RequestParam(value = "categoryId", required = false) Long categoryId,HttpServletRequest request) throws Exception{
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		       Category category = categoryService.getCategoryById(categoryId);
				if(category != null){
					category.setStatus(Constants.RECORD_IN_ACTIVE);
					category.setUpdatedBy(user.getUserCode());
					categoryService.addCategory(category);
				}
			
				return "success";
		}
	
	@RequestMapping(value = "/validateCategory",  method = RequestMethod.POST)
	@ResponseBody Boolean validateCategory(@RequestParam(value = "categoryName", required = false) String categoryName) throws Exception {
	return categoryService.getCatgoryByCategoryName(categoryName);
	}
	
	@RequestMapping(value = "/getSubCategoryList")
	@ResponseBody
	public String getSubCategoryList(@RequestParam(value = "categoryId", required = false) Long categoryId,HttpServletRequest request) throws Exception {
		String resultJson = null;
		List<Category> subCategoriesList = categoryService.getsubCategoriesList(categoryId);
		resultJson = toJSON(subCategoriesList);
		return resultJson;
	}
	


}
