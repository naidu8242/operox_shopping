package com.bis.operox.inv.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.inv.dao.entity.Counter;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.CounterService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

@RestController
public class CounterRestController {
	
	@Autowired
	private CounterService counterService;
	
	@Autowired 
	private CommonUtil commonUtil;
	
	@RequestMapping(value = "/addCounter",  method = RequestMethod.POST)
	public  String addStore(@RequestParam(value="json", required=false) String json, HttpServletRequest request) throws Exception {
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
         json = "{"+json+"}";
		 JSONObject jsonObj = new JSONObject(json);
		 counterService.addCounterDetails(jsonObj, user);
		return "counterList";
	}
	
	@RequestMapping(value = "/getCountersList")
	@ResponseBody
	public String getCountersList(HttpServletRequest request) throws Exception {
		List<Counter> countersList = counterService.listCounters();
		String resultJson = commonUtil.toJSON(countersList);
		return resultJson;
	}
	
	 @RequestMapping(value = "/removeCounter", method = RequestMethod.POST)
	 public @ResponseBody String removeCounter(@RequestParam(value = "counterId", required = false) Long counterId, HttpServletRequest request) throws Exception{
		   User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
	       counterService.removeCounterById(counterId,user.getUserCode());
			return "success";
		}
	 
	 @RequestMapping(value = "/validateCounterName" , method = RequestMethod.POST)
	 public @ResponseBody  Boolean validateCounterName(@RequestParam(value="storeId", required=false) String storeId, @RequestParam(value="counterName", required=false) String counterName,@RequestParam(value="counterId", required=false) String counterId, HttpServletRequest request) throws Exception {
		 	Boolean flag = false;
		    Counter counter = counterService.getCounterByCounterIdAndStoredIdAndCounterName(storeId,counterName,counterId);
		    if(counter == null){
		    	flag = counterService.validateCounterName(storeId,counterName);
		    }
			return flag;
	   }
}
