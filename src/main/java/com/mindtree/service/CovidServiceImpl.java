package com.mindtree.service;

import com.mindtree.dao.CovidDao;
import com.mindtree.entity.CovidData;
import com.mindtree.entity.CovidDataDto;
import com.mindtree.entity.CovidDataDtoByState;
import com.mindtree.exception.InvalidStateCodeException;
import com.mindtree.exception.NoDataFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CovidServiceImpl implements CovidService {
    @Autowired
    CovidDao covidDao;

    @Override
    public List<String> getAllStates() {
        List<CovidData> data = covidDao.findAll();
        List<String> states = data.stream().map(i -> i.getState()).distinct().collect(Collectors.toList());
        return states;
    }


    @Override
    public List<String> findDistrictNameByState(String state) {
        List<CovidData> data = covidDao.findAll();
        List<String> district = null;
        List<CovidData> stateData = covidDao.findByState(state);
        try {
            if (stateData.size() == 0) {
                throw new InvalidStateCodeException("Invalid State code, please check your input");
            }
            district = data.stream().filter(covidData -> covidData.getState().equalsIgnoreCase(state))
                    .map(covidData -> covidData.getDistrict()).sorted().collect(Collectors.toList());
        } catch (InvalidStateCodeException e) {
            // log the error message
            System.out.println(e.getMessage());
            // set district to an empty list
            district = new ArrayList<>();
        }
        return district;
    }

    @Override
    public List<CovidDataDto> findDataByStateInDateRange(Date startDate, Date endDate) {
        List<CovidDataDto> result = null;
        List<CovidData> covidData = covidDao.findByDateBetween(startDate, endDate);

        try {
            if (covidData.size() == 0) {
                throw new NoDataFoundException("No Data Present");
            }
            result = covidData.stream()
                    .sorted(Comparator.comparing(CovidData::getDate))
                    .map(data -> {
                        CovidDataDto dto = new CovidDataDto();
                        BeanUtils.copyProperties(data, dto);
                        return dto;
                    })
                    .collect(Collectors.toList());


        } catch (NoDataFoundException e) {
            System.out.println(e.getMessage());
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public List<CovidDataDtoByState> findDataByStateAndDateRange(Date startDate, Date endDate, String firstStateCode, String secondStateCode) {

        List<CovidData> response = covidDao.findConfirmedCasesByDateAndState(startDate,endDate,firstStateCode,secondStateCode);


        return null;
    }
}
