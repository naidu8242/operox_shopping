package com.bis.operox.production.rest;

import java.io.Writer;
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

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.production.dao.entity.Currency;
import com.bis.operox.production.dao.entity.QCCheckList;
import com.bis.operox.production.service.CurrencyService;
import com.bis.operox.production.service.QCCheckListReportService;
import com.bis.operox.production.service.QCCheckListService;
import com.bis.operox.production.service.RawMaterialService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;


@RestController
@PropertySource("${propertyLocation:operoxapp.properties}")
public class CurrencyRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyRestController.class);
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RawMaterialService rawMaterialService;
	
	@Autowired
	QCCheckListService qcCheckListService;
	
	@Autowired
	QCCheckListReportService qcCheckListReportService;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	CurrencyService currencyService;
	
	@RequestMapping(value = "/saveCurrency")
	@ResponseBody
	String saveCurrency(HttpServletRequest request,@RequestParam(value = "json", required = false) String json) throws Exception{
		String json1 = "{" + json + "}";
		JSONObject jsonObj = new JSONObject(json1);
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 currencyService.addCurrencyDetails(jsonObj,user);
		return "success";
	}
	
	@RequestMapping(value = "/getCurrencyList")
	@ResponseBody
	public String getCurrencyList(HttpServletRequest request) throws Exception {
		String resultJson = null;
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		List<Currency> currencyList = currencyService.listCurrency();
		resultJson = toJSON(currencyList);
		return resultJson;
	}
	
	@RequestMapping(value = "/removeCurrency", method = RequestMethod.POST)
	@ResponseBody String removeCurrency(@RequestParam(value = "currencyId", required = false) Long currencyId,HttpServletRequest request) throws Exception{
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		       Currency currency = currencyService.getCurrencyById(currencyId);
				if(currency != null){
					currency.setStatus(Constants.RECORD_IN_ACTIVE);
					currency.setUpdatedBy(user.getUserCode());
					currencyService.addCurrency(currency);
				}
			
				return "success";
		}
	
	@RequestMapping(value = "/validateCurrency",  method = RequestMethod.POST)
	@ResponseBody Boolean validateCurrency(@RequestParam(value = "currencyName", required = false) String currencyName) throws Exception {
	return currencyService.getCurrencyByCurrency(currencyName);
	}
	
	
	private String toJSON(Object obj){
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
            public HierarchicalStreamWriter createWriter(Writer writer) {
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }
        });
        return xstream.toXML(obj);
    }
}
