package com.example.zenikameetingplanner.repository;

import com.example.zenikameetingplanner.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting , Long> {
    @Query("select  r from  Meeting r   where r.date between  :MeetingDateMinusOneHourForInfection and  :MeetingDatePlusOneHourForInfection ")
    List<Meeting> findAllMeetingBetweenTwoGivenDates(LocalDateTime MeetingDateMinusOneHourForInfection , LocalDateTime MeetingDatePlusOneHourForInfection);
}
