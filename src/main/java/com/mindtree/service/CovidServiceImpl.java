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
        List<String> states = data.stream().map(covidData -> covidData.getState()).distinct().collect(Collectors.toList());
        return states;
    }


    @Override
    public List<String> findDistrictNamesbyState(String state) {
        List<CovidData> data = covidDao.findAll();
        List<String> districtNames = null;
        List<CovidData> stateData = covidDao.findByState(state);
        try {
            if (stateData.size() == 0) {
                throw new InvalidStateCodeException("Invalid State code, please check your input");
            }
            districtNames = data.stream().filter(covidData -> covidData.getState().equalsIgnoreCase(state))
                    .map(covidData -> covidData.getDistrict()).sorted().collect(Collectors.toList());
        } catch (InvalidStateCodeException e) {
            // log the error message
            System.out.println(e.getMessage());
            // set district to an empty list
            districtNames = new ArrayList<>();
        }
        return districtNames;
    }

    @Override
    public List<CovidDataDto> findDataByStateInDateRange(Date startDate, Date endDate) {
        List<CovidDataDto> covidDataDtos = null;
        List<CovidData> covidData = covidDao.findByDateBetween(startDate, endDate);

        try {
            if (covidData.size() == 0) {
                throw new NoDataFoundException("No Data Present");
            }
            covidDataDtos = covidData.stream()
                    .sorted(Comparator.comparing(CovidData::getDate))
                    .map(covidData1 -> {
                        CovidDataDto covidDataDto = new CovidDataDto();
                        BeanUtils.copyProperties(covidData1, covidDataDto);
                        return covidDataDto;
                    })
                    .collect(Collectors.toList());
        } catch (NoDataFoundException e) {
            System.out.println(e.getMessage());
            covidDataDtos = new ArrayList<>();
        }
        return covidDataDtos;
    }

    @Override
    public List<CovidDataDtoByState> getDataFromTwoStatesAndDateRange(Date startdate, Date enddate, String firststate, String secondstate) {
        List<CovidData> covidData = covidDao.findByDateBetween(startdate, enddate);
        List<CovidDataDtoByState> covidDataDtoByStates = new ArrayList<>();
        String firststateconfirmtotal = null;
        String secondstateconfirmtotal = null;
        for (CovidData data : covidData) {
            if (data.getState().equals(firststate)) {
                firststateconfirmtotal = data.getConfirmed();
            }
            if (data.getState().equals(secondstate)) {
                secondstateconfirmtotal = data.getConfirmed();

            }
            CovidDataDtoByState covidDataDtoByState = new CovidDataDtoByState();
            covidDataDtoByState.setDate(data.getDate());
            covidDataDtoByState.setFirsyState(firststate);
            covidDataDtoByState.setSecondState(secondstate);
            covidDataDtoByState.setSecondStateConfirmedTotal(secondstateconfirmtotal);
            covidDataDtoByState.setFirstStateConfirmedTotal(firststateconfirmtotal);
            covidDataDtoByStates.add(covidDataDtoByState);
        }
        return covidDataDtoByStates;
    }
}