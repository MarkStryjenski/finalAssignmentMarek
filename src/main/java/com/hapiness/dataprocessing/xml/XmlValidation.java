package com.hapiness.dataprocessing.xml;

import com.hapiness.dataprocessing.classes.Covid19;
import com.hapiness.dataprocessing.classes.WorldHappiness;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class XmlValidation {
    public static void main(String[] args) throws Exception {

        //Since all the xml files are correct there will be no error message.
        //If you change for example value of: covid_deaths in covid19.xml to string you will get error that input is invalid
        validateXMLSchema("src\\main\\java\\com\\hapiness\\dataprocessing\\xsd\\internetUsageSchema.xsd","src\\main\\java\\com\\hapiness\\dataprocessing\\xml\\internetUsage.xml");
        validateXMLSchema("src\\main\\java\\com\\hapiness\\dataprocessing\\xsd\\worldHappinessSchema.xsd","src\\main\\java\\com\\hapiness\\dataprocessing\\xml\\worldHappiness.xml");
        validateXMLSchema("src\\main\\java\\com\\hapiness\\dataprocessing\\xsd\\covid19Schema.xsd","src\\main\\java\\com\\hapiness\\dataprocessing\\xml\\covid19.xml");
    }


    /**
     * Used to validate xml file against xsd
     * @param xsdPath
     * @param xmlPath
     * @return true if file is valid
     */
    public static boolean validateXMLSchema(String xsdPath, String xmlPath){

        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }

}
