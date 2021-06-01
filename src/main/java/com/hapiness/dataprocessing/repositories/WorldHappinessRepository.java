package com.hapiness.dataprocessing.repositories;

import com.hapiness.dataprocessing.classes.InternetUsage;
import com.hapiness.dataprocessing.classes.WorldHappiness;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface WorldHappinessRepository extends MongoRepository<WorldHappiness,String> {
    @Query("{'countryName': ?0}")
    WorldHappiness findCountry(String countryName);
}
