package com.mindtree;

import com.mindtree.dao.CovidDao;
import com.mindtree.entity.CovidData;
import com.mindtree.entity.CovidDataDto;
import com.mindtree.entity.CovidDataDtoByState;
import com.mindtree.service.CovidServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class CovidServiceTest {

    @InjectMocks
    CovidServiceImpl covidServiceImpl;

    @Mock
    CovidDao covidDao;
    List<CovidData> allstates;

    //1
    @Test
    public void testFunctionalityNameGetAllStates() {
        List<String> states = Arrays.asList("st1", "st2", "st3");
        when(covidServiceImpl.getAllStates()).thenReturn(states);
    }

    @Test
    public void testFunctionalityFindDistrictNamesbyState() {
        allstates = Helper.getAllStatesData();
        when(covidDao.findAll()).thenReturn(allstates);
        when(covidDao.findByState(Mockito.anyString())).thenReturn(allstates);
        List<String> response = covidServiceImpl.findDistrictNamesbyState("JK");
        assertEquals(1, response.size());

    }

    @Test
    public void testFunctionalityFindDistrictNamesbyInvalidState() {
        allstates = Helper.getAllStatesDataempty();
        when(covidDao.findByState(Mockito.anyString())).thenReturn(allstates);
        covidServiceImpl.findDistrictNamesbyState("JJ");
    }

    @Test /* (expected = InvalidDateExceptionca.class) */
    public void testFunctionalityFindDataByStateInDateRange() {

        String date1 = "2020-08-06";
        Date strtdate = Date.valueOf(date1);
        String date2 = "2020-08-07";
        Date endDate = Date.valueOf(date2);
        List<CovidData> covidData = Helper.getDatesData();
        Mockito.when(covidDao.findAll()).thenReturn(covidData);
        Mockito.when(covidDao.findByDateBetween(strtdate, endDate)).thenReturn(covidData);
        List<CovidDataDto> result = covidServiceImpl.findDataByStateInDateRange(strtdate, endDate);
        assertEquals(3, result.size());
    }

    @Test
    public void testFunctionalityFindDataByStateInDateRangeNoRecordFound() {

        String date1 = "2020-08-06";
        Date strtdate = Date.valueOf(date1);
        String date2 = "2020-08-07";
        Date endDate = Date.valueOf(date2);
        List<CovidData> covidData = Helper.getDatesEmptyData();
        Mockito.when(covidDao.findAll()).thenReturn(covidData);
        Mockito.when(covidDao.findByDateBetween(strtdate, endDate)).thenReturn(covidData);
        covidServiceImpl.findDataByStateInDateRange(strtdate, endDate);
    }


    @Test
    public void testFunctionalityFindDataByStateInDateRangeNoRecordFound11() {

        String date1 = "2020-08-06";
        Date strtdate = Date.valueOf(date1);
        String date2 = "2020-08-07";
        Date endDate = Date.valueOf(date2);
        String firstState = "JK";
        String secondState = "AS";
        List<CovidData> covidData = Helper.getDatesData();
        Mockito.when(covidDao.findByDateBetween(strtdate, endDate)).thenReturn(covidData);
        List<CovidDataDtoByState> result = covidServiceImpl.getDataFromTwoStatesAndDateRange(strtdate, endDate, firstState, secondState);
        assertNotNull(result);
    }
}
