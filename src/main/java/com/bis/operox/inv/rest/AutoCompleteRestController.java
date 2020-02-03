package com.bis.operox.inv.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bis.operox.inv.dao.entity.Countries;
import com.bis.operox.inv.dao.entity.States;
import com.bis.operox.inv.dao.entity.Webgeocities;
import com.bis.operox.inv.service.CountriesService;
import com.bis.operox.inv.service.StatesService;
import com.bis.operox.inv.service.WebgeocitiesService;
import com.bis.operox.util.CommonUtil;

/**
 * Prasad Salina
 * @author bis113
 *
 */

@RestController
public class AutoCompleteRestController {

	private static final Logger logger = LoggerFactory.getLogger(AutoCompleteRestController.class);
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private CountriesService countriesService;
	
	@Autowired
	private StatesService statesService;
	
	@Autowired
	private WebgeocitiesService webgeocitiesService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@PostConstruct
	public void init() {
		logger.debug(" *** AutoCompleteController init with: " + applicationContext);
	}
   /* @RequestMapping(value = "/clientAutoComplete")
    public String clientAutoComplete(HttpServletRequest request) throws Exception {
    	OrgLocations orgLocations = (OrgLocations) SourceLeadSessionManager.getSessionAttribute(request, "orgLocations");
    	Collection<OrgRelations> clientsList = orgRelationsService.getAllClientsByOrgLocationId(orgLocations.getId());
    	for(OrgRelations orgRelations : clientsList ){
    		orgRelations.setClientName(orgRelations.getResponderLocationId().getOrg().getOrgName());
    	}
    	String resultJson = commonUtil.toJSON(clientsList);
    	return resultJson;
	}*/
    
    @RequestMapping(value = "/countryAutoComplete")
    public String countryAutoComplete() throws Exception {
    	
    	ArrayList<String> countriesList = new ArrayList<>();
    	
    	List<Countries> countryList = countriesService.getAllCountries();
    	 
    	if(countryList != null && !countryList.isEmpty())
    	{
    		for(Countries countries: countryList){
    			countriesList.add(countries.getName());
    		}
    	}
		
		String[] temp = (String[]) countriesList.toArray(new String[] {});
		JSONArray mJSONArray = new JSONArray(Arrays.asList(temp));
		
		String result = mJSONArray.toString();
		
        return result;
	}
    
    @RequestMapping(value = "/sourceLeadCountryAutoComplete")
    public String sourceLeadCountryAutoComplete() throws Exception {
    	
    	ArrayList<String> countriesList = new ArrayList<>();
    	
    	List<Countries> countryList = countriesService.getAllSourceLeadCountries();
    	 
    	if(countryList != null && !countryList.isEmpty())
    	{
    		for(Countries countries: countryList){
    			countriesList.add(countries.getName());
    		}
    	}
		
		String[] temp = (String[]) countriesList.toArray(new String[] {});
		JSONArray mJSONArray = new JSONArray(Arrays.asList(temp));
		
		String result = mJSONArray.toString();
		
        return result;
	}
    
    
    @RequestMapping(value = "/locationAutoComplete")
    public String locationAutoComplete(@RequestParam(value="countryName", required=false) String countryName) throws Exception {
    	ArrayList<String> countriesList = new ArrayList<>();
    	List<String> countryList = countriesService.getAllStatesAndCitiesByCountryName(countryName);
    	if(countryList != null && !countryList.isEmpty())
    	{
    		for(String countries: countryList){
    			countriesList.add(countries);
    		}
    	}
		String[] temp = (String[]) countriesList.toArray(new String[] {});
		JSONArray mJSONArray = new JSONArray(Arrays.asList(temp));
		
		String result = mJSONArray.toString();
        return result;
	}
    
    @RequestMapping(value = "/getStatesByCountry")
    public String getStatesByCountry(@RequestParam(value="countryName", required=false) String countryName) throws Exception {
    	
    	ArrayList<String> statesList = new ArrayList<>();
    	
    	List<States> states = statesService.getAllStatesByCountry(countryName);
    	 
    	if(states != null && !states.isEmpty())
    	{
    		for(States state: states){
    			statesList.add(state.getStateName());
    		}
    	}
		
		String[] temp = (String[]) statesList.toArray(new String[] {});
		JSONArray mJSONArray = new JSONArray(Arrays.asList(temp));
		
		String result = mJSONArray.toString();
		
        return result;
	}
    
    
    @RequestMapping(value = "/getCitiesByStateAndCountry")
    public String getCitiesByStateAndCountry(@RequestParam(value="countryName", required=false) String countryName, @RequestParam(value="stateName", required=false) String stateName) throws Exception {
    	
    	ArrayList<String> citiesList = new ArrayList<>();
    	
    	List<Webgeocities> cities = webgeocitiesService.getAllCitiesByStateAndCountry(stateName,countryName);
    	 
    	if(cities != null && !cities.isEmpty())
    	{
    		for(Webgeocities city: cities){
    			citiesList.add(city.getCityName());
    		}
    	}
		String[] temp = (String[]) citiesList.toArray(new String[] {});
		JSONArray mJSONArray = new JSONArray(Arrays.asList(temp));
		String result = mJSONArray.toString();
        return result;
	}
    
}
