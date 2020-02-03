package com.bis.operox.production.rest;

import java.util.Date;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bis.operox.inv.dao.entity.Product;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.ProductService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.production.dao.entity.QCCheckList;
import com.bis.operox.production.dao.entity.QCCheckListReport;
import com.bis.operox.production.dao.entity.RawMaterial;
import com.bis.operox.production.service.QCCheckListReportService;
import com.bis.operox.production.service.QCCheckListService;
import com.bis.operox.production.service.RawMaterialService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;


@RestController
@PropertySource("${propertyLocation:operoxapp.properties}")
public class CheckListRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(CheckListRestController.class);
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RawMaterialService rawMaterialService;
	
	@Autowired
	QCCheckListService qcCheckListService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	QCCheckListReportService qcCheckListReportService;
	
	@Autowired
	CommonUtil commonUtil;
	
	@RequestMapping(value = "/saveRawMaterialTask" , method = RequestMethod.POST)
	 public @ResponseBody  String saveRawMaterialTask(@RequestParam(value="json", required=false) String json,MultipartHttpServletRequest request) throws Exception {
		 
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 String json1 = "{" + json + "}";
		 MultipartFile multiFile = null;
		 Iterator<String> itrator = request.getFileNames();
			if(itrator.hasNext()){
		        multiFile = request.getFile(itrator.next());
			}
		 JSONObject jsonObj = new JSONObject(json1);
		 qcCheckListService.addWorkOrderCheckList(jsonObj, user,multiFile);
       return "Success";
			 
	   }
	
	
	@RequestMapping(value = "/saveProductTask" , method = RequestMethod.POST)
	 public @ResponseBody  String saveProductTask(@RequestParam(value="json", required=false) String json,MultipartHttpServletRequest request) throws Exception {
		 
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 String json1 = "{" + json + "}";
		 MultipartFile multiFile = null;
		 Iterator<String> itrator = request.getFileNames();
			if(itrator.hasNext()){
		        multiFile = request.getFile(itrator.next());
			}
		 JSONObject jsonObj = new JSONObject(json1);
		 qcCheckListService.addWorkOrderCheckListForProducts(jsonObj, user,multiFile);
      return "Success";
			 
	   }
	
	@RequestMapping(value = "/getCheckList",  method = RequestMethod.GET)
    @ResponseBody
       public  String getCustomersList(HttpServletRequest request) throws Exception {
        List<QCCheckList> checkList = qcCheckListService.listQcCheckList();
        for(QCCheckList qcCheckList : checkList){
           Integer checklistReport = qcCheckListReportService.getQcCheckListCountById(qcCheckList.getId());
           qcCheckList.setNoOfTests(checklistReport);
           if(qcCheckList.getRawMaterial() != null){
        	   	RawMaterial rawMaterial =  rawMaterialService.getRawMaterialById(qcCheckList.getRawMaterial().getId());
        	   	qcCheckList.setNameOfMaterial(rawMaterial.getMaterialName());
           }
           if(qcCheckList.getProductid() != null){
       	   	Product product =  productService.getProductById(qcCheckList.getProductid().getId());
       	   	qcCheckList.setNameOfMaterial(product.getProductName());
          }
           }
        logger.info("the total nimber of customers"+checkList.size());
        return commonUtil.toJSON(checkList);
        
       }
    
    @RequestMapping(value = "/deleteCheckList",method = RequestMethod.GET)
    @ResponseBody
       public String deleteCustomer(@RequestParam(value="checkListId", required=false) String checkListId,HttpServletRequest request){
        User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
        QCCheckList checkList = qcCheckListService.getQcCheckListById(Long.parseLong(checkListId));
           if(checkList != null){
               checkList.setStatus(0);
               checkList.setUpdatedBy(user.getFirstName());
               checkList.setUpdatedDate(new Date());
               qcCheckListService.addQcCheckList(checkList);
           }
           return "success";
       }
    
    @RequestMapping(value = "/getSubCheckReportList",  method = RequestMethod.POST)
    @ResponseBody
       public  String getSubCheckReportList(@RequestParam(value="checkListId", required=false) String checkListId,HttpServletRequest request) throws Exception {
    	 List<QCCheckListReport> checklistReport = qcCheckListReportService.getQcCheckReportByQCCheckList(Long.parseLong(checkListId));
        return commonUtil.toJSON(checklistReport);
        
       }
    
}
