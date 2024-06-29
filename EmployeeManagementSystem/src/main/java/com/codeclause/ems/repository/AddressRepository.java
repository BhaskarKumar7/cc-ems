package com.codeclause.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeclause.ems.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
