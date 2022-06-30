package com.stg.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Admin", schema = "test_drive_schema")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	@Column(name = "AdminName", length = 30)
	private String adminName;
	@Column(name = "Password", length = 10)
	private String password;
	@Column(name="ContactNo",length = 10)
	private String contactNo;
	@Column(name = "MailId", length = 30)
	private String mailId;
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "admin")
	@JsonManagedReference(value = "admin_bike")
	@JsonIgnore
	private List<BikeModel> bikeModels;
	

}
