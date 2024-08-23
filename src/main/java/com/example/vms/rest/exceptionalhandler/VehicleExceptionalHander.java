package com.example.vms.rest.exceptionalhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.vms.rest.exceptions.VehicleNotFoundByIdException;
import com.example.vms.rest.util.ErrorStructure;
import com.example.vms.rest.util.AppResponseBuilder;

@RestControllerAdvice
public class VehicleExceptionalHander {
	private AppResponseBuilder builder;

	public VehicleExceptionalHander(AppResponseBuilder builder) {
		super();
		this.builder = builder;
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> handleVehicleNotFoundById(VehicleNotFoundByIdException ex){
		return builder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "vehicle not found by the given ID");
	}

}
