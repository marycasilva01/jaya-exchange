package br.com.jaya.converters.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.jaya.converters.client.ApiClient;
import br.com.jaya.converters.client.api.ExchangeApi;
import br.com.jaya.converters.client.api.RatesApi;
import br.com.jaya.converters.controller.TransactionController;
import br.com.jaya.converters.exception.MoedaException;
import br.com.jaya.converters.model.dto.TransactionResponseDTO;
import br.com.jaya.converters.model.entity.TransactionEntity;
import br.com.jaya.converters.model.request.TransactionRequestDTO;
import br.com.jaya.converters.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ApiClient client;

	private static final Logger LOG = Logger.getLogger(TransactionController.class.getName());
	
	  public List<TransactionResponseDTO> findTransactiondByIdUser(Integer idUser){
	  
		  LOG.info("Buscando transações do usuário: " + idUser );
		  List<TransactionEntity> transactions = transactionRepository.findByIdUser(idUser);

		  if(!transactions.isEmpty()) { 
			  return transactions.stream().map(transation -> createResponse(transation)).collect(Collectors.toList()); 
		  }

		  return new ArrayList<TransactionResponseDTO>(); 
	  }
	 

	private TransactionResponseDTO createResponse(TransactionEntity transation) {
		return TransactionResponseDTO.builder()
				.id(transation.getId())
				.currencyOrigin(transation.getCurrencyOrigin())
				.currencyDestination(transation.getCurrencyDestination())
				.dateTimeUTC(transation.getDateTimeUTC())
				.idUser(transation.getIdUser())
				.rateConversion(transation.getRateConversion())
				.valueDestination(transation.getValueDestination())
				.valueSource(transation.getValueSource())
				.build();
	}


	public TransactionResponseDTO saveTransaction(TransactionRequestDTO request) {

		LOG.info("Chamando api exchange com moeda " + request.getCurrencyOrigin() );
		ExchangeApi result = client.callURL(request.getCurrencyOrigin());

		if (result != null && result.getRates() != null) {

			try {
				Field fieldRates = RatesApi.class.getDeclaredField(request.getCurrencyDestination().toLowerCase());
					
				fieldRates.setAccessible(true);

				Double rate = null;
				rate = (Double) fieldRates.get(result.getRates());
				LOG.info("Realizando Conversão " );
				
				BigDecimal valueDestination = request.getValueOrigin().multiply(new BigDecimal(rate));
				TransactionEntity transation = transactionRepository.save(createEntity(request, rate, valueDestination));
				
				LOG.info("Transação salva id: "+ transation.getId() );
				
				return createResponse(transation);
			} catch (NoSuchFieldException e) {
				LOG.severe("Moeda não encontrada para conversão: " + request.getCurrencyDestination());
				throw new MoedaException(HttpStatus.UNPROCESSABLE_ENTITY, "Moeda destino não encontrada para conversão!");
			} catch (Exception e) {
				LOG.severe("Ocorreu um erro no request: " + request.toString());
			}

		}
		return null;

	}

	private TransactionEntity createEntity(TransactionRequestDTO request, Double rate, BigDecimal valueDestination) {
		return TransactionEntity.builder().currencyOrigin(request.getCurrencyOrigin())
				.dateTimeUTC(LocalDateTime.now(ZoneId.of("UTC"))).idUser(request.getIdUser())
				.currencyDestination(request.getCurrencyDestination()).rateConversion(rate)
				.valueSource(request.getValueOrigin()).valueDestination(valueDestination).build();
	}

}
