package com.codeclause.ems.service;

import com.codeclause.ems.dto.AddressDto;
import com.codeclause.ems.entity.AddressEntity;

public interface AddressService {
	public AddressEntity convertDtoToEntity(AddressDto addressDto);
	public AddressDto convertEntityToDto(AddressEntity addressEntity);
}
