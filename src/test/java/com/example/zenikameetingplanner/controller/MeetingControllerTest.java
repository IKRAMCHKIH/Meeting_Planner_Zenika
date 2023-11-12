package com.example.zenikameetingplanner.controller;

import com.example.zenikameetingplanner.exception.InputExp;
import org.junit.jupiter.api.Test;
import com.example.zenikameetingplanner.dto.MeetingDTO;
import com.example.zenikameetingplanner.exception.CapacityExp;
import com.example.zenikameetingplanner.exception.EquipementExp;
import com.example.zenikameetingplanner.exception.RoomNotFindExp;
import com.example.zenikameetingplanner.mapper.MeetingMapper;
import com.example.zenikameetingplanner.model.Meeting;
import com.example.zenikameetingplanner.service.MeetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class MeetingControllerTest {
    @Mock
    private MeetingService meetingService;

    @Mock
    private MeetingMapper meetingMapper;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private MeetingController meetingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testReservation() throws RoomNotFindExp, CapacityExp, EquipementExp, InputExp {
        // Arrange
        MeetingDTO meetingRequest = new MeetingDTO();
        Meeting meeting = new Meeting();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(meetingMapper.meetingRequestToMeetingEntity(any(MeetingDTO.class))).thenReturn(meeting);
        when(meetingService.meetingReservation(any(Meeting.class))).thenReturn("Reservation Successful");

        // Act
        ResponseEntity<String> responseEntity = meetingController.reservation(meetingRequest, bindingResult);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Reservation Successful", responseEntity.getBody());
    }
    @Test
    void testReservationWithValidationError() throws RoomNotFindExp, CapacityExp, EquipementExp {
        // Arrange
        MeetingDTO meetingRequest = new MeetingDTO();

        when(bindingResult.hasErrors()).thenReturn(true);

        // Act and Assert
        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class,
                () -> meetingController.reservation(meetingRequest, bindingResult)
        );

        assertEquals("Input validation failed: " + bindingResult.getAllErrors(), exception.getMessage());
    }
}