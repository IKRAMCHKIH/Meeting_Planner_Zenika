package com.example.zenikameetingplanner.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import com.example.zenikameetingplanner.enums.MeetingType;
import com.example.zenikameetingplanner.model.Equipment;
import com.example.zenikameetingplanner.model.Meeting;
import com.example.zenikameetingplanner.model.Room;
import com.example.zenikameetingplanner.repository.MeetingRepository;
import com.example.zenikameetingplanner.repository.RoomRepository;
import com.example.zenikameetingplanner.service.impl.RoomServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private MeetingRepository meetingRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testIsRoomCapacitySufficient() {
        Room room = new Room();
        room.setRoomCapacity(100L);

        assertFalse(roomService.isRoomCapacitySufficient(70, room));
        assertFalse(roomService.isRoomCapacitySufficient(80, room));
    }

    @Test
    void testGetAvailableRoomsForReservation() {
        List<Room> excludedRooms = Arrays.asList(new Room(), new Room());
        when(roomRepository.findAllowedRooms(excludedRooms)).thenReturn(Arrays.asList(new Room(), new Room()));

        List<Room> availableRooms = roomService.getAvailableRoomsForReservation(excludedRooms);

        assertFalse(availableRooms.isEmpty());
        verify(roomRepository, times(1)).findAllowedRooms(excludedRooms);
    }
    @Test
    void testGetAllEquipmentInRoom() {
        Room room = new Room();
        room.setRoomName("Room1");
        when(roomRepository.getEquipmentInARoom("Room1")).thenReturn(Arrays.asList(new Equipment(), new Equipment()));

        List<Equipment> equipmentList = roomService.getAllEquipmentInRoom(room);

        assertFalse(equipmentList.isEmpty());
        verify(roomRepository, times(1)).getEquipmentInARoom("Room1");
    }

    @Test
    void testGetEligibleRoomsForReservation() {
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusHours(2);

        List<Meeting> reservationList = Arrays.asList(new Meeting(), new Meeting());
        when(meetingRepository.findAllMeetingBetweenTwoGivenDates(startTime, endTime)).thenReturn(reservationList);
        when(roomRepository.findAllowedRooms(anyList())).thenReturn(Arrays.asList(new Room(), new Room()));

        List<Room> eligibleRooms = roomService.getEligibleRoomsForReservation(startTime, endTime);

        assertFalse(eligibleRooms.isEmpty());
        verify(meetingRepository, times(1)).findAllMeetingBetweenTwoGivenDates(startTime, endTime);
        verify(roomRepository, times(1)).findAllowedRooms(anyList());
    }

}