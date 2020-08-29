package br.com.jaya.converters.client.api;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatesApi {
	@JsonProperty("CAD")
	private Double cad;
	@JsonProperty("HKD")
	private Double hkd;
	@JsonProperty("ISK")
	private Double isk;
	@JsonProperty("PHP")
	private Double php;
	@JsonProperty("DKK")
	private Double dkk;
	@JsonProperty("HUF")
	private Double huf;
	@JsonProperty("CZK")
	private Double czk;
	@JsonProperty("GBP")
	private Double gbp;
	@JsonProperty("RON")
	private Double ron;
	@JsonProperty("SEK")
	private Double sek;
	@JsonProperty("IDR")
	private Double idr;
	@JsonProperty("INR")
	private Double inr;
	@JsonProperty("BRL")
	private Double brl;
	@JsonProperty("RUB")
	private Double rub;
	@JsonProperty("HRK")
	private Double hrk;
	@JsonProperty("JPY")
	private Double jpy;
	@JsonProperty("THB")
	private Double thb;
	@JsonProperty("CHF")
	private Double chf;
	@JsonProperty("EUR")
	private Double eur;
	@JsonProperty("MYR")
	private Double myr;
	@JsonProperty("BGN")
	private Double bgn;
	
	@JsonProperty("CNY")
	private Double cny;
	@JsonProperty("NOK")
	private Double nok;
	@JsonProperty("NZD")
	private Double nzd;
	@JsonProperty("ZAR")
	private Double zar;
	@JsonProperty("USD")
	private Double usd;
	@JsonProperty("MXN")
	private Double mxn;
	@JsonProperty("SGD")
	private Double sgd;
	@JsonProperty("AUD")
	private Double aud;
	@JsonProperty("ILS")
	private Double ils;
	@JsonProperty("KRW")
	private Double krw;
	@JsonProperty("PLN")
	private Double pln;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
  
}
