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

import com.bis.operox.inv.dao.entity.Department;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.DepartmentService;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;

/**
 * @author Ajith Matta
 *
 */
@RestController
public class DepartmentRestController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	CommonUtil commonutill;
	
	
	/**
	 * @author Ajith Matta
	 * @param json
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/storeDepartmentById", method = RequestMethod.POST)
    public String storeDepartmentById(@RequestParam(value = "json", required = false) String json,HttpServletRequest request) throws Exception {
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		String json1 = "{"+json+"}";
		JSONObject jsonObject = new JSONObject(json1);
		departmentService.addDepartment(jsonObject, user);
		return "departmentList";
	 }
	
	/**
	 * @author Ajith Matta
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getDepartmentList")
	public String getDepartmentList(HttpServletRequest request) throws Exception{
		String resultJson;
		 List<Department>   departmentList = departmentService.listDepartments();
		resultJson = commonutill.toJSON(departmentList);
		return resultJson;
		
	}
	
	/**
	 * @author Ajith Matta
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getDepartmentId")
	public String getDepartmentId(@RequestParam(value = "id", required = false) Long id,HttpServletRequest request) throws Exception{
		String resultJson;
		Department departmentList = departmentService.getDepartmentById(id);
		resultJson = commonutill.toJSON(departmentList);
		 return resultJson;
	}
	
	/**
	 * @author Ajith Matta
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeDepartment", method = RequestMethod.POST)
	 public String removeDepartment(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) throws Exception{
		
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 departmentService.removeDepartmentById(id,user.getUserCode());
		 return "success";
		
	}
	
	@RequestMapping(value = "/validateDepartmentNameAndId" , method = RequestMethod.POST)
	 public  Boolean validateDepartmentNameAndId(@RequestParam(value="departmentName", required=false) String departmentName,@RequestParam(value="departmentId", required=false) String departmentId,@RequestParam(value="id", required=false) String id,
			HttpServletRequest request) throws Exception {
		 	Boolean flag = false;
		 	Department department = departmentService.getDepartmentByDeptIdAndDepartmentName(id,departmentName,departmentId);
		    if(department == null){
		    	flag = departmentService.validateDepartmentNameAndId(departmentName,departmentId);
		    }
			return flag;
	   }

}
