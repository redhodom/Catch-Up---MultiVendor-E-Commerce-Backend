package com.ec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.entity.AddressEntity;
import com.ec.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repo;
	
	public AddressEntity addAddress(AddressEntity address) {
		return repo.save(address);
	}
	
	public List<AddressEntity> getAddressByUser(Long userId){
		return repo.findByUserId(userId);
	}
	
	public AddressEntity updateAddress(Long id, AddressEntity address) {
		
		AddressEntity existing = repo.findById(id).orElse(null);
		
		if(existing != null) {
			existing.setFullName(address.getFullName());
			existing.setMobileNumber(address.getMobileNumber());
			existing.setAddressLine(address.getAddressLine());
			existing.setCity(address.getCity());
			existing.setState(address.getState());
			existing.setPincode(address.getPincode());
			existing.setCountry(address.getCountry());
			
			return repo.save(existing);
		}
		
		return null;
	}
	
	public String deleteAddress(Long id) {
		repo.deleteById(id);
		return "Address Deleted";
	}
	
}
