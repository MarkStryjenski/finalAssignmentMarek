package com.hapiness.dataprocessing.repositories;

import com.hapiness.dataprocessing.classes.InternetUsage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InternetUsageRepository extends MongoRepository<InternetUsage,String> {
    @Query("{'countryOrArea': ?0}")
    InternetUsage findCountry(String countryOrArea);
}
