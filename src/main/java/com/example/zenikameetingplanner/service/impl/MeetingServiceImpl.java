package com.example.zenikameetingplanner.service.impl;

import com.example.zenikameetingplanner.exception.CapacityExp;
import com.example.zenikameetingplanner.exception.RoomNotFindExp;
import com.example.zenikameetingplanner.exception.EquipementExp;
import com.example.zenikameetingplanner.model.Meeting;
import com.example.zenikameetingplanner.model.Room;
import com.example.zenikameetingplanner.repository.MeetingRepository;
import com.example.zenikameetingplanner.service.MeetingService;
import com.example.zenikameetingplanner.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MeetingServiceImpl implements MeetingService {
    private MeetingRepository reservationRepository ;
    private RoomService roomService ;
    @Override
    public String meetingReservation(Meeting meet) throws RoomNotFindExp , CapacityExp, EquipementExp {

        List<Room> eligibleRoomsForReservation =  roomService.getEligibleRoomsForReservation(meet.getDate ().minusHours (1) , meet.getDate ().plusHours (1));
        if (eligibleRoomsForReservation.isEmpty ()){
            throw new RoomNotFindExp("there is no empty room for now please check later ");
        }
        else {
            // check of capacity (70 % in covid /!\ )
            eligibleRoomsForReservation =  eligibleRoomsForReservation.stream ().filter (salle -> roomService.isRoomCapacitySufficient(meet.getNbrOfPerson(), salle) ).collect(Collectors.toList());
            if (eligibleRoomsForReservation.isEmpty ()){
                throw  new CapacityExp("Any Room available with capacity demanded check later ");
            }
            else {
                // check by MeetType
                eligibleRoomsForReservation =  roomService.getRoomsSuitableForMeeting (meet.getMeetType(), eligibleRoomsForReservation);
                if (eligibleRoomsForReservation.isEmpty ()){
                    throw  new EquipementExp ("Rooms dosn't have required equipement ");
                }
            }
        }
        meet.setRoom (eligibleRoomsForReservation.get (0));
        reservationRepository.save(meet);
        return "The room is reserved "+meet.getRoom ().getRoomName ();
    }
    }

