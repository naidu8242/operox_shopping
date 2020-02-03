package com.bis.operox.inv.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.Customer;
import com.bis.operox.inv.dao.entity.MeasuringUnit;
import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.service.BrandService;
import com.bis.operox.inv.service.CategoryService;
import com.bis.operox.inv.service.MeasuringUnitService;
import com.bis.operox.inv.service.ProductService;
import com.bis.operox.util.CommonUtil;

@Controller
public class ProductController {
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	MeasuringUnitService measuringUnitService;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	CategoryService categoryService;
	
	/*@PreAuthorize("hasRole('ROLE_STORE_MANAGER')")*/
    @RequestMapping(value = "/product")
	public ModelAndView productHome(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		commonUtil.highLightSubMenu(Constants.Product, request);
		ModelAndView model = new ModelAndView();
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("product/productHome");
		return model;

	}

	@RequestMapping(value = "/addProduct")
	public ModelAndView addProduct(HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
		
		List<Category> categoryList = categoryService.listCategories();
		List<Brand> brandsList = brandService.listBrands();
		List<MeasuringUnit> measuringUnitList =  measuringUnitService.listMeasuringUnit();
		ModelAndView model = new ModelAndView();
		model.addObject("measuringUnitList", measuringUnitList);
		model.addObject("brandsList",brandsList);
		model.addObject("categoryList", categoryList);
		model.addObject("operoxUrl", operoxUrl);
		model.setViewName("product/addProduct");
		return model;
	}
	
	@RequestMapping(value = "/editProduct/{productId}")
	public ModelAndView editProduct(@PathVariable Long productId,HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
        ModelAndView model = new ModelAndView();
        Product product = productService.getProductById(productId);
            if(product.getCategory()!=null){
                product.setCatagoryName(product.getCategory().getCategoryName());
            }
            if(product.getBrand()!=null){
                product.setBrandName(product.getBrand().getBrandName());
            }
        model.addObject("product", product);
        List<Category> categoryList = categoryService.listCategories();
        model.addObject("categoryList", categoryList);
        List<MeasuringUnit> measuringUnitList =  measuringUnitService.listMeasuringUnit();
		model.addObject("measuringUnitList", measuringUnitList);
        List<Brand> brandsList = brandService.listBrands();
        model.addObject("brandsList", brandsList);
        model.addObject("operoxUrl", operoxUrl);
        model.setViewName("product/addProduct");
        return model;
	}
	
	@RequestMapping(value = "/viewProduct/{pid}")
	public ModelAndView viewCustomer(@PathVariable Long pid,HttpServletRequest request){
		commonUtil.highLightMenu(Constants.Setup, request);
        ModelAndView model = new ModelAndView();
        Product product = productService.getProductById(pid);
        String date = commonUtil.getDateString(product.getUpdatedDate());
        product.setDateStr(date);
            if(product.getCategory()!=null){
                product.setCatagoryName(product.getCategory().getCategoryName());
                product.setSubCatagoryName(product.getSubCatagoryName());
            }
            if(product.getBrand()!=null){
                product.setBrandName(product.getBrand().getBrandName());
            }
        model.addObject("product", product);
        model.addObject("operoxUrl", operoxUrl);
        model.setViewName("product/viewProduct");
        return model;
	}
}
