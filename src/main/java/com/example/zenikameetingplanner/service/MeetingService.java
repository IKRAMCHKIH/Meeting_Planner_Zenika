package com.example.zenikameetingplanner.service;

import com.example.zenikameetingplanner.exception.CapacityExp;
import com.example.zenikameetingplanner.exception.RoomNotFindExp;
import com.example.zenikameetingplanner.exception.EquipementExp;
import com.example.zenikameetingplanner.model.Meeting;

public interface MeetingService {
    String meetingReservation(Meeting meet) throws RoomNotFindExp , CapacityExp , EquipementExp;
}
