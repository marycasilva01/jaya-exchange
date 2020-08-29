package br.com.jaya.converters.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

import br.com.jaya.converters.model.dto.TransactionResponseDTO;
import br.com.jaya.converters.model.request.TransactionRequestDTO;
import br.com.jaya.converters.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
//@Api(value = PROPOSAL_DATA_REQUEST_MAPPING, produces = APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(path = "/jaya/v1/transation")
public class TransactionController {
	
	private static final Logger LOG = Logger.getLogger(TransactionController.class.getName());
	
	
	@Autowired
	private TransactionService transationService;
	
	@ApiOperation(value = "Buscar a lista de transações do usuário")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de transações do usuário"),
		    @ApiResponse(code = 404, message = "Não foram encontradas transações para o usuário"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@GetMapping
	@RequestMapping(value = "/{idUser}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<TransactionResponseDTO>> findTransationByUser(@NotNull @PathVariable("idUser") Integer idUser) {
		
		
		
        List<TransactionResponseDTO> transactions = transationService.findTransactiondByIdUser(idUser);

        if (transactions.isEmpty()) {
        	LOG.info("Não foram encontradas transações para o usuário: " + idUser );
        	return ResponseEntity.notFound().build() ;
        } 
       
        return ResponseEntity.ok(transactions);
    }
	
	@ApiOperation(value = "Realiza a conversão de moeda e persiste a transação")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a transação salva"),
		    @ApiResponse(code = 422, message = "Não foi encontrada a moeda para conversão"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	
	@PostMapping
	@RequestMapping(method = RequestMethod.POST, produces="application/json", consumes="application/json" )
    public ResponseEntity<TransactionResponseDTO> saveTransation(@Valid @RequestBody  TransactionRequestDTO request)  {
		LOG.info("Request recebido para salvar transação " + request.toString());
		TransactionResponseDTO transaction = transationService.saveTransaction(request);

         return ResponseEntity.ok(transaction);        
     }

}
