package com.mindtree.service;

import com.mindtree.entity.CovidDataDto;
import com.mindtree.entity.CovidDataDtoByState;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface CovidService {

    List<String> getAllStates();

    List<String> findDistrictNamesbyState(String state);

    List<CovidDataDto> findDataByStateInDateRange( Date startDate, Date endDate);


    List<CovidDataDtoByState> getDataFromTwoStatesAndDateRange(Date startdate, Date enddate, String firststate, String secondstate);
}
