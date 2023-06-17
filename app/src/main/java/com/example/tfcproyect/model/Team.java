package com.example.tfcproyect.model;

public class Team {
    private int id;
    private String abbreviation;
    private String city;
    private String conference;
    private String division;
    private String name;
    private String urlLogo;
    private String fullName;

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
}
