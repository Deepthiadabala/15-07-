package com.stg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.exception.ResourceNotFoundException;
import com.stg.model.BikeModel;
import com.stg.model.User;
import com.stg.repository.BikeModelRepository;

@Service
public class BikeServiceImpl implements BikeService {
	@Autowired
	private BikeModelRepository bikeModelRepository;

	@Override
	public BikeModel createBike(BikeModel bikeModel) {
		BikeModel bikeModel1 = bikeModelRepository.save(bikeModel);
		return bikeModel1;
	}

	@Override
	public List<BikeModel> readBikeByName(String bikeName) {
		List<BikeModel> bikeModels = bikeModelRepository.findByBikeName(bikeName);
		return bikeModels;
	}

	@Override
	public BikeModel updateBikeById(int modelNo, BikeModel bikeModel) {
		Optional<BikeModel> optional = bikeModelRepository.findById(modelNo);
		if (optional.isPresent()) {
			return bikeModelRepository.save(bikeModel);

		} else {
			throw new ResourceNotFoundException("That admin is not Found");
		}

	}

	@Override
	public String deleteBikeByName(String bikeName) throws ResourceNotFoundException {
		List<Integer> bikeIds = bikeModelRepository.findByNameReturnBikeId(bikeName);
		String message = null;
		if (bikeIds.size() != 0) {
			for (Integer integer : bikeIds) {
				bikeModelRepository.deleteById(integer);
			}
			message = "Deleted Successfull";
		} else {
			throw new ResourceNotFoundException("Name is not found");
		}
		return message;
	}

	@Override
	public String deleteByBikeId(int bikeId, BikeModel bikeModel) {
		if (bikeModel.getModelNo() == bikeId) {
			bikeModelRepository.deleteById(bikeId);
			return "deleted";
		} else {
			throw new ResourceNotFoundException("Id is not found");
		}
	}

	@Override
	public BikeModel getBikeByModelNo(int modelNo) {
		BikeModel bike = bikeModelRepository.findById(modelNo).get();
		return bike;
	}

	@Override
	public List<User> getUsersByModelNo(int modelNo) {

		BikeModel bikeModel = bikeModelRepository.findById(modelNo).get();
		if (bikeModel == null) {
			throw new ResourceNotFoundException("Bike model not available.");
		}
		return bikeModel.getUsers();
	}

}
