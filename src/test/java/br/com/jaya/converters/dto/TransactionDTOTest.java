package br.com.jaya.converters.dto;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import br.com.jaya.converters.client.api.ExchangeApi;
import br.com.jaya.converters.client.api.RatesApi;
import br.com.jaya.converters.exception.error.ErrorResponse;
import br.com.jaya.converters.exception.error.ObjectError;
import br.com.jaya.converters.model.dto.TransactionResponseDTO;
import br.com.jaya.converters.model.entity.TransactionEntity;
import br.com.jaya.converters.model.request.TransactionRequestDTO;

public class TransactionDTOTest {

	
	@Test
	public void testeDTO() {
		
		assertThat(ObjectError.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(), hasValidBeanHashCode(), hasValidGettersAndSetters(), hasValidBeanToString()));
		
		assertThat(ErrorResponse.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(), hasValidBeanHashCode(), hasValidGettersAndSetters(), hasValidBeanToString()));
		
		assertThat(TransactionEntity.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(), hasValidBeanHashCode(), hasValidGettersAndSetters(), hasValidBeanToString()));
		
		assertThat(ExchangeApi.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(), hasValidBeanHashCode(), hasValidGettersAndSetters(), hasValidBeanToString()));
		
		assertThat(RatesApi.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(), hasValidBeanHashCode(), hasValidGettersAndSetters(), hasValidBeanToString()));
		
		assertThat(TransactionRequestDTO.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(), hasValidBeanHashCode(), hasValidBeanToString(), hasValidGettersAndSetters()));
		
		assertThat(TransactionResponseDTO.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(), hasValidBeanHashCode(), hasValidBeanToString(), hasValidGettersAndSetters()));
	}
}
