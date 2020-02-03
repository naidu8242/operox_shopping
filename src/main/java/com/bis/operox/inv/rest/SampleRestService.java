package com.bis.operox.inv.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleRestService {
	
	@RequestMapping(value = "/greetService/{name}",  method = RequestMethod.GET)
	public @ResponseBody String addEmployee(@PathVariable String name) throws Exception {
	
		return "Hello Mr."+name;
	}

}
