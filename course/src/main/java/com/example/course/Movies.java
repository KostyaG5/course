package com.example.course;

import java.io.Serializable;

public class Movies implements Serializable {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String movieName;
    private String producer;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public int getOutYear() {
        return outYear;
    }

    public void setOutYear(int outYear) {
        this.outYear = outYear;
    }

    public int getWatchersQuantity() {
        return watchersQuantity;
    }

    public void setWatchersQuantity(int watchersQuantity) {
        this.watchersQuantity = watchersQuantity;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    private String actor;

    public Movies(String id, String movieName, String producer, String actor,
                  int outYear, int watchersQuantity, float rate, long money) {
        this.id = id;
        this.movieName = movieName;
        this.producer = producer;
        this.actor = actor;
        this.outYear = outYear;
        this.watchersQuantity = watchersQuantity;
        this.rate = rate;
        this.money = money;
    }

    public Movies(String id) {
        this.id = id;
    }

    private int outYear, watchersQuantity;
    private float rate;
    private long money;
}