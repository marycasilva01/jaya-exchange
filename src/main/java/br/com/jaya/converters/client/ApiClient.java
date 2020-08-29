package br.com.jaya.converters.client;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jaya.converters.client.api.ExchangeApi;
import br.com.jaya.converters.controller.TransactionController;

@Service
public class ApiClient {
	
	private static final Logger LOG = Logger.getLogger(TransactionController.class.getName());
	
	public ExchangeApi callURL(String param) {
		
	    RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "https://api.exchangeratesapi.io/latest";
	    
        Map<String, String> uriParam = new HashMap<>();
        uriParam.put("base", param);
        
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
        		.queryParam("base",param).build();
        
        ExchangeApi result = null;
        
        try {
	    	
        	result = restTemplate.getForObject(builder.toUriString(), ExchangeApi.class, uriParam);
	        
	    } catch(HttpClientErrorException ex)  {
	    	LOG.severe("Erro na chamada da AP: " + ex.toString());
	    }
        
        return result;
   }

}
