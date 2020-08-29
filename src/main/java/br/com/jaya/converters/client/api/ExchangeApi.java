package br.com.jaya.converters.client.api;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeApi {
	
	private RatesApi rates;
	private String base;
	private Date date;
	
}
