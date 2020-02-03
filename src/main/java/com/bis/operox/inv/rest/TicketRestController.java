package com.bis.operox.inv.rest;

import java.io.Writer;
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.bis.operox.constants.Constants;
import com.bis.operox.inv.dao.entity.Brand;
import com.bis.operox.inv.dao.entity.Category;
import com.bis.operox.inv.dao.entity.Ticket;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.BrandService;
import com.bis.operox.inv.service.CategoryService;
import com.bis.operox.inv.service.TicketService;
import com.bis.operox.inv.service.UserService;
import com.bis.operox.inv.web.CustomersController;
import com.bis.operox.util.CommonUtil;
import com.bis.operox.util.OperoxSessionManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;


@RestController
@PropertySource("${propertyLocation:operoxapp.properties}")
public class TicketRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TicketRestController.class);
	
	@Value("${operoxUrl}")
	private String operoxUrl;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	CommonUtil commonUtil;
	
	@RequestMapping(value = "/saveTicket")
	@ResponseBody
	String saveTicket(@RequestParam(value = "json", required = false) String json,MultipartHttpServletRequest request) throws Exception{
		String json1 = "{" + json + "}";
		MultipartFile multiFile = null;
		Iterator<String> itrator = request.getFileNames();
		if(itrator.hasNext()){
	        multiFile = request.getFile(itrator.next());
		}
		JSONObject jsonObj = new JSONObject(json1);
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 ticketService.addTicketDetails(jsonObj,user,multiFile);
		return "ticketHome";
	}
	
	@RequestMapping(value = "/getTicketsList",  method = RequestMethod.GET)
	 @ResponseBody
	    public  String getTicketsList(HttpServletRequest request) throws Exception {
		 User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		 List<Ticket> ticketsList = ticketService.ticketsList();
		 for (Ticket ticket : ticketsList) {
			 ticket.setAssignedUserName(ticket.getUser().getFirstName()+ticket.getUser().getLastName());
		}
		 
		 return commonUtil.toJSON(ticketsList);
		 
	    }
	
	@RequestMapping(value = "/deleteTicket", method = RequestMethod.GET)
	@ResponseBody
	public String removeTicket(@RequestParam(value = "ticketId", required = false) Long ticketId,HttpServletRequest request) throws Exception{
		User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		Ticket ticket = ticketService.getTicketById(ticketId);
				if(ticket != null){
					ticket.setStatus(Constants.RECORD_IN_ACTIVE);
					ticket.setUpdatedBy(user.getUserCode());
					ticketService.addTicket(ticket);
				}
			
				return "success";
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
