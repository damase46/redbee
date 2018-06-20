package com.redbee.persistor.repository;

import com.redbee.persistor.customer.HotelRepositoryCustomer;
import com.redbee.persistor.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRepository extends MongoRepository<Hotel, Long>, HotelRepositoryCustomer {}
