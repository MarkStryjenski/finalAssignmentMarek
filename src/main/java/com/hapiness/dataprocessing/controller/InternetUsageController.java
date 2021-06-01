package com.hapiness.dataprocessing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.hapiness.dataprocessing.classes.InternetUsage;
import com.hapiness.dataprocessing.repositories.InternetUsageRepository;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import com.sun.xml.internal.ws.api.message.stream.XMLStreamReaderMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.validation.Valid;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/internetUsage")
public class InternetUsageController {
    private InternetUsageRepository internetUsageRepository;

    public InternetUsageController(InternetUsageRepository internetUsageRepository) {
        this.internetUsageRepository = internetUsageRepository;
    }

    /**
     * http://localhost:8080//internetUsage/all
     * Get method
     */
    @GetMapping("/all")
    public List<InternetUsage> getAll() {
        List<InternetUsage> internetUsages = this.internetUsageRepository.findAll();

        return internetUsages;
    }

    /**
     * Make sure countryOrArea starts with big letter. for example countryOrArea/China
     * http://localhost:8080//internetUsage/country/countryOrArea
     * Get method 
     */
    @GetMapping(value="/country/{countryOrArea}")
    public InternetUsage getCountryByCountryOrAreaName(@PathVariable("countryOrArea") String countryOrArea) {
        InternetUsage internetUsage = this.internetUsageRepository.findCountry(countryOrArea);

        return internetUsage;
    }

    /**
     * http://localhost:8080//internetUsage
     * Post method
     * We check submitted json body and validate it against the schema defined for the dataset.
     * If the body is not valid or the country already exists in collection we don't insert data
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> insert(@Valid @RequestBody String requestStr) throws JsonProcessingException, FileNotFoundException {

        File initialFile = new File("C:\\Users\\Marek\\Desktop\\DataProcessing\\finalAssignment\\src\\main\\java\\com\\hapiness\\dataprocessing\\jsonSchema\\internetUsageSchema.json");
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
            InternetUsage internetUsage = om.readValue(requestStr, InternetUsage.class);
            String countryName = internetUsage.getCountryOrArea();
            InternetUsage existingCountry = this.internetUsageRepository.findCountry(countryName);
            if (existingCountry == null) {
                this.internetUsageRepository.insert(internetUsage);
                return ResponseEntity.ok(internetUsage.getCountryOrArea() + ": Inserted successfully");
            }
            return new ResponseEntity<>("Country Already exists in collection", HttpStatus.BAD_REQUEST);
        }
//            throw new RuntimeException("Please fix json " + errorsCombined);


    }

    /**
     * http://localhost:8080//internetUsage
     * Put method
     * We check submitted json body and validate it against the schema defined for the dataset.
     * If the body is not valid or the country does not exists in collection we don't update data.
     */
    @PutMapping
    public ResponseEntity update(@RequestBody String requestStr) throws FileNotFoundException, JsonProcessingException {
        File initialFile = new File("C:\\Users\\Marek\\Desktop\\DataProcessing\\finalAssignment\\src\\main\\java\\com\\hapiness\\dataprocessing\\jsonSchema\\internetUsageSchema.json");
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
            InternetUsage internetUsage = om.readValue(requestStr, InternetUsage.class);
            String countryName = internetUsage.getCountryOrArea();
            InternetUsage existingCountry = this.internetUsageRepository.findCountry(countryName);
            if (existingCountry != null) {
                this.internetUsageRepository.delete(existingCountry);
                this.internetUsageRepository.save(internetUsage);
                return ResponseEntity.ok(internetUsage.getCountryOrArea() + ": Updated successfully");
            } else {
                return new ResponseEntity<>("Country " + countryName + " does not exist in collection. You must insert missing country first", HttpStatus.BAD_REQUEST);
            }

        }

    }

    /**
     * Make sure countryOrArea starts with big letter. for example countryOrArea/China
     * http://localhost:8080//internetUsage/countryOrArea
     * Delete Method
     */
    @DeleteMapping("/{countryOrArea}")
    public ResponseEntity delete(@PathVariable("countryOrArea") String countryOrArea) {
        InternetUsage internetUsage = this.internetUsageRepository.findCountry(countryOrArea);

        this.internetUsageRepository.delete(internetUsage);
        return ResponseEntity.ok("Delete successful");
    }
}
