package com.lgsc.kunqu.common.web;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.fastjson.JSON;

@Configuration
@ControllerAdvice
public class ValidatorConfiguration {

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseBody
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String handleResourceNotFoundException(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		StringBuilder strBuilder = new StringBuilder();
		for (ConstraintViolation<?> violation : violations) {
			strBuilder.append(violation.getMessage() + " ");
		}
		if (StringUtils.isBlank(strBuilder.toString())) {
			strBuilder.append(e.toString());
		}
		return JSON.toJSONString(StringUtils.trim(strBuilder.toString()));
	}
	
}
