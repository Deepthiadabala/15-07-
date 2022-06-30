package com.stg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.exception.ResourceNotFoundException;
import com.stg.model.Admin;

import com.stg.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepository;

	public Admin create(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public Admin getAdminById(int adminId) throws ResourceNotFoundException {

		Admin admin = null;
		admin = adminRepository.findByAdminId(adminId);
		if (admin != null) {
			return admin;
		} else {
			throw new ResourceNotFoundException("Admin id is not found:" + adminId);
		}

	}

	@Override
	public Admin updateAdminById(int adminId, Admin admin) {
		Optional<Admin> optional = adminRepository.findById(adminId);
		if (optional.isPresent()) {
			return adminRepository.save(admin);

		} else {
			throw new ResourceNotFoundException("That admin is not Found");
		}

	}

	@Override
	public String deleteAdminById(int adminId) throws ResourceNotFoundException {
		adminRepository.deleteById(adminId);
		return "Deleted successfully";
	}

	@Override
	public List<Object> readAllBookingsById(int userId) {
		 if ((adminRepository.findAllBookings(userId).size() > 0 )) {
			 return adminRepository.findAllBookings(userId);
		 }
		 else {
			 throw new ResourceNotFoundException("There is no Bookings by this user");
		 }
}
}
