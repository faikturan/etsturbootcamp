package com.example.roombookingapp.services;

import com.example.roombookingapp.data.BookingRepository;
import com.example.roombookingapp.data.RoomRepository;
import com.example.roombookingapp.data.UserRepository;
import com.example.roombookingapp.model.Layout;
import com.example.roombookingapp.model.entities.LayoutCapacity;
import com.example.roombookingapp.model.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataInitialization {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;


    @EventListener(ApplicationReadyEvent.class)
    public void initData(){

        List<Room> rooms = roomRepository.findAll();
        if (rooms.size() == 0) {
            Room blueRoom = new Room("Blue meeting room", "1st Floor");
            blueRoom.setCapacity(new LayoutCapacity(Layout.BOARD, 8));
            blueRoom.setCapacity(new LayoutCapacity(Layout.THEATER, 16));
            roomRepository.save(blueRoom);


            Room redRoom = new Room("Red meeting room", "2nd Floor");
            blueRoom.setCapacity(new LayoutCapacity(Layout.BOARD, 12));
            blueRoom.setCapacity(new LayoutCapacity(Layout.USHAPE, 26));
            roomRepository.save(redRoom);

            Room confRoom = new Room("Main Conference Room", "1st Floor");
            blueRoom.setCapacity(new LayoutCapacity(Layout.THEATER, 80));
            blueRoom.setCapacity(new LayoutCapacity(Layout.USHAPE, 40));
            roomRepository.save(confRoom);


        }


    }

}
