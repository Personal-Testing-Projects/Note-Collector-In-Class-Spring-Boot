package lk.ijse.appspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//When using this annotstion we should return a view here
//This is called as server side rendering
//Bt nowadays this one is not using
//To return a view we can use Thymeleaf dependency
@RequestMapping("/welcome")
public class WelcomeController {
    @GetMapping
    public String viewWelcomeString(Model model) {
        model.addAttribute("message", "Note Collector");
        return "welcome";
    }
}
