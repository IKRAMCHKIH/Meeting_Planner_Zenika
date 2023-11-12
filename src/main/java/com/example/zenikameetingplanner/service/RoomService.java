package com.example.zenikameetingplanner.service;

import com.example.zenikameetingplanner.enums.MeetingType;
import com.example.zenikameetingplanner.model.Equipment;
import com.example.zenikameetingplanner.model.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {
    boolean isRoomCapacitySufficient(long numberOfParticipants, Room room);
    List<Room> getAvailableRoomsForReservation(List<Room> excludedRooms);
    List<Equipment> getAllEquipmentInRoom(Room room);
    List<Room> getEligibleRoomsForReservation(LocalDateTime startTime, LocalDateTime endTime);
    List<Room> getRoomsContainingDemandedEquipment(List<Equipment> requiredEquipment, List<Room> candidateRooms);
    List<Room> getRoomsSuitableForMeeting(MeetingType meetingType, List<Room> roomsWithSufficientCapacity);





}
