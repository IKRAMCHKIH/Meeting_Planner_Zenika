package com.example.zenikameetingplanner.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.example.zenikameetingplanner.enums.MeetingType;
import com.example.zenikameetingplanner.exception.CapacityExp;
import com.example.zenikameetingplanner.exception.EquipementExp;
import com.example.zenikameetingplanner.exception.RoomNotFindExp;
import com.example.zenikameetingplanner.model.Meeting;
import com.example.zenikameetingplanner.model.Room;
import com.example.zenikameetingplanner.repository.MeetingRepository;
import com.example.zenikameetingplanner.service.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MeetingServiceImplTest {
    @Mock
    private MeetingRepository meetingRepository;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private MeetingServiceImpl meetingService;

    @Test
    void testMeetingReservationRoomNotFindExp() {
        // Arrange
        Meeting meeting = new Meeting();
        meeting.setDate(LocalDateTime.now());

        when(roomService.getEligibleRoomsForReservation(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Collections.emptyList());

        // Act and Assert
        assertThrows(RoomNotFindExp.class, () -> meetingService.meetingReservation(meeting));
    }

    @Test
    void testMeetingReservationCapacityExp() {
        // Arrange
        Meeting meeting = new Meeting();
        meeting.setDate(LocalDateTime.now());

        when(roomService.getEligibleRoomsForReservation(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList(new Room()));
        when(roomService.isRoomCapacitySufficient(anyInt(), any(Room.class))).thenReturn(false);

        // Act and Assert
        assertThrows(CapacityExp.class, () -> meetingService.meetingReservation(meeting));
    }

    @Test
    void testMeetingReservationEquipementExp() {
        // Arrange
        Meeting meeting = new Meeting();
        meeting.setDate(LocalDateTime.now());

        when(roomService.getEligibleRoomsForReservation(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList(new Room()));
        when(roomService.isRoomCapacitySufficient(anyInt(), any(Room.class))).thenReturn(true);
        when(roomService.getRoomsSuitableForMeeting(MeetingType.valueOf(anyString()), anyList())).thenReturn(Collections.emptyList());

        // Act and Assert
        assertThrows(EquipementExp.class, () -> meetingService.meetingReservation(meeting));
    }

    @Test
    void testMeetingReservationSuccess() throws RoomNotFindExp, CapacityExp, EquipementExp {
        // Arrange
        Meeting meeting = new Meeting();
        meeting.setDate(LocalDateTime.now());

        Room eligibleRoom = new Room();
        eligibleRoom.setRoomName("Room1");

        when(roomService.getEligibleRoomsForReservation(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList(eligibleRoom));
        when(roomService.isRoomCapacitySufficient(anyInt(), any(Room.class))).thenReturn(true);
        when(roomService.getRoomsSuitableForMeeting(MeetingType.valueOf(anyString()), anyList())).thenReturn(Collections.singletonList(eligibleRoom));

        // Act
        String result = meetingService.meetingReservation(meeting);

        // Assert
        verify(meetingRepository, times(1)).save(meeting);
        // Add more assertions based on your specific scenario

    }
    }