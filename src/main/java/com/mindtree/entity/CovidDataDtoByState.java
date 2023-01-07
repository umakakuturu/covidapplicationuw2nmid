package com.mindtree.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class CovidDataDtoByState {

    private Date date;

    @JsonProperty("FIRST STATE")
    private String firsyState;
    @JsonProperty("SECOND STATE")
    private String secondState;

    @JsonProperty("FIRST STATE CONFIRMED TOTAL")
    private String firstStateConfirmedTotal;

    @JsonProperty("SECOND STATE CONFIRMED TOTAL")
    private String secondStateConfirmedTotal;

    public CovidDataDtoByState(Date date, String firsyState, String secondState, String firstStateConfirmedTotal, String secondStateConfirmedTotal) {
        this.date = date;
        this.firsyState = firsyState;
        this.secondState = secondState;
        this.firstStateConfirmedTotal = firstStateConfirmedTotal;
        this.secondStateConfirmedTotal = secondStateConfirmedTotal;
    }

    public CovidDataDtoByState() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFirsyState() {
        return firsyState;
    }

    public void setFirsyState(String firsyState) {
        this.firsyState = firsyState;
    }

    public String getSecondState() {
        return secondState;
    }

    public void setSecondState(String secondState) {
        this.secondState = secondState;
    }

    public String getFirstStateConfirmedTotal() {
        return firstStateConfirmedTotal;
    }

    public void setFirstStateConfirmedTotal(String firstStateConfirmedTotal) {
        this.firstStateConfirmedTotal = firstStateConfirmedTotal;
    }
    public String getSecondStateConfirmedTotal() {
        return secondStateConfirmedTotal;
    }

    public void setSecondStateConfirmedTotal(String secondStateConfirmedTotal) {
        this.secondStateConfirmedTotal = secondStateConfirmedTotal;
    }

    @Override
    public String toString() {
        return "CovidDataDtoByState{" +
                "date=" + date +
                ", firsyState='" + firsyState + '\'' +
                ", secondState='" + secondState + '\'' +
                ", firstStateConfirmedTotal='" + firstStateConfirmedTotal + '\'' +
                ", secondStateConfirmedTotal='" + secondStateConfirmedTotal + '\'' +
                '}';
    }

}
