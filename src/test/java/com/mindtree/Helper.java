package com.mindtree;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import com.mindtree.entity.CovidData;
import lombok.Builder;

@Builder
public class Helper {

	public static List<CovidData> getAllStatesData() {

		String str = "2020-08-08";
		Date d = Date.valueOf(str);
		List<CovidData> data = new ArrayList<>();
		CovidData coviddata = new CovidData();
		coviddata.setId(1);
		coviddata.setConfirmed("wd");
		coviddata.setDate(d);
		coviddata.setDistrict("dsws");
		coviddata.setState("JK");
		coviddata.setTested("sdds");
		data.add(coviddata);

		CovidData coviddata2 = new CovidData();
		coviddata2.setId(1);
		coviddata2.setConfirmed("wd");
		coviddata2.setDate(d);
		coviddata2.setDistrict("dsws");
		coviddata2.setState("wddxsds");
		coviddata2.setTested("sdds");
		data.add(coviddata2);

		return data;
	}

	public static List<CovidData> getAllStatesDataempty() {
		return new ArrayList<>();
	}

	public static List<CovidData> getDatesEmptyData() {
		return new ArrayList<>();
	}

	public static List<CovidData> getDatesData() {

		String str = "2020-08-08";
		Date d = Date.valueOf(str);
		List<CovidData> data = new ArrayList<>();
		CovidData coviddata = new CovidData();
		coviddata.setId(1);
		coviddata.setConfirmed("wd");
		coviddata.setDate(d);
		coviddata.setDistrict("dsws");
		coviddata.setState("JK");
		coviddata.setTested("sdds");
		data.add(coviddata);

		CovidData coviddata1 = new CovidData();
		String str1 = "2020-08-07";
		Date d1 = Date.valueOf(str1);
		coviddata1.setId(1);
		coviddata1.setConfirmed("wd");
		coviddata1.setDate(d1);
		coviddata1.setDistrict("dsws");
		coviddata1.setState("wddxsds");
		coviddata1.setTested("sdds");
		data.add(coviddata1);

		CovidData coviddata2 = new CovidData();
		String str2 = "2020-08-06";
		Date d2 = Date.valueOf(str2);
		coviddata2.setId(1);
		coviddata2.setConfirmed("wd");
		coviddata2.setDate(d2);
		coviddata2.setDistrict("dsws");
		coviddata2.setState("wddxsds");
		coviddata2.setTested("sdds");
		data.add(coviddata2);

		return data;
	}

}
