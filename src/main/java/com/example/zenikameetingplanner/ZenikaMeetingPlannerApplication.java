package com.example.zenikameetingplanner;

import com.example.zenikameetingplanner.enums.EquipmentType;
import com.example.zenikameetingplanner.enums.MeetingType;
import com.example.zenikameetingplanner.model.Equipment;
import com.example.zenikameetingplanner.model.Meeting;
import com.example.zenikameetingplanner.model.Room;
import com.example.zenikameetingplanner.repository.EquipementRepository;
import com.example.zenikameetingplanner.repository.MeetingRepository;
import com.example.zenikameetingplanner.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ZenikaMeetingPlannerApplication implements CommandLineRunner {

    @Autowired
    MeetingRepository meetingRepository ;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    EquipementRepository equipementRepository ;

    public static void main(String[] args) {
        SpringApplication.run(ZenikaMeetingPlannerApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Equipment equipment1 = new Equipment (1L , EquipmentType.ECRAN);
        Equipment equipment2 = new Equipment (2L , EquipmentType.NEANT);
        Equipment equipment3 = new Equipment (3L , EquipmentType.PIEUVRE);
        Equipment equipment4 = new Equipment (4L , EquipmentType.TABLEAU);
        Equipment equipment5 = new Equipment (5L , EquipmentType.WEBCAM);

        equipementRepository.save (equipment1);
        equipementRepository.save (equipment2);
        equipementRepository.save (equipment3);
        equipementRepository.save (equipment4);
        equipementRepository.save (equipment5);

        List<Equipment> equipmentForRoom5 = new ArrayList<>();
        equipmentForRoom5.add (equipment1);
        equipmentForRoom5.add (equipment3);
        equipmentForRoom5.add (equipment5);

        Room r1 =  new Room ( 1l,"c001" , 12L , null,null);
        Room r2 =new Room ( 2l,"c002" , 23L , null,null);
        Room r3 = new Room (3l,"c003" , 17L , null,null);
        Room r4 = new Room ( 4l,"c004" , 39L , null, null);
        Room r5 = new Room (5l,"c005" , 27L , null,equipmentForRoom5);
        Room r6 = new Room (6l,"c006" , 24L , null,null);
        Meeting reservation1 = new Meeting (1L, LocalDateTime.of (2022 , 11, 23 , 8 ,00) ,10 , r1 , MeetingType.VC  );
        Meeting reservation2 = new Meeting (2L, LocalDateTime.of (2022 , 11, 23 , 9 ,00) ,16 , r2 , MeetingType.RC  );
        Meeting reservation3 = new Meeting (3L, LocalDateTime.of (2022 , 11, 23 , 10 ,00) ,6 , r3 , MeetingType.SPEC  );
        Meeting reservation4 = new Meeting (4L, LocalDateTime.of (2022 , 11, 23 , 11 ,00) ,10 , r4 , MeetingType.RC  );
        Meeting reservation5 = new Meeting (5L, LocalDateTime.of (2022 , 11, 23 , 12 ,00) ,5 , r5 , MeetingType.RS  );
        Meeting reservation6 = new Meeting (6L, LocalDateTime.of (2022 , 11, 23 , 14 ,00) ,3 , r2 , MeetingType.SPEC  );
        roomRepository.save (r1);
        roomRepository.save (r2);
        roomRepository.save (r3);
        roomRepository.save (r4);
        roomRepository.save (r5);
        roomRepository.save (r6);
        meetingRepository.save (reservation1);
        meetingRepository.save (reservation2);
        meetingRepository.save (reservation3);
        meetingRepository.save (reservation4);
        meetingRepository.save (reservation5);
        meetingRepository.save (reservation6);



    }

}
