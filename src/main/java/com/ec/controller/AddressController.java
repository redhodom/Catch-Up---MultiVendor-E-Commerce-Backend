package com.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.entity.AddressEntity;
import com.ec.services.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService service;
	
	@PostMapping
	public AddressEntity addAddress(@RequestBody AddressEntity address) {
		return service.addAddress(address);
	}
	
	@GetMapping("/user/{userId}")
	public List<AddressEntity> getAddressByUser(@PathVariable Long userId){
		return service.getAddressByUser(userId);
	}
	
	@PutMapping("/{id}")
	public AddressEntity updateAddress(@PathVariable Long id, @RequestBody AddressEntity adress) {
		return service.updateAddress(id, adress);
	}
	
	 @DeleteMapping("/{id}")
	    public String deleteAddress(@PathVariable Long id) {
	        return service.deleteAddress(id);
	    }
}
