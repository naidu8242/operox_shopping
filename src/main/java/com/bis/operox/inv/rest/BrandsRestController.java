package com.bis.operox.inv.rest;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.BrandService;
import com.bis.operox.inv.service.CategoryService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.inv.web.CustomersController;
import com.bis.operox.util.OperoxSessionManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;


@RestController
@PropertySource("${propertyLocation:operoxapp.properties}")
public class BrandsRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(BrandsRestController.class);
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BrandService brandService;
	
	
	@RequestMapping(value = "/saveBrand")
	@ResponseBody
	String saveBrand(HttpServletRequest request,@RequestParam(value = "json", required = false) String json) throws Exception{
		String json1 = "{" + json + "}";
		JSONObject jsonObj = new JSONObject(json1);
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 brandService.addBrandsDetails(jsonObj,user);
		return "brandHome";
	}
	
	private String toJSON(Object obj){
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
            public HierarchicalStreamWriter createWriter(Writer writer) {
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }
        });
        return xstream.toXML(obj);
    }

	@RequestMapping(value = "/getBrandsList",  method = RequestMethod.POST)
	@ResponseBody String getBrandsList(@RequestParam(value = "categoryId", required = false) String categoryId) throws Exception {
		List<Brand> brandList = brandService.listBrands();
		
		for (Brand brand : brandList) {
			User user2 = userService.getUserByUserCode(brand.getUpdatedBy());
			brand.setUserName(user2.getFirstName()+user2.getLastName());
			}
		return toJSON(brandList);
	}
	
	@RequestMapping(value = "/removeBrand", method = RequestMethod.POST)
	@ResponseBody String removeBrand(@RequestParam(value = "brandId", required = false) Long brandId,HttpServletRequest request) throws Exception{
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		       Brand brand = brandService.getBrandById(brandId);
				if(brand != null){
					brand.setStatus(Constants.RECORD_IN_ACTIVE);
					brand.setUpdatedBy(user.getUserCode());
					brandService.addBrand(brand);
				}
			
				return "success";
		}
	
	@RequestMapping(value = "/validateBrand",  method = RequestMethod.POST)
	@ResponseBody Boolean validateBrand(@RequestParam(value = "brandName", required = false) String brandName) throws Exception {
	logger.info("Entered product name is "+brandName);
	return brandService.getBrandByBrandName(brandName);
	}
	
//	getSubBrands
}
