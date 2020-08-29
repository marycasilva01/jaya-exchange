package br.com.jaya.converters.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import br.com.jaya.converters.client.ApiClient;
import br.com.jaya.converters.client.api.ExchangeApi;
import br.com.jaya.converters.client.api.RatesApi;
import br.com.jaya.converters.exception.MoedaException;
import br.com.jaya.converters.model.dto.TransactionResponseDTO;
import br.com.jaya.converters.model.entity.TransactionEntity;
import br.com.jaya.converters.model.request.TransactionRequestDTO;
import br.com.jaya.converters.repository.TransactionRepository;

@ExtendWith (MockitoExtension.class)
public class TransactionServiceTest {
	
	
	@InjectMocks
	private TransactionService transactionService;
	
	@Mock
    private TransactionRepository transactionRepository;
	
	@Mock
	private ApiClient client;
	
	private TransactionEntity transaction;
	
	@Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
    public void testFindByIdUserNoNull() {
    	
    	
	   List<TransactionEntity> listTransaction = new ArrayList<TransactionEntity>();
	   listTransaction.add(mockTransation());

       when(transactionRepository.findByIdUser(any())).thenReturn(listTransaction);

       List<TransactionResponseDTO> transaction = transactionService.findTransactiondByIdUser(1);
       
       Assert.assertNotNull(transaction);
       
    }
    
    
    @Test
    public void testFindByIdUserIsEmpy() {
    	
    	when(transactionRepository.findByIdUser(any())).thenReturn(new ArrayList<>());
    	
    	List<TransactionResponseDTO> transactionResponseDTO = transactionService.findTransactiondByIdUser(1);
    	
        Assert.assertTrue(transactionResponseDTO.isEmpty());
    }
    
    
    @Test
    public void testClienNullReturn() throws MoedaException, IllegalArgumentException, IllegalAccessException {
    	
    	when(client.callURL(any())).thenReturn(null);
    	
    	TransactionResponseDTO transactionResponseDTO = transactionService.saveTransaction(this.mockTransationRequestDTO());
    	
        assertNull(transactionResponseDTO);
    }
    
    @Test
    public void testExceptionSave() throws MoedaException, IllegalArgumentException, IllegalAccessException {
    	
    	when(client.callURL(any())).thenReturn(this.mockExchangeApi());
    	
    	try {
    		transactionService.saveTransaction(this.mockTransationRequestError());
    	
	    } catch (MoedaException e) {
	        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatus());
	    }
    }
    
    @Test
    public void testException2Save() throws MoedaException, IllegalArgumentException, IllegalAccessException {
    	
    	when(client.callURL(any())).thenReturn(this.mockExchangeApiErro2());
    	
    	try {
    		transactionService.saveTransaction(this.mockTransationRequestDTO());
    	
	    } catch (Exception e) {
	        assertEquals("Ocorreu um erro no request:", e.getMessage());
	    }
    }
    
    
    
    @Test
    public void testSaveService() throws MoedaException, IllegalArgumentException, IllegalAccessException {
    	
    	when(client.callURL(any())).thenReturn(this.mockExchangeApi());
    	
    	when(transactionRepository.save(any())).thenReturn(this.mockTransation());
    	
    	TransactionResponseDTO transaction = transactionService.saveTransaction(this.mockTransationRequestDTO());
    	
        assertNotNull(transaction);
    }
    
    public TransactionEntity mockTransation() {
  		transaction = new TransactionEntity();
  		
  		transaction.setId(1L);
  		transaction.setIdUser(1);
  		transaction.setRateConversion(Double.valueOf("10"));
  		transaction.setValueDestination(BigDecimal.ONE);
  		transaction.setValueSource(BigDecimal.ONE);
  		transaction.setCurrencyDestination("BRL");
  		transaction.setCurrencyOrigin("BRL");
  		
  		return transaction;
  	}
    
    public TransactionResponseDTO mockTransationResponseDTO() {
    	TransactionResponseDTO transaction = new TransactionResponseDTO();
		
		transaction.setId(1L);
		transaction.setIdUser(1);
		transaction.setRateConversion(Double.valueOf("10"));
		transaction.setValueDestination(BigDecimal.ONE);
		transaction.setValueSource(BigDecimal.ONE);
		transaction.setCurrencyDestination("BRL");
		
		return transaction;
	}
    
    public TransactionRequestDTO mockTransationRequestDTO() {
    	TransactionRequestDTO transaction = new TransactionRequestDTO();
		
		transaction.setIdUser(1);
		transaction.setCurrencyDestination("BRL");
		transaction.setCurrencyOrigin("BRL");
		transaction.setValueOrigin(BigDecimal.ONE);
		
		return transaction;
	}
    
    public TransactionRequestDTO mockTransationRequestError() {
    	TransactionRequestDTO transaction = new TransactionRequestDTO();
		
		transaction.setIdUser(1);
		transaction.setCurrencyDestination("CCA");
		transaction.setCurrencyOrigin("BRL");
		
		return transaction;
	}
    
    public ExchangeApi mockExchangeApi() {
    	ExchangeApi exchangeApi = new ExchangeApi();
		
    	exchangeApi.setBase("BRL");
    	exchangeApi.setDate(new Date(0));
    	exchangeApi.setRates(new RatesApi());
		
    	RatesApi rateApi = new RatesApi();
    	rateApi.setBrl(1.3146705065D);
    	exchangeApi.setRates(rateApi);
		return exchangeApi;
	}
    
    public ExchangeApi mockExchangeApiErro2() {
    	ExchangeApi exchangeApi = new ExchangeApi();
		
    	exchangeApi.setBase("AAC");
    	exchangeApi.setDate(new Date(0));
    	exchangeApi.setRates(new RatesApi());
		
    	RatesApi rateApi = new RatesApi();
    	rateApi.setBrl(1.3146705065D);
    	exchangeApi.setRates(rateApi);
		return exchangeApi;
	}
    
    
    
    
    
    
    
    



}
