package br.com.jaya.converters.client;

import static org.junit.Assert.assertNotNull;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jaya.converters.client.api.ExchangeApi;

@ExtendWith (MockitoExtension.class)
class ApiClientTest {
	
	@InjectMocks
	private ApiClient apiClient ;

	@Test
	void testClient() {
		
		ExchangeApi result = apiClient.callURL("BRL");
		assertNotNull(result);
	}
	
	@Test
	void testClientError() {
		
		try {
			
			ExchangeApi result = apiClient.callURL("");
			assertNotNull(result);
		}
		catch (HttpClientErrorException ex) {
			 Assert.assertEquals(400, ex.getRawStatusCode());
		     Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
		}
	}
	
	@Test
	public void testGetEmployeeListSuccessWithHeaders() throws URISyntaxException 
	{
	    RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "https://api.exchangeratesapi.io/latest";
	    String param = "USD";
	    
        Map<String, String> uriParam = new HashMap<>();
        uriParam.put("base", param);
        
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("base",param).build();
	    try {
	    	
	    	ExchangeApi result = restTemplate.getForObject(builder.toUriString(), ExchangeApi.class, uriParam);
	       
	    	assertNotNull(result);;
	        
	    } catch(HttpClientErrorException ex)  {
	        Assert.assertEquals(400, ex.getRawStatusCode());
	        Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
	    }
	}

}
