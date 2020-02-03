package com.bis.operox.inv.rest;

import java.io.Writer;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.MeasuringUnit;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.MeasuringUnitService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.util.OperoxSessionManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

@RestController
public class MeasuringUnitRestController {
	
	@Autowired
	private MeasuringUnitService measuringUnitService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/saveMeasuringUnit",  method = RequestMethod.POST)
	@ResponseBody String saveMeasuringUnit(@RequestParam(value = "json", required = false) String json, HttpServletRequest request) throws Exception {
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		String json1 = "{" + json + "}";
		JSONObject jsonObj = new JSONObject(json1);
		measuringUnitService.addMeasuringUnitDetails(jsonObj,user);
		return "success";
	}
	
	
	@RequestMapping(value = "/getmeasuringUnitsList")
	@ResponseBody
	public String getmeasuringUnitsList(HttpServletRequest request) throws Exception {
		String resultJson = null;
		List<MeasuringUnit> measuringUnitsList = measuringUnitService.listMeasuringUnit();
		for (MeasuringUnit measuringUnit : measuringUnitsList) {
				User user2 = userService.getUserByUserCode(measuringUnit.getUpdatedBy());
				measuringUnit.setUserName(user2.getFirstName());
		}
		resultJson = toJSON(measuringUnitsList);
		return resultJson;
		
	}
	
	@RequestMapping(value = "/deleteMeasuringUnit")
	 @ResponseBody
		public String deleteMeasuringUnit(@RequestParam(value="measuringUnitId", required=false) String measuringUnitId,HttpServletRequest request){
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 MeasuringUnit measuringUnit = measuringUnitService.getMeasuringUnitById(Long.parseLong(measuringUnitId));
			if(measuringUnit != null){
				measuringUnit.setStatus(0);
				measuringUnit.setUpdatedBy(user.getFirstName());
				measuringUnit.setUpdatedDate(new Date());
				measuringUnitService.addMeasuringUnit(measuringUnit);
			}
			return "success";
		}
	
	@RequestMapping(value = "/validateMeasuringUnit",  method = RequestMethod.POST)
	@ResponseBody Boolean validatePhone(@RequestParam(value = "measuringUnit", required = false) String measuringUnit) throws Exception {
		return measuringUnitService.validateMeasuringUnit(measuringUnit);
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
