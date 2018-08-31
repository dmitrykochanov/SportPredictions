package com.dmko.sportpredictions.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Prediction {

    private String team1;
    private String team2;
    private String time;
    private String tournament;
    private String place;
    private String prediction;
    @SerializedName("article")
    private List<Article> articles;

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Prediction{" +
                "team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", time='" + time + '\'' +
                ", tournament='" + tournament + '\'' +
                ", place='" + place + '\'' +
                ", prediction='" + prediction + '\'' +
                ", articles=" + articles +
                '}';
    }
}
