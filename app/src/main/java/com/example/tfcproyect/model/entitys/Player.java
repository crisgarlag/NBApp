package com.example.tfcproyect.model.entitys;

public class Player {
    private int id;
    private String firstName;
    private int heightFeet;
    private int heightInches;
    private String lastName;
    private String position;
    private Team team;
    private int weightPounds;
    private String urlPhoto;
    private String fullName;

    public Player(int id, String firstName, int height_feet, int height_inches, String lastName, String position, Team team, int weight_pounds) {
        this.id = id;
        this.firstName = firstName;
        this.heightFeet = height_feet;
        this.heightInches = height_inches;
        this.lastName = lastName;
        this.position = position;
        this.team = team;
        this.weightPounds = weight_pounds;
    }

    public Player() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getHeight_feet() {
        return heightFeet;
    }

    public void setHeight_feet(int height_feet) {
        this.heightFeet = height_feet;
    }

    public int getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(int heightInches) {
        this.heightInches = heightInches;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getWeightPounds() {
        return weightPounds;
    }

    public void setWeightPounds(int weightPounds) {
        this.weightPounds = weightPounds;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getFullName(){
        return firstName +" "+ lastName;
    }

}

