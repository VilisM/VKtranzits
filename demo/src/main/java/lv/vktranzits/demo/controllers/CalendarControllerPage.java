
package lv.vktranzits.demo.controllers;

import lv.vktranzits.demo.repos.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//import jakarta.transaction.Transactional;

@Controller
public class CalendarControllerPage {

    @Autowired
    EventRepository er;

    @GetMapping("/calendar")
    // @ResponseBody
    public String welcome() {
        return "calendar";
    }
}