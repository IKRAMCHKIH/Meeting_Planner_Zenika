package com.example.zenikameetingplanner.model;

import com.example.zenikameetingplanner.enums.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EquipmentType equipmentName ;

    // Equals method override for custom comparison logic
    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself, return true
        if (this == o) {
            return true;
        }

        // Check if o is an instance of Equipment or not
        if (!(o instanceof Equipment)) {
            return false;
        }

        // Typecast o to Equipment for data member comparison
        Equipment e = (Equipment) o;

        // Compare the equipmentName and return accordingly
        return Objects.equals(this.equipmentName, e.equipmentName);
    }


}
