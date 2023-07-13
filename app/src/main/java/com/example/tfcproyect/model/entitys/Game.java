package com.example.tfcproyect.model.entitys;

public class Game {

    private String awayTeam;
    private String homeTeam;
    private String awayTeamId;
    private String homeTeamId;
    private String awayTeamScore;
    private String homeTeamScore;
    private String urlLogoAwayTeam;
    private String urlLogoHomeTeam;

    public Game(String awayTeam, String homeTeam, String awayTeamId, String homeTeamId, String awayTeamScore, String homeTeamScore) {
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;
        this.awayTeamId = awayTeamId;
        this.homeTeamId = homeTeamId;
        this.awayTeamScore = awayTeamScore;
        this.homeTeamScore = homeTeamScore;
    }

    public Game() {
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(String awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(String awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public String getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(String homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public String getUrlLogoAwayTeam() {
        return urlLogoAwayTeam;
    }

    public void setUrlLogoAwayTeam(String urlLogoAwayTeam) {
        this.urlLogoAwayTeam = urlLogoAwayTeam;
    }

    public String getUrlLogoHomeTeam() {
        return urlLogoHomeTeam;
    }

    public void setUrlLogoHomeTeam(String urlLogoHomeTeam) {
        this.urlLogoHomeTeam = urlLogoHomeTeam;
    }
}
