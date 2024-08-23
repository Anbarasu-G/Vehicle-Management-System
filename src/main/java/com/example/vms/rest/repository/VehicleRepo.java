package com.example.vms.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.vms.rest.model.Vehicle;

public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {

	@Query("FROM Vehicle v WHERE v.modelName=:modelName")
	Optional<Vehicle> findByModelName(String modelName); 

	@Query("FROM Vehicle v WHERE v.vehicleType=:vehicleType")
	Optional<Vehicle> findByVehicleType(String vehicleType); 


	@Query("FROM Vehicle v WHERE v.color=:color")
	Optional<Vehicle> findByColor(String color); 

	@Query("FROM Vehicle v WHERE v.modelName=:modelName AND v.vehicleType=:vehicleType AND v.color=:color")
	Optional<Vehicle> findByModelNameAndVehicleTypeAndColor(String modelName,String vehicleType, String color);

}
