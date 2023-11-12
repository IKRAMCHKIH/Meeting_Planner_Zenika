package com.example.zenikameetingplanner.mapper;


import com.example.zenikameetingplanner.dto.MeetingDTO;
import com.example.zenikameetingplanner.model.Meeting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MeetingMapper {
    @Mapping(target="meetType", source="meetingType")
    @Mapping(target="date", source="meetingDate" , dateFormat = "yyyy-MM-dd HH:mm")
    Meeting meetingRequestToMeetingEntity(MeetingDTO meetingRequest);

}


