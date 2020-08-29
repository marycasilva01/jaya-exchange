package br.com.jaya.converters.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.jaya.converters.model.entity.TransactionEntity;

@ExtendWith (MockitoExtension.class)
class TransactionRepositoryTest {
	
	@Autowired
    private TransactionRepository transactionRepository;
	
	private Integer idUser;
	
	private Long id;
	
	private TransactionEntity transactionEntity;
 
	
	@BeforeClass
    public void setUp() {
		this.id = (Long.parseLong(new Random().nextInt(999999999) + ""));
        this.idUser = (Integer.parseInt(new Random().nextInt(999999999) + ""));
        transactionRepository.save(mockTransation());
    }
	
	@AfterClass
    public void setDown() {
		transactionRepository.delete(transactionEntity);
    }
	    
    @Test
    public void testFindByIdUserIsEmpty() {
    	
    	List<TransactionEntity> listTransaction = transactionRepository.findByIdUser(1);
        Assert.assertTrue(listTransaction.isEmpty());
        
    }
    
    public TransactionEntity mockTransation() {
		transactionEntity = new TransactionEntity();
		
		transactionEntity.setId(id);
		transactionEntity.setIdUser(idUser);
		transactionEntity.setRateConversion(Double.valueOf("10"));
		transactionEntity.setValueDestination(BigDecimal.ONE);
		transactionEntity.setValueSource(BigDecimal.ONE);
		transactionEntity.setCurrencyDestination("BRL");
		
		return transactionEntity;
	}



}
