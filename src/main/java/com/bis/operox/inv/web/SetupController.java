package com.bis.operox.inv.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.Company;
import com.bis.operox.inv.dao.entity.Countries;
import com.bis.operox.inv.dao.entity.Department;
import com.bis.operox.inv.dao.entity.MeasuringUnit;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.Role;
import com.bis.operox.inv.dao.entity.Shift;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.BrandService;
import com.bis.operox.inv.service.CategoryService;
import com.bis.operox.inv.service.CountriesService;
import com.bis.operox.inv.service.DepartmentService;
import com.bis.operox.inv.service.MeasuringUnitService;
import com.bis.operox.inv.service.ProductService;
import com.bis.operox.inv.service.RoleService;
import com.bis.operox.inv.service.ShiftService;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.payroll.entity.Leaves;
import com.bis.operox.payroll.service.LeavesService;
import com.bis.operox.production.dao.entity.Currency;
import com.bis.operox.production.service.CurrencyService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@Controller
public class SetupController {
	
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	CategoryService categoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private CountriesService countriesService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private ShiftService shiftService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MeasuringUnitService measuringUnitService;
	
	@Autowired
	private LeavesService leavesService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/users")
	public ModelAndView usersHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.User, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("setup/usersHome");
		return model;

	}
	
	@RequestMapping(value = "/addUser")
	public ModelAndView addUser(HttpServletRequest request) throws Exception{
		
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.User, request);
		ModelAndView model = new ModelAndView();
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		Company company = (Company) OperoxSessionManager.getSessionAttribute(request, "company");
		Countries countries = countriesService.getCountriesByCountryName(user.getStore().getAddress().getCountry());
		if(countries!=null){
			model.addObject("loginCountryName", countries.getName());
		}
		model.addObject("operoxUrl", operoxUrl);
		
		List<Department> departmentList = departmentService.listDepartments();
		model.addObject("departmentList", departmentList);
		
		List<Store> storeList = storeService.getStoreListByCompanyId(company.getId());
		model.addObject("storeList", storeList);
				
		List<Role> roleList= roleService.getRoles();
		model.addObject("roleList", roleList);
		
	    List<Shift> shiftList = shiftService.shiftListByCompanyId(company.getId());
		model.addObject("shiftList", shiftList);
		
		model.setViewName("setup/addUser");
		return model;

	}
	
	@RequestMapping(value = "/editUser/{userId}")
	public ModelAndView editUser(@PathVariable Long userId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		User user = userService.getUserById(userId);
		Company company = (Company) OperoxSessionManager.getSessionAttribute(request, "company");
		
		List<Department> departmentList = departmentService.listDepartments();
		model.addObject("departmentList", departmentList);
		
		List<Store> storeList = storeService.getStoreListByCompanyId(company.getId());
		model.addObject("storeList", storeList);
				
		List<Role> roleList= roleService.getRoles();
		model.addObject("roleList", roleList);
		
	    List<Shift> shiftList = shiftService.shiftListByCompanyId(company.getId());
		model.addObject("shiftList", shiftList);
		
		model.addObject("user", user);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("setup/addUser");
		return model;

	}
	
	
	@RequestMapping(value = "/userView/{userId}")
	public ModelAndView userView(@PathVariable Long userId, HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		User user = userService.getUserById(userId);
		
		model.addObject("user", user);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("setup/userView");
		return model;

	}
	
	
	@RequestMapping(value = "/shift")
	public ModelAndView shiftHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Shift, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("setup/shiftHome");
		return model;

	}
	
	
	@RequestMapping(value = "/addShift")
	public ModelAndView addShift(HttpServletRequest request) throws Exception{
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("setup/addShift");
		return model;

	}
	
	
	@RequestMapping(value = "/editShift/{shiftId}")
	public ModelAndView editShift(@PathVariable Long shiftId,HttpServletRequest request){
	commonUtil.highLightMenu(Constants.Setup, request);
	Shift shift = null;
	ModelAndView model = new ModelAndView();
	if(null != shiftId && !"".equals(shiftId)){
		shift = shiftService.getShiftById(shiftId);
		model.addObject("shift", shift);
	}
	model.addObject("operoxUrl", operoxUrl);
	model.setViewName("setup/addShift");
	return model;

}

	
	@RequestMapping(value = "/shiftView/{shiftId}")
	public ModelAndView shiftView(@PathVariable Long shiftId,HttpServletRequest request){
	commonUtil.highLightMenu(Constants.Setup, request);
	Shift shift = null;
	ModelAndView model = new ModelAndView();
	if(null != shiftId && !"".equals(shiftId)){
		shift = shiftService.getShiftById(shiftId);
		model.addObject("shift", shift);
	}
	model.addObject("operoxUrl", operoxUrl);
	model.setViewName("setup/shiftView");
	return model;

}
	
	
	
	
	@RequestMapping(value = "/category")
	public ModelAndView categoryHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Category, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("setup/categoryHome");
		return model;

	}
	
	@RequestMapping(value = "/addCategory")
	public ModelAndView addCategory(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		model.setViewName("setup/addCategory");
		model.addObject("operoxUrl", operoxUrl);
		return model;
	}
	
	@RequestMapping(value = "/brandHome")
	public ModelAndView brandHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Brands, request);
		ModelAndView model = new ModelAndView();
		
		List<Brand> brandList=brandService.listBrands();
		model.addObject("brandList",brandList);
		model.setViewName("setup/brandHome");
		return model;

	}
	
	@RequestMapping(value = "/addBrand")
	public ModelAndView addBrand(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		List<Category>  categoryList = categoryService.listCategories();
		List<Brand> brandList=brandService.listBrands();
		model.addObject("brandList",brandList);
		model.addObject("categoryList",categoryList);
		model.setViewName("setup/addBrand");
		return model;

	}
	
	
	@RequestMapping(value = "/measuringUnit")
	public ModelAndView measuringUnitHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.MeasuringUnit, request);
		ModelAndView model = new ModelAndView();
		model.setViewName("setup/measuringUnitHome");
		model.addObject("operoxUrl", operoxUrl);
		return model;

	}
	
	
	@RequestMapping(value = "/addMeasuringUnit")
	public ModelAndView addMeasuringUnit(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		model.setViewName("setup/addMeasuringUnit");
		return model;

	}
	
	
	@RequestMapping(value = "/editCategory/{categoryId}")
	public ModelAndView editCategory(@PathVariable Long categoryId, HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		Category category = categoryService.getCategoryById(categoryId);
		List<Category> subCategoriesList = categoryService.getsubCategoriesList(categoryId);
		model.addObject("category", category);
		model.addObject("subCategoriesList", subCategoriesList);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("setup/editCategory");
		return model;

	}
	
	@RequestMapping(value = "/editBrands/{brandId}")
	public ModelAndView editBrands(@PathVariable Long brandId, HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		List<Category> categoriesList = categoryService.listCategories();
		Brand brand = brandService.getBrandById(brandId);
		model.addObject("brand", brand);
		model.addObject("categoryList", categoriesList);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("setup/addBrand");
		return model;

	}
	
	@RequestMapping(value = "/editMeasuringUnit/{measuringUnitId}")
	public ModelAndView editCustomer(@PathVariable Long measuringUnitId,HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		MeasuringUnit measuringUnit = measuringUnitService.getMeasuringUnitById(measuringUnitId);
		model.addObject("measuringUnit",measuringUnit);
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("setup/addMeasuringUnit");
		return model;
	}
	
	@RequestMapping(value = "/viewBrand/{brandId}")
	public ModelAndView viewCustomer(@PathVariable Long brandId,HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		Brand brand = brandService.getBrandById(brandId);
		if(brand.getUpdatedDate()!=null){
			brand.setUpdateDateStr(commonUtil.getDateString(brand.getUpdatedDate()));
		}
		model.addObject("brand",brand);
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("setup/viewBrand");
		return model;
	}
	
	/**
	 * Handles view details of Measuring unit	
	 * @param mid
	 * @param request
	 * @return
	 */
		@RequestMapping(value = "/viewMeasuringunit/{mid}")
		public ModelAndView viewMeasuringunit(@PathVariable Long mid,HttpServletRequest request){
			commonUtil.highLightMenu(Constants.Setup, request);
			ModelAndView model = new ModelAndView();
			MeasuringUnit measuringUnit = measuringUnitService.getMeasuringUnitById(mid);
			if(measuringUnit.getCreatedDate()!=null){
				measuringUnit.setDateStr(commonUtil.getDateString(measuringUnit.getCreatedDate()));
			}
			
			
			model.addObject("measuringUnit",measuringUnit);
			model.addObject("operoxUrl",operoxUrl);
			model.setViewName("setup/viewMeasurUnit");
			return model;
		}




	
	@RequestMapping(value = "/viewCategory/{categoryId}")
	public ModelAndView viewCategory(@PathVariable Long categoryId,HttpServletRequest request) throws Exception{
		commonUtil.highLightMenu(Constants.Setup, request);
		ModelAndView model = new ModelAndView();
		Category category = categoryService.getCategoryById(categoryId);
			User user2 = userService.getUserByUserCode(category.getUpdatedBy());
			category.setUserName(user2.getFirstName());
		model.addObject("category",category);
		model.addObject("operoxUrl",operoxUrl);
		model.setViewName("setup/viewCategory");
		return model;
	}
	
	
	
	@RequestMapping(value = "/offers")
	public ModelAndView offers(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Offers, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("setup/offersHome");
		return model;
	
	}
	
	@RequestMapping(value = "/addOffer")
	public ModelAndView addOffer(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Offers, request);
		Company company = (Company) OperoxSessionManager.getSessionAttribute(request, "company");
		ModelAndView model = new ModelAndView();
		List<Store> storesList = null;
		try {
			 storesList =  storeService.storesListByCompanyId(company.getId());
			 if(null != storesList){
				 model.addObject("storesList", storesList);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Product> buyItemsProductsList = productService.getAllOfferProducts(company.getId());
		model.addObject("buyItemsProductsList", buyItemsProductsList);
		
		List<Product> productsList = productService.getAllProducts(company.getId());
		model.addObject("productsList", productsList);
		
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("setup/addOffer");
		return model;
	
	}
	
}
	
