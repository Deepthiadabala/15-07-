package com.stg.service;

import java.util.List;

import com.stg.exception.ResourceNotFoundException;
import com.stg.model.Admin;


public interface AdminService {
	public abstract Admin create(Admin admin);

	public abstract Admin getAdminById(int AdminId) throws ResourceNotFoundException;
    public abstract Admin updateAdminById(int adminId, Admin admin);
	public abstract String deleteAdminById(int AdminId) throws ResourceNotFoundException;
	public abstract List<Object> readAllBookingsById(int userId);


}
