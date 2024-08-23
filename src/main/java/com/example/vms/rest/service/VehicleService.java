package com.example.vms.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.vms.rest.exceptions.VehicleNotFoundByIdException;
import com.example.vms.rest.mapper.VehicleMapper;
import com.example.vms.rest.model.Vehicle;
import com.example.vms.rest.repository.VehicleRepo;
import com.example.vms.rest.requestdto.VehicleRequest;
import com.example.vms.rest.responsedto.VehicleResponse;

@Service
public class VehicleService {
	private  VehicleRepo vehicleRepo;
	private VehicleMapper mapper;

	public VehicleService(VehicleRepo vehicleRepo,VehicleMapper mapper) {
		super();
		this.vehicleRepo = vehicleRepo;
		this.mapper=mapper;
	}

	public VehicleResponse saveVehicle(VehicleRequest vehicleRequest) {
		Vehicle vehicle = mapper.mapToVehicleEntity(vehicleRequest, new Vehicle());
		vehicleRepo.save(vehicle);
		return mapper.mapToVehicleResponse(vehicle);
	}

	public VehicleResponse findVehicleById(int regNo) {
		Optional<Vehicle> optional = vehicleRepo.findById(regNo);
		if(optional.isPresent())
			return mapper.mapToVehicleResponse(optional.get());
		else
			throw new VehicleNotFoundByIdException("vehicle not found ");
	}

	public List<VehicleResponse> findAll() {
		List<Vehicle> list = vehicleRepo.findAll();
		return mapper.mapToVehicleResponse(list);

	}

	public VehicleResponse updateVehicle(VehicleRequest vehicleRequest, int regNo) {
		Optional<Vehicle> optional = vehicleRepo.findById(regNo);
		if (optional.isPresent()) {
			Vehicle vehicle = mapper.mapToVehicleEntity(vehicleRequest, optional.get());
			vehicle= vehicleRepo.save(vehicle);
			return mapper.mapToVehicleResponse(vehicle);
		}
		else
			throw new VehicleNotFoundByIdException("update failed");
	}

	public VehicleResponse deleteVehicle(int regNo) {
		Optional<Vehicle> optional = vehicleRepo.findById(regNo);

		if(optional.isPresent()) {
			Vehicle vehicle = optional.get();
			vehicleRepo.delete(vehicle);
			return mapper.mapToVehicleResponse(vehicle);
		}
		return null;
	}

	public VehicleResponse findByModelName(String modelName) {
		Optional<Vehicle> optional = vehicleRepo.findByModelName(modelName);
		if (optional.isPresent())
			return mapper.mapToVehicleResponse(optional.get());
		else
			throw new VehicleNotFoundByIdException("vehicle not found ");
	}

	public VehicleResponse findByVehicleType(String vehicleType) {
		Optional<Vehicle> optional = vehicleRepo.findByVehicleType(vehicleType);
		if (optional.isPresent()) 
			return	mapper.mapToVehicleResponse(optional.get());
		else
			throw new VehicleNotFoundByIdException("vehicle not found ");
	}

	public VehicleResponse findByColor(String color) {
		Optional<Vehicle> optional = vehicleRepo.findByColor(color);
		if (optional.isPresent()) 
			return	mapper.mapToVehicleResponse(optional.get());
		else
			throw new VehicleNotFoundByIdException("vehicle not found ");
	}

	public VehicleResponse findByModelNameAndVehicleTypeAndColor(String modelName,String vehicleType,String color) {
		Optional<Vehicle> optional = vehicleRepo.findByModelNameAndVehicleTypeAndColor(modelName, vehicleType, color);
		if(optional.isPresent())
			return mapper.mapToVehicleResponse(optional.get());
		else
			throw new VehicleNotFoundByIdException("vehicle not found ");
	}
}


