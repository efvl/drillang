package app.prog.evv.drillang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/home")
    public String getHome(){
        return "welcome to home";
    }

}
