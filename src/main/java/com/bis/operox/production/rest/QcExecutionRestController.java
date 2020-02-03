package com.bis.operox.production.rest;

import java.io.Writer;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.Supplier;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.production.dao.entity.QCCheckList;
import com.bis.operox.production.dao.entity.QCCheckListReport;
import com.bis.operox.production.dao.entity.QCExecution;
import com.bis.operox.production.dao.entity.QCExecutionResults;
import com.bis.operox.production.dao.entity.RawMaterial;
import com.bis.operox.production.service.QCCheckListReportService;
import com.bis.operox.production.service.QCCheckListService;
import com.bis.operox.production.service.QCExecutionResultsService;
import com.bis.operox.production.service.QCExecutionService;
import com.bis.operox.production.service.RawMaterialService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;


@RestController
@PropertySource("${propertyLocation:operoxapp.properties}")
public class QcExecutionRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(QcExecutionRestController.class);
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	UserService userService;
	
	@Autowired
	QCCheckListService qcCheckListService;
	
	@Autowired
	RawMaterialService rawMaterialService;
	
	@Autowired
	QCCheckListReportService qcCheckListReportService;
	
	@Autowired
	QCExecutionResultsService qcExecutionResultsService;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	QCExecutionService qcExecutionService;
	
	@RequestMapping(value = "/getCheckListByMaterialId")
	@ResponseBody String getCheckListByMaterialId(@RequestParam(value = "materialId", required = false) Long materialId,HttpServletRequest request) throws Exception {
		String resultJson = null;
		List<QCCheckList> qcCheckList = qcCheckListService.getQcCheckListByRawMaterialId(materialId);
		resultJson = toJSON(qcCheckList);
		return resultJson;
	}
	
	@RequestMapping(value = "/getCheckListByProductId")
	@ResponseBody String getCheckListByProductId(@RequestParam(value = "productId", required = false) Long productId,HttpServletRequest request) throws Exception {
		String resultJson = null;
		List<QCCheckList> qcCheckList = qcCheckListService.getQcCheckListByProductId(productId);
		resultJson = toJSON(qcCheckList);
		return resultJson;
	}
	
	@RequestMapping(value = "/getTestListByCheckListId")
	@ResponseBody String getTestListByCheckListId(@RequestParam(value = "checkList", required = false) Long checkList,HttpServletRequest request) throws Exception {
		String resultJson = null;
		List<QCCheckListReport> checklistReport = qcCheckListReportService.getQcCheckReportByQCCheckList(checkList);
		resultJson = toJSON(checklistReport);
		return resultJson;
	}
	
	@RequestMapping(value = "/getTestListByExecutionId")
	@ResponseBody String getTestListByExecutionId(@RequestParam(value = "executionId", required = false) Long executionId,@RequestParam(value = "checkList", required = false) Long checkList,HttpServletRequest request) throws Exception {
		String resultJson = null;
		List<QCExecutionResults> qcExecutionResults = qcExecutionResultsService.getQCExecutionResultsByQCExecution(executionId);
		List<QCCheckListReport> checklistReport = qcCheckListReportService.getQcCheckReportByQCCheckList(checkList);
		for(QCExecutionResults executionResults :qcExecutionResults){
			for(QCCheckListReport qcChecklistReport :checklistReport){
				executionResults.setHowToCheck(qcChecklistReport.getHowToCheck());
				executionResults.setWhatToCheck(qcChecklistReport.getWhatToCheck());
				executionResults.setTestDescription(qcChecklistReport.getTestDescription());
			}
		}
		resultJson = toJSON(qcExecutionResults);
		return resultJson;
	}
	
	@RequestMapping(value = "/saveExecute" , method = RequestMethod.POST)
	 public @ResponseBody  String saveExecute(@RequestParam(value="json", required=false) String json,MultipartHttpServletRequest request) throws Exception {
		 
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 String json1 = "{" + json + "}";
		 MultipartFile multiFile = null;
		 Iterator<String> itrator = request.getFileNames();
			if(itrator.hasNext()){
		        multiFile = request.getFile(itrator.next());
			}
		 JSONObject jsonObj = new JSONObject(json1);
		 qcExecutionService.addQCExcetion(jsonObj, user,multiFile);
      return "Success";
			 
	   }
	
	private String toJSON(Object obj){
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
            public HierarchicalStreamWriter createWriter(Writer writer) {
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }
        });
        return xstream.toXML(obj);
    }
	
	@RequestMapping(value = "/getQcQaRawList",  method = RequestMethod.GET)
	 @ResponseBody
	    public  String getQcQaRawMaterialList(@RequestParam(value="workOrderId", required=false) String workOrderId,@RequestParam(value="type", required=false) String type, HttpServletRequest request) throws Exception {
		List<QCExecution> qcExecutionList = qcExecutionService.getQcExecutionList(Long.parseLong(workOrderId),type);
		for(QCExecution qcExecution : qcExecutionList){
			if(qcExecution.getRawMaterial() != null && !"".equals(qcExecution.getRawMaterial())){
				if(qcExecution.getRawMaterial().getMaterialName() != null && !"".equals(qcExecution.getRawMaterial().getMaterialName())){
					qcExecution.setRawMaterialName(qcExecution.getRawMaterial().getMaterialName());
				}
			}
			if(qcExecution.getQcCheckList() != null && !"".equals(qcExecution.getQcCheckList())){
				if(qcExecution.getQcCheckList().getCheckName() != null && !"".equals(qcExecution.getQcCheckList().getCheckName())){
					qcExecution.setQcCheckListName(qcExecution.getQcCheckList().getCheckName());
				}
			}
			if(qcExecution.getProduct() != null && !"".equals(qcExecution.getProduct())){
				if(qcExecution.getProduct().getProductName() != null && !"".equals(qcExecution.getProduct().getProductName())){
					qcExecution.setProductName(qcExecution.getProduct().getProductName());
				}
			}
		}
		return commonUtil.toJSON(qcExecutionList);
	 }
	
	@RequestMapping(value = "/deleteQcExecution",method = RequestMethod.GET)
	 @ResponseBody
		public String deleteCustomer(@RequestParam(value="qcExecutionId", required=false) String qcExecutionId,HttpServletRequest request){
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		QCExecution qcExecution = qcExecutionService.getQCExecutionById(Long.parseLong(qcExecutionId));
			if(qcExecution != null){
				qcExecution.setStatus(0);
				qcExecution.setUpdatedBy(user.getFirstName());
				qcExecution.setUpdatedDate(new Date());
				qcExecutionService.addQCExecution(qcExecution);
			}
			return "success";
		}
	
	@RequestMapping(value = "/editExecution",method = RequestMethod.GET)
	 @ResponseBody
	 public String editExecution(@RequestParam(value="executionId", required=false) String executionId,HttpServletRequest request){
		QCExecution qcExecution = qcExecutionService.getQCExecutionById(Long.parseLong(executionId));
		if(qcExecution.getStartDate() != null && !"".equals(qcExecution.getStartDate())){
			qcExecution.setStartDateStr(commonUtil.getDateString(qcExecution.getStartDate()));
		}
		if(qcExecution.getEndDate() != null && !"".equals(qcExecution.getEndDate())){
			qcExecution.setEndDateStr(commonUtil.getDateString(qcExecution.getEndDate()));
		}
		return commonUtil.toJSON(qcExecution);
	}
	
	@RequestMapping(value = "/viewExecution",method = RequestMethod.GET)
	 @ResponseBody
	 public String viewExecution(@RequestParam(value="executionId", required=false) String executionId,HttpServletRequest request){
		QCExecution qcExecution = qcExecutionService.getQCExecutionById(Long.parseLong(executionId));
		if(qcExecution.getRawMaterial() != null && !"".equals(qcExecution.getRawMaterial())){
			if(qcExecution.getRawMaterial().getMaterialName() != null && !"".equals(qcExecution.getRawMaterial().getMaterialName())){
				qcExecution.setRawMaterialName(qcExecution.getRawMaterial().getMaterialName());
			}
		}
		if(qcExecution.getQcCheckList() != null && !"".equals(qcExecution.getQcCheckList())){
			if(qcExecution.getQcCheckList().getCheckName() != null && !"".equals(qcExecution.getQcCheckList().getCheckName())){
				qcExecution.setQcCheckListName(qcExecution.getQcCheckList().getCheckName());
				qcExecution.setCheckListId(qcExecution.getQcCheckList().getId());
			}
		}
		if(qcExecution.getProduct() != null && !"".equals(qcExecution.getProduct())){
			if(qcExecution.getProduct().getProductName() != null && !"".equals(qcExecution.getProduct().getProductName())){
				qcExecution.setProductName(qcExecution.getProduct().getProductName());
				qcExecution.setProductId(qcExecution.getProduct().getId());
			}
		}
		if(qcExecution.getStartDate() != null && !"".equals(qcExecution.getStartDate())){
			qcExecution.setStartDateStr(commonUtil.getDateString(qcExecution.getStartDate()));
		}
		if(qcExecution.getEndDate() != null && !"".equals(qcExecution.getEndDate())){
			qcExecution.setEndDateStr(commonUtil.getDateString(qcExecution.getEndDate()));
		}
		return commonUtil.toJSON(qcExecution);
	}
}
