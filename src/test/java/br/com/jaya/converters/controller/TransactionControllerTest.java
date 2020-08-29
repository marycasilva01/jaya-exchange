package br.com.jaya.converters.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.jaya.converters.model.dto.TransactionResponseDTO;
import br.com.jaya.converters.service.TransactionService;

@ExtendWith (MockitoExtension.class)
public class TransactionControllerTest {
		
	@InjectMocks
    private TransactionController controller;
	
    @Mock
    private TransactionService transactionService;
    
    
    @Test
    public void testFindByIdUser() throws Exception {
    	
        when(transactionService.findTransactiondByIdUser(anyInt())).thenReturn(new ArrayList<TransactionResponseDTO>());
        
        ResponseEntity<List<TransactionResponseDTO>> response = controller.findTransationByUser(1);
        
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
