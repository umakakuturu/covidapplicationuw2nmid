package com.mindtree.dao;

import com.mindtree.entity.CovidData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Repository
public interface CovidDao extends JpaRepository<CovidData, Integer> {

    List<CovidData> findByState(String state);

    List<CovidData> findByDate(Date startDate);
    @Query("SELECT e FROM CovidData e WHERE e.date > :startDate AND e.date < :endDate")
    List<CovidData> findByDateBetween(Date startDate, Date endDate);

   /* @Query("SELECT new com.example.DTO(date, state, SUM(CASE WHEN state = :state1 THEN confirmed ELSE 0 END) as confirmed1, SUM(CASE WHEN state = :state2 THEN confirmed ELSE 0 END) as confirmed2) FROM Cases c WHERE date BETWEEN :startDate AND :endDate AND (state = :state1 OR state = :state2) GROUP BY date, state")
    List<DTO> findConfirmedCasesByDateAndState(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state1") String state1, @Param("state2") String state2);
*/

    @Query("SELECT new com.mindtree.entity.CovidDataDtoByState(date, state, SUM(CASE WHEN state = :state1 THEN firstStateConfirmedTotal ELSE 0 END) as firstStateConfirmedTotal, SUM(CASE WHEN state = :state2 THEN secondStateConfirmedTotal ELSE 0 END) as secondStateConfirmedTotal) FROM Cases c WHERE date BETWEEN :startDate AND :endDate AND (state = :state1 OR state = :state2) GROUP BY date, state")
    List<CovidData> findConfirmedCasesByDateAndState(Date startDate, Date endDate, String state1, String state2);

}
