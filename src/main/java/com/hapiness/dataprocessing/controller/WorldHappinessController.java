package com.hapiness.dataprocessing.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hapiness.dataprocessing.classes.WorldHappiness;
import com.hapiness.dataprocessing.repositories.WorldHappinessRepository;
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
@RequestMapping("/worldHappiness")
public class WorldHappinessController {

    private WorldHappinessRepository worldHappinessRepository;

    public WorldHappinessController(WorldHappinessRepository worldHappinessRepository) {
        this.worldHappinessRepository = worldHappinessRepository;
    }

    /**
     * http://localhost:8080//worldHappiness/all
     * Get method
     */
    @GetMapping("/all")
    public List<WorldHappiness> getAll() {
        List<WorldHappiness> worldHappiness = this.worldHappinessRepository.findAll();

        return worldHappiness;
    }

    /**
     * Make sure countryName starts with big letter. for example country/China
     * http://localhost:8080//worldHappiness/country/countryName
     * Get method 
     */
    @GetMapping("/country/{countryName}")
    public WorldHappiness getCountryBycountryNameName(@PathVariable("countryName") String countryName) {
        WorldHappiness worldHappiness = this.worldHappinessRepository.findCountry(countryName);

        return worldHappiness;
    }

    /**
     * http://localhost:8080//worldHappiness
     * Post method
     * We check submitted json body and validate it against the schema defined for the dataset.
     * If the body is not valid or the country already exists in collection we don't insert data
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> insert(@Valid @RequestBody String requestStr) throws JsonProcessingException, FileNotFoundException {

        File initialFile = new File("C:\\Users\\Marek\\Desktop\\DataProcessing\\finalAssignment\\src\\main\\java\\com\\hapiness\\dataprocessing\\jsonSchema\\worldHappinessSchema.json");
        InputStream schemaAsStream = new FileInputStream(initialFile);
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemaAsStream);


        ObjectMapper om = new ObjectMapper();
//        om.setPropertyNamingStrategy(PropertyNamingStrategy.I)
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
            WorldHappiness worldHappiness = om.readValue(requestStr, WorldHappiness.class);
            String countryName = worldHappiness.getCountryName();
            WorldHappiness existingCountry = this.worldHappinessRepository.findCountry(countryName);
            if (existingCountry == null) {
                this.worldHappinessRepository.insert(worldHappiness);
                return ResponseEntity.ok(worldHappiness.getCountryName() + ": Inserted successfully");
            }
            return new ResponseEntity<>("Country Already exists in collection", HttpStatus.BAD_REQUEST);
        }
//            throw new RuntimeException("Please fix json " + errorsCombined);


    }

    /**
     * http://localhost:8080//worldHappiness
     * Put method
     * We check submitted json body and validate it against the schema defined for the dataset.
     * If the body is not valid or the country does not exists in collection we don't update data.
     */
    @PutMapping
    public ResponseEntity update(@RequestBody String requestStr) throws FileNotFoundException, JsonProcessingException {
        File initialFile = new File("C:\\Users\\Marek\\Desktop\\DataProcessing\\finalAssignment\\src\\main\\java\\com\\hapiness\\dataprocessing\\jsonSchema\\worldHappinessSchema.json");
        InputStream schemaAsStream = new FileInputStream(initialFile);
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemaAsStream);


        ObjectMapper om = new ObjectMapper();
//        om.setPropertyNamingStrategy(PropertyNamingStrategy.I)
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
            WorldHappiness worldHappiness = om.readValue(requestStr, WorldHappiness.class);
            String countryName = worldHappiness.getCountryName();
            WorldHappiness existingCountry = this.worldHappinessRepository.findCountry(countryName);
            if (existingCountry != null) {
                this.worldHappinessRepository.delete(existingCountry);
                this.worldHappinessRepository.save(worldHappiness);
                return ResponseEntity.ok(worldHappiness.getCountryName() + ": Updated successfully");
            } else {
                return new ResponseEntity<>("Country " + countryName + " does not exist in collection. You must insert missing country first", HttpStatus.BAD_REQUEST);
            }

        }

    }

    /**
     * Make sure countryName starts with big letter. for example worldHappiness.xml/China
     * http://localhost:8080//worldHappiness/countryName
     * Delete Method
     */
    @DeleteMapping("/{countryName}")
    public ResponseEntity delete(@PathVariable("countryName") String countryName) {
        WorldHappiness worldHappiness = this.worldHappinessRepository.findCountry(countryName);

        this.worldHappinessRepository.delete(worldHappiness);
        return ResponseEntity.ok("Delete successful");
    }
}
