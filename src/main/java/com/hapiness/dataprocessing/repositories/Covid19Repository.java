package com.hapiness.dataprocessing.repositories;

import com.hapiness.dataprocessing.classes.Covid19;
import com.hapiness.dataprocessing.classes.InternetUsage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Covid19Repository extends MongoRepository<Covid19,String> {
    @Query("{'country': ?0}")
    Covid19 findCountry(String country);
}
