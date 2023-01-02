package com.mindtree.controller;

import com.mindtree.entity.CovidDataDto;
import com.mindtree.entity.CovidDataDtoByState;
import com.mindtree.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
@RequestMapping("/covid")
@RestController
public class CovidController {

    @Autowired
    private CovidService covidService;

    @GetMapping("/states")
    public List<String> findAllStates() {
        return covidService.getAllStates();
    }

    @GetMapping("/district/{state}")
    public List<String> findDistrictNamebyState(@PathVariable("state") String state) {
        List<String> districts = covidService.findDistrictNameByState(state);
        return districts;

    }

    // 3
    @GetMapping("/DataBetweenDates")
    public List<CovidDataDto> findDataByStateInDateRange(@RequestParam("startDate") Date startDate,
                                                         @RequestParam("endDate") Date endDate) {
        return covidService.findDataByStateInDateRange(startDate, endDate);
    }

    @GetMapping("/DataBetweenDatesByStateWise")
    public List<CovidDataDtoByState> findDataByStateAndDateRange(@RequestParam("startDate") Date startDate,
                                                                 @RequestParam("endDate") Date endDate,
                                                                 @RequestParam("firstStateCode") String firstStateCode, @RequestParam("secondStateCode") String secondStateCode ) {
        return covidService.findDataByStateAndDateRange(startDate,endDate,firstStateCode, secondStateCode);
    }

}