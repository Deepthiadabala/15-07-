package com.stg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.model.Admin;
import com.stg.model.User;
import com.stg.service.AdminService;
import com.stg.service.UserService;

@RestController
@RequestMapping(value = "admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;


	@PostMapping(value = "create")
	public Admin createAdmin(@RequestBody Admin admin) {
		return adminService.create(admin);
	}

	@GetMapping(value = "get/{adminId}")
	public Admin readAdmin(@PathVariable int adminId) {
		return adminService.getAdminById(adminId);
	}
	
	@GetMapping(value = "getAllUsers")
	public ResponseEntity<List<User>> readAllUsers() {
		List<User> listOfUsers = userService.getAllUser();
		return new ResponseEntity<List<User>>(listOfUsers, HttpStatus.ACCEPTED);
	}


	@DeleteMapping("/delete/{adminId}")
	private String deleteAdmin(@PathVariable("adminId") int adminId) {
		adminService.deleteAdminById(adminId);
		return "Successfully deleted";
	}

	@PutMapping(value = "updateAdmin/{adminId}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable int adminId, @RequestBody Admin admin) {
		Admin admin2 = adminService.updateAdminById(adminId, admin);
		return new ResponseEntity<Admin>(admin2, HttpStatus.OK);

	}
	
	@GetMapping(value="getBookings/{userId}")
	public ResponseEntity<List<Object>> getAllBookings(@PathVariable("userId") int userId){
		List<Object> bookings = adminService.readAllBookingsById(userId);
		return new ResponseEntity<List<Object>>(bookings,HttpStatus.ACCEPTED);
	}
}
