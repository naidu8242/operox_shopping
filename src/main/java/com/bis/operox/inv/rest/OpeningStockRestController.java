package com.bis.operox.inv.rest;

import java.io.Writer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.inv.dao.entity.ProductStock;
import com.bis.operox.inv.service.ProductStockService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

@RestController
public class OpeningStockRestController {
	@Autowired
	 
	ProductStockService  productStockService;
	
	@RequestMapping(value = "/getOpeningStockList", method = RequestMethod.POST)
    public String OpeningStockList() {  
		   
		   String resultJson;
    	   List<ProductStock>  productList = productStockService.listProductStock();
    	   
    	   resultJson =  toJSON(productList);
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
