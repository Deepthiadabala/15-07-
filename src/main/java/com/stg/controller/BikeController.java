package com.stg.controller;

import java.util.List;
import java.util.Optional;

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

import com.stg.model.BikeModel;
import com.stg.model.User;
import com.stg.service.BikeService;

@RestController
@RequestMapping(value = "bike")
public class BikeController {
	@Autowired
	private BikeService bikeService;

	// creating post mapping that post the bike details in the database
	@PostMapping("/createBike")
	public ResponseEntity<BikeModel> createBike(@RequestBody BikeModel bikeModel) {
		BikeModel bikeModel2 = bikeService.createBike(bikeModel);
		return new ResponseEntity<BikeModel>(bikeModel2, HttpStatus.CREATED);
	}

	@GetMapping("/readBikeById/{bikeId}")
	public ResponseEntity<BikeModel> readBikeById(@PathVariable int bikeId) {
		BikeModel bikeModels = bikeService.getBikeByModelNo(bikeId);
		return new ResponseEntity<BikeModel>(bikeModels, HttpStatus.CREATED);
	}

	// Reading mapping that get the bike details in the database
	@GetMapping("/readBikeByName/{bikeName}")
	public ResponseEntity<List<BikeModel>> readBikeByName(@PathVariable String bikeName) {
		List<BikeModel> bikeModels = bikeService.readBikeByName(bikeName);
		return new ResponseEntity<List<BikeModel>>(bikeModels, HttpStatus.CREATED);
	}

	// updating put mapping that post the bike details in the database
	@PutMapping("/updateBike/{modelNo}")
	public ResponseEntity<BikeModel> updateBikeById(@PathVariable int modelNo, @RequestBody BikeModel bikeModel) {
		BikeModel bikeModel2 = bikeService.updateBikeById(modelNo, bikeModel);
		return new ResponseEntity<BikeModel>(bikeModel2, HttpStatus.CREATED);
	}

	// deleting delete mapping that delete the bike model in the database by Id
	@DeleteMapping("/deleteBikeById/{id}")
	public ResponseEntity<String> deleteBikeById(@PathVariable int id, BikeModel bikeModel) {
		String message = bikeService.deleteByBikeId(id, bikeModel);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@GetMapping(value = "get-users/{modelNo}")
	public List<User> getUsersByModelNo(@PathVariable int modelNo) {
		return bikeService.getUsersByModelNo(modelNo);
	}

}
