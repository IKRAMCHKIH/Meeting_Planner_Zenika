package com.example.zenikameetingplanner.model;

import com.example.zenikameetingplanner.enums.MeetingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private LocalDateTime date;
    private long nbrOfPerson;
    @ManyToOne
    private Room room ;

    @Enumerated(EnumType.STRING)
    private MeetingType meetType;

}
