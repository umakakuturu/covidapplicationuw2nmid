package com.mindtree.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.sql.Date;


@Entity
public class CovidData {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "date")
    private Date date;
    private String state;
    private String district;
    private String tested;
    private String confirmed;
    private String recovered;

    public CovidData() {
        super();
    }

    public CovidData(int id, Date date, String state, String district, String tested, String confirmed,
                     String recovered) {
        super();
        this.id = id;
        this.date = date;
        this.state = state;
        this.district = district;
        this.tested = tested;
        this.confirmed = confirmed;
        this.recovered = recovered;
    }

    @Override
    public String toString() {
        return "CovidData [id=" + id + ", date=" + date + ", state=" + state + ", district=" + district + ", tested="
                + tested + ", confirmed=" + confirmed + ", recovered=" + recovered + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTested() {
        return tested;
    }

    public void setTested(String tested) {
        this.tested = tested;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }


}
