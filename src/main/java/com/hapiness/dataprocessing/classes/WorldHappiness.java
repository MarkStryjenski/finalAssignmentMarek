package com.hapiness.dataprocessing.classes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Document(collection = "WorldHappiness")
@XmlRootElement
public class WorldHappiness {

    @Id
    private String id;

    @NotNull
    private String countryName;

    @NotNull
    private String regionalIndicator;

    @NotNull
    private String ladderScore;

    @NotNull
    private String standardErrorOfLadderScore;

    @NotNull
    private String upperWhisker;

    @NotNull
    private String lowerWhisker;

    @NotNull
    private String loggedGDPPerCapita;

    @NotNull
    private String socialSupport;

    @NotNull
    private String healthyLifeExpectancy;

    @NotNull
    private String freedomToMakeLifeChoices;

    @NotNull
    private String generosity;

    @NotNull
    private String perceptionsOfCorruption;

    @NotNull
    private String ladderScoreInDystopia;

    @NotNull
    private String explainedByLogGDPPerCapita;

    @NotNull
    private String explainedBySocialSupport;

    @NotNull
    private String explainedByHealthyLifeExpectancy;

    @NotNull
    private String explainedByFreedomToMakeLifeChoices;

    @NotNull
    private String explainedByGenerosity;

    @NotNull
    private String explainedByPerceptionsOfCorruption;

    @NotNull
    private String dystopiaResidual;

    public WorldHappiness() {

    }

