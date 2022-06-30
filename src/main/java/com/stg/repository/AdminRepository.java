package com.stg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stg.model.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	public abstract Admin findByAdminId(int adminId);

	@Query(value = "select user.user_name, bike_model.model_name from user inner join users_bikes on user.user_id = users_bikes.user_id join bike_model on users_bikes.model_no = bike_model.model_no where user.user_id = :userId",nativeQuery = true)
	public abstract List<Object> findAllBookings(@Param("userId") int userId);
	
	@Query(value="select * from users_bikes where user_id=users_bikes.user_id",nativeQuery = true)
	public abstract Object readAllBookingsById(@Param("userId") int userId);
}
