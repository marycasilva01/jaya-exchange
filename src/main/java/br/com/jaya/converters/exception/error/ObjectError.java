package br.com.jaya.converters.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ObjectError {
	
	private  String message;
    private  String field;
    private  Object parameter;

}
