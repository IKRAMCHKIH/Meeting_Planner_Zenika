package com.example.zenikameetingplanner.repository;

import com.example.zenikameetingplanner.model.Equipment;
import com.example.zenikameetingplanner.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipementRepository  extends JpaRepository<Equipment, Long> {
}
