package com.stg.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.stg.exception.ResourceNotFoundException;
import com.stg.model.ResponseSuccess;
import com.stg.model.User;

public interface UserService {
	public abstract User createUser(User user);

	public abstract User getUser(int userId);

	public abstract User updateUserById(User user, int userId);
	
	public abstract List<User> getAllUser();

	public abstract List<User> getUserByName(String userName);
	
	public abstract ResponseEntity<ResponseSuccess> bookBike(int userId, int modelNo);
	
	public abstract String deleteUserBooking(int userId,int modelNo) throws ResourceNotFoundException;

}
