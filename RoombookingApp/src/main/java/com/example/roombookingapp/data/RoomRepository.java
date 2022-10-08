package com.example.roombookingapp.data;

import com.example.roombookingapp.model.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
