package com.hapiness.dataprocessing.controller;

import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hapiness.dataprocessing.classes.Covid19;
import com.hapiness.dataprocessing.classes.InternetUsage;
import com.hapiness.dataprocessing.classes.WorldHappiness;
import com.hapiness.dataprocessing.repositories.InternetUsageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.Response;

@Controller
public class GraphController {


    @GetMapping("/displayBarGraph")
    public String barGraph(Model model) throws JAXBException {
        Map<String, Double> country = new LinkedHashMap<>();
        Map<String, String> countryNameMap = new LinkedHashMap<>();

        String countryName = "China";
        final String uriInternetUsage = "http://localhost:8080//internetUsage/country/" + countryName;
        final String uriHappiness = "http://localhost:8080//worldHappiness/country/" + countryName;
        final String uriCovid = "http://localhost:8080//covid/country/" + countryName;

        RestTemplate restTemplateHappiness = new RestTemplate();
        RestTemplate restTemplateInternet = new RestTemplate();
        RestTemplate restTemplateCovid = new RestTemplate();


        InternetUsage internetUsage = restTemplateInternet.getForObject(uriInternetUsage, InternetUsage.class);
        WorldHappiness worldHappiness = restTemplateHappiness.getForObject(uriHappiness, WorldHappiness.class);
        Covid19 covid19 = restTemplateCovid.getForObject(uriCovid, Covid19.class);

        assert worldHappiness != null;
        Double ladderScore = Double.parseDouble(worldHappiness.getLadderScore());
        Double freedomToMakeLifeChoices = Double.parseDouble(worldHappiness.getFreedomToMakeLifeChoices());
        assert internetUsage != null;
        Double worldRankInternetUsage = Double.parseDouble(internetUsage.getWorldRank());
        assert covid19 != null;
        Double countryFluPneumonia = Double.parseDouble(covid19.getFlu_death_rate_per_10000());

        countryNameMap.put("countryName", countryName);
        country.put("LadderScoreHappiness", ladderScore);
        country.put("FreedomToMakeLifeChoices", freedomToMakeLifeChoices);
        country.put("WorldRankInInternetUsage", worldRankInternetUsage);
        country.put("countryFluPneumoniaDeaths by 10000", countryFluPneumonia);
        model.addAttribute("country", country);
        model.addAttribute("countryNameMap", countryNameMap);

        return "barGraph";
    }

    @GetMapping("/displayPieChart")
    public String pieChart(Model model) {

        Map<String, String> countryMap1 = new LinkedHashMap<>();
        Map<String, String> countryMap2 = new LinkedHashMap<>();

        String countryName1 = "Algeria";
        String countryName2 = "Afghanistan";

        final String uriCovid1 = "http://localhost:8080//covid/country/" + countryName1;
        final String uriCovid2 = "http://localhost:8080//covid/country/" + countryName2;


        RestTemplate restTemplateCovid1 = new RestTemplate();
        RestTemplate restTemplateCovid2 = new RestTemplate();


        Covid19 covid19Country1 = restTemplateCovid1.getForObject(uriCovid1, Covid19.class);
        Covid19 covid19Country2 = restTemplateCovid2.getForObject(uriCovid2, Covid19.class);


        assert covid19Country1 != null;
        Double countryCovidDeaths1 = Double.parseDouble(covid19Country1.getCovid_deaths());
        assert covid19Country2 != null;
        Double countryCovidDeaths2 = Double.parseDouble(covid19Country2.getCovid_deaths());

        countryMap1.put("countryName1", countryName1);
        countryMap2.put("countryName2", countryName2);

        model.addAttribute("deaths1", countryCovidDeaths1);
        model.addAttribute("deaths2", countryCovidDeaths2);
        return "pieChart";
    }

}
