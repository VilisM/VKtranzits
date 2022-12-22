
package lv.vktranzits.demo.controllers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lv.vktranzits.demo.models.Event;
import lv.vktranzits.demo.repos.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//import jakarta.transaction.Transactional;
import java.time.LocalDateTime;

@Controller
public class CalendarControllerPage {

    @Autowired
    EventRepository er;

    // @GetMapping("/calendar")
    // // @ResponseBody
    // public String welcome() {
    //     return "calendar-page";
    // }
}