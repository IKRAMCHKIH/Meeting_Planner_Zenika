package com.example.zenikameetingplanner.service.impl;

import com.example.zenikameetingplanner.enums.MeetingType;
import com.example.zenikameetingplanner.model.Equipment;
import com.example.zenikameetingplanner.model.Meeting;
import com.example.zenikameetingplanner.model.Room;
import com.example.zenikameetingplanner.repository.MeetingRepository;
import com.example.zenikameetingplanner.service.RoomService;
import com.example.zenikameetingplanner.repository.RoomRepository;
import com.example.zenikameetingplanner.utils.EquipementUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {
    RoomRepository roomRepository;
    MeetingRepository meetingRepository;

    @Override
    public boolean isRoomCapacitySufficient(long numberOfParticipants, Room room) {
        long  nbrAllowedInARoomWithCovid = room.getRoomCapacity () - room.getRoomCapacity () * 30 / 100 ;
        return numberOfParticipants < nbrAllowedInARoomWithCovid ;
    }

    @Override
    public List<Room> getAvailableRoomsForReservation(List<Room> excludedRooms) {
        return roomRepository.findAllowedRooms (excludedRooms);
    }

    @Override
    public List<Equipment> getAllEquipmentInRoom(Room room) {
        return roomRepository.getEquipmentInARoom (room.getRoomName ());
    }

    @Override
    public List<Room> getEligibleRoomsForReservation(LocalDateTime startTime, LocalDateTime endTime) {
        List<Meeting> reservationList = meetingRepository.findAllMeetingBetweenTwoGivenDates(startTime ,  endTime);
        List<Room> notAllowedRooms = reservationList.stream ().map (reservation1 -> reservation1.getRoom ()).collect(Collectors.toList());
        return  getAvailableRoomsForReservation(notAllowedRooms);
    }

    @Override
    public List<Room> getRoomsContainingDemandedEquipment(List<Equipment> requiredEquipment, List<Room> rooms) {
        List<Room> RoomsReadyForReservation = new ArrayList<>();
        for (Room room : rooms){
            if ( getAllEquipmentInRoom (room).containsAll (requiredEquipment)){
                RoomsReadyForReservation.add (room);
            }
        }
        return  RoomsReadyForReservation ;
    }

    @Override
    public List<Room> getRoomsSuitableForMeeting(MeetingType meetingType, List<Room> roomsWithSufficientCapacity) {
        if(meetingType == meetingType.RS){
            return roomsWithSufficientCapacity;
        }
        else {
            List<Equipment> equipmentsNeededForTheMeeting = EquipementUtils.getEquipmentsNeededForEachMeeting (meetingType);

            return getRoomsContainingDemandedEquipment (equipmentsNeededForTheMeeting, roomsWithSufficientCapacity);
        }
    }
}
