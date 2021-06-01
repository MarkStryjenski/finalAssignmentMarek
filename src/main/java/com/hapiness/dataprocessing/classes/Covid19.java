package com.hapiness.dataprocessing.classes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Document(collection = "Covid")
@XmlRootElement
public class Covid19 {
    @Id
    private String id;

    @NotNull
    private String country;

    @NotNull
    private String covid_confirmed;

    @NotNull
    private String covid_deaths;

    @NotNull
    private String covid_recovered;

    @NotNull
    private String covid19_first_date;

    @NotNull
    private String flu_death_rate_per_10000;

    @NotNull
    private String h1n1_Cases_underestimate;

    @NotNull
    private String h1n1_Cases_confirmed;

    @NotNull
    private String h1n1_Deaths_confirmed;

    public Covid19(){

    }

    public Covid19(String id, String country, String covid_confirmed, String covid_deaths, String covid_recovered, String covid19_first_date, String flu_death_rate_per_10000, String h1n1_Cases_underestimate, String h1n1_Cases_confirmed, String h1n1_Deaths_confirmed) {
        this.id = id;
        this.country = country;
        this.covid_confirmed = covid_confirmed;
        this.covid_deaths = covid_deaths;
        this.covid_recovered = covid_recovered;
        this.covid19_first_date = covid19_first_date;
        this.flu_death_rate_per_10000 = flu_death_rate_per_10000;
        this.h1n1_Cases_underestimate = h1n1_Cases_underestimate;
        this.h1n1_Cases_confirmed = h1n1_Cases_confirmed;
        this.h1n1_Deaths_confirmed = h1n1_Deaths_confirmed;
    }

    @Override
    public String toString() {
        return "Covid19{" +
                "id='" + id + '\'' +
                ", country='" + country + '\'' +
                ", covid_confirmed='" + covid_confirmed + '\'' +
                ", covid_deaths='" + covid_deaths + '\'' +
                ", covid_recovered='" + covid_recovered + '\'' +
                ", covid19_first_date='" + covid19_first_date + '\'' +
                ", flu_pneumonia_death_rate_per_1='" + flu_death_rate_per_10000 + '\'' +
                ", h1n1_Cases_underestimate='" + h1n1_Cases_underestimate + '\'' +
                ", h1n1_Cases_confirmed='" + h1n1_Cases_confirmed + '\'' +
                ", h1n1_Deaths_confirmed='" + h1n1_Deaths_confirmed + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCovid_confirmed() {
        return covid_confirmed;
    }

    public void setCovid_confirmed(String covid_confirmed) {
        this.covid_confirmed = covid_confirmed;
    }

    public String getCovid_deaths() {
        return covid_deaths;
    }

    public void setCovid_deaths(String covid_deaths) {
        this.covid_deaths = covid_deaths;
    }

    public String getCovid_recovered() {
        return covid_recovered;
    }

    public void setCovid_recovered(String covid_recovered) {
        this.covid_recovered = covid_recovered;
    }

    public String getCovid19_first_date() {
        return covid19_first_date;
    }

    public void setCovid19_first_date(String covid19_first_date) {
        this.covid19_first_date = covid19_first_date;
    }

    public String getFlu_death_rate_per_10000() {
        return flu_death_rate_per_10000;
    }

    public void setFlu_death_rate_per_10000(String flu_death_rate_per_10000) {
        this.flu_death_rate_per_10000 = flu_death_rate_per_10000;
    }

    public String getH1n1_Cases_underestimate() {
        return h1n1_Cases_underestimate;
    }

    public void setH1n1_Cases_underestimate(String h1n1_Cases_underestimate) {
        this.h1n1_Cases_underestimate = h1n1_Cases_underestimate;
    }

    public String getH1n1_Cases_confirmed() {
        return h1n1_Cases_confirmed;
    }

    public void setH1n1_Cases_confirmed(String h1n1_Cases_confirmed) {
        this.h1n1_Cases_confirmed = h1n1_Cases_confirmed;
    }

    public String getH1n1_Deaths_confirmed() {
        return h1n1_Deaths_confirmed;
    }

    public void setH1n1_Deaths_confirmed(String h1n1_Deaths_confirmed) {
        this.h1n1_Deaths_confirmed = h1n1_Deaths_confirmed;
    }
}
