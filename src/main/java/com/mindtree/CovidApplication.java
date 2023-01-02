package com.mindtree;

import com.mindtree.controller.CovidController;
import com.mindtree.entity.CovidDataDto;
import com.mindtree.entity.CovidDataDtoByState;
import com.mindtree.exception.InvalidDateException;
import com.mindtree.exception.NoDataFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CovidApplication {

    public static void main(String[] args) {
        SpringApplication.run(CovidApplication.class, args);

    }


    @Autowired
    CovidController controller;

    @PostConstruct
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Display the main menu
            System.out.println("*************************************************");
            System.out.println("1. Get State Names");
            System.out.println("2. Get District given state");
            System.out.println("3. Display Data by state with in date range");
            System.out.println("4. Display Confirmed cases by comparing two states for a given date range");
            System.out.println("5. Exit");
            System.out.println("please select Option:");


            // Read the user's selection
            int selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    // Get the names of all states in India
                    List<String> states = controller.findAllStates();
                    // Display the list of states
                    states.forEach(state -> System.out.println(state + " "));
                    break;
                case 2:
                    // Read the user's input for the state
                    System.out.println("please enter state code : ");
                    String state = scanner.next();
                    // Get the list of districts for the specified state
                    List<String> districts = null;
                    districts = controller.findDistrictNamebyState(state);
                    // Display the list of districts
                    districts.forEach(district -> System.out.println(district + " "));
                    break;
                case 3:
                    System.out.println("please enter startDate (YYYY_MM_DD): ");
                    String startDateInput = scanner.next();
                    Date startDate = null;

                    try {
                        startDate = Date.valueOf(startDateInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("End date must be in the format YYYY-MM-DD");
                    }

                    System.out.println("please enter endDate (YYYY_MM_DD): ");
                    String endDateInput = scanner.next();
                    Date endDate = null;
                    try {
                        endDate = Date.valueOf(endDateInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("End date must be in the format YYYY-MM-DD");
                    }
                    if (endDate != null && startDate != null && endDate.before(startDate)) {
                        System.out.println("Invalid date range: end date must be after start date");
                    }
                    List<CovidDataDto> covidDataDtos = controller.findDataByStateInDateRange(startDate, endDate);
                    covidDataDtos.forEach(covidDataDto -> System.out.println(covidDataDto + " "));
                    break;
                case 4:
                    System.out.println("please enter startDate (YYYY_MM_DD): ");
                    String startDateInput1 = scanner.next();
                    Date startDate1 = null;
                    try {
                        startDate1 = Date.valueOf(startDateInput1);
                    } catch (IllegalArgumentException e) {
                        System.out.println("End date must be in the format YYYY-MM-DD");
                    }

                    System.out.println("please enter endDate (YYYY_MM_DD): ");
                    String endDateInput1 = scanner.next();
                    Date endDate1 = null;
                    try {
                        endDate1 = Date.valueOf(endDateInput1);
                    } catch (IllegalArgumentException e) {
                        System.out.println("End date must be in the format YYYY-MM-DD");
                    }
                    if (endDate1 != null && startDate1 != null && endDate1.before(startDate1)) {
                        System.out.println("Invalid date range: end date must be after start date");
                    }
                    System.out.println("please enter first state code : ");
                    String state1 = scanner.next();

                    System.out.println("please enter second state code : ");
                    String state2 = scanner.next();

                    List<CovidDataDtoByState> covidDataDtoByStates = controller.findDataByStateAndDateRange(startDate1, endDate1,state1,state2);

                    break;


                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
    }
}





