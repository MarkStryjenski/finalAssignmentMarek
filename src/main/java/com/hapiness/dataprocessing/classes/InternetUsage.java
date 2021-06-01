package com.hapiness.dataprocessing.classes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Document(collection = "InternetUsage")
@XmlRootElement
public class InternetUsage {

    @Id
    private String id;

//    @JacksonXmlProperty(isAttribute = true)
    @NotNull
    private String countryOrArea;

    @NotNull
    private String internetUsers;

    @NotNull
    private String population;

    @NotNull
    private String worldRank;

    @NotNull
    private String percentage;

    @NotNull
    private String percentageRank;

    public InternetUsage(){

    }

    public InternetUsage(String countryOrArea, String internetUsers,String population, String worldRank, String percentage, String percentageRank) {
        this.countryOrArea = countryOrArea;
        this.internetUsers = internetUsers;
        this.population = population;
        this.worldRank = worldRank;
        this.percentage = percentage;
        this.percentageRank = percentageRank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryOrArea() {
        return countryOrArea;
    }

    public void setCountryOrArea(String countryOrArea) {
        this.countryOrArea = countryOrArea;
    }

    public String getInternetUsers() {
        return internetUsers;
    }

    public void setInternetUsers(String internetUsers) {
        this.internetUsers = internetUsers;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getWorldRank() {
        return worldRank;
    }

    public void setWorldRank(String worldRank) {
        this.worldRank = worldRank;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getPercentageRank() {
        return percentageRank;
    }

    public void setPercentageRank(String percentageRank) {
        this.percentageRank = percentageRank;
    }

    @Override
    public String toString() {
        return "InternetUsage{" +
                "id='" + id + '\'' +
                ", countryOrArea='" + countryOrArea + '\'' +
                ", internetUsers=" + internetUsers +
                ", population=" + population +
                ", worldRank=" + worldRank +
                ", percentage='" + percentage + '\'' +
                ", percentageRank=" + percentageRank +
                '}';
    }
}
//    private String id;
//
//    private String countryOrArea;
//
//    private int internetUsers;
//
//    private int population;
//
//    private int worldRank;
//
//    private String percentage;
//
//    private int percentageRank;