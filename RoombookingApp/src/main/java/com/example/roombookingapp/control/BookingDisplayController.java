package com.example.roombookingapp.control;

import com.example.roombookingapp.data.BookingRepository;
import com.example.roombookingapp.model.DateRequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BookingDisplayController {

    @Autowired
    BookingRepository bookingRepository;

    public ModelAndView showCalender(Date date){
        Map<String, Object> model = new HashMap<>();
        model.put("dateRequest", new DateRequestCommand(date));
        model.put("bookings", bookingRepository.findAllByDate(date));
        return new ModelAndView("home", model);
    }

    @RequestMapping("")
    public ModelAndView homePage(){
        Date date = new Date(new java.util.Date().getTime());
        return showCalender(date);
    }

    @RequestMapping("/calender")
    public ModelAndView calender(@RequestParam Date date){
        return showCalender(date);
    }

}
