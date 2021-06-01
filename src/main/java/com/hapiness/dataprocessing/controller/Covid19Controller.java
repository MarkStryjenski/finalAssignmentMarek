package com.hapiness.dataprocessing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hapiness.dataprocessing.classes.Covid19;
import com.hapiness.dataprocessing.repositories.Covid19Repository;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/covid")
public class Covid19Controller {

    private Covid19Repository covid19Repository;

    public Covid19Controller(Covid19Repository covid19Repository) {
        this.covid19Repository = covid19Repository;
    }

    /**
     * http://localhost:8080//covid/all
     * Get method
     */
    @GetMapping("/all")
    public List<Covid19> getAll() {
        List<Covid19> covid = this.covid19Repository.findAll();

        return covid;
    }

    /**
     * Make sure countryName starts with big letter. for example country/China
     * http://localhost:8080//covid/country/countryName
     * Get method 
     */
    @GetMapping("/country/{country}")
    public Covid19 getCountryBycountryNameName(@PathVariable("country") String country) {
        Covid19 covid19 = this.covid19Repository.findCountry(country);

        return covid19;
    }

    /**
     * http://localhost:8080//covid
     * Post method
     * We check submitted json body and validate it against the schema defined for the dataset.
     * If the body is not valid or the country already exists in collection we don't insert data
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> insert(@Valid @RequestBody String requestStr) throws JsonProcessingException, FileNotFoundException {

        File initialFile = new File("C:\\Users\\Marek\\Desktop\\DataProcessing\\finalAssignment\\src\\main\\java\\com\\hapiness\\dataprocessing\\jsonSchema\\covid19Schema.json");
        InputStream schemaAsStream = new FileInputStream(initialFile);
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemaAsStream);


        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(requestStr);

        Set<ValidationMessage> errors = schema.validate(jsonNode);
        String errorsCombined = "";
        for (ValidationMessage error : errors) {
            System.out.println(error);
            errorsCombined += error.toString() + "\n";
        }
        System.out.println(errorsCombined);
        if (errors.size() > 0) {
            return new ResponseEntity<>(errorsCombined, HttpStatus.BAD_REQUEST);
        } else {
            Covid19 covid = om.readValue(requestStr, Covid19.class);
            String countryName = covid.getCountry();
            Covid19 existingCountry = this.covid19Repository.findCountry(countryName);
            if (existingCountry == null) {
                this.covid19Repository.insert(covid);
                return ResponseEntity.ok(covid.getCountry() + ": Inserted successfully");
            }
            return new ResponseEntity<>("Country Already exists in collection", HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * http://localhost:8080//covid
     * Put method
     * We check submitted json body and validate it against the schema defined for the dataset.
     * If the body is not valid or the country does not exists in collection we don't update data.
     */
    @PutMapping
    public ResponseEntity update(@RequestBody String requestStr) throws FileNotFoundException, JsonProcessingException {
        File initialFile = new File("C:\\Users\\Marek\\Desktop\\DataProcessing\\finalAssignment\\src\\main\\java\\com\\hapiness\\dataprocessing\\jsonSchema\\covid19Schema.json");
        InputStream schemaAsStream = new FileInputStream(initialFile);
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemaAsStream);


        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(requestStr);

        Set<ValidationMessage> errors = schema.validate(jsonNode);
        String errorsCombined = "";
        for (ValidationMessage error : errors) {
            System.out.println(error);
            errorsCombined += error.toString() + "\n";
        }
        System.out.println(errorsCombined);
        if (errors.size() > 0) {
            return new ResponseEntity<>(errorsCombined, HttpStatus.BAD_REQUEST);
        } else {
            Covid19 covid = om.readValue(requestStr, Covid19.class);
            String countryName = covid.getCountry();
            Covid19 existingCountry = this.covid19Repository.findCountry(countryName);
            if (existingCountry != null) {
                this.covid19Repository.delete(existingCountry);
                this.covid19Repository.save(covid);
                return ResponseEntity.ok(covid.getCountry() + ": Updated successfully");
            } else {
                return new ResponseEntity<>("Country " + countryName + " does not exist in collection. You must insert missing country first", HttpStatus.BAD_REQUEST);
            }

        }
    }

    /**
     * Make sure countryName starts with big letter. for example countryName/China
     * http://localhost:8080//covid/countryName
     * Delete Method
     */
    @DeleteMapping("/{countryName}")
    public ResponseEntity delete(@PathVariable("countryName") String countryName) {
        Covid19 covid = this.covid19Repository.findCountry(countryName);

        this.covid19Repository.delete(covid);
        return ResponseEntity.ok("Delete successful");
    }
}