    public WorldHappiness(String id, String countryName, String regionalIndicator, String ladderScore, String standardErrorOfLadderScore, String upperWhisker, String lowerWhisker, String loggedGDPPerCapita, String socialSupport, String healthyLifeExpectancy, String freedomToMakeLifeChoices, String generosity, String perceptionsOfCorruption, String ladderScoreInDystopia, String explainedByLogGDPPerCapita, String explainedBySocialSupport, String explainedByHealthyLifeExpectancy, String explainedByFreedomToMakeLifeChoices, String explainedByGenerosity, String explainedByPerceptionsOfCorruption, String dystopiaResidual) {
        this.id = id;
        this.countryName = countryName;
        this.regionalIndicator = regionalIndicator;
        this.ladderScore = ladderScore;
        this.standardErrorOfLadderScore = standardErrorOfLadderScore;
        this.upperWhisker = upperWhisker;
        this.lowerWhisker = lowerWhisker;
        this.loggedGDPPerCapita = loggedGDPPerCapita;
        this.socialSupport = socialSupport;
        this.healthyLifeExpectancy = healthyLifeExpectancy;
        this.freedomToMakeLifeChoices = freedomToMakeLifeChoices;
        this.generosity = generosity;
        this.perceptionsOfCorruption = perceptionsOfCorruption;
        this.ladderScoreInDystopia = ladderScoreInDystopia;
        this.explainedByLogGDPPerCapita = explainedByLogGDPPerCapita;
        this.explainedBySocialSupport = explainedBySocialSupport;
        this.explainedByHealthyLifeExpectancy = explainedByHealthyLifeExpectancy;
        this.explainedByFreedomToMakeLifeChoices = explainedByFreedomToMakeLifeChoices;
        this.explainedByGenerosity = explainedByGenerosity;
        this.explainedByPerceptionsOfCorruption = explainedByPerceptionsOfCorruption;
        this.dystopiaResidual = dystopiaResidual;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public String toString() {
        return "WorldHappiness{" +
                "id='" + id + '\'' +
                ", countryName='" + countryName + '\'' +
                ", regionalIndicator='" + regionalIndicator + '\'' +
                ", ladderScore='" + ladderScore + '\'' +
                ", standardErrorOfLadderScore='" + standardErrorOfLadderScore + '\'' +
                ", upperWhisker='" + upperWhisker + '\'' +
                ", lowerWhisker='" + lowerWhisker + '\'' +
                ", loggedGDPPerCapita='" + loggedGDPPerCapita + '\'' +
                ", socialSupport='" + socialSupport + '\'' +
                ", healthyLifeExpectancy='" + healthyLifeExpectancy + '\'' +
                ", freedomToMakeLifeChoices='" + freedomToMakeLifeChoices + '\'' +
                ", generosity='" + generosity + '\'' +
                ", perceptionsOfCorruption='" + perceptionsOfCorruption + '\'' +
                ", ladderScoreInDystopia='" + ladderScoreInDystopia + '\'' +
                ", explainedByLogGDPPerCapita='" + explainedByLogGDPPerCapita + '\'' +
                ", explainedBySocialSupport='" + explainedBySocialSupport + '\'' +
                ", explainedByHealthyLifeExpectancy='" + explainedByHealthyLifeExpectancy + '\'' +
                ", explainedByFreedomToMakeLifeChoices='" + explainedByFreedomToMakeLifeChoices + '\'' +
                ", explainedByGenerosity='" + explainedByGenerosity + '\'' +
                ", explainedByPerceptionsOfCorruption='" + explainedByPerceptionsOfCorruption + '\'' +
                ", dystopiaResidual='" + dystopiaResidual + '\'' +
                '}';
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegionalIndicator() {
        return regionalIndicator;
    }

    public void setRegionalIndicator(String regionalIndicator) {
        this.regionalIndicator = regionalIndicator;
    }

    public String getLadderScore() {
        return ladderScore;
    }

    public void setLadderScore(String ladderScore) {
        this.ladderScore = ladderScore;
    }

    public String getStandardErrorOfLadderScore() {
        return standardErrorOfLadderScore;
    }

    public void setStandardErrorOfLadderScore(String standardErrorOfLadderScore) {
        this.standardErrorOfLadderScore = standardErrorOfLadderScore;
    }

    public String getUpperWhisker() {
        return upperWhisker;
    }

    public void setUpperWhisker(String upperWhisker) {
        this.upperWhisker = upperWhisker;
    }

    public String getLowerWhisker() {
        return lowerWhisker;
    }

    public void setLowerWhisker(String lowerWhisker) {
        this.lowerWhisker = lowerWhisker;
    }

    public String getLoggedGDPPerCapita() {
        return loggedGDPPerCapita;
    }

    public void setLoggedGDPPerCapita(String loggedGDPPerCapita) {
        this.loggedGDPPerCapita = loggedGDPPerCapita;
    }

    public String getSocialSupport() {
        return socialSupport;
    }

    public void setSocialSupport(String socialSupport) {
        this.socialSupport = socialSupport;
    }

    public String getHealthyLifeExpectancy() {
        return healthyLifeExpectancy;
    }

    public void setHealthyLifeExpectancy(String healthyLifeExpectancy) {
        this.healthyLifeExpectancy = healthyLifeExpectancy;
    }

    public String getFreedomToMakeLifeChoices() {
        return freedomToMakeLifeChoices;
    }

    public void setFreedomToMakeLifeChoices(String freedomToMakeLifeChoices) {
        this.freedomToMakeLifeChoices = freedomToMakeLifeChoices;
    }

    public String getGenerosity() {
        return generosity;
    }

    public void setGenerosity(String generosity) {
        this.generosity = generosity;
    }

    public String getPerceptionsOfCorruption() {
        return perceptionsOfCorruption;
    }

    public void setPerceptionsOfCorruption(String perceptionsOfCorruption) {
        this.perceptionsOfCorruption = perceptionsOfCorruption;
    }

    public String getLadderScoreInDystopia() {
        return ladderScoreInDystopia;
    }

    public void setLadderScoreInDystopia(String ladderScoreInDystopia) {
        this.ladderScoreInDystopia = ladderScoreInDystopia;
    }

    public String getExplainedByLogGDPPerCapita() {
        return explainedByLogGDPPerCapita;
    }

    public void setExplainedByLogGDPPerCapita(String explainedByLogGDPPerCapita) {
        this.explainedByLogGDPPerCapita = explainedByLogGDPPerCapita;
    }

    public String getExplainedBySocialSupport() {
        return explainedBySocialSupport;
    }

    public void setExplainedBySocialSupport(String explainedBySocialSupport) {
        this.explainedBySocialSupport = explainedBySocialSupport;
    }

    public String getExplainedByHealthyLifeExpectancy() {
        return explainedByHealthyLifeExpectancy;
    }

    public void setExplainedByHealthyLifeExpectancy(String explainedByHealthyLifeExpectancy) {
        this.explainedByHealthyLifeExpectancy = explainedByHealthyLifeExpectancy;
    }

    public String getExplainedByFreedomToMakeLifeChoices() {
        return explainedByFreedomToMakeLifeChoices;
    }

    public void setExplainedByFreedomToMakeLifeChoices(String explainedByFreedomToMakeLifeChoices) {
        this.explainedByFreedomToMakeLifeChoices = explainedByFreedomToMakeLifeChoices;
    }

    public String getExplainedByGenerosity() {
        return explainedByGenerosity;
    }

    public void setExplainedByGenerosity(String explainedByGenerosity) {
        this.explainedByGenerosity = explainedByGenerosity;
    }

    public String getExplainedByPerceptionsOfCorruption() {
        return explainedByPerceptionsOfCorruption;
    }

    public void setExplainedByPerceptionsOfCorruption(String explainedByPerceptionsOfCorruption) {
        this.explainedByPerceptionsOfCorruption = explainedByPerceptionsOfCorruption;
    }

    public String getDystopiaResidual() {
        return dystopiaResidual;
    }

    public void setDystopiaResidual(String dystopiaResidual) {
        this.dystopiaResidual = dystopiaResidual;
    }

}
