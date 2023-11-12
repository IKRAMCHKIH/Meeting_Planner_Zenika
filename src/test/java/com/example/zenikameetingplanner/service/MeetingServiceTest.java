package com.example.zenikameetingplanner.service;

import static org.junit.jupiter.api.Assertions.*;
import com.example.zenikameetingplanner.exception.CapacityExp;
import com.example.zenikameetingplanner.exception.EquipementExp;
import com.example.zenikameetingplanner.exception.RoomNotFindExp;
import com.example.zenikameetingplanner.model.Meeting;
import com.example.zenikameetingplanner.repository.MeetingRepository;
import com.example.zenikameetingplanner.service.MeetingService;
import com.example.zenikameetingplanner.service.impl.MeetingServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class MeetingServiceTest {
    @Mock
    private MeetingRepository meetingRepository;

    @InjectMocks
    private MeetingServiceImpl meetingService;

    @Test
    void testMeetingReservation() throws RoomNotFindExp, CapacityExp, EquipementExp {
        // Arrange
        Meeting meeting = new Meeting();
        meeting.setId(1L);

        when(meetingRepository.save(meeting)).thenReturn(meeting);

        // Act
        String result = meetingService.meetingReservation(meeting);

        // Assert
        assertEquals("Reservation successful. Meeting ID: 1", result);
    }

}