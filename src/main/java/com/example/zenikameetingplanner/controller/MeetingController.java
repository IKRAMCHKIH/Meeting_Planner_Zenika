package com.example.zenikameetingplanner.controller;

import com.example.zenikameetingplanner.dto.MeetingDTO;
import com.example.zenikameetingplanner.exception.CapacityExp;
import com.example.zenikameetingplanner.exception.EquipementExp;
import com.example.zenikameetingplanner.exception.InputExp;
import com.example.zenikameetingplanner.exception.RoomNotFindExp;
import com.example.zenikameetingplanner.mapper.MeetingMapper;
import com.example.zenikameetingplanner.model.Meeting;
import com.example.zenikameetingplanner.service.MeetingService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Null;

@RestController
@AllArgsConstructor
public class MeetingController {

    private MeetingService meetingService;


    private MeetingMapper meetingMapper;

   /* public MeetingController(MeetingMapper meetingMapper) {
        this.meetingMapper = meetingMapper;
    }*/

    @PostMapping("/")
    public ResponseEntity<String> reservation(@Valid @RequestBody  MeetingDTO meetingRequest , BindingResult bindingResult) throws RoomNotFindExp , CapacityExp, EquipementExp, InputExp {

        if (bindingResult.hasErrors ()){
            throw new InputExp (bindingResult.getAllErrors ().toString ());
        }

        Meeting meeting = meetingMapper.meetingRequestToMeetingEntity (meetingRequest);


        return ResponseEntity.status (HttpStatus.CREATED).body (meetingService.meetingReservation(meeting));
    }

    }
