package com.example.vms.rest.requestdto;

public class VehicleRequest {
	
    private String vehicleType;
    private String modelName;
    private String color;
    
    public String getVehicleType() {
		return vehicleType;
	}

    public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
    
}
