package com.stg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stg.model.BikeModel;
@Repository
public interface BikeModelRepository extends JpaRepository<BikeModel, Integer> {
	@Query(value = "SELECT * from bike_model where model_name LIKE %?1%",nativeQuery = true)
	public abstract List<BikeModel> findByBikeName(String bikeName);
	
	@Query(value = "DELETE from bike_model where model_name LIKE %?1%",nativeQuery = true)
	public abstract String deleteByBikeName(String bikeName);
	
	@Query(value = "SELECT model_no from bike_model where model_name LIKE %?1%",nativeQuery = true)
	public abstract List<Integer> findByNameReturnBikeId(String bikeName);

	public abstract Optional<BikeModel> save(Optional<BikeModel> bikeModel);

	
	
}
