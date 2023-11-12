package com.example.zenikameetingplanner.utils;

import com.example.zenikameetingplanner.enums.EquipmentType;
import com.example.zenikameetingplanner.enums.MeetingType;
import com.example.zenikameetingplanner.model.Equipment;

import java.util.Arrays;
import java.util.List;

public class EquipementUtils {
    public static List<Equipment> getEquipmentsNeededForEachMeeting(MeetingType MeetingType){
        switch (MeetingType){
            case VC:
                return Arrays.asList (
                        new Equipment (1L, EquipmentType.ECRAN),
                        new Equipment (2L, EquipmentType.PIEUVRE),
                        new Equipment (3L , EquipmentType.WEBCAM)
                );

            case SPEC:
                return Arrays.asList (
                        new Equipment (1l ,EquipmentType.TABLEAU)
                );
            case RC:
                return Arrays.asList (
                        new Equipment (1L, EquipmentType.ECRAN),
                        new Equipment (2L, EquipmentType.PIEUVRE),
                        new Equipment (3L, EquipmentType.TABLEAU)
                );
        }
        return null ;
    }
}
