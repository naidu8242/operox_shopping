package com.bis.operox.inv.rest;

import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.inv.dao.entity.StockReturns;
import com.bis.operox.inv.dao.entity.Store;
import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.StockReturnsService;
import com.bis.operox.inv.service.StoreService;
import com.bis.operox.util.OperoxSessionManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;


@RestController
public class StockReturnsRestController {
	
	@Autowired
	StockReturnsService  stockReturnsService;
	
	@Autowired
	StoreService  storeService;
	

	@RequestMapping(value = "/storeReturnStock" , method = RequestMethod.POST)
	 public @ResponseBody  String storeReturnStock(@RequestParam(value="json", required=false) String json,HttpServletRequest request) throws Exception {
		   
		    User user = (User) OperoxSessionManager.getSessionAttribute(request, "user");
		
		    String json1 = "{"+json+"}";
		    JSONObject jsonObj = new JSONObject(json1);
		    StockReturns stockReturns =  stockReturnsService.addStockReturns(jsonObj,user);
		    return "success";		
	 }
	
	
	
	@RequestMapping(value = "/getStockReturnsList", method = RequestMethod.POST)
    public String StockReturnsList() {  
		   
		   String resultJson;
    	   List<StockReturns>  stockList = stockReturnsService.getAllStockReturns();
    	   
    	   resultJson =  toJSON(stockList);
		   return resultJson;
	        
	 
	}
	
	@RequestMapping(value = "/getStoreListExcludingFromStore", method = RequestMethod.POST)
    public String getStoreListExcludingFromStore(@RequestParam(value="fromStore", required=false) Long fromStore,HttpServletRequest request) {  
		   
		   String resultJson;
    	   List<Store> storeList =null;
			try {
				storeList = storeService.getStoreListExcludingFromStore(fromStore);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	   resultJson =  toJSON(storeList);
		   return resultJson;
	        
	 
	}
	
	public String toJSON(Object obj){
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
            public HierarchicalStreamWriter createWriter(Writer writer) {
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }
        });
        return xstream.toXML(obj);
    }
}
	
