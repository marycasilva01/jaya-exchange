package br.com.jaya.converters.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {

	@NotNull
	@ApiModelProperty(value = "Id do usuário")
	private Integer idUser;
	@NotBlank
	@ApiModelProperty(value = "Moeda Origem")
	private String currencyOrigin;
	@NotNull
	@ApiModelProperty(value = "Valor Origem")
	private BigDecimal valueOrigin;
	@NotBlank
	@ApiModelProperty(value = "Moeda destino")
	private String currencyDestination;

}
