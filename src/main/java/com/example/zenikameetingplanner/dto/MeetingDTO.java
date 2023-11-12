package com.example.zenikameetingplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDTO {
    @NotNull
    @Pattern(regexp = "^(VC|RC|SPEC|RS)$")
    private String meetingType;
    @NotNull
    @Min(2)
    private long nbrOfPerson ;
    @NotNull
    private String  meetingDate ;

}
