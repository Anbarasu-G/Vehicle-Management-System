package com.example.vms.rest.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.vms.rest.model.Vehicle;
import com.example.vms.rest.requestdto.VehicleRequest;
import com.example.vms.rest.responsedto.VehicleResponse;

@Component
public class VehicleMapper {

	public Vehicle mapToVehicleEntity(VehicleRequest vehicleRequest,Vehicle vehicle) {

		vehicle.setVehicleType(vehicleRequest.getVehicleType());
		vehicle.setModelName(vehicleRequest.getModelName());
		vehicle.setColor(vehicleRequest.getColor());

		return vehicle;
	}
	
	public VehicleResponse mapToVehicleResponse(Vehicle vehicle) {
		VehicleResponse response=new VehicleResponse();

		response.setRegNo(vehicle.getRegNo());
		response.setVehicleType(vehicle.getVehicleType());
		response.setModelName(vehicle.getModelName());
		response.setColor(vehicle.getColor());

		return response;
	}

	public List<VehicleResponse> mapToVehicleResponse(List<Vehicle> vehicles) {
		
		List<VehicleResponse> list=new ArrayList<VehicleResponse>();	
		for (Vehicle vehicle : vehicles) {
			VehicleResponse response=new VehicleResponse();

			response.setRegNo(vehicle.getRegNo());
			response.setVehicleType(vehicle.getVehicleType());
			response.setModelName(vehicle.getModelName());
			response.setColor(vehicle.getColor());
           
			list.add(response);	
		}
		return list;
	}
}
