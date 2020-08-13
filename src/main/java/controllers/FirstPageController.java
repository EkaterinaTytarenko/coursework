package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;


@Controller
public class FirstPageController {

    @GetMapping("/")
    public String login(Principal principal) {
        if(principal != null) {
            return "redirect:/hello";
        }
        return "login";
    }


}
