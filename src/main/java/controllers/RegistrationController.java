package controllers;

import entities.Role;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import repos.UserRepo;
import services.UserService;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        String name=user.getUsername();
        User userFromDb = (User) userService.loadUserByUsername(name);
        if (userFromDb != null) {
            model.put("message", "User exists! Please,try again");
            return "registration";
        }

        String password=user.getPassword().split(",")[0];
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.addUser(user);

        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String register( Map<String, Object> model){
        model.put("message", "");
        return "registration";
    }
}
