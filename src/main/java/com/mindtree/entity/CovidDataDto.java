package com.mindtree.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CovidDataDto {

	private Date date;
	private String state;
	@JsonProperty("Confirmed total")
	private String confirmed;

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

	@Override
	public String toString() {
		return "CovidDataDto [date=" + date + ", state=" + state + ", confirmedTotal=" + confirmed + "]";
	}

	public String getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}

}
