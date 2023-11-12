package com.example.tfcproyect.model.entitys;

public class Team {
    private int id;
    private String abbreviation;
    private String city;
    private String conference;
    private String division;
    private String name;
    private String urlLogo;
    private String fullName;
    private String headCoach;
    private Stadium stadium;

    public Team(int id, String abbreviation, String city, String conference, String division, String name, String urlLogo, String fullName, String headCoach, Stadium stadium) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.city = city;
        this.conference = conference;
        this.division = division;
        this.name = name;
        this.urlLogo = urlLogo;
        this.fullName = fullName;
        this.headCoach = headCoach;
        this.stadium = stadium;
    }

    public Team(int id, String abbreviation, String city, String conference, String division, String name, String urlLogo, String fullName, String headCoach) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.city = city;
        this.conference = conference;
        this.division = division;
        this.name = name;
        this.urlLogo = urlLogo;
        this.fullName = fullName;
        this.headCoach = headCoach;

    }

    public Team(int id, String abbreviation, String city, String conference, String division, String name, String urlLogo) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.city = city;
        this.conference = conference;
        this.division = division;
        this.name = name;
        this.urlLogo = urlLogo;
    }



    public Team() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadCoach() {
        return headCoach;
    }

    public void setHeadCoach(String headCoach) {
        this.headCoach = headCoach;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }
}
