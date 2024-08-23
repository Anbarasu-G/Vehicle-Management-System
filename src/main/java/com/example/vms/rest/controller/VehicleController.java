package com.example.vms.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vms.rest.exceptions.VehicleNotFoundByIdException;
import com.example.vms.rest.requestdto.VehicleRequest;
import com.example.vms.rest.responsedto.VehicleResponse;
import com.example.vms.rest.service.VehicleService;
import com.example.vms.rest.util.AppResponseBuilder;
import com.example.vms.rest.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class VehicleController {

	private VehicleService vehicleService;
	private AppResponseBuilder builder;

	public VehicleController(VehicleService vehicleService,AppResponseBuilder builder) {
		super();
		this.vehicleService = vehicleService;
		this.builder=builder;
	}

	@Operation(description = "This endpoint is used to save the vehicle details in the database",
			responses = {
					@ApiResponse(responseCode = "202", description = "Vehicle Created"),
					@ApiResponse(responseCode = "500", description = "Internal Server Error",
					content = {
							@Content(schema = @Schema (anyOf = RuntimeException.class))
					})
			})
	
	@PostMapping("/vehicles")
	public ResponseEntity<ResponseStructure<VehicleResponse>> saveVehicle(@RequestBody VehicleRequest vehicleRequest) {	
		VehicleResponse response = vehicleService.saveVehicle(vehicleRequest);
		return builder.success(HttpStatus.CREATED, "vehicle created", response);	
	}

	@Operation(description = "This endpoint is used to retrieve the Vehicle details int the database based on their id's",
			responses = {
					@ApiResponse(responseCode = "302", description = "Vehicle Found"),
					@ApiResponse(responseCode = "404", description = "Vehicle Not Found Based on their Id",
					content = {
							@Content(schema = @Schema(anyOf = VehicleNotFoundByIdException.class))
					})
			})
	
	@GetMapping("/vehicles/{regNo}")
	public ResponseEntity<ResponseStructure<VehicleResponse>> findById(@PathVariable("regNo") int regNo){
		VehicleResponse response = vehicleService.findVehicleById(regNo);
		return builder.success(HttpStatus.FOUND, "vehicle found", response);
	}

	@Operation(description = "This endpoint is used to update the vehicle details based in the database based on their Id's",
			responses = {
					@ApiResponse(responseCode = "202", description = "Vehicle updated"),
					@ApiResponse(responseCode = "404", description = "Vehicle Not Found",
					content = {
							@Content(schema = @Schema(anyOf = VehicleNotFoundByIdException.class))
					})
			})
	
	@PutMapping("/vehicles/{regNo}")
	public ResponseEntity<ResponseStructure<VehicleResponse>> updateVehicle(@RequestBody VehicleRequest vehicleRequest,@PathVariable("regNo") int regNo){
		VehicleResponse response=vehicleService.updateVehicle(vehicleRequest, regNo);
		return builder.success(HttpStatus.OK, "vehicle updated", response);
	}

	@Operation(description = "This endpoint is used to retrieve all the vehicles details in the database",
			responses = {
					@ApiResponse(responseCode = "302", description = "Vehicles Found"),
					@ApiResponse(responseCode = "404", description = "Vehicles Not Found",
					content = {
							@Content(schema = @Schema(anyOf = VehicleNotFoundByIdException.class))
					})
			})
	
	@GetMapping("/vehicles")
	public ResponseEntity<ResponseStructure<List<VehicleResponse>>> findAll() {
		List<VehicleResponse> vehicles = vehicleService.findAll();
		return builder.success(HttpStatus.FOUND, "vehicles found", vehicles);
	}

	@Operation(description = "This endpoint is used to delete  the vehicle details in the database",
			responses = {
					@ApiResponse(responseCode = "202", description = "Vehicles Deleted"),
					@ApiResponse(responseCode = "404", description = "Vehicles Not Found",
					content = {
							@Content(schema = @Schema(anyOf = VehicleNotFoundByIdException.class))
					})
			})
	
	@DeleteMapping("/vehicles/{regNo}")
	public ResponseEntity<ResponseStructure<VehicleResponse>> deleteVehicle(@PathVariable("regNo") int regNo){
		VehicleResponse response= vehicleService.deleteVehicle(regNo);
		return builder.success(HttpStatus.OK, "vehile deleted", response);
	}
	
	/**
	 * @GetMapping("/vehicles/{modelName}") public
	 * ResponseEntity<ResponseStructure<VehicleResponse>>
	 * findByModelName(@PathVariable("modelName") String modelName){ VehicleResponse
	 * response = vehicleService.findByModelName(modelName); return
	 * builder.success(HttpStatus.FOUND, "vehicles found", response); }
	 * 
	 * @GetMapping("/vehicles/{vehicleType}") public
	 * ResponseEntity<ResponseStructure<VehicleResponse>>
	 * findByVehicleType(@PathVariable("vehicleType") String vehicleType){
	 * VehicleResponse response = vehicleService.findByVehicleType(vehicleType);
	 * return builder.success(HttpStatus.FOUND, "vehicle found", response); }
	 * 
	 * @GetMapping("/vehicles/{color}") public
	 * ResponseEntity<ResponseStructure<VehicleResponse>>
	 * findByColor(@PathVariable("color") String color){ VehicleResponse response =
	 * vehicleService.findByVehicleType(color); return
	 * builder.success(HttpStatus.FOUND, "vehicle found", response); }
	 */
	
	@Operation(description = "This endpoint is used to retrieve the Vehicle details based on the conditions, "
			+ "which are model name, vehicle type, color of the vehicle",
			responses = {
					@ApiResponse(responseCode = "302" , description ="Vehicle Found"),
					@ApiResponse(responseCode = "404", description = "Vehicle Not Found",
					content = {
							@Content(schema = @Schema(anyOf = VehicleNotFoundByIdException.class))
					})
			})
	
	@GetMapping("/vehicles/{modelName}/{vehicleType}/{color}")
	public ResponseEntity<ResponseStructure<VehicleResponse>> findByModelNameAndVehicleTypeAndColor(
	        		 @PathVariable("modelName") String modelName,
	                 @PathVariable("vehicleType")String vehicleType,
	                 @PathVariable("color")String color) {
		
		VehicleResponse response = vehicleService.findByModelNameAndVehicleTypeAndColor(modelName, vehicleType, color);
		return builder.success(HttpStatus.FOUND, "vehicle found", response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
