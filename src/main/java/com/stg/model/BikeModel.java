package com.stg.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BikeModel", schema = "test_drive_schema")
public class BikeModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int modelNo;
	@Column(name = "ModelName", length = 30)
	private String modelName;
	@Column(name = "Price", length = 30)
	private int price;
	@Column(name = "EngineCapacity", length = 30)
	private String engineCapacity;
	@Column(name = "Mileage", length = 30)
	private String mileage;
	@Column(name = "Gears", length = 30)
	private String gears;
	@Column(name = "Brakes", length = 30)
	private String brakes;
	@Column(name = "FuelTankCapacity", length = 30)
	private String fuelTankCapacity;
	@Column(name = "WheelType", length = 30)
	private String wheelType;
	@Column(name = "StartingMechanism", length = 30)
	private String startingMechanism;

	public BikeModel(String modelName, int price, String engineCapacity, String mileage, String gears, String brakes,
			String fuelTankCapacity, String wheelType, String startingMechanism) {
		super();
		this.modelName = modelName;
		this.price = price;
		this.engineCapacity = engineCapacity;
		this.mileage = mileage;
		this.gears = gears;
		this.brakes = brakes;
		this.fuelTankCapacity = fuelTankCapacity;
		this.wheelType = wheelType;
		this.startingMechanism = startingMechanism;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adminId", referencedColumnName = "adminId")
	@JsonBackReference(value = "admin_bike")
	private Admin admin;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE } , mappedBy = "bikeModels")
	@JsonIgnore
	private List<User> users;


}
