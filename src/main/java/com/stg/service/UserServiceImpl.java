package com.stg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stg.exception.ResourceNotFoundException;
import com.stg.model.BikeModel;
import com.stg.model.ResponseSuccess;
import com.stg.model.User;
import com.stg.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BikeService bikeService;

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(int userId) {
		return userRepository.findByUserId(userId);
	}

	@Override
	public User updateUserById(User user, int userId) {
		java.util.Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			return userRepository.save(user);
		} else {
			throw new ResourceNotFoundException("That User is not Found");
		}
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public List<User> getUserByName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public ResponseEntity<ResponseSuccess> bookBike(int userId, int modelNo) {
		try {
			User user = userRepository.findByUserId(userId);
			BikeModel bikeModel = bikeService.getBikeByModelNo(modelNo);
			if (bikeModel == null) {
				throw new ResourceNotFoundException("Bike model not available.");
			}
			user.getBikeModels().add(bikeModel);
			userRepository.save(user);
			ResponseSuccess responseSuccess = new ResponseSuccess("Bike boooked successfully.");
			return new ResponseEntity<ResponseSuccess>(responseSuccess, HttpStatus.OK);

		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

	}

	@Override
	public String deleteUserBooking(int userId, int modelNo) throws ResourceNotFoundException {
		try {
			User user = userRepository.findByUserId(userId);
			BikeModel bikeModel = bikeService.getBikeByModelNo(modelNo);
			
			Object user1 = userRepository.findUserBookedBike(userId, modelNo);
			
			if ((user.getUserId() == userId) && (bikeModel.getModelNo() == modelNo)) {
				if (((User) user1).getUserId() == userId) {
					userRepository.deleteUserBookedBike(userId, modelNo);
					return "Booking Deleted Successfully";
				}else {
					throw new ResourceNotFoundException("There is no bookng with userId :"+userId);
				}

			} else {
				throw new ResourceNotFoundException("Invalid Booking Details");
			}
		} catch (Exception e) {
			throw new ResourceNotFoundException("Invalid Booking Details");
		}
	}

}
