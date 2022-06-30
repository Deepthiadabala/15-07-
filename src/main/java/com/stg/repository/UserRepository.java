package com.stg.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stg.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public abstract User findByUserId(int userId);
	@Query(value = "SELECT * from user where user_name LIKE %?1%",nativeQuery = true)
	public abstract List<User> findByUserName(String userName);
	
	@Transactional
	@Modifying
	@Query(value = "delete from users_bikes  where user_id=:userId and model_no=:modelNo", nativeQuery = true)
	public abstract  Object deleteUserBookedBike(@Param("userId") int userId,@Param("modelNo") int modelNo);
	
	@Query(value = "select * from users_bikes where user_id = :userId and model_no =:modelNo ",nativeQuery = true)
	public abstract User findUserBookedBike(@Param("userId") int userId,@Param("modelNo") int modelNo);
}
