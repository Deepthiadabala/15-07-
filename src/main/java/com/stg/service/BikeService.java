package com.stg.service;

import java.util.List;


import com.stg.model.BikeModel;
import com.stg.model.User;

public interface BikeService {
	public abstract BikeModel createBike(BikeModel bikeModel);

	public abstract List<BikeModel> readBikeByName(String bikeName);

	 public abstract BikeModel updateBikeById(int modelNo, BikeModel BikeModel);

	public abstract String deleteBikeByName(String bikeName);

	public abstract String deleteByBikeId(int bikeId, BikeModel bikeModel);

	public abstract BikeModel getBikeByModelNo(int modelNo);

	public abstract List<User> getUsersByModelNo(int modelNo);

	
}
