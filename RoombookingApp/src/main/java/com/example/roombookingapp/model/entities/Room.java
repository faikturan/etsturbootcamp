package com.example.roombookingapp.model.entities;

import com.example.roombookingapp.model.Layout;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "name cannot be blank.")
    private String name;

    @NotBlank(message = "location cannot be blank.")
    private String location;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<LayoutCapacity> capacities;

    public Room() {
    }

    public Room(String name, String location) {
        this.name = name;
        this.location = location;
        capacities = new ArrayList<>();
        for (Layout layout: Layout.values()) {
            capacities.add(new LayoutCapacity(layout, 0));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<LayoutCapacity> getCapacities() {
        return capacities;
    }

    public void setCapacities(List<LayoutCapacity> capacities) {
        this.capacities = capacities;
    }

    public void setCapacity(LayoutCapacity capacity){
        for (LayoutCapacity lc: capacities) {
            if (lc.getLayout() == capacity.getLayout()){
                lc.setCapacity(capacity.getCapacity());
            }
        }
    }
}
