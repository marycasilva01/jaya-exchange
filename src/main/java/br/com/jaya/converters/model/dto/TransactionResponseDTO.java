package br.com.jaya.converters.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponseDTO {
	   @ApiModelProperty(value = "Código da transação")
		private Long id;
	   @ApiModelProperty(value = "Código do usuário")
		private Integer idUser;
	   @ApiModelProperty(value = "Moeda Origem")
		private String currencyOrigin;
	   @ApiModelProperty(value = "Valor origem")
		private BigDecimal valueSource;
	   @ApiModelProperty(value = "Moeda destino")
		private String currencyDestination;
	   @ApiModelProperty(value = "Valor convertido")
		private BigDecimal valueDestination;
	   @ApiModelProperty(value = "Taxa de conversão")
		private Double rateConversion;
	   @ApiModelProperty(value = "Data/Hora conversão")
		private LocalDateTime dateTimeUTC;
}
